package com.courseplatform.po.DTO;

import com.courseplatform.po.User;

public class QuestionDTO {

	private String questionno;

    private User questionpublisher;

    private String questiontime;

    private String questioncontent;

    private String questioncourse;

    private String questionresoveflag;

	public String getQuestionno() {
		return questionno;
	}

	public void setQuestionno(String questionno) {
		this.questionno = questionno;
	}

	public User getQuestionpublisher() {
		return questionpublisher;
	}

	public void setQuestionpublisher(User questionpublisher) {
		this.questionpublisher = questionpublisher;
	}

	public String getQuestiontime() {
		return questiontime;
	}

	public void setQuestiontime(String questiontime) {
		this.questiontime = questiontime;
	}

	public String getQuestioncontent() {
		return questioncontent;
	}

	public void setQuestioncontent(String questioncontent) {
		this.questioncontent = questioncontent;
	}

	public String getQuestioncourse() {
		return questioncourse;
	}

	public void setQuestioncourse(String questioncourse) {
		this.questioncourse = questioncourse;
	}

	public String getQuestionresoveflag() {
		return questionresoveflag;
	}

	public void setQuestionresoveflag(String questionresoveflag) {
		this.questionresoveflag = questionresoveflag;
	}

}
