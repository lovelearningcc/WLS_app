package com.wls.jzgy.entity;

/**
 * description ：
 * project name：jzgy
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

    //有参数的构造器
    public ZhiShui(Integer cmd, Integer dayOfWeek, String deviceNum, Integer hour, Integer minutes, Boolean open, Integer type,
                   String commond, String content, Integer deviceType, String num, String numLen, String startAdd,
                   Boolean isopen, Integer day, Integer minute, Integer month, Integer second, Integer year,
                   String data, String uid, String iemi, String phone, String date, String eid, String etypecode) {


        this.cmd = cmd;
        this.dayOfWeek = dayOfWeek;
        this.deviceNum = deviceNum;
        this.hour = hour;
        this.minutes = minutes;
        this.open = open;
        this.type = type;

        this.commond = commond;
        this.content = content;
        this.deviceType = deviceType;
        this.num = num;
        this.numLen = numLen;
        this.startAdd = startAdd;
        this.isopen = isopen;

        this.day = day;
        this.minute = minute;
        this.month = month;
        this.second = second;
        this.year = year;
        this.data = data;
        this.uid = uid;
        this.iemi = iemi;
        this.phone = phone;

        this.date = date;
        this.eid = eid;
        this.etypecode = etypecode;
    }

    public String getEtypecode() {
        return etypecode;
    }
    public void setEtypecode(String etypecode) {
        this.etypecode = etypecode;
    }

    public String getEid() {
        return eid;
    }
    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIemi() {
        return iemi;
    }
    public void setIemi(String iemi) {
        this.iemi = iemi;
    }

    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getDay() {
        return day;
    }
    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getMinute() {
        return minute;
    }
    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public Integer getMonth() {
        return month;
    }
    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getSecond() {
        return second;
    }
    public void setSecond(Integer second) {
        this.second = second;
    }

    public Integer getYear() {
        return year;
    }
    public void setYear(Integer year) {
        this.year = year;
    }

    public Boolean getIsopen() {
        return isopen;
    }

    public void setIsopen(Boolean isopen) {
        this.isopen = isopen;
    }

    public String getCommond() { return commond; }
    public void setCommond(String commond) { this.commond = commond; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public Integer getDeviceType() { return deviceType; }

    public void setDeviceType(Integer deviceType) { this.deviceType = deviceType; }

    public String getNum() { return num; }

    public void setNum(String num) { this.num = num; }

    public String getNumLen() { return numLen; }

    public void setNumLen(String numLen) { this.numLen = numLen; }

    public String getStartAdd() { return startAdd; }

    public void setStartAdd(String startAdd) { this.startAdd = startAdd; }

    public Integer getCmd() {
        return cmd;
    }

    public void setCmd(Integer cmd) {
        this.cmd = cmd;
    }

    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinutes() {
        return minutes;
    }
    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public Boolean getOpen() {
        return open;
    }
    public void setOpen(Boolean open) {
        this.open = open;
    }

    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
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






















