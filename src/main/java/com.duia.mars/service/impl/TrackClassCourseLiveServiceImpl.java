package com.duia.mars.service.impl;


import com.duia.mars.common.util.CollectUtils;
import com.duia.mars.common.util.DateUtils;
import com.duia.mars.core.AbstractService;
import com.duia.mars.dao.TrackClassCourseLiveMapper;
import com.duia.mars.dto.TrackClassCourseLiveStudyDataDto;
import com.duia.mars.model.ClassStudentDetail;
import com.duia.mars.model.TrackClassCourseLive;
import com.duia.mars.service.ClassStudentDetailService;
import com.duia.mars.service.TrackClassCourseLiveService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
@Transactional
public class TrackClassCourseLiveServiceImpl extends AbstractService<TrackClassCourseLive> implements TrackClassCourseLiveService {

    private static final Logger logger = LoggerFactory.getLogger(TrackClassCourseLiveServiceImpl.class);

    @Resource
    private TrackClassCourseLiveMapper trackClassCourseLiveMapper;

    @Resource
    private ClassStudentDetailService classStudentDetailService;


    // todo 是否合并同一次进入, true为合并，false为不合并
    private boolean combineSameEnter = true;


    @Override
    public List<TrackClassCourseLiveStudyDataDto> findStudyDataByCreateTime(String beginTime, String endTime) {

        if (StringUtils.isBlank(beginTime) || StringUtils.isBlank(endTime))
            throw new IllegalArgumentException("参数：beginTime与endTime都不可为空");

        // get data
        List<TrackClassCourseLiveStudyDataDto> noTeachList = trackClassCourseLiveMapper.selectStudyDataNoTeachSkuByCreateTime(beginTime, endTime);
        List<TrackClassCourseLiveStudyDataDto> onlyTeachList = trackClassCourseLiveMapper.selectStudyDataOnlyTeachSkuByCreateTime(beginTime, endTime);
        List<TrackClassCourseLiveStudyDataDto> assignedList = trackClassCourseLiveMapper.selectStudyDataAssignedByCreateTime(beginTime, endTime);

        // union
        Collection<TrackClassCourseLiveStudyDataDto> union = CollectUtils.union(noTeachList, onlyTeachList, assignedList);

        // studentId
        // 没有studentId的userId
        Set<Integer> userIdSet = extractUserIdByEmptyStudentId(union);
        // 根据userId获取studentId
        List<ClassStudentDetail> userStudentList = classStudentDetailService.findUserStudentByUserIds(userIdSet);

        // 组建返回数据
        List<TrackClassCourseLiveStudyDataDto> list = buildStudyDataDto(union, userStudentList);

        return list;
    }


    @Override
    public List<TrackClassCourseLiveStudyDataDto> findStudyDataByInTime(String beginTime, String endTime) {

        if (StringUtils.isBlank(beginTime) || StringUtils.isBlank(endTime))
            throw new IllegalArgumentException("参数：beginTime与endTime都不可为空");

        // get data
        List<TrackClassCourseLiveStudyDataDto> noTeachList = trackClassCourseLiveMapper.selectStudyDataNoTeachSkuByInTime(beginTime, endTime);
        List<TrackClassCourseLiveStudyDataDto> onlyTeachList = trackClassCourseLiveMapper.selectStudyDataOnlyTeachSkuByInTime(beginTime, endTime);
        List<TrackClassCourseLiveStudyDataDto> assignedList = trackClassCourseLiveMapper.selectStudyDataAssignedByInTime(beginTime, endTime);

        // union
        Collection<TrackClassCourseLiveStudyDataDto> union = CollectUtils.union(noTeachList, onlyTeachList, assignedList);

        // studentId
        // 没有studentId的userId
        Set<Integer> userIdSet = extractUserIdByEmptyStudentId(union);
        // 根据userId获取studentId
        List<ClassStudentDetail> userStudentList = classStudentDetailService.findUserStudentByUserIds(userIdSet);

        // 组建返回数据/过滤数据
        List<TrackClassCourseLiveStudyDataDto> list = buildStudyDataDto(union, userStudentList);

        return list;
    }

    @Override
    public List<TrackClassCourseLiveStudyDataDto> findStudyDataByEnterTime(String beginTime, String endTime) {

        if (StringUtils.isBlank(beginTime) || StringUtils.isBlank(endTime))
            throw new IllegalArgumentException("参数：beginTime与endTime都不可为空");

        // get data
        List<TrackClassCourseLiveStudyDataDto> noTeachList = trackClassCourseLiveMapper.selectStudyDataNoTeachSkuByEnterTime(beginTime, endTime);
        List<TrackClassCourseLiveStudyDataDto> onlyTeachList = trackClassCourseLiveMapper.selectStudyDataOnlyTeachSkuByEnterTime(beginTime, endTime);
        List<TrackClassCourseLiveStudyDataDto> assignedList = trackClassCourseLiveMapper.selectStudyDataAssignedByEnterTime(beginTime, endTime);

        // union
        Collection<TrackClassCourseLiveStudyDataDto> union = CollectUtils.union(noTeachList, onlyTeachList, assignedList);

        if (union.isEmpty())
            return new ArrayList<>();

        // studentId
        // 没有studentId的userId
        Set<Integer> userIdSet = extractUserIdByEmptyStudentId(union);

        List<ClassStudentDetail> userStudentList;

        if (!userIdSet.isEmpty()) {
            // 根据userId获取studentId
            userStudentList = classStudentDetailService.findUserStudentByUserIds(userIdSet);
        } else {
            userStudentList = new ArrayList<>();
        }

        // 组建返回数据/过滤数据
        List<TrackClassCourseLiveStudyDataDto> list = buildStudyDataDto(union, userStudentList);

        return list;
    }


    private Set<Integer> extractUserIdByEmptyStudentId(Collection<TrackClassCourseLiveStudyDataDto> sds) {

        Set<Integer> set = new HashSet<>();

        for (TrackClassCourseLiveStudyDataDto sd : sds) {

            if (studentIdIsEmpty(sd.getStudentId())) {
                set.add(sd.getUserId());
            }
        }

        return set;
    }


    /**
     * 组建（过滤）学习记录数据
     * @param sourceList 要被组建的学习记录源数据
     * @param userStudentList 存储userId对应studentId的list
     * @return
     */
    private List<TrackClassCourseLiveStudyDataDto> buildStudyDataDto(
            Collection<TrackClassCourseLiveStudyDataDto> sourceList, List<ClassStudentDetail> userStudentList) {

        List<TrackClassCourseLiveStudyDataDto> list = new ArrayList<>(sourceList.size());

        if (null == sourceList || sourceList.isEmpty())
            return list;

        // 去重使用
        Map<String, TrackClassCourseLiveStudyDataDto> sdMap = new HashMap<>();

        Map<Integer, Integer> userStudentPairMap = toMapKeyUserValueStudent(userStudentList);

        boolean useUniqueMap = combineSameEnter;   // 是否使用唯一记录map

        for (TrackClassCourseLiveStudyDataDto sd : sourceList) {

            // 过滤数据
            if (isInvalid(sd))
                continue ;

            // 设置studentId
            if (studentIdIsEmpty(sd.getStudentId()))
                sd.setStudentId(userStudentPairMap.get(sd.getUserId()));

            if (useUniqueMap) {
                // 同一次进入组为一条，以最大updateTime的记录为最终记录
                String uniqueEnterKey = uniqueEnter(sd);
                TrackClassCourseLiveStudyDataDto uniqueEnterSd = sdMap.get(uniqueEnterKey);
                if (null == uniqueEnterSd) {
                    sdMap.put(uniqueEnterKey, sd);
                } else if (uniqueEnterSd.getUpdateTime().getTime() < sd.getUpdateTime().getTime()) {
                    sdMap.put(uniqueEnterKey, sd);
                }
            }

            if (!useUniqueMap)
                list.add(sd);
        }

        if (useUniqueMap) {
            // sdMap转为list
            Collection<TrackClassCourseLiveStudyDataDto> values = sdMap.values();
            for (TrackClassCourseLiveStudyDataDto v : values) {
                list.add(v);
            }
        }

        return list;
    }

    private String uniqueEnter(TrackClassCourseLiveStudyDataDto sd) {
        StringBuilder sb = new StringBuilder();
        sb.append(sd.getUserId()).append("-");
        sb.append(sd.getCourseId()).append("-");
        sb.append(sd.getEnterTime().getTime());
        return sb.toString();
    }

    private boolean isInvalid(TrackClassCourseLiveStudyDataDto sd) {

        // 课程id为空
        if (null == sd.getCourseId() || 0 == sd.getCourseId())
            return true;

        /*if (null == sd.getEnterTime())
            return true;
        if (null == sd.getUpdateTime())
            return true;*/

        // 进入时间小于更新时间
        if (sd.getEnterTime().getTime() > sd.getUpdateTime().getTime())
            return true;

        // 进出时间与上课时间没有交集
        if (!courseTimeHasIntersectionWithAttendanceTime(sd))
            return true;

        return false;
    }

    /**
     * 判断课表上课时间与出勤时间是否有交集
     * @param sd
     * @return 有交集返回true，否则返回false
     */
    private boolean courseTimeHasIntersectionWithAttendanceTime(TrackClassCourseLiveStudyDataDto sd) {

        String courseStartTime = sd.getCourseStartTime();
        Date startTime = DateUtils.stringtoDate(courseStartTime, DateUtils.FORMAT_TWO);
        String courseEndTime = sd.getCourseEndTime();
        Date endTime = DateUtils.stringtoDate(courseEndTime, DateUtils.FORMAT_TWO);

        return hasIntersection(startTime, endTime, sd.getEnterTime(), sd.getUpdateTime());
    }

    /**
     * 判断baseStart-endStart与compareStart与compareEnd是否有交集
     * @param baseStart
     * @param baseEnd
     * @param compareStart
     * @param compareEnd
     * @return 有交集返回true，否则返回false
     */
    private boolean hasIntersection(Date baseStart, Date baseEnd, Date compareStart, Date compareEnd) {

        // 如果compareEnd小于baseStart
        if (compareEnd.getTime() < baseStart.getTime())
            return false;
        // 如果compareStart小于baseEnd
        if (compareStart.getTime() > baseEnd.getTime())
            return false;

        // 其余的为有交集的
        return true;
    }

    /**
     * 转为以userId作为key，学员id作为value的map
     * @param userStudentList 存放userId，studentId的list
     * @return
     */
    private Map<Integer,Integer> toMapKeyUserValueStudent(List<ClassStudentDetail> userStudentList) {

        Map<Integer, Integer> map = new HashMap<>();

        for (ClassStudentDetail csd : userStudentList) {

            map.put(csd.getUserId(), csd.getId());
        }

        return map;
    }

    private boolean studentIdIsEmpty(Integer studentId) {

        if (null == studentId || 0 >= studentId)
            return true;
        return false;
    }


}
