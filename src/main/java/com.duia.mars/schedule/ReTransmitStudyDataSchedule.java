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
 * 重新传输学习记录的调度
 */
@Component
@Configurable
public class ReTransmitStudyDataSchedule implements ISchedule {

    private static final Logger logger = LoggerFactory.getLogger(ReTransmitStudyDataSchedule.class);

    @Resource
    private ScheduleRecordService scheduleRecordService;

    @Resource
    private TrackClassCourseLiveService trackClassCourseLiveService;

    @Resource
    private TransmitStudyDataHistoryService transmitStudyDataHistoryService;

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


    @Override
    //@Scheduled(cron = "0 30 22 * * ?")    // 每天22:30
    @Scheduled(cron = "0 30 22 1 1 ?")    // 每天22:30// todo not execute
    public void schedule() {

        logger.error("【重新传输学习数据(每日)调度】开始.....");

        Date now = new Date();

        // 调度时间，日期
        Date scheduleTime = now;
        String scheduleDay = DateUtils.dateToString(scheduleTime, DateUtils.LONG_DATE_FORMAT);

        String beforeDay = DateUtils.befoDay(DateUtils.LONG_DATE_FORMAT);

        String beginTime = beforeDay + " 00:00:00";
        String endTime = beforeDay += " 23:59:59";

        ScheduleRecord record = new ScheduleRecord(Common.ScheduleType.TYPE_RE_TRANSMIT_STUDY_DATA_INDEX, now, ReTransmitStudyDataSchedule.class.getName());
        record.setScheduleTime(new Date());

        // 开始记录调度使用时长
        long beginMil = System.currentTimeMillis();

        try {

            scheduleRecordService.save(record);


            // 获取昨天调度记录
            // 第一次发送的记录
            Integer sendResultStatus = transmitStudyDataHistoryService.findSendResultStatusByDataDateAndCurrentSendTimes(beforeDay, 1);

            // 如果发送成功，则不再执行，如果发送失败，则再次发送

            // 发送成功
            if (null != sendResultStatus && TransmitStudyDataHistory.SEND_RESULT_STATUS_SUCCESS == sendResultStatus) {
                logger.info("第1步：校验历史发送记录状态：{}，发送成功！", sendResultStatus);
                return ;
            }


            // 发送失败，重新发送
            logger.info("第1步：校验历史发送记录状态：{}，发送失败！", sendResultStatus);

            // 获取历史发送的数据，如果没有，则从数据库获取

            // 从mongo获取
            long start = System.currentTimeMillis();

            List<TransmitStudyData> transmitStudyDataList = null;

            List<? extends TransmitStudyDataMean> meanDataList = getSavedDataByDataDate(beforeDay);

            logger.info("第2.1步：从存储数据处（mongo）获取历史发送的数据，数据量：{}，用时：{}ms", meanDataList.size(), System.currentTimeMillis() - start);

            // 标识是否需要保存数据
            boolean needSaveTransmitStudyData = false;

            // 组织数据，前半部分

            if (!meanDataList.isEmpty()) {

                start = System.currentTimeMillis();

                // 转为用于发送的数据
                transmitStudyDataList = buildTransmitStudyDataByMeanData(
                        trackClassCourseLiveTransmitMeanHandler, meanDataList);
            } else {

                needSaveTransmitStudyData = true;

                start = System.currentTimeMillis();

                // 从数据库获取数据
                List<? extends StudyData> studyDataList = loadStudyData(beginTime, endTime);

                logger.info("第2.2步：从数据库（mysql）获取学习记录数据，数据量：{}，用时：{}ms", studyDataList.size(), System.currentTimeMillis() - start);

                if (studyDataList.isEmpty())
                    throw new NoneDataException(String.format(
                            "没有获取到学习记录数据！可能有问题。参数：数据日期：%s，时间区间：%s 至 %s"
                            , beforeDay, beginTime, endTime));

                start = System.currentTimeMillis();

                // 转为用于发送的数据
                transmitStudyDataList = buildTransmitStudyData(beforeDay, studyDataList);
            }

            // 组织数据，后半部分

            // 设置发送时间
            transmitStudyDataList = trackClassCourseLiveTransmitHandler.setSendTime(now, transmitStudyDataList);
            // 设置签名
            transmitStudyDataList = trackClassCourseLiveTransmitHandler.buildSignature(transmitStudyDataList);

            logger.info("第3步：组织数据，用时：{}ms", System.currentTimeMillis() -start);


            // 保存数据
            if (needSaveTransmitStudyData) {
                start = System.currentTimeMillis();

                saveTransmitStudyData(transmitStudyDataList);

                logger.info("第4步：保存数据用时：{}ms", System.currentTimeMillis() - start);
            } else {
                logger.info("第4步：无需保存数据");
            }

            // 发送数据

            // 新建发送历史，第二次
            TransmitStudyDataHistory history = saveNewHistory(transmitStudyDataList);

            // 发送数据
            start = System.currentTimeMillis();

            SendTransmitResult sendTransmitResult = sendTransmitStudyData(transmitStudyDataList);

            long sendUseMills = System.currentTimeMillis() - start;

            logger.info("第5步：发送数据用时：{}ms", sendUseMills);

            // 更新发送历史
            updateHistory(history, sendTransmitResult, (int)sendUseMills);

        } catch (Exception e) {

            logger.error("【重新传输学习数据(每日)调度】发生异常", e);

            record.setStatus(ScheduleRecord.STATUS_FAILED);
            record.setRemark(e.getMessage());

            sendExceptionEmail(scheduleDay, e.getMessage());
        }

        // 本次调度总耗时
        long totalUseMills = System.currentTimeMillis() - beginMil;

        logger.info("本次调度耗时： " + totalUseMills);

        record.setExecuteTime(String.valueOf(totalUseMills));
        record.setStatus(ScheduleRecord.STATUS_SUCCESS);

        scheduleRecordService.update(record);
        logger.info("【重新传输学习数据(每日)调度】结束.....");
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
        tsdh.setCurrentSendTimes(2);                // 本次是第几次发送
        tsdh.setSendResultStatus(TransmitStudyDataHistory.SEND_RESULT_STATUS_UNSEND);
        tsdh.setSendTime(tsd.getSendTime());
        tsdh.setCreateTime(now);
        tsdh.setUpdateTime(now);

        return tsdh;
    }


    /**
     * 组建传输学习记录，根据从mongo获取的被保存的数据
     * @param transmitStudyDataMeanHandler
     * @param tsdmList 从mongo获取的被保存的数据
     * @return
     */
    private List<TransmitStudyData> buildTransmitStudyDataByMeanData(
            TransmitStudyDataMeanHandler transmitStudyDataMeanHandler, List<? extends TransmitStudyDataMean> tsdmList) {

        return transmitStudyDataMeanHandler.buildTransmitStudyData(tsdmList);
    }

    /**
     * 获取被保存（到mongo）的数据，根据数据日期
     * @param dataDate 数据日期
     * @return
     */
    private List<? extends TransmitStudyDataMean> getSavedDataByDataDate(String dataDate) {

        return trackClassCourseLiveTransmitRepository.findByDataDate(dataDate);
    }

    /**
     * 发送异常Email
     */
    private void sendExceptionEmail(String scheduleDay, String exceptionMessage) {

        EmailUtils emailUtils = new EmailUtils();

        String subject = "调度失败：失败重调学习记录数据错误";

        String content = String.format(
                "啊哦，看到这封邮件表明传输学习记录数据失败重调调度失败了！调度日期：%s\n当前时间：%s\n" +
                        "异常信息：\n" +
                        "%s"
                , scheduleDay, DateUtils.getNow(), exceptionMessage);

        emailUtils.sendEmail(subject, content);
    }


    @Override
    public void restore(Integer scheduleId) {

        logger.info("重新传输学习数据调度不可进行恢复操作！");
    }

}
