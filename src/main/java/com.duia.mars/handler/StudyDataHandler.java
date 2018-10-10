package com.duia.mars.handler;

import com.duia.mars.dto.StudyData;

import java.util.List;

/**
 * 学习记录处理器
 * @author xingshaofei
 * @date 2017-12-27 下午 6:39
 */
public interface StudyDataHandler {

    /**
     * 学习记录数据转为字符串形式
     * @param studyDataList 学习记录数据列表
     * @return 转为的字符串，若参数：{学习记录数据列表}为null或空列表则返回null
     */
    public String dataToString(List<? extends StudyData> studyDataList);
}
