package com.test.demo.entity;

import java.util.Date;

public class StuDocument {
    private String stuid;

    private Long substuid;

    private String paper;

    private String translation;

    private String sourcecode;

    private String paperblind;

    private String assesspass;

    private String assesstid;

    private Date assessdate;

    private String operator;

    private Date operatedtime;

    private String paperstatus;

    private String paperblindstatus;

    private String translationstatus;

    private String sourcecodestatus;

    public StuDocument() {
    }

    public StuDocument(String stuid, Long substuid, String paper, String translation, String sourcecode, String paperblind, String assesspass, String assesstid, Date assessdate, String operator, Date operatedtime, String paperstatus, String paperblindstatus, String translationstatus, String sourcecodestatus) {
        this.stuid = stuid;
        this.substuid = substuid;
        this.paper = paper;
        this.translation = translation;
        this.sourcecode = sourcecode;
        this.paperblind = paperblind;
        this.assesspass = assesspass;
        this.assesstid = assesstid;
        this.assessdate = assessdate;
        this.operator = operator;
        this.operatedtime = operatedtime;
        this.paperstatus = paperstatus;
        this.paperblindstatus = paperblindstatus;
        this.translationstatus = translationstatus;
        this.sourcecodestatus = sourcecodestatus;
    }


    public StuDocument(String stuid) {
        this.stuid= stuid;
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid == null ? null : stuid.trim();
    }

    public Long getSubstuid() {
        return substuid;
    }

    public void setSubstuid(Long substuid) {
        this.substuid = substuid;
    }

    public String getPaper() {
        return paper;
    }

    public void setPaper(String paper) {
        this.paper = paper == null ? null : paper.trim();
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation == null ? null : translation.trim();
    }

    public String getSourcecode() {
        return sourcecode;
    }

    public void setSourcecode(String sourcecode) {
        this.sourcecode = sourcecode == null ? null : sourcecode.trim();
    }

    public String getPaperblind() {
        return paperblind;
    }

    public void setPaperblind(String paperblind) {
        this.paperblind = paperblind == null ? null : paperblind.trim();
    }

    public String getAssesspass() {
        return assesspass;
    }

    public void setAssesspass(String assesspass) {
        this.assesspass = assesspass == null ? null : assesspass.trim();
    }

    public String getAssesstid() {
        return assesstid;
    }

    public void setAssesstid(String assesstid) {
        this.assesstid = assesstid == null ? null : assesstid.trim();
    }

    public Date getAssessdate() {
        return assessdate;
    }

    public void setAssessdate(Date assessdate) {
        this.assessdate = assessdate;
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

    public String getPaperstatus() {
        return paperstatus;
    }

    public void setPaperstatus(String paperstatus) {
        this.paperstatus = paperstatus == null ? null : paperstatus.trim();
    }

    public String getPaperblindstatus() {
        return paperblindstatus;
    }

    public void setPaperblindstatus(String paperblindstatus) {
        this.paperblindstatus = paperblindstatus == null ? null : paperblindstatus.trim();
    }

    public String getTranslationstatus() {
        return translationstatus;
    }

    public void setTranslationstatus(String translationstatus) {
        this.translationstatus = translationstatus == null ? null : translationstatus.trim();
    }

    public String getSourcecodestatus() {
        return sourcecodestatus;
    }

    public void setSourcecodestatus(String sourcecodestatus) {
        this.sourcecodestatus = sourcecodestatus == null ? null : sourcecodestatus.trim();
    }
}