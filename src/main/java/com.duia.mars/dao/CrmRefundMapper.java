package com.duia.mars.dao;

import com.duia.mars.core.Mapper;
import com.duia.mars.model.CrmRefund;

import java.util.List;

public interface CrmRefundMapper extends Mapper<CrmRefund> {
    List<CrmRefund> getRefundList();
}