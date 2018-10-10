package com.duia.mars.service;

import com.duia.mars.common.dto.FinanceMonthEnsureDto;
import com.duia.mars.core.Service;
import com.duia.mars.model.FinanceMonthEnsure;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/09/21.
 */
public interface FinanceMonthEnsureService extends Service<FinanceMonthEnsure> {
    List<FinanceMonthEnsureDto> selectAllCourseInfo(String scheduleTime);

    /**
     * 初步保存订单-班级-课程数，开结课时间
     *
     * @param scheduleTime
     * @throws Exception
     */
    void financeSaveAllCourseInfo(String scheduleTime) throws Exception;

    /**
     * 计算订单-班级-课程-json数据  每月课程的json
     *
     * @param scheduleTime
     * @throws Exception
     */
    void financeSaveEveryMonCount(String scheduleTime) throws Exception;

    List<FinanceMonthEnsure> getDetailByOrderId(Integer orderId);

    void closeFmeByDate(String scheduleTime);

    List<FinanceMonthEnsure> getFmeDetailByFoId(Integer id);
}
