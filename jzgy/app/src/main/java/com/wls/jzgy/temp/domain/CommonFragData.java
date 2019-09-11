package com.wls.jzgy.temp.domain;

/**
 * @Author kklu
 * @Email W258840818@163.com
 * @Data 2019/1/22 11:11
 * @Description  服务端的数据解析后显示到UI上的实体
 */
public class CommonFragData {

    private int total;
    private int data;
    private int userId;
    private String urlTag;

    public String getUrlTag() {
        return urlTag;
    }

    public void setUrlTag(String urlTag) {
        this.urlTag = urlTag;
    }

    private String time;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        userId = userId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "CommonFragData1{" +
                "total=" + total +
                ", data=" + data +
                ", userId=" + userId +
                ", urlTag='" + urlTag + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
