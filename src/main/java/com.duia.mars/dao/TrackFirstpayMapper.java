package com.duia.mars.dao;

import com.duia.mars.core.Mapper;
import com.duia.mars.model.TrackFirstpay;

import java.util.List;
import java.util.Map;

public interface TrackFirstpayMapper extends Mapper<TrackFirstpay> {

    public List<TrackFirstpay> selectListDatas(Map<String, Object> parmas);

    public void saveData(List<TrackFirstpay> list);
}