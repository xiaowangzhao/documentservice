package com.test.demo.entity;

public class Teacher {
    private String tno;//教职工号
    private String tname;//教师名称
    private String tbirthday;//出生日期
    private String tsex;//性别
    private String tage;//年龄
    private String tid;//身份证号
    private String tpstatus;//政治面貌
    private String tdept;//教研室
    private String tpost;//职称
    private String tstudy;//研究方向
    private String temail;//邮箱
    private String tphone;//电话号码
    private String tremark;//备注
    private String tstart;//参加工作时间
    private String tctime;//来校报道时间



    public String getTno() {
        return tno;
    }
    public void setTno(String tno) {
        this.tno = tno;
    }
    public String getTname() {
        return tname;
    }
    public void setTname(String tname) {
        this.tname = tname;
    }
    public String getTsex() {
        return tsex;
    }
    public void setTsex(String tsex) {
        this.tsex = tsex;
    }
    public String getTage() {
        return tage;
    }
    public void setTage(String tage) {
        this.tage = tage;
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

    public String getTstudy() {
        return tstudy;
    }
    public void setTstudy(String tstudy) {
        this.tstudy = tstudy;
    }
    public String getTemail() {
        return temail;
    }
    public void setTemail(String temail) {
        this.temail = temail;
    }
    public String getTphone() {
        return tphone;
    }
    public void setTphone(String tphone) {
        this.tphone = tphone;
    }
    public String getTremark() {
        return tremark;
    }
    public void setTremark(String tremark) {
        this.tremark = tremark;
    }
    public String getTstart() {
        return tstart;
    }
    public void setTstart(String tstart) {
        this.tstart = tstart;
    }
    public String getTbirthday() {
        return tbirthday;
    }
    public void setTbirthday(String tbirthday) {
        this.tbirthday = tbirthday;
    }
    public String getTid() {
        return tid;
    }
    public void setTid(String tid) {
        this.tid = tid;
    }
    public String getTpstatus() {
        return tpstatus;
    }
    public void setTpstatus(String tpstatus) {
        this.tpstatus = tpstatus;
    }
    public String getTctime() {
        return tctime;
    }
    public void setTctime(String tctime) {
        this.tctime = tctime;
    }

    @Override
    public String toString() {
        return "Teacher [tno=" + tno + ", tname=" + tname + ", tbirthday=" + tbirthday + ", tsex=" + tsex + ", tage="
                + tage + ", tid=" + tid + ", tpstatus=" + tpstatus + ", tdept=" + tdept + ", tpost=" + tpost
                + ", tstudy=" + tstudy + ", temail=" + temail + ", tphone=" + tphone + ", tremark=" + tremark
                + ", tstart=" + tstart + ", tctime=" + tctime + "]";
    }
    public Teacher() {
        super();
        // TODO Auto-generated constructor stub
    }

}
