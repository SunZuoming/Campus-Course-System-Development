package com.courseplatform.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.courseplatform.dao.UserViewMainDao;
import com.courseplatform.po.ArticleTable;
import com.courseplatform.po.ArticletypeTable;
import com.courseplatform.po.CollectionTable;
import com.courseplatform.po.CommentTable;
import com.courseplatform.po.NoticeTable;
import com.courseplatform.po.ReportTable;
import com.courseplatform.service.UserViewMainService;

@Service
@Transactional
public class UserViewMainServiceImpl implements  UserViewMainService{

	@Resource
	private UserViewMainDao userViewMainDao;

	public List<ArticletypeTable> querytArticletypes() throws Exception{
		return userViewMainDao.querytArticletypes();
	}

	public List<ArticleTable> queryArticlesByTid(String typeid) throws Exception {
		return userViewMainDao.queryArticlesByTid(typeid);
	}

	public List<ArticleTable> queryArticles() throws Exception {
		return userViewMainDao.queryArticles();
	}

	public List<ArticletypeTable> queryArticlesByTname(String articletypename) throws Exception {
		return userViewMainDao.queryArticlesByTname(articletypename);
	}

	public List<ArticleTable> queryArticlesByAno(String articleno) throws Exception {
		return userViewMainDao.queryArticlesByAno(articleno);
	}

	public String countArticletypeBytype(String articletypeid) throws Exception {
		return userViewMainDao.countArticletypeBytype(articletypeid);
	}

	public List<CommentTable> queryCommentByAno(String articleno) throws Exception {
		return userViewMainDao.queryCommentByAno(articleno);
	}

	public void insertReportArticle(ReportTable reporttable) throws Exception {
		userViewMainDao.insertReportArticle(reporttable);
	}

	public void insertCommentArticle(CommentTable commentTable) throws Exception {
		userViewMainDao.insertCommentArticle(commentTable);
	}

	public List<CollectionTable> queryCollection(String collectionno, String collectiontype, String collector)
			throws Exception {
		return userViewMainDao.queryCollection(collectionno,collectiontype,collector);
	}

	public void insertCollectionArticle(CollectionTable collectiontable) throws Exception {
		userViewMainDao.insertCollectionArticle(collectiontable);
	}

	public void updateArticleReadnum(String articleno) throws Exception {
		userViewMainDao.updateArticleReadnum(articleno);
	}
	
	public void updateArticleCollnum(String articleno) throws Exception {
		userViewMainDao.updateArticleCollnum(articleno);
	}

	public void updateArticleCommentnum(String articleno) throws Exception {
		userViewMainDao.updateArticleCommentnum(articleno);
	}

	public void updateArticleReportnum(String articleno) throws Exception {
		userViewMainDao.updateArticleReportnum(articleno);
	}

	public List<ArticleTable> queryHostArctiles() throws Exception {
		return userViewMainDao.queryHostArctiles();
	}

	public List<ArticleTable> queryNewArctiles() throws Exception {
		return userViewMainDao.queryNewArctiles();
	}

	public List<ArticleTable> queryCollectionArctiles() throws Exception {
		return userViewMainDao.queryCollectionArctiles();
	}

	public List<ArticleTable> queryNewArtsByUno(String articlepublisherno) {
		return userViewMainDao.queryNewArtsByUno(articlepublisherno);
	}

	public List<ArticleTable> queryReadArtsByUno(String articlepublisherno) {
		return userViewMainDao.queryReadArtsByUno(articlepublisherno);
	}

	public List<ArticleTable> queryCollArtsByUno(String articlepublisherno) {
		return userViewMainDao.queryCollArtsByUno(articlepublisherno);
	}

	public List<ArticleTable> queryCommArtsByUno(String articlepublisherno) {
		return userViewMainDao.queryCommArtsByUno(articlepublisherno);
	}

	public List<NoticeTable> queryNotices() throws Exception {
		return userViewMainDao.queryNotices();
	}

	public List<ArticleTable> queryArticleByAname(String aname, String typeid) {
		return userViewMainDao.queryArticleByAname(aname,typeid);
	}
}
