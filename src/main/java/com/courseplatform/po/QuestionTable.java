package com.courseplatform.po;

public class QuestionTable {
    private String questionno;

    private String questionpublisherno;
    
    private String questionpublisher;

    private String questiontime;

    private String questioncontent;

    private String questioncourse;

    private String questionresoveflag;
    
    private String questionname;
    
    private String questiontypeno;
    
    private String questiontypename;

    private Integer questioncommentnum;

    public String getQuestionno() {
        return questionno;
    }

    public String getQuestionpublisherno() {
		return questionpublisherno;
	}

	public void setQuestionpublisherno(String questionpublisherno) {
		this.questionpublisherno = questionpublisherno;
	}

	public String getQuestionname() {
		return questionname;
	}

	public void setQuestionname(String questionname) {
		this.questionname = questionname;
	}

	public String getQuestiontypeno() {
		return questiontypeno;
	}

	public void setQuestiontypeno(String questiontypeno) {
		this.questiontypeno = questiontypeno;
	}

	public String getQuestiontypename() {
		return questiontypename;
	}

	public void setQuestiontypename(String questiontypename) {
		this.questiontypename = questiontypename;
	}

	public Integer getQuestioncommentnum() {
		return questioncommentnum;
	}

	public void setQuestioncommentnum(Integer questioncommentnum) {
		this.questioncommentnum = questioncommentnum;
	}

	public void setQuestionno(String questionno) {
        this.questionno = questionno == null ? null : questionno.trim();
    }

    public String getQuestionpublisher() {
        return questionpublisher;
    }

    public void setQuestionpublisher(String questionpublisher) {
        this.questionpublisher = questionpublisher == null ? null : questionpublisher.trim();
    }

    public String getQuestiontime() {
        return questiontime;
    }

    public void setQuestiontime(String questiontime) {
        this.questiontime = questiontime == null ? null : questiontime.trim();
    }

    public String getQuestioncontent() {
        return questioncontent;
    }

    public void setQuestioncontent(String questioncontent) {
        this.questioncontent = questioncontent == null ? null : questioncontent.trim();
    }

    public String getQuestioncourse() {
        return questioncourse;
    }

    public void setQuestioncourse(String questioncourse) {
        this.questioncourse = questioncourse == null ? null : questioncourse.trim();
    }

    public String getQuestionresoveflag() {
        return questionresoveflag;
    }

    public void setQuestionresoveflag(String questionresoveflag) {
        this.questionresoveflag = questionresoveflag == null ? null : questionresoveflag.trim();
    }
}