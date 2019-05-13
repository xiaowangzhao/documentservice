package com.test.demo.entity;

import java.math.BigInteger;
import java.util.Date;

public class DocumentReview {
    private Long subid; //课题号



    private String subname;
    private String stuid;
    private Float significance;

    private Float designcontent;

    private Float composeability;

    private Float translationlevel;

    private Float innovative;

    private Date reviewtime;

    private String reviewerid;

    private String submitflag;

    private String reviewopinion;
    private String docstatus;

    public String getDocstatus() {
        return docstatus;
    }

    public void setDocstatus(String docstatus) {
        this.docstatus = docstatus;
    }

    public DocumentReview() {
    }



    public DocumentReview(Long subid, String stuid,String subname, Float significance, Float designcontent, Float composeability, Float translationlevel, Float innovative, Date reviewtime, String reviewerid, String submitflag, String reviewopinion) {
        this.subid = subid;
        this.stuid = stuid;
        this.subname = subname;
        this.significance = significance;
        this.designcontent = designcontent;
        this.composeability = composeability;
        this.translationlevel = translationlevel;
        this.innovative = innovative;
        this.reviewtime = reviewtime;
        this.reviewerid = reviewerid;
        this.submitflag = submitflag;
        this.reviewopinion = reviewopinion;
    }
    public DocumentReview(Long subid, String stuid, Float significance, Float designcontent, Float composeability, Float translationlevel, Float innovative, Date reviewtime, String reviewerid, String submitflag, String reviewopinion) {
        this.subid = subid;
        this.stuid = stuid;

        this.significance = significance;
        this.designcontent = designcontent;
        this.composeability = composeability;
        this.translationlevel = translationlevel;
        this.innovative = innovative;
        this.reviewtime = reviewtime;

        this.submitflag = submitflag;
        this.reviewopinion = reviewopinion;
    }


    public DocumentReview(Long subid, String s) {
        this.subid = subid;
        this.submitflag = s;
    }
    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    public Long getSubid() {
        return subid;
    }

    public void setSubid(Long subid) {
        this.subid = subid;
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid == null ? null : stuid.trim();
    }

    public Float getSignificance() {
        return significance;
    }

    public void setSignificance(Float significance) {
        this.significance = significance;
    }

    public Float getDesigncontent() {
        return designcontent;
    }

    public void setDesigncontent(Float designcontent) {
        this.designcontent = designcontent;
    }

    public Float getComposeability() {
        return composeability;
    }

    public void setComposeability(Float composeability) {
        this.composeability = composeability;
    }

    public Float getTranslationlevel() {
        return translationlevel;
    }

    public void setTranslationlevel(Float translationlevel) {
        this.translationlevel = translationlevel;
    }

    public Float getInnovative() {
        return innovative;
    }

    public void setInnovative(Float innovative) {
        this.innovative = innovative;
    }

    public Date getReviewtime() {
        return reviewtime;
    }

    public void setReviewtime(Date reviewtime) {
        this.reviewtime = reviewtime;
    }

    public String getReviewerid() {
        return reviewerid;
    }

    public void setReviewerid(String reviewerid) {
        this.reviewerid = reviewerid == null ? null : reviewerid.trim();
    }

    public String getSubmitflag() {
        return submitflag;
    }

    public void setSubmitflag(String submitflag) {
        this.submitflag = submitflag == null ? null : submitflag.trim();
    }

    public String getReviewopinion() {
        return reviewopinion;
    }

    public void setReviewopinion(String reviewopinion) {
        this.reviewopinion = reviewopinion == null ? null : reviewopinion.trim();
    }

    @Override
    public String toString() {
        return "DocumentReview{" +
                "subid=" + subid +
                ", subname='" + subname + '\'' +
                ", stuid='" + stuid + '\'' +
                ", significance=" + significance +
                ", designcontent=" + designcontent +
                ", composeability=" + composeability +
                ", translationlevel=" + translationlevel +
                ", innovative=" + innovative +
                ", reviewtime=" + reviewtime +
                ", reviewerid='" + reviewerid + '\'' +
                ", submitflag='" + submitflag + '\'' +
                ", reviewopinion='" + reviewopinion + '\'' +
                '}';
    }
}