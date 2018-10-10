package com.duia.mars.schedule;

import com.duia.mars.common.constant.Common;
import com.duia.mars.common.util.DateUtils;
import com.duia.mars.common.util.EmailUtils;
import com.duia.mars.model.ScheduleRecord;
import com.duia.mars.service.FinanceOrderService;
import com.duia.mars.service.ScheduleRecordService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 每月确认上一月
 */
@Component
@Configurable
public class FinanceEnsureMonSchedule implements ISchedule {
    private static Logger logger = Logger.getLogger(FinanceEnsureMonSchedule.class);

    @Resource
    private ScheduleRecordService scheduleRecordService;

    @Resource
    private FinanceOrderService financeOrderService;

    @Override
    //@Scheduled(cron = "0 0 5 4  * ?")
    @Scheduled(cron = "0 0 5 4 1 ?")   // todo not execute
    public void schedule() {
        logger.error("【财务确认收入(每月)调度调度】开始.....");
        ScheduleRecord record = new ScheduleRecord(Common.ScheduleType.TYPE_FINANCE_ENSURE_MON_INDEX,new Date(), FinanceEnsureMonSchedule.class.getName());

        try {
            String scheduleMon = DateUtils.getLastMonth(record.getScheduleDate(), DateUtils.MONTG_DATE_FORMAT);
            long beginMil = System.currentTimeMillis();
            scheduleRecordService.save(record);
            financeOrderService.ensureLastMon(scheduleMon);
            logger.info("每月确认的耗时:"+ (System.currentTimeMillis() - beginMil));
            record.setExecuteTime(""+(System.currentTimeMillis() - beginMil));
            record.setStatus(1);
        } catch (Exception e) {
            logger.error("【财务确认收入(每月)调度调度】发生异常");
            logger.error(e.getMessage(), e);
            record.setStatus(-1);
            record.setRemark(e.getMessage());
            EmailUtils emailUtils = new EmailUtils();
            emailUtils.sendExptionEmail();
        }
        scheduleRecordService.update(record);
        logger.error("【财务确认收入(每月)调度调度】结束.....");
    }

    @Override
    public void restore(Integer scheduleId) {
        ScheduleRecord record = scheduleRecordService.findById(scheduleId);
        if (record == null || record.getStatus().intValue() == 1) {
            return;
        }
        logger.error("【财务确认收入(每月)调度调度" + record.getScheduleDate() + "数据恢复】开始......");
        try {
            String scheduleMon = DateUtils.getLastMonth(record.getScheduleDate(), DateUtils.MONTG_DATE_FORMAT);
            long beginMil = System.currentTimeMillis();
            financeOrderService.ensureLastMon(scheduleMon);
            logger.info("每月确认的耗时:"+ (System.currentTimeMillis() - beginMil));
            record.setExecuteTime(""+(System.currentTimeMillis() - beginMil));
            record.setStatus(1);
        } catch (Exception e) {
            logger.error("【财务确认收入(每月)调度调度" + record.getScheduleDate() + "数据恢复】发生异常");
            logger.error(e.getMessage(), e);
            record.setStatus(-1);
            record.setRemark(e.getMessage());
            EmailUtils emailUtils = new EmailUtils();
            emailUtils.sendExptionEmail();
        }
        scheduleRecordService.update(record);
        logger.error("【财务确认收入(每月)调度调度" + record.getScheduleDate() + "数据恢复】结束......");
    }


}
