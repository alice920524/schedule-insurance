package com.duia.mars.mongodb.handler;

import com.duia.mars.dto.TransmitStudyData;
import com.duia.mars.mongodb.model.TrackClassCourseLiveTransmitMean;
import com.duia.mars.mongodb.model.TransmitStudyDataMean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 传输学习数据的mongo格式的处理器-正课直播
 * @author xingshaofei
 * @date 2017-12-28 下午 6:52
 */
@Component
public class TrackClassCourseLiveTransmitMeanHandler implements TransmitStudyDataMeanHandler {

    @Override
    public List<TrackClassCourseLiveTransmitMean> buildTransmitStudyDataMean(List<TransmitStudyData> transmitStudyDataList) {

        List<TrackClassCourseLiveTransmitMean> list = new ArrayList<>(transmitStudyDataList.size());

        Date now = new Date();

        for (TransmitStudyData tsd : transmitStudyDataList) {

            TrackClassCourseLiveTransmitMean tsdm = new TrackClassCourseLiveTransmitMean();

            tsdm.setDataDate(tsd.getDataDate());

            tsdm.setTotalRecordCount(tsd.getTotalRecordCount());
            tsdm.setTotalBatchCount(tsd.getTotalBatchCount());
            tsdm.setCurrentBatchNum(tsd.getCurrentBatchNum());
            tsdm.setCurrentRecordCount(tsd.getCurrentRecordCount());

            //tsdm.setData(tsd.getData());                        // 不设置data
            //tsdm.setCompressedData(tsd.getCompressedData());    // 不设置压缩后字节数据
            tsdm.setEncryptData(tsd.getEncryptData());

            tsdm.setCreateTime(now);

            list.add(tsdm);
        }

        return list;
    }

    @Override
    public List<TransmitStudyData> buildTransmitStudyData(List<? extends TransmitStudyDataMean> tsdmList) {

        List<TransmitStudyData> list = new ArrayList<>(tsdmList.size());

        for (TransmitStudyDataMean tsdm : tsdmList) {

            TransmitStudyData tsd = new TransmitStudyData();

            tsd.setDataDate(tsdm.getDataDate());

            tsd.setTotalRecordCount(tsdm.getTotalRecordCount());
            tsd.setTotalBatchCount(tsdm.getTotalBatchCount());

            tsd.setCurrentBatchNum(tsdm.getCurrentBatchNum());
            tsd.setCurrentRecordCount(tsdm.getCurrentRecordCount());

            // 不设置sendTime
            // do nothing

            // 不设置data
            //tsd.setData(tsdm.getData());
            // 不设置compressedData
            //tsd.setCompressedData(tsdm.getCompressedData());

            // 设置加密后的数据
            tsd.setEncryptData(tsdm.getEncryptData());

            // 不设置signature
            // do nothing

            list.add(tsd);
        }

        return list;
    }
}
