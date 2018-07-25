package com.courseplatform.po;

public class HomeworkcommitTable {
    private String homeworkcommitno;

    private String homeworkno;

    private String homeworkcommiter;

    private String commithomeworktime;

    private String homeworkfileurl;

    private Float homeworkscorec;

    private String homereviseflag;

    private String homeworktxtcontent;
    
    private String homeworkpublisher ;
    
    private String username;
    
    private String homeworkcourse;
    
    private String homepg;
    
    
    public String getHomepg() {
		return homepg;
	}

	public void setHomepg(String homepg) {
		this.homepg = homepg;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHomeworkcourse() {
		return homeworkcourse;
	}

	public void setHomeworkcourse(String homeworkcourse) {
		this.homeworkcourse = homeworkcourse;
	}

	public String getHomeworkpublisher() {
		return homeworkpublisher;
	}

	public void setHomeworkpublisher(String homeworkpublisher) {
		this.homeworkpublisher = homeworkpublisher;
	}

	public String getHomeworkcommitno() {
        return homeworkcommitno;
    }

    public void setHomeworkcommitno(String homeworkcommitno) {
        this.homeworkcommitno = homeworkcommitno == null ? null : homeworkcommitno.trim();
    }

    public String getHomeworkno() {
        return homeworkno;
    }

    public void setHomeworkno(String homeworkno) {
        this.homeworkno = homeworkno == null ? null : homeworkno.trim();
    }

    public String getHomeworkcommiter() {
        return homeworkcommiter;
    }

    public void setHomeworkcommiter(String homeworkcommiter) {
        this.homeworkcommiter = homeworkcommiter == null ? null : homeworkcommiter.trim();
    }

    public String getCommithomeworktime() {
        return commithomeworktime;
    }

    public void setCommithomeworktime(String commithomeworktime) {
        this.commithomeworktime = commithomeworktime == null ? null : commithomeworktime.trim();
    }

    public String getHomeworkfileurl() {
        return homeworkfileurl;
    }

    public void setHomeworkfileurl(String homeworkfileurl) {
        this.homeworkfileurl = homeworkfileurl == null ? null : homeworkfileurl.trim();
    }

    public Float getHomeworkscorec() {
        return homeworkscorec;
    }

    public void setHomeworkscorec(Float homeworkscorec) {
        this.homeworkscorec = homeworkscorec;
    }

    public String getHomereviseflag() {
        return homereviseflag;
    }

    public void setHomereviseflag(String homereviseflag) {
        this.homereviseflag = homereviseflag == null ? null : homereviseflag.trim();
    }

    public String getHomeworktxtcontent() {
        return homeworktxtcontent;
    }

    public void setHomeworktxtcontent(String homeworktxtcontent) {
        this.homeworktxtcontent = homeworktxtcontent == null ? null : homeworktxtcontent.trim();
    }
}