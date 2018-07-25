package com.courseplatform.dao;

import java.util.Map;

import com.courseplatform.po.CommentTable;

public interface CommentTableDao {

	/**
	 * 获取评论数目
	 * @return
	 */
	public long getCommnetNum();
	
	/**
	 * 获取某人在某日的评论数目
	 * @return
	 */
	public Integer getCommnetNumByNo(Map<String, String> param);
	
	/**
	 * 根据评论人获取评论信息
	 * @param userNo
	 * @return
	 */
	public CommentTable getComment(String commentNo);
	
	/**
	 * 根据文章编号删除对该文章的评论
	 * @param articleNo
	 */
	public void deleteCommentByArticleNo(String articleNo);
	
	/**
	 * 根据评论编号删除评论
	 * @param commentNo
	 */
	public void deleteCommentByCommentNo(String commentNo);
}
