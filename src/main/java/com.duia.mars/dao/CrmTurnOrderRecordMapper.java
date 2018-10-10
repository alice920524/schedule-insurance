package com.duia.mars.dao;

import com.duia.mars.core.Mapper;
import com.duia.mars.model.CrmTurnOrderRecord;

import java.util.List;

public interface CrmTurnOrderRecordMapper extends Mapper<CrmTurnOrderRecord> {
    List<CrmTurnOrderRecord> getBalanceList();

    List<CrmTurnOrderRecord> getTurnList();
}