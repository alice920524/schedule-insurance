package com.duia.mars.dao;

import com.duia.mars.common.dto.FinanceOrderDto;
import com.duia.mars.core.Mapper;
import com.duia.mars.model.FinanceOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FinanceOrderMapper extends Mapper<FinanceOrder> {

    List<FinanceOrder> searchEnsureOrderList(@Param("beginTime") String beginTime, @Param("endTime") String endTime);

    void updateByOrderId(FinanceOrder financeOrder);

    void closeFinorByDate(String scheduleTime);

    List<FinanceOrderDto> selectNotSureById(Integer id);

    List<Integer> selectIdByScheduleTime(@Param("scheduleTime") String scheduleTime, @Param("stopStatus") Integer stopStatus);

    Integer checkNotSureCount(Integer id);

    List<FinanceOrder> selectCloseOrder();

    void updateAllCountById(Integer id);
}