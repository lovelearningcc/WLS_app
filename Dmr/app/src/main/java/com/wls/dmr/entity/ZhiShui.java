package com.wls.dmr.entity;

/**
 * description ：
 * project name：Dmr
 * author : wls-gcc
 * creation date: 2019\3\20 0020 14:28
 *
 * @version 1.0
 */

public class ZhiShui {


    //定义的私有属性
    private Integer cmd;
    private Integer dayOfWeek;
    private String deviceNum;
    private Integer hour;
    private Integer minutes;
    private Boolean open;
    private Integer type;
    private Boolean isopen;
    private String date;
    private String eid;



    //gcc_add —— 通用接口
    private String commond;
    private String content;
//    private String deviceNum;
    private Integer deviceType;
    private String num;
    private String numLen;
    private String startAdd;
    private String data;
    private String uid;
    private String iemi;
    private String phone;

    //gcc_add —— 设置设备系统时间
    private Integer day;
    private Integer minute;
    private Integer month;
    private Integer second;
    private Integer year;
    private String etypecode;


    //无参数的构造器
    public ZhiShui() {

    }

    public void setEtypecode(String etypecode) {
        this.etypecode = etypecode;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setIemi(String iemi) {
        this.iemi = iemi;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public void setSecond(Integer second) {
        this.second = second;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setCommond(String commond) { this.commond = commond; }

    public void setContent(String content) { this.content = content; }

    public void setNum(String num) { this.num = num; }

    public void setNumLen(String numLen) { this.numLen = numLen; }

    public void setStartAdd(String startAdd) { this.startAdd = startAdd; }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    //
//    @Override
//    public String toString() {
//        return "ZhhiShui{" +
//                "cmd=" + cmd +
//                ", dayOfWeek=" + dayOfWeek +
//                ", deviceNum='" + deviceNum + '\'' +
//                ", hour=" + hour +
//                ", minutes=" + minutes +
//                ", type=" + type +
//                ", open=" + open +
//
//                '}';
//    }
}






















