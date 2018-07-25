package com.courseplatform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courseplatform.dao.AnswerTableDao;
import com.courseplatform.dao.ArticleTableDao;
import com.courseplatform.dao.CommentTableDao;
import com.courseplatform.dao.QuestionTableDao;
import com.courseplatform.dao.SharedfileTableDao;
import com.courseplatform.dao.UserTableDao;
import com.courseplatform.po.AnswerTable;
import com.courseplatform.po.ArticleTable;
import com.courseplatform.po.CommentTable;
import com.courseplatform.po.QuestionTable;
import com.courseplatform.po.SharedfileTable;
import com.courseplatform.po.DTO.AnswerDTO;
import com.courseplatform.po.DTO.ArticleDTO;
import com.courseplatform.po.DTO.CommentDTO;
import com.courseplatform.po.DTO.QuestionDTO;
import com.courseplatform.po.DTO.SharedFileDTO;
import com.courseplatform.service.GoodsService;

import jxl.Sheet;

@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private ArticleTableDao articleTableDao;
	
	@Autowired
	private CommentTableDao commentTableDao;
	
	@Autowired
	private SharedfileTableDao sharedfileTableDao;
	
	@Autowired
	private QuestionTableDao questionTableDao;
	
	@Autowired
	private AnswerTableDao answerTableDao;
	
	@Autowired
	private UserTableDao userTableDao;
	
	public ArticleDTO getArticleDTO(String articleNo) {
		ArticleDTO articleDTO = new ArticleDTO();
		ArticleTable articleTable = articleTableDao.getArticle(articleNo);
		articleDTO.setArticlecollectnum(articleTable.getArticlecollectnum());
		articleDTO.setArticlecontent(articleTable.getArticlecontent());
		articleDTO.setArticlefilename(articleTable.getArticlefilename());
		articleDTO.setArticlefileurl(articleTable.getArticlefileurl());
		articleDTO.setArticlename(articleTable.getArticlename());
		articleDTO.setArticleno(articleTable.getArticleno());
		articleDTO.setArticlepublisher(userTableDao.getUserByUserNo(articleTable.getArticlepublisherno()));
		articleDTO.setArticlepublishtime(articleTable.getArticlepublishtime());
		articleDTO.setArticlereadnum(articleTable.getArticlereadnum());
		articleDTO.setArticlereportnum(articleTable.getArticlereportnum());
		articleDTO.setArticletypes(articleTable.getArticletypes());
		return articleDTO;
	}

	public CommentDTO getCommentDTO(String commentNo) {
		CommentDTO commentDTO = new CommentDTO();
		CommentTable commentTable = commentTableDao.getComment(commentNo);
		commentDTO.setCommentcontent(commentTable.getCommentcontent());
		commentDTO.setCommentno(commentTable.getCommentno());
		commentDTO.setCommenttime(commentTable.getCommenttime());
		commentDTO.setArticle(articleTableDao.getArticle(commentTable.getArticleno()));
		commentDTO.setCommenter(userTableDao.getUserByUserNo(commentTable.getCommenter()));
		return commentDTO;
	}

	public SharedFileDTO getSharedFileDTO(String sharedFileNo) {
		SharedFileDTO sharedFileDTO = new SharedFileDTO();
		SharedfileTable sharedfileTable = sharedfileTableDao.getSharedFile(sharedFileNo);
		sharedFileDTO.setSharedfilebadnum(sharedfileTable.getSharedfilebadnum());
		sharedFileDTO.setSharedfilecollectnum(sharedfileTable.getSharedfilecollectnum());
		sharedFileDTO.setSharedfiledownloadnum(sharedfileTable.getSharedfiledownloadnum());
		sharedFileDTO.setSharedfilegoodrate(sharedfileTable.getSharedfilegoodrate());
		sharedFileDTO.setSharedfilegoognum(sharedfileTable.getSharedfilegoognum());
		sharedFileDTO.setSharedfilename(sharedfileTable.getSharedfilename());
		sharedFileDTO.setSharedfileno(sharedfileTable.getSharedfileno());
		sharedFileDTO.setSharedfilepassflag(sharedfileTable.getSharedfilepassflag());
		sharedFileDTO.setSharedfilereportnum(sharedfileTable.getSharedfilereportnum());
		sharedFileDTO.setSharedfiletypes(sharedfileTable.getSharedfiletypes());
		sharedFileDTO.setSharedfileurl(sharedfileTable.getSharedfileurl());
		sharedFileDTO.setUploader(userTableDao.getUserByUserNo(sharedfileTable.getUploader()));
		sharedFileDTO.setUploadtime(sharedfileTable.getUploadtime());
		return sharedFileDTO;
	}

	public QuestionDTO getQuestionDTO(String questionNo) {
		QuestionDTO questionDTO = new QuestionDTO();
		QuestionTable questionTable = questionTableDao.getQuestion(questionNo);
		questionDTO.setQuestioncontent(questionTable.getQuestioncontent());
		questionDTO.setQuestioncourse(questionTable.getQuestioncourse());
		questionDTO.setQuestionno(questionTable.getQuestionno());
		questionDTO.setQuestionpublisher(userTableDao.getUserByUserNo(questionTable.getQuestionpublisher()));
		questionDTO.setQuestionresoveflag(questionTable.getQuestionresoveflag());
		questionDTO.setQuestiontime(questionTable.getQuestiontime());
		return questionDTO;
	}

	public AnswerDTO getAnswerDTO(String answerNo) {
		AnswerDTO answerDTO = new AnswerDTO();
		AnswerTable answerTable = answerTableDao.getAnswer(answerNo);
		answerDTO.setAnswercontent(answerTable.getAnswercontent());
		answerDTO.setAnswerfilename(answerTable.getAnswerfilename());
		answerDTO.setAnswerfileurl(answerTable.getAnswerfileurl());
		answerDTO.setAnswerno(answerTable.getAnswerno());
		answerDTO.setAnswerrightflag(answerTable.getAnswerrightflag());
		answerDTO.setAnswertime(answerTable.getAnswertime());
		answerDTO.setQuestion(questionTableDao.getQuestion(answerTable.getQuestionno()));
		answerDTO.setAnswerner(userTableDao.getUserByUserNo(answerTable.getAnswerner()));
		return answerDTO;
	}

}
