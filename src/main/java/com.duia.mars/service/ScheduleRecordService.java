package com.duia.mars.service;

import com.duia.mars.core.Service;
import com.duia.mars.model.ScheduleRecord;
import com.github.pagehelper.PageInfo;


/**
 * Created by CodeGenerator on 2017/09/21.
 */
public interface ScheduleRecordService extends Service<ScheduleRecord> {

    /**
     * 分页
     * @param page
     * @param size
     * @return
     */
    PageInfo page(Integer page, Integer size, Integer type, Integer status);

    /**
     * 更新状态为失败，根据调度记录id
     * @param id 调度记录id
     * @return 更新成功返回true，否则返回false
     */
    boolean updateStatusAsFailById(Integer id);
}
