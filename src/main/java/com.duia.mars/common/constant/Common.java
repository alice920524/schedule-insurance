package com.duia.mars.common.constant;

/**
 * Created by liuhao on 16/7/7.
 */
public class Common {

    public static class ScheduleType {

        /**
         * 统计首次付费时长
         */
        public final static Integer TYPE_TRACK_FIRST_INDEX = 8;


        /**
         * 超48小时关闭班级
         */
        public final static Integer TYPE_COLSE_CLASS_INDEX = 9;

        /**
         * 用户首单统计调度
         */
        public final static Integer TYP_USER_FIRST_ORDER_INDEX = 12;

        /**
         * 重置用户积分调度
         */
        public final static Integer TYPE_RESET_USER_POINT = 16;

        /**
         *  每天财务确认收入计算订单
         */
        public final static Integer TYPE_FINANCE_ENSURE_DAY_INDEX = 19;

        /**
         * 每月财务确认收入计算订单
         */
        public final static Integer TYPE_FINANCE_ENSURE_MON_INDEX = 20;

        /**
         * 每日传输学习记录数据调度
         */
        public final static Integer TYPE_TRANSMIT_STUDY_DATA_INDEX = 30;

        /**
         * 每日重新传输学习记录数据调度
         */
        public final static Integer TYPE_RE_TRANSMIT_STUDY_DATA_INDEX = 31;

    }

    public static class CacheName{
        public final static String SCHEDULE_VISIT_LOG = "duia.sale.schedule.visit.log";
    }
}
