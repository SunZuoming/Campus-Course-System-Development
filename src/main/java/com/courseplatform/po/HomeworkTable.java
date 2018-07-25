package com.courseplatform.po;

public class HomeworkTable {
    private String homeworkno;

    private String homeworkpublisher;

    private String homeworkcourse;

    private String homeworkpublishtime;

    private String homeworkcommittime;

    private String homeworkcommittype;

    private String homeworkcontent;
    
    private String username;

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHomeworkno() {
        return homeworkno;
    }

    public void setHomeworkno(String homeworkno) {
        this.homeworkno = homeworkno == null ? null : homeworkno.trim();
    }

    public String getHomeworkpublisher() {
        return homeworkpublisher;
    }

    public void setHomeworkpublisher(String homeworkpublisher) {
        this.homeworkpublisher = homeworkpublisher == null ? null : homeworkpublisher.trim();
    }

    public String getHomeworkcourse() {
        return homeworkcourse;
    }

    public void setHomeworkcourse(String homeworkcourse) {
        this.homeworkcourse = homeworkcourse == null ? null : homeworkcourse.trim();
    }

    public String getHomeworkpublishtime() {
        return homeworkpublishtime;
    }

    public void setHomeworkpublishtime(String homeworkpublishtime) {
        this.homeworkpublishtime = homeworkpublishtime == null ? null : homeworkpublishtime.trim();
    }

    public String getHomeworkcommittime() {
        return homeworkcommittime;
    }

    public void setHomeworkcommittime(String homeworkcommittime) {
        this.homeworkcommittime = homeworkcommittime == null ? null : homeworkcommittime.trim();
    }

    public String getHomeworkcommittype() {
        return homeworkcommittype;
    }

    public void setHomeworkcommittype(String homeworkcommittype) {
        this.homeworkcommittype = homeworkcommittype == null ? null : homeworkcommittype.trim();
    }

    public String getHomeworkcontent() {
        return homeworkcontent;
    }

    public void setHomeworkcontent(String homeworkcontent) {
        this.homeworkcontent = homeworkcontent == null ? null : homeworkcontent.trim();
    }
}