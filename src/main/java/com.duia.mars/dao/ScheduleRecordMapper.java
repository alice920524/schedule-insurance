package com.duia.mars.dao;

import com.duia.mars.core.Mapper;
import com.duia.mars.model.ScheduleRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScheduleRecordMapper extends Mapper<ScheduleRecord> {

    /**
     * 分页
     * @param type
     * @param status
     * @return
     */
    List<ScheduleRecord> page(@Param("type") Integer type, @Param("status") Integer status);

    /**
     * 更新状态为失败，根据调度记录id
     * @param id 调度记录id
     * @return 影响行数
     */
    int updateStatusAsFailById(@Param("id") Integer id);
}