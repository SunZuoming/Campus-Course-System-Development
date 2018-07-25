package com.courseplatform.dao;

import java.util.Map;

import com.courseplatform.po.ArticleTable;

public interface ArticleTableDao {

	/**
	 * 获取文章总数目
	 * @return
	 */
	public long getArticleNum();
	
	/**
	 * 根据文章编号获取文章信息
	 * @param articleNo
	 * @return
	 */
	public ArticleTable getArticle(String articleNo);
	
	/**
	 * 根据文章编号删除文章记录
	 * @param articleNo
	 */
	public void deleteArticle(String articleNo);
	
	/**
	 * 获取某用户在某日的发表的文章数
	 * @param param
	 * @return
	 */
	public Integer getArticleNumByNo(Map<String, String> param);
}
