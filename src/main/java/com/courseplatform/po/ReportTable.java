package com.courseplatform.po;

public class ReportTable {
    private String reportno;

    private String reportreason;

    private String reporter;

    private String reporteer;

    private String reportgoodsno;

    private String reportgoodstype;

    private String reporttime;

    private String reportfinishflag;

    public String getReportno() {
        return reportno;
    }

    public void setReportno(String reportno) {
        this.reportno = reportno == null ? null : reportno.trim();
    }

    public String getReportreason() {
        return reportreason;
    }

    public void setReportreason(String reportreason) {
        this.reportreason = reportreason == null ? null : reportreason.trim();
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter == null ? null : reporter.trim();
    }

    public String getReporteer() {
        return reporteer;
    }

    public void setReporteer(String reporteer) {
        this.reporteer = reporteer == null ? null : reporteer.trim();
    }

    public String getReportgoodsno() {
        return reportgoodsno;
    }

    public void setReportgoodsno(String reportgoodsno) {
        this.reportgoodsno = reportgoodsno == null ? null : reportgoodsno.trim();
    }

    public String getReportgoodstype() {
        return reportgoodstype;
    }

    public void setReportgoodstype(String reportgoodstype) {
        this.reportgoodstype = reportgoodstype == null ? null : reportgoodstype.trim();
    }

    public String getReporttime() {
        return reporttime;
    }

    public void setReporttime(String reporttime) {
        this.reporttime = reporttime == null ? null : reporttime.trim();
    }

    public String getReportfinishflag() {
        return reportfinishflag;
    }

    public void setReportfinishflag(String reportfinishflag) {
        this.reportfinishflag = reportfinishflag == null ? null : reportfinishflag.trim();
    }
}