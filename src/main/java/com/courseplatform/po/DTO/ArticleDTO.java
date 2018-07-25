package com.courseplatform.po.DTO;

import com.courseplatform.po.User;

public class ArticleDTO {

	private String articleno;

    private String articlename;

    private User articlepublisher;

    private String articlefileurl;

    private String articlefilename;

    private String articlecontent;

    private String articlepublishtime;

    private String articletypes;

    private Integer articlereadnum;


    private Integer articlecollectnum;

    private Integer articlereportnum;


	public String getArticleno() {
		return articleno;
	}

	public void setArticleno(String articleno) {
		this.articleno = articleno;
	}

	public String getArticlename() {
		return articlename;
	}

	public void setArticlename(String articlename) {
		this.articlename = articlename;
	}

	public User getArticlepublisher() {
		return articlepublisher;
	}

	public void setArticlepublisher(User articlepublisher) {
		this.articlepublisher = articlepublisher;
	}

	public String getArticlefileurl() {
		return articlefileurl;
	}

	public void setArticlefileurl(String articlefileurl) {
		this.articlefileurl = articlefileurl;
	}

	public String getArticlefilename() {
		return articlefilename;
	}

	public void setArticlefilename(String articlefilename) {
		this.articlefilename = articlefilename;
	}

	public String getArticlecontent() {
		return articlecontent;
	}

	public void setArticlecontent(String articlecontent) {
		this.articlecontent = articlecontent;
	}

	public String getArticlepublishtime() {
		return articlepublishtime;
	}

	public void setArticlepublishtime(String articlepublishtime) {
		this.articlepublishtime = articlepublishtime;
	}

	public String getArticletypes() {
		return articletypes;
	}

	public void setArticletypes(String articletypes) {
		this.articletypes = articletypes;
	}

	public Integer getArticlereadnum() {
		return articlereadnum;
	}

	public void setArticlereadnum(Integer articlereadnum) {
		this.articlereadnum = articlereadnum;
	}


	public Integer getArticlecollectnum() {
		return articlecollectnum;
	}

	public void setArticlecollectnum(Integer articlecollectnum) {
		this.articlecollectnum = articlecollectnum;
	}

	public Integer getArticlereportnum() {
		return articlereportnum;
	}

	public void setArticlereportnum(Integer articlereportnum) {
		this.articlereportnum = articlereportnum;
	}

}
