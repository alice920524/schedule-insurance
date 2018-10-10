package com.duia.mars.handler;

import com.duia.mars.dto.StudyData;
import com.google.gson.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 学习数据处理器-正课直播
 * @author xingshaofei
 * @date 2017-12-27 下午 6:40
 */
@Component
public class TrackClassCourseLiveStudyDataHandler implements StudyDataHandler {

    // 字段映射
    private static Map<String, String> fieldMappingMap = new HashMap<>();

    // 排除字段
    private static Set<String> exField = new HashSet<>();

    static {

        // 字段映射
        fieldMappingMap.put("studentId",            "a");   // 学员id
        //fieldMappingMap.put("insuranceUserName",    "b");   // 保险人姓名
        //fieldMappingMap.put("idCard",               "c");   // 身份证号
        //fieldMappingMap.put("payTime",              "d");   // 购买日期，格式：yyyy-MM-dd HH:mm:ss
        //fieldMappingMap.put("insurancePeriod",      "e");   // 保期年份，格式：yyyy年
        //fieldMappingMap.put("signDate",             "f");   // 签署时间，格式：yyyy-MM-dd HH:mm:ss
        fieldMappingMap.put("classNo",              "b");   // 所在班级班号
        fieldMappingMap.put("classCourseCount",     "c");   // 班级课表总节数
        fieldMappingMap.put("classTypeId",          "d");   // 班型id
        fieldMappingMap.put("classTypeTitle",       "e");   // 班型名称
        fieldMappingMap.put("scheduleName",         "f");   // 课表名称
        fieldMappingMap.put("chapterName",          "g");   // 章名称
        fieldMappingMap.put("courseId",             "h");   // 节id
        fieldMappingMap.put("courseName",           "i");   // 节名称
        fieldMappingMap.put("courseStartTime",      "j");   // 课表上课时间，格式：yyyy-MM-dd HH:mm
        fieldMappingMap.put("courseEndTime",        "k");   // 课表下课时间，格式：yyyy-MM-dd HH:mm
        fieldMappingMap.put("source",               "l");   // 数据来源
        fieldMappingMap.put("enterTime",            "m");   // 进入时间，格式：yyyy-MM-dd HH:mm:ss
        fieldMappingMap.put("updateTime",           "n");   // 更新时间，格式：yyyy-MM-dd HH:mm:ss

        // 排除字段
        exField.add("id");
        exField.add("userId");
        exField.add("studentIdAddition");
        exField.add("skuId");
    }



    @Override
    public String dataToString(List<? extends StudyData> studyDataList) {

        FieldNamingStrategy fns = new FieldNamingStrategy() {

            @Override
            public String translateName(Field f) {

                String name = fieldMappingMap.get(f.getName());

                if (null == name)
                    return f.getName();

                return name;
            }
        };

        ExclusionStrategy es = new ExclusionStrategy() {

            @Override
            public boolean shouldSkipField(FieldAttributes f) {

                return exField.contains(f.getName());
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {

                return false;
            }
        };

        GsonBuilder gb = new GsonBuilder();
        Gson g = gb
                .setFieldNamingStrategy(fns)
                .setExclusionStrategies(es)
                .serializeNulls()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();

        return g.toJson(studyDataList);
    }

}
