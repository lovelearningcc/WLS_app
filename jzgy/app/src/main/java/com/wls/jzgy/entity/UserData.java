package com.wls.jzgy.entity;

import java.io.Serializable;

/**
 * Created by Fang on 2017/9/6.
 */

public class UserData implements Serializable {
    //运行时长
    private String time;
    //起止时间
    private int startendTime;
    private int Data;

    //gcc_add
    private String eid;
    private String type;
    private String msg;
    private String timestamps;

    private String province;
    private String city;

    private String num;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(String timestamps) {
        this.timestamps = timestamps;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    //gcc_end
    public int getData() {
        return Data;
    }

    public void setData(int data) {
        Data = data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getStartendTime() {
        return startendTime;
    }

    public void setStartendTime(int startendTime) {
        this.startendTime = startendTime;
    }
}
