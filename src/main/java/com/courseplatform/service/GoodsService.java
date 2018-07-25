package com.courseplatform.service;

import com.courseplatform.po.DTO.AnswerDTO;
import com.courseplatform.po.DTO.ArticleDTO;
import com.courseplatform.po.DTO.CommentDTO;
import com.courseplatform.po.DTO.QuestionDTO;
import com.courseplatform.po.DTO.SharedFileDTO;

public interface GoodsService {
	
	/**
	 * 获取文章类DTO
	 * @param articleNo
	 * @return
	 */
	public ArticleDTO getArticleDTO(String articleNo);
	
	/**
	 * 获取评论类DTO
	 * @param commentNo
	 * @return
	 */
	public CommentDTO getCommentDTO(String commentNo);
	
	/**
	 * 获取共享文件类DTO
	 * @param sharedFileNo
	 * @return
	 */
	public SharedFileDTO getSharedFileDTO(String sharedFileNo);
	
	/**
	 * 获取问题类DTO
	 * @param questionNo
	 * @return
	 */
	public QuestionDTO getQuestionDTO(String questionNo);
	
	/**
	 * 获取回答类DTO
	 * @param answerNo
	 * @return
	 */
	public AnswerDTO getAnswerDTO(String answerNo);
}
