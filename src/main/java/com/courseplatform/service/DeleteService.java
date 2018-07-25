package com.courseplatform.service;

/**
 * 用于删除表中记录
 * @author Administrator
 *
 */
public interface DeleteService {

	/**
	 * 删除文章记录，以及对这篇文章的评论
	 * @param articleNo
	 */
	public void deleteArticle(String articleNo);
	
	/**
	 * 删除某条评论
	 * @param commentNo
	 */
	public void deleteComment(String commentNo);
	
	/**
	 * 删除某个问题，以及对其的回答
	 * @param questionNo
	 */
	public void deleteQuestion(String questionNo);
	
	/**
	 * 删除某条回答记录
	 * @param answerNo
	 */
	public void deleteAnswer(String answerNo);
	
	/**
	 * 删除某个共享文件
	 * @param fileNo
	 */
	public void deleteSharedFile(String fileNo);
}
