package com.courseplatform.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.courseplatform.dao.AnswerTableDao;
import com.courseplatform.dao.ArticleTableDao;
import com.courseplatform.dao.CommentTableDao;
import com.courseplatform.dao.QuestionTableDao;
import com.courseplatform.dao.SharedfileTableDao;
import com.courseplatform.service.DeleteService;
import com.courseplatform.util.StringUtil;

@Service
public class DeleteServiceImpl implements DeleteService {
	
	@Autowired
	private ArticleTableDao articleTableDao;
	
	@Autowired
	private CommentTableDao commentTableDao;
	
	@Autowired
	private QuestionTableDao questionTableDao;
	
	@Autowired
	private AnswerTableDao answerTableDao;
	
	@Autowired
	private SharedfileTableDao sharedfileTableDao;

	@Transactional
	public void deleteArticle(String articleNo) {
		commentTableDao.deleteCommentByArticleNo(articleNo);
		articleTableDao.deleteArticle(articleNo);
	}

	@Transactional
	public void deleteComment(String commentNo) {
		commentTableDao.deleteCommentByCommentNo(commentNo);
	}

	@Transactional
	public void deleteQuestion(String questionNo) {
		answerTableDao.deleteAnswerByQuestionNo(questionNo);
		questionTableDao.deleteQuestion(questionNo);
	}

	@Transactional
	public void deleteAnswer(String answerNo) {
		answerTableDao.deleteAnswerByAnswerNo(answerNo);
	}

	public void deleteSharedFile(String sharedFileNo) {
		String url = sharedfileTableDao.getSharedFile(sharedFileNo).getSharedfileurl();
		File folder = new File(url);
		if(folder.exists()) {
			StringUtil.deleteFile(folder);
			sharedfileTableDao.deleteSharedFile(sharedFileNo);
		}else {
			sharedfileTableDao.deleteSharedFile(sharedFileNo);
		}
	}

}
