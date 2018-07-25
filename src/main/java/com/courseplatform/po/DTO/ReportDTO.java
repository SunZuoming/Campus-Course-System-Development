package com.courseplatform.po.DTO;

import com.courseplatform.po.User;

public class ReportDTO {

	private String reportno;

    private String reportreason;

    private User reporter;

    private User reporteer;

    private String reportgoodsno;

    private String reportgoodstype;

    private String reporttime;

    private String reportfinishflag;

	public String getReportno() {
		return reportno;
	}

	public void setReportno(String reportno) {
		this.reportno = reportno;
	}

	public String getReportreason() {
		return reportreason;
	}

	public void setReportreason(String reportreason) {
		this.reportreason = reportreason;
	}

	public User getReporter() {
		return reporter;
	}

	public void setReporter(User reporter) {
		this.reporter = reporter;
	}

	public User getReporteer() {
		return reporteer;
	}

	public void setReporteer(User reporteer) {
		this.reporteer = reporteer;
	}

	public String getReportgoodsno() {
		return reportgoodsno;
	}

	public void setReportgoodsno(String reportgoodsno) {
		this.reportgoodsno = reportgoodsno;
	}

	public String getReportgoodstype() {
		return reportgoodstype;
	}

	public void setReportgoodstype(String reportgoodstype) {
		this.reportgoodstype = reportgoodstype;
	}

	public String getReporttime() {
		return reporttime;
	}

	public void setReporttime(String reporttime) {
		this.reporttime = reporttime;
	}

	public String getReportfinishflag() {
		return reportfinishflag;
	}

	public void setReportfinishflag(String reportfinishflag) {
		this.reportfinishflag = reportfinishflag;
	}

}
