package com.duia.mars.service.impl;


import com.duia.mars.core.AbstractService;
import com.duia.mars.dao.ClassStudentDetailMapper;
import com.duia.mars.model.ClassStudentDetail;
import com.duia.mars.service.ClassStudentDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ClassStudentDetailServiceImpl extends AbstractService<ClassStudentDetail> implements ClassStudentDetailService {

    @Resource
    private ClassStudentDetailMapper classStudentDetailMapper;


    @Override
    public List<ClassStudentDetail> findUserStudentByUserIds(Set<Integer> userIdSet) {

        return classStudentDetailMapper.selectUserStudentByUserIds(userIdSet);
    }
}
