package com.duia.mars.schedule;

import com.duia.mars.common.constant.Common;
import com.duia.mars.common.util.DateUtils;
import com.duia.mars.common.util.EmailUtils;
import com.duia.mars.model.ScheduleRecord;
import com.duia.mars.service.FinanceMonthEnsureService;
import com.duia.mars.service.FinanceOrderService;
import com.duia.mars.service.ScheduleRecordService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;


/**
 * 财务确认收入，每天调用前一天
 * 1.查询昨天订单财务确认的（包含关闭）
 */
@Component
@Configurable
public class FinanceEnsureDaySchedule implements ISchedule {
    private static Logger logger = Logger.getLogger(FinanceEnsureDaySchedule.class);

    @Resource
    private ScheduleRecordService scheduleRecordService;

    @Resource
    private FinanceOrderService financeOrderService;

    @Resource
    private FinanceMonthEnsureService financeMonthEnsureService;



    @Override
    //@Scheduled(cron = "0 0 4 * * ?")
    @Scheduled(cron = "0 0 4 1 1 ?")    // todo not execute
    public void schedule() {
        logger.error("【财务确认收入(每日)调度调度】开始.....");
        ScheduleRecord record = new ScheduleRecord(Common.ScheduleType.TYPE_FINANCE_ENSURE_DAY_INDEX, new Date(), FinanceEnsureDaySchedule.class.getName());
            try {
                String beforeDay =   DateUtils.befoDay(DateUtils.LONG_DATE_FORMAT);
                long beginMil = System.currentTimeMillis();
                String beginTime = beforeDay + " 00:00:00";
                String endTime = beforeDay + " 23:59:59";
                String scheduleTime = DateUtils.dateToString(record.getScheduleDate(), DateUtils.LONG_DATE_FORMAT);
                scheduleRecordService.save(record);
                //先删一波
                financeOrderService.closeByDate(scheduleTime);
                logger.info("第1步耗时    ： " + (System.currentTimeMillis() - beginMil));


                //计算可以归入的订单
                financeOrderService.handleOrderByTime(beginTime, endTime, scheduleTime);
                logger.info("第2步耗时    ： " + (System.currentTimeMillis() - beginMil));


                //保存fme表  订单和班级 还有初步的开课结课  总课时
                financeMonthEnsureService.financeSaveAllCourseInfo(scheduleTime);
                logger.info("第3步耗时    ： " + (System.currentTimeMillis() - beginMil));

                //更新fme表  订单和班级  每月上课的json
                financeMonthEnsureService.financeSaveEveryMonCount(scheduleTime);
                logger.info("第4步耗时    ： " + (System.currentTimeMillis() - beginMil));

                /**
                 * 以下俩步可以考虑并行
                 */
                //回头来更新订单的总课时 结课时间
                financeOrderService.financeSaveOrderClassCount(scheduleTime);
                logger.info("第5步耗时    ： " + (System.currentTimeMillis() - beginMil));

                //计算在本月前就已经完结的课时 算到订单上 包含没有课表
                financeOrderService.countBeforeMonth(scheduleTime);
                logger.info("第6步耗时    ： " + (System.currentTimeMillis() - beginMil));


                /**
                 * 顺序执行
                 */
                //退款 每天算一次所有退款但是没更新的
                financeOrderService.handleRefund();
                logger.info("第7步耗时    ： " + (System.currentTimeMillis() - beginMil));

                //退款 每天算一次所有退款但是没更新的
                financeOrderService.handleBalance();
                logger.info("第8步耗时    ： " + (System.currentTimeMillis() - beginMil));

                //退款 每天算一次所有退款但是没更新的
                financeOrderService.handleTurn();
                logger.info("第9步耗时    ： " + (System.currentTimeMillis() - beginMil));

                //退款 每天算一次所有退款但是没更新的
                financeOrderService.handleClose();
                logger.info("第10步耗时    ： " + (System.currentTimeMillis() - beginMil));
                record.setExecuteTime(""+(System.currentTimeMillis() - beginMil));
                record.setStatus(1);
            } catch (Exception e) {
                logger.error("【财务确认收入(每日)调度调度】发生异常");
                logger.error(e.getMessage(), e);
                record.setStatus(-1);
                record.setRemark(e.getMessage());
                EmailUtils emailUtils = new EmailUtils();
                emailUtils.sendExptionEmail();
            }
            scheduleRecordService.update(record);
            logger.error("【财务确认收入(每日)调度调度】结束.....");
    }

    @Override
    public void restore(Integer scheduleId) {
        ScheduleRecord record = scheduleRecordService.findById(scheduleId);
        if (record == null || record.getStatus().intValue() == 1) {
            return;
        }
        logger.error("【财务确认收入(每日)调度调度" + record.getScheduleDate() + "数据恢复】开始......");

        try {

            // 调度时间的前一天
            String beforeDay = DateUtils.dateToString(DateUtils.nextDay(record.getScheduleDate(), -1), DateUtils.LONG_DATE_FORMAT);
            long beginMil = System.currentTimeMillis();
            String beginTime = beforeDay + " 00:00:00";
            String endTime = beforeDay + " 23:59:59";
            // 调度时间
            String scheduleTime = DateUtils.dateToString(record.getScheduleDate(), DateUtils.LONG_DATE_FORMAT);

            //先删一波
            financeOrderService.closeByDate(scheduleTime);
            logger.info("第1步耗时    ： " + (System.currentTimeMillis() - beginMil));


            //计算可以归入的订单
            financeOrderService.handleOrderByTime(beginTime, endTime, scheduleTime);
            logger.info("第2步耗时    ： " + (System.currentTimeMillis() - beginMil));


            //保存fme表  订单和班级 还有初步的开课结课  总课时
            financeMonthEnsureService.financeSaveAllCourseInfo(scheduleTime);
            logger.info("第3步耗时    ： " + (System.currentTimeMillis() - beginMil));

            //更新fme表  订单和班级  每月上课的json
            financeMonthEnsureService.financeSaveEveryMonCount(scheduleTime);
            logger.info("第4步耗时    ： " + (System.currentTimeMillis() - beginMil));

            /**
             * 以下俩步可以考虑并行
             */
            //回头来更新订单的总课时 结课时间
            financeOrderService.financeSaveOrderClassCount(scheduleTime);
            logger.info("第5步耗时    ： " + (System.currentTimeMillis() - beginMil));

            //计算在本月前就已经完结的课时 算到订单上
            financeOrderService.countBeforeMonth(scheduleTime);
            logger.info("第6步耗时    ： " + (System.currentTimeMillis() - beginMil));


            /**
             * 顺序执行
             */
            //退款 每天算一次所有退款但是没更新的
            financeOrderService.handleRefund();
            logger.info("第7步耗时    ： " + (System.currentTimeMillis() - beginMil));

            //退款 每天算一次所有退款但是没更新的
            financeOrderService.handleBalance();
            logger.info("第8步耗时    ： " + (System.currentTimeMillis() - beginMil));

            //退款 每天算一次所有退款但是没更新的
            financeOrderService.handleTurn();
            logger.info("第9步耗时    ： " + (System.currentTimeMillis() - beginMil));

            //退款 每天算一次所有退款但是没更新的
            financeOrderService.handleClose();
            logger.info("第10步耗时    ： " + (System.currentTimeMillis() - beginMil));
            record.setExecuteTime(""+(System.currentTimeMillis() - beginMil));
            record.setStatus(1);
        } catch (Exception e) {
            logger.error("【财务确认收入(每日)调度调度" + record.getScheduleDate() + "数据恢复】发生异常");
            logger.error(e.getMessage(), e);
            record.setStatus(-1);
            record.setRemark(e.getMessage());
            EmailUtils emailUtils = new EmailUtils();
            emailUtils.sendExptionEmail();
        }
        scheduleRecordService.update(record);
        logger.error("【财务确认收入(每日)调度调度" + record.getScheduleDate() + "数据恢复】结束......");
    }

}
