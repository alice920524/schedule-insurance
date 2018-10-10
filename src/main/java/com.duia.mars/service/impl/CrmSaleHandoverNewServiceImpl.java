package com.duia.mars.service.impl;

import com.duia.mars.core.AbstractService;
import com.duia.mars.dao.CrmSaleHandoverNewMapper;
import com.duia.mars.model.CrmSaleHandoverNew;
import com.duia.mars.service.CrmSaleHandoverNewService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/09/21.
 */
@Service
@Transactional
public class CrmSaleHandoverNewServiceImpl extends AbstractService<CrmSaleHandoverNew> implements CrmSaleHandoverNewService {
    @Resource
    private CrmSaleHandoverNewMapper crmSaleHandoverNewMapper;

}
