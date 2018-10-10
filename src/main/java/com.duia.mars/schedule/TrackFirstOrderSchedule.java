package com.duia.mars.schedule;

import com.duia.mars.common.constant.Common;
import com.duia.mars.common.util.DateUtils;
import com.duia.mars.model.ScheduleRecord;
import com.duia.mars.service.ScheduleRecordService;
import com.duia.mars.service.TrackFirstOrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by qiaolu on 2017/3/11.
 */

/*首单统计
* 每天一次
* 都以财务审核通过时间为准
* 只算父订单，分期分批按照最后一单财务日期
* 退款的订单还算
* 关闭的订单不算
* */
@Component
@Configurable
public class TrackFirstOrderSchedule implements ISchedule {
    private static Logger logger = Logger.getLogger(TrackFirstOrderSchedule.class);

    @Autowired
    private TrackFirstOrderService trackFirstOrderService;
    @Autowired
    private ScheduleRecordService scheduleRecordService;


    @Override
    //@Scheduled(cron = "0 0 2 * * ?")
    @Scheduled(cron = "0 0 2 1 1 ?")    // todo not execute
    public void schedule() {
        String date = DateUtils.getCurrDate(DateUtils.LONG_DATE_FORMAT);
        logger.debug(date + "【用户首单统计调度】开始......");
        Calendar c = Calendar.getInstance();
        ScheduleRecord record = new ScheduleRecord(Common.ScheduleType.TYP_USER_FIRST_ORDER_INDEX, c.getTime(), TrackFirstOrderSchedule.class.getName());
        try {
            scheduleRecordService.save(record);
            trackFirstOrderService.handleFirstOrder(DateUtils.befoDay(DateUtils.LONG_DATE_FORMAT));
            record.setStatus(1);
        } catch (Exception e) {
            logger.error(date + "【用户首单统计调度】发生异常");
            logger.error(e.getMessage(), e);
            record.setStatus(-1);
            record.setRemark(e.getMessage());
        }
        try{
            scheduleRecordService.update(record);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        logger.debug(date + "【用户首单统计调度】结束......");
    }


    @Override
    public void restore(Integer scheduleId) {
        ScheduleRecord record = scheduleRecordService.findById(scheduleId);
        if (record == null || record.getStatus().intValue() == 1) {
            return;
        }
        logger.debug("【用户首单统计" + record.getScheduleDate() + "数据恢复】开始......");
        try {
            //获取需要调度的活动ID
            record.getScheduleDate();
            Date date = DateUtils.nextDay(record.getScheduleDate(), -1);
            trackFirstOrderService.handleFirstOrder(DateUtils.dateToString(date,DateUtils.LONG_DATE_FORMAT));
            record.setStatus(1);
        } catch (Exception e) {
            logger.debug("【用户首单统计" + record.getScheduleDate() + "数据恢复】发生异常");
            logger.error(e.getMessage(), e);
            record.setStatus(-1);
            record.setRemark(e.getMessage());
        }
        try{
            scheduleRecordService.update(record);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        logger.debug("【用户首单统计" + record.getScheduleDate() + "数据恢复】结束......");
    }
}
