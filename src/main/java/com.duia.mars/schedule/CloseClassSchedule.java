package com.duia.mars.schedule;

import com.duia.mars.common.constant.Common;
import com.duia.mars.common.http.DuiaClient;
import com.duia.mars.common.http.impl.DuiaClientFactory;
import com.duia.mars.model.ScheduleRecord;
import com.duia.mars.service.ScheduleRecordService;
import com.duia.util.core.DateUtils;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 超48小时不修改关闭班级的调度
 * 2016/12/28.
 */
@Component
@Configurable
public class CloseClassSchedule implements ISchedule {
    private static Logger logger = Logger.getLogger(CloseClassSchedule.class);


    private static boolean isRunning = false;

    @Resource
    private ScheduleRecordService scheduleRecordService;

    @Value("${duia.new.back.url}")
    private String url;


    @Override
    @Scheduled(cron = "0 0 3 * * ?")
    public void schedule(){
        if(isRunning){
            logger.debug("【关闭班级更新调度】正在更新当中......");
            return;
        }
        isRunning = true;
        String date = DateUtils.getCurrDate(DateUtils.LONG_DATE_FORMAT);
        logger.debug("【关闭班级更新调度】开始.....");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH,-1);
        ScheduleRecord record = new ScheduleRecord(Common.ScheduleType.TYPE_COLSE_CLASS_INDEX,c.getTime(),CloseClassSchedule.class.getName());
        try{
            scheduleRecordService.save(record);
            Map dataMap = new HashMap<String, String>();
            DuiaClient client = DuiaClientFactory.getInstance().getClient("http");
            String result = client.post(url + "crmSaleHandoverNew/financeCloseSchedule");
            Gson gson = new Gson();
            Map map = gson.fromJson(result,HashMap.class);
            if (map.get("code") == null || map.get("code") == "") {
                logger.error("【关闭班级更新调度】发生异常:远程调用问题"+map.get("msg"));
                record.setStatus(-1);
                record.setRemark((String)map.get("msg"));
            }
            if (((String)map.get("code")).equals("200")) {
                record.setStatus(1);
            }else{
                logger.error("【关闭班级更新调度】发生异常:远程调用问题"+map.get("msg"));
                record.setStatus(-1);
                record.setRemark((String)map.get("msg"));
            }
        }catch (Exception e){
            logger.error("【关闭班级更新调度】发生异常");
            logger.error(e.getMessage(),e);
            record.setStatus(-1);
            record.setRemark(e.getMessage());
        }
        isRunning = false;
        scheduleRecordService.update(record);
        logger.debug("【关闭班级更新调度】结束.....");
    }

    @SuppressWarnings("unchecked")
    @Override
    public void restore(Integer scheduleId) {
        ScheduleRecord record = scheduleRecordService.findById(scheduleId);
        if(record == null || record.getStatus().intValue() == 1){
            return;
        }
        logger.debug("【关闭班级更新调度"+record.getScheduleDate()+"数据恢复】开始......");
        try{
            Map dataMap = new HashMap<String, String>();
            DuiaClient client = DuiaClientFactory.getInstance().getClient("http");
            String result = client.post(url + "crmSaleHandoverNew/financeCloseSchedule");
            Gson gson = new Gson();
            Map map = gson.fromJson(result,HashMap.class);
            if (map.get("code") == null || map.get("code") == "") {
                logger.error("【关闭班级更新调度】发生异常:远程调用问题"+map.get("msg"));
                record.setStatus(-1);
                record.setRemark((String)map.get("msg"));
            }
            if (((String)map.get("code")).equals("200")) {
                record.setStatus(1);
            }else{
                logger.error("【关闭班级更新调度】发生异常:远程调用问题"+map.get("msg"));
                record.setStatus(-1);
                record.setRemark((String)map.get("msg"));
            }
        }catch (Exception e){
            logger.debug("【关闭班级更新调度"+record.getScheduleDate()+"数据恢复】发生异常");
            logger.error(e.getMessage(),e);
            record.setStatus(-1);
            record.setRemark(e.getMessage());
        }
        scheduleRecordService.update(record);
        logger.debug("【关闭班级更新调度"+record.getScheduleDate()+"数据恢复】结束......");

    }
  /*  public static  void main(String []args){
        DuiaClient client = DuiaClientFactory.getInstance().getClient("http");
        System.out.println(url);
       String a= client.post(ParamsUtils.getInstance().getConfig("duia.new.back.url")  + "/crmSaleHandoverNew/financeCloseSchedule");
        System.out.println(a);
    }*/


}
