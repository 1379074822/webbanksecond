package cn.edu.buct.entity;

public class User {
    private String uid;
    private String un;
    private String pw;
    private Integer tm;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUn() {
        return un;
    }

    public void setUn(String un) {
        this.un = un;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public Integer getTm() {
        return tm;
    }

    public void setTm(Integer tm) {
        this.tm = tm;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", un='" + un + '\'' +
                ", pw='" + pw + '\'' +
                ", tm=" + tm +
                '}';
    }
}


