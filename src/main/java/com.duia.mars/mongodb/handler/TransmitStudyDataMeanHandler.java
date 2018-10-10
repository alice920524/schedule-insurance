package com.duia.mars.mongodb.handler;/**
 * Created by Administrator on 2017/12/28 0028.
 */

import com.duia.mars.dto.TransmitStudyData;
import com.duia.mars.mongodb.model.TransmitStudyDataMean;

import java.util.List;

/**
 * 传输学习数据的mongo格式的处理器
 * @author xingshaofei
 * @date 2017-12-28 下午 6:48
 */
public interface TransmitStudyDataMeanHandler {

    /**
     * 组建传输学习数据的mongo格式的数据
     * @param transmitStudyDataList 传输学习数据列表，如果为null空返回一个空的列表
     * @return
     */
    List<? extends TransmitStudyDataMean> buildTransmitStudyDataMean(List<TransmitStudyData> transmitStudyDataList);

    /**
     * 组建传输学习记录，根据从mongo获取的被保存的数据
     * @param tsdmList mongo格式的数据
     * @return
     */
    List<TransmitStudyData> buildTransmitStudyData(List<? extends TransmitStudyDataMean> tsdmList);
}
