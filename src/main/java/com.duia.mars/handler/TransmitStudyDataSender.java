package com.duia.mars.handler;

import com.duia.mars.dto.SendTransmitResult;
import com.duia.mars.dto.TransmitStudyData;
import com.duia.mars.http.HttpResponseEntity;
import com.duia.mars.http.HttpSender;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 传输学习数据发送器
 * @author xingshaofei
 * @date 2018-01-13 下午 8:54
 */
@Component
public class TransmitStudyDataSender {

    private static final Logger logger = LoggerFactory.getLogger(TransmitStudyDataSender.class);

    //@Value("${send.study-data.url}")
    //private String sendStudyDataUrl;

    /**
     * 发送数据
     * @param tsdList 要发送的传输学习数据
     * @param sendStudyDataUrl 发送学习记录到此url
     * @return 发送结果
     */
    public SendTransmitResult sendTransmitStudyData(List<TransmitStudyData> tsdList, String sendStudyDataUrl) {

        String path = sendStudyDataUrl;

        HttpSender httpSender = new HttpSender();

        int code = 0;
        String message = "";

        try {

            for (TransmitStudyData tsd : tsdList) {

                logger.info("本次传输到此Url：{}", path);
                logger.info("本次传输的学习记录数据：{}", tsd);

                Map<String, Object> params = buildParams(tsd);

                HttpResponseEntity hre = httpSender.post(sendStudyDataUrl, params);

                // http状态码
                int statusCode = hre.getStatusCode();

                // 根据状态码组织数据
                if (200 == statusCode) {    // http状态成功

                    String result = hre.getEntityString();

                    logger.info("发送数据时返回结果：{}", result);

                    // 解析数据
                    SendTransmitResult str = null;
                    try {

                        Gson gson = new GsonBuilder().create();
                        str = gson.fromJson(result, SendTransmitResult.class);

                    } catch (Exception e) {

                        logger.error("解析返回结果时异常", e);

                        str.setCode(500);
                        str.setMessage("解析返回结果时异常");
                    }

                    // 数据的code和message
                    code = str.getCode();
                    message = str.getMessage();

                    if (200 != code)
                        break ;

                } else {                // http状态失败

                    throw new RuntimeException("Http状态失败，状态：" + statusCode);
                }
            }

        } catch (Exception e) {
            logger.error("发送数据时异常", e);
            throw new RuntimeException("发送数据时异常", e);
        }

        SendTransmitResult str = new SendTransmitResult();
        str.setCode(code);
        str.setMessage(message);

        return str;
    }

    /**
     * 组建参数
     * @param tsd
     * @return
     */
    private Map<String, Object> buildParams(TransmitStudyData tsd) {

        Map<String, Object> params = new HashMap<>();

        params.put("dataDate", tsd.getDataDate());

        params.put("totalRecordCount", tsd.getTotalRecordCount());
        params.put("totalBatchCount", tsd.getTotalBatchCount());
        params.put("currentBatchNum", tsd.getCurrentBatchNum());
        params.put("currentRecordCount", tsd.getCurrentRecordCount());

        params.put("sendTime", tsd.getSendTime());

        params.put("data", tsd.getEncryptData());

        params.put("signature", tsd.getSignature());

        return params;
    }


}
