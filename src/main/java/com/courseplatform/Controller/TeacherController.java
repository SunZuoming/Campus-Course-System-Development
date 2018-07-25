package com.courseplatform.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.courseplatform.po.Course;
import com.courseplatform.po.CourseFile;
import com.courseplatform.po.CourseTable;
import com.courseplatform.po.CourseinfoTable;
import com.courseplatform.po.HomeworkTable;
import com.courseplatform.po.HomeworkcommitTable;
import com.courseplatform.po.JurisdictionTable;
import com.courseplatform.po.SharedfileTable;
import com.courseplatform.po.TeacherCourse;
import com.courseplatform.po.User;
import com.courseplatform.po.Userimage;
import com.courseplatform.service.StudentService;
import com.courseplatform.service.TeacherService;
import com.courseplatform.util.AES1;
import com.courseplatform.util.MailUtils;
import com.courseplatform.util.UUIDUtils;
import com.courseplatform.util.yzm;

@Controller
@RequestMapping("/teacher/")
public class TeacherController {

	
	@Autowired
	private TeacherService teacherservice;
	
	@Autowired
	private StudentService studentservice;

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
				if(users.getUserState().equals("3")) {
					model.addAttribute("loginwo", "审核未通过！");
					return "teacherJsp/loginwo";
				}
				// 教师
				session.setAttribute("userno", users.getUserNo());
				// System.out.println( user.getUserNo());
				userimage.setUserno(users.getUserNo());
				System.out.println(user.getUserNo());
				Userimage image = teacherservice.imageTo(userimage);
				session.setAttribute("im", image);
				return "";
			} else {
				// 学生
				session.setAttribute("userno", users.getUserNo());
				userimage.setUserno(users.getUserNo());
				System.out.println(user.getUserNo());
				Userimage image = teacherservice.imageTo(userimage);
				session.setAttribute("im", image);
				return "";
			}
		}
		

	}

	// 注册
	@RequestMapping("register.do")
	public String register(User user, Model model,HttpServletRequest request,Userimage userimage) {
		String code = UUIDUtils.getUUID() + UUIDUtils.getUUID();
		StringBuilder sb = new StringBuilder();
		Random rand = new Random();
		for (int i = 0; i < 10; i++) {
			sb.append(rand.nextInt(10));
		}
		String jsb=request.getParameter("js");
		System.out.println("24"+jsb);
		if(jsb==null){
			user.setRoleId("JS003");
			user.setUserState("0");
		}else{
			user.setRoleId("JS002");
			user.setUserState("3");
		}
		String data = sb.toString();
		user.setUserNo(data);
		user.setUserCode(code);
			String up=request.getParameter("userPassword");
			String pass=AES1.AESEncode("abcd", up);
			user.setUserPassword(pass);
			teacherservice.registerUser(user);
			userimage.setUserno(data);
		    HttpSession session = request.getSession();
			teacherservice.registerImage(userimage);
			Userimage image=teacherservice.imageTo(userimage);
			session.setAttribute("im",image);
			if(jsb==null) {
				try {
					MailUtils.sendMail(user.getEmail(), user.getUserCode());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				model.addAttribute("loginwo", "您已经注册成功！ 请去邮箱激活");
			}else {
				model.addAttribute("loginwo", "您已经注册成功！ 请等待管理员审核");
			}
		
		return "teacherJsp/loginwo";
	}

	// 激活email
	@RequestMapping("ActiveEmail.do")
	public String ActiveEmail(User user, HttpServletRequest request, Model model) {
		User users=teacherservice.findByCode(user.getUserCode(), user);
		if (users != null&&!users.getUserCode().equals("")) {
			teacherservice.updateCode(user.getUserCode(), user);
			model.addAttribute("loginwo", "您已经激活成功！您的账号为"+users.getUserNo() +",请去登录！");
		} else {
			model.addAttribute("loginwo", "您已激活");
		}
		return "teacherJsp/loginwo";

	}

	// 验证码
	@RequestMapping("yzm.do")
	public void Yzm(HttpServletRequest request,
			HttpServletResponse response) {
		yzm y=new yzm();
		try {
			y.yzml(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/*
	 * 更新用户个人信息
	 */
	@RequestMapping("updateUser.do")
	public String updateUser(User user, Model model, HttpServletRequest request) {
		user.setUserNo(request.getSession().getAttribute("userno").toString());
		System.out.println(user.getUserNo());
		teacherservice.updateUser(user);
		System.out.println(user.getUserName());
		model.addAttribute("user", user);
		return getUserto(user, model, request);
	}
	
	/*
	 * 得到用户个人信息
	 */
	@RequestMapping("getUserto.do")
	public String getUserto(User user, Model model, HttpServletRequest request) {
		String us=request.getSession().getAttribute("userno").toString();
		user.setUserNo(us);
		User userss = teacherservice.getUser(user);
		model.addAttribute("user", userss);
		return "teacherJsp/personalcenter";
		}
	
	@RequestMapping("updatep.do")
	public String getUsertos(User user, Model model, HttpServletRequest request) {
		String us=request.getSession().getAttribute("userno").toString();
		user.setUserNo(us);
		User userss = teacherservice.getUser(user);
		model.addAttribute("user", userss);
		return "teacherJsp/updatepassword";
		}
	
	@RequestMapping("updatep1.do")
	public String getUsertos1(User user, Model model, HttpServletRequest request) {
		String us=request.getSession().getAttribute("userno").toString();
		user.setUserNo(us);
		User userss = teacherservice.getUser(user);
		model.addAttribute("user", userss);
		return "teacherJsp/createcou";
		}
      
	//更新密码
	@RequestMapping("updateUserPassword.do")
	public String updateUserPassword(User user, HttpServletRequest request,Model model) {
		String old = request.getParameter("oldpassword");
		String news = user.getUserPassword();
		user.setUserNo(request.getSession().getAttribute("userno").toString());
		boolean flag =  teacherservice.updateUserPassword(old,user,news);
		if (flag) {
			model.addAttribute("msg", "修改密码成功！");
			return "teacherJsp/activemsg";
		} else {
			model.addAttribute("msg", "旧密码不正确！");
			return "teacherJsp/activemsg";
		}
	}
	
	//寻找密码
	@RequestMapping("seacherPassword.do")
	public String seacherPassword(User user, Model model, HttpServletRequest request, HttpServletResponse response) {
		User users = teacherservice.searchPassword(user);
		if (users==null) {
			model.addAttribute("loginwo", "账号或手机号错误，请重新输入！");
			return "teacherJsp/loginwo";
		}else {
			String pass=AES1.AESDncode("abcd", users.getUserPassword());
			try {
				MailUtils.searchSendMail(users.getEmail(),pass);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model.addAttribute("loginwo", "您的密码已经发送到邮箱！");
			return "teacherJsp/loginwo";
		}
		
	}
	
	//发布作业
	@RequestMapping("publishWork.do")
	public String publishWork(HomeworkTable homework,TeacherCourse teachercourse,User user, Model model,HttpServletRequest request) {
		String userto=request.getSession().getAttribute("userno").toString();
		teachercourse.setTeacherno(request.getSession().getAttribute("userno").toString());
		//homework.setHomeworkpublisher(user);
		user.setUserNo(userto);
		User users = teacherservice.getUser(user);
		homework.setUsername(users.getUserName());
		homework.setHomeworkpublisher(userto);
		StringBuilder sb = new StringBuilder();
		Random rand = new Random();
		for (int i = 0; i < 10; i++) {
			sb.append(rand.nextInt(10));
		}
		String data = sb.toString();
		homework.setHomeworkno(data);
		Date d1 = new Date(); // 获取当前时间对象d1
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		// /格式化当前时间d1,最后呈现格式如2017-09-21 06:09:02
		String time = dateFormat.format(d1);
		//System.out.println(time);
		homework.setHomeworkpublishtime(time);
		String coursename=request.getParameter("co.coursename");
		System.out.println(coursename);
		homework.setHomeworkcourse(coursename);
		teacherservice.publshwork(homework);
	
		CourseTable course=new CourseTable();
		course.setUsernot(userto);
		//邮件发送提示
		course.setCoursename(coursename);
		teacherservice.getsCourse(course);
		//加载作业发布所选课程	
		List<TeacherCourse> tcll=teacherservice.CourseByus(teachercourse);
		model.addAttribute("co",tcll);
		return "teacherJsp/publishwork";
	}
	
	
	//加载作业发布所选课程
	@RequestMapping("loadCourse.do")
	public String loadCourse(Model model,TeacherCourse teachercourse,HttpServletRequest request) {
		teachercourse.setTeacherno(request.getSession().getAttribute("userno").toString());
		//course.setUsernot("9780329693");
		List<TeacherCourse> tcll=teacherservice.CourseByus(teachercourse);
		model.addAttribute("co",tcll);
		return "teacherJsp/publishwork";
	}
	
	//上传课件
	@RequestMapping("uploadFile.do")  
	public String teacherFileUpload(  
	        @RequestParam("file") 
	        CommonsMultipartFile files[],  
	        HttpServletRequest request, ModelMap model,CourseFile coursefile,TeacherCourse teachercourse) {  
	  
	   // List<String> list = new ArrayList<String>(); 
				
	    // 获得项目的路径  
	    ServletContext sc = request.getSession().getServletContext();  
	    // 上传位置  
	    String path = sc.getRealPath("/coursefile") + "/"; // 设定文件保存的目录  
	    File f = new File(path);  
	    if (!f.exists())  
	        f.mkdirs();  
	  
	    for (int i = 0; i < files.length; i++) {  
	        // 获得原始文件名  
	        String fileName = files[i].getOriginalFilename();  
	        System.out.println("原始文件名:" + fileName);  
	        // 新文件名  
	        //String newFileName = UUID.randomUUID() + fileName;
	        String newFileName = fileName;
	        
	        if (!files[i].isEmpty()) { 
	            try {  
	                FileOutputStream fos = new FileOutputStream(path  
	                        + newFileName);  
	                InputStream in = files[i].getInputStream();  
	                int b = 0;  
	                while ((b = in.read()) != -1) {  
	                    fos.write(b);  
	                }  
	                fos.close();  
	                in.close();  
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }
	            request.setAttribute("file1", "文件传输完成");
	        }
	       // System.out.println("上传图片到:" + path + newFileName);  
	       // list.add(path + newFileName);  
	        String list=newFileName;
	        System.out.println(list);
	        // 保存文件地址，用于JSP页面回显  
	        model.addAttribute("fileList", list);
	        String courseuser=request.getSession().getAttribute("userno").toString();
			coursefile.setCourseuser(courseuser);
	       // coursefile.setCourseuser("9780329693");
	        String cou=request.getParameter("co.coursename");
	        coursefile.setCoursename(cou);
			coursefile.setFiledress(list);
			teacherservice.courseFile(coursefile);
	        
	    }  
	    // 保存文件地址，用于JSP页面回显  
	    //model.addAttribute("fileList", list);
	    String courseuser=request.getSession().getAttribute("userno").toString();
		coursefile.setCourseuser(courseuser);
		teachercourse.setTeacherno(request.getSession().getAttribute("userno").toString());
		List<TeacherCourse> tcll=teacherservice.CourseByus(teachercourse);
		model.addAttribute("co",tcll);
	    return "teacherJsp/publishwork";  
	} 
	
	//教师查询发布的作业
	@RequestMapping("teacherwork.do")
	public String getteacherworkto(HomeworkTable homework, Model model, HttpServletRequest request) {
		String us=request.getSession().getAttribute("userno").toString();
		homework.setHomeworkpublisher(us);
		//homework.setHomeworkpublisher("9780329693");
		List<HomeworkTable> homeworks = teacherservice.teacherWork(homework);
		model.addAttribute("homework", homeworks);
		return "teacherJsp/teacherwork";
    }
	
	//查询学生提交的作业
	@RequestMapping("studentupwork.do")
	public String getstudentto(HomeworkcommitTable homeworkcommit, Model model, HttpServletRequest request) {
		String us=request.getSession().getAttribute("userno").toString();
		homeworkcommit.setHomeworkpublisher(us);
		//homeworkcommit.setHomeworkpublisher("9780329693");
		List<HomeworkcommitTable> homeworks = teacherservice.studentUpWork(homeworkcommit);
		model.addAttribute("work", homeworks);
		return "teacherJsp/checkstudentwork";
	}
	
	/*@RequestMapping("up.do")
	public String up(HttpServletRequest request,Model model) {
		 HttpSession session = request.getSession();
		String home1=request.getParameter("homeworkno");
		session.setAttribute("home1", home1);
		//System.out.println(home);
		return "teacherJsp/teachercorrect";
	}*/
	
	//显示作业信息
	@RequestMapping("studentWorkbyno.do")
	public String studentWorkbyno(HomeworkcommitTable homeworkcommit, Model model, HttpServletRequest request) {
		 HttpSession session = request.getSession();
		 String home1=request.getParameter("homeworkcommitno");
	     session.setAttribute("home1", home1);
		 homeworkcommit.setHomeworkcommitno(home1);
		 HomeworkcommitTable ho= teacherservice.studentWorkbyno(homeworkcommit);
		 String urll=ho.getHomeworkfileurl();
		 session.setAttribute("urll", urll);
	     model.addAttribute("ho", ho);
		 return "teacherJsp/tachercorrect";
	}
	
	//批改作业
	@RequestMapping("checkStudentWork.do")
    public String checkStudentWork(HomeworkcommitTable homeworkcommit, Model model, HttpServletRequest request) {
    	 HttpSession session = request.getSession();
		 String cuo=session.getAttribute("home1").toString();
		 homeworkcommit.setHomeworkcommitno(cuo);
    	 teacherservice.checkStudentWork(homeworkcommit);
    	 model.addAttribute("msg", "保存成功！");
		 return "teacherJsp/activemsg";
    }
			
	//下载学生上传作业文件
			@RequestMapping("downFile.do")  
			public String downFile(HttpServletRequest request,  
			        HttpServletResponse response,Model model) {  
			   // System.out.println("1");  
			    // 得到要下载的文件名  
			    HttpSession session = request.getSession();
			    String fileName=session.getAttribute("urll").toString();
			   // String fileName = request.getParameter("filename");  
			    System.out.println(fileName);  
			    try {  
			       // fileName = new String(fileName.getBytes("iso8859-1"), "UTF-8");  
			        //System.out.println("fileName");  
			        // 获取上传文件的目录  
			      
			        ServletContext sc = request.getSession().getServletContext();  
			        //System.out.println("4");  
			        // 上传位置  
			        String fileSaveRootPath = sc.getRealPath("/homeworkfile");   
			         
			        System.out.println(fileSaveRootPath + "\\" + fileName);  
			        // 得到要下载的文件  
			        File file = new File(fileSaveRootPath + "\\" + fileName);  
			       // File file = new File(fileName); 
			        // 如果文件不存在  
			        if (!file.exists()) {  
			        	model.addAttribute("msg", "要下载的资源已被删除！");
						return "teacherJsp/activemsg";
			        }  
			        // 处理文件名  
			        String realname = fileName.substring(fileName.indexOf("_") + 1);  
			        // 设置响应头，控制浏览器下载该文件  
			        response.setHeader("content-disposition", "attachment;filename="  
			                + URLEncoder.encode(realname, "UTF-8"));  
			        // 读取要下载的文件，保存到文件输入流  
			        FileInputStream in = new FileInputStream(fileSaveRootPath + "\\" + fileName);
			        //FileInputStream in = new FileInputStream(fileName);  
			        // 创建输出流  
			        OutputStream out = response.getOutputStream();  
			        // 创建缓冲区  
			        byte buffer[] = new byte[1024];  
			        int len = 0;  
			        // 循环将输入流中的内容读取到缓冲区当中  
			        while ((len = in.read(buffer)) > 0) {  
			            // 输出缓冲区的内容到浏览器，实现文件下载  
			            out.write(buffer, 0, len);  
			        }  
			        // 关闭文件输入流  
			        in.close();  
			        // 关闭输出流  
			        out.flush();
			        out.close();  
					out=null; 
					return null;
			    } catch (Exception e) {  
			  
			    }  
			    return "teacherJsp/tachercorrect";
			}  

	
	//加载所选课程
private static final int LENGTH1 = 3;
	@RequestMapping("loadCreateCourse.do")
	public ModelAndView loadCourse(Model model,String nowPageParam,CourseinfoTable courseinfo,HttpServletRequest request) {
		User user=new User();
		String us=request.getSession().getAttribute("userno").toString();
		user.setUserNo(us);
		User userss = teacherservice.getUser(user);
		model.addAttribute("user", userss);
		ModelAndView mv = new ModelAndView("teacherJsp/createcourse");
		
		long total = teacherservice.getcourseNum();
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
		List<CourseinfoTable> courseinfos=teacherservice.getCourseAll(param);
		//model.addAttribute("file", sf);
		return mv.addObject("cou", courseinfos)
				.addObject("totalPage", totalPage)
				.addObject("initPage", nowPage)
				.addObject("teacherCount", total);
		
	}
	
	//创建课程
	@RequestMapping("CreateCourse.do")
	public String createCourse(TeacherCourse teachercourse,Model model,HttpServletRequest request) {
		String us=request.getSession().getAttribute("userno").toString();
		User user=new User();
		user.setUserNo(us);
		teachercourse.setTeacherno(us);
		User users = teacherservice.getUser(user);
		teachercourse.setTeachername(users.getUserName());
		String coursename=request.getParameter("courseinfono");
		CourseinfoTable courseinfo=teacherservice.getCourseByNo(coursename);
        teachercourse.setCoursename(courseinfo.getCoursename());
		TeacherCourse tcou=teacherservice.CourseByuser(teachercourse);
		if(tcou==null) {
			teacherservice.createCourse(teachercourse);
			model.addAttribute("msg", "课程创建成功，请等待审核！");
			return "teacherJsp/activemsg";
		}else {
			model.addAttribute("msg", "您已经创建此课程！");
			return "teacherJsp/activemsg";
		}
	}
	
	//创建并增加课程
	@RequestMapping("AddCourse.do")
	public String addCourse(TeacherCourse teachercourse,CourseinfoTable courseinfo,Model model,HttpServletRequest request) {
		String us=request.getSession().getAttribute("userno").toString();
		User user=new User();
		user.setUserNo(us);
		StringBuilder sb = new StringBuilder();
		Random rand = new Random();
		for (int i = 0; i < 10; i++) {
			sb.append(rand.nextInt(10));
		}
		String data = sb.toString();
		courseinfo.setCourseinfono(data);
		teachercourse.setTeacherno(us);
		User users = teacherservice.getUser(user);
		teachercourse.setTeachername(users.getUserName());
		TeacherCourse tcou=teacherservice.CourseByuser(teachercourse);
		if(tcou==null) {
			teacherservice.createCourse(teachercourse);
			teacherservice.addCourse(courseinfo);
			model.addAttribute("msg", "课程创建成功，请等待审核！");
			return "teacherJsp/activemsg";
		}else {
			model.addAttribute("msg", "您已经创建此课程！");
			return "teacherJsp/activemsg";
		}
	}
	
	//上传头像
	@RequestMapping("imageFile.do")  
	public String teacherFileUpload1(  
	        @RequestParam("file") 
	        CommonsMultipartFile files[],  
	        HttpServletRequest request, Model model,Userimage userimage,User user) {  
	  
	   // List<String> list = new ArrayList<String>(); 
				
	    // 获得项目的路径  
	    ServletContext sc = request.getSession().getServletContext();  
	    // 上传位置  
	    String path = sc.getRealPath("/imagefile") + "/"; // 设定文件保存的目录  

	    File f = new File(path);  
	    if (!f.exists())  
	        f.mkdirs();  
	  
	    for (int i = 0; i < files.length; i++) {  
	        // 获得原始文件名  
	        String fileName = files[i].getOriginalFilename();  
	        System.out.println("原始文件名:" + fileName);  
	        // 新文件名  
	        //String newFileName = UUID.randomUUID() + fileName;
	        String newFileName = fileName;
	        
	        if (!files[i].isEmpty()) { 
	            try {  
	                FileOutputStream fos = new FileOutputStream(path  
	                        + newFileName);  
	                InputStream in = files[i].getInputStream();  
	                int b = 0;  
	                while ((b = in.read()) != -1) {  
	                    fos.write(b);  
	                }  
	                fos.close();  
	                in.close();  
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }
	            request.setAttribute("file1", "文件传输完成");
	        }
	       System.out.println("上传图片到:" + path + newFileName);  
	       //list.add(path + newFileName);  
	        String list="imagefile/"+newFileName;
	        System.out.println(list);
	        // 保存文件地址，用于JSP页面回显  
	        model.addAttribute("fileList", list);
	        String courseuser=request.getSession().getAttribute("userno").toString();
	        userimage.setUserno(courseuser);
	        userimage.setImagedress(list);
			teacherservice.updataImage(userimage);
			HttpSession session = request.getSession();
			Userimage image=teacherservice.imageTo(userimage);
			session.setAttribute("im",image);
			user.setUserNo(request.getSession().getAttribute("userno").toString());
	    }  
	    return getUserto(user, model, request);  
	} 
	
	//退出登录
	@RequestMapping("zxdl.do")
	public String zxdl(Model model,HttpServletRequest request) {
	     HttpSession session = request.getSession();
	     session.invalidate();
	     return "teacherJsp/";
	}
	
	
	/**
	 * 上传共享文件
	 * @param sharedFileNo
	 */
	@RequestMapping("upSharedFile.do")
	public String upSharedFile(@RequestParam("file") CommonsMultipartFile files[], HttpServletRequest request,
			ModelMap model, SharedfileTable sharedfileTable) {
		HttpSession session = request.getSession();
		String user2 = request.getSession().getAttribute("userno").toString();
		sharedfileTable.setUploader(user2);
		StringBuilder sb = new StringBuilder();
		Random rand = new Random();
		for (int i = 0; i < 10; i++) {
			sb.append(rand.nextInt(10));
		}
		String data = sb.toString();
		sharedfileTable.setSharedfileno(data);
		
		Date d1 = new Date(); // 获取当前时间对象d1
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// /格式化当前时间d1,最后呈现格式如2017-09-21 06:09:02
		String time = dateFormat.format(d1);
		// System.out.println(time);
		sharedfileTable.setUploadtime(time);
		
		// List<String> list = new ArrayList<String>();

		// 获得项目的路径
		ServletContext sc = request.getSession().getServletContext();
		// 上传位置
		//--String path = sc.getRealPath("/gxfile") + "/"; // 设定文件保存的目录
		String path="D:/coursePlatform/gxFile/";
		File f = new File(path);
		if (!f.exists())
			f.mkdirs();

		for (int i = 0; i < files.length; i++) {
			// 获得原始文件名
			String fileName = files[i].getOriginalFilename();
			System.out.println("原始文件名:" + fileName);
			// 新文件名
			 UUID newFileName = UUID.randomUUID();
			//--String newFileName = fileName;

			if (!files[i].isEmpty()) {
				try {
					FileOutputStream fos = new FileOutputStream(path + newFileName);
					InputStream in = files[i].getInputStream();
					int b = 0;
					while ((b = in.read()) != -1) {
						fos.write(b);
					}
					fos.close();
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				request.setAttribute("file1", "文件传输完成");
			}
			System.out.println("上传图片到:" + path + newFileName);
			//list.add(path + newFileName);
			String list=path + newFileName;
			//--String list = newFileName;
			System.out.println(list);
			// 保存文件地址，用于JSP页面回显
			model.addAttribute("fileList", list);
			sharedfileTable.setSharedfileurl(list);
			sharedfileTable.setSharedfilename(fileName);
			teacherservice.upSharedFile(sharedfileTable);
            
		}
		 return "studentJsp/upsharefile";
	}
	
	private static final int LENGTH = 6;
			//下载共享文件
			@RequestMapping("listshareFileto.do")  
			public ModelAndView listFileto(HttpServletRequest request, String nowPageParam, 
			        HttpServletResponse response,SharedfileTable sharedfileTable,Model model) {
				
				ModelAndView mv = new ModelAndView("studentJsp/sharefile");
				
					long total = teacherservice.getfileNum();
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
					List<SharedfileTable> sf=teacherservice.downSharedFile(param);
					
					//model.addAttribute("file", sf);
					return mv.addObject("file", sf)
							.addObject("totalPage", totalPage)
							.addObject("initPage", nowPage)
							.addObject("teacherCount", total);
				
			}
			
			//搜索共享文件
			@RequestMapping("listshareFileshou.do")  
			public ModelAndView souSharedFile(HttpServletRequest request,  
			        HttpServletResponse response,String nowPageParam,SharedfileTable sharedfileTable,Model model) {
				   String fileto=request.getParameter("fileto");
					System.out.println(fileto);
				   if(!fileto.equals("")) {
					   ModelAndView mv = new ModelAndView("studentJsp/sharefile");
					   List<SharedfileTable> sf=teacherservice.souSharedFile(fileto);
					   return mv.addObject("file", sf);
					   
				   }else {
					   return  listFileto(request,nowPageParam,response,sharedfileTable,model);
				   }
				
			}
			
			//下载
			@RequestMapping("downSharedFile.do")  
			public String downSharedFile(HttpServletRequest request,  
			        HttpServletResponse response,Model model,SharedfileTable sharedfileTable) {  
			    System.out.println("1");  
			    // 得到要下载的文件名  
			    String fileNameurl = request.getParameter("filename");  
			    sharedfileTable.setSharedfileurl(fileNameurl);
			    SharedfileTable sh=teacherservice.sSharedFile(sharedfileTable);
			    System.out.println(fileNameurl);  
			    try {  
			        //fileName = new String(fileName.getBytes("iso8859-1"), "UTF-8");  
			        System.out.println("fileName");  
			        // 获取上传文件的目录  
			      
			        //ServletContext sc = request.getSession().getServletContext();  
			       // System.out.println("4");  
			        // 上传位置  
			        //String fileSaveRootPath = sc.getRealPath("/gxfile");   
			        String fileSaveRootPath="D:/coursePlatform/gxFile/";
			        //System.out.println(fileSaveRootPath + "\\" + fileName);  
			        System.out.println(fileSaveRootPath);
			        // 得到要下载的文件  
			        //File file = new File(fileSaveRootPath + "\\" + fileName);  
			         File file = new File(fileNameurl); 
			        // 如果文件不存在  
			        if (fileNameurl==null) {  
			        	model.addAttribute("msg", "要下载的资源已被删除！");
						return "teacherJsp/activemsg";
			        }  
			        // 处理文件名  
			        //String realname = fileNameurl.substring(fileNameurl.indexOf("_") + 1); 
			        String realname =sh.getSharedfilename();
			        // 设置响应头，控制浏览器下载该文件  
			        response.setHeader("content-disposition", "attachment;filename="  
			                + URLEncoder.encode(realname, "UTF-8"));  
			        // 读取要下载的文件，保存到文件输入流  
			        //FileInputStream in = new FileInputStream(fileSaveRootPath + "\\" + fileNameurl);
			    
			       FileInputStream in = new FileInputStream(fileNameurl);  
			        // 创建输出流  
			        OutputStream out = response.getOutputStream();  
			        // 创建缓冲区  
			        byte buffer[] = new byte[1024];  
			        int len = 0;  
			        // 循环将输入流中的内容读取到缓冲区当中  
			        while ((len = in.read(buffer)) > 0) {  
			            // 输出缓冲区的内容到浏览器，实现文件下载  
			            out.write(buffer, 0, len);  
			        }  
			        // 关闭文件输入流  
			        in.close();  
			        // 关闭输出流  
			        out.flush();
			        out.close();  
					out=null; 
					
					int i;
					SharedfileTable sh1=teacherservice.sSharedFile(sharedfileTable);
				    i=sh1.getSharedfiledownloadnum()+1;
				    sharedfileTable.setSharedfiledownloadnum(i);
				    teacherservice.updatafileNum(sharedfileTable);
					 
					 
					return null;
			    } catch (Exception e) {  
			  
			    }  
			    return "studentJsp/sharefile";
			} 
			
	// 查询教师已经创建的课程

	@RequestMapping("loadCoursebyuser.do")
	public String loadCoursebyuser(Model model, TeacherCourse teachercourse, HttpServletRequest request) {
		User user=new User();
		String us=request.getSession().getAttribute("userno").toString();
		user.setUserNo(us);
		User userss = teacherservice.getUser(user);
		model.addAttribute("user", userss);
		teachercourse.setTeacherno(request.getSession().getAttribute("userno").toString());
		List<TeacherCourse> tcll = teacherservice.CourseByus(teachercourse);
		model.addAttribute("co1", tcll);
		return "teacherJsp/teacherbycourse";
	}
	

	//删除教师课程
	@RequestMapping("deletecourseby.do")
	public String deletecourse(Model model, TeacherCourse teachercourse, HttpServletRequest request) {
		Integer id=Integer.parseInt(request.getParameter("id"));
		teachercourse.setId(request.getParameter("id"));
	    teacherservice.deletecourse(teachercourse);
		
		//
	    User user=new User();
		String us=request.getSession().getAttribute("userno").toString();
		user.setUserNo(us);
		User userss = teacherservice.getUser(user);
		model.addAttribute("user", userss);
		teachercourse.setTeacherno(request.getSession().getAttribute("userno").toString());
		List<TeacherCourse> tcll = teacherservice.CourseByus(teachercourse);
		model.addAttribute("co1", tcll);
		return "teacherJsp/teacherbycourse";
	}
	
	    //退出登录
		@RequestMapping("zxdl.do")
		public String zxdl(Model model,HttpServletRequest request,HttpServletResponse response) {
		     HttpSession session = request.getSession();
		     
		     return "teacherJsp/teacherlogin";
		}
	
}
