package com.courseplatform.dao;

import java.util.Map;

import com.courseplatform.po.QuestionTable;

public interface QuestionTableDao {

	/**
	 * 获取问题总数目
	 * @return
	 */
	public long getQuestionNum();
	

	/**
	 * 获取某人在某日的问题总数目
	 * @return
	 */
	public Integer getQuestionNumByNo(Map<String, String> param);
	
	/**
	 * 根据问题编号获取问题信息
	 * @param questionNo
	 * @return
	 */
	public QuestionTable getQuestion(String questionNo);
	
	/**
	 * 删除某条问题记录
	 * @param questionNo
	 */
	public void deleteQuestion(String questionNo);
}
