package com.duia.mars.schedule;

import com.duia.mars.common.constant.Common;
import com.duia.mars.common.util.DateUtils;
import com.duia.mars.common.util.EmailUtils;
import com.duia.mars.dto.SendTransmitResult;
import com.duia.mars.dto.StudyData;
import com.duia.mars.dto.TransmitStudyData;
import com.duia.mars.exception.NoneDataException;
import com.duia.mars.handler.TransmitStudyDataHandler;
import com.duia.mars.handler.TransmitStudyDataSender;
import com.duia.mars.model.ScheduleRecord;
import com.duia.mars.model.TransmitStudyDataHistory;
import com.duia.mars.mongodb.dao.TrackClassCourseLiveTransmitRepository;
import com.duia.mars.mongodb.handler.TransmitStudyDataMeanHandler;
import com.duia.mars.mongodb.model.TransmitStudyDataMean;
import com.duia.mars.service.ScheduleRecordService;
import com.duia.mars.service.TrackClassCourseLiveService;
import com.duia.mars.service.TransmitStudyDataHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * 传输学习数据的调度
 */
@Component
@Configurable
public class CustomTransmitStudyDataSchedule implements ISchedule {

    private static final Logger logger = LoggerFactory.getLogger(CustomTransmitStudyDataSchedule.class);

    @Resource
    private ScheduleRecordService scheduleRecordService;

    @Resource
    private TransmitStudyDataHistoryService transmitStudyDataHistoryService;

    @Resource
    private TrackClassCourseLiveService trackClassCourseLiveService;

    @Resource
    private TransmitStudyDataHandler trackClassCourseLiveTransmitHandler;

    @Resource
    private TransmitStudyDataMeanHandler trackClassCourseLiveTransmitMeanHandler;

    @Resource
    private TransmitStudyDataSender transmitStudyDataSender;

    @Resource
    private TrackClassCourseLiveTransmitRepository trackClassCourseLiveTransmitRepository;

    @Value("${send.study-data.url}")
    private String sendStudyDataUrl;

    private boolean send = true;   // todo for test


    @Override
    //@Scheduled(cron = "0 0 1 * * ?")    // 每天01:00
    @Scheduled(cron = "0 47 18 15 6 ?")
    public void schedule() {

        // todo for send history data(2017-06-29 至 2018-01-24) to product env
        sendStudyDataUrl = "http://123.56.3.50:8097/recordrecv";    // 8095 测试

        String beginDay = "2018-03-06"; // 如果使用现在表，则最早为2018-03-06
        String endDay = "2018-06-15";   // 如果使用备份表，则最晚到2018-03-06（不含）

        while (true) {

            if (beginDay.equals(endDay))
                break ;

            schedule(beginDay);

            //Date beginDate = DateUtils.nextMonth(DateUtils.stringtoDate(beginDay, "yyyy-MM-dd"), 1);
            Date beginDate = DateUtils.nextDay(DateUtils.stringtoDate(beginDay, "yyyy-MM-dd"), 1);

            beginDay = DateUtils.format(beginDate, "yyyy-MM-dd");
        }


        logger.info("【传输学习数据(本次时间段)调度】结束.....");

    }

    public void schedule(String before) {

        logger.info("【传输学习数据(每日)调度】开始.....");

        // 调度时间，日期
        Date scheduleTime = new Date();
        String scheduleDay = DateUtils.dateToString(scheduleTime, DateUtils.LONG_DATE_FORMAT);

        // 调度记录
        ScheduleRecord record = new ScheduleRecord(Common.ScheduleType.TYPE_TRANSMIT_STUDY_DATA_INDEX, scheduleTime, CustomTransmitStudyDataSchedule.class.getName());
        record.setScheduleTime(scheduleTime);

        // 开始记录调度使用时长
        long beginMil = System.currentTimeMillis();

        try {

            // 保存调度记录
            //scheduleRecordService.save(record);


            // 昨天
            String beforeDay = DateUtils.befoDay(DateUtils.LONG_DATE_FORMAT);

            // todo for test
            beforeDay = before;

            // 昨天时间区间
            String beginTime = beforeDay + " 00:00:00";
            String endTime = beforeDay + " 23:59:59";


            // todo for test
            logger.info("昨天：{}，调度日期：{}", beforeDay, scheduleDay);


            // 获取昨天数据
            long s = System.currentTimeMillis();

            List<? extends StudyData> studyDataList = loadStudyData(beginTime, endTime);

            logger.info("第1步：获取数据用时：{}ms，获取数据量：{}", System.currentTimeMillis() - s, studyDataList.size());

            if (studyDataList.isEmpty())
                throw new NoneDataException(String.format(
                        "没有获取到学习记录数据！可能有问题。参数：数据日期：%s，时间区间：%s 至 %s"
                        , beforeDay, beginTime, endTime));


            // 组织成传输数据
            s = System.currentTimeMillis();

            List<TransmitStudyData> transmitStudyDataList = buildTransmitStudyData(beforeDay, studyDataList);

            logger.info("第2步：组织传输数据用时：{}ms", System.currentTimeMillis() - s);


            // 保存数据
            s = System.currentTimeMillis();

            saveTransmitStudyData(transmitStudyDataList);

            logger.info("第3步：保存数据用时：{}ms", System.currentTimeMillis() - s);


            // 发送数据

            // 新建发送历史
            //TransmitStudyDataHistory history = saveNewHistory(transmitStudyDataList);

            if (!send)
                throw new RuntimeException("不发送");

            // 发送数据
            s = System.currentTimeMillis();

            SendTransmitResult sendTransmitResult = sendTransmitStudyData(transmitStudyDataList);

            long sendUseMills = System.currentTimeMillis() - s;

            logger.info("第4步：发送数据用时：{}ms", sendUseMills);


            // 更新发送历史
            //updateHistory(history, sendTransmitResult, (int)(sendUseMills));

            record.setStatus(ScheduleRecord.STATUS_SUCCESS);

        } catch (Exception e) {

            logger.error("【传输学习数据(每日)调度】发生异常", e);

            record.setStatus(ScheduleRecord.STATUS_FAILED);
            record.setRemark(e.getMessage());

            sendExceptionEmail(scheduleDay, "22:30", e.getMessage());
        }

        // 本次调度总耗时
        long totalUseMills = System.currentTimeMillis() - beginMil;

        logger.info("本次调度耗时： " + totalUseMills);

        // 设置调度记录执行时间和状态
        record.setExecuteTime(String.valueOf(totalUseMills));

        // 更新调度记录
        //scheduleRecordService.update(record);

        logger.info("【传输学习数据(每日)调度】结束.....");
    }

    private void print(List<? extends StudyData> studyDataList) {

        for (StudyData sd : studyDataList) {

            logger.info(sd.toString());
        }
    }


    /**
     * 获取学习记录数据
     * @param beginTime 时间区间：开始时间
     * @param endTime   时间区间：结束时间
     * @return
     */
    private List<? extends StudyData> loadStudyData(String beginTime, String endTime) {

        // 根据create_time
        //return trackClassCourseLiveService.findStudyDataByCreateTime(beginTime, endTime);
        // 根据inTime
        // return trackClassCourseLiveService.findStudyDataByInTime(beginTime, endTime);
        // 根据enterTime
        return trackClassCourseLiveService.findStudyDataByEnterTime(beginTime, endTime);
    }

    /**
     * 组建传输学习数据
     * @param dataDate 数据日期
     * @param studyDataList 学习数据
     * @return
     */
    private List<TransmitStudyData> buildTransmitStudyData(String dataDate, List<? extends StudyData> studyDataList) {

        Date now = new Date();

        List<TransmitStudyData> transmitStudyDataList = trackClassCourseLiveTransmitHandler.buildBaseTransmitStudyData(dataDate, studyDataList);

        // 压缩基本数据
        transmitStudyDataList = trackClassCourseLiveTransmitHandler.buildCompressedData(transmitStudyDataList);
        // 组织加密基本数据
        transmitStudyDataList = trackClassCourseLiveTransmitHandler.buildEncryptData(transmitStudyDataList);
        // 设置发送时间
        transmitStudyDataList = trackClassCourseLiveTransmitHandler.setSendTime(now, transmitStudyDataList);
        // 设置加密签名
        transmitStudyDataList = trackClassCourseLiveTransmitHandler.buildSignature(transmitStudyDataList);

        return transmitStudyDataList;
    }

    /**
     * 保存传输学习数据
     * @param transmitStudyDataList 要被保存的传输学习数据
     */
    private void saveTransmitStudyData(List<TransmitStudyData> transmitStudyDataList) {

        // 组织
        List<? extends TransmitStudyDataMean> tsdMeanList = buildTransmitStudyDataToMean(trackClassCourseLiveTransmitMeanHandler, transmitStudyDataList);

        // 保存
        saveTransmitStudyDataMean(trackClassCourseLiveTransmitRepository, tsdMeanList);
    }

    /**
     * 保存新的传输历史
     * @param transmitStudyDataList
     * @return
     */
    private TransmitStudyDataHistory saveNewHistory(List<TransmitStudyData> transmitStudyDataList) {

        // 组织发送记录
        TransmitStudyDataHistory history = buildTransmitStudyDataHistory(transmitStudyDataList);
        // 保存发送记录
        saveHistory(history);

        return history;
    }


    /**
     * 更新发送历史的发送结果
     * @param history
     * @param str
     */
    private void updateHistory(TransmitStudyDataHistory history, SendTransmitResult str, int sendDuration) {

        int code = str.getCode();
        if (200 == code)
            history.setSendResultStatus(TransmitStudyDataHistory.SEND_RESULT_STATUS_SUCCESS);
        else
            history.setSendResultStatus(TransmitStudyDataHistory.SEND_RESULT_STATUS_FAILED);

        history.setSendResultContent(str.getMessage());

        history.setSendDuration(sendDuration);

        transmitStudyDataHistoryService.update(history);
    }

    /**
     * 发送数据
     * @param tsdList 要发送的传输学习数据
     * @return 发送结果
     */
    private SendTransmitResult sendTransmitStudyData(List<TransmitStudyData> tsdList) {

        return transmitStudyDataSender.sendTransmitStudyData(tsdList, sendStudyDataUrl);
    }


    /**
     * 保存发送历史
     * @param history
     */
    private void saveHistory(TransmitStudyDataHistory history) {

        transmitStudyDataHistoryService.save(history);
    }

    /**
     * 组建发送历史
     * @param tsdList 传输学习数据列表
     * @return
     */
    private TransmitStudyDataHistory buildTransmitStudyDataHistory(List<TransmitStudyData> tsdList) {

        if (null == tsdList || tsdList.isEmpty())
            throw new IllegalArgumentException("参数不可空");

        TransmitStudyData tsd = tsdList.get(0);

        TransmitStudyDataHistory tsdh = new TransmitStudyDataHistory();

        Date now = new Date();

        tsdh.setDataDate(tsd.getDataDate());
        tsdh.setTotalRecordCount(tsd.getTotalRecordCount());
        tsdh.setTotalBatchCount(tsd.getTotalBatchCount());
        tsdh.setCurrentSendTimes(1);                // 本次是第几次发送
        tsdh.setSendResultStatus(TransmitStudyDataHistory.SEND_RESULT_STATUS_UNSEND);
        tsdh.setSendTime(tsd.getSendTime());
        tsdh.setCreateTime(now);
        tsdh.setUpdateTime(now);

        return tsdh;
    }

    /**
     * 组织用于保存的传输学习数据
     * @param transmitStudyDataMeanHandler
     * @param tsdList 传输学习数据列表
     * @return
     */
    private List<? extends TransmitStudyDataMean> buildTransmitStudyDataToMean(
            TransmitStudyDataMeanHandler transmitStudyDataMeanHandler, List<TransmitStudyData> tsdList) {

        return transmitStudyDataMeanHandler.buildTransmitStudyDataMean(tsdList);
    }

    /**
     * 保存传输学习数据mongo格式
     * @param mongoRepository
     * @param tsdMeanList 传输学习数据mongo格式
     */
    private void saveTransmitStudyDataMean(MongoRepository mongoRepository, List<? extends TransmitStudyDataMean> tsdMeanList) {

        List saved = mongoRepository.save(tsdMeanList);

        if (saved.size() == tsdMeanList.size())
            logger.info("保存传输数据成功，size：{}", tsdMeanList.size());
        else
            logger.info("保存传输数据：{}条，但需要保存的有：{}条", saved.size(), tsdMeanList.size());
    }

    /**
     * 发送异常Email
     */
    private void sendExceptionEmail(String scheduleDay, String reTransmitTime, String exceptionMessage) {

        EmailUtils emailUtils = new EmailUtils();

        String subject = "调度失败：传输学习记录数据错误";

        String content = String.format(
                "啊哦，看到这封邮件表明昨天的传输学习记录数据调度失败了，请在当天%s %s失败重调前解决！当前时间：%s\n" +
                "异常信息：\n" +
                "%s"
                , scheduleDay, reTransmitTime, DateUtils.getNow(), exceptionMessage);

        emailUtils.sendEmail(subject, content);
    }


    @Override
    public void restore(Integer scheduleId) {

        logger.info("传输学习数据调度不可进行恢复操作！");
    }

}
