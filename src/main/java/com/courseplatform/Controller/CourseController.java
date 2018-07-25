package com.courseplatform.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.courseplatform.po.Course;
import com.courseplatform.po.CourseinfoTable;
import com.courseplatform.po.Pricecourse;
import com.courseplatform.po.Privatecourse;
import com.courseplatform.po.TeacherCourse;
import com.courseplatform.po.WatchTable;
import com.courseplatform.service.StudentService;
import com.courseplatform.service.WatchService;

@Controller
@RequestMapping("/course/")
public class CourseController { 
	
	@Autowired
	private WatchService watchservice;
	
    //课程选择
	private static final int LENGTH1 =5;
	@RequestMapping("CourseAll.do")
	public ModelAndView getCourseAll(TeacherCourse tcourse,String nowPageParam,Model model,HttpServletRequest request,WatchTable watch) {
		String s=request.getSession().getAttribute("userno").toString();
		watch.setUsernos(s);
		//model.addAttribute("tc", tcl);
		List<WatchTable> was=watchservice.userWatchCourse(watch);
	    model.addAttribute("wa", was);
	    ModelAndView mv = new ModelAndView("courseJsp/courseall");
	    
	    long total = watchservice.getteacourseNum();
		long totalPage = 0;
		Integer nowPage = new Integer(0);
		if(total == 0) {
			totalPage =1;
			nowPage = 1;
		}else {
			if(total%LENGTH1 == 0) {
				totalPage =  total/LENGTH1;
			}else {
				totalPage =  total/LENGTH1+1;
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
		param.put("start", (nowPage-1)*LENGTH1);
		param.put("length", LENGTH1);
		List<TeacherCourse> tc=watchservice.CourseAll(param);
		List<TeacherCourse> tcl=new ArrayList<TeacherCourse>();
		int flag = 0;
	    for(int i = 0; i < tc.size(); i++) {
	    	for (int j = 0; j < was.size(); j++) {
				if(tc.get(i).getCoursename().equals(was.get(j).getCoursename())&&
						tc.get(i).getTeachername().equals(was.get(j).getUsername())) {
					flag=1;
					break;
				}
				flag=0;
	    }
	    if(flag==0) {
	    	tcl.add(tc.get(i));
	    }
		
	}
		//model.addAttribute("file", sf);
		return mv.addObject("tc", tcl)
				.addObject("totalPage", totalPage)
				.addObject("initPage", nowPage)
				.addObject("teacherCount", total);
}
	    
	
	/**
	 * 插入一条带有关注标记的课程记录
	 * @param watch
	 */
	@SuppressWarnings("unlikely-arg-type")
	@RequestMapping("insertCourse.do")
	public String insertCourse(CourseinfoTable courseinfo,WatchTable watch,TeacherCourse tcourse,
			HttpServletRequest request,Model model) {
		
		String s=request.getSession().getAttribute("userno").toString();
		watch.setUsernos(s);
		tcourse.setId(request.getParameter("id"));
		TeacherCourse tocourse=watchservice.CourseByid(tcourse);
		String k=tocourse.getCoursename();
		CourseinfoTable courseinfoto=watchservice.getcourseno(k);
		watch.setCourseinfono(courseinfoto.getCourseinfono());
		watch.setCoursename(tocourse.getCoursename());
		watch.setUsernot(tocourse.getTeacherno());
		watch.setUsername(tocourse.getTeachername());
			watchservice.insertWatchCourse(watch);
			model.addAttribute("cour", "关注成功！");
			return "courseJsp/error";
	
	}
	
	/**
	 * 删除学生课程
	 * @param watch
	 */
	@RequestMapping("deleteCourse.do")
	public String deleteCourse(WatchTable watch,HttpServletRequest request,Model model) {
		String s=request.getSession().getAttribute("userno").toString();
		watch.setUsernos(s);
		Integer id=Integer.parseInt(request.getParameter("id"));
		watch.setId(id);
		watchservice.deleteWatchCourse(watch);
		return userWatchCourse(watch,request,model);
	}
	
	// 查询学生关注课程
	@RequestMapping("userCourseall.do")
	public String userWatchCourse(WatchTable watch,HttpServletRequest request,Model model) {
		String s=request.getSession().getAttribute("userno").toString();
		watch.setUsernos(s);
		List<WatchTable> wa=watchservice.userWatchCourse(watch);
	    model.addAttribute("wa", wa);
		return "courseJsp/usercourse";
	}
	
	/**
	 * 查询视频课程信息
	 * @param watch
	 */
	@RequestMapping("pricourse.do")
	public String prCourse(Pricecourse pri,HttpServletRequest request,Model model) {
		HttpSession session = request.getSession();
		Integer id=Integer.parseInt(request.getParameter("id"));
		pri.setId(id);
		Pricecourse pric=watchservice.prCourse(pri);
		session.setAttribute("priva", pric.getConame());
		model.addAttribute("pric", pric);
		return "courseJsp/pricecourse";
	}
	
	/**
	 * 插入视频课程记录
	 * @param watch
	 */
	@RequestMapping("inpricourse.do")
	public String insertpriCourse(Privatecourse pse,HttpServletRequest request,Model model) {
		String s=request.getSession().getAttribute("userno").toString();
		pse.setUserid(s);
		pse.setConame(request.getSession().getAttribute("priva").toString());
		Privatecourse psee=watchservice.priCourse(pse);
		if(psee==null) {
			watchservice.insertpriCourse(pse);
			return AllpriCourse(pse,request,model);
		}else {
			model.addAttribute("cour", "你已经关注！");
			return "courseJsp/error";
		}
	}
	
	/**
	 * 选择视频课程记录
	 * @param watch
	 */
	@RequestMapping("allpricourse.do")
	public String AllpriCourse(Privatecourse pse,HttpServletRequest request,Model model) {
		String s=request.getSession().getAttribute("userno").toString();
		pse.setUserid(s);
		List<Privatecourse> psee=watchservice.AllpriCourse(pse);
		HttpSession session = request.getSession();
		model.addAttribute("psee", psee);
		return "courseJsp/privatecourse";
	}
	
	/**
	 * 删除视频
	 * @param watch
	 */
	@RequestMapping("deleteWork.do")
	public String deletework(Privatecourse pse,HttpServletRequest request,Model model) {
		Integer id=Integer.parseInt(request.getParameter("id"));
		pse.setId(id);
		watchservice.deleteWatch(pse);
		return AllpriCourse(pse,request,model);
	}
	
	/**
	 * 观看视频
	 * @param watch
	 */
	@RequestMapping("Work.do")
	public String work(Privatecourse pse,HttpServletRequest request,Model model) {
		Integer id=Integer.parseInt(request.getParameter("id"));
		pse.setId(id);
		Privatecourse pse1=watchservice.selectWatch(pse);
		System.out.println(id);
		System.out.println(pse1.getSpurl());
		model.addAttribute("pse1", pse1);
		return "courseJsp/sp";
	}
}

