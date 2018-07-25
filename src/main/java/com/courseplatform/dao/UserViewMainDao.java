package com.courseplatform.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.courseplatform.po.ArticleTable;
import com.courseplatform.po.ArticletypeTable;
import com.courseplatform.po.CollectionTable;
import com.courseplatform.po.CommentTable;
import com.courseplatform.po.NoticeTable;
import com.courseplatform.po.ReportTable;

public interface UserViewMainDao {
	/**
	 * 查询所有文章类型
	 * @return
	 * @throws Exception
	 */
	List<ArticletypeTable> querytArticletypes() throws Exception;
	
	/**
	 * 根据文章类型查询所有文章
	 * @param typeid：文章类型id
	 * @return
	 * @throws Exception
	 */
	List<ArticleTable> queryArticlesByTid(@Param("ARTICLETYPES")String typeid) throws Exception;
	
	/**
	 * 查询所有文章
	 * @param typeid：文章类型id
	 * @return
	 * @throws Exception
	 */
	List<ArticleTable> queryArticles() throws Exception;
	
	/**
	 * 根据文章类型名称查询文章信息
	 * @param a
	 * @return
	 * @throws Exception
	 */
	List<ArticletypeTable> queryArticlesByTname(@Param("ARTICLETYPENAME")String articletypename) throws Exception;
	
	/**
	 * 根据文章编号查询文章信息
	 * @param articleno：文章编号
	 * @return
	 * @throws Exception
	 */
	List<ArticleTable> queryArticlesByAno(@Param("ARTICLENO")String articleno) throws Exception;
	
	/**
	 * 根据文章类型统计该类型的文章数
	 * @param commentTable
	 * @throws Exception
	 */
	String countArticletypeBytype(@Param("articletypeid")String articletypeid) throws Exception;
	
	/**
	 * 根据文章编号查询文章相关评论 
	 * @param articleno：文章编号
	 * @return
	 * @throws Exception
	 */
	List<CommentTable> queryCommentByAno(@Param("ARTICLENO")String articleno) throws Exception;
	
	/**
	 * 新增文章举报记录
	 * @throws Exception
	 */
	void insertReportArticle(@Param("reporttable")ReportTable reporttable) throws Exception;
	
	/**
	 * 新增文章评论记录
	 * @param commentTable
	 * @throws Exception
	 */
	void insertCommentArticle(@Param("commenttable")CommentTable commentTable) throws Exception;
	
	/**
	 * 根据收藏作品编号、收藏作品类型、收藏人查询收藏表记录
	 * @param commentTable
	 * @throws Exception
	 */
	List<CollectionTable> queryCollection(@Param("collectionno")String collectionno,@Param("collectiontype")String collectiontype,@Param("collector")String collector) throws Exception;
	
	/**
	 * 新增收藏表:收藏文章
	 * @param commentTable
	 * @throws Exception
	 */
	void insertCollectionArticle(@Param("collectiontable")CollectionTable collectiontable) throws Exception;
	
	/**
	 * 根据文章编号进行文章浏览次数+1
	 * @param commentTable
	 * @throws Exception
	 */
	void updateArticleReadnum(@Param("articleno")String articleno) throws Exception;
	
	/**
	 * 根据文章编号进行文章收藏次数+1
	 * @param commentTable
	 * @throws Exception
	 */
	void updateArticleCollnum(@Param("articleno")String articleno) throws Exception;
	
	/**
	 * 根据文章编号进行文章评论次数+1
	 * @param commentTable
	 * @throws Exception
	 */
	void updateArticleCommentnum(@Param("articleno")String articleno) throws Exception;
	
	/**
	 * 根据文章编号进行文章举报次数+1
	 * @param commentTable
	 * @throws Exception
	 */
	void updateArticleReportnum(@Param("articleno")String articleno) throws Exception;
	
	/**
	 * 查询浏览最高的前10个文章信息
	 * @return
	 * @throws Exception
	 */
	List<ArticleTable> queryHostArctiles() throws Exception;
	
	/**
	 * 查询最新发表的前10个文章信息
	 * @return
	 * @throws Exception
	 */
	List<ArticleTable> queryNewArctiles() throws Exception;
	
	/**
	 * 查询收藏最高的前10个文章信息
	 * @return
	 * @throws Exception
	 */
	List<ArticleTable> queryCollectionArctiles() throws Exception;
	
	/**
	 * 查询公告信息
	 * @return
	 * @throws Exception
	 */
	List<NoticeTable> queryNotices() throws Exception;
	
	/**
     * 根据作者编号查询该作者下的最新发表文章
     * @param articlepublisherno
     * @return
     */
	List<ArticleTable> queryNewArtsByUno(@Param("articlepublisherno")String articlepublisherno);
	
	/**
     * 根据作者编号查询该作者下的浏览最高文章
     * @param articlepublisherno
     * @return
     */
	List<ArticleTable> queryReadArtsByUno(@Param("articlepublisherno")String articlepublisherno);
	
	/**
     * 根据作者编号查询该作者下的推荐(收藏)最高文章
     * @param articlepublisherno
     * @return
     */
	List<ArticleTable> queryCollArtsByUno(@Param("articlepublisherno")String articlepublisherno);
	
	/**
     * 根据作者编号查询该作者下的评论最高
     * @param articlepublisherno
     * @return
     */
	List<ArticleTable> queryCommArtsByUno(@Param("articlepublisherno")String articlepublisherno);
	
	/**
     * 根据文章标题查询文章信息
     * @return
     */
	List<ArticleTable> queryArticleByAname(@Param("aname")String aname,@Param("typeid")String typeid);
}
