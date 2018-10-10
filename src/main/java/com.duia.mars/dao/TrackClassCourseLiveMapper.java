package com.duia.mars.dao;


import com.duia.mars.core.Mapper;
import com.duia.mars.dto.TrackClassCourseLiveStudyDataDto;
import com.duia.mars.model.TrackClassCourseLive;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TrackClassCourseLiveMapper extends Mapper<TrackClassCourseLive> {

    /**
     * 获取学习数据，不含教师资格证sku的，根据createTime
     * @param beginTime 时间区间-开始时间，不可为null，格式yyyy-MM-dd HH:mm:ss
     * @param endTime 时间区间-结束时间，不可为null，格式yyyy-MM-dd HH:mm:ss
     * @return
     */
    List<TrackClassCourseLiveStudyDataDto> selectStudyDataNoTeachSkuByCreateTime(
            @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 获取学习数据，只含教师资格证sku的，根据createTime
     * @param beginTime 时间区间-开始时间，不可为null，格式yyyy-MM-dd HH:mm:ss
     * @param endTime 时间区间-结束时间，不可为null，格式yyyy-MM-dd HH:mm:ss
     * @return
     */
    List<TrackClassCourseLiveStudyDataDto> selectStudyDataOnlyTeachSkuByCreateTime(
            @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 获取学习数据，，一些额外指定的人的，根据createTime
     * @param beginTime 时间区间-开始时间，不可为null，格式yyyy-MM-dd HH:mm:ss
     * @param endTime 时间区间-结束时间，不可为null，格式yyyy-MM-dd HH:mm:ss
     * @return
     */
    List<TrackClassCourseLiveStudyDataDto> selectStudyDataAssignedByCreateTime(
            @Param("beginTime") String beginTime, @Param("endTime") String endTime);


    /**
     * 获取学习数据，不含教师资格证sku的，根据inTime
     * @param beginTime 时间区间-开始时间，不可为null，格式yyyy-MM-dd HH:mm:ss
     * @param endTime 时间区间-结束时间，不可为null，格式yyyy-MM-dd HH:mm:ss
     * @return
     */
    List<TrackClassCourseLiveStudyDataDto> selectStudyDataNoTeachSkuByInTime(
            @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 获取学习数据，只含教师资格证sku的，根据inTime
     * @param beginTime 时间区间-开始时间，不可为null，格式yyyy-MM-dd HH:mm:ss
     * @param endTime 时间区间-结束时间，不可为null，格式yyyy-MM-dd HH:mm:ss
     * @return
     */
    List<TrackClassCourseLiveStudyDataDto> selectStudyDataOnlyTeachSkuByInTime(
            @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 获取学习数据，一些额外指定的人的，根据inTime
     * @param beginTime 时间区间-开始时间，不可为null，格式yyyy-MM-dd HH:mm:ss
     * @param endTime 时间区间-结束时间，不可为null，格式yyyy-MM-dd HH:mm:ss
     * @return
     */
    List<TrackClassCourseLiveStudyDataDto> selectStudyDataAssignedByInTime(
            @Param("beginTime") String beginTime, @Param("endTime") String endTime);


    /**
     * 获取学习数据，不含教师资格证sku的，根据enterTime
     * @param beginTime 时间区间-开始时间，不可为null，格式yyyy-MM-dd HH:mm:ss
     * @param endTime 时间区间-结束时间，不可为null，格式yyyy-MM-dd HH:mm:ss
     * @return
     */
    List<TrackClassCourseLiveStudyDataDto> selectStudyDataNoTeachSkuByEnterTime(
            @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 获取学习数据，只含教师资格证sku的，根据enterTime
     * @param beginTime 时间区间-开始时间，不可为null，格式yyyy-MM-dd HH:mm:ss
     * @param endTime 时间区间-结束时间，不可为null，格式yyyy-MM-dd HH:mm:ss
     * @return
     */
    List<TrackClassCourseLiveStudyDataDto> selectStudyDataOnlyTeachSkuByEnterTime(
            @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 获取学习数据，一些额外指定的人的，根据enterTime
     * @param beginTime 时间区间-开始时间，不可为null，格式yyyy-MM-dd HH:mm:ss
     * @param endTime 时间区间-结束时间，不可为null，格式yyyy-MM-dd HH:mm:ss
     * @return
     */
    List<TrackClassCourseLiveStudyDataDto> selectStudyDataAssignedByEnterTime(
            @Param("beginTime") String beginTime, @Param("endTime") String endTime);

}
