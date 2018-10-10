package com.duia.mars.handler;

import com.duia.mars.dto.StudyData;
import com.duia.mars.dto.TransmitStudyData;

import java.util.Date;
import java.util.List;

/**
 * 传输学习数据处理器
 * @author xingshaofei
 * @date 2017-12-27 下午 6:51
 */
public interface TransmitStudyDataHandler {

    /**
     * 组织传输学习数据：基本数据，不含压缩，加密，发送时间，签名
     * @param dataDate 数据的日期，格式yyyy-MM-dd
     * @param studyDataList 学习数据，如果为null，返回一个空的list
     * @return 传输学习数据
     */
    public List<TransmitStudyData> buildBaseTransmitStudyData(String dataDate, List<? extends StudyData> studyDataList);

    /**
     * 组织压缩版数据
     * @param tsdList 要被设置Data的加密版的传输学习数据列表，如果为null，返回一个空的list
     * @return
     */
    public List<TransmitStudyData> buildCompressedData(List<TransmitStudyData> tsdList);

    /**
     * 组织加密版数据
     * @param tsdList 要被设置Data的加密版的传输学习数据列表，如果为null，返回一个空的list
     */
    public List<TransmitStudyData> buildEncryptData(List<TransmitStudyData> tsdList);

    /**
     * 设置发送时间
     * @param sendTime 要被设置的发送时间
     * @param tsdList 要被设置发送时间的传输学习数据列表，如果为null，返回一个空的list
     * @return
     */
    public List<TransmitStudyData> setSendTime(Date sendTime, List<TransmitStudyData> tsdList);

    /**
     * 组建加密签名
     * @param tsdList 要被设置加密签名的传输学习数据列表，如果为null，返回一个空的list
     * @return
     */
    public List<TransmitStudyData> buildSignature(List<TransmitStudyData> tsdList);


}
