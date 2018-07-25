package com.courseplatform.po;

public class AnswerTable {
    private String answerno;

    private String questionno;

    private String answernerno;

    private String answerner;

    private String answertime;

    private String answercontent;

    private String answerfileurl;

    private String answerfilename;

    private String answerrightflag;

    public String getAnswerno() {
        return answerno;
    }

    public void setAnswerno(String answerno) {
        this.answerno = answerno == null ? null : answerno.trim();
    }

    public String getQuestionno() {
        return questionno;
    }

    public String getAnswernerno() {
		return answernerno;
	}

	public void setAnswernerno(String answernerno) {
		this.answernerno = answernerno;
	}

	public void setQuestionno(String questionno) {
        this.questionno = questionno == null ? null : questionno.trim();
    }

    public String getAnswerner() {
        return answerner;
    }

    public void setAnswerner(String answerner) {
        this.answerner = answerner == null ? null : answerner.trim();
    }

    public String getAnswertime() {
        return answertime;
    }

    public void setAnswertime(String answertime) {
        this.answertime = answertime == null ? null : answertime.trim();
    }

    public String getAnswercontent() {
        return answercontent;
    }

    public void setAnswercontent(String answercontent) {
        this.answercontent = answercontent == null ? null : answercontent.trim();
    }

    public String getAnswerfileurl() {
        return answerfileurl;
    }

    public void setAnswerfileurl(String answerfileurl) {
        this.answerfileurl = answerfileurl == null ? null : answerfileurl.trim();
    }

    public String getAnswerfilename() {
        return answerfilename;
    }

    public void setAnswerfilename(String answerfilename) {
        this.answerfilename = answerfilename == null ? null : answerfilename.trim();
    }

    public String getAnswerrightflag() {
        return answerrightflag;
    }

    public void setAnswerrightflag(String answerrightflag) {
        this.answerrightflag = answerrightflag == null ? null : answerrightflag.trim();
    }
}