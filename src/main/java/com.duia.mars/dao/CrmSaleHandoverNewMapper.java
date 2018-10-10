package com.duia.mars.dao;

import com.duia.mars.common.dto.CrmSaleHandoverNewDto;
import com.duia.mars.core.Mapper;
import com.duia.mars.model.CrmSaleHandoverNew;

import java.util.List;
import java.util.Map;

public interface CrmSaleHandoverNewMapper extends Mapper<CrmSaleHandoverNew> {
    List<CrmSaleHandoverNewDto> selectFinanceFirstOrder(Map map);
}