package com.courseplatform.po;

public class NoticeTable {
    private String noticeid;

    private String noticepublisher;

    private String notcecontent;

    private String noticestartdate;

    private String noticeenddate;

    private String noticepublisherdate;

    private String noticeendflag;

    public String getNoticeid() {
        return noticeid;
    }

    public void setNoticeid(String noticeid) {
        this.noticeid = noticeid == null ? null : noticeid.trim();
    }

    public String getNoticepublisher() {
        return noticepublisher;
    }

    public void setNoticepublisher(String noticepublisher) {
        this.noticepublisher = noticepublisher == null ? null : noticepublisher.trim();
    }

    public String getNotcecontent() {
        return notcecontent;
    }

    public void setNotcecontent(String notcecontent) {
        this.notcecontent = notcecontent == null ? null : notcecontent.trim();
    }

    public String getNoticestartdate() {
        return noticestartdate;
    }

    public void setNoticestartdate(String noticestartdate) {
        this.noticestartdate = noticestartdate == null ? null : noticestartdate.trim();
    }

    public String getNoticeenddate() {
        return noticeenddate;
    }

    public void setNoticeenddate(String noticeenddate) {
        this.noticeenddate = noticeenddate == null ? null : noticeenddate.trim();
    }

    public String getNoticepublisherdate() {
        return noticepublisherdate;
    }

    public void setNoticepublisherdate(String noticepublisherdate) {
        this.noticepublisherdate = noticepublisherdate == null ? null : noticepublisherdate.trim();
    }

    public String getNoticeendflag() {
        return noticeendflag;
    }

    public void setNoticeendflag(String noticeendflag) {
        this.noticeendflag = noticeendflag == null ? null : noticeendflag.trim();
    }
}