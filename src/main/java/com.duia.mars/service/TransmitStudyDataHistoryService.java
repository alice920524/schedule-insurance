package com.duia.mars.service;

import com.duia.mars.core.Service;
import com.duia.mars.model.TransmitStudyDataHistory;

public interface TransmitStudyDataHistoryService extends Service<TransmitStudyDataHistory> {

    /**
     * 根据数据日期和当前发送次数获取发送结果状态
     * @param dataDate 数据日期
     * @param currentSendTimes 当前发送次数
     * @return
     */
    Integer findSendResultStatusByDataDateAndCurrentSendTimes(String dataDate, int currentSendTimes);
}