package com.wls.jzgy.utils;

/**
 * Author：gcc .
 * UpdateTime：2018-12-21
 * Version：v1.0  更改网络接口
 */
public class WebUrls {

    public static String baseurl = "http://39.105.78.205:1000";


    //gcc_add

//    public static String getloginurl = "http://39.105.78.205:1002/account/login";  //登录
//    public static String getuidurl = "http://39.105.78.205:1002/account/getuid";  //得到uid
//    public static String getdeviceonlineurl = "http://39.105.78.205:1002/appEquiment/equipmentonline"; //获取在线设备
//    public static String getdeviceofflineurl = "http://39.105.78.205:1002/appEquiment/equipmentoffline"; //获取离线设备
//    public static String getsetsystimeurl = "http://39.105.78.205:1002/appEquiment/setSysTime"; //设置设备系统时间
//    public static String getrealtimedata = "http://39.105.78.205:1002/appEquiment/selectDataByeid"; //接收心跳包
//    public static String getcustomurl = "http://39.105.78.205:1002/appEquiment/customMessage";  //自定义接口
//    public static String getupdataurl = "http://39.105.78.205:1002/file/version";  //获取服务器版本号

    public static String getloginurl =  baseurl + "/account/login";  //登录
//    public static String getloginurl =  "http://testapi.yiweiimage.com:8080/auth";  //登录

    public static String getuidurl = baseurl +"/account/getuid";  //得到uid
    public static String getdeviceonlineurl = baseurl +"/appEquiment/equipmentonline"; //获取在线设备
    public static String getdeviceofflineurl = baseurl +"/appEquiment/equipmentoffline"; //获取离线设备
    public static String getsetsystimeurl = baseurl +"/appEquiment/setSysTime"; //设置设备系统时间
    public static String getrealtimedata = baseurl +"/appEquiment/selectDataByeid"; //接收心跳包
    public static String getcustomurl = baseurl +"/appEquiment/customMessage";  //自定义接口
    public static String getupdataurl = baseurl +"/file/version";  //获取服务器版本号

}


