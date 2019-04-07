package cn.edu.buct.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;


public class Log {


    private  Integer lid;
    private  String  snum;
    private  String  uid;
    private  String  fctp;
    private  String  rs;
    private Timestamp lc;
    private  String  sta;

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public String getSnum() {
        return snum;
    }

    public void setSnum(String snum) {
        this.snum = snum;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;

    }

    public Log() {
    }

    public Log(Integer lid, String snum, String uid, String fctp, String rs, Timestamp lc, String sta) {
        this.lid = lid;
        this.snum = snum;
        this.uid = uid;
        this.fctp = fctp;
        this.rs = rs;
        this.lc = lc;
        this.sta = sta;
    }

    public String getFctp() {
        return fctp;
    }

    public void setFctp(String fctp) {
        this.fctp = fctp;
    }

    public String getRs() {
        return rs;
    }

    public void setRs(String rs) {
        this.rs = rs;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,  timezone="GMT+8")
    public Timestamp getLc() {
        return lc;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,  timezone="GMT+8")
    public void setLc(Timestamp lc) {
        this.lc = lc;
    }

    public String getSta() {
        return sta;
    }

    public void setSta(String sta) {
        this.sta = sta;
    }
    @Override
    public String toString() {
        return "Log{" +
                "lid='" + lid + '\'' +
                ", snum='" + snum + '\'' +
                ", uid='" + uid + '\'' +
                ", fctp='" + fctp + '\'' +
                ",rs='" + rs + '\'' +
                ",lc='" + lc + '\'' +
                ",sta='" + sta +'\''+
                '}';
    }
}
