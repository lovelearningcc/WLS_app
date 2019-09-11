package com.wls.jzgy.temp.utils;

/**
 * @Author kklu
 * @Email W258840818@163.com
 * @Data 2019/1/21 17:58
 * @Description
 */
public class UrlUtils {
    //是否是线上，true:是，false：否
    public static boolean online = false;
    private static final String END = ".shtml";
    public static final String URL_ONLINE_BASE = "http://182.61.33.72";
    public static final String URL_LOCAL_BASE = "http://192.168.1.101";
    public static String baseUrl = "http://192.168.1.101";

    public static final String URL_DAY = "everyday_runtime";
    public static final String URL_MONTH = "every_month";
    public static final String URL_THIS_WEEK = "this_week";
    public static final String URL_LASE_WEEK = "last_week";

    //zy_and
    public static final String URL_INTERVENE_TIME = "intervene_time";                     //查询用户使用干预状况
    public static final String URL_RUN_DETAIL = "run_detail";                             //查询用户运行轨迹具体内容
    public static final String URL_CHECK_USER = "check_user";                             //查询使用不合格的用户
    public static final String URL_PAY_MENT = "payment";                                      //查询用户支付情况
    public static final String URL_DISTANCE = "distance/getDistance.shtml";           //查询传感器距离

    /**
     * 非传感器url
     *
     * @param tag
     * @return
     */
    public static String getCommonUrl(String tag) {
        if (online) {
            baseUrl = URL_ONLINE_BASE;
        } else {
            baseUrl = URL_LOCAL_BASE;
        }
        return baseUrl + "/tong/" + tag + END;
    }

    /**
     * 传感器url
     *
     * @param tag
     * @return
     */
    public static String getSensorUrl(String tag) {
        if (online) {
            baseUrl = URL_ONLINE_BASE;
        } else {
            baseUrl = URL_LOCAL_BASE;
        }
        return baseUrl + "/" + tag + END;
    }

}
