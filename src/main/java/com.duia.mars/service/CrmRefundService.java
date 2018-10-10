package com.duia.mars.service;

import com.duia.mars.core.Service;
import com.duia.mars.model.CrmRefund;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/09/21.
 */
public interface CrmRefundService extends Service<CrmRefund> {
    List<CrmRefund> getRefundList();
}
