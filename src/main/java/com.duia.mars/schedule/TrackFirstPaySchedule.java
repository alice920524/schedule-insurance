package com.duia.mars.schedule;

import com.duia.mars.common.constant.Common;
import com.duia.mars.common.util.DateUtils;
import com.duia.mars.model.ScheduleRecord;
import com.duia.mars.service.ScheduleRecordService;
import com.duia.mars.service.TrackFirstpayService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;

/**
 * Created by qiaolu on 2016/9/20.
 */

/*首次支付时长定时任务*/
@Component
@Configurable
public class TrackFirstPaySchedule implements ISchedule{
    private static Logger logger = Logger.getLogger(TrackFirstPaySchedule.class);

    @Autowired
    private TrackFirstpayService trackFirstpayService;
    @Autowired
    private ScheduleRecordService scheduleRecordService;


    @Override
    //@Scheduled(cron = "0 0 2 1 * ?")
    @Scheduled(cron = "0 0 2 1 * ?")    // todo not execute
    public void schedule() {
        String date = DateUtils.getCurrDate(DateUtils.LONG_DATE_FORMAT);
        logger.debug(date + "【首次付费时长调度】开始......");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH,-1);
        ScheduleRecord record = new ScheduleRecord(Common.ScheduleType.TYPE_TRACK_FIRST_INDEX,c.getTime(),TrackFirstPaySchedule.class.getName());
        try{
            scheduleRecordService.save(record);
            trackFirstpayService.saveDateToTrackFirstPay(DateUtils.getFirstDayOfLMonth(DateUtils.FORMAT_FOUR), DateUtils.getLastDayOfLMonth(DateUtils.FORMAT_FOUR_END));
            record.setStatus(1);
        }catch (Exception e){
            logger.error(date + "【首次付费时长调度】发生异常");
            logger.error(e.getMessage(),e);
            record.setStatus(-1);
            record.setRemark(e.getMessage());
        }
        scheduleRecordService.update(record);
        logger.debug(date + "【首次付费时长调度】结束......");
    }





    @Override
    public void restore(Integer scheduleId) {
        ScheduleRecord record = scheduleRecordService.findById(scheduleId);
        if(record == null || record.getStatus().intValue() == 1){
            return;
        }
        logger.debug("【首次付费时长"+record.getScheduleDate()+"数据恢复】开始......");
        try{
            //获取需要调度的活动ID
            trackFirstpayService.saveDateToTrackFirstPay(DateUtils.getFirstDayOfLMonth(DateUtils.FORMAT_FOUR), DateUtils.getLastDayOfLMonth(DateUtils.FORMAT_FOUR_END));
            record.setStatus(1);
        }catch (Exception e){
            logger.debug("【首次付费时长"+record.getScheduleDate()+"数据恢复】发生异常");
            logger.error(e.getMessage(),e);
            record.setStatus(-1);
            record.setRemark(e.getMessage());
        }
        scheduleRecordService.update(record);
        logger.debug("【首次付费时长"+record.getScheduleDate()+"数据恢复】结束......");
    }
}
