package com.duia.mars.common.constant;

/**
 * Created by 李恒名 on 2017/7/18.
 */
public final class Constant {
    private Constant() {
    }

    public static final Integer WEB_TYPE = 999;
    public static final Integer WAP_TYPE = 998;
    public static final String VERIFY_FAIL_PAGE = "/verifyFail";//验证失败页面路径
    public final static String SPACE = " ";
    public final static String APP_FLAG = "appFlag";


    public final static String KUAIDI_KEY = "86e62fac7dbc82df46284b5144b6e192";//快递查询API KEY
    public static final String SESSION_USER_KEY = "user";
    public static final String PAY_TYPE_MF = "pay_type_mf";//免费支付
    public static final String AUDIT_SUCCESS = "AUDIT_SUCCESS"; //审计成功


    public final static String VERIFY_REDIS_CACHE_NAME_SPACE = "verifyClassStudent:";//验证过滤器放的缓存命名空间

    public final static String VERIFY_REDIS_CACHE_KEY_CLASS_STUDENT_ID = "_VERIFY_CLASS_STUDENT_ID";//clasStudentId 数据
    public final static String VERIFY_REDIS_CACHE_KEY__USER_CLASS_STUDENT_ID = "_VERIFY_USER_CLASS_STUDENT_ID";//User + clasStudentId 是否对应数据

    public final static String VERIFY_REDIS_CACHE_KEY_TIME_OUT = "_VERIFY_TIME_OUT";//班级到期拦截验证
    public final static String VERIFY_REDIS_CACHE_KEY_DROP_OUT = "_VERIFY_DROP_OUT";//休学拦截验证

    public final static String signKey = "signature";

    public final static String VERIFY_REDIS_CACHE_KEY_GUARANTEE = "_VERIFY_GUARANTEE";//保过
    public final static String VERIFY_REDIS_CACHE_KEY_INSURANCE = "_VERIFY_INSURANCE";//保险


    public final static String md5SignKey = "duiaNiuBi)JN#ERFGBN";

}