package com.courseplatform.po.DTO;

import com.courseplatform.po.User;

public class NoticeDTO {

	private String noticeid;

    private User noticepublisher;

    private String notcecontent;

    private String noticestartdate;

    private String noticeenddate;

    private String noticepublisherdate;

    private String noticeendflag;
    
    //公告是否可修改标志
    private String noticeUpdateFlag;

	public String getNoticeid() {
		return noticeid;
	}

	public void setNoticeid(String noticeid) {
		this.noticeid = noticeid;
	}


	public String getNotcecontent() {
		return notcecontent;
	}

	public void setNotcecontent(String notcecontent) {
		this.notcecontent = notcecontent;
	}

	public String getNoticestartdate() {
		return noticestartdate;
	}

	public void setNoticestartdate(String noticestartdate) {
		this.noticestartdate = noticestartdate;
	}

	public String getNoticeenddate() {
		return noticeenddate;
	}

	public void setNoticeenddate(String noticeenddate) {
		this.noticeenddate = noticeenddate;
	}

	public String getNoticepublisherdate() {
		return noticepublisherdate;
	}

	public void setNoticepublisherdate(String noticepublisherdate) {
		this.noticepublisherdate = noticepublisherdate;
	}

	public String getNoticeendflag() {
		return noticeendflag;
	}

	public void setNoticeendflag(String noticeendflag) {
		this.noticeendflag = noticeendflag;
	}

	public String getNoticeUpdateFlag() {
		return noticeUpdateFlag;
	}

	public void setNoticeUpdateFlag(String noticeUpdateFlag) {
		this.noticeUpdateFlag = noticeUpdateFlag;
	}

	public User getNoticepublisher() {
		return noticepublisher;
	}

	public void setNoticepublisher(User noticepublisher) {
		this.noticepublisher = noticepublisher;
	}
}
