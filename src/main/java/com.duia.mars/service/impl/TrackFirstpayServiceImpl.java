package com.duia.mars.service.impl;

import com.duia.mars.core.AbstractService;
import com.duia.mars.dao.TrackFirstpayMapper;
import com.duia.mars.model.TrackFirstpay;
import com.duia.mars.service.TrackFirstpayService;
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
public class TrackFirstpayServiceImpl extends AbstractService<TrackFirstpay> implements TrackFirstpayService {
    @Resource
    private TrackFirstpayMapper trackFirstpayMapper;


    @Override
    public void saveDateToTrackFirstPay(String beginTime, String endTime) {
        Map<String, Object> params = new HashMap<>();
        params.put("beginTime",beginTime);
        params.put("endTime", endTime);
        List<TrackFirstpay> listData = trackFirstpayMapper.selectListDatas(params);
        if (listData.size() > 0) {
            trackFirstpayMapper.saveData(listData);
        }
    }

}
