package com.duia.mars.schedule;

import com.duia.mars.common.constant.Common;
import com.duia.mars.common.dto.PointCleanTime;
import com.duia.mars.common.util.DateUtils;
import com.duia.mars.model.ScheduleRecord;
import com.duia.mars.service.AuthorityUsersService;
import com.duia.mars.service.ScheduleRecordService;
import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by fukai on 2017/7/21.
 */

/*
* 重置用户总积分
* 每天一次
* */
@Component
@Configurable
public class ResetUserPointSchedule implements ISchedule {
    private static Logger logger = Logger.getLogger(ResetUserPointSchedule.class);

    private static SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();

    private static final String POINT_CLEAN_TIME_KEY = "sale:point.cleanTime.";//积分清零时间

    @Resource(name = "saleRedisTemplate")
    private RedisTemplate redisTemplate;

    @Resource
    private AuthorityUsersService authorityUsersService;
    @Resource
    private ScheduleRecordService scheduleRecordService;

    /**
     * 从Redis中获取“积分清零时间”
     * 1.重置redis中的调度时间
     * 2.根据redis中的调度时间，重置调度任务时间
     */
    //@Scheduled(cron = "0 55 23 * * ?")
    @Scheduled(cron = "0 55 23 1 1 ?")  // todo not execute
//    @Scheduled(cron = "0/30 * * * * ? ")
    private void reSetCleanTime() {
        Object newCleanTime = redisTemplate.opsForValue().get(POINT_CLEAN_TIME_KEY + "new");
        if (newCleanTime != null) {
            //能获取到“new”，则表示有重置操作，新置时间更新，删除“new”！
            redisTemplate.opsForValue().set(POINT_CLEAN_TIME_KEY + "old", newCleanTime);
            redisTemplate.expire(POINT_CLEAN_TIME_KEY + "new", 0, TimeUnit.SECONDS);
        }
    }

    /**
     * @Description: 修改任务的触发时间
     * 注：暂时不用！
     *
     * @param triggerName 触发器名
     * @param triggerGroupName 触发器组
     * @param newTime cron表达式
     *
     * @author Comsys-LZP
     * @date 2014-6-26 下午03:49:37
     * @version V2.0
     */
    @Deprecated
    public void modifyJobTime(String triggerName, String triggerGroupName, String newTime) {
        try {
            TriggerKey triggerKey = new TriggerKey(triggerName, triggerGroupName);
            Scheduler scheduler = gSchedulerFactory.getScheduler();
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (trigger == null) {
                return;
            }
            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(newTime)) {
                CronTriggerFactoryBean ct = (CronTriggerFactoryBean) trigger;
                // 修改时间
                ct.setCronExpression(newTime);
                // 重启触发器
                scheduler.resumeTrigger(triggerKey);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 执行调度任务
     */
    @Override
//    @Scheduled(cron = "0 0 0/1 * * ?")
//    @Scheduled(cron = "00 10 20 * * ?")
   @Scheduled(cron = "0/30 * * * 1 ? ")
    public void schedule() {
        String date = DateUtils.getCurrDate(DateUtils.LONG_DATE_FORMAT);
        Integer curHour = Integer.parseInt(DateUtils.getCurrDate("HH"));
        Object cleanTime = redisTemplate.opsForValue().get(POINT_CLEAN_TIME_KEY + "old");
        if (cleanTime != null) {
            String cleanTimeStr = (String)cleanTime;
            String[] arrStr = cleanTimeStr.split(",");
            if (Integer.parseInt(arrStr[0]) == curHour) {
                /*****************匹配通过，调度任务开始执行******************/
                logger.debug(date + "【重置用户积分调度】开始......");
                Calendar c = Calendar.getInstance();
                ScheduleRecord record = new ScheduleRecord(Common.ScheduleType.TYPE_RESET_USER_POINT, c.getTime(), ResetUserPointSchedule.class.getName());
                try {
                    scheduleRecordService.save(record);
                    authorityUsersService.updateUserPoint();
                    record.setStatus(1);
                } catch (Exception e) {
                    logger.error(date + "【重置用户积分调度】发生异常");
                    logger.error(e.getMessage(), e);
                    record.setStatus(-1);
                    record.setRemark(e.getMessage());
                }
                try{
                    scheduleRecordService.update(record);
                }catch (Exception e){
                    logger.error(e.getMessage(),e);
                }
                logger.debug(date + "【重置用户积分调度】结束......");
                /*****************匹配通过，调度任务开始结束******************/
            }
        } else {
            logger.error(date + "【重置用户积分调度】查询调度时间发生异常......");
        }
    }


    /**
     * 数据恢复
     * @param scheduleId
     */
    @Override
    public void restore(Integer scheduleId) {
        ScheduleRecord record = scheduleRecordService.findById(scheduleId);
        if (record == null || record.getStatus().intValue() == 1) {
            return;
        }
        logger.debug("【重置用户积分" + record.getScheduleDate() + "数据恢复】开始......");
        try {
            //获取需要调度的活动ID
            record.getScheduleDate();
            Date date = DateUtils.nextDay(record.getScheduleDate(), -1);
            authorityUsersService.updateUserPoint();
            record.setStatus(1);
        } catch (Exception e) {
            logger.debug("【重置用户积分" + record.getScheduleDate() + "数据恢复】发生异常");
            logger.error(e.getMessage(), e);
            record.setStatus(-1);
            record.setRemark(e.getMessage());
        }
        try{
            scheduleRecordService.update(record);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        logger.debug("【重置用户积分" + record.getScheduleDate() + "数据恢复】结束......");
    }
}
