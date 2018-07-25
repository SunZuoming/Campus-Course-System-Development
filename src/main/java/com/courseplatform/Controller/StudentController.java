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

import com.courseplatform.po.Course;
import com.courseplatform.po.CourseTable;
import com.courseplatform.po.HomeworkcommitTable;
import com.courseplatform.po.User;
import com.courseplatform.po.Userimage;
import com.courseplatform.service.StudentService;
import com.courseplatform.service.TeacherService;

@Controller
@RequestMapping("/student/")
public class StudentController {

	@Autowired
	private StudentService studentservice;
	
	@Autowired
	private TeacherService teacherservice;
	
	//得到课程列表
	@RequestMapping("getCourse.do")
	public String getStudentCourse(CourseTable course,HttpServletRequest request,Model model) {
		String s=request.getSession().getAttribute("userno").toString();
		//String s="5237468622";
//		CourseTable course2 = new CourseTable();
		course.setUsernos(s);
		//System.out.println(course.getUsernos());
		List<CourseTable> courses=studentservice.getStudentCourse(course);
		model.addAttribute("co",courses);
		return "studentJsp/studentcourse";
	}
	
	//学生查询自己的作业
	@RequestMapping("getStudentWork.do")
	public String getstudentwork(Course course,HttpServletRequest request,Model model) {
		 HttpSession session = request.getSession();
		String s=request.getSession().getAttribute("userno").toString();
		//String s="5237468622";
		course.setUsernos(s);
		List<Course> work=studentservice.studentwork(course);
		model.addAttribute("work", work);
	    return "studentJsp/studentwork";
	}
		
	//上传作业
	@RequestMapping("upStudentWork.do")
	public String upStudentWork(HomeworkcommitTable homeworkcommit,HttpServletRequest request,Model model) {
	      HttpSession session = request.getSession();
		  String user2=request.getSession().getAttribute("userno").toString();
		  homeworkcommit.setHomeworkcommiter(user2);
		  StringBuilder sb = new StringBuilder();
		  Random rand = new Random();
			for (int i = 0; i < 10; i++) {
				sb.append(rand.nextInt(10));
			}
		  String data = sb.toString();
		  homeworkcommit.setHomeworkcommitno(data);
		  Date d1 = new Date(); // 获取当前时间对象d1
		  SimpleDateFormat dateFormat = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
		  // /格式化当前时间d1,最后呈现格式如2017-09-21 06:09:02
		  String time = dateFormat.format(d1);
		  //System.out.println(time);
		  homeworkcommit.setCommithomeworktime(time);
		  String cuo=session.getAttribute("home").toString();
		  homeworkcommit.setHomeworkno(cuo);
		  System.out.println(cuo);
		  studentservice.upwork(homeworkcommit);
		  model.addAttribute("msg", "提交成功");
		  return "teacherJsp/activemsg";
		
	}
	
	@RequestMapping("up.do")
	public String up(HttpServletRequest request,Model model) {
		 HttpSession session = request.getSession();
		String home=request.getParameter("homeworkno");
		session.setAttribute("home", home);
		//System.out.println(home);
		return "studentJsp/uphomework";
	}
	
	
	//上传文件
		@RequestMapping("upStudentfile.do")  
		public String teacherFileUpload(  
		        @RequestParam("file") 
		        CommonsMultipartFile files[],  
		        HttpServletRequest request, ModelMap model,HomeworkcommitTable homeworkcommit) { 
			 HttpSession session = request.getSession();
		   // List<String> list = new ArrayList<String>(); 
					
		    // 获得项目的路径  
		    ServletContext sc = request.getSession().getServletContext();  
		    // 上传位置  
		    String path = sc.getRealPath("/homeworkfile") + "/"; // 设定文件保存的目录  
		    File f = new File(path);  
		    if (!f.exists())  
		        f.mkdirs();  
		  
		    for (int i = 0; i < files.length; i++) {  
		        // 获得原始文件名  
		        String fileName = files[i].getOriginalFilename();  
		        System.out.println("原始文件名:" + fileName);  
		        System.out.println(path);  
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
		        //String list=path + newFileName;
		        String list=newFileName;
		        System.out.println(list);
		        // 保存文件地址，用于JSP页面回显  
		        model.addAttribute("fileList", list);
		        String user3=request.getSession().getAttribute("userno").toString();
			    //homework.setHomeworkpublisher(user);
				 homeworkcommit.setHomeworkcommiter(user3);
				 StringBuilder sb = new StringBuilder();
				 Random rand = new Random();
			    for (int i1 = 0; i1 < 10; i1++) {
						sb.append(rand.nextInt(10));
				}
				  String data = sb.toString();
				  homeworkcommit.setHomeworkcommitno(data);
				  Date d1 = new Date(); // 获取当前时间对象d1
				  SimpleDateFormat dateFormat = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
				  // /格式化当前时间d1,最后呈现格式如2017-09-21 06:09:02
				  String time = dateFormat.format(d1);
				  //System.out.println(time);
				  homeworkcommit.setCommithomeworktime(time);
				  homeworkcommit.setHomeworkfileurl(list);
				  String cuo=session.getAttribute("home").toString();
				  homeworkcommit.setHomeworkno(cuo);
				  System.out.println(cuo);
				  studentservice.upwork(homeworkcommit);
				  
		    } 
		    model.addAttribute("msg", "提交成功");
		    return "teacherJsp/activemsg";
		} 
		
		//下载资源
		@RequestMapping("listFile.do")  
		public String listFile(HttpServletRequest request,  
		        HttpServletResponse response,Course course,Model model) {
			String s=request.getSession().getAttribute("userno").toString();
			//String s="5237468622";
			course.setUsernos(s);
			List<Course> file=studentservice.getCourseFile(course);
			 model.addAttribute("file", file);
			 
			 // 获取上传文件的目录  
		    ServletContext sc = request.getSession().getServletContext();  
		    // 上传位置  
		    String uploadFilePath = sc.getRealPath("/coursefile") + "/"; // 设定文件保存的目录  
		    // 存储要下载的文件名  
		    Map<String, String> fileNameMap = new HashMap<String, String>();  
		    // 递归遍历filepath目录下的所有文件和目录，将文件的文件名存储到map集合中  
		    listfile(new File(uploadFilePath), fileNameMap, model,request,response,course);// File既可以代表一个文件也可以代表一个目录  
		    // 将Map集合发送到listfile.jsp页面进行显示  
		    //request.setAttribute("fileNameMap", fileNameMap);  
			return "studentJsp/courseware";
		}
		
		public void listfile(File file,Map<String,String> map,Model model,HttpServletRequest request,  
		        HttpServletResponse response,Course course){
			try{
			//如果file代表的不是一个文件，而是一个目录
			if(!file.isFile()){
			//列出该目录下的所有文件和目录
			File files[] = file.listFiles();
			//遍历files[]数组
			for(File f : files){
			//递归
			listfile(f,map, model,request,response,course);
			}
			}else{
			/**
			* 处理文件名，上传后的文件是以uuid_文件名的形式去重新命名的，去除文件名的uuid_部分
			file.getName().indexOf("_")检索字符串中第一次出现"_"字符的位置，如果文件名类似于：9349249849-88343-8344_阿_凡_达.avi
			那么file.getName().substring(file.getName().indexOf("_")+1)处理之后就可以得到阿_凡_达.avi部分
			*/
			String realName = file.getName().substring(file.getName().indexOf("_")+1);
			//file.getName()得到的是文件的原始名称，这个名称是唯一的，因此可以作为key，realName是处理过后的名称，有可能会重复
			map.put(file.getName(), realName);
			}
			}catch(Exception e){
				listFile(request,response,course,model);
			    
			}
		}
		
		//下载课件
		@RequestMapping("downFile.do")  
		public String downFile(HttpServletRequest request,  
		        HttpServletResponse response,Model model) {  
		    // 得到要下载的文件名  
		    String fileName = request.getParameter("filename");  
		    System.out.println(fileName);  
		    try {  
		        //fileName = new String(fileName.getBytes("iso8859-1"), "UTF-8");  
		        System.out.println(fileName);  
		        // 获取上传文件的目录  
		      
		        ServletContext sc = request.getSession().getServletContext();  
		        // 上传位置  
		        String fileSaveRootPath = sc.getRealPath("/coursefile");   
		         
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
		    return "studentJsp/courseware";
		}  
		
		//學生查看已經提交的作業
		@RequestMapping("studenLook.do")  
		public String studenLook(HomeworkcommitTable homeworkcommit, Model model, HttpServletRequest request) {
			String s=request.getSession().getAttribute("userno").toString();
			//String s="5237468622";
			homeworkcommit.setHomeworkcommiter(s);
			List<HomeworkcommitTable> homework=studentservice.studenLook(homeworkcommit);
			model.addAttribute("ho", homework);
		    return "studentJsp/studentlook";
			
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
			return "studentJsp/spersonalcenter";
			}

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
		       // System.out.println("上传图片到:" + path + newFileName);  
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
}
