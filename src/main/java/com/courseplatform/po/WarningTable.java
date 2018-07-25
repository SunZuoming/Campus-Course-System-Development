package com.courseplatform.po;

public class WarningTable {
    private String warningid;

    private String warningpublisher;

    private String warningpeople;

    private String warningreason;

    private String warningreasontype;

    private String warningreasonno;

    private String warningreadflag;

    private String warningreviseflag;

    private String warningfinshflag;

    private String warningstartdate;

    private String warningenddate;

    private String warningreaddate;

    private Integer warningnumber;

    public String getWarningid() {
        return warningid;
    }

    public void setWarningid(String warningid) {
        this.warningid = warningid == null ? null : warningid.trim();
    }

    public String getWarningpublisher() {
        return warningpublisher;
    }

    public void setWarningpublisher(String warningpublisher) {
        this.warningpublisher = warningpublisher == null ? null : warningpublisher.trim();
    }

    public String getWarningpeople() {
        return warningpeople;
    }

    public void setWarningpeople(String warningpeople) {
        this.warningpeople = warningpeople == null ? null : warningpeople.trim();
    }

    public String getWarningreason() {
        return warningreason;
    }

    public void setWarningreason(String warningreason) {
        this.warningreason = warningreason == null ? null : warningreason.trim();
    }

    public String getWarningreasontype() {
        return warningreasontype;
    }

    public void setWarningreasontype(String warningreasontype) {
        this.warningreasontype = warningreasontype == null ? null : warningreasontype.trim();
    }

    public String getWarningreasonno() {
        return warningreasonno;
    }

    public void setWarningreasonno(String warningreasonno) {
        this.warningreasonno = warningreasonno == null ? null : warningreasonno.trim();
    }

    public String getWarningreadflag() {
        return warningreadflag;
    }

    public void setWarningreadflag(String warningreadflag) {
        this.warningreadflag = warningreadflag == null ? null : warningreadflag.trim();
    }

    public String getWarningreviseflag() {
        return warningreviseflag;
    }

    public void setWarningreviseflag(String warningreviseflag) {
        this.warningreviseflag = warningreviseflag == null ? null : warningreviseflag.trim();
    }

    public String getWarningfinshflag() {
        return warningfinshflag;
    }

    public void setWarningfinshflag(String warningfinshflag) {
        this.warningfinshflag = warningfinshflag == null ? null : warningfinshflag.trim();
    }

    public String getWarningstartdate() {
        return warningstartdate;
    }

    public void setWarningstartdate(String warningstartdate) {
        this.warningstartdate = warningstartdate == null ? null : warningstartdate.trim();
    }

    public String getWarningenddate() {
        return warningenddate;
    }

    public void setWarningenddate(String warningenddate) {
        this.warningenddate = warningenddate == null ? null : warningenddate.trim();
    }

    public String getWarningreaddate() {
        return warningreaddate;
    }

    public void setWarningreaddate(String warningreaddate) {
        this.warningreaddate = warningreaddate == null ? null : warningreaddate.trim();
    }

    public Integer getWarningnumber() {
        return warningnumber;
    }

    public void setWarningnumber(Integer warningnumber) {
        this.warningnumber = warningnumber;
    }
}