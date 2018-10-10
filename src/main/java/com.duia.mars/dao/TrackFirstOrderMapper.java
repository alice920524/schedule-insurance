package com.duia.mars.dao;

import com.duia.mars.common.dto.CrmSaleHandoverNewDto;
import com.duia.mars.core.Mapper;
import com.duia.mars.model.TrackFirstOrder;

import java.util.List;

public interface TrackFirstOrderMapper extends Mapper<TrackFirstOrder> {
    public void saveData(List<CrmSaleHandoverNewDto> list);
}