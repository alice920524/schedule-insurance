package com.duia.mars.web;

import com.duia.mars.common.constant.Common;
import com.duia.mars.core.Result;
import com.duia.mars.core.ServiceException;
import com.duia.mars.model.ScheduleRecord;
import com.duia.mars.schedule.ISchedule;
import com.duia.mars.service.ScheduleRecordService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * Created by chenqi on 2016/10/24.
 */
@RestController
@RequestMapping("/restore")
public class RestoreDataController {

    private static final Logger logger = LoggerFactory.getLogger(RestoreDataController.class);

    @Autowired
    private ScheduleRecordService scheduleRecordService;

    @Resource(name = "enRedisTemplate")
    private RedisTemplate redisTemplate;

    @Value("${duia.new.back.url}")
    private String duiaNewBackUrl;

    @Autowired
    private ApplicationContext context;

    @GetMapping(value = "/r-p")
    public ModelAndView restorePage(HttpServletRequest request, Model model,
                                    Long timestamp, String signature) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            String key = request.getSession().getId();
            redisTemplate.opsForHash().put(Common.CacheName.SCHEDULE_VISIT_LOG,key,signature);
            redisTemplate.expire(Common.CacheName.SCHEDULE_VISIT_LOG, 30, TimeUnit.MINUTES);
        } catch (Exception ex) {

        }
        model.addAttribute("testAttr", "HELLO WORLD!");
        modelAndView.setViewName("restore_schedule");
        return modelAndView;
    }

    @GetMapping("/ssf-p")
    public ModelAndView setScheduleAsFailPage() {

        ModelAndView mv = new ModelAndView();

        mv.setViewName("set_schedule_fail");

        return mv;
    }

    @PostMapping("/setScheduleAsFail")
    public Result setScheduleAsFail(@RequestParam("scheduleRecordId") Integer scheduleRecordId) {

        boolean success = scheduleRecordService.updateStatusAsFailById(scheduleRecordId);

        Result result = new Result();
        if (success) {
            result.setCode(HttpServletResponse.SC_OK);
            result.setMessage("成功");
            return result;
        }

        result.setCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        result.setMessage("失败");

        return result;
    }

    /**
     * 分页
     * @param page 页码
     * @param size 单页显示条数
     * @param type
     * @param status
     * @return
     */
    @PostMapping(value = "/page")
    public Result page(HttpServletRequest request, Model model,
                             @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "1") Integer size,
                             @RequestParam(required = false) Integer type, @RequestParam(required = false) Integer status) {
        Result data = new Result();
        try {
            String key = request.getSession().getId();
            check(key);
            //开启分页
            PageHelper.startPage(page, size);
            PageInfo<ScheduleRecord> recordPageInfo = scheduleRecordService.page(page, size, type, status);
            model.addAttribute("pm", recordPageInfo);
            model.addAttribute("testAttr", "HELLO WORLD!");
            if (recordPageInfo != null && recordPageInfo.getList() != null && recordPageInfo.getList().size() > 0) {
                data.setCode(1);
                data.setData(recordPageInfo);
                data.setMessage("数据获取成功！");
            } else {
                data.setData(recordPageInfo);
                data.setMessage("未获取到相关数据！");
            }
        } catch (Exception ex) {
            logger.error("数据获取异常！");
            data.setCode(-1);
            data.setMessage("数据获取异常！");
            ex.printStackTrace();
        }
        return data;
    }

    /**
     * 重新调度-数据恢复
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/restore")
    public Result restore(HttpServletRequest request, @RequestParam Integer id){
        String key = request.getSession().getId();
        check(key);
        Result data = new Result();
        try{
            ScheduleRecord record = scheduleRecordService.findById(id);
            /*WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();*/
            Class cls = Class.forName(record.getClassType());
            ISchedule schedule = (ISchedule) context.getBean(cls);
            schedule.restore(id);
            ScheduleRecord result = scheduleRecordService.findById(id);
            if(result.getStatus().equals(1)){
                data.setCode(1);
                data.setMessage("数据恢复成功");
            }else{
                data.setCode(-1);
                data.setMessage("数据恢复失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            data.setCode(0);
            data.setMessage("数据恢复失败");
        }
        return data;
    }

    private void check(String key){
        boolean result = redisTemplate.opsForHash().hasKey(Common.CacheName.SCHEDULE_VISIT_LOG,key);
        if(!result){
            throw new ServiceException("请求被拒绝");
        }
    }
}
