package com.courseplatform.dao;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.courseplatform.po.User;
import com.courseplatform.po.report.OperationReport;
import com.courseplatform.service.DataCountDTOService;
import com.courseplatform.service.SendEmailService;
import com.courseplatform.service.UserService;
import com.courseplatform.util.ReportExport;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class Test {

	@Autowired
	private DataCountDTOService dataCountDTOService;
	
	@Autowired
	private SendEmailService sendEmailService;
	
	@Autowired
	private UserService userService;
	
	@org.junit.Test
	public void sendEmail() {
		sendEmailService.sendSimpleMail("通知", "\t\t明天下午四点二教205集合开会！\n\t\t收到请回复", "2372590227@qq.com");
	}
	
	@org.junit.Test
	public void insertMonth() {
		List<User> userList = userService.getAllUser();
		for (User user : userList) {
			dataCountDTOService.insertOperationDay(user.getUserNo());
		}
		
		dataCountDTOService.insertOperationMonth();
		dataCountDTOService.insertOperationYear();
	}
	
	@org.junit.Test
	public void insertYear() {
		dataCountDTOService.insertOperationYear();
	}
	
	@org.junit.Test
	public void getAllUser() {
		List<User> userList = userService.getAllUser();
		for (User user : userList) {
			System.out.println(user.getUserNo());
		}
	}
	
	@org.junit.Test
	public void getDayReport2() {
		Map< String, String> param = new HashMap<String, String>();
		param.put("startDate", "2018-01-01");
		param.put("endDate", "2018-06-01");
		List<OperationReport> list = dataCountDTOService.getDayReport(param);
		
		System.out.println("ribaobiao:");
		for (OperationReport operationDaysReport : list) {
			System.out.println("-----------");
			System.out.println(operationDaysReport.getUserNo());
			System.out.println(operationDaysReport.getUserName());
			System.out.println(operationDaysReport.getRoleName());
			System.out.println(operationDaysReport.getDate());
			System.out.println(operationDaysReport.getOperation());
			System.out.println(operationDaysReport.getArticle());
			System.out.println(operationDaysReport.getComment());
			System.out.println(operationDaysReport.getSharedFile());
			System.out.println(operationDaysReport.getQuestion());
			System.out.println(operationDaysReport.getAnswer());
			System.out.println(operationDaysReport.getHomeWork());
			System.out.println(operationDaysReport.getHomeWorkCommit());
			System.out.println(operationDaysReport.getCollection());
			System.out.println("-----------");
		}
	}
	
	@org.junit.Test
	public void createReport() {
		try {
			String userNoReq = "";
//			String startDate = request.getParameter("startDate");
			String startDate = "2018-01-01";
			//String endDate  = request.getParameter("endDate");
			String endDate = "2018-05-01";
			System.out.println(startDate+"  "+endDate);
			/*if("".equals(endDate)||"".equals(startDate)||endDate==null||startDate==null) {
				return new ModelAndView("pages/info").addObject("message", "数据传输异常");
			}*/
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
			List<OperationReport> operationDayListResult = null;
			if("".equals(userNoReq)) {
				Map<String, String> param = new HashMap<String, String>();
				param.put("startDate", startDate);
				param.put("endDate", endDate);
				System.out.println("1");
				operationDayListResult = dataCountDTOService.getDayReport(param);
			}else {
				Map<String, String> param = new HashMap<String, String>();
				String userNo = "%"+userNoReq+"%";
				param.put("startDate", startDate);
				param.put("endDate", endDate);
				param.put("userNo", userNo);
				System.out.println("2");
				operationDayListResult = dataCountDTOService.getDayReportListByNo(param);
			}
			for (OperationReport operationReport : operationDayListResult) {
				OperationReport opReport = new OperationReport();
				opReport.setUserNo(operationReport.getUserNo());
				opReport.setUserName(operationReport.getUserName());
				opReport.setRoleName(operationReport.getRoleName());
				opReport.setDate(operationReport.getDate());
				opReport.setArticle(operationReport.getArticle()==null?0:operationReport.getArticle());
				opReport.setAnswer(operationReport.getAnswer()==null?0:operationReport.getAnswer());
				opReport.setComment(operationReport.getComment()==null?0:operationReport.getComment());
				opReport.setQuestion(operationReport.getQuestion()==null?0:operationReport.getQuestion());
				opReport.setSharedFile(operationReport.getSharedFile()==null?0:operationReport.getSharedFile());
				opReport.setCollection(operationReport.getCollection()==null?0:operationReport.getCollection());
				opReport.setHomeWork(operationReport.getHomeWork()==null?0:operationReport.getHomeWork());
				opReport.setHomeWorkCommit(operationReport.getHomeWorkCommit()==null?0:operationReport.getHomeWorkCommit());
				opReport.setOperation(operationReport.getOperation()==null?0:operationReport.getOperation());
				operationDayList.add(opReport);
			}
			/*if(operationDayList.size()==0) {
				return new ModelAndView("pages/info").addObject("message", "当前无符合该条件的数据");
			}*/
//			String pdf = request.getParameter("PDF");
//			String html = request.getParameter("HTML");
//			String xls = request.getParameter("XLS");
			String pdf = null;
			String html = "on";
			String xls = "on";
			System.out.println(pdf+" "+html+" "+xls);
			if(pdf != null || html!=null ||xls!=null) {
				Map resultMap = new HashMap();
				Map resultMap2 = new HashMap();
				String jasp = "";
				if(pdf !=null || xls !=null){
		    		String jasperPath = "G:\\Java\\CoursePlatform\\file\\report\\operation.jasper";
		    		String conDir = new String();
		    		StringBuffer tempFileDoc = new StringBuffer("D:\\download");

					tempFileDoc.append(System.getProperty("file.separator"));
					tempFileDoc.append("file");
					tempFileDoc.append(System.getProperty("file.separator"));
					tempFileDoc.append("download");
					tempFileDoc.append(System.getProperty("file.separator"));
					conDir = tempFileDoc.toString();

		    		System.out.println(jasperPath+"  "+conDir);
		    		resultMap = ReportExport.fixreportMap(pdf, "", xls, operationDayList, parameters, jasp, jasperPath, conDir);
		    	}
		    	if (html != null) {
					String jasperPath = "G:\\Java\\CoursePlatform\\file\\report\\operation.jasper";
					
					String conDir = new String();
					StringBuffer tempFileDoc = new StringBuffer("D:\\download");
						tempFileDoc.append(System.getProperty("file.separator"));
						tempFileDoc.append("file");
						tempFileDoc.append(System.getProperty("file.separator"));
						tempFileDoc.append("download");
						tempFileDoc.append(System.getProperty("file.separator"));
						conDir = tempFileDoc.toString();
					resultMap2 = ReportExport.fixreportMap("", html, "", operationDayList, parameters, jasp, jasperPath, conDir);
					
					resultMap.put("htmltag", resultMap2.get("htmltag"));
					resultMap.put("html", resultMap2.get("html"));
					resultMap.put("jasperPrint", resultMap2.get("jasperPrint"));
 				}
		    	
		    	
		    	if(resultMap.size()==3){
		    		System.out.println("2"+resultMap2.get("html"));
		    		System.out.println("2"+resultMap2.get("pdf"));
		    		System.out.println("2"+resultMap2.get("xls"));
//		    		return new ModelAndView("pages/downDayReport")
//		    				.addObject("resultMap", resultMap2)
//		    				.addObject("title",title)
//		    				.addObject("title2","")
//		    				.addObject("url", "${basePath}pages/admin/dayReport.jsp")
//		    				/*.addObject("explainFile", explainFile)*/;
		    	}else {
		    		System.out.println("1"+resultMap.get("html"));
		    		System.out.println("1"+resultMap.get("pdf"));
		    		System.out.println("1"+resultMap.get("xls"));
//		    		return new ModelAndView("pages/downDayReport")
//		    				.addObject("resultMap", resultMap)
//		    				.addObject("title",title)
//		    				.addObject("title2","")
//		    				.addObject("url", "${basePath}pages/admin/dayReport.jsp")
//		    				/*.addObject("explainFile", explainFile)*/;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
//			return new ModelAndView("pages/info").addObject("message", "系统繁忙，请稍后再试");
		}
	}
	
	/**
	 * 报表测试
	 */
	@org.junit.Test
	public void createReport2() {
		String title = new String("课程通年操作统计报表");
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
		List<OperationReport> operationDayListResult = null;
		Map<String, String> param = new HashMap<String, String>();
		param.put("startDate", "2017-01-01");
		param.put("endDate", "2018-01-01");
		System.out.println("1");
		operationDayListResult = dataCountDTOService.getYearReportList(param);

		/*for (OperationReport operationReport : operationDayListResult) {
			OperationReport opReport = new OperationReport();
			opReport.setUserNo(operationReport.getUserNo());
			opReport.setUserName(operationReport.getUserName());
			opReport.setRoleName(operationReport.getRoleName());
			opReport.setDate(operationReport.getDate());
			opReport.setArticle(operationReport.getArticle() == null ? 0 : operationReport.getArticle());
			opReport.setAnswer(operationReport.getAnswer() == null ? 0 : operationReport.getAnswer());
			opReport.setComment(operationReport.getComment() == null ? 0 : operationReport.getComment());
			opReport.setQuestion(operationReport.getQuestion() == null ? 0 : operationReport.getQuestion());
			opReport.setSharedFile(operationReport.getSharedFile() == null ? 0 : operationReport.getSharedFile());
			opReport.setCollection(operationReport.getCollection() == null ? 0 : operationReport.getCollection());
			opReport.setHomeWork(operationReport.getHomeWork() == null ? 0 : operationReport.getHomeWork());
			opReport.setHomeWorkCommit(
					operationReport.getHomeWorkCommit() == null ? 0 : operationReport.getHomeWorkCommit());
			opReport.setOperation(operationReport.getOperation() == null ? 0 : operationReport.getOperation());
			operationDayList.add(opReport);
		}*/
		String pdf = null;
		String html = "on";
		String xls = "on";
		System.out.println(pdf + " " + html + " " + xls);
		if (pdf != null || html != null || xls != null) {
			Map resultMap = new HashMap();
			Map resultMap2 = new HashMap();
			String jasp = "";
			if (pdf != null || xls != null) {
				String jasperPath = "G:\\Java\\CoursePlatform\\file\\report\\operation.jasper";
//				String jasperPath = "G:\\Java\\CoursePlatform\\file\\report\\OperationReport.jasper";
				// jasperPath =
				// request.getSession().getServletContext().getRealPath(jasperPath);
				String conDir = new String();
				StringBuffer tempFileDoc = new StringBuffer("D:\\download");
				tempFileDoc.append(System.getProperty("file.separator"));
				tempFileDoc.append("file");
				tempFileDoc.append(System.getProperty("file.separator"));
				tempFileDoc.append("download");
				tempFileDoc.append(System.getProperty("file.separator"));
				conDir = tempFileDoc.toString();
				System.out.println(jasperPath + "  " + conDir);
				try {
					resultMap = ReportExport.fixreportMap(pdf, "", xls, operationDayListResult, parameters, jasp, jasperPath,
							conDir);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			if (html != null) {
				String jasperPath = "G:\\Java\\CoursePlatform\\file\\report\\operation.jasper";
//				String jasperPath = "G:\\Java\\CoursePlatform\\file\\report\\OperationReport.jasper";

				String conDir = new String();
				StringBuffer tempFileDoc = new StringBuffer("D:\\download");
				tempFileDoc.append(System.getProperty("file.separator"));
				tempFileDoc.append("file");
				tempFileDoc.append(System.getProperty("file.separator"));
				tempFileDoc.append("download");
				tempFileDoc.append(System.getProperty("file.separator"));
				conDir = tempFileDoc.toString();

				try {
					resultMap2 = ReportExport.fixreportMap("", html, "", operationDayListResult, parameters, jasp, jasperPath,
							conDir);
				} catch (Exception e) {
					e.printStackTrace();
				}

				resultMap.put("htmltag", resultMap2.get("htmltag"));
				resultMap.put("html", resultMap2.get("html"));
				resultMap.put("jasperPrint", resultMap2.get("jasperPrint"));
			}
		}
	}
}
