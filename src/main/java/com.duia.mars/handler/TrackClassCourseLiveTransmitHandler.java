package com.duia.mars.handler;

import com.duia.mars.common.util.Base64Util;
import com.duia.mars.common.util.CollectUtils;
import com.duia.mars.common.util.CompressUtils;
import com.duia.mars.common.util.DateUtils;
import com.duia.mars.dto.StudyData;
import com.duia.mars.dto.TransmitStudyData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

/**
 * 传输学习数据处理器-正课直播
 * @author xingshaofei
 * @date 2017-12-27 下午 6:51
 */
@Component
public class TrackClassCourseLiveTransmitHandler implements TransmitStudyDataHandler {

    private static final Logger logger = LoggerFactory.getLogger(TrackClassCourseLiveTransmitHandler.class);

    @Resource
    private StudyDataHandler trackClassCourseLiveStudyDataHandler;

    @Resource
    private SignatureBuilder studyDataSignatureBuilder;

    @Value("${send.study-data.batch-size}")
    private int batchSize;                                 // 每批记录数


    @Override
    public List<TransmitStudyData> buildBaseTransmitStudyData(String dataDate, List<? extends StudyData> studyDataList) {

        if (null == studyDataList || studyDataList.isEmpty())
            return new ArrayList<>();

        // 数据的日期 dataDate

        // 数据总记录数
        int totalRecordCount = studyDataList.size();

        // 分批
        List<? extends StudyData>[] lists = CollectUtils.split(studyDataList, batchSize);

        if (null == lists || lists.length == 0)
            throw new RuntimeException("将数据分批时出现异常：分批后数据为空");


        // 分批次数据
        List<TransmitStudyData> tsdList = new ArrayList<>(lists.length);

        // 当前批次
        int currentBatchNum = 1;

        // 组织批次数据
        for (List<? extends StudyData> l : lists) {

            // 传输数据
            TransmitStudyData tsd = new TransmitStudyData();

            // 数据的日期
            tsd.setDataDate(dataDate);

            // 数据总记录数
            tsd.setTotalRecordCount(totalRecordCount);

            // 总批数
            tsd.setTotalBatchCount(lists.length);

            // 当前批次
            tsd.setCurrentBatchNum(currentBatchNum++);

            // 本批次记录数
            tsd.setCurrentRecordCount(l.size());

            // 数据
            String data = trackClassCourseLiveStudyDataHandler.dataToString(l);
            tsd.setData(data);

            // 发送时间
            // tsd.setSendTime(now);

            // 加密
            //tsd.setSignature(buildSign(tsd));

            // 将传输数据加入到传输数据列表
            tsdList.add(tsd);
        }

        return tsdList;
    }

    @Override
    public List<TransmitStudyData> buildCompressedData(List<TransmitStudyData> tsdList) {

        for (TransmitStudyData tsd : tsdList) {

            byte[] compressData = compressData(tsd.getData());
            tsd.setCompressedData(compressData);

            // 为避免内存溢出，清除data数据
            tsd.setData(null);
        }

        return tsdList;
    }

    @Override
    public List<TransmitStudyData> buildEncryptData(List<TransmitStudyData> tsdList) {

        for (TransmitStudyData tsd : tsdList) {

            String encryptData = encryptData(tsd.getCompressedData());
            tsd.setEncryptData(encryptData);
        }

        return tsdList;
    }

    @Override
    public List<TransmitStudyData> setSendTime(Date sendTime, List<TransmitStudyData> tsdList) {

        for (TransmitStudyData tsd : tsdList) {

            tsd.setSendTime(sendTime);
        }

        return tsdList;
    }

    @Override
    public List<TransmitStudyData> buildSignature(List<TransmitStudyData> tsdList) {

        for (TransmitStudyData tsd : tsdList) {

            tsd.setSignature(buildSign(tsd));
        }

        return tsdList;
    }


    /**
     * 压缩数据
     * @param data 要被压缩的字符串
     * @return 压缩后的字节
     * @throws RuntimeException 如果发生IOException异常抛出
     */
    private byte[] compressData(String data) {

        String charset = "UTF-8";

        try {

            return CompressUtils.compressByDeflater(data, charset);

        } catch (IOException e) {
            logger.error("压缩数据异常", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 加密，使用Base64
     * @param data 要被加密的字符串
     * @return
     */
    private String encryptData(byte[] data) {

        String charset = "UTF-8";

        try {

            return Base64Util.encodeToString(data, charset);
        } catch (UnsupportedEncodingException e) {

            logger.error("按照Base64对数据进行编码时出现异常", e);
            throw new IllegalArgumentException("参数无法进行Base64编码", e);
        }
    }

    /**
     * 加密，使用Base64
     * @param data 要被加密的字符串
     * @return
     */
    private String encryptData(String data) {

        String charset = "UTF-8";

        try {

            return Base64Util.encodeToString(data, charset);
        } catch (UnsupportedEncodingException e) {

            logger.error("按照Base64对数据进行编码时出现异常", e);
            throw new IllegalArgumentException("参数无法进行Base64编码", e);
        }
    }

    // 签名
    private String buildSign(TransmitStudyData tsd) {

        TreeMap<String, String> treeMap = new TreeMap<>();

        treeMap.put("dataDate", tsd.getDataDate().toString());
        treeMap.put("totalRecordCount", tsd.getTotalRecordCount().toString());
        treeMap.put("totalBatchCount", tsd.getTotalBatchCount().toString());
        treeMap.put("currentBatchNum", tsd.getCurrentBatchNum().toString());
        treeMap.put("currentRecordCount", tsd.getCurrentRecordCount().toString());
        treeMap.put("sendTime", DateUtils.format(tsd.getSendTime(), DateUtils.FORMAT_ONE));
        treeMap.put("data", tsd.getEncryptData());

        return studyDataSignatureBuilder.build(treeMap);
    }


}
