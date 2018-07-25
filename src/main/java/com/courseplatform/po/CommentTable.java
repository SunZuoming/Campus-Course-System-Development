package com.courseplatform.po;

public class CommentTable {
    private String commentno;

    private String articleno;

    private String commentcontent;

    private String commenttime;

    private String commenter;
    
    private String commenterno;

    public String getCommentno() {
        return commentno;
    }

    public void setCommentno(String commentno) {
        this.commentno = commentno == null ? null : commentno.trim();
    }

    public String getCommenterno() {
		return commenterno;
	}

	public void setCommenterno(String commenterno) {
		this.commenterno = commenterno;
	}

	public String getArticleno() {
        return articleno;
    }

    public void setArticleno(String articleno) {
        this.articleno = articleno == null ? null : articleno.trim();
    }

    public String getCommentcontent() {
        return commentcontent;
    }

    public void setCommentcontent(String commentcontent) {
        this.commentcontent = commentcontent == null ? null : commentcontent.trim();
    }

    public String getCommenttime() {
        return commenttime;
    }

    public void setCommenttime(String commenttime) {
        this.commenttime = commenttime == null ? null : commenttime.trim();
    }

    public String getCommenter() {
        return commenter;
    }

    public void setCommenter(String commenter) {
        this.commenter = commenter == null ? null : commenter.trim();
    }
}