package com.duia.mars.service;

import com.duia.mars.core.Service;
import com.duia.mars.model.CrmTurnOrderRecord;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/09/21.
 */
public interface CrmTurnOrderRecordService extends Service<CrmTurnOrderRecord> {
    List<CrmTurnOrderRecord> getBalanceList();

    List<CrmTurnOrderRecord> getTurnList();
}
