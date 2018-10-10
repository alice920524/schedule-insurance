package com.duia.mars.service;

import com.duia.mars.core.Service;
import com.duia.mars.model.FinanceOrder;


/**
 * Created by CodeGenerator on 2017/09/21.
 */
public interface FinanceOrderService extends Service<FinanceOrder> {
    public void handleOrderByTime(String beginTime, String endTime, String scheduleTime) throws Exception;

    public void handleRefund() throws Exception;

    public void handleBalance() throws Exception;

    public void handleTurn() throws Exception;

    public void handleClose() throws Exception;

    /**
     * 更新所有订单的总课时和结课时间
     * @param scheduleTime
     * @throws Exception
     */
    public void financeSaveOrderClassCount(String scheduleTime) throws Exception;

    public void closeByDate(String scheduleTime) throws Exception;

    // public int countSurplus(String monDate, Long orderId) throws Exception;

    public void countBeforeMonth(String scheduleTime) throws Exception;

    public void ensureLastMon(String scheduleMonDate) throws Exception;
}
