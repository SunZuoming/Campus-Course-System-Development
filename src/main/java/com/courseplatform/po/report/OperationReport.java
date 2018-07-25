package com.courseplatform.po.report;

import java.io.Serializable;

@SuppressWarnings("serial")
public class OperationReport implements Serializable {

	// 用户编号
	private String userNo;
	// 用户姓名
	private String userName;
	// 用户身份名称
	private String roleName;
	// 用户操作统计日期
	private String date;
	//用户的总操作数
	private Integer operation;
	// 用户发布的文章数目
	private Integer article;
	// 用户的评论数目
	private Integer comment;
	// 用户提出问题的数目
	private Integer question;
	// 用户回答问题的数目
	private Integer answer;
	// 用户共享文件的数目
	private Integer sharedFile;
	// 用户收藏的数目
	private Integer collection;
	//
	private Integer homeWork;
	
	private Integer homeWorkCommit;

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getOperation() {
		return operation;
	}

	public void setOperation(Integer operation) {
		this.operation = operation;
	}

	public Integer getArticle() {
		return article;
	}

	public void setArticle(Integer article) {
		this.article = article;
	}

	public Integer getComment() {
		return comment;
	}

	public void setComment(Integer comment) {
		this.comment = comment;
	}

	public Integer getQuestion() {
		return question;
	}

	public void setQuestion(Integer question) {
		this.question = question;
	}

	public Integer getAnswer() {
		return answer;
	}

	public void setAnswer(Integer answer) {
		this.answer = answer;
	}

	public Integer getSharedFile() {
		return sharedFile;
	}

	public void setSharedFile(Integer sharedFile) {
		this.sharedFile = sharedFile;
	}

	public Integer getCollection() {
		return collection;
	}

	public void setCollection(Integer collection) {
		this.collection = collection;
	}

	public Integer getHomeWork() {
		return homeWork;
	}

	public void setHomeWork(Integer homeWork) {
		this.homeWork = homeWork;
	}

	public Integer getHomeWorkCommit() {
		return homeWorkCommit;
	}

	public void setHomeWorkCommit(Integer homeWorkCommit) {
		this.homeWorkCommit = homeWorkCommit;
	}

	
}
