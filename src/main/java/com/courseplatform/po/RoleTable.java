package com.courseplatform.po;

public class RoleTable {
    private String roleid;

    private String rolename;

    private String roleuseflag;

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid == null ? null : roleid.trim();
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    public String getRoleuseflag() {
        return roleuseflag;
    }

    public void setRoleuseflag(String roleuseflag) {
        this.roleuseflag = roleuseflag == null ? null : roleuseflag.trim();
    }
}