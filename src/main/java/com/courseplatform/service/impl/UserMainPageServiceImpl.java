package com.courseplatform.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.courseplatform.dao.UserMainPageDao;
import com.courseplatform.po.AnswerTable;
import com.courseplatform.po.ArticleTable;
import com.courseplatform.po.ArticletypeTable;
import com.courseplatform.po.CollectionTable;
import com.courseplatform.po.FunctionTable;
import com.courseplatform.po.InfoTable;
import com.courseplatform.po.JurisdictionTable;
import com.courseplatform.po.NoticeTable;
import com.courseplatform.po.QuestionTable;
import com.courseplatform.po.QuestiontypeTable;
import com.courseplatform.po.ReplayTable;
import com.courseplatform.po.UserTable;
import com.courseplatform.service.UserMainPageService;

@Service
@Transactional
public class UserMainPageServiceImpl implements  UserMainPageService{

	@Resource
	private UserMainPageDao userMainPageDao;

	public List<UserTable> queryRoleidByUserno(String userno) {
		return userMainPageDao.queryRoleidByUserno(userno);
	}

	public List<JurisdictionTable> queryJidByRoleid(String roleid) {
		return userMainPageDao.queryJidByRoleid(roleid);
	}

	public List<FunctionTable> queryFunByFno(String functionno) {
		return userMainPageDao.queryFunByFno(functionno);
	}

	public List<CollectionTable> queryCollArtsByUserno(String collector) {
		return userMainPageDao.queryCollArtsByUserno(collector);
	}

	public List<ArticleTable> queryArtMsgByAno(String articleno) {
		return userMainPageDao.queryArtMsgByAno(articleno);
	}

	public List<ArticleTable> queryArtMsgByUserno(String userno) {
		return userMainPageDao.queryArtMsgByUserno(userno);
	}
	
	public List<ArticletypeTable> queryArttypes() {
		return userMainPageDao.queryArttypes();
	}

	public void insertArticle(ArticleTable articletable) throws Exception {
		userMainPageDao.insertArticle(articletable);
	}

	public void deleteArticleColl(String collectionno, String collectiontype, String collector) throws Exception {
		userMainPageDao.deleteArticleColl(collectionno,collectiontype,collector);
	}

	public void deleteArticle(String articleno) throws Exception {
		userMainPageDao.deleteArticle(articleno);
		userMainPageDao.deleteArticleCollections(articleno);
	}

	public void updateArticleMsg(ArticleTable articletable) throws Exception {
		userMainPageDao.updateArticleMsg(articletable);
	}

	public List<QuestiontypeTable> quertQuestiontypes() {
		return userMainPageDao.quertQuestiontypes();
	}

	public void insertQuestion(QuestionTable questiontable) throws Exception {
		userMainPageDao.insertQuestion(questiontable);
	}

	public List<QuestionTable> queryNewQuestion() {
		return userMainPageDao.queryNewQuestion();
	}

	public List<QuestionTable> queryCommentNumQuestion() {
		return userMainPageDao.queryCommentNumQuestion();
	}

	public List<QuestionTable> queryMounthComnumQue(String questiontypeno) {
		return userMainPageDao.queryMounthComnumQue(questiontypeno);
	}

	public List<QuestionTable> queryYearComnumQue(String questiontypeno) {
		return userMainPageDao.queryYearComnumQue(questiontypeno);
	}

	public List<QuestionTable> queryQueByQtype(String questiontypeno,Integer start, Integer stop) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("questiontypeno", questiontypeno);
		hashMap.put("start", start);
		hashMap.put("stop", stop);
		return userMainPageDao.queryQueByQtype(hashMap);
	}

	public List<QuestionTable> queryComnumQueByQtype(String questiontypeno,Integer start, Integer stop) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("questiontypeno", questiontypeno);
		hashMap.put("start", start);
		hashMap.put("stop", stop);
		return userMainPageDao.queryComnumQueByQtype(hashMap);
	}

	public List<QuestionTable> quertQuestionByQno(String questionno) {
		return userMainPageDao.quertQuestionByQno(questionno);
	}

	public void insertAnswer(AnswerTable answertable) throws Exception {
		userMainPageDao.insertAnswer(answertable);
	}

	public List<AnswerTable> quertAnswersByQno(String questionno) {
		return userMainPageDao.quertAnswersByQno(questionno);
	}

	public List<String> quertArtByUserno(String userno) {
		return userMainPageDao.quertArtByUserno(userno);
	}

	public List<ArticletypeTable> queryArttypeByTypeno(String typeno) {
		return userMainPageDao.queryArttypeByTypeno(typeno);
	}

	public List<ArticleTable> queryArtclesByTypeno(String userno, String typeid) {
		return userMainPageDao.queryArtclesByTypeno(userno,typeid);
	}

	public void updateQuesnumAdd1(String questionno) throws Exception {
		userMainPageDao.updateQuesnumAdd1(questionno);
	}

	public void insertReplay(ReplayTable replaytable) throws Exception {
		userMainPageDao.insertReplay(replaytable);
	}

	public List<ReplayTable> queryReplayByAnswerno(String answerno) {
		return userMainPageDao.queryReplayByAnswerno(answerno);
	}

	public void insertInfo(InfoTable infotable) throws Exception {
		userMainPageDao.insertInfo(infotable);
	}

	public List<InfoTable> queryInfonumByUserno(String userno) {
		return userMainPageDao.queryInfonumByUserno(userno);
	}

	public List<AnswerTable> queryAnwserByAno(String answerno) {
		return userMainPageDao.queryAnwserByAno(answerno);
	}

	public List<ReplayTable> queryReplayByRno(String replayno) {
		return userMainPageDao.queryReplayByRno(replayno);
	}

	public void updateInfoReadsign(String[] infonos) throws Exception {
		if(infonos.length > 0){
			for(int i = 0,len = infonos.length; i < len; i++){
				userMainPageDao.updateInfoReadsign(infonos[i]);
			}
		}
	}

	public void deleteInfoByInfono(String[] infonos) throws Exception {
		if(infonos.length > 0){
			for(int i = 0,len = infonos.length; i < len; i++){
				userMainPageDao.deleteInfoByInfono(infonos[i]);
			}
		}
	}

	public List<InfoTable> queryInfoByUserno(String userno) {
		return userMainPageDao.queryInfoByUserno(userno);
	}

	public List<NoticeTable> queryNotices() {
		return userMainPageDao.queryNotices();
	}

	public List<ArticleTable> queryHostArctiles() {
		return userMainPageDao.queryHostArctiles();
	}

	public List<QuestionTable> queryAllQueByQname(String questionname, String questiontypeno) {
		return userMainPageDao.queryAllQueByQname(questionname,questiontypeno);
	}

	public List<QuestionTable> queryHostQueByQname(String questionname, String questiontypeno) {
		return userMainPageDao.queryHostQueByQname(questionname,questiontypeno);
	}

}
