package com.courseplatform.po.DTO;

import com.courseplatform.po.QuestionTable;
import com.courseplatform.po.User;

public class AnswerDTO {

	private String answerno;

    private QuestionTable question;

    private User answerner;

    private String answertime;

    private String answercontent;

    private String answerfileurl;

    private String answerfilename;

    private String answerrightflag;

	public String getAnswerno() {
		return answerno;
	}

	public void setAnswerno(String answerno) {
		this.answerno = answerno;
	}

	public QuestionTable getQuestion() {
		return question;
	}

	public void setQuestion(QuestionTable question) {
		this.question = question;
	}

	public User getAnswerner() {
		return answerner;
	}

	public void setAnswerner(User answerner) {
		this.answerner = answerner;
	}

	public String getAnswertime() {
		return answertime;
	}

	public void setAnswertime(String answertime) {
		this.answertime = answertime;
	}

	public String getAnswercontent() {
		return answercontent;
	}

	public void setAnswercontent(String answercontent) {
		this.answercontent = answercontent;
	}

	public String getAnswerfileurl() {
		return answerfileurl;
	}

	public void setAnswerfileurl(String answerfileurl) {
		this.answerfileurl = answerfileurl;
	}

	public String getAnswerfilename() {
		return answerfilename;
	}

	public void setAnswerfilename(String answerfilename) {
		this.answerfilename = answerfilename;
	}

	public String getAnswerrightflag() {
		return answerrightflag;
	}

	public void setAnswerrightflag(String answerrightflag) {
		this.answerrightflag = answerrightflag;
	}
    
    
}
