package com.courseplatform.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.weaver.ast.Var;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.courseplatform.po.ArticleTable;
import com.courseplatform.po.ArticletypeTable;
import com.courseplatform.po.CollectionTable;
import com.courseplatform.po.CommentTable;
import com.courseplatform.po.NoticeTable;
import com.courseplatform.po.ReportTable;
import com.courseplatform.service.UserMainPageService;
import com.courseplatform.service.UserViewMainService;


@Controller
@RequestMapping("/userViewMain/")
public class UserViewMainController {
	
	@Resource
	private UserViewMainService userViewMainService;

	@Resource
	private UserMainPageService userMainPageService;
	
	
	
	/**
	 * 加载主页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("userViewMainController.do")
	public String exect(HttpServletRequest request,HttpServletResponse response) {
		
		/*request.getSession().setAttribute("userno", "YH001");
		request.getSession().setAttribute("username", "测试员(YH001)");*/
		String userno = (String) request.getSession().getAttribute("userno");
		if(!"".equals(userno)){
			request.setAttribute("myPageHtmlStr", "<a onclick='myMainPage()' >我的主页</a>");
		}
		try {
			// 获取文章所有类型
			List<ArticletypeTable> articletypes = userViewMainService.querytArticletypes();
			StringBuffer articletypesHtmlstr = new StringBuffer("");
			request.setAttribute("articleTabsHtmlStr", "");
			request.setAttribute("articletypesHtmlstr", "");
			if(articletypes.size() > 0){
				// 获取所有文章
				List<ArticleTable> articleTables = userViewMainService.queryArticles();
				// 当前页
				int nowPage = 1;
				// 总页
				int countPage = articleTables.size()%15 == 0 ? articleTables.size()/15 : articleTables.size()/15 + 1;
				// 文章字符串
				String articeHtmlStr = getArticlesHtmlStr(articleTables,nowPage,countPage,"pagehome");
				// 根据文章类型进行文章选项卡初始化
				String articleTabsHtmlStr = getArticleTabsHtmlStr(articletypes,articeHtmlStr);
				request.setAttribute("articleTabsHtmlStr", articleTabsHtmlStr.toString());
				String rootNode = "-1";// 根节点id
				List<ArticletypeTable> articletypesRoot = getArticletypeBytypePid(articletypes, rootNode);
				if(articletypesRoot.size() > 0){
					articletypesHtmlstr.append("<ul class='list-group'>");
					for(ArticletypeTable articletype : articletypesRoot){
						articletypesHtmlstr.append("<li id='"+ articletype.getArticletypeid() + "_" + articletype.getArticletypename() + "' class='list-group-item' onclick='clickLi(this.id)' onmouseover='typeOnmouseover(this)' onmouseout='typeOnmouseout(this)'>");
						articletypesHtmlstr.append("<span class='badge'>" + countArticletypeBytype(articletype.getArticletypeid()) +"</span>");
						articletypesHtmlstr.append(articletype.getArticletypename());
						List<ArticletypeTable> list = getArticletypeBytypePid(articletypes, articletype.getArticletypeid());
						if(list.size() > 0){
							articletypesHtmlstr.append("<div style='margin-left:150px;position:fixed;float:left;z-index:9999;'>");
							articletypesHtmlstr.append("<ul class='list-group' style='display:none;width:150px;'>");
							for(ArticletypeTable a : list){
								articletypesHtmlstr.append("<li id='" + a.getArticletypeid() + "_" + a.getArticletypename() + "' class='list-group-item' onclick='clickLi(this.id)'>");
								articletypesHtmlstr.append("<span class='badge'>" + countArticletypeBytype(a.getArticletypeid()) +"</span>");
								articletypesHtmlstr.append(a.getArticletypename());
								articletypesHtmlstr.append("</li>");
							}
							articletypesHtmlstr.append("</ul>");
							articletypesHtmlstr.append("</div>");
						}
						articletypesHtmlstr.append("</li>");
					}
					articletypesHtmlstr.append("</ul>");
				}
				request.setAttribute("articletypesHtmlstr", articletypesHtmlstr.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "teacherJsp/userViewMain";
	}
	
	/**
	 * 根据文章类型统计该类型的文章数
	 * @param articletypeid
	 * @return
	 */
	private String countArticletypeBytype(String articletypeid){
		String count = "0";
		try {
			count = userViewMainService.countArticletypeBytype(articletypeid);
		} catch (Exception e) {
			count = "0";
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 根据文章类型进行文章选项卡初始化
	 * @param articletypes
	 * @return
	 */
	private String getArticleTabsHtmlStr(List<ArticletypeTable> articletypes,String articeHtmlStr){
		StringBuffer htmlStr = new StringBuffer();
		htmlStr.append("<div id='firstPage' title='首页' style='padding:20px;display:none;background-color:#fafafa;'>");
		htmlStr.append(articeHtmlStr);
		htmlStr.append("</div>");
		for(ArticletypeTable articletypeTable : articletypes){
			htmlStr.append("<div id='" + articletypeTable.getArticletypeid() + "' title='" + articletypeTable.getArticletypename() + "' style='padding:20px;display:none;background-color:#fafafa;' data-options='closable:true'>");
			try {
				List<ArticleTable> articleTables = userViewMainService.queryArticlesByTid(articletypeTable.getArticletypeid());
				if(articleTables.size() > 0){
					int countPage = articleTables.size()%15 == 0 ? articleTables.size()/15 : articleTables.size()/15 + 1;
					htmlStr.append(getArticlesHtmlStr(articleTables,1,countPage,"page" + articletypeTable.getArticletypeid()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			htmlStr.append("</div>");
		}
		return htmlStr.toString();
	}
	
	/**
	 * 根据父节点id查询所有子节点
	 * @return list
	 */
	private List<ArticletypeTable> getArticletypeBytypePid(List<ArticletypeTable> articletypes,String pid){
		List<ArticletypeTable> list = new LinkedList<ArticletypeTable>();
		for(ArticletypeTable articletype : articletypes){
			if(pid.equals(articletype.getArticletypefather())){
				list.add(articletype);
			}
		}
		return list;
	}
	
	/**
	 * 根据文章类型进行文章查询
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("getArticles.do")
	public void getArticles(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String articletypeid = request.getParameter("articletypeid");
		try {
			List<ArticleTable> articleTables = userViewMainService.queryArticlesByTid(articletypeid);
			PrintWriter pw = response.getWriter();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("articeHtmlStr", "");
			if(articleTables.size() > 0){
				// 对文章信息进行拼接为html
				int countPage = articleTables.size()%15 == 0 ? articleTables.size()/15 : articleTables.size()/15 + 1;
				String articeHtmlStr = getArticlesHtmlStr(articleTables,1,countPage,"page" + articletypeid);
				jsonObject.put("articeHtmlStr", articeHtmlStr);
			}
			pw.write(jsonObject.toString());
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 对文章信息进行拼接为html
	 * @param articleTables
	 * @return
	 */
	private String getArticlesHtmlStr(List<ArticleTable> articleTables,int nowPage,int countPage ,String articletype){
		StringBuffer htmlStr = new StringBuffer("");
		if(articleTables.size() > 0){
			if(nowPage > countPage){
				nowPage = countPage;
			}
			for(int i = (nowPage - 1) * 15, size = articleTables.size(); i < size; i++){
				if(i == nowPage * 15){
					break;
				}
				ArticleTable articleTable = articleTables.get(i);
				htmlStr.append("<div style='width:100%;height:150px;border-bottom:#000000 1px dashed;background-color:#fafafa;'>");
				//htmlStr.append("<a name=" + articleTable.getArticleno() + " style='margin-top:70px;float:left;' onclick='report(this.name)'>举报</a>");
				htmlStr.append("<div style='height:40px;margin-left:10px;'>");
				htmlStr.append("<a href='viewArticleMsg.do?articleno=" + articleTable.getArticleno() + "'  target='_blank' style='font-size:25px;text-decoration:underline;'>" + articleTable.getArticlename() + "</a>");
				htmlStr.append("</div>");
				htmlStr.append("<div style='height:70px;margin-left:10px;'>");
				htmlStr.append("<span style='font-size:25px;'>简介:</span>" + articleTable.getArticlecabstract());
				htmlStr.append("<a name='" + articleTable.getArticleno() + "' onclick='readArctile(this.name)'>(阅读全文)</a>");
				htmlStr.append("</div>");
				htmlStr.append("<div style='height:40px;margin-left:10px;font-size:15px;'>");
				htmlStr.append("<span style='font-size:20px;color:blue;'>" + articleTable.getArticlepublisher() + "</span>&nbsp;&nbsp;");
				htmlStr.append("发布于&nbsp;&nbsp;" + articleTable.getArticlepublishtime() + "&nbsp;&nbsp;");
				htmlStr.append("<a name=" + articleTable.getArticleno() + " onclick='comment(this.name)'>评论(" + articleTable.getArticlecomnum() + ")</a>&nbsp;&nbsp;<a name=" + articleTable.getArticleno() + " onclick='collectionArctile(this)'>收藏(" + articleTable.getArticlecollectnum() + ")</a>&nbsp;&nbsp;");
				htmlStr.append("<a name=" + articleTable.getArticleno() + " onclick='report(this.name)'>举报(" + articleTable.getArticlereportnum() + ")</a>&nbsp;&nbsp;<a name=" + articleTable.getArticleno() + " onclick='readArctile(this.name)'>浏览(" + articleTable.getArticlereadnum() + ")</a>");
				htmlStr.append("</div>");
				htmlStr.append("</div>");
			}
		}
		// 分页html字符串
		String pageHtmlStr = "";
		if(countPage > 0){
			pageHtmlStr = getPage(nowPage, countPage, articletype);
		}
		htmlStr.append(pageHtmlStr);
		return htmlStr.toString();
	}
	
	/**
	 * 进行文章分页栏拼接
	 * @return
	 */
	private String getPage(Integer nowPage,Integer countPage,String articletype){
		StringBuffer htmlStr = new StringBuffer();
		// 进行分页拼接
		htmlStr.append("<div style='text-align:center;'>");
		htmlStr.append("<nav aria-label='Page navigation'>");
		htmlStr.append("<ul class='pagination'>");
		htmlStr.append("<li>");
		htmlStr.append("<a href='#' aria-label='Previous' name='" + articletype + "' onclick='previousPageClick(this.name)'><span aria-hidden='true'>&laquo;</span></a>");
		htmlStr.append("</li>");
		for(int i = 1; i <= countPage; i++){
			if(i == nowPage){
				htmlStr.append("<li name='" + articletype + "' class='active' onclick='pageClick(this)'><a href='#' name='" + articletype + "'>" + i + "</a></li>");
			}else {
				htmlStr.append("<li name='" + articletype + "' onclick='pageClick(this)'><a href='#'  name='" + articletype + "'>" + i + "</a></li>");
			}
		}
		htmlStr.append("<li>");
		htmlStr.append("<a href='#' aria-label='Next' name='" + articletype + "' onclick='nextPageClick(this.name)'><span aria-hidden='true'>&raquo;</span></a>");
		htmlStr.append("</li>");
		htmlStr.append("</ul>");
		htmlStr.append("</nav>");
		htmlStr.append("</div>");
		return htmlStr.toString();
	}
	
	/**
	 * 根据文章类型查询该类型下所有文章
	 * @param request
	 * @param response
	 */
	@RequestMapping("getArticlesByType.do")
	public void getArticlesByType(HttpServletRequest request,HttpServletResponse response){
		String articletypename = request.getParameter("articletypename");
		try {
			PrintWriter pw = response.getWriter();
			JSONObject jsonObject = new JSONObject();
			if("首页".equals(articletypename)){
				// 获取所有文章
				List<ArticleTable> articleTables = userViewMainService.queryArticles();
				jsonObject.put("articletypeid", "firstPage");
				if(!articleTables.isEmpty()){
					int countPage = articleTables.size()%15 == 0 ? articleTables.size()/15 : articleTables.size()/15 + 1;
					String articeHtmlStr = getArticlesHtmlStr(articleTables,1,countPage,"pagehome");
					jsonObject.put("articeHtmlStr", articeHtmlStr);
				}
			}else {
				List<ArticletypeTable> articletypeTables = userViewMainService.queryArticlesByTname(articletypename);
				jsonObject.put("articeHtmlStr", "");
				if(articletypeTables.size() > 0){
					String articletypeid = articletypeTables.get(0).getArticletypeid();
					jsonObject.put("articletypeid", articletypeid);
					List<ArticleTable> articleTables = userViewMainService.queryArticlesByTid(articletypeid);
					if(!articleTables.isEmpty()){
						int countPage = articleTables.size()%15 == 0 ? articleTables.size()/15 : articleTables.size()/15 + 1;
						String articeHtmlStr = getArticlesHtmlStr(articleTables,1,countPage,"page" +articletypeid);
						jsonObject.put("articeHtmlStr", articeHtmlStr);
					}
				}
			}
			pw.write(jsonObject.toString());
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据文章类型查询该类型下所有文章(分页栏查询)
	 * @param request
	 * @param response
	 */
	@RequestMapping("getArticlesPageByType.do")
	public void getArticlesPageByType(HttpServletRequest request,HttpServletResponse response){
		String articletypeid = request.getParameter("articletypeid");
		int nowPage = Integer.parseInt(request.getParameter("nowPage"));
		try {
			PrintWriter pw = response.getWriter();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("articeHtmlStr", "");
			if("首页".equals(articletypeid)){
				// 获取所有文章
				List<ArticleTable> articleTables = userViewMainService.queryArticles();
				jsonObject.put("articletypeid", "firstPage");
				if(!articleTables.isEmpty()){
					int countPage = articleTables.size()%15 == 0 ? articleTables.size()/15 : articleTables.size()/15 + 1;
					String articeHtmlStr = getArticlesHtmlStr(articleTables,nowPage,countPage,"pagehome");
					jsonObject.put("articeHtmlStr", articeHtmlStr);
				}
			}else {
				jsonObject.put("articletypeid", articletypeid);
				List<ArticleTable> articleTables = userViewMainService.queryArticlesByTid(articletypeid);
				if(!articleTables.isEmpty()){
					int countPage = articleTables.size()%15 == 0 ? articleTables.size()/15 : articleTables.size()/15 + 1;
					String articeHtmlStr = getArticlesHtmlStr(articleTables,nowPage,countPage,"page" +articletypeid);
					jsonObject.put("articeHtmlStr", articeHtmlStr);
				}
			}
			pw.write(jsonObject.toString());
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询文章详情
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("viewArticleMsg.do")
	public String viewArticleMsg(HttpServletRequest request,HttpServletResponse response){
		// 文章编号
		String articleno = request.getParameter("articleno");
		// 根据文章编号查询文章信息
		getArticleMsg(request,articleno);
		return "teacherJsp/viewArticleMsg";
	}
	
	/**
	 * 对文章评论进行html拼接
	 * @param articleTable
	 * @return
	 */
	private String getCommentHtmlStr(List<CommentTable> commentTables){
		StringBuffer htmlStr = new StringBuffer("");
		for(CommentTable commentTable : commentTables){
			htmlStr.append("<div style='width:100%;'>");
			htmlStr.append(commentTable.getCommenter() + "&nbsp;&nbsp;:&nbsp;&nbsp" + commentTable.getCommentcontent());
			htmlStr.append("<br>");
			htmlStr.append(commentTable.getCommenttime());
			htmlStr.append("<br>");
			htmlStr.append("</div>");
			htmlStr.append("<br>");
		}
		return htmlStr.toString();
	}
	
	/**
	 * 根据文章编号进行文章信息查询
	 * @param articleno
	 * @return
	 */
	private void getArticleMsg(HttpServletRequest request,String articleno){
		try {
			// 根据文章编号进行文章浏览次数+1
			userViewMainService.updateArticleReadnum(articleno);
			List<ArticleTable> articleTables = userViewMainService.queryArticlesByAno(articleno);
			if(articleTables.size() > 0){
				ArticleTable articleTable = articleTables.get(0);
				request.setAttribute("articleno", articleTable.getArticleno()); // 编号
				request.setAttribute("articlename", articleTable.getArticlename()); // 名称
				request.setAttribute("articlepublisher", articleTable.getArticlepublisher()); // 作者
				// 作者编号
				String articlepublisherno = articleTable.getArticlepublisherno();
				request.setAttribute("articlepublisherno", articlepublisherno); // 作者编号
				request.setAttribute("articlepublishtime", articleTable.getArticlepublishtime()); // 发布时间
				request.setAttribute("articlecabstract", articleTable.getArticlecabstract()); // 简介
				request.setAttribute("articlecontent", articleTable.getArticlecontent()); // 内容
				request.setAttribute("articlereadnum", articleTable.getArticlereadnum());// 浏览
				request.setAttribute("articlecomnum", articleTable.getArticlecomnum());// 评论
				request.setAttribute("articlecollectnum", articleTable.getArticlecollectnum());// 收藏
				request.setAttribute("articlereportnum", articleTable.getArticlereportnum());// 举报
				// 根据作者编号查询该作者下的最新发表文章
				List<ArticleTable> newArts = userViewMainService.queryNewArtsByUno(articlepublisherno);
				if(newArts.size() > 0){
					String newArtsHtmlStr = appendArticleMsg(newArts);
					request.setAttribute("newArtsHtmlStr", newArtsHtmlStr);
				}
				// 根据作者编号查询该作者下的浏览最高文章
				List<ArticleTable> readArts = userViewMainService.queryReadArtsByUno(articlepublisherno);
				if(readArts.size() > 0){
					String readArtsHtmlStr = appendArticleMsg(readArts);
					request.setAttribute("readArtsHtmlStr", readArtsHtmlStr);
				}
				// 根据作者编号查询该作者下的推荐(收藏)最高文章
				List<ArticleTable> collArts = userViewMainService.queryCollArtsByUno(articlepublisherno);
				if(collArts.size() > 0){
					String collArtsHtmlStr = appendArticleMsg(collArts);
					request.setAttribute("collArtsHtmlStr", collArtsHtmlStr);
				}
				// 根据作者编号查询该作者下的评论最高
				List<ArticleTable> commArts = userViewMainService.queryCommArtsByUno(articlepublisherno);
				if(commArts.size() > 0){
					String commArtsHtmlStr = appendArticleMsg(commArts);
					request.setAttribute("commArtsHtmlStr", commArtsHtmlStr);
				}
			}else {
				request.setAttribute("articleno", ""); // 编号
				request.setAttribute("articlename", ""); // 名称
				request.setAttribute("articlepublisher", ""); // 作者
				request.setAttribute("articlepublisherno", ""); // 作者编号
				request.setAttribute("articlepublishtime", ""); // 发布时间
				request.setAttribute("articlecabstract", "该文章已失效"); // 简介
				request.setAttribute("articlecontent", "该文章已失效"); // 内容
				request.setAttribute("articlereadnum", "0");// 浏览
				request.setAttribute("articlecomnum", "0");// 评论
				request.setAttribute("articlecollectnum", "0");// 收藏
				request.setAttribute("articlereportnum", "0");// 举报
			}
			// 查询文章所有相关评论信息
			List<CommentTable> commentTables = userViewMainService.queryCommentByAno(articleno);
			if(commentTables.size() > 0){
				// 对文章评论进行html拼接
				String commentHtmlStr = getCommentHtmlStr(commentTables);
				request.setAttribute("commentHtmlStr", commentHtmlStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 对文章进行举报
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("reportArticle.do")
	public void reportArticle(HttpServletRequest request,HttpServletResponse response) throws IOException{
		PrintWriter printWriter = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		// 举报人
		String reporter = request.getParameter("reporter");
		// 被举报人
		String reporteer = request.getParameter("reporteer");
		// 被举报文章编号
		String reportgoodsno = request.getParameter("reportgoodsno");
		// 被举报原因
		String reportreason = request.getParameter("reportreason");
		// 被举报类型
		String reportgoodstype = request.getParameter("reportgoodstype");
		ReportTable reporttable = new ReportTable();
		try {
			// 举报编号
			reporttable.setReportno("JZ" + System.currentTimeMillis());
			reporttable.setReportreason(reportreason);
			reporttable.setReporter(reporter);
			reporttable.setReporteer(reporteer);
			reporttable.setReportgoodsno(reportgoodsno);
			reporttable.setReportgoodstype(reportgoodstype);
			reporttable.setReportfinishflag("0");
			userViewMainService.insertReportArticle(reporttable);
			jsonObject.append("msg", "举报成功");
			
			// 对文章举报记录进行+1
			userViewMainService.updateArticleReportnum(reportgoodsno);
		} catch (Exception e) {
			jsonObject.append("msg", "举报失败");
			e.printStackTrace();
		}
		printWriter.append(jsonObject.toString());
		printWriter.flush();
	}
	
	/**
	 * 验证用户是否登录
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("verifyUserLogin.do")
	public void verifyUserLogin(HttpServletRequest request,HttpServletResponse response) throws IOException{
		PrintWriter pWriter = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		HttpSession session = request.getSession();
		// 获取用户编号
		String userno = (String) session.getAttribute("userno");
		jsonObject.append("userno", userno);
		pWriter.write(jsonObject.toString());
		pWriter.flush();
	}
	
	/**
	 * 文章发表评论
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("comment.do")
	public void comment(HttpServletRequest request,HttpServletResponse response) throws IOException{
		PrintWriter printWriter = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		// 评论人
		String commenter = request.getParameter("commenter");
		// 评论人姓名
		String commenterno = request.getParameter("commenterno");
		// 文章编号
		String articleno = request.getParameter("articleno");
		// 评论内容
		String commentcontent = request.getParameter("commentcontent");
		CommentTable commentTable = new CommentTable();
		try {
			// 评论编号
			commentTable.setCommentno("PL" + System.currentTimeMillis());
			commentTable.setCommenter(commenter);
			commentTable.setCommenterno(commenterno);
			commentTable.setArticleno(articleno);
			commentTable.setCommentcontent(commentcontent);
			userViewMainService.insertCommentArticle(commentTable);
			jsonObject.append("msg", "评论成功");
			
			// 对文章评论记录进行+1
			userViewMainService.updateArticleCommentnum(articleno);
			
			// 查询文章所有相关评论信息
			List<CommentTable> commentTables = userViewMainService.queryCommentByAno(articleno);
			if(commentTables.size() > 0){
				// 对文章评论进行html拼接
				String commentHtmlStr = getCommentHtmlStr(commentTables);
				jsonObject.append("commentHtmlStr", commentHtmlStr);
			}
		} catch (Exception e) {
			jsonObject.append("msg", "评论失败");
			e.printStackTrace();
		}
		printWriter.append(jsonObject.toString());
		printWriter.flush();
	}

	/**
	 * 文章收藏
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("cllectionArticle.do")
	public void cllectionArticle(HttpServletRequest request,HttpServletResponse response) throws IOException{
		PrintWriter printWriter = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		// 收藏人
		String collector = request.getParameter("collector");
		// 文章编号
		String collectionno = request.getParameter("collectionno");
		// 收藏作品类型
		String collectiontype = request.getParameter("collectiontype");
		// 根据收藏作品编号、收藏作品类型、收藏人查询收藏表记录
		List<CollectionTable> collectionTables;
		try {
			collectionTables = userViewMainService.queryCollection(collectionno, collectiontype, collector);
			if(collectionTables.size() > 0){
				jsonObject.append("msg", "已收藏");
			}else {
				try {
					CollectionTable collectionTable = new CollectionTable();
					// 收藏编号
					collectionTable.setCollectno("SC" + System.currentTimeMillis());
					collectionTable.setCollector(collector);
					collectionTable.setCollectionno(collectionno);
					collectionTable.setCollectiontype(collectiontype);
					userViewMainService.insertCollectionArticle(collectionTable);
					jsonObject.append("msg", "收藏成功");
					// 进行文章收藏记录+1
					userViewMainService.updateArticleCollnum(collectionno);
				} catch (Exception e) {
					jsonObject.append("msg", "收藏失败");
					e.printStackTrace();
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		printWriter.append(jsonObject.toString());
		printWriter.flush();
	}
	
	/**
	 * 进行热门文章、最新文章、收藏最多文章、公告信息展示
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("getArsHostNewCollmaxGg.do")
	public void getArsHostNewCollmaxGg(HttpServletRequest request,HttpServletResponse response) throws IOException{
		PrintWriter printWriter = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		try {
			// 热门文章
			List<ArticleTable> hosts = userViewMainService.queryHostArctiles();
			if(hosts.size() > 0){
				String hostArticlesHtmlstr = appendArticleMsg(hosts);
				jsonObject.append("hostArticlesHtmlstr", hostArticlesHtmlstr);
			}else {
				jsonObject.append("hostArticlesHtmlstr", "");
			}
			// 最新文章
			List<ArticleTable> news = userViewMainService.queryNewArctiles();
			if(news.size() > 0){
				String newArticlesHtmlstr = appendArticleMsg(news);
				jsonObject.append("newArticlesHtmlstr", newArticlesHtmlstr);
			}else {
				jsonObject.append("newArticlesHtmlstr", "");
			}
			// 收藏最多文章
			List<ArticleTable> collmaxs = userViewMainService.queryCollectionArctiles();
			if(collmaxs.size() > 0){
				String collmaxArticlesHtmlstr = appendArticleMsg(collmaxs);
				jsonObject.append("collmaxArticlesHtmlstr", collmaxArticlesHtmlstr);
			}else {
				jsonObject.append("collmaxArticlesHtmlstr", "");
			}
			// 公告信息
			List<NoticeTable> noticeTables = userMainPageService.queryNotices();
			if(noticeTables.size() > 0){
				String noticeHtmlStr = getNoticeMsgHtmlStr(noticeTables);
				jsonObject.append("noticeHtmlStr", noticeHtmlStr);
			}else {
				jsonObject.append("noticeHtmlStr", "暂无公告信息");
			}
			printWriter.write(jsonObject.toString());
			printWriter.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 对公告进行html拼接
	 * @param noticeTables
	 * @return
	 */
	private String getNoticeMsgHtmlStr(List<NoticeTable> noticeTables){
		StringBuffer htmlStr = new StringBuffer();
		htmlStr.append("<ul style='margin-left:-15px;'>");
		for(NoticeTable noticeTable : noticeTables){
			String content = noticeTable.getNotcecontent();
			String contentView = "";
			if(content.length() >= 16){
				contentView = content.substring(0, 16);
			}else {
				contentView = content.substring(0);
			}
			htmlStr.append("<li style='margin-top:5px;'><a name='" + content + "' onclick='noticeClick(this.name)'>" + contentView + "...</a></li>");
		}
		htmlStr.append("</ul>");
		return htmlStr.toString();
	}
	
	/**
	 * 对文章list进行拼接为html字符串
	 * @param articleTables
	 * @return
	 */
	private String appendArticleMsg(List<ArticleTable> articleTables){
		StringBuffer htmlStr = new StringBuffer();
		htmlStr.append("<ul style='margin-left:-15px;'>");
		for(ArticleTable articleTable : articleTables){
			htmlStr.append("<li style='margin-top:5px;'><a href='viewArticleMsg.do?articleno=" + articleTable.getArticleno() + "'  target='_blank'>" + articleTable.getArticlename() + "</a></li>");
		}
		htmlStr.append("</ul>");
		return htmlStr.toString();
	}
	
	/**
	 * 文章收藏
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("queryArticleByAname.do")
	public void queryArticleByAname(HttpServletRequest request,HttpServletResponse response) throws IOException{
		PrintWriter printWriter = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		String aname = request.getParameter("val");
		String typeid = request.getParameter("typeid");
		String articletypeid = typeid;
		if("firstPage".equals(typeid)){
			typeid = "";
			articletypeid = "pagehome";
		}
		List<ArticleTable> articleTables = userViewMainService.queryArticleByAname(aname, typeid);
		int countPage = articleTables.size()%15 == 0 ? articleTables.size()/15 : articleTables.size()/15 + 1;
		jsonObject.append("articeHtmlStr", "");
		if(articleTables.size() > 0){
			String articeHtmlStr = getArticlesHtmlStr(articleTables,1,countPage,"page" + articletypeid);
			jsonObject.append("articeHtmlStr", articeHtmlStr);
		}
		printWriter.write(jsonObject.toString());
		printWriter.flush();
	}
}
