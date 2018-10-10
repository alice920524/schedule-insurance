package com.duia.mars.dao;

import com.duia.mars.common.dto.FinanceMonthEnsureDto;
import com.duia.mars.core.Mapper;
import com.duia.mars.model.FinanceMonthCount;
import com.duia.mars.model.FinanceMonthEnsure;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FinanceMonthEnsureMapper extends Mapper<FinanceMonthEnsure> {
    List<FinanceMonthEnsureDto> selectAllCourseInfo(String scheduleTime);

    List<Integer> selectClassIdbyFTime(String scheduleTime);

    List<FinanceMonthCount> selectClassEveryMonCount(Integer classId);

    void updateByClassId(FinanceMonthEnsure financeMonthEnsure);

    List<FinanceMonthEnsure> getDetailByOrderId(Integer id);

    void closeFmeByDate(String scheduleTime);

    List<FinanceMonthEnsure> getFmeDetailByFoId(@Param("foId") Integer foId);
}