package com.duia.mars.service;

import com.duia.mars.core.Service;
import com.duia.mars.model.TrackFirstpay;


/**
 * Created by CodeGenerator on 2017/09/21.
 */
public interface TrackFirstpayService extends Service<TrackFirstpay> {
    public void saveDateToTrackFirstPay(String beginTime, String endTime);
}
