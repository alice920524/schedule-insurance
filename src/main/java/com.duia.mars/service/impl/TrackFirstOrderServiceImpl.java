package com.duia.mars.service.impl;

import com.duia.mars.common.dto.CrmSaleHandoverNewDto;
import com.duia.mars.core.AbstractService;
import com.duia.mars.dao.CrmSaleHandoverNewMapper;
import com.duia.mars.dao.TrackFirstOrderMapper;
import com.duia.mars.model.TrackFirstOrder;
import com.duia.mars.service.TrackFirstOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2017/09/21.
 */
@Service
@Transactional
public class TrackFirstOrderServiceImpl extends AbstractService<TrackFirstOrder> implements TrackFirstOrderService {
    @Resource
    private TrackFirstOrderMapper trackFirstOrderMapper;

    @Resource
    private CrmSaleHandoverNewMapper crmSaleHandoverNewMapper;




    @Override
    public void handleFirstOrder(String date) throws Exception{
        Map map = new HashMap();
        map.put("beginTime", date + " 00:00:00");
        map.put("endTime", date+ " 23:59:59");
        List<CrmSaleHandoverNewDto> crmSaleHandoverNewDtoList = crmSaleHandoverNewMapper.selectFinanceFirstOrder(map);
        if (crmSaleHandoverNewDtoList.size() > 0) {
            trackFirstOrderMapper.saveData(crmSaleHandoverNewDtoList);
        }

    }

}
