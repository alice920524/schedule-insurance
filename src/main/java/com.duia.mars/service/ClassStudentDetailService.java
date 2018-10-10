package com.duia.mars.service;

import com.duia.mars.core.Service;
import com.duia.mars.model.ClassStudentDetail;

import java.util.List;
import java.util.Set;

public interface ClassStudentDetailService extends Service<ClassStudentDetail> {

    /**
     * 获取用户id和学员id，根据多个用户id
     * @param userIdSet 多个用户id的集合
     * @return
     */
    List<ClassStudentDetail> findUserStudentByUserIds(Set<Integer> userIdSet);
}
