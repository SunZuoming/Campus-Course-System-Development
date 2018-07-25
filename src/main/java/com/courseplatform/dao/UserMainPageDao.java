package com.courseplatform.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

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

public interface UserMainPageDao {
    /**
     * 根据用户编号查询用户角色编号
     * @param userno
     * @return
     */
	List<UserTable> queryRoleidByUserno(@Param("userno")String userno);
	
	/**
     * 根据角色编号查询角色权限
     * @param roleid
     * @return
     */
	List<JurisdictionTable> queryJidByRoleid(@Param("roleid")String roleid);
	
	/**
     * 根据功能编号查询功能全部信息
     * @param functionno
     * @return
     */
	List<FunctionTable> queryFunByFno(@Param("functionno")String functionno);
	
	/**
     * 根据收藏人编号查询该用户所收藏的全部文章
     * @param collector
     * @return
     */
	List<CollectionTable> queryCollArtsByUserno(@Param("collector")String collector);
	
	/**
     * 根据文章编号查询文章信息
     * @param articleno
     * @return
     */
	List<ArticleTable> queryArtMsgByAno(@Param("articleno")String articleno);
	
	/**
     * 根据用户编号查询用户所发布的文章信息
     * @param userno
     * @return
     */
	List<ArticleTable> queryArtMsgByUserno(@Param("userno")String userno);
	
	/**
     * 查询所有文章类型
     * @param userno
     * @return
     */
	List<ArticletypeTable> queryArttypes();
	
	/**
     * 新增文章表
     * @param articletable
     * @return
     */
	void insertArticle(@Param("articletable")ArticleTable articletable) throws Exception;
	
	/**
     * 根据文章编号、收藏人编号、收藏类型进行收藏记录的删除
     * @param collectionno
     * @param collectiontype
     * @param collector
     * @return
     */
	void deleteArticleColl(@Param("collectionno")String collectionno,@Param("collectiontype")String collectiontype,@Param("collector")String collector) throws Exception;
	
	/**
     * 根据文章编号删除文章记录
     * @param articleno
     * @return
     */
	void deleteArticle(@Param("articleno")String articleno) throws Exception;
	
	/**
     * 根据文章编号删除文章收藏的所有记录
     * @param articleno
     * @return
     */
	void deleteArticleCollections(@Param("collectionno")String collectionno) throws Exception;
	
	/**
     * 根据文章编号修改文章信息
     * @param articletable
     * @return
     */
	void updateArticleMsg(@Param("articletable")ArticleTable articletable) throws Exception;
	
	/**
	 * 查询所有问题类型
	 * @return
	 */
	List<QuestiontypeTable> quertQuestiontypes();
	
	/**
	 * 新增问题表
	 * @throws Exception
	 */
	void insertQuestion(@Param("questiontable")QuestionTable questiontable) throws Exception;
	
	/**
	 * 查询最新5条问题记录
	 * @return
	 */
	List<QuestionTable> queryNewQuestion();
	
	/**
	 * 查询评论数最高的15条记录
	 * @return
	 */
	List<QuestionTable> queryCommentNumQuestion();
	
	/**
	 * 根据问题类型查询该类型本月评论数最高的问题(取前3条数据)
	 * @return
	 */
	List<QuestionTable> queryMounthComnumQue(@Param("questiontypeno")String questiontypeno);
	
	/**
	 * 根据问题类型查询该类型本年评论数最高的问题(取前3条数据)
	 * @return
	 */
	List<QuestionTable> queryYearComnumQue(@Param("questiontypeno")String questiontypeno);
	
	/**
	 * 根据问题类型查询该类型下全部问题信息(按问题发表时间降序排序)
	 * @return
	 */
	List<QuestionTable> queryQueByQtype(@Param("questionmap")HashMap<String, Object> questionmap);
	
	/**
	 * 根据问题类型查询该类型下评论数最高的问题(按评论降序排序)
	 * @return
	 */
	List<QuestionTable> queryComnumQueByQtype(@Param("questionmap")HashMap<String, Object> questionmap);
	
	/**
	 * 根据问题编号查询问题详情
	 * @return
	 */
	List<QuestionTable> quertQuestionByQno(@Param("questionno")String questionno);
	
	/**
	 * 新增问题回答表
	 * @param a
	 * @throws Exception
	 */
	void insertAnswer(@Param("answertable")AnswerTable answertable) throws Exception;
	
	/**
	 * 根据问题编号查询问题回答记录信息
	 * @return
	 */
	List<AnswerTable> quertAnswersByQno(@Param("questionno")String questionno);
	
	/**
	 * 根据用户编号查询用户所有文章分类信息
	 * @return
	 */
	List<String> quertArtByUserno(@Param("userno")String userno);
	
	/**
	 * 通过课程文章类型编号查询课程文章类型信息
	 * @return
	 */
	List<ArticletypeTable> queryArttypeByTypeno(@Param("typeno")String typeno);
	
	/**
     * 根据用户编号、课程文章类型编号查询用户所发布的文章信息
     * @param userno
     * @return
     */
	List<ArticleTable> queryArtclesByTypeno(@Param("userno")String userno,@Param("typeid")String typeid);
	
	/**
	 * 根据问题编号对问题评论记录进行+1
	 * @param a
	 * @throws Exception
	 */
	void updateQuesnumAdd1(@Param("questionno")String questionno) throws Exception;
	
	/**
	 * 新增回复表
	 * @param replaytable
	 * @throws Exception
	 */
	void insertReplay(@Param("replaytable")ReplayTable replaytable) throws Exception;
	
	/**
	 * 根据评论编号进行该评论的回复查询
	 * @param answerno
	 * @return
	 */
	List<ReplayTable> queryReplayByAnswerno(@Param("answerno")String answerno);
	
	/**
	 * 新增消息表
	 * @param replaytable
	 * @throws Exception
	 */
	void insertInfo(@Param("infotable")InfoTable infotable) throws Exception;
	
	/**
	 * 查询个人未读消息记录
	 * @param userno
	 * @return
	 */
	List<InfoTable> queryInfonumByUserno(@Param("userno")String userno);
	
	/**
	 * 查询个人消息记录
	 * @param userno
	 * @return
	 */
	List<InfoTable> queryInfoByUserno(@Param("userno")String userno);
	
	/**
	 * 根据评论编号查询该评论记录
	 * @param answerno
	 * @return
	 */
	List<AnswerTable> queryAnwserByAno(@Param("answerno")String answerno);
	
	/**
	 * 根据回复编号查询回复记录
	 * @param answerno
	 * @return
	 */
	List<ReplayTable> queryReplayByRno(@Param("replayno")String replayno);
	
	/**
	 * 根据消息编号将消息是否已读标志改为已读 (1)
	 * @param infono
	 * @throws Exception
	 */
	void updateInfoReadsign(@Param("infono")String infono) throws Exception;
	
	/**
	 * 根据消息编号删除消息记录
	 * @param infono
	 * @throws Exception
	 */
	void deleteInfoByInfono(@Param("infono")String infono) throws Exception;
	
	/**
	 * 查询公告信息
	 * @return
	 * @throws Exception
	 */
	List<NoticeTable> queryNotices();
	
	/**
	 * 查询收藏最高的前10个文章信息
	 * @return
	 * @throws Exception
	 */
	List<ArticleTable> queryHostArctiles();
	
	/**
	 * 根据问题类型和问题标题进行模糊查询
	 * @return
	 */
	List<QuestionTable> queryAllQueByQname(@Param("questionname")String questionname,@Param("questiontypeno")String questiontypeno);
	
	/**
	 * 根据问题类型和问题标题进行模糊查询,并按评论排序
	 * @return
	 */
	List<QuestionTable> queryHostQueByQname(@Param("questionname")String questionname,@Param("questiontypeno")String questiontypeno);
}
