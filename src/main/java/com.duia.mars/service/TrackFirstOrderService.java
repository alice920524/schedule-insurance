package com.duia.mars.service;

import com.duia.mars.core.Service;
import com.duia.mars.model.TrackFirstOrder;


/**
 * Created by CodeGenerator on 2017/09/21.
 */
public interface TrackFirstOrderService extends Service<TrackFirstOrder> {
    public void handleFirstOrder(String date) throws Exception;
}
