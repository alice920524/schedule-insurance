package com.duia.mars.service.impl;


import com.duia.mars.core.AbstractService;
import com.duia.mars.dao.TransmitStudyDataHistoryMapper;
import com.duia.mars.model.TransmitStudyDataHistory;
import com.duia.mars.service.TransmitStudyDataHistoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class TransmitStudyDataHistoryServiceImpl extends AbstractService<TransmitStudyDataHistory> implements TransmitStudyDataHistoryService {

    @Resource
    private TransmitStudyDataHistoryMapper transmitStudyDataHistoryMapper;

    @Override
    public Integer findSendResultStatusByDataDateAndCurrentSendTimes(String dataDate, int currentSendTimes) {

        if (StringUtils.isBlank(dataDate) || currentSendTimes <= 0)
            throw new IllegalArgumentException("参数：beforeDay不可为空，currentSendTimes必须>0");

        return transmitStudyDataHistoryMapper.selectSendResultStatusByDataDateAndCurrentSendTimes(dataDate, currentSendTimes);
    }
}
