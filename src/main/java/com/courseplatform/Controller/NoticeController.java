package com.courseplatform.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.courseplatform.po.NoticeTable;
import com.courseplatform.po.User;
import com.courseplatform.po.DTO.NoticeDTO;
import com.courseplatform.service.NoticeService;
import com.courseplatform.service.UserService;
import com.courseplatform.util.BadWordUtil;
import com.courseplatform.util.CalendarUtil;

@Controller
@RequestMapping("/notice/")
public class NoticeController {
	
	@Autowired
	private NoticeService nservice;
	
	@Autowired
	private UserService userService;

	/**
	 * 
	 * @param notice
	 * @param model
	 * @return
	 */
	@RequestMapping("getNotice.do")
	public String List(NoticeTable notice, Model model,HttpServletRequest request,HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("adminSession");
		if(user == null) {
			model.addAttribute("message", "您已未知原因已退出登录，请重新登录");
			model.addAttribute("backFlag", "login");
			return "pages/info";
		}
		try {
			Long count = nservice.getNoticeNum();
			List<NoticeTable> noticeList = nservice.getNotice(notice);
			List<NoticeDTO> noticeDtoList = new ArrayList<NoticeDTO>();
			for (NoticeTable noticeTable : noticeList) {
				NoticeDTO noticeDTO = new NoticeDTO();
				noticeDTO.setNoticeid(noticeTable.getNoticeid());
				noticeDTO.setNoticepublisher(userService.getUserByUserNo(noticeTable.getNoticepublisher()));
				noticeDTO.setNotcecontent(noticeTable.getNotcecontent());
				noticeDTO.setNoticeenddate(noticeTable.getNoticeenddate());
				noticeDTO.setNoticepublisherdate(noticeTable.getNoticepublisherdate());
				noticeDTO.setNoticestartdate(noticeTable.getNoticestartdate());
				noticeDTO.setNoticeendflag(noticeTable.getNoticeendflag());
				String nowDate = CalendarUtil.getSysTimeYMD()+" 00:00:00";
				if(CalendarUtil.getSubLongTime(nowDate, noticeDTO.getNoticestartdate()+" 00:00:00")>=0
						&& CalendarUtil.getSubLongTime(nowDate, noticeDTO.getNoticeenddate()+" 00:00:00")<=0
						&& "0".equals(noticeDTO.getNoticeendflag())) {
					//该公告展示中，不可修改，可提前结束
					noticeDTO.setNoticeUpdateFlag("1");
				}
				if(CalendarUtil.getSubLongTime(nowDate, noticeDTO.getNoticestartdate()+" 00:00:00") < 0
						&& "0".equals(noticeDTO.getNoticeendflag())) {
					//该公告还未开始展示，可修改，可提前结束
					noticeDTO.setNoticeUpdateFlag("0");
				}
				if(CalendarUtil.getSubLongTime(nowDate, noticeDTO.getNoticeenddate()+" 00:00:00") > 0
						&& "0".equals(noticeDTO.getNoticeendflag())) {
					//该公告已结束，不可修改，不可提前结束
					noticeDTO.setNoticeUpdateFlag("2");
				}
				if("1".equals(noticeDTO.getNoticeendflag())) {
					//该公告已被提前结束，不可修改
					noticeDTO.setNoticeUpdateFlag("3");
				}
				noticeDtoList.add(noticeDTO);
			}
			model.addAttribute("notice", noticeDtoList);
			model.addAttribute("count", count);
			return "jsp/notice/news1";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "系统繁忙，请稍后再试");
			return "pages/info";
		}
		

	}

	@RequestMapping(value = "getNoticeById.do")
	public String getNoticeById(@RequestParam("id") String id, Model model,HttpServletRequest request) throws UnsupportedEncodingException {
		User user = (User) request.getSession().getAttribute("adminSession");
		if(user == null) {
			model.addAttribute("message", "您已未知原因已退出登录，请重新登录");
			model.addAttribute("backFlag", "login");
			return "pages/info";
		}
		try {
			NoticeTable snotice = nservice.selectById(id);
			NoticeDTO noticeDTO = new NoticeDTO();
			noticeDTO.setNoticeid(snotice.getNoticeid());
			noticeDTO.setNoticepublisher(userService.getUserByUserNo(snotice.getNoticepublisher()));
			noticeDTO.setNotcecontent(snotice.getNotcecontent());
			noticeDTO.setNoticeenddate(snotice.getNoticeenddate());
			noticeDTO.setNoticepublisherdate(snotice.getNoticepublisherdate());
			noticeDTO.setNoticestartdate(snotice.getNoticestartdate());
			noticeDTO.setNoticeendflag(snotice.getNoticeendflag());
			String nowDate = CalendarUtil.getSysTimeYMD()+" 00:00:00";
			if(CalendarUtil.getSubLongTime(nowDate, noticeDTO.getNoticestartdate()+" 00:00:00")>=0
					&& CalendarUtil.getSubLongTime(nowDate, noticeDTO.getNoticeenddate()+" 00:00:00")<=0
					&& "0".equals(noticeDTO.getNoticeendflag())) {
				//该公告展示中，不可修改，可提前结束
				noticeDTO.setNoticeUpdateFlag("1");
			}
			if(CalendarUtil.getSubLongTime(nowDate, noticeDTO.getNoticestartdate()+" 00:00:00") < 0
					&& "0".equals(noticeDTO.getNoticeendflag())) {
				//该公告还未开始展示，可修改，可提前结束
				noticeDTO.setNoticeUpdateFlag("0");
			}
			if(CalendarUtil.getSubLongTime(nowDate, noticeDTO.getNoticeenddate()+" 00:00:00") > 0
					&& "0".equals(noticeDTO.getNoticeendflag())) {
				//该公告已结束，不可修改，不可提前结束
				noticeDTO.setNoticeUpdateFlag("2");
			}
			if("1".equals(noticeDTO.getNoticeendflag())) {
				//该公告已被提前结束，不可修改
				noticeDTO.setNoticeUpdateFlag("3");
			}
			model.addAttribute("notices", noticeDTO);
			return "jsp/notice/updatenews";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "系统繁忙，请稍后再试");
			return "pages/info";
		}
		

	}

	@RequestMapping("loadadd")
	public String loadadd() {
		return "jsp/notice/New";

	}

	@RequestMapping("addnotice.do")
	public String addNotice(Model model, HttpServletRequest request,HttpServletResponse response) {
		NoticeTable snotice = new NoticeTable();

		String content = request.getParameter("content");
		String startTime = request.getParameter("noticestarttime");
		String endTime = request.getParameter("noticeendtime");
		User publisher = (User)request.getSession().getAttribute("adminSession");
		if(publisher==null) {
			model.addAttribute("message","您因为未知原因退出登录，请重新登录");
			model.addAttribute("backFlag", "login");
			return "pages/info";
		}
		String publisherNo = publisher.getUserNo();
		try {
			snotice.setNoticeid("GG"+System.currentTimeMillis());
			snotice.setNotcecontent(content);
			snotice.setNoticepublisher(publisherNo);

			String startTimeHMS = startTime + " 00:00:00";
			String endTimeHMS = endTime + " 00:00:00";
			String currentdate = CalendarUtil.getSysTimeYMD();
			String currentDateHMS = CalendarUtil.getSysTimeYMDHMS();
			Long a = CalendarUtil.str2Date(currentDateHMS).getTime();
			Long b = CalendarUtil.str2Date(startTimeHMS).getTime();
			Long c = CalendarUtil.str2Date(endTimeHMS).getTime();
			if (a > b) {
				String message = "时间不符合规定时间";
				request.getSession().setAttribute("msg", message);
				return "jsp/notice/New";
			} else if (a > c) {
				String message = "时间不符合规定时间";
				request.getSession().setAttribute("msg", message);
				return "jsp/notice/New";
			} else if (b > c) {
				String message = "时间不符合规定时间";
				request.getSession().setAttribute("msg", message);
				return "jsp/notice/New";
			} else {
				snotice.setNoticestartdate(startTime);
				snotice.setNoticeenddate(endTime);
				snotice.setNoticepublisherdate(currentdate);
				snotice.setNoticeendflag("0");
			}
			nservice.addNotice(snotice);
			return List(new NoticeTable(), model,request,response);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "系统繁忙，请稍后再试");
			return "pages/info";
		}

	}

	@RequestMapping("updateNotice.do")
	public String updateNotice(NoticeTable qnotice, Model model
			,HttpServletRequest request,HttpServletResponse response) {
		
		User publisher = (User)request.getSession().getAttribute("adminSession");
		if(publisher==null) {
			model.addAttribute("message","您因为未知原因退出登录，请重新登录");
			model.addAttribute("backFlag", "login");
			return "pages/info";
		}
		try {
			String noticeid = request.getParameter("noticeid");
			String noticepublisher = request.getParameter("noticepublisher");
			String content = request.getParameter("noticecontent");
			String start = request.getParameter("noticestarttime");
			String end = request.getParameter("noticeendtime");
			String fixtime = request.getParameter("noticepublishtime");
			qnotice.setNoticeid(noticeid);
			qnotice.setNoticepublisher(noticepublisher);
			qnotice.setNotcecontent(content);
			qnotice.setNoticestartdate(start);
			qnotice.setNoticeenddate(end);
			qnotice.setNoticepublisherdate(fixtime);
			nservice.updateNotice(qnotice);
			return List(new NoticeTable(), model,request,response);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "系统繁忙，请稍后再试");
			return "pages/info";
		}
		
	}

	@RequestMapping("deleteNotice.do")
	public String delete(@RequestParam("id") String id, Model model,
			HttpServletRequest request,HttpServletResponse response) 
					throws UnsupportedEncodingException {
		User publisher = (User)request.getSession().getAttribute("adminSession");
		if(publisher==null) {
			model.addAttribute("message","您因为未知原因退出登录，请重新登录");
			model.addAttribute("backFlag", "login");
			return "pages/info";
		}
		try {
			String nowDate = CalendarUtil.getSysTimeYMD()+" 00:00:00";
			NoticeTable notice = nservice.selectById(id);
			if((CalendarUtil.getSubLongTime(nowDate, notice.getNoticeenddate()+" 00:00:00") > 0
					&& "0".equals(notice.getNoticeendflag()))
					|| "1".equals(notice.getNoticeendflag())) {
				nservice.deleteNotice(id);
				return List(null, model, request, response);
			}else {
				model.addAttribute("message", "该公告不可删除");
				return "pages/info";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "系统繁忙，请稍后再试");
			return "pages/info";
		}
	}

	@RequestMapping("endNotice.do")
	public String endNotice(HttpServletRequest request,HttpServletResponse response,Model model) {
		User publisher = (User)request.getSession().getAttribute("adminSession");
		if(publisher==null) {
			model.addAttribute("message","您因为未知原因退出登录，请重新登录");
			model.addAttribute("backFlag", "login");
			return "pages/info";
		}
		try {
			String noticeId = request.getParameter("id");
			if(noticeId == null || "".equals(noticeId)) {
				model.addAttribute("message", "数据传输异常，请稍后再试");
				return "pages/info";
			}
			nservice.endNotice(noticeId);
			return List(null, model, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "系统繁忙，请稍后再试");
			return "pages/info";
		}
		
	}
	
	/**
	 * 批量删除操作
	 * 
	 * @param array
	 * @return
	 */
	@SuppressWarnings("null")
	@RequestMapping("deleteM.do")
	public @ResponseBody Integer deleteMap(@RequestParam(value = "array") Integer[] array) {

		if (array == null && array.length <= 0) {
			return 0;
		}
		for (int s : array) {
			System.out.println(s);
		}
		System.out.println(array.length);
		return nservice.deleteMap(array);
	}

	/**
	 * 退出
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("exist.do")
	public String exit(HttpSession session) {
		session.invalidate();
		return "index";
	}

	/**
	 * 验证公告信息的规范性
	 * 
	 * @param news
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("validate.do")
	public void validate(NoticeTable notice, HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter printWriter = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		String str = request.getParameter("noticecontent");
		int len = str.length();
		System.out.println(len);

		boolean smp = BadWordUtil.justice(str);
		System.out.println(smp);
		if (str == null || str.equals("")) {
			jsonObject.append("content", "输入为空,请输入");
		} else if (len >= 300) {
			System.out.println("sa " + str.length());
			jsonObject.append("content", "必须在300字以内");
		} else if (smp == true) {
			jsonObject.append("content", "有敏感词，请避免敏感词");
		} else {
			jsonObject.append("content", "");
		}
		printWriter.write(jsonObject.toString());
		printWriter.flush();

	}
}
