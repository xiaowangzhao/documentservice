package com.test.demo.entity;

public class WeekSummaryKey {
    private String stuid;

    private Integer weekorder;

    public WeekSummaryKey(String stuid, Integer weekorder) {
        this.stuid = stuid;
        this.weekorder = weekorder;
    }

    public WeekSummaryKey() {

    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid == null ? null : stuid.trim();
    }

    public Integer getWeekorder() {
        return weekorder;
    }

    public void setWeekorder(Integer weekorder) {
        this.weekorder = weekorder;
    }
}