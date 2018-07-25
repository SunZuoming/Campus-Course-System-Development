 package com.courseplatform.Controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.courseplatform.po.FunctionTable;
import com.courseplatform.po.JurisdictionTable;
import com.courseplatform.po.LoginTable;
import com.courseplatform.po.ProhibitLoginRecordTable;
import com.courseplatform.po.User;
import com.courseplatform.po.WarningTable;
import com.courseplatform.po.DTO.AnswerDTO;
import com.courseplatform.po.DTO.ArticleDTO;
import com.courseplatform.po.DTO.CommentDTO;
import com.courseplatform.po.DTO.DataCountDTO;
import com.courseplatform.po.DTO.ProhibitDTO;
import com.courseplatform.po.DTO.QuestionDTO;
import com.courseplatform.po.DTO.ReportDTO;
import com.courseplatform.po.DTO.ReporterDTO;
import com.courseplatform.po.DTO.SharedFileDTO;
import com.courseplatform.po.report.OperationReport;
import com.courseplatform.service.DataCountDTOService;
import com.courseplatform.service.DeleteService;
import com.courseplatform.service.GoodsService;
import com.courseplatform.service.LoginTableService;
import com.courseplatform.service.ProhibitDTOService;
import com.courseplatform.service.ProhibitLoginRecordService;
import com.courseplatform.service.ReportDTOService;
import com.courseplatform.service.ReporterDTOService;
import com.courseplatform.service.SendEmailService;
import com.courseplatform.service.UserMainPageService;
import com.courseplatform.service.UserService;
import com.courseplatform.service.WarningTableService;
import com.courseplatform.util.CalendarUtil;
import com.courseplatform.util.MD5Util;
import com.courseplatform.util.ReportExport;
import com.courseplatform.util.StringUtil;

@Controller
@RequestMapping("pages/admin/")
public class AdminUserController {
	
	private static final int LENGTH = 2;
	
	
	@Autowired
	private UserService userService;

	@Autowired
	private ProhibitLoginRecordService proService;
	
	@Autowired
	private LoginTableService loTableService;
	
	@Autowired
	private DataCountDTOService dataDTOService;
	
	@Autowired
	private ReportDTOService reportDTOService;
	
	@Autowired
	private ReporterDTOService ReporterDTOService;
	
	@Autowired
	private GoodsService goodService;
	
	@Autowired
	private SendEmailService sendEmailService;
	
	@Autowired
	private DeleteService deleteService;
	
	@Autowired
	private WarningTableService warningTableService;
	
	@Autowired
	private ProhibitDTOService prohibitDTOService;
	
	@Autowired
	private UserMainPageService userMainPageService;

	/**
	 * 登录
	 * @param request
	 * @param response
	 * @return
	 */
	@Transactional
	@SuppressWarnings({"static-access" })
	@RequestMapping("adminlogin.do")
	public ModelAndView adminLogin(HttpServletRequest request,HttpServletResponse response) {
//		System.out.println(request.getServletPath());
		User user = new User();
		ModelAndView mv = new ModelAndView("pages/adminLogin");
		try {
			
			HttpSession session = request.getSession();
			String userNo = (String) request.getParameter("userNo");
			String password = (String) request.getParameter("userPassword");
			if("".equals(userNo)||"".equals(password)||userNo == null||password ==null) {
				mv.addObject("message", "数据传输出现异常，请稍后再试");
				return mv;
			}
			String userPassword = MD5Util.encryptMD5(password);
			user.setUserNo(userNo);
			user.setUserPassword(userPassword);
			LoginTable loTable = this.getLoginTable(user, request);
			loTableService.insertLogin(loTable);
			User userResult = userService.checkLogin(user);
			if (userResult == null) {
				mv.addObject("message", "账号与密码不符");
				return mv;
			} else {
				ProhibitLoginRecordTable prTable = new ProhibitLoginRecordTable();
				prTable.setProhibitLogineer(userResult.getUserNo());
				ProhibitLoginRecordTable prTableResult = proService.getProhibit(prTable);
				if(userResult.getProhibitLoginnum() == 4) {
					userResult.setUserState("2");
					userService.updateUser(userResult);
					mv.addObject("message", "您已被永久封号");
					return mv;
				}
				if ("1".equals(userResult.getUserState())) {
					mv.addObject("message", "您的账号已被禁止登录");
					return mv;
				}
				if (!userPassword.equals(userResult.getUserPassword())) {
					if ((userResult.getLoginFailnumber()+1) > 4) {
						if(userResult.getLoginFailnumber()<5) {
							userResult.setLoginFailnumber(userResult.getLoginFailnumber()+1);
						}
						userResult.setUserState("1");
						if(prTableResult == null ) {
							ProhibitLoginRecordTable prTableResult2 = new ProhibitLoginRecordTable();
							prTableResult2.setProhibitLogineer(userResult.getUserNo());
							prTableResult2.setProhibitLoginReason("登录失败次数超过5次");
							prTableResult2.setProhibitLoginer("System");
							prTableResult2.setProhibitLoginTime(CalendarUtil.getDateTime(new Date().getTime()));
							prTableResult2.setProhibitLoginType(Integer.toString(userResult.getProhibitLoginnum()));
							prTableResult2.setProhibitLoginFlag("0");
							prTableResult2.setProhibitLoginOperations("");
							prTableResult2.setProhibitCommitFlag("1");
							prTableResult2.setProhibitCommitTime(CalendarUtil.getDateTime(new Date().getTime()));
							prTableResult2.setProhibitLoginSurplusDays(StringUtil.getProhibitLoginSurplusDays(userResult));
							proService.insertProRecord(prTableResult2);
							userResult.setProhibitLoginnum(userResult.getProhibitLoginnum() + 1);
							userService.updateUser(userResult);
							mv.addObject("message", "您因为多次错误登录被封号，请联系管理员进行处理");
							return mv;
						}else {
							prTableResult.setProhibitLoginReason("登录失败次数超过5次");
							prTableResult.setProhibitLoginer(prTableResult.getProhibitLoginer()+",System");
							prTableResult.setProhibitLoginTime(CalendarUtil.getDateTime(new Date().getTime()));
							prTableResult.setProhibitLoginType(Integer.toString(userResult.getProhibitLoginnum()));
							prTableResult.setProhibitLoginFlag("0");
							prTableResult.setProhibitLoginOperations("");
							prTableResult.setProhibitCommitFlag("1");
							prTableResult.setProhibitCommitTime(CalendarUtil.getDateTime(new Date().getTime()));
							prTableResult.setProhibitLoginSurplusDays(StringUtil.getProhibitLoginSurplusDays(userResult));
							proService.updateProRecord(prTableResult);
							userResult.setProhibitLoginnum(userResult.getProhibitLoginnum() + 1);
							userService.updateUser(userResult);
							mv.addObject("message", "您因为多次错误登录被封号，请联系管理员进行处理");
							return mv;
						}
					} else {
						userResult.setLoginFailnumber(userResult.getLoginFailnumber() + 1);
						userService.updateUser(userResult);
						String mess = "您今天还有"+(5-userResult.getLoginFailnumber())+"次登录机会";
						mv.addObject("message", mess);
						return mv;
					}
				}else {
					if("0".equals(userResult.getLoginFirstflag())) {
						//登录成功，将失败次数归零
						userResult.setLoginFailnumber(0);
						userService.updateUser(userResult);
						//更新登录信息表中登录结果
						LoginTable loTable2 = this.updateLoginTable(loTable, "0", "", "");
						loTableService.updateLogin(loTable2);
						//将用户信息、登录信息存入session，用户密码置空
						userResult.setUserPassword("");
						session.setAttribute("adminSession", userResult);
						session.setAttribute("loTable", loTable2);
						return new ModelAndView("pages/changePassword")
								.addObject("flag", "1");
					}else {
						userResult.setLoginFailnumber(0);
						userService.updateUser(userResult);
						LoginTable loTable2 = this.updateLoginTable(loTable, "0", "", "");
						loTableService.updateLogin(loTable2);
						session.setAttribute("adminSession", userResult);
						session.setAttribute("loTable", loTable2);
						Map<String, String> result = new HashMap<String, String>();
						// 根据角色编号查询角色权限
						List<JurisdictionTable> jurisdictionTables = userMainPageService.queryJidByRoleid("000001");
						request.setAttribute("functionHtmlStr", "该用户暂无任何功能");
						if(jurisdictionTables.size() > 0){
							// 对用户功能进行拼接为html字符串
							result = getFunctionsHtmlStr(jurisdictionTables,userResult);
						}
						userResult.setUserPassword("");
						ModelAndView mvSuccess = new ModelAndView("pages/adminMain")
								.addObject("flag","0")
								.addObject("hMenu", result.get("hMenu"))
								.addObject("sMenu", result.get("sMenu"));
						return mvSuccess;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return mv.addObject("message", "系统繁忙，请稍后再试");
		}
	}
	
	/**
	 * 对用户功能进行拼接为html字符串
	 * @param jurisdictionTables
	 * @return
	 */
	private Map<String, String> getFunctionsHtmlStr(List<JurisdictionTable> jurisdictionTables,User user){
		//横向菜单栏的H5
		StringBuffer htmlStr = new StringBuffer();
		//竖向菜单栏的H5
		StringBuffer functionStr = new StringBuffer();
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
						htmlStr.append("<li>");
						htmlStr.append("<a id='"+functionTable.getFunctionno()+"' class='dropdown-toggle' data-toggle='dropdown' >"+functionTable.getFunctionname() + "<span class='caret'></span></a>");
						htmlStr.append("<ul class='dropdown-menu' role='menu'>");
						for(FunctionTable fun : sunFuncions){
							htmlStr.append("<li><a href='"+fun.getFunctionurl()+"' target='iframe'>"+fun.getFunctionname()+"</a></li>");
						}
						htmlStr.append("</ul>");
						htmlStr.append("</li>");
					}
					
				}
				if("-2".equals(functionTable.getFunctionfather())) {
					if("添加管理员".equals(functionTable.getFunctionname()) || "权限配置".equals(functionTable.getFunctionname()) && "admin".equals(user.getUserNo())) {
						functionStr.append("<li><a href='"+functionTable.getFunctionurl() + "' target='iframe'><i class='"+functionTable.getFunctionnote()+"'></i>"+functionTable.getFunctionname()+"</a></li>");
					}else {
						if("修改密码".equals(functionTable.getFunctionname()) || "退出".equals(functionTable.getFunctionname())) {
							functionStr.append("<li><a href='"+functionTable.getFunctionurl() + "'><i class='"+functionTable.getFunctionnote()+"'></i>"+functionTable.getFunctionname()+"</a></li>");
						}else {
							functionStr.append("<li><a href='"+functionTable.getFunctionurl() + "' target='iframe'><i class='"+functionTable.getFunctionnote()+"'></i>"+functionTable.getFunctionname()+"</a></li>");
						}
					}
				}
			}
		}
		Map<String, String> result = new HashMap<String, String>();
		result.put("hMenu", htmlStr.toString());
		result.put("sMenu", functionStr.toString());
//		return htmlStr.toString();
		return result;
	}
	
	/**
	 * 判断原密码是否正确
	 * @param session
	 * @param manager
	 * @return
	 * @throws IOException 
	 * @throws Exception
	 */
	@RequestMapping("ifn.do")
	public void ifnull(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter printWriter = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		User user = (User) session.getAttribute("adminSession");
		if(user == null) {
			jsonObject.append("retCode", "9999");
			jsonObject.append("retMsg", "您因未知原因退出登录，请重新登录，3秒后跳转登录页面");
		}else {
			User userResult = userService.getUserByUserNo(user.getUserNo());
			String password = (String) request.getParameter("oldPassword");
			if(password == null || "".equals(password)) {
				jsonObject.append("retCode", "8888");
				jsonObject.append("retMsg", "数据传输异常，请确认原密码后提交");
			}else {
				try {
					String oldPassword = MD5Util.encryptMD5(password);
					if(oldPassword.equals(userResult.getUserPassword())) {
						jsonObject.append("retCode", "0000");
						jsonObject.append("retMsg", "");
					}else {
						jsonObject.append("retCode", "1111");
						jsonObject.append("retMsg", "原密码不正确");
					}
				} catch (Exception e) {
					e.printStackTrace();
					jsonObject.append("retCode", "7777");
					jsonObject.append("retMsg", "系统繁忙，请稍后再试");
				}
			}
			
		}
		printWriter.write(jsonObject.toString());
		printWriter.flush();
	}
	
	/**
	 * 退出
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("loginout.do")
	public ModelAndView logout(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("pages/adminLogin");
		try {
			request.getSession().removeAttribute("adminSession");
			request.getSession().removeAttribute("loTable");
			return mv;
		}catch (Exception e) {
			request.getSession().removeAttribute("adminSession");
			request.getSession().removeAttribute("loTable");
			return mv;
		}
	}
	
	/**
	 * 修改密码
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("changePassword.do")
	public ModelAndView changePassword(HttpServletRequest request,HttpServletResponse response) {
		
		User user = (User)request.getSession().getAttribute("adminSession");
		if(user==null) {
			return new ModelAndView("pages/adminLogin")
					.addObject("message","您因为未知原因退出登录，请重新登录").addObject("backFlag", "login");
		}
		ModelAndView mv = new ModelAndView("pages/changePassword");
		try {
			String password = userService.getUserByUserNo(user.getUserNo()).getUserPassword();
			String oldPassqordReq = (String)request.getParameter("oldPassword");
			String newPasswordReq = (String)request.getParameter("newPassword");
			if("".equals(newPasswordReq)||"".equals(oldPassqordReq)
					||newPasswordReq == null||oldPassqordReq == null) {
				return new ModelAndView("pages/info").addObject("message", "数据传输出现异常，请稍后再试");
			}
			String oldPassword = MD5Util.encryptMD5(oldPassqordReq);
			String newPassword = MD5Util.encryptMD5(newPasswordReq);
			String flag = (String) request.getParameter("flag");
			if("".equals(password)||password == null) {
				request.getSession().removeAttribute("adminSession");
				return new ModelAndView("pages/info").addObject("message","系统出现异常，请联系管理员进行处理，您已被强制下线");
			}
			if(! oldPassword .equals(password)) {
				return mv.addObject("message","原密码输入错误");
			}else {
				user.setUserPassword(newPassword);
				if("1".equals(flag)) {
					user.setLoginFirstflag("1");
				}
				System.out.println(user.getUserPassword());
				userService.changePassword(user);
				request.getSession().removeAttribute("adminSession");
				request.getSession().removeAttribute("loTable");
				return new ModelAndView("pages/adminLogin").addObject("message","密码修改成功，请重新登录");
			}
		}catch (Exception e) {
			e.printStackTrace();
			return mv.addObject("message", "系统繁忙，请稍后再试");
		}
	}

	/**
	 * 跳转至添加管理员页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("showAddView.do")
	public ModelAndView showAddView(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("pages/addAdmin");
		User user = (User)request.getSession().getAttribute("adminSession");
		if(user==null) {
			return new ModelAndView("pages/info")
					.addObject("message","您因为未知原因退出登录，请重新登录")
					.addObject("backFlag", "login");
		}
		Boolean flag = true;
		String userNo = "";
		try {
			while(flag) {
				userNo = StringUtil.getNo(10);
				User userT = userService.getUserByUserNo(userNo);
				if(userT == null) {
					flag = false;
				}
			}
			return mv.addObject("userNo", userNo);
		} catch (Exception e) {
			return new ModelAndView("pages/info").addObject("message", "系统正忙，请稍后再试");
		}
	}
	
	/**
	 * 添加管理员
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("addAdmin.do")
	public ModelAndView addAdmin(HttpServletRequest request,HttpServletResponse response) {
		User userSession = (User)request.getSession().getAttribute("adminSession");
		if(userSession==null) {
			return new ModelAndView("pages/info")
					.addObject("message","您因为未知原因退出登录，请重新登录")
					.addObject("backFlag", "login");
		}else {
			if (!"admin".equals(userSession.getUserNo())) {
				request.getSession().removeAttribute("adminSession");
				request.getSession().removeAttribute("loTable");
				return new ModelAndView()
						.addObject("message", "您现在为违规操作，已被强制下线！")
						.addObject("backFlag", "login");
			}
		}
		try {
			String userNo = request.getParameter("userNo");
			String userPassword =MD5Util.encryptMD5((String)request.getParameter("password"));
			String userName = request.getParameter("name");
			String userPhone = request.getParameter("phone");
			String userEmail = request.getParameter("email");
			if("".equals(userNo) || "".equals(userPhone) || "".equals(userName) || "".equals(userPassword)
					|| "".equals(userEmail) || userName == null || userNo ==null || userPhone == null
					|| userPassword == null || userEmail == null) {
				return new ModelAndView("pages/info").addObject("message","请将信息填写完整");
			}else {
				User user = new User();
				user.setUserNo(userNo);
				user.setUserPassword(userPassword);
				user.setUserPhone(userPhone);
				user.setUserName(userName);
				user.setEmail(userEmail);
				user.setRoleId("000001");
//				user.setLoginFailnumber(0);
//				user.setl
				userService.insertUser(user);
				return showAddView(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("pages/info").addObject("message","添加失败");
		}
	}
	
	/**
	 * 显示5条未审核教师列表信息列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("showMainData.do")
	public ModelAndView showMainData(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("pages/mainFrame");
		try {
			Integer teacherCount = userService.getTeacherNum();
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("start", 0);
			map.put("length", 5);
			List<User> teacherList = userService.getUserList3State(map);
			DataCountDTO dataDTO = dataDTOService.getDataDTO();
//			dataDTO.setArticleNum(123);
//			dataDTO.setQuestionNum(300);
//			dataDTO.setReportNum(150);
//			dataDTO.setWarningNum(100);
//			dataDTO.setNoticeNum(50);
//			dataDTO.setProhibitNum(70);
			return mv.addObject("teacherCount", teacherCount)
					.addObject("teacherList", teacherList)
					.addObject("dataDTO", dataDTO)
					.addObject("flag", "1");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ModelAndView("pages/info").addObject("message", "系统繁忙，请稍后再试");
		}
	}
	
	/**
	 * 通过教师注册审核
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("comTeacher.do")
	public ModelAndView comTeacher(HttpServletRequest request,HttpServletResponse response) {
		User userSession = (User)request.getSession().getAttribute("adminSession");
		if(userSession==null) {
			return new ModelAndView("pages/info")
					.addObject("message","您因为未知原因退出登录，请重新登录")
					.addObject("backFlag", "login");
		}
		try {
			String userNo = request.getParameter("userNo");
			if(userNo == null || "".equals(userNo)) {
				return new ModelAndView("pages/info").addObject("message", "网络出现异常");
			} else {
				userService.updateTeacherState(userNo);
				User user = userService.getUserByUserNo(userNo);
				System.out.println(user.getEmail());
				String content = user.getUserName()+"老师，您好，您在校园课程通系统中注册的"+user.getUserNo()+"教师用户已通过审核，现您可登录系统继续其他操作。\n感谢您的使用！";
				sendEmailService.sendSimpleMail("注册教师审核通过通知", content, user.getEmail());
				return showMainData(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("pages/info").addObject("message", "系统繁忙，请稍后再试");
		}
	}
	
	/**
	 * 通过教师注册审核2
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("comTeacher2.do")
	public ModelAndView comTeacher2(HttpServletRequest request,HttpServletResponse response) {
		User userSession = (User)request.getSession().getAttribute("adminSession");
		if(userSession==null) {
			return new ModelAndView("pages/info")
					.addObject("message","您因为未知原因退出登录，请重新登录")
					.addObject("backFlag", "login");
		}
		try {
			String userNo = request.getParameter("userNo");
			String num = request.getParameter("nowPage");
			if(userNo == null || "".equals(userNo)) {
				return new ModelAndView("pages/info").addObject("message", "数据传输出现异常");
			} else {
				userService.updateTeacherState(userNo);
				User user = userService.getUserByUserNo(userNo);
				String content = user.getUserName()+"老师，您好，您在校园课程通系统中注册的"+user.getUserNo()+"教师用户已通过审核，现您可登录系统继续其他操作。\n感谢您的使用！";
				sendEmailService.sendSimpleMail("注册教师审核通过通知", content, user.getEmail());
				return showTea(num,request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("pages/info").addObject("message", "系统繁忙，请稍后再试");
		}
	}
	
	@RequestMapping("delComTeacher.do")
	public void delTeacher(HttpServletRequest request,HttpServletResponse response) {
		try {
			PrintWriter printWriter = response.getWriter();
			JSONObject jsonObject = new JSONObject();
			String userNo = request.getParameter("userNo");
			String reason = request.getParameter("reason");
			if (userNo==null || reason==null ||"".equals(userNo)|| "".equals(reason)) {
				jsonObject.append("retCode", "000000");
				jsonObject.append("retMsg", "数据传输出现异常，请稍后再试");
			}else {
				try {
					User userT = userService.getUserByUserNo(userNo);
					String content = userT.getUserName()+"您好,您在校园课程通系统中申请注册的教师身份用户现因"+reason+"原因没有通过审核，现已被删除，谢谢您的使用";
					sendEmailService.sendSimpleMail("注册教师审核失败通知", content, userT.getEmail());
					userService.deleteTeacher(userNo);
					jsonObject.append("retCode", "111");
					
				} catch (Exception e) {
					jsonObject.append("retCode", "000000");
					jsonObject.append("retMsg", "删除失败");
				}
				
			}
			printWriter.write(jsonObject.toString());
			printWriter.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 显示未审核注册的教师列表（分页）
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("showTea.do")
	public ModelAndView showTea(String nowPageParam, HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("pages/comTeacher");
		User userSession = (User)request.getSession().getAttribute("adminSession");
		if(userSession==null) {
			return new ModelAndView("pages/info")
					.addObject("message","您因为未知原因退出登录，请重新登录")
					.addObject("backFlag", "login");
		}
		try {
			long total = userService.getTeacherNum();
			long totalPage = 0;
			Integer nowPage = new Integer(0);
			if(total == 0) {
				totalPage =1;
				nowPage = 1;
			}else {
				if(total%LENGTH == 0) {
					totalPage =  total/LENGTH;
				}else {
					totalPage =  total/LENGTH+1;
				}
				
				if(nowPageParam != null) {
					int nowPageT = Integer.valueOf(nowPageParam);
					if(nowPageT>totalPage) {
						nowPage = (int) totalPage;
					}else {
						nowPage = nowPageT;
					}
				}else {
					nowPage = Integer.valueOf(request.getParameter("num"));
				}
			}
			Map<String, Integer> param = new HashMap<String, Integer>();
			param.put("start", (nowPage-1)*LENGTH);
			param.put("length", LENGTH);
			List<User> teacherList = userService.getUserList3State(param);
			if(teacherList.size() == 0) {
				return new ModelAndView("pages/info").addObject("message", "当前无需要审核的教师注册信息")
						.addObject("backFlag", "home");
			}else {
				return mv.addObject("teacherList", teacherList)
						.addObject("totalPage", totalPage)
						.addObject("initPage", nowPage)
						.addObject("teacherCount", total);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("pages/info").addObject("message", "系统繁忙，请稍后再试");
		}
	}
	
	/**
	 * 展示未处理举报信息（分页）
	 * @param nowPageParam
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("showRep.do")
	public ModelAndView showRepList(String nowPageParam, HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("pages/showReport");
		User userSession = (User)request.getSession().getAttribute("adminSession");
		if(userSession==null) {
			return new ModelAndView("pages/info")
					.addObject("message","您因为未知原因退出登录，请重新登录")
					.addObject("backFlag", "login");
		}
		try {
			int total = reportDTOService.getReportNumFlag();
			int totalPage = 0;
			Integer nowPage = 0;
			if(total==0) {
				totalPage = 1;
				nowPage = 1;
			}else {
				if(total % LENGTH == 0) {
					totalPage = total/LENGTH;
				}else {
					totalPage = total/LENGTH+1;
				}
				if(nowPageParam != null) {
					int nowPageT = Integer.valueOf(nowPageParam);
					if(nowPageT>totalPage) {
						nowPage = (int) totalPage;
					}else {
						nowPage = nowPageT;
					}
				}else {
					nowPage = Integer.valueOf(request.getParameter("num"));
				}
			}
			Map<String, Integer> param = new HashMap<String, Integer>();
			param.put("start", (nowPage-1)*LENGTH);
			param.put("length", LENGTH);
			
			List<ReportDTO> reportDTOList = reportDTOService.getReportDTOList(param);
			
			for (ReportDTO reportDTO : reportDTOList) {
				System.out.println(reportDTO.getReportgoodstype());
			} 
			if(reportDTOList.size()==0) {
				return new ModelAndView("pages/info")
						.addObject("message", "当前无未处理的举报信息")
						.addObject("backFlag", "home");
			}else {
				return mv.addObject("reportList", reportDTOList)
						.addObject("reportCount", total)
						.addObject("initPage", nowPage)
						.addObject("totalPage",totalPage);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("pages/info").addObject("message", "系统繁忙，请稍后再试");
		}
	}
	
	/**
	 * 显示举报人、被举报人信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("showUser.do")
	public ModelAndView showUser(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("pages/showReporter");
		User userSession = (User)request.getSession().getAttribute("adminSession");
		if(userSession==null) {
			return new ModelAndView("pages/info")
					.addObject("message","您因为未知原因退出登录，请重新登录")
					.addObject("backFlag", "login");
		}
		try {
			String reporter = request.getParameter("userNo");
			String repFlag = request.getParameter("repFlag");
			String nowPageString = request.getParameter("num");
			if("".equals(reporter)||"".equals(repFlag)||"".equals(nowPageString)
					||repFlag==null||reporter==null||nowPageString==null) {
				return new ModelAndView("pages/info").addObject("message", "数据传输出现异常请稍后再试");
			}
			ReporterDTO reporterDTO = ReporterDTOService.getReportDTO(reporter);
			if(reporterDTO == null) {
				return new ModelAndView("pages/info").addObject("message", "数据出现异常，请查看数据库");
			}
			int total = reporterDTO.getReporterNum();
			int totalPage = 0;
			if(total == 0) {
				totalPage = 1;
			}else {
				if(total % LENGTH == 0) {
					totalPage = total/LENGTH;
				}else {
					totalPage = total/LENGTH+1;
				}
			}
			Integer nowPage = 0;
			if(nowPageString == null || "".equals(nowPageString)) {
				nowPage = 1;
			}else {
				nowPage = Integer.valueOf(request.getParameter("num"));
			}
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("start", (nowPage-1)*LENGTH);
			param.put("length", LENGTH);
			param.put("reporter", reporter);
			List<ReportDTO> reportDTOList = reportDTOService.getReporterList(param);
			return mv.addObject("reporterDTO", reporterDTO)
					.addObject("reportList", reportDTOList)
					.addObject("repFlag", repFlag)
					.addObject("initPage", nowPage)
					.addObject("totalPage", totalPage);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ModelAndView("pages/info").addObject("message", "系统繁忙，请稍后再试");
		}
	}
	
	/**
	 * 显示被举报作品信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("showGoods.do")
	public ModelAndView showGoods(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("pages/showGoods");
		User userSession = (User)request.getSession().getAttribute("adminSession");
		if(userSession==null) {
			return new ModelAndView("pages/info")
					.addObject("message","您因为未知原因退出登录，请重新登录")
					.addObject("backFlag", "login");
		}
		try {
			String goodsFlag = request.getParameter("goodsFlag");
			String goodsNo = request.getParameter("goodsNo");
			if("0".equals(goodsFlag)) {
				ArticleDTO articleDTO = goodService.getArticleDTO(goodsNo);
				mv.addObject("articleDTO", articleDTO);
			}else if("1".equals(goodsFlag)) {
				QuestionDTO questionDTO = goodService.getQuestionDTO(goodsNo);
				mv.addObject("questionDTO", questionDTO);
			}else if("2".equals(goodsFlag)) {
				SharedFileDTO sharedFileDTO = goodService.getSharedFileDTO(goodsNo);
				mv.addObject("sharedFileDTO", sharedFileDTO);
			}else if("3".equals(goodsFlag)) {
				CommentDTO commentDTO = goodService.getCommentDTO(goodsNo);
				mv.addObject("commentDTO", commentDTO);
			}else {
				AnswerDTO answerDTO = goodService.getAnswerDTO(goodsNo);
				mv.addObject("answerDTO", answerDTO);
			}
			return mv.addObject("goodsFlag", goodsFlag);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ModelAndView("pages/info").addObject("message", "系统繁忙请稍后再试");
		}
	}
	
	/**
	 * 显示警告发出页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("showWarn.do")
	public ModelAndView showWarn(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("pages/showWarn");
		User userSession = (User)request.getSession().getAttribute("adminSession");
		if(userSession==null) {
			return new ModelAndView("pages/info")
					.addObject("message","您因为未知原因退出登录，请重新登录")
					.addObject("backFlag", "login");
		}
		try {
			String reportNo = request.getParameter("reportNo");
			String nowPage = request.getParameter("nowPage");
			ReportDTO reportDTO = reportDTOService.getReportByNo(reportNo);
			if(reportDTO == null) {
				return new ModelAndView("pages/info").addObject("message", "系统数据出现异常，请查看数据库");
			}else {
				return mv.addObject("reportDTO", reportDTO)
						.addObject("nowPage", nowPage);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ModelAndView("pages/info").addObject("message", "系统繁忙，请稍后再试");
		}
	}
	
	/**
	 * 举报处理，对举报人或被举报人发出警告信息
	 * 更新用户的警告次数，判断是否需要对用户进行封号
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("pubWarn.do")
	public ModelAndView pubWarn (HttpServletRequest request,HttpServletResponse response) {
		User userSession = (User)request.getSession().getAttribute("adminSession");
		if(userSession==null) {
			return new ModelAndView("pages/info")
					.addObject("message","您因为未知原因退出登录，请重新登录")
					.addObject("backFlag", "login");
		}
		try {
			User user = (User)request.getSession().getAttribute("adminSession");
			if(user==null) {
				return new ModelAndView("pages/adminLogin").addObject("message","您因为未知原因退出登录，请重新登录");
			}
			String warnningPeople = request.getParameter("warnningner");
			String nowPage = request.getParameter("nowPage");
			String reportNo = request.getParameter("reportNo");
			String content = request.getParameter("content");
//			String deleteFlag = request.getParameter("deleteFlag");
			String emailFlag = request.getParameter("emailFlag");
			if("".equals(warnningPeople)||"".equals(nowPage)||"".equals(reportNo)||
					"".equals(content)||"".equals(emailFlag) || warnningPeople == null ||
					nowPage == null || reportNo == null || content == null || emailFlag == null) {
				return new ModelAndView("pages/info").addObject("message", "网络异常，数据传输出现错误");
			}
			ReportDTO reportDTO = reportDTOService.getReportByNo(reportNo);
			if(reportDTO == null) {
				return new ModelAndView("pages/info").addObject("message", "数据出现异常");
			}
			User userW = userService.getUserByUserNo(warnningPeople);
			WarningTable warning = new WarningTable();
			warning.setWarningpeople(warnningPeople);
			warning.setWarningpublisher(user.getUserNo());
			warning.setWarningreason(content);
			warning.setWarningid(Long.toString(System.currentTimeMillis()));
			warning.setWarningreason(content);
			warning.setWarningstartdate(CalendarUtil.getSysTimeYMD());
			if(warnningPeople.equals(reportDTO.getReporter().getUserNo())){
				warning.setWarningreasonno(reportNo);
				warning.setWarningreasontype("5");
			}else {
				warning.setWarningreasonno(reportDTO.getReportgoodsno());
				warning.setWarningreasontype(reportDTO.getReportgoodstype());
			}
			//if("Y".equals(deleteFlag)) {
				warning.setWarningfinshflag("1");
				warning.setWarningenddate(CalendarUtil.getSysTimeYMD());
				String goodsType = reportDTO.getReportgoodstype();
				String goodsNo = reportDTO.getReportgoodsno();
				if("0".equals(goodsType)) {
					deleteService.deleteArticle(goodsNo);
				}
				if("1".equals(goodsType)) {
					deleteService.deleteQuestion(goodsNo);
				}
				if("2".equals(goodsType)) {
					deleteService.deleteSharedFile(goodsNo);
				}
				if("3".equals(goodsType)) {
					deleteService.deleteComment(goodsNo);
				}
				if("4".equals(goodsType)) {
					deleteService.deleteAnswer(goodsNo);
				}
			//}
			String flag = warningTableService.publicWarning(warning);
			if("0".equals(flag)) {
				content = "由于您过往封号次数为4次，您已被永久封号！";
				sendEmailService.sendSimpleMail("校园课程通系统永久封号通知", content, userW.getEmail());
			}
			if("1".equals(flag)) {
				content = "由于您的警告次数超过5次 ，你已被封号，如有疑问请联系管理员进行处理。";
				sendEmailService.sendSimpleMail("校园课程通系统封号通知", content, userW.getEmail());
			}
			if("2".equals(flag)) {
				if("Y".equals(emailFlag)) {
					content = "您有一条警告信息需要您进行处理，请尽快登录查看处理。\n警告编号："+warning.getWarningid();
					sendEmailService.sendSimpleMail("校园课程通系统警告通知", content, userW.getEmail());
				}
			}
			reportDTOService.endReport(reportNo);
			return showRepList(nowPage,request,response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ModelAndView("pages/info").addObject("message", "系统正忙，请稍后再试");
		}
	}
	
	/**
	 * 显示剩余天数大于0的封号信息
	 * @param nowPageParam
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("showPro.do")
	public ModelAndView showPro (String nowPageParam,HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("pages/canelPro");
		User userSession = (User)request.getSession().getAttribute("adminSession");
		if(userSession==null) {
			return new ModelAndView("pages/info")
					.addObject("message","您因为未知原因退出登录，请重新登录")
					.addObject("backFlag", "login");
		}
		try {
			Integer total = userService.getProNum();
			System.out.println("total:"+total);
			Integer totalPage = 0;
			Integer nowPage = 0;
			if(total == 0) {
				totalPage = 1;
				nowPage = 1;
			}else {
				if (total % LENGTH == 0) {
					totalPage = total / LENGTH;
				} else {
					totalPage = total / LENGTH + 1;
				}
				if (nowPageParam != null && !"".equals(nowPageParam)) {
					int nowPageT = Integer.valueOf(nowPageParam);
					if (nowPageT > totalPage) {
						nowPage = totalPage;
					} else {
						nowPage = nowPageT;
					}
				} else {
					nowPage = Integer.valueOf(request.getParameter("num"));
				}
			}
			Map<String, Integer> param = new HashMap<String, Integer>();
			param.put("start", (nowPage - 1) * LENGTH);
			param.put("length", LENGTH);
			List<ProhibitDTO> proDTOList = prohibitDTOService.getProhibitDTO(param);
			for (ProhibitDTO prohibitDTO : proDTOList) {
				System.out.println(prohibitDTO.getProhibitLogineer().getUserName());
			}
			if (proDTOList.size() == 0) {
				return new ModelAndView("pages/info")
						.addObject("message", "当前无被封号的用户")
						.addObject("backFlag", "home");
			} else {
				return mv.addObject("proDTOList", proDTOList)
						.addObject("totalPage", totalPage)
						.addObject("initPage",nowPage)
						.addObject("forEachFlag", "1");
			}
		} catch (Exception e) {
			e.printStackTrace();;
			return new ModelAndView("pages/info").addObject("message", "系统繁忙，请稍后再试");
		}
	}
	
	/**
	 * 根据输入的用户编号查询用户
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("showPro2.do")
	public ModelAndView showPro2 (HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("pages/canelPro");
		User userSession = (User)request.getSession().getAttribute("adminSession");
		if(userSession==null) {
			return new ModelAndView("pages/info")
					.addObject("message","您因为未知原因退出登录，请重新登录")
					.addObject("backFlag", "login");
		}
		try {
			String userNo = request.getParameter("userNo");
			if(userNo == null || "".equals(userNo)) {
				return showPro("1", request, response);
			}else {
				ProhibitDTO proDTOList = prohibitDTOService.getProhibitDTOByNo(userNo);
				if (proDTOList == null) {
					return new ModelAndView("pages/info")
							.addObject("message", "当前无被封号的用户")
							.addObject("backFlag", "home");
				} else {
					return mv.addObject("proDTOList", proDTOList)
							.addObject("totalPage", 1)
							.addObject("initPage",1)
							.addObject("forEachFlag", "0");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();;
			return new ModelAndView("pages/info").addObject("message", "系统繁忙，请稍后再试");
		}
	}
	
	/**
	 * 解除封号，将用户状态修改为0，失败登录次数置零，将封号剩余天数修改为0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("cancelPro.do")
	public ModelAndView cancelPro(HttpServletRequest request,HttpServletResponse response) {
		User userSession = (User)request.getSession().getAttribute("adminSession");
		if(userSession==null) {
			return new ModelAndView("pages/info")
					.addObject("message","您因为未知原因退出登录，请重新登录")
					.addObject("backFlag", "login");
		}
		try {
			String userNo = request.getParameter("userNo");
			String nowPage = request.getParameter("nowPage");
			if("".equals(userNo)||"".equals(nowPage)||userNo == null || nowPage ==null) {
				return new ModelAndView("pages/info").addObject("message", "数据传输出现错误");
			}
			User user = userService.getUserByUserNo(userNo);
			String content = user.getUserName()+"用户您好，您的账号"+user.getUserNo()+"已被解除封号，您可正常使用本系统！";
			sendEmailService.sendSimpleMail("校园课程通系统解除封号通知", content, user.getEmail());
			prohibitDTOService.cancelPro(userNo);
			return showPro(nowPage, request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ModelAndView("pages/info").addObject("message", "系统繁忙，请稍后再试");
		}
	}
	
	/**
	 * 生成日操作报表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("dayReport.do")
	public ModelAndView dayReport(HttpServletRequest request,HttpServletResponse response) {
		User userSession = (User)request.getSession().getAttribute("adminSession");
		if(userSession==null) {
			return new ModelAndView("pages/info")
					.addObject("message","您因为未知原因退出登录，请重新登录")
					.addObject("backFlag", "login");
		}
		try {
			String userNoReq = request.getParameter("userNo");
			String startDate = request.getParameter("startDate");
			String endDate  = request.getParameter("endDate");
			System.out.println(startDate+"  "+endDate);
			if("".equals(endDate)||"".equals(startDate)||endDate==null||startDate==null) {
				return new ModelAndView("pages/info").addObject("message", "数据传输异常");
			}
			String title = new String("课程通日操作统计报表");
			String p_userNo = new String("账号");
			String p_userName = new String("姓名");
			String p_roleName = new String("身份");
			String p_date = new String("日期");
			String p_operation = new String("总统计数");
			String p_article = new String("发表文章数");
			String p_comment = new String("发表评论数");
			String p_sharedFile = new String("共享文件数");
			String p_question = new String("提问数");
			String p_answer = new String("回答数");
			String p_homeWork = new String("发布作业数");
			String p_homeWorkCommit = new String("提交作业数");
			String p_collection = new String("收藏数");
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("title", title);
			parameters.put("p_userNo", p_userNo);
			parameters.put("p_userName", p_userName);
			parameters.put("p_roleName", p_roleName);
			parameters.put("p_date", p_date);
			parameters.put("p_operation", p_operation);
			parameters.put("p_article", p_article);
			parameters.put("p_comment", p_comment);
			parameters.put("p_sharedFile", p_sharedFile);
			parameters.put("p_question", p_question);
			parameters.put("p_answer", p_answer);
			parameters.put("p_homeWork", p_homeWork);
			parameters.put("p_homeWorkCommit", p_homeWorkCommit);
			parameters.put("p_collection", p_collection);
			
			List<OperationReport> operationDayList = new LinkedList<OperationReport>();
			if("".equals(userNoReq)) {
				Map<String, String> param = new HashMap<String, String>();
				param.put("startDate", startDate);
				param.put("endDate", endDate);
				System.out.println("1");
				operationDayList = dataDTOService.getDayReport(param);
			}else {
				Map<String, String> param = new HashMap<String, String>();
				String userNo = "%"+userNoReq+"%";
				param.put("startDate", startDate);
				param.put("endDate", endDate);
				param.put("userNo", userNo);
				System.out.println("2");
				operationDayList = dataDTOService.getDayReportListByNo(param);
			}
			if(operationDayList.size()==0) {
				return new ModelAndView("pages/info").addObject("message", "当前无符合该条件的数据");
			}
			String pdf = request.getParameter("PDF");
			String html = request.getParameter("HTML");
			String xls = request.getParameter("XLS");
			System.out.println(pdf+" "+html+" "+xls);
			if(pdf != null || html!=null ||xls!=null) {
				Map resultMap = new HashMap();
				Map resultMap2 = new HashMap();
				String jasp = "";
				if(pdf !=null || xls !=null){
		    		String jasperPath = "C:\\Users\\Administrator\\Desktop\\交大\\CoursePlatform\\CoursePlatform\\file\\report\\operation.jasper";
		    		String conDir = new String();
		    		StringBuffer tempFileDoc = new StringBuffer("D:\\coursePlatform\\download");

					tempFileDoc.append(System.getProperty("file.separator"));
					tempFileDoc.append("file");
					tempFileDoc.append(System.getProperty("file.separator"));
					tempFileDoc.append("download");
					tempFileDoc.append(System.getProperty("file.separator"));
					conDir = tempFileDoc.toString();

		    		System.out.println(jasperPath+"  "+conDir);
		    		resultMap = ReportExport.fixreportMap(pdf, "", xls, operationDayList, parameters, jasp, jasperPath, conDir);
		    		resultMap.put("xls", resultMap.get("xls").toString().replace("\\", "/"));
		    	}
		    	if (html != null) {
					String jasperPath = "C:\\Users\\Administrator\\Desktop\\交大\\CoursePlatform\\CoursePlatform\\file\\report\\operation.jasper";
					
					String conDir = new String();
					StringBuffer tempFileDoc = new StringBuffer("D:\\coursePlatform\\download");
						tempFileDoc.append(System.getProperty("file.separator"));
						tempFileDoc.append("file");
						tempFileDoc.append(System.getProperty("file.separator"));
						tempFileDoc.append("download");
						tempFileDoc.append(System.getProperty("file.separator"));
						conDir = tempFileDoc.toString();
					resultMap2 = ReportExport.fixreportMap("", html, "", operationDayList, parameters, jasp, jasperPath, conDir);
					resultMap2.put("html", resultMap2.get("html").toString().replace("\\", "/"));
					resultMap.put("htmltag", resultMap2.get("htmltag"));
					resultMap.put("html", resultMap2.get("html"));
					resultMap.put("jasperPrint", resultMap2.get("jasperPrint"));
 				}
		    	if(resultMap.size()==3){
		    		return new ModelAndView("pages/downDayReport")
		    				.addObject("resultMap", resultMap2)
		    				.addObject("title",title)
		    				.addObject("url", "pages/dayReport.jsp");
		    	}else {
		    		return new ModelAndView("pages/downDayReport")
		    				.addObject("resultMap", resultMap)
		    				.addObject("title",title)
		    				.addObject("url", "pages/dayReport.jsp");
				}
			}else {
				return new ModelAndView("pages/info").addObject("message", "请选择至少一种格式");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("pages/info").addObject("message", "系统繁忙，请稍后再试");
		}
	}
	
	
	/**
	 * 生成月操作报表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("monthReport.do")
	public ModelAndView monthReport(HttpServletRequest request,HttpServletResponse response) {
		User userSession = (User)request.getSession().getAttribute("adminSession");
		if(userSession==null) {
			return new ModelAndView("pages/info")
					.addObject("message","您因为未知原因退出登录，请重新登录")
					.addObject("backFlag", "login");
		}
		try {
			String userNoReq = request.getParameter("userNo");
			String startDate = request.getParameter("startDate");
			String endDate  = request.getParameter("endDate");
			System.out.println(startDate+"  "+endDate);
			if("".equals(endDate)||"".equals(startDate)||endDate==null||startDate==null) {
				return new ModelAndView("pages/info").addObject("message", "数据传输异常");
			}
			startDate = startDate+"-01";
			endDate = endDate+"-01";
			String title = new String("课程通月操作统计报表");
			String p_userNo = new String("账号");
			String p_userName = new String("姓名");
			String p_roleName = new String("身份");
			String p_date = new String("年-月");
			String p_operation = new String("总统计数");
			String p_article = new String("发表文章数");
			String p_comment = new String("发表评论数");
			String p_sharedFile = new String("共享文件数");
			String p_question = new String("提问数");
			String p_answer = new String("回答数");
			String p_homeWork = new String("发布作业数");
			String p_homeWorkCommit = new String("提交作业数");
			String p_collection = new String("收藏数");
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("title", title);
			parameters.put("p_userNo", p_userNo);
			parameters.put("p_userName", p_userName);
			parameters.put("p_roleName", p_roleName);
			parameters.put("p_date", p_date);
			parameters.put("p_operation", p_operation);
			parameters.put("p_article", p_article);
			parameters.put("p_comment", p_comment);
			parameters.put("p_sharedFile", p_sharedFile);
			parameters.put("p_question", p_question);
			parameters.put("p_answer", p_answer);
			parameters.put("p_homeWork", p_homeWork);
			parameters.put("p_homeWorkCommit", p_homeWorkCommit);
			parameters.put("p_collection", p_collection);
			
			List<OperationReport> operationDayList = new LinkedList<OperationReport>();
			if("".equals(userNoReq)) {
				Map<String, String> param = new HashMap<String, String>();
				param.put("startDate", startDate);
				param.put("endDate", endDate);
				System.out.println("1");
				operationDayList = dataDTOService.getMonthReportList(param);
			}else {
				Map<String, String> param = new HashMap<String, String>();
				String userNo = "%"+userNoReq+"%";
				param.put("startDate", startDate);
				param.put("endDate", endDate);
				param.put("userNo", userNo);
				System.out.println("2");
				operationDayList = dataDTOService.getMonthReportListByNo(param);
			}
			if(operationDayList.size()==0) {
				return new ModelAndView("pages/info").addObject("message", "当前无符合该条件的数据");
			}
			for (OperationReport operationReport : operationDayList) {
				operationReport.setDate(operationReport.getDate().substring(0, 7));
			}
			String pdf = request.getParameter("PDF");
			String html = request.getParameter("HTML");
			String xls = request.getParameter("XLS");
			System.out.println(pdf+" "+html+" "+xls);
			if(pdf != null || html!=null ||xls!=null) {
				Map resultMap = new HashMap();
				Map resultMap2 = new HashMap();
				String jasp = "";
				if(pdf !=null || xls !=null){
		    		String jasperPath = "C:\\Users\\Administrator\\Desktop\\交大\\CoursePlatform\\CoursePlatform\\file\\report\\operation.jasper";
		    		String conDir = new String();
		    		StringBuffer tempFileDoc = new StringBuffer("D:\\coursePlatform\\download");

					tempFileDoc.append(System.getProperty("file.separator"));
					tempFileDoc.append("file");
					tempFileDoc.append(System.getProperty("file.separator"));
					tempFileDoc.append("download");
					tempFileDoc.append(System.getProperty("file.separator"));
					conDir = tempFileDoc.toString();

		    		System.out.println(jasperPath+"  "+conDir);
		    		resultMap = ReportExport.fixreportMap(pdf, "", xls, operationDayList, parameters, jasp, jasperPath, conDir);
		    		resultMap.put("xls", resultMap.get("xls").toString().replace("\\", "/"));
		    	}
		    	if (html != null) {
					String jasperPath = "C:\\Users\\Administrator\\Desktop\\交大\\CoursePlatform\\CoursePlatform\\file\\report\\operation.jasper";
					
					String conDir = new String();
					StringBuffer tempFileDoc = new StringBuffer("D:\\coursePlatform\\download");
						tempFileDoc.append(System.getProperty("file.separator"));
						tempFileDoc.append("file");
						tempFileDoc.append(System.getProperty("file.separator"));
						tempFileDoc.append("download");
						tempFileDoc.append(System.getProperty("file.separator"));
						conDir = tempFileDoc.toString();
					resultMap2 = ReportExport.fixreportMap("", html, "", operationDayList, parameters, jasp, jasperPath, conDir);
					resultMap2.put("html", resultMap2.get("html").toString().replace("\\", "/"));
					resultMap.put("htmltag", resultMap2.get("htmltag"));
					resultMap.put("html", resultMap2.get("html"));
					resultMap.put("jasperPrint", resultMap2.get("jasperPrint"));
 				}
		    	if(resultMap.size()==3){
		    		return new ModelAndView("pages/downDayReport")
		    				.addObject("resultMap", resultMap2)
		    				.addObject("title",title)
		    				.addObject("url", "pages/monthReport.jsp");
		    	}else {
		    		return new ModelAndView("pages/downDayReport")
		    				.addObject("resultMap", resultMap)
		    				.addObject("title",title)
		    				.addObject("url", "pages/monthReport.jsp");
				}
			}else {
				return new ModelAndView("pages/info").addObject("message", "请选择至少一种格式");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("pages/info").addObject("message", "系统繁忙，请稍后再试");
		}
	}
	
	/**
	 * 生成年操作报表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("yearReport.do")
	public ModelAndView yearReport(HttpServletRequest request,HttpServletResponse response) {
		User userSession = (User)request.getSession().getAttribute("adminSession");
		if(userSession==null) {
			return new ModelAndView("pages/info")
					.addObject("message","您因为未知原因退出登录，请重新登录")
					.addObject("backFlag", "login");
		}
		try {
			String userNoReq = request.getParameter("userNo");
			String startDate = request.getParameter("startDate");
			String endDate  = request.getParameter("endDate");
			System.out.println(startDate+"  "+endDate);
			if("".equals(endDate)||"".equals(startDate)||endDate==null||startDate==null) {
				return new ModelAndView("pages/info").addObject("message", "数据传输异常");
			}
			startDate = startDate+"-01-01";
			endDate = endDate+"-01-01";
			String title = new String("课程通年操作统计报表");
			String p_userNo = new String("账号");
			String p_userName = new String("姓名");
			String p_roleName = new String("身份");
			String p_date = new String("年份");
			String p_operation = new String("总统计数");
			String p_article = new String("发表文章数");
			String p_comment = new String("发表评论数");
			String p_sharedFile = new String("共享文件数");
			String p_question = new String("提问数");
			String p_answer = new String("回答数");
			String p_homeWork = new String("发布作业数");
			String p_homeWorkCommit = new String("提交作业数");
			String p_collection = new String("收藏数");
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("title", title);
			parameters.put("p_userNo", p_userNo);
			parameters.put("p_userName", p_userName);
			parameters.put("p_roleName", p_roleName);
			parameters.put("p_date", p_date);
			parameters.put("p_operation", p_operation);
			parameters.put("p_article", p_article);
			parameters.put("p_comment", p_comment);
			parameters.put("p_sharedFile", p_sharedFile);
			parameters.put("p_question", p_question);
			parameters.put("p_answer", p_answer);
			parameters.put("p_homeWork", p_homeWork);
			parameters.put("p_homeWorkCommit", p_homeWorkCommit);
			parameters.put("p_collection", p_collection);
			
			List<OperationReport> operationDayList = new LinkedList<OperationReport>();
			if("".equals(userNoReq)) {
				Map<String, String> param = new HashMap<String, String>();
				param.put("startDate", startDate);
				param.put("endDate", endDate);
				System.out.println("1");
				System.out.println(startDate+"  "+endDate);
				operationDayList = dataDTOService.getYearReportList(param);
			}else {
				Map<String, String> param = new HashMap<String, String>();
				String userNo = "%"+userNoReq+"%";
				param.put("startDate", startDate);
				param.put("endDate", endDate);
				param.put("userNo", userNo);
				System.out.println("2");
				operationDayList = dataDTOService.getYearReportListByNo(param);
			}
			if(operationDayList.size()==0) {
				return new ModelAndView("pages/info").addObject("message", "当前无符合该条件的数据");
			}
			for (OperationReport operationReport : operationDayList) {
				operationReport.setDate(operationReport.getDate().substring(0, 4));
			}
			String pdf = request.getParameter("PDF");
			String html = request.getParameter("HTML");
			String xls = request.getParameter("XLS");
			System.out.println(pdf+" "+html+" "+xls);
			if(pdf != null || html!=null ||xls!=null) {
				Map resultMap = new HashMap();
				Map resultMap2 = new HashMap();
				String jasp = "";
				if(pdf !=null || xls !=null){
		    		String jasperPath = "C:\\Users\\Administrator\\Desktop\\交大\\CoursePlatform\\CoursePlatform\\file\\report\\operation.jasper";
		    		String conDir = new String();
		    		StringBuffer tempFileDoc = new StringBuffer("D:\\coursePlatform\\download");

					tempFileDoc.append(System.getProperty("file.separator"));
					tempFileDoc.append("file");
					tempFileDoc.append(System.getProperty("file.separator"));
					tempFileDoc.append("download");
					tempFileDoc.append(System.getProperty("file.separator"));
					conDir = tempFileDoc.toString();

		    		System.out.println(jasperPath+"  "+conDir);
		    		resultMap = ReportExport.fixreportMap(pdf, "", xls, operationDayList, parameters, jasp, jasperPath, conDir);
		    		resultMap.put("xls", resultMap.get("xls").toString().replace("\\", "/"));
		    	}
		    	if (html != null) {
					String jasperPath = "C:\\Users\\Administrator\\Desktop\\交大\\CoursePlatform\\CoursePlatform\\file\\report\\operation.jasper";
					
					String conDir = new String();
					StringBuffer tempFileDoc = new StringBuffer("D:\\coursePlatform\\download");
						tempFileDoc.append(System.getProperty("file.separator"));
						tempFileDoc.append("file");
						tempFileDoc.append(System.getProperty("file.separator"));
						tempFileDoc.append("download");
						tempFileDoc.append(System.getProperty("file.separator"));
						conDir = tempFileDoc.toString();
					resultMap2 = ReportExport.fixreportMap("", html, "", operationDayList, parameters, jasp, jasperPath, conDir);
					resultMap2.put("html", resultMap2.get("html").toString().replace("\\", "/"));
					resultMap.put("htmltag", resultMap2.get("htmltag"));
					resultMap.put("html", resultMap2.get("html"));
					resultMap.put("jasperPrint", resultMap2.get("jasperPrint"));
 				}
		    	if(resultMap.size()==3){
		    		return new ModelAndView("pages/downDayReport")
		    				.addObject("resultMap", resultMap2)
		    				.addObject("title",title)
		    				.addObject("url", "pages/monthReport.jsp");
		    	}else {
		    		return new ModelAndView("pages/downDayReport")
		    				.addObject("resultMap", resultMap)
		    				.addObject("title",title)
		    				.addObject("url", "pages/monthReport.jsp");
				}
			}else {
				return new ModelAndView("pages/info").addObject("message", "请选择至少一种格式");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("pages/info").addObject("message", "系统繁忙，请稍后再试");
		}
	}
	/**
	 * 下载文件
	 * @param request
	 * @param response
	 */
	@RequestMapping("downReportFile.do")
	public void downReportFile(HttpServletRequest request,HttpServletResponse response) {
		String filename = request.getParameter("file");
		String title = request.getParameter("title");
		String property = request.getParameter("property");
		FileInputStream in = null;
		ServletOutputStream out = null;
		try {

			response.setContentType("application/x-msdownload");
			String filenamea = title+property;
			System.out.println("filenamea"+filenamea);
			response.setHeader("Content-Disposition", "attachment; filename=" +new String(filenamea.getBytes("gb2312"), "ISO8859-1"));
			in = new FileInputStream(filename);
			out = response.getOutputStream();
			out.flush();
			int aRead = 0;
			while ((aRead = in.read()) != -1 & in != null) {
				out.write(aRead);
			}
			out.flush();

		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}


	}
	/**
	 * 获取登录记录实体对象
	 * @param user
	 * @return
	 */
	public static LoginTable getLoginTable(User user, HttpServletRequest request) {
		LoginTable loTable = new LoginTable();
		loTable.setLoginid(Long.toString(System.currentTimeMillis()));
		loTable.setLoginuserno(user.getUserNo());
		loTable.setLoginip(StringUtil.getIp(request));
		loTable.setLoginresult("1");
		loTable.setLogintime(CalendarUtil.getSysTimeYMDHMS());
		loTable.setLogintype("0");
		return loTable;
	}
	
	/**
	 * 更新登录记录实体对象
	 * @param loTable
	 * @param loginresult
	 * @param loginOutTime
	 * @param loginOutType
	 * @return
	 */
	public static LoginTable updateLoginTable(LoginTable loTable,
			String loginresult,String loginOutTime,String loginOutType) {
		
		loTable.setLoginresult(loginresult);
		loTable.setLoginouttime(loginOutTime);
		loTable.setLoginouttype(loginOutType);
		return loTable;
	}

	
}
