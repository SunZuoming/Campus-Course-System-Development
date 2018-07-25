package com.courseplatform.Controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.courseplatform.po.TeacherCourse;
import com.courseplatform.po.User;
import com.courseplatform.service.SendEmailService;
import com.courseplatform.service.TCService;
import com.courseplatform.service.UserService;



@Controller
@RequestMapping("/teachercourse/")
public class TCController {
	@Autowired
	private TCService tservice;
	@Autowired
	private UserService userservice;
	@Autowired
	private SendEmailService sendemail;
	@RequestMapping("list.do")
	public String list(TeacherCourse tc,Model model){
		List<TeacherCourse> list=tservice.list(tc);
		int count=0;
		for(TeacherCourse t:list){
			count++;
		}
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		return "jsp/teachercourse/list";
	}
	@RequestMapping("getMessage.do")
	public String getId(@RequestParam("id") int id,Model model){
		TeacherCourse tc=tservice.getMessage(id);
		model.addAttribute("tc", tc);
		return "jsp/teachercourse/shenhe";
	}
	@RequestMapping("update.do")
	public String update(HttpServletRequest request,Model model) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");
		String id=request.getParameter("id");
		String teacherno=request.getParameter("teacherno");
		String teachername=request.getParameter("teachername");
		String coursename=request.getParameter("coursename");
		String flag=request.getParameter("flag");
		//System.out.println(flag);
		//System.out.println(teachername);
		TeacherCourse tc=new TeacherCourse();
		try {
			tc.setId(id);
			tc.setTeacherno(teacherno);
			tc.setTeachername(teachername);
			tc.setCoursename(coursename);
			tc.setCoursefalg(flag);
			tservice.updatestatus(tc);
			if("0".equals(flag)){
				try {
					//发送邮件
					String subject="申请课程审核回执信息";
					String content="您申请的课程"+coursename+"经审核未通过";
					String toMail=userservice.getUserByUserNo(teacherno).getEmail();
					sendemail.sendSimpleMail(subject, content, toMail);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if("1".equals(flag)){
				//发送邮件
				try {
					User user=userservice.getUserByUserNo(teacherno);
					String subject="申请课程审核回执信息";
					String content="您申请的课程"+coursename+"经审核已通过，请在个人系统查看"
							+ "";
					String toMail=user.getEmail();
					sendemail.sendSimpleMail(subject, content, toMail);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		return "jsp/teachercourse/list";
		return list(new TeacherCourse(),model);
		
	}
}
