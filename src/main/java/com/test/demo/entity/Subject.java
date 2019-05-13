package com.test.demo.entity;

public class Subject {
    private Long subid;

    private String subname;

    private String usedyear;

    private String subkind;

    private String subsource;

    private String subtype;

    private int isoutschool;

    private String tid;

    private String asstid;

    private String status;

    private String subdirection;

    private String subsort;

    private String summary;

    private String content;

    private String requirement;

    private String refpapers;

    private String subprog;

    private String specid;

    private String condition;//在整个生命周期中，课题包括未提交、审核中、审核通过、选题中、n学生初选、已选共6种状态

    private String reviewopinion;//审核意见（课题盲审）


    private String tnames;

    private String tdept;

    private String tpost;

    public String getTnames() {
        return tnames;
    }

    public void setTnames(String tnames) {
        this.tnames = tnames;
    }

    public String getTdept() {
        return tdept;
    }

    public void setTdept(String tdept) {
        this.tdept = tdept;
    }

    public String getTpost() {
        return tpost;
    }

    public void setTpost(String tpost) {
        this.tpost = tpost;
    }

    public String getSpecid() {
        return specid;
    }

    public void setSpecid(String specid) {
        this.specid = specid;
    }

    public String getReviewopinion() {
        return reviewopinion;
    }

    public void setReviewopinion(String reviewopinion) {
        this.reviewopinion = reviewopinion;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Long getSubid() {
        return subid;
    }

    public void setSubid(Long subid) {
        this.subid = subid;
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    public String getUsedyear() {
        return usedyear;
    }

    public void setUsedyear(String usedyear) {
        this.usedyear = usedyear;
    }

    public String getSubkind() {
        return subkind;
    }

    public void setSubkind(String subkind) {
        this.subkind = subkind;
    }

    public String getSubsource() {
        return subsource;
    }

    public void setSubsource(String subsource) {
        this.subsource = subsource;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public int getIsoutschool() {
        return isoutschool;
    }

    public void setIsoutschool(int isoutschool) {
        this.isoutschool = isoutschool;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getAsstid() {
        return asstid;
    }

    public void setAsstid(String asstid) {
        this.asstid = asstid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubdirection() {
        return subdirection;
    }

    public void setSubdirection(String subdirection) {
        this.subdirection = subdirection;
    }

    public String getSubsort() {
        return subsort;
    }

    public void setSubsort(String subsort) {
        this.subsort = subsort;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getRefpapers() {
        return refpapers;
    }

    public void setRefpapers(String refpapers) {
        this.refpapers = refpapers;
    }

    public String getSubprog() {
        return subprog;
    }

    public void setSubprog(String subprog) {
        this.subprog = subprog;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subid=" + subid +
                ", subname='" + subname + '\'' +
                ", usedyear='" + usedyear + '\'' +
                ", subkind='" + subkind + '\'' +
                ", subsource='" + subsource + '\'' +
                ", subtype='" + subtype + '\'' +
                ", isoutschool=" + isoutschool +
                ", tid='" + tid + '\'' +
                ", asstid='" + asstid + '\'' +
                ", status='" + status + '\'' +
                ", subdirection='" + subdirection + '\'' +
                ", subsort='" + subsort + '\'' +
                ", summary='" + summary + '\'' +
                ", content='" + content + '\'' +
                ", requirement='" + requirement + '\'' +
                ", refpapers='" + refpapers + '\'' +
                ", subprog='" + subprog + '\'' +
                ", specid='" + specid + '\'' +
                ", condition='" + condition + '\'' +
                ", reviewopinion='" + reviewopinion + '\'' +
                ", tnames='" + tnames + '\'' +
                ", tdept='" + tdept + '\'' +
                ", tpost='" + tpost + '\'' +
                '}';
    }
}
