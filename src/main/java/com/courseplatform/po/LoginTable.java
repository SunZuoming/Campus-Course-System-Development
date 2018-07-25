package com.courseplatform.po;

public class LoginTable {
    private String loginid;

    private String loginuserno;

    private String loginip;

    private String loginresult;

    private String logintime;

    private String loginouttime;

    private String loginouttype;

    private String logintype;

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid == null ? null : loginid.trim();
    }

    public String getLoginuserno() {
        return loginuserno;
    }

    public void setLoginuserno(String loginuserno) {
        this.loginuserno = loginuserno == null ? null : loginuserno.trim();
    }

    public String getLoginip() {
        return loginip;
    }

    public void setLoginip(String loginip) {
        this.loginip = loginip == null ? null : loginip.trim();
    }

    public String getLoginresult() {
        return loginresult;
    }

    public void setLoginresult(String loginresult) {
        this.loginresult = loginresult == null ? null : loginresult.trim();
    }

    public String getLogintime() {
        return logintime;
    }

    public void setLogintime(String logintime) {
        this.logintime = logintime == null ? null : logintime.trim();
    }

    public String getLoginouttime() {
        return loginouttime;
    }

    public void setLoginouttime(String loginouttime) {
        this.loginouttime = loginouttime == null ? null : loginouttime.trim();
    }

    public String getLoginouttype() {
        return loginouttype;
    }

    public void setLoginouttype(String loginouttype) {
        this.loginouttype = loginouttype == null ? null : loginouttype.trim();
    }

    public String getLogintype() {
        return logintype;
    }

    public void setLogintype(String logintype) {
        this.logintype = logintype == null ? null : logintype.trim();
    }
}