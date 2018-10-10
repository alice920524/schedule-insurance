package com.duia.mars.dao;


import com.duia.mars.core.Mapper;
import com.duia.mars.model.TransmitStudyDataHistory;
import org.apache.ibatis.annotations.Param;

public interface TransmitStudyDataHistoryMapper extends Mapper<TransmitStudyDataHistory> {

    /**
     * 根据数据日期和当前发送次数获取发送结果状态
     * @param dataDate 数据日期
     * @param currentSendTimes 当前发送次数
     * @return
     */
    Integer selectSendResultStatusByDataDateAndCurrentSendTimes(@Param("dataDate") String dataDate, @Param("currentSendTimes") int currentSendTimes);

    // todo for test emoji
    void updateEmoji(@Param("emoji") String emoji);
}