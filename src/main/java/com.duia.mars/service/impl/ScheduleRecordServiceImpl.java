package com.duia.mars.service.impl;

import com.duia.mars.core.AbstractService;
import com.duia.mars.dao.ScheduleRecordMapper;
import com.duia.mars.model.ScheduleRecord;
import com.duia.mars.service.ScheduleRecordService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2017/09/21.
 */
@Service
@Transactional
public class ScheduleRecordServiceImpl extends AbstractService<ScheduleRecord> implements ScheduleRecordService {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleRecordServiceImpl.class);

    @Resource
    private ScheduleRecordMapper scheduleRecordMapper;

    /**
     * 分页
     * @param page
     * @param size
     * @param type
     * @param status
     * @return
     */
    @Override
    public PageInfo page(Integer page, Integer size, Integer type, Integer status) {
        List<ScheduleRecord> recordList = scheduleRecordMapper.page(type, status);
        return new PageInfo<>(recordList);
    }

    @Override
    public boolean updateStatusAsFailById(Integer id) {

        int count = scheduleRecordMapper.updateStatusAsFailById(id);

        if (count == 1)
            return true;
        if (count > 1) {
            logger.error("更新影响行数>1，为{}，参数：id={}", count, id);
            throw new RuntimeException("影响行数大于1");
        }

        return false;
    }

}
