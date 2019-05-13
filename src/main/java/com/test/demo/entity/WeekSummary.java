package com.test.demo.entity;

import java.util.Date;

public class WeekSummary extends WeekSummaryKey {
    private String thiscontent;

    private String support;

    private String nextcontent;

    private String tutorreply;

    private String tutorreview;

    private String operator;

    private Date operatedtime;

    private Short ismodifed;

    public WeekSummary(String stuid, Integer weekorder) {
        super(stuid, weekorder);
    }

    public WeekSummary(String stuid, Integer weekorder, String thiscontent, String support, String nextcontent, String tutorreply, String tutorreview, String operator, Date operatedtime, Short ismodifed) {
        super(stuid, weekorder);
        this.thiscontent = thiscontent;
        this.support = support;
        this.nextcontent = nextcontent;
        this.tutorreply = tutorreply;
        this.tutorreview = tutorreview;
        this.operator = operator;
        this.operatedtime = operatedtime;
        this.ismodifed = ismodifed;
    }

    public WeekSummary(String stuid, Integer weekorder, String thiscontent, String support, String nextcontent ) {
        super(stuid, weekorder);
        this.thiscontent = thiscontent;
        this.support = support;
        this.nextcontent = nextcontent;

    }
    public WeekSummary(String stuid, Integer weekorder,String tutorreply,String tutorreview  ) {

        super(stuid, weekorder);
        this.tutorreply =tutorreply;
        this.tutorreview =tutorreview;

    }

    public WeekSummary() {
        super();
    }

    public WeekSummary(String stuid, int weeknum, short i) {
        super(stuid, weeknum);
        this.ismodifed = i;
    }

    public String getThiscontent() {
        return thiscontent;
    }

    public void setThiscontent(String thiscontent) {
        this.thiscontent = thiscontent == null ? null : thiscontent.trim();
    }

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support == null ? null : support.trim();
    }

    public String getNextcontent() {
        return nextcontent;
    }

    public void setNextcontent(String nextcontent) {
        this.nextcontent = nextcontent == null ? null : nextcontent.trim();
    }

    public String getTutorreply() {
        return tutorreply;
    }

    public void setTutorreply(String tutorreply) {
        this.tutorreply = tutorreply == null ? null : tutorreply.trim();
    }

    public String getTutorreview() {
        return tutorreview;
    }

    public void setTutorreview(String tutorreview) {
        this.tutorreview = tutorreview == null ? null : tutorreview.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Date getOperatedtime() {
        return operatedtime;
    }

    public void setOperatedtime(Date operatedtime) {
        this.operatedtime = operatedtime;
    }

    public Short getIsmodifed() {
        return ismodifed;
    }

    public void setIsmodifed(Short ismodifed) {
        this.ismodifed = ismodifed;
    }
}