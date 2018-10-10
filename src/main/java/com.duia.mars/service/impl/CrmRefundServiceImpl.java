package com.duia.mars.service.impl;

import com.duia.mars.core.AbstractService;
import com.duia.mars.dao.CrmRefundMapper;
import com.duia.mars.model.CrmRefund;
import com.duia.mars.service.CrmRefundService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2017/09/21.
 */
@Service
@Transactional
public class CrmRefundServiceImpl extends AbstractService<CrmRefund> implements CrmRefundService {
    @Resource
    private CrmRefundMapper crmRefundMapper;

    @Override
    public List<CrmRefund> getRefundList() {
        return crmRefundMapper.getRefundList();
    }
}
