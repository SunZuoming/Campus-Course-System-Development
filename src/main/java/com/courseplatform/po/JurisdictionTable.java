package com.courseplatform.po;

public class JurisdictionTable {
    private String jurisdictionno;

    private String roleid;

    private String functionno;

    public String getJurisdictionno() {
        return jurisdictionno;
    }

    public void setJurisdictionno(String jurisdictionno) {
        this.jurisdictionno = jurisdictionno == null ? null : jurisdictionno.trim();
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid == null ? null : roleid.trim();
    }

    public String getFunctionno() {
        return functionno;
    }

    public void setFunctionno(String functionno) {
        this.functionno = functionno == null ? null : functionno.trim();
    }
}