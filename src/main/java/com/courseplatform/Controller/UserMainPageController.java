package com.courseplatform.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.courseplatform.po.AnswerTable;
import com.courseplatform.po.ArticleTable;
import com.courseplatform.po.ArticletypeTable;
import com.courseplatform.po.CollectionTable;
import com.courseplatform.po.CommentTable;
import com.courseplatform.po.FunctionTable;
import com.courseplatform.po.InfoTable;
import com.courseplatform.po.JurisdictionTable;
import com.courseplatform.po.NoticeTable;
import com.courseplatform.po.QuestionTable;
import com.courseplatform.po.QuestiontypeTable;
import com.courseplatform.po.ReplayTable;
import com.courseplatform.po.User;
import com.courseplatform.po.UserTable;
import com.courseplatform.po.Userimage;
import com.courseplatform.service.TeacherService;
import com.courseplatform.service.UserMainPageService;
import com.courseplatform.service.UserViewMainService;
import com.courseplatform.util.AES1;

@Controller
@RequestMapping("/userMainPage/")
public class UserMainPageController {
	@Resource
	private UserMainPageService userMainPageService;
	
	@Resource
	private UserViewMainService userViewMainService;
	
	@Autowired
	private TeacherService teacherservice;

	// 登录
	@RequestMapping("login.do")
	public String login(User user,Userimage userimage, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		User users = teacherservice.getUser(user);
		JurisdictionTable jurisdiction = new JurisdictionTable();
		String ju = "000002";
		String identify = request.getParameter("identify");
		//System.out.println(identify);
		String identify1 = request.getSession().getAttribute("valiCode")
				.toString();
		//System.out.println(users.getUserPassword());
		String password=request.getParameter("userPassword");
		String pass = null;
		if(users==null|| ju.equals("000001")) {
		    model.addAttribute("loginwo", "账号错误，请重新输入！");
		    return "teacherJsp/loginwo";
		} else {
			if(users.getRoleId().equals("000001")) {
			    model.addAttribute("loginwo", "账号错误，请重新输入！");
			    return "teacherJsp/loginwo";
			}
			if (!request.getParameter("userPassword").equals(AES1.AESDncode("abcd", users.getUserPassword()))) {
				// pass=AES1.AESDncode("abcd", users.getUserPassword());
				model.addAttribute("loginwo", "密码错误，请重新输入！");
				return "teacherJsp/loginwo";
			} 
			if (!identify.equals(identify1)) {
				model.addAttribute("loginwo", "验证码不正确！");
				return "teacherJsp/loginwo";
			} 
			if (users.getUserState().equals("1") || users.getUserState().equals("2")) {
				model.addAttribute("loginwo", "您已经被封号！");
				return "teacherJsp/loginwo";
			} 
			if (users.getRoleId().equals("JS002")) {
				// 教师
				session.setAttribute("userno", users.getUserNo());
				session.setAttribute("username", users.getUserName());
				// System.out.println( user.getUserNo());
				userimage.setUserno(users.getUserNo());
				System.out.println(user.getUserNo());
				Userimage image = teacherservice.imageTo(userimage);
				session.setAttribute("im", image);
				return exect(request, response);
			} else {
				// 学生
				session.setAttribute("userno", users.getUserNo());
				session.setAttribute("username", users.getUserName());
				userimage.setUserno(users.getUserNo());
				System.out.println(user.getUserNo());
				Userimage image = teacherservice.imageTo(userimage);
				session.setAttribute("im", image);
				return exect(request, response);
			}
		}
		

	}
	
	/**
	 * 加载主页面:根据权限进行个人页面加载
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("userMainPageController.do")
	public String exect(HttpServletRequest request,HttpServletResponse response) {
		// 用户编号
		//String userno = request.getParameter("userno");
		String userno = (String) request.getSession().getAttribute("userno");
		try {
			// 根据用户编号查询用户角色编号
			List<UserTable> users = userMainPageService.queryRoleidByUserno(userno);
			if(users.size() > 0){
				UserTable userTable = users.get(0);
				// 用户角色编号
				String roleid = userTable.getRoleid();
				// 用户编号
				request.setAttribute("userno", userTable.getUserno());
				// 用户名称
				request.setAttribute("username", userTable.getUsername());
				// 用户电话
				request.setAttribute("userphone", userTable.getUserphone());
				// 根据角色编号查询角色权限
				List<JurisdictionTable> jurisdictionTables = userMainPageService.queryJidByRoleid(roleid);
				request.setAttribute("functionHtmlStr", "该用户暂无任何功能");
				if(jurisdictionTables.size() > 0){
					// 对用户功能进行拼接为html字符串
					String functionHtmlStr = getFunctionsHtmlStr(jurisdictionTables);
					request.setAttribute("functionHtmlStr", functionHtmlStr);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "teacherJsp/userMainPage";
	}
	
	/**
	 * 对用户功能进行拼接为html字符串
	 * @param jurisdictionTables
	 * @return
	 */
	private String getFunctionsHtmlStr(List<JurisdictionTable> jurisdictionTables){
		StringBuffer htmlStr = new StringBuffer();
		// 用户所有功能list
		List<FunctionTable> list = new LinkedList<FunctionTable>();
		for(JurisdictionTable jurisdictionTable : jurisdictionTables){
			// 功能编号
			String functionno = jurisdictionTable.getFunctionno();
			// 根据功能编号查询功能全部信息
			List<FunctionTable> functionTables = userMainPageService.queryFunByFno(functionno);
			if(functionTables.size() > 0){
				list.add(functionTables.get(0));
			}
		}
		if(list.size() > 0){
			for(FunctionTable functionTable : list){
				if("-1".equals(functionTable.getFunctionfather())){
					// 功能编号
					String functionno = functionTable.getFunctionno();
					// 根据功能列表查询该功能下的子功能
					List<FunctionTable> sunFuncions = new LinkedList<FunctionTable>();
					for(FunctionTable fTable : list){
						if(functionno.equals(fTable.getFunctionfather())){
							sunFuncions.add(fTable);
						}
					}
					if(sunFuncions.size() > 0){
						htmlStr.append("<div class='btn-group'>");
						htmlStr.append("<button type='button' class='btn btn-default dropdown-toggle' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false' style='border:0;background-color:black;'>");
						htmlStr.append("<a href='#'>" + functionTable.getFunctionname() + "</a>");
						htmlStr.append("</button>");
					}else {
						if("消息".equals(functionTable.getFunctionname())){
							htmlStr.append("<div class='btn-group'>");
							htmlStr.append("<button type='button' class='btn btn-default dropdown-toggle' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false' style='border:0;background-color:black;'>");
							htmlStr.append("<a href='#' name='" + functionTable.getFunctionurl() + "' onclick='funClick(this.name)'>" + functionTable.getFunctionname() + "<span id='myInfomation' class='badge' style='color:white;background-color:red;'></span></a>");
							htmlStr.append("</button>");
						}else {
							htmlStr.append("<div class='btn-group'>");
							htmlStr.append("<button type='button' class='btn btn-default dropdown-toggle' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false' style='border:0;background-color:black;'>");
							htmlStr.append("<a href='#' name='" + functionTable.getFunctionurl() + "' onclick='funClick(this.name)'>" + functionTable.getFunctionname() + "</a>");
							htmlStr.append("</button>");
						}
					}
					if(sunFuncions.size() > 0){
						htmlStr.append("<ul class='dropdown-menu'>");
						for(FunctionTable fun : sunFuncions){
							htmlStr.append("<li style='margin-top:5px;'><a href='#' name=" + fun.getFunctionurl() + " onclick='funClick(this.name)'>" + fun.getFunctionname() + "</a></li>");
						}
						htmlStr.append("</ul>");
					}
					htmlStr.append("</div>");
				}
			}
		}
		return htmlStr.toString();
	}

	/**
	 * 点击我收藏的文章功能链接
	 * @param request
	 * @param response
	 */
	@RequestMapping("getCollectionArtcles.do")
	public String getCollectionArtcles(HttpServletRequest request,HttpServletResponse response){
		// 用户编号
		String userno = request.getParameter("userno");
		// 用户文章list
		List<ArticleTable> list = new LinkedList<ArticleTable>();
		// 根据收藏人编号查询该用户所收藏的全部文章
		List<CollectionTable> collectionTables = userMainPageService.queryCollArtsByUserno(userno);
		if(collectionTables.size() > 0){
			for(CollectionTable collectionTable : collectionTables){
				List<ArticleTable> articleTables = userMainPageService.queryArtMsgByAno(collectionTable.getCollectionno());
				if(articleTables.size() > 0){
					list.add(articleTables.get(0));
				}
			}
		}
		if(list.size() > 0){
			// 根据用户文章list拼接为相应的html字符
			String collArtsHtmlStr = getCollArtsHtmlStr(list);
			request.setAttribute("collArtsHtmlStr", collArtsHtmlStr);
		}
		return "teacherJsp/myCollectionArticles";
	}
	
	/**
	 * 我收藏的文章：根据用户文章list拼接为相应的html字符
	 * @param articleTables
	 * @return String
	 */
	private String getCollArtsHtmlStr(List<ArticleTable> articleTables){
		StringBuffer htmlStr = new StringBuffer("");
		for(ArticleTable articleTable : articleTables){
			htmlStr.append("<div style='width:100%;height:150px;border-bottom:#000000 1px dashed;'>");
			htmlStr.append("<div style='height:40px;margin-left:10px;'>");
			htmlStr.append("<a href='#' name='" + articleTable.getArticleno() + "' onclick='readArctile(this.name)' style='font-size:25px;text-decoration:underline;'>" + articleTable.getArticlename() + "</a>");
			htmlStr.append("</div>");
			htmlStr.append("<div style='height:70px;margin-left:10px;'>");
			htmlStr.append("<span style='font-size:25px;'>简介:</span>" + articleTable.getArticlecabstract());
			htmlStr.append("<a name='" + articleTable.getArticleno() + "' onclick='readArctile(this.name)'>(阅读全文)</a>");
			htmlStr.append("</div>");
			htmlStr.append("<div style='height:40px;float:right;font-size:15px;'>");
			htmlStr.append("<span style='font-size:20px;color:blue;'>" + articleTable.getArticlepublisher() + "</span>&nbsp;&nbsp;");
			htmlStr.append("发布于&nbsp;&nbsp;" + articleTable.getArticlepublishtime() + "&nbsp;&nbsp;");
			htmlStr.append("<a name=" + articleTable.getArticleno() + " onclick='comment(this.name)'>评论(" + articleTable.getArticlecomnum() + ")</a>&nbsp;&nbsp;<a name=" + articleTable.getArticleno() + " onclick='collectionArctile(this)'>收藏(" + articleTable.getArticlecollectnum() + ")</a>&nbsp;&nbsp;");
			htmlStr.append("<a name=" + articleTable.getArticleno() + " onclick='report(this.name)'>举报(" + articleTable.getArticlereportnum() + ")</a>&nbsp;&nbsp;<a name=" + articleTable.getArticleno() + " onclick='readArctile(this.name)'>浏览(" + articleTable.getArticlereadnum() + ")</a>");
			htmlStr.append("&nbsp;&nbsp;<a href='#' name=" + articleTable.getArticleno() + "_" + articleTable.getArticlename() + " onclick='cancelCollection(this.name)'>取消收藏</a>&nbsp;&nbsp;");
			htmlStr.append("</div>");
			htmlStr.append("</div>");
		}
		return htmlStr.toString();
	}
	
	/**
	 * 取消收藏
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("cancelArticle.do")
	public void cancelArticle(HttpServletRequest request,HttpServletResponse response) throws IOException{
		PrintWriter printWriter = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		// 文章编号
		String collectionno = request.getParameter("collectionno");
		// 收藏类型
		String collectiontype = request.getParameter("collectiontype");
		// 收藏人
		String collector = request.getParameter("collector");
		jsonObject.append("collArtsHtmlStr", "");
		try {
			// 取消收藏
			userMainPageService.deleteArticleColl(collectionno, collectiontype, collector);
			// 用户文章list
			List<ArticleTable> list = new LinkedList<ArticleTable>();
			// 根据收藏人编号查询该用户所收藏的全部文章
			List<CollectionTable> collectionTables = userMainPageService.queryCollArtsByUserno(collector);
			if(collectionTables.size() > 0){
				for(CollectionTable collectionTable : collectionTables){
					List<ArticleTable> articleTables = userMainPageService.queryArtMsgByAno(collectionTable.getCollectionno());
					if(articleTables.size() > 0){
						list.add(articleTables.get(0));
					}
				}
			}
			if(list.size() > 0){
				// 根据用户文章list拼接为相应的html字符
				String collArtsHtmlStr = getCollArtsHtmlStr(list);
				jsonObject.append("collArtsHtmlStr", collArtsHtmlStr);
			}
		} catch (Exception e) {
			jsonObject.append("msg", "取消收藏失败");
			e.printStackTrace();
		}
		printWriter.write(jsonObject.toString());
		printWriter.flush();
	}

	/**
	 * 点击我发布的文章功能链接
	 * @param request
	 * @param response
	 */
	@RequestMapping("getMyArtcles.do")
	public String getMyArtcles(HttpServletRequest request,HttpServletResponse response){
		// 用户编号
		String userno = request.getParameter("userno");
		// 根据用户编号查询用户所发布的文章信息
		List<ArticleTable> list = userMainPageService.queryArtMsgByUserno(userno);
		if(list.size() > 0){
			// 根据用户文章list拼接为相应的html字符
			String collArtsHtmlStr = getMyArtsHtmlStr(list);
			request.setAttribute("collArtsHtmlStr", collArtsHtmlStr);
		}
		return "teacherJsp/myCollectionArticles";
	}
	
	/**
	 * 我发布的文章：根据用户文章list拼接为相应的html字符
	 * @param articleTables
	 * @return String
	 */
	private String getMyArtsHtmlStr(List<ArticleTable> articleTables){
		StringBuffer htmlStr = new StringBuffer("");
		for(ArticleTable articleTable : articleTables){
			htmlStr.append("<div style='width:100%;height:150px;border-bottom:#000000 1px dashed;'>");
			htmlStr.append("<div style='height:40px;margin-left:10px;'>");
			htmlStr.append("<a href='#' name='" + articleTable.getArticleno() + "' onclick='readArctile(this.name)' style='font-size:25px;text-decoration:underline;'>" + articleTable.getArticlename() + "</a>");
			htmlStr.append("</div>");
			htmlStr.append("<div style='height:70px;margin-left:10px;'>");
			htmlStr.append("<span style='font-size:25px;'>简介:</span>" + articleTable.getArticlecabstract());
			htmlStr.append("<a name='" + articleTable.getArticleno() + "' onclick='readArctile(this.name)'>(阅读全文)</a>");
			htmlStr.append("</div>");
			htmlStr.append("<div style='height:40px;float:right;font-size:15px;'>");
			htmlStr.append("<span style='font-size:20px;color:blue;'>" + articleTable.getArticlepublisher() + "</span>&nbsp;&nbsp;");
			htmlStr.append("发布于&nbsp;&nbsp;" + articleTable.getArticlepublishtime() + "&nbsp;&nbsp;");
			htmlStr.append("<a name=" + articleTable.getArticleno() + " onclick='comment(this.name)'>评论(" + articleTable.getArticlecomnum() + ")</a>&nbsp;&nbsp;<a name=" + articleTable.getArticleno() + " onclick='collectionArctile(this)'>收藏(" + articleTable.getArticlecollectnum() + ")</a>&nbsp;&nbsp;");
			htmlStr.append("<a name=" + articleTable.getArticleno() + " onclick='report(this.name)'>举报(" + articleTable.getArticlereportnum() + ")</a>&nbsp;&nbsp;<a name=" + articleTable.getArticleno() + " onclick='readArctile(this.name)'>浏览(" + articleTable.getArticlereadnum() + ")</a>");
			htmlStr.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a name=" + articleTable.getArticleno() + " onclick='updateArtcile(this.name)'>编辑</a>&nbsp;&nbsp;&nbsp;");
			htmlStr.append("<a name=" + articleTable.getArticleno() + "_" + articleTable.getArticlename() + " onclick='deleteArticle(this.name)'>删除</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
			htmlStr.append("</div>");
			htmlStr.append("</div>");
		}
		return htmlStr.toString();
	}
	
	
	/**
	 * 删除我发布的文章
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("getMyCourseArt.do")
	public void getMyCourseArt(HttpServletRequest request,HttpServletResponse response) throws IOException{
		PrintWriter printWriter = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		// 用户编号
		String userno = request.getParameter("userno");
		StringBuffer myCourseArtHtmlStr = new StringBuffer();
		List<String> typelist = userMainPageService.quertArtByUserno(userno);
		if(typelist.size() > 0){
			HashMap<String, Integer> typeMap = new HashMap<String, Integer>();
			String[] types = typelist.toString().replaceAll(" ", "").replaceAll("\\[", "").replaceAll("\\]", "").split(",");
			for(int i = 0,len = types.length; i < len; i++){
				String type = types[i];
				if(typeMap.get(type) != null){
					typeMap.put(type, typeMap.get(type) + 1);
				}else {
					typeMap.put(type, 1);
				}
			}
			Iterator iterator = typeMap.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry entry = (Map.Entry)iterator.next();
				String typeno = (String) entry.getKey();
				myCourseArtHtmlStr.append("<a name='" + entry.getKey() + "' onclick='typeClick(this.name)'>" + getArttypeByTypeno(typeno) + "(" + entry.getValue() + ")</a><br>");
			}
		}
		jsonObject.append("myCourseArtHtmlStr", myCourseArtHtmlStr.toString());
		printWriter.write(jsonObject.toString());
		printWriter.flush();
	}
	
	/**
	 * 通过课程文章类型编号查询课程文章类型信息
	 * @param typeno
	 * @return
	 */
	private String getArttypeByTypeno(String typeno){
		List<ArticletypeTable> articletypeTables = userMainPageService.queryArttypeByTypeno(typeno);
		if(articletypeTables.size() > 0){
			return articletypeTables.get(0).getArticletypename();
		}else{
			return "";
		}
	}
	
	/**
	 * 删除我发布的文章
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("deleteArticle.do")
	public void deleteArticle(HttpServletRequest request,HttpServletResponse response) throws IOException{
		PrintWriter printWriter = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		// 文章编号
		String articleno = request.getParameter("articleno");
		// 用户编号
		String userno = request.getParameter("userno");
		jsonObject.append("collArtsHtmlStr", "");
		try {
			// 删除我发布的文章及相关信息
			userMainPageService.deleteArticle(articleno);
			// 根据用户编号查询用户所发布的文章信息
			List<ArticleTable> list = userMainPageService.queryArtMsgByUserno(userno);
			if(list.size() > 0){
				// 根据用户文章list拼接为相应的html字符
				String collArtsHtmlStr = getMyArtsHtmlStr(list);
				jsonObject.append("collArtsHtmlStr", collArtsHtmlStr);
			}
		} catch (Exception e) {
			jsonObject.append("msg", "删除失败");
			e.printStackTrace();
		}
		printWriter.write(jsonObject.toString());
		printWriter.flush();
	}
	
	/**
	 * 发布文章
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("addArtcles.do")
	public void addArtcles(HttpServletRequest request,HttpServletResponse response) throws IOException{
		PrintWriter printWriter = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		// 用户编号
		String userno = request.getParameter("userno");
		// 用户名
		String username = request.getParameter("username");
		// 文章标题
		String articlename = request.getParameter("articlename");
		// 文章简介
		String articlecabstract = request.getParameter("articlecabstract");
		// 文章内容
		String articlecontent = request.getParameter("articlecontent");
		// 发布文章类型
		String types = request.getParameter("types");
		// 文章编号
		String articleno = "WZ" + System.currentTimeMillis();
		ArticleTable articleTable = new ArticleTable();
		articleTable.setArticleno(articleno);
		articleTable.setArticlepublisherno(userno);
		articleTable.setArticlepublisher(username);
		articleTable.setArticlename(articlename);
		articleTable.setArticlecabstract(articlecabstract);
		articleTable.setArticlecontent(articlecontent);
		articleTable.setArticletypes(types);
		articleTable.setArticlecollectnum(0);
		articleTable.setArticlecomnum(0);
		articleTable.setArticlereadnum(0);
		articleTable.setArticlereportnum(0);
		try {
			userMainPageService.insertArticle(articleTable);
			jsonObject.append("msg", "发布成功");
		} catch (Exception e) {
			jsonObject.append("msg", "发布失败");
			e.printStackTrace();
		}
		printWriter.append(jsonObject.toString());
		printWriter.flush();
	}
	
	/**
	 * 修改文章信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("updateArtcleMsg.do")
	public void updateArtcleMsg(HttpServletRequest request,HttpServletResponse response) throws IOException{
		PrintWriter printWriter = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		// 文章编号
		String articleno = request.getParameter("articleno");
		// 文章标题
		String articlename = request.getParameter("articlename");
		// 文章简介
		String articlecabstract = request.getParameter("articlecabstract");
		// 文章内容
		String articlecontent = request.getParameter("articlecontent");
		// 发布文章类型
		String types = request.getParameter("types");
		ArticleTable articleTable = new ArticleTable();
		articleTable.setArticleno(articleno);
		articleTable.setArticlename(articlename);
		articleTable.setArticlecabstract(articlecabstract);
		articleTable.setArticlecontent(articlecontent);
		articleTable.setArticletypes(types);
		try {
			userMainPageService.updateArticleMsg(articleTable);
			jsonObject.append("msg", "修改成功");
		} catch (Exception e) {
			jsonObject.append("msg", "修改失败");
			e.printStackTrace();
		}
		printWriter.append(jsonObject.toString());
		printWriter.flush();
	}
	
	/**
	 * 编辑我发布的文章信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("updateArticles.do")
	public String updateArtcles(HttpServletRequest request,HttpServletResponse response){
		// 文章编号
		String articleno = request.getParameter("articleno");
		List<ArticleTable> articleTables = userMainPageService.queryArtMsgByAno(articleno);
		// 加载发布文章类型
		List<ArticletypeTable> articletypeTables = userMainPageService.queryArttypes();
		StringBuffer htmlStr = new StringBuffer();
		if(articleTables.size() > 0){
			ArticleTable articleTable = articleTables.get(0);
			request.setAttribute("articlename", articleTable.getArticlename());
			request.setAttribute("articlecabstract", articleTable.getArticlecabstract());
			request.setAttribute("articlecontent", articleTable.getArticlecontent());
			// 文章类型
			String articletypes = articleTable.getArticletypes();
			if(articletypeTables.size() > 0){
				for(ArticletypeTable articletypeTable : articletypeTables){
					if(articletypes.indexOf(articletypeTable.getArticletypeid()) > -1){
						htmlStr.append("<input type='checkbox' name='typeArt' checked='checked' style='margin-left:20px;' "
								+ "value='" + articletypeTable.getArticletypeid() + "'>&nbsp;&nbsp;"
								+ "<span style='font-size:16px;font-style:italic;'>" + articletypeTable.getArticletypename() + "</span>");
					}else {
						htmlStr.append("<input type='checkbox' name='typeArt' style='margin-left:20px;' "
								+ "value='" + articletypeTable.getArticletypeid() + "'>&nbsp;&nbsp;"
								+ "<span style='font-size:16px;font-style:italic;'>" + articletypeTable.getArticletypename() + "</span>");
					}
				}
			}
		}else {
			request.setAttribute("articlename", "");
			request.setAttribute("articlecabstract", "");
			request.setAttribute("articlecontent", "");
			if(articletypeTables.size() > 0){
				for(ArticletypeTable articletypeTable : articletypeTables){
					htmlStr.append("<input type='checkbox' name='typeArt' checked='checked' style='margin-left:20px;' "
							+ "value='" + articletypeTable.getArticletypeid() + "'>&nbsp;&nbsp;"
							+ "<span style='font-size:16px;font-style:italic;'>" + articletypeTable.getArticletypename() + "</span>");
				}
			}
		}
		request.setAttribute("articleno", articleno);
		request.setAttribute("articletypesHtmlStr", htmlStr.toString());
		request.setAttribute("signUpdate", "");
		request.setAttribute("signSave", "none");
		return "teacherJsp/updateAndAddArticleMsg";
	}
	
	/**
	 * 进入发布文章界面
	 * @param request
	 * @param response
	 */
	@RequestMapping("getAddArtclesPage.do")
	public String getAddArtclesPage(HttpServletRequest request,HttpServletResponse response){
		// 加载发布文章类型
		List<ArticletypeTable> articletypeTables = userMainPageService.queryArttypes();
		StringBuffer htmlStr = new StringBuffer();
		if(articletypeTables.size() > 0){
			for(ArticletypeTable articletypeTable : articletypeTables){
				htmlStr.append("<input type='checkbox' name='typeArt' style='margin-left:20px;' value='" + articletypeTable.getArticletypeid() + "'>&nbsp;&nbsp;"
						+ "<span style='font-size:16px;font-style:italic;'>" + articletypeTable.getArticletypename() + "</span>");
			}
		}
		request.setAttribute("articletypesHtmlStr", htmlStr.toString());
		request.setAttribute("signUpdate", "none");
		request.setAttribute("signSave", "");
		return "teacherJsp/updateAndAddArticleMsg";
	}
	
	/**
	 * 进入提问界面
	 * @param request
	 * @param response
	 */
	@RequestMapping("getAddQuestionPage.do")
	public String getAddQuestionPage(HttpServletRequest request,HttpServletResponse response){
		// 查询所有问题分类
		List<QuestiontypeTable> questiontypeTables = userMainPageService.quertQuestiontypes();
		StringBuffer questiontypeHtmlStr = new StringBuffer();
		if(questiontypeTables.size() > 0){
			int count = 0;
			for(QuestiontypeTable questiontypeTable : questiontypeTables){
				count++;
				questiontypeHtmlStr.append("<input type='radio' name='qtype' value='" + questiontypeTable.getQuestiontypeno() + "_" + questiontypeTable.getQuestiontypename() + "' />" + questiontypeTable.getQuestiontypename() + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				if(count%6 == 0){
					questiontypeHtmlStr.append("<br>");
				}
			}
		}
		request.setAttribute("questiontypeHtmlStr", questiontypeHtmlStr);
		return "teacherJsp/addQuestion";
	}
	
	/**
	 * 进入课程问题吧页面
	 * @param request
	 * @param response
	 */
	@RequestMapping("getAcadExcCenterPage.do")
	public String getAcadExcCenterPage(HttpServletRequest request,HttpServletResponse response){
		// 查询所有问题分类
		List<QuestiontypeTable> questiontypeTables = userMainPageService.quertQuestiontypes();
		StringBuffer questionHtmlStr = new StringBuffer();
		if(questiontypeTables.size() > 0){
			questionHtmlStr.append("<div class='list-group'>");
			for(QuestiontypeTable questiontypeTable : questiontypeTables){
				questionHtmlStr.append("<a id=" + questiontypeTable.getQuestiontypeno() + " class='list-group-item' onclick='typeClick(this)'>" + questiontypeTable.getQuestiontypename() + "</a>");
			}
			questionHtmlStr.append("</div>");
		}
		request.setAttribute("questionHtmlStr", questionHtmlStr);
		// 查询最新5条问题
		List<QuestionTable> newQuestions = userMainPageService.queryNewQuestion();
		if(newQuestions.size() > 0){
			String newQuestionsHtmlStr = getQuestionHtmlStr(newQuestions,false);
			request.setAttribute("newQuestionsHtmlStr", newQuestionsHtmlStr);
		}
		// 查询评论数最高的15条记录
		List<QuestionTable> commentnumQues = userMainPageService.queryCommentNumQuestion();
		if(commentnumQues.size() > 0){
			String commentnumQuesHtmlStr = getQuestionHtmlStr(commentnumQues,true);
			request.setAttribute("commentnumQuesHtmlStr", commentnumQuesHtmlStr);
		}
		return "teacherJsp/academicExchangeCenter";
	}
	
	/**
	 * 进行问题拼接为html字符串
	 * @param questionTables
	 * @return
	 */
	private String getQuestionHtmlStr(List<QuestionTable> questionTables,Boolean sign){
		StringBuffer htmlStr = new StringBuffer();
		htmlStr.append("<ul>");
		int count = 0;
		for(QuestionTable questionTable : questionTables){
			htmlStr.append("<li style='margin-top:10px;'><a id='" + questionTable.getQuestionno() + "' onclick='questionClick(this)' >" + questionTable.getQuestionname() + "</a><a id=" + questionTable.getQuestiontypeno() + " onclick='typeClick(this)' style='float:right;margin-right:20px;'>" + questionTable.getQuestiontypename() + "</a></li>");
			count ++;
			if(count%5 == 0 && sign){
				htmlStr.append("<hr>");
			}
		}
		htmlStr.append("</ul>");
		return htmlStr.toString();
	}

	/**
	 * 处理提问请求(新增提问)
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("insertQuestion.do")
	public void insertQuestion(HttpServletRequest request,HttpServletResponse response) throws IOException{
		PrintWriter printWriter = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		QuestionTable questionTable = new QuestionTable();
		questionTable.setQuestionno("WT" + System.currentTimeMillis());
		questionTable.setQuestionpublisherno(request.getParameter("userno"));
		questionTable.setQuestionpublisher(request.getParameter("username"));
		questionTable.setQuestioncontent(request.getParameter("qconcant"));
		questionTable.setQuestionname(request.getParameter("qname"));
		questionTable.setQuestiontypeno(request.getParameter("qtype"));
		questionTable.setQuestiontypename(request.getParameter("qtypename"));
		questionTable.setQuestioncommentnum(0);
		try {
			userMainPageService.insertQuestion(questionTable);
			jsonObject.append("msg", "提问成功");
		} catch (Exception e) {
			jsonObject.append("msg", "提问失败");
			e.printStackTrace();
		}
		printWriter.append(jsonObject.toString());
		printWriter.flush();
	}
	
	/**
	 * 进入点击问题类型页面
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("getQuetypePage.do")
	public String getQuetypePage(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		// 查询所有问题分类
		List<QuestiontypeTable> questiontypeTables = userMainPageService.quertQuestiontypes();
		StringBuffer questionHtmlStr = new StringBuffer();
		if(questiontypeTables.size() > 0){
			questionHtmlStr.append("<div class='list-group'>");
			for(QuestiontypeTable questiontypeTable : questiontypeTables){
				questionHtmlStr.append("<a id=" + questiontypeTable.getQuestiontypeno() + " class='list-group-item' onclick='typeClick(this)'>" + questiontypeTable.getQuestiontypename() + "</a>");
			}
			questionHtmlStr.append("</div>");
		}
		request.setAttribute("questionHtmlStr", questionHtmlStr);
		// 问题类型编号
		String typeno = request.getParameter("typeid");
		// 问题类型名称
		String typename = request.getParameter("typename");
		request.setAttribute("typeno", typeno);
		request.setAttribute("typename", typename);
		
		// 根据问题类型查询该类型本月评论数最高的问题(取前3条数据)
		List<QuestionTable> monthComms = userMainPageService.queryMounthComnumQue(typeno);
		String monthCommsHtmlStr = getQuestionHtmlStr(monthComms);
		request.setAttribute("monthCommsHtmlStr", monthCommsHtmlStr);
		
		// 根据问题类型查询该类型本年评论数最高的问题(取前3条数据)
		List<QuestionTable> yearComms = userMainPageService.queryYearComnumQue(typeno);
		String yearCommsHtmlStr = getQuestionHtmlStr(yearComms);
		request.setAttribute("yearCommsHtmlStr", yearCommsHtmlStr);
		
		// 根据问题类型查询该类型下全部问题信息(按问题发表时间降序排序)
		Integer start = 0;// 分页开始
		Integer stop = 15;// 分页开始
		List<QuestionTable> questionTables1 = userMainPageService.queryQueByQtype(typeno,start, stop);
		String questionTables1HtmlStr = getQuestionHtmlStr(questionTables1);
		request.setAttribute("questionTables1HtmlStr", questionTables1HtmlStr);
		
		// 根据问题类型查询该类型下评论数最高的问题(按评论降序排序)
		List<QuestionTable> questionTables2 = userMainPageService.queryComnumQueByQtype(typeno,start, stop);
		String questionTables2HtmlStr = getQuestionHtmlStr(questionTables2);
		request.setAttribute("questionTables2HtmlStr", questionTables2HtmlStr);
		return "teacherJsp/questiontypePage";
	}
	
	/**
	 * 对问题显示（问题/作者/评论/发表时间）进行拼接
	 * @param questionTables
	 * @return
	 */
	private String getQuestionHtmlStr(List<QuestionTable> questionTables){
		StringBuffer htmlStr = new StringBuffer();
		if(questionTables.size() > 0){
			for(QuestionTable questionTable : questionTables){
				htmlStr.append("<span style='margin-top:10px;'>");
				htmlStr.append("<a href='getQuestionPage.do?questionno=" + questionTable.getQuestionno() + "'>" + questionTable.getQuestionname() + "</a>");
				htmlStr.append("<a style='float:right;margin-right:20px;'>" + questionTable.getQuestiontime() + "</a>");
				htmlStr.append("<a style='float:right;margin-right:40px;'>" + questionTable.getQuestioncommentnum() + "</a>");
				htmlStr.append("<a style='float:right;margin-right:40px;'>" + questionTable.getQuestionpublisher() + "</a>");
				htmlStr.append("</span><hr>");
			}
		}
		return htmlStr.toString();
	}
	
	/**
	 * 返回问题详情页面
	 * @param request
	 * @param response
	 * @throws  
	 */
	@RequestMapping("getQuestionPage.do")
	public String getQuestionPage(HttpServletRequest request,HttpServletResponse response){
		// 问题编号
		String questionno = request.getParameter("questionno");
		// 根据问题编号查询问题详情
		List<QuestionTable> questionTables = userMainPageService.quertQuestionByQno(questionno);
		if(questionTables.size() > 0){
			QuestionTable questionTable = questionTables.get(0);
			request.setAttribute("questionno", questionTable.getQuestionno());
			request.setAttribute("questionname", questionTable.getQuestionname());
			request.setAttribute("questionpublisher", questionTable.getQuestionpublisher());
			request.setAttribute("questiontime", questionTable.getQuestiontime());
			request.setAttribute("questioncommentnum", questionTable.getQuestioncommentnum());
			request.setAttribute("questioncontent", questionTable.getQuestioncontent());
		}
		// 根据问题编号查询问题相关评论信息
		List<AnswerTable> answerTables = userMainPageService.quertAnswersByQno(questionno);
		// 用户编号
		String userno = (String)request.getSession().getAttribute("userno");
		if(userno == null){
			userno = "";
		}
		String answerHtmlStr = getAnswerHtmlStr(answerTables, userno);
		request.setAttribute("answerHtmlStr", answerHtmlStr);
		return "teacherJsp/questionPage";
	}
	
	/**
	 * @throws IOException 
	 * 问题评论
	 * @param request
	 * @param response
	 * @throws  
	 */
	@RequestMapping("commentQuestion.do")
	public void commentQuestion(HttpServletRequest request,HttpServletResponse response) throws IOException{
		PrintWriter printWriter = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		// 评论人
		String userno = request.getParameter("userno");
		// 评论人编号
		String username = request.getParameter("username");
		// 评论内容
		String commentConcent = request.getParameter("commentConcent");
		// 问题编号
		String questionno = request.getParameter("questionno");
		// 评论编号
		String answerno = "HD"+System.currentTimeMillis();
		
		AnswerTable answerTable = new AnswerTable();
		answerTable.setAnswerno(answerno);
		answerTable.setQuestionno(questionno);
		answerTable.setAnswerner(username);
		answerTable.setAnswernerno(userno);
		answerTable.setAnswercontent(commentConcent);
		InfoTable infoTable = new InfoTable();
		// 消息编号
		String infono = "XX" + System.currentTimeMillis();
		infoTable.setInfono(infono);
		infoTable.setInfo(answerno);
		List<QuestionTable> list = userMainPageService.quertQuestionByQno(questionno);
		if(list.size() > 0){
			infoTable.setInfouserno(list.get(0).getQuestionpublisherno());
		}
		infoTable.setInfotype("1"); // 消息类型 1:评论，2：回复
		infoTable.setInforeadsign("0"); // 是否已读：  0 未读  1 已读
		try {
			userMainPageService.insertAnswer(answerTable);
			jsonObject.append("msg", "发表成功");
			// 进行问题评论加1
			userMainPageService.updateQuesnumAdd1(questionno);
			// 新增消息表
			userMainPageService.insertInfo(infoTable);
		} catch (Exception e) {
			jsonObject.append("msg", "发表失败");
			e.printStackTrace();
		}
		// 根据问题编号查询问题详情
		List<QuestionTable> questionTables = userMainPageService.quertQuestionByQno(questionno);
		if(questionTables.size() > 0){
			QuestionTable questionTable = questionTables.get(0);
			jsonObject.append("questionno", questionTable.getQuestionno());
			jsonObject.append("questionname", questionTable.getQuestionname());
			jsonObject.append("questionpublisher", questionTable.getQuestionpublisher());
			jsonObject.append("questiontime", questionTable.getQuestiontime());
			jsonObject.append("questioncommentnum", questionTable.getQuestioncommentnum());
			jsonObject.append("questioncontent", questionTable.getQuestioncontent());
		}
		// 根据问题编号查询问题相关评论信息
		List<AnswerTable> answerTables = userMainPageService.quertAnswersByQno(questionno);
		String answerHtmlStr = getAnswerHtmlStr(answerTables,userno);
		jsonObject.append("answerHtmlStr", answerHtmlStr);
		printWriter.write(jsonObject.toString());
		printWriter.flush();
	}
	
	/**
	 * 对问题相关评论信息进行html字符串拼接
	 * @param answerTables
	 * @return
	 */
	private String getAnswerHtmlStr(List<AnswerTable> answerTables,String userno){
		StringBuffer htmlStr = new StringBuffer();
		if(answerTables.size() > 0){
			for(AnswerTable answerTable : answerTables){
				htmlStr.append("<div style='overflow-y:auto;'>");
				htmlStr.append(answerTable.getAnswerner() + ":" + answerTable.getAnswercontent() + "<br>" + answerTable.getAnswertime() + "&nbsp;&nbsp;&nbsp;");
				htmlStr.append("<a name='" + answerTable.getAnswerno() + "_" + answerTable.getAnswernerno() + "_" + answerTable.getAnswerner() + "' onclick='replay(this)'>回复</a><br>");
				// 根据评论编号进行改评论相关回复查询
				List<ReplayTable> replayTables = userMainPageService.queryReplayByAnswerno(answerTable.getAnswerno());
				htmlStr.append("<div style='margin-left:30px;'>" + getReplayHtmlStr(replayTables,userno) + "</div>");
				htmlStr.append("<div id='" + answerTable.getAnswerno() + "' style='overflow-y:auto;'></div>");
				htmlStr.append("</div><hr>");
			}
		}
		return htmlStr.toString();
	}
	
	/**
	 * 对评论回复list进行html字符串拼接
	 * @param replayTables
	 * @return
	 */
	private String getReplayHtmlStr(List<ReplayTable> replayTables,String userno){
		StringBuffer htmlStr = new StringBuffer();
		if(replayTables.size() > 0){
			for(ReplayTable replayTable : replayTables){
				htmlStr.append(replayTable.getReplaypname() + "&nbsp;回复&nbsp;" + replayTable.getAnswerpname() + "&nbsp;:&nbsp;" + replayTable.getReplayconcant());
				htmlStr.append("<br>" + replayTable.getReplaytime() + "&nbsp;&nbsp;&nbsp;");
				if(userno.equals(replayTable.getReplaypno())){
					htmlStr.append("<a name='" + replayTable.getAnswerno() + "_" + replayTable.getAnswerpno() + "_" + replayTable.getAnswerpname() + "' onclick='replay(this)'>回复</a><br>");
				}else {
					htmlStr.append("<a name='" + replayTable.getAnswerno() + "_" + replayTable.getReplaypno() + "_" + replayTable.getReplaypname() + "' onclick='replay(this)'>回复</a><br>");
				}
			}
		}
		return htmlStr.toString();
	}
	
	/**
	 * 点击我的课程文章列表链接请求
	 * @param request
	 * @param response
	 */
	@RequestMapping("getMyArtclesByTypeno.do")
	public String getMyArtclesByTypeno(HttpServletRequest request,HttpServletResponse response){
		// 用户编号
		String userno = request.getParameter("userno");
		// 课程文章类型编号
		String typeid = request.getParameter("typeid");
		// 根据用户编号、课程文章类型编号查询用户所发布的文章信息
		List<ArticleTable> list = userMainPageService.queryArtclesByTypeno(userno,typeid);
		if(list.size() > 0){
			// 根据用户文章list拼接为相应的html字符
			String collArtsHtmlStr = getMyArtsHtmlStr(list);
			request.setAttribute("collArtsHtmlStr", collArtsHtmlStr);
		}
		return "teacherJsp/myCollectionArticles";
	}
	
	/**
	 * 回复
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("saveReplay.do")
	public void saveReplay(HttpServletRequest request,HttpServletResponse response) throws IOException{
		PrintWriter printWriter = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		// 问题编号
		String questionno = request.getParameter("questionno");
		// 回复人编号
		String replaypno = request.getParameter("userno");
		// 回复人姓名
		String replaypname = request.getParameter("username");
		// 回复评论编号
		String answerno = request.getParameter("answerno");
		// 要回复人编号
		String answerpno = request.getParameter("replayPno");
		// 要回复人姓名
		String answerpname = request.getParameter("replayPname");
		// 回复内容
		String replayconcant = request.getParameter("replayconcant");
		// 回复编号
		String replayno = "HF" + System.currentTimeMillis();
		ReplayTable replayTable = new ReplayTable();
		replayTable.setReplayno(replayno);
		replayTable.setReplaypno(replaypno);
		replayTable.setReplaypname(replaypname);
		replayTable.setAnswerno(answerno);
		replayTable.setAnswerpno(answerpno);
		replayTable.setAnswerpname(answerpname);
		replayTable.setReplayconcant(replayconcant);
		InfoTable infoTable = new InfoTable();
		// 消息编号
		String infono = "XX" + System.currentTimeMillis();
		infoTable.setInfono(infono);
		infoTable.setInfo(replayno);
		infoTable.setInfouserno(answerpno);
		infoTable.setInfotype("2"); // 消息类型 1:评论，2：回复
		infoTable.setInforeadsign("0"); // 是否已读：  0 未读  1 已读
		try {
			// 新增回复表
			userMainPageService.insertReplay(replayTable);
			// 新增消息表
			userMainPageService.insertInfo(infoTable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 根据问题编号查询问题相关评论信息
		List<AnswerTable> answerTables = userMainPageService.quertAnswersByQno(questionno);
		String answerHtmlStr = getAnswerHtmlStr(answerTables,answerpno);
		jsonObject.append("answerHtmlStr", answerHtmlStr);
		printWriter.write(jsonObject.toString());
		printWriter.flush();
	}
	
	/**
	 * 查询个人消息未读个数
	 * @throws IOException 
	 */
	@RequestMapping("queryMyInfonums.do")
	public void queryMyInfonums(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter printWriter = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		// 用户编号
		String userno = request.getParameter("userno");
		List<InfoTable> infoTables = userMainPageService.queryInfonumByUserno(userno);
		if(infoTables.size() > 0){
			jsonObject.append("infonum", infoTables.size());
		}else {
			jsonObject.append("infonum", "");
		}
		printWriter.write(jsonObject.toString());
		printWriter.flush();
	}
	
	/**
	 * 进入我的消息中心页面
	 * @throws IOException 
	 */
	@RequestMapping("getInfoPage.do")
	public String getInfoPage(HttpServletRequest request, HttpServletResponse response){
		// 用户编号
		String userno = (String) request.getSession().getAttribute("userno");
		List<InfoTable> infoTables = userMainPageService.queryInfoByUserno(userno);
		if(infoTables.size() > 0){
			// 将消息拼接为html
			String infomationHtmlStr = getInfoHtmlStr(infoTables);
			request.setAttribute("infomationHtmlStr", infomationHtmlStr);
		}
		return "teacherJsp/infoPage";
	}
	
	/**
	 * 将消息拼接为html
	 * @param infoTables
	 * @return
	 */
	private String getInfoHtmlStr(List<InfoTable> infoTables){
		StringBuffer htmlStr = new StringBuffer();
		for(InfoTable infoTable : infoTables){
			String infono = infoTable.getInfono();
			String info = infoTable.getInfo();
			String readsign = infoTable.getInforeadsign();
			String infotype = infoTable.getInfotype();
			if("1".equals(infotype)){// 1 评论
				List<AnswerTable> answerTables = userMainPageService.queryAnwserByAno(info);
				if(answerTables.size() > 0){
					AnswerTable answerTable = answerTables.get(0);
					htmlStr.append("<tr>");
					htmlStr.append("<td>" + answerTable.getAnswerner() + "</td>");
					htmlStr.append("<td><div><a style='text-decoration:underline;' name='" + answerTable.getAnswerno() + "_" + infono + "' onclick='infoAnswer(this.name)'>" + answerTable.getAnswercontent() + "</a></div></td>");
					if("1".equals(readsign)){
						htmlStr.append("<td>已读</td>");
					}else if("0".equals(readsign)){
						htmlStr.append("<td style='color:red;'>未读</td>");
					}else {
						htmlStr.append("<td></td>");
					}
					htmlStr.append("<td>" + answerTable.getAnswertime() + "</td>");
					htmlStr.append("<td align='center'>");
					htmlStr.append("<a style='color:red;' name=" + infono + " onclick='deleteAnswer(this.name)'>x</a>&nbsp;&nbsp;");
					htmlStr.append("<input type='checkbox' name='cbInfo' value='" + infono + "' onclick='infoCbClick()' />");
					htmlStr.append("</td>");
					htmlStr.append("</tr>");
				}
			}else if("2".equals(infotype)){// 2 回复
				List<ReplayTable> replayTables = userMainPageService.queryReplayByRno(info);
				if(replayTables.size() > 0){
					ReplayTable replayTable = replayTables.get(0);
					htmlStr.append("<tr>");
					htmlStr.append("<td>" + replayTable.getReplaypname() + "</td>");
					htmlStr.append("<td><a style='text-decoration:underline;' name='" + replayTable.getReplayno() + "_" + infono + "' onclick='infoReplay(this.name)'>" + replayTable.getReplayconcant() + "</a></td>");
					if("1".equals(readsign)){
						htmlStr.append("<td>已读</td>");
					}else if("0".equals(readsign)){
						htmlStr.append("<td style='color:red;'>未读</td>");
					}else {
						htmlStr.append("<td></td>");
					}
					htmlStr.append("<td>" + replayTable.getReplaytime() + "</td>");
					htmlStr.append("<td align='center'>");
					htmlStr.append("<a style='color:red;' name=" + infono + " onclick='deleteAnswer(this.name)'>x</a>&nbsp;&nbsp;");
					htmlStr.append("<input type='checkbox' name='cbInfo' value='" + infono + "' onclick='infoCbClick()' />");
					htmlStr.append("</td>");
					htmlStr.append("</tr>");
				}
			}
		}
		return htmlStr.toString();
	}
	
	/**
	 * 对消息标记已读
	 * @throws IOException 
	 */
	@RequestMapping("markread.do")
	public void markread(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter printWriter = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		// 用户编号
		String userno = request.getParameter("userno");
		// 消息编号
		String infonoStr = request.getParameter("infonos");
		String[] infonos = infonoStr.split(",");
		try {
			userMainPageService.updateInfoReadsign(infonos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonObject.append("infomationHtmlStr", "");
		if("".equals(userno) || userno == null){
			
		}else {
			List<InfoTable> infoTables = userMainPageService.queryInfoByUserno(userno);
			if(infoTables.size() > 0){
				// 将消息拼接为html
				String infomationHtmlStr = getInfoHtmlStr(infoTables);
				jsonObject.append("infomationHtmlStr", infomationHtmlStr);
			}
		}
		printWriter.append(jsonObject.toString());
		printWriter.flush();
	}
	
	/**
	 * 对消息删除选中
	 * @throws IOException 
	 */
	@RequestMapping("deleteCb.do")
	public void deleteCb(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter printWriter = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		// 用户编号
		String userno = request.getParameter("userno");
		// 消息编号
		String infonoStr = request.getParameter("infonos");
		String[] infonos = infonoStr.split(",");
		try {
			userMainPageService.deleteInfoByInfono(infonos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonObject.append("infomationHtmlStr", "");
		if("".equals(userno) || userno == null){
			
		}else {
			List<InfoTable> infoTables = userMainPageService.queryInfoByUserno(userno);
			if(infoTables.size() > 0){
				// 将消息拼接为html
				String infomationHtmlStr = getInfoHtmlStr(infoTables);
				jsonObject.append("infomationHtmlStr", infomationHtmlStr);
			}
		}
		printWriter.append(jsonObject.toString());
		printWriter.flush();
	}
	
	/**
	 * 点击消息中的评论类型消息记录
	 * @param request
	 * @param response
	 * @throws  
	 */
	@RequestMapping("infoAnswer.do")
	public String infoAnswer(HttpServletRequest request,HttpServletResponse response){
		// 消息编号
		String infono = request.getParameter("infono");
		String[] infonos = {infono};
		try {
			userMainPageService.updateInfoReadsign(infonos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 评论问题编号
		String answerno = request.getParameter("answerno");
		List<AnswerTable> answerTables = userMainPageService.queryAnwserByAno(answerno);
		if(answerTables.size() > 0){
			AnswerTable answerTable = answerTables.get(0);
			// 问题编号
			String questionno = answerTable.getQuestionno();
			// 根据问题编号查询问题详情
			List<QuestionTable> questionTables = userMainPageService.quertQuestionByQno(questionno);
			if(questionTables.size() > 0){
				QuestionTable questionTable = questionTables.get(0);
				request.setAttribute("questionno", questionTable.getQuestionno());
				request.setAttribute("questionname", questionTable.getQuestionname());
				request.setAttribute("questionpublisher", questionTable.getQuestionpublisher());
				request.setAttribute("questiontime", questionTable.getQuestiontime());
				request.setAttribute("questioncommentnum", questionTable.getQuestioncommentnum());
				request.setAttribute("questioncontent", questionTable.getQuestioncontent());
			}
			StringBuffer htmlStr = new StringBuffer();
			htmlStr.append("<div style='overflow-y:auto;'>");
			htmlStr.append(answerTable.getAnswerner() + ":" + answerTable.getAnswercontent() + "<br>" + answerTable.getAnswertime() + "&nbsp;&nbsp;&nbsp;");
			htmlStr.append("<a name='" + answerTable.getAnswerno() + "_" + answerTable.getAnswernerno() + "_" + answerTable.getAnswerner() + "' onclick='replay(this)'>回复</a><br>");
			htmlStr.append("<div id='" + answerTable.getAnswerno() + "' style='overflow-y:auto;'></div>");
			htmlStr.append("</div><hr>");
			String answerHtmlStr = getAnswerHtmlStr(answerTables, htmlStr.toString());
			request.setAttribute("answerHtmlStr", answerHtmlStr);
		}
		return "teacherJsp/InfoQuestionPage";
	}
	
	/**
	 * 点击消息中的评论回复消息记录
	 * @param request
	 * @param response
	 * @throws  
	 */
	@RequestMapping("infoReplay.do")
	public String infoReplay(HttpServletRequest request,HttpServletResponse response){
		// 评论问题回复编号
		String replayno = request.getParameter("replayno");
		// 用户编号
		String userno = request.getParameter("userno");
		// 消息编号
		String infono = request.getParameter("infono");
		String[] infonos = {infono};
		try {
			userMainPageService.updateInfoReadsign(infonos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<ReplayTable> replayTables = userMainPageService.queryReplayByRno(replayno);
		if(replayTables.size() > 0){
			ReplayTable replayTable = replayTables.get(0);
			// 评论问题编号
			String answerno = replayTable.getAnswerno();
			List<AnswerTable> answerTables = userMainPageService.queryAnwserByAno(answerno);
			if(answerTables.size() > 0){
				AnswerTable answerTable = answerTables.get(0);
				// 问题编号
				String questionno = answerTable.getQuestionno();
				// 根据问题编号查询问题详情
				List<QuestionTable> questionTables = userMainPageService.quertQuestionByQno(questionno);
				if(questionTables.size() > 0){
					QuestionTable questionTable = questionTables.get(0);
					request.setAttribute("questionno", questionTable.getQuestionno());
					request.setAttribute("questionname", questionTable.getQuestionname());
					request.setAttribute("questionpublisher", questionTable.getQuestionpublisher());
					request.setAttribute("questiontime", questionTable.getQuestiontime());
					request.setAttribute("questioncommentnum", questionTable.getQuestioncommentnum());
					request.setAttribute("questioncontent", questionTable.getQuestioncontent());
				}
				StringBuffer htmlStr = new StringBuffer();
				htmlStr.append("<div style='overflow-y:auto;'>");
				htmlStr.append(answerTable.getAnswerner() + ":" + answerTable.getAnswercontent() + "<br>" + answerTable.getAnswertime() + "&nbsp;&nbsp;&nbsp;");
				htmlStr.append("<a name='" + answerTable.getAnswerno() + "_" + answerTable.getAnswernerno() + "_" + answerTable.getAnswerner() + "' onclick='replay(this)'>回复</a><br>");
				// 根据评论编号进行改评论相关回复查询
				List<ReplayTable> replayList = userMainPageService.queryReplayByAnswerno(answerTable.getAnswerno());
				htmlStr.append("<div style='margin-left:30px;'>" + getReplayHtmlStr(replayList,userno,replayno) + "</div>");
				htmlStr.append("<div id='" + answerTable.getAnswerno() + "' style='overflow-y:auto;'></div>");
				htmlStr.append("</div><hr>");
				String answerHtmlStr = getAnswerHtmlStr(answerTables, htmlStr.toString());
				request.setAttribute("answerHtmlStr", answerHtmlStr);
			}
		}
		return "teacherJsp/InfoQuestionPage";
	}
	
	/**
	 * 对评论回复list进行html字符串拼接(方法重写)
	 * @param replayTables
	 * @return
	 */
	private String getReplayHtmlStr(List<ReplayTable> replayTables,String userno,String replayno){
		StringBuffer htmlStr = new StringBuffer();
		if(replayTables.size() > 0){
			for(ReplayTable replayTable : replayTables){
				if(replayno.equals(replayTable.getReplayno())){
					htmlStr.append("<div style='text-decoration:underline;'>" + replayTable.getReplaypname() + "&nbsp;回复&nbsp;" + replayTable.getAnswerpname() + "&nbsp;:&nbsp;" + replayTable.getReplayconcant() + "</div>");
				}else {
					htmlStr.append("<div style='text-decoration:underline;'>" + replayTable.getReplaypname() + "&nbsp;回复&nbsp;" + replayTable.getAnswerpname() + "&nbsp;:&nbsp;" + replayTable.getReplayconcant() + "</div>");
				}
				htmlStr.append("<br>" + replayTable.getReplaytime() + "&nbsp;&nbsp;&nbsp;");
				if(userno.equals(replayTable.getReplaypno())){
					htmlStr.append("<a name='" + replayTable.getAnswerno() + "_" + replayTable.getAnswerpno() + "_" + replayTable.getAnswerpname() + "' onclick='replay(this)'>回复</a><br>");
				}else {
					htmlStr.append("<a name='" + replayTable.getAnswerno() + "_" + replayTable.getReplaypno() + "_" + replayTable.getReplaypname() + "' onclick='replay(this)'>回复</a><br>");
				}
			}
		}
		return htmlStr.toString();
	}
	
	/**
	 * 首页
	 * @param request
	 * @param response
	 * @throws  
	 */
	@RequestMapping("getFirstPage.do")
	public String getFirstPage(HttpServletRequest request,HttpServletResponse response){
		// 查询评论数最高的15条问题记录
		List<QuestionTable> commentnumQues = userMainPageService.queryCommentNumQuestion();
		if(commentnumQues.size() > 0){
			List<QuestionTable> commentnumQuelist = new LinkedList<QuestionTable>();
			int size = commentnumQues.size();
			if(size > 5){
				size = 5;
			}
			for(int i = 0; i < size; i++){
				commentnumQuelist.add(commentnumQues.get(i));
			}
			String commentnumQuesHtmlStr = getQuestionHtmlStr(commentnumQuelist,false);
			request.setAttribute("commentnumQuesHtmlStr", commentnumQuesHtmlStr);
		}
		// 公告信息
		List<NoticeTable> noticeTables = userMainPageService.queryNotices();
		if(noticeTables.size() > 0){
			String noticeHtmlStr = getNoticeMsgHtmlStr(noticeTables);
			request.setAttribute("noticeHtmlStr", noticeHtmlStr);
		}else{
			request.setAttribute("noticeHtmlStr", "暂无公告信息");
		}
		// 热门文章
		List<ArticleTable> hosts = userMainPageService.queryHostArctiles();
		if(hosts.size() > 0){
			String hostArticlesHtmlstr = appendArticleMsg(hosts);
			request.setAttribute("hostArticlesHtmlstr", hostArticlesHtmlstr);
		}
		return "teacherJsp/firstPage";
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
	 * 根据问题类型查询出全部tab中的搜索条件
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("queryAllQueByQname.do")
	public void queryAllQueByQname(HttpServletRequest request,HttpServletResponse response) throws IOException{
		PrintWriter printWriter = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		String typeno = request.getParameter("typeno");
		String val = request.getParameter("val");
		List<QuestionTable> questionTables = userMainPageService.queryAllQueByQname(val, typeno);
		jsonObject.append("questionHtmlStr", "");
		if(questionTables.size() > 0){
			String questionHtmlStr = getQuestionHtmlStr(questionTables);
			jsonObject.append("questionHtmlStr", questionHtmlStr);
		}
		printWriter.write(jsonObject.toString());
		printWriter.flush();
	}
	
	/**
	 * 根据问题类型查询出全部tab中的搜索条件
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("queryHostQueByQname.do")
	public void queryHostQueByQname(HttpServletRequest request,HttpServletResponse response) throws IOException{
		PrintWriter printWriter = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		String typeno = request.getParameter("typeno");
		String val = request.getParameter("val");
		List<QuestionTable> questionTables = userMainPageService.queryHostQueByQname(val, typeno);
		jsonObject.append("questionHtmlStr", "");
		if(questionTables.size() > 0){
			String questionHtmlStr = getQuestionHtmlStr(questionTables);
			jsonObject.append("questionHtmlStr", questionHtmlStr);
		}
		printWriter.write(jsonObject.toString());
		printWriter.flush();
	}
}
