package com.wls.dmr.temp.domain;

/**
 * @Author kklu
 * @Email W258840818@163.com
 * @Data 2019/1/24 20:16
 * @Description
 */
public class UpdateTimeData {
    private String tag;
    private String time;

    public UpdateTimeData(String tag, String time) {
        this.tag = tag;
        this.time = time;
    }

    public String getTag() {
        return tag;
    }

    public String getTime() {
        return time;
    }

}
