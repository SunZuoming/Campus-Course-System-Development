package com.courseplatform.po.DTO;

import com.courseplatform.po.ArticleTable;
import com.courseplatform.po.User;

public class CommentDTO {

	private String commentno;

    private ArticleTable article;

    private String commentcontent;

    private String commenttime;

    private User commenter;

	public String getCommentno() {
		return commentno;
	}

	public void setCommentno(String commentno) {
		this.commentno = commentno;
	}

	public String getCommentcontent() {
		return commentcontent;
	}

	public void setCommentcontent(String commentcontent) {
		this.commentcontent = commentcontent;
	}

	public String getCommenttime() {
		return commenttime;
	}

	public void setCommenttime(String commenttime) {
		this.commenttime = commenttime;
	}

	public User getCommenter() {
		return commenter;
	}

	public void setCommenter(User commenter) {
		this.commenter = commenter;
	}

	public ArticleTable getArticle() {
		return article;
	}

	public void setArticle(ArticleTable article) {
		this.article = article;
	}

	
    
}
