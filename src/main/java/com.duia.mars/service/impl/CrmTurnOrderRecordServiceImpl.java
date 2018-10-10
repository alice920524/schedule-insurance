package com.duia.mars.service.impl;

import com.duia.mars.core.AbstractService;
import com.duia.mars.dao.CrmTurnOrderRecordMapper;
import com.duia.mars.model.CrmTurnOrderRecord;
import com.duia.mars.service.CrmTurnOrderRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2017/09/21.
 */
@Service
@Transactional
public class CrmTurnOrderRecordServiceImpl extends AbstractService<CrmTurnOrderRecord> implements CrmTurnOrderRecordService {
    @Resource
    private CrmTurnOrderRecordMapper crmTurnOrderRecordMapper;

    @Override
    public List<CrmTurnOrderRecord> getBalanceList() {
        return crmTurnOrderRecordMapper.getBalanceList();
    }

    @Override
    public List<CrmTurnOrderRecord> getTurnList() {
        return crmTurnOrderRecordMapper.getTurnList();
    }
}
