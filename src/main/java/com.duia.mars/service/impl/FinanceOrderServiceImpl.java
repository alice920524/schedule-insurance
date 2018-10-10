package com.duia.mars.service.impl;

import com.duia.mars.common.dto.FinanceOrderDto;
import com.duia.mars.common.util.DateUtils;
import com.duia.mars.core.AbstractService;
import com.duia.mars.dao.FinanceOrderMapper;
import com.duia.mars.model.*;
import com.duia.mars.service.CrmRefundService;
import com.duia.mars.service.CrmTurnOrderRecordService;
import com.duia.mars.service.FinanceMonthEnsureService;
import com.duia.mars.service.FinanceOrderService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * Created by CodeGenerator on 2017/09/21.
 */
@Service
//@Transactional
public class FinanceOrderServiceImpl extends AbstractService<FinanceOrder> implements FinanceOrderService {

    @Resource
    private FinanceOrderMapper financeOrderMapper;

    @Resource
    private CrmRefundService crmRefundService;

    @Resource
    private FinanceMonthEnsureService financeMonthEnsureService;

    @Resource
    private CrmTurnOrderRecordService crmTurnOrderRecordService;

    Gson gson = new Gson();


    @Override
    public void handleOrderByTime(String beginTime, String endTime, String scheduleTime) throws Exception {
        synchronized (this) {
            List<FinanceOrder> financeOrderList = financeOrderMapper.searchEnsureOrderList(beginTime, endTime);
            for (FinanceOrder financeOrder : financeOrderList) {
                financeOrder.setScheduleTime(scheduleTime);
                //// TODO: 2017/8/28  计算注册费
                // financeOrder.setRegistFee(ArithUtil.mul(ArithUtil.add(financeOrder.getCostPrice(), financeOrder.getBalance() == null ? 0 : financeOrder.getBalance()), rate));
                financeOrderMapper.insert(financeOrder);
            }
        }
    }

    @Override
    public void handleRefund() throws Exception {
        //crmRefunds  为新的退款记录 且finance_order 有记录的
        synchronized (this) {
            List<CrmRefund> crmRefunds = crmRefundService.getRefundList();
            for (CrmRefund crmRefund : crmRefunds) {
                FinanceOrder financeOrder = new FinanceOrder();
                financeOrder.setRefundClassCount(countSurplus(crmRefund.getCreateTime(), crmRefund.getOrderId()));
                financeOrder.setRefundClassTime( crmRefund.getCreateTime());
                financeOrder.setStopStatus(1);
                financeOrder.setStopDate( DateUtils.formatSort(crmRefund.getCreateTime(), DateUtils.LONG_DATE_FORMAT));
                financeOrder.setOrderId(crmRefund.getOrderId());
                financeOrderMapper.updateByOrderId(financeOrder);
            }
        }
    }

    @Override
    public void handleBalance() throws Exception {
        synchronized (this) {
            List<CrmTurnOrderRecord> crmTurnOrderRecordList = crmTurnOrderRecordService.getBalanceList();
            for (CrmTurnOrderRecord crmTurnOrderRecord : crmTurnOrderRecordList) {
                FinanceOrder financeOrder = new FinanceOrder();
                financeOrder.setBalanceClassCount(countSurplus(crmTurnOrderRecord.getCreateTime(), crmTurnOrderRecord.getOldOrderId()));
                financeOrder.setBalanceClassTime( crmTurnOrderRecord.getCreateTime());
                financeOrder.setStopStatus(1);
                financeOrder.setStopDate( DateUtils.formatSort( crmTurnOrderRecord.getCreateTime(), DateUtils.LONG_DATE_FORMAT));
                financeOrder.setOrderId(crmTurnOrderRecord.getOldOrderId());
                financeOrderMapper.updateByOrderId(financeOrder);
            }
        }
    }

    @Override
    public void handleTurn() throws Exception {
        synchronized (this) {
            List<CrmTurnOrderRecord> crmTurnOrderRecordList = crmTurnOrderRecordService.getTurnList();
            for (CrmTurnOrderRecord crmTurnOrderRecord : crmTurnOrderRecordList) {
                FinanceOrder financeOrder = new FinanceOrder();
                financeOrder.setTurnClassCount(countSurplus(crmTurnOrderRecord.getCreateTime(), crmTurnOrderRecord.getOldOrderId()));
                financeOrder.setTurnClassTime( crmTurnOrderRecord.getCreateTime());
                financeOrder.setStopStatus(1);
                financeOrder.setStopDate( DateUtils.formatSort( crmTurnOrderRecord.getCreateTime(), DateUtils.LONG_DATE_FORMAT));
                financeOrder.setOrderId(crmTurnOrderRecord.getOldOrderId());
                financeOrderMapper.updateByOrderId(financeOrder);
            }
        }
    }


    @Override
    public void handleClose() throws Exception {
        synchronized (this) {
            List<FinanceOrder> financeOrderList = financeOrderMapper.selectCloseOrder();
            for (FinanceOrder financeOrder : financeOrderList) {
                financeOrder.setCloseClassCount(countSurplus(financeOrder.getCloseClassTime(), financeOrder.getOrderId()));
                financeOrder.setCloseClassTime( financeOrder.getCloseClassTime());
                financeOrder.setStopStatus(1);
                financeOrder.setStopDate( DateUtils.formatSort( financeOrder.getCloseClassTime(), DateUtils.LONG_DATE_FORMAT));
                financeOrder.setOrderId(financeOrder.getOrderId());
                financeOrderMapper.updateByOrderId(financeOrder);
            }
        }
    }


    @Override
    public void financeSaveOrderClassCount(String scheduleTime) throws Exception {
        synchronized (this) {
            List<Integer> ids = financeOrderMapper.selectIdByScheduleTime(scheduleTime,null);
            for (Integer id : ids) {
                financeOrderMapper.updateAllCountById(id);
            }
        }
    }

    /**
     * 1. 关闭订单-班级记录
     * 2. 关闭订单记录
     *
     * @param scheduleTime
     * @throws Exception
     */
    public void closeByDate(String scheduleTime) throws Exception {
        financeMonthEnsureService.closeFmeByDate(scheduleTime);
        financeOrderMapper.closeFinorByDate(scheduleTime);
    }


    /**
     * 退款，退余额，关订单，转订单，升级老订单处理剩余
     * <p/>
     * 计算剩余多少课时
     * <p/>
     * 传入截至日期，计算
     * 这一步计算所有剩余的课时 过滤掉已经确认完成的班级
     *
     * @param monDate
     * @param orderId
     * @return
     */
    private int countSurplus(String monDate, Integer orderId) throws Exception {
//        String parDate = DateUtils.

        monDate = DateUtils.formatSort(monDate, DateUtils.MONTG_DATE_FORMAT);
        int sumCount = 0;
        synchronized (this) {

            List<FinanceMonthEnsure> financeMonthEnsureList = financeMonthEnsureService.getDetailByOrderId(orderId);

            for (FinanceMonthEnsure financeMonthEnsure : financeMonthEnsureList) {
                if ((financeMonthEnsure.getEveryMonthMoney() == null || financeMonthEnsure.getEveryMonthMoney() == "") && financeMonthEnsure.getAllCourse() == 1) {
                    sumCount += 1;
                    //标记订单班级完成确认
                    financeMonthEnsure.setStatus(1);
                    financeMonthEnsureService.update(financeMonthEnsure);
                } else {
                    List<FinanceMonthCount> financeMonthCountList = gson.fromJson(financeMonthEnsure.getEveryMonthMoney(),
                            new TypeToken<List<FinanceMonthCount>>() {
                            }.getType());
                    if (financeMonthCountList != null && financeMonthCountList.size() > 0) {
                        for (FinanceMonthCount FinanceMonthCount : financeMonthCountList) {
                            //结算日之后的再加入
                            if (FinanceMonthCount.getMonDate().compareTo(monDate) > 0) {
                                sumCount += Integer.parseInt(FinanceMonthCount.getMonCount());
                                //标记订单班级完成确认
                                financeMonthEnsure.setStatus(1);
                                financeMonthEnsureService.update(financeMonthEnsure);
                            }
                        }
                    } else {
                        throw new Exception("结算订单班级获取的数据有异常，订单id：" + orderId);
                    }
                }
            }
        }
        return sumCount;
    }


    /**
     * 计算创建月之前确认课时（不包含本月）
     * 包含每个订单每个班级所有已经结课的课程（课程数），没有班级或者课表对应的（按照1算）。
     *
     * @param scheduleTime
     */
    public void countBeforeMonth(String scheduleTime) throws Exception {
        synchronized (this) {
            List<Integer> ids = financeOrderMapper.selectIdByScheduleTime(scheduleTime,0);
            for (Integer id : ids) {
                FinanceOrder financeOrder = new FinanceOrder();
                financeOrder.setId(id);
                financeOrder.setBeforeCount(0);
                List<FinanceOrderDto> financeOrderDtoList = financeOrderMapper.selectNotSureById(id);
                for (FinanceOrderDto financeOrderDto : financeOrderDtoList) {
                    //更新班级
                    FinanceMonthEnsure financeMonthEnsure = new FinanceMonthEnsure();
                    financeOrder.setBeforeCount(financeOrder.getBeforeCount() + financeOrderDto.getAllCourse());
                    financeMonthEnsure.setId(financeOrderDto.getFmId());
                    financeMonthEnsure.setStatus(1);
                    financeMonthEnsureService.update(financeMonthEnsure);
                }
                //完成 check 更新订单记录
                // 所有课程跑完 检查是不是都能全部确认
                if (financeOrderMapper.checkNotSureCount(id) == 0) {
                    financeOrder.setStopStatus(1);
                    financeOrder.setStopDate( DateUtils.formatSort(scheduleTime, DateUtils.LONG_DATE_FORMAT));
                }
                this.update(financeOrder);
            }
        }
    }

    public void ensureLastMon(String scheduleMonDate) throws Exception {
        synchronized (this) {
            List<Integer> ids = financeOrderMapper.selectIdByScheduleTime(null,0);
            for (Integer id : ids) {
                FinanceOrder financeOrder = new FinanceOrder();
                financeOrder.setId(id);
                List<FinanceMonthEnsure> financeMonthEnsures = financeMonthEnsureService.getFmeDetailByFoId(id);
                for (FinanceMonthEnsure financeMonthEnsure : financeMonthEnsures) {
                    //更新班级
                    String endDate = DateUtils.dateToString(financeMonthEnsure.getEndDate(),DateUtils.MONTG_DATE_FORMAT);
                    if (endDate == scheduleMonDate) {
                        financeMonthEnsure.setStatus(1);
                        financeMonthEnsureService.update(financeMonthEnsure);
                    }

                }
                //完成 check 更新订单记录
                // 所有课程跑完 检查是不是都能全部确认
                if (financeOrderMapper.checkNotSureCount(id) == 0) {
                    financeOrder.setStopStatus(1);
                    financeOrder.setStopDate(DateUtils.getCurrDate(DateUtils.LONG_DATE_FORMAT));
                    this.update(financeOrder);
                }
            }
        }
    }

}
