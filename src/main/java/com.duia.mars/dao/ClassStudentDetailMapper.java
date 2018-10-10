package com.duia.mars.dao;


import com.duia.mars.core.Mapper;
import com.duia.mars.model.ClassStudentDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface ClassStudentDetailMapper extends Mapper<ClassStudentDetail> {

    /**
     * 获取用户id和学员id，根据多个用户id
     * @param userIds 多个用户id的集合
     * @return
     */
    List<ClassStudentDetail> selectUserStudentByUserIds(@Param("userIds") Set<Integer> userIds);
}
