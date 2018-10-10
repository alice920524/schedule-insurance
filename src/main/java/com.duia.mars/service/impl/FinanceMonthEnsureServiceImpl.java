package com.duia.mars.service.impl;

import com.duia.mars.common.dto.FinanceMonthEnsureDto;
import com.duia.mars.core.AbstractService;
import com.duia.mars.dao.FinanceMonthEnsureMapper;
import com.duia.mars.model.FinanceMonthCount;
import com.duia.mars.model.FinanceMonthEnsure;
import com.duia.mars.service.FinanceMonthEnsureService;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2017/09/21.
 */
@Service
//@Transactional
public class FinanceMonthEnsureServiceImpl extends AbstractService<FinanceMonthEnsure> implements FinanceMonthEnsureService {

    @Resource
    private FinanceMonthEnsureMapper financeMonthEnsureMapper;


    Gson gson = new Gson();

    @Override
    public List<FinanceMonthEnsureDto> selectAllCourseInfo(String scheduleTime) {
        return financeMonthEnsureMapper.selectAllCourseInfo(scheduleTime);
    }


    @Override
    public void financeSaveAllCourseInfo(String scheduleTime) throws Exception {
        List<FinanceMonthEnsureDto> financeMonthEnsureDtoList = financeMonthEnsureMapper.selectAllCourseInfo(scheduleTime);
        if (financeMonthEnsureDtoList != null && financeMonthEnsureDtoList.size() > 0) {
            synchronized (financeMonthEnsureDtoList) {
                for (FinanceMonthEnsureDto financeMonthEnsureDto : financeMonthEnsureDtoList) {
                    System.err.println(financeMonthEnsureDto.toString());
                    financeMonthEnsureMapper.insert(financeMonthEnsureDto);
                }
            }
        }
    }

    @Override
    public void financeSaveEveryMonCount(String scheduleTime) throws Exception {
        //取出来需要计算的
        List<Integer> classIds = financeMonthEnsureMapper.selectClassIdbyFTime(scheduleTime);
        if (classIds != null && classIds.size() > 0)
            synchronized (classIds) {
                for (Integer classId : classIds) {
                    List<FinanceMonthCount> financeMonthCounts = financeMonthEnsureMapper.selectClassEveryMonCount(classId);
                    FinanceMonthEnsure financeMonthEnsure = new FinanceMonthEnsure();
                    if (financeMonthCounts != null && financeMonthCounts.size() > 0) {
                        String everyMonEnsure = gson.toJson(financeMonthCounts);
                        financeMonthEnsure.setEveryMonthMoney(everyMonEnsure);
                        financeMonthEnsure.setClassId(classId);
                        financeMonthEnsureMapper.updateByClassId(financeMonthEnsure);
                    } else {
                        financeMonthEnsure.setAllCourse(1);
                        financeMonthEnsure.setClassId(classId);
                        financeMonthEnsureMapper.updateByClassId(financeMonthEnsure);
                    }
                }
            }
    }



    @Override
    public List<FinanceMonthEnsure> getDetailByOrderId(Integer orderId) {
        return financeMonthEnsureMapper.getDetailByOrderId(orderId);
    }

    @Override
    public void closeFmeByDate(String scheduleTime) {
        financeMonthEnsureMapper.closeFmeByDate(scheduleTime);
    }

    @Override
    public List<FinanceMonthEnsure> getFmeDetailByFoId(Integer id) {
        return financeMonthEnsureMapper.getFmeDetailByFoId(id);
    }
}
