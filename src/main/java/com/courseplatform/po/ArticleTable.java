package com.courseplatform.po;

public class ArticleTable {
    private String articleno;
    
    private String articlename;

    private String articlepublisherno;
    
    private String articlepublisher;

    private String articlefileurl;

    private String articlefilename;

    private String articlecontent;
    
    private String articlecabstract;

    private String articlepublishtime;

    private Integer articlecomnum;
    
    private String articletypes;

    private Integer articlereadnum;

    private Integer articlecollectnum;

    private Integer articlereportnum;

    public String getArticleno() {
        return articleno;
    }

    public void setArticleno(String articleno) {
        this.articleno = articleno == null ? null : articleno.trim();
    }

	public String getArticlecabstract() {
		return articlecabstract;
	}

	public void setArticlecabstract(String articlecabstract) {
		this.articlecabstract = articlecabstract;
	}

	public String getArticlepublisherno() {
		return articlepublisherno;
	}

	public void setArticlepublisherno(String articlepublisherno) {
		this.articlepublisherno = articlepublisherno;
	}

	public Integer getArticlecomnum() {
		return articlecomnum;
	}

	public void setArticlecomnum(Integer articlecomnum) {
		this.articlecomnum = articlecomnum;
	}

	public String getArticlename() {
        return articlename;
    }

    public void setArticlename(String articlename) {
        this.articlename = articlename == null ? null : articlename.trim();
    }

    public String getArticlepublisher() {
        return articlepublisher;
    }

    public void setArticlepublisher(String articlepublisher) {
        this.articlepublisher = articlepublisher == null ? null : articlepublisher.trim();
    }

    public String getArticlefileurl() {
        return articlefileurl;
    }

    public void setArticlefileurl(String articlefileurl) {
        this.articlefileurl = articlefileurl == null ? null : articlefileurl.trim();
    }

    public String getArticlefilename() {
        return articlefilename;
    }

    public void setArticlefilename(String articlefilename) {
        this.articlefilename = articlefilename == null ? null : articlefilename.trim();
    }

    public String getArticlecontent() {
        return articlecontent;
    }

    public void setArticlecontent(String articlecontent) {
        this.articlecontent = articlecontent == null ? null : articlecontent.trim();
    }

    public String getArticlepublishtime() {
        return articlepublishtime;
    }

    public void setArticlepublishtime(String articlepublishtime) {
        this.articlepublishtime = articlepublishtime == null ? null : articlepublishtime.trim();
    }

    public String getArticletypes() {
        return articletypes;
    }

    public void setArticletypes(String articletypes) {
        this.articletypes = articletypes == null ? null : articletypes.trim();
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