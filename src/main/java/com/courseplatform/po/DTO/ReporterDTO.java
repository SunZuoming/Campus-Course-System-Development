package com.courseplatform.po.DTO;

import com.courseplatform.po.User;

public class ReporterDTO {

	//举报人、被举报人
	private User reporter;
	//被举报次数
	private Integer reporterNum;

	public User getReporter() {
		return reporter;
	}

	public void setReporter(User reporter) {
		this.reporter = reporter;
	}

	public Integer getReporterNum() {
		return reporterNum;
	}

	public void setReporterNum(Integer reporterNum) {
		this.reporterNum = reporterNum;
	}
	
	
}
