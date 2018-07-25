package com.courseplatform.dao;

import java.util.Map;

import com.courseplatform.po.AnswerTable;

public interface AnswerTableDao {

	/**
	 * 获取答案总数目
	 * @return
	 */
	public long getAnswerNum();
	
	/**
	 * 获取某用户在某日的回答数目
	 * @return
	 */
	public Integer getAnswerNumByNo(Map<String, String> param);
	
	/**
	 * 根据回答编号获取回答信息
	 * @param answerNo
	 * @return
	 */
	public AnswerTable getAnswer(String answerNo);
	
	/**
	 * 根据问题编号删除对该问题的回答
	 * @param questionNo
	 */
	public void deleteAnswerByQuestionNo(String questionNo);
	
	/**
	 * 根据回答编号删除回答信息
	 * @param answerNo
	 */
	public void deleteAnswerByAnswerNo(String answerNo);
}
