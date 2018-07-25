package com.courseplatform.po;

public class UserTable {
    private String userno;

    private String roleid;

    private String username;

    private String userpassword;

    private String loginfirstflag;

    private Float usersystemscore;

    private String userphone;

    private Integer warningnum;

    private String userstate;

    private Integer loginfailnumber;

    private Integer prohibitloginnum;

    private Integer warningafterprohibitnum;

    private String email;

    private String activestate;

    private String usercode;

    public String getUserno() {
        return userno;
    }

    public void setUserno(String userno) {
        this.userno = userno == null ? null : userno.trim();
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid == null ? null : roleid.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword == null ? null : userpassword.trim();
    }

    public String getLoginfirstflag() {
        return loginfirstflag;
    }

    public void setLoginfirstflag(String loginfirstflag) {
        this.loginfirstflag = loginfirstflag == null ? null : loginfirstflag.trim();
    }

    public Float getUsersystemscore() {
        return usersystemscore;
    }

    public void setUsersystemscore(Float usersystemscore) {
        this.usersystemscore = usersystemscore;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone == null ? null : userphone.trim();
    }

    public Integer getWarningnum() {
        return warningnum;
    }

    public void setWarningnum(Integer warningnum) {
        this.warningnum = warningnum;
    }

    public String getUserstate() {
        return userstate;
    }

    public void setUserstate(String userstate) {
        this.userstate = userstate == null ? null : userstate.trim();
    }

    public Integer getLoginfailnumber() {
        return loginfailnumber;
    }

    public void setLoginfailnumber(Integer loginfailnumber) {
        this.loginfailnumber = loginfailnumber;
    }

    public Integer getProhibitloginnum() {
        return prohibitloginnum;
    }

    public void setProhibitloginnum(Integer prohibitloginnum) {
        this.prohibitloginnum = prohibitloginnum;
    }

    public Integer getWarningafterprohibitnum() {
        return warningafterprohibitnum;
    }

    public void setWarningafterprohibitnum(Integer warningafterprohibitnum) {
        this.warningafterprohibitnum = warningafterprohibitnum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getActivestate() {
        return activestate;
    }

    public void setActivestate(String activestate) {
        this.activestate = activestate == null ? null : activestate.trim();
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode == null ? null : usercode.trim();
    }
}