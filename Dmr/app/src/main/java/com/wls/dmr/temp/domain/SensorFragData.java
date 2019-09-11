package com.wls.dmr.temp.domain;

/**
 * @Author kklu
 * @Email W258840818@163.com
 * @Data 2019/1/24 14:19
 * @Description 服务端的数据解析后显示到UI上的实体
 */
public class SensorFragData {
    private int total;
    private String distance;
    private Long dTime;
    private String name;
    private int userId;
    private String username;

    public void setTotal(int total) {
        this.total = total;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public Long getdTime() {
        return dTime;
    }

    public void setdTime(Long dTime) {
        this.dTime = dTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "SensorFragData1{" +
                "total=" + total +
                ", distance='" + distance + '\'' +
                ", dTime=" + dTime +
                ", name='" + name + '\'' +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                '}';
    }
}
