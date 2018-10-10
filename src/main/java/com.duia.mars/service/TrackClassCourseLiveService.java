package com.duia.mars.service;

import com.duia.mars.core.Service;
import com.duia.mars.dto.TrackClassCourseLiveStudyDataDto;
import com.duia.mars.model.TrackClassCourseLive;

import java.util.Collection;
import java.util.List;

public interface TrackClassCourseLiveService extends Service<TrackClassCourseLive> {

    /**
     * 获取学习数据，根据createTime
     * @param beginTime 时间区间-开始时间，不可为null，格式yyyy-MM-dd HH:mm:ss
     * @param endTime 时间区间-结束时间，不可为null，格式yyyy-MM-dd HH:mm:ss
     * @return
     */
    List<TrackClassCourseLiveStudyDataDto> findStudyDataByCreateTime(String beginTime, String endTime);

    /**
     * 获取学习数据，根据inTime
     * @param beginTime 时间区间-开始时间，不可为null，格式yyyy-MM-dd HH:mm:ss
     * @param endTime 时间区间-结束时间，不可为null，格式yyyy-MM-dd HH:mm:ss
     * @return
     */
    List<TrackClassCourseLiveStudyDataDto> findStudyDataByInTime(String beginTime, String endTime);

    /**
     * 获取学习数据，根据enterTime
     * @param beginTime 时间区间-开始时间，不可为null，格式yyyy-MM-dd HH:mm:ss
     * @param endTime 时间区间-结束时间，不可为null，格式yyyy-MM-dd HH:mm:ss
     * @return
     */
    List<TrackClassCourseLiveStudyDataDto> findStudyDataByEnterTime(String beginTime, String endTime);
}
