package com.courseplatform.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courseplatform.dao.CourseFileDao;
import com.courseplatform.dao.CourseTableDao;
import com.courseplatform.dao.CourseinfoTableDao;
import com.courseplatform.dao.HomeworkTableDao;
import com.courseplatform.dao.HomeworkcommitTableDao;
import com.courseplatform.dao.SharedfileTableDao;
import com.courseplatform.dao.TeacherCourseDao;
import com.courseplatform.dao.TeacherDao;
import com.courseplatform.dao.UserimageDao;
import com.courseplatform.po.CourseFile;
import com.courseplatform.po.CourseTable;
import com.courseplatform.po.CourseinfoTable;
import com.courseplatform.po.HomeworkTable;
import com.courseplatform.po.HomeworkcommitTable;
import com.courseplatform.po.SharedfileTable;
import com.courseplatform.po.TeacherCourse;
import com.courseplatform.po.User;
import com.courseplatform.po.Userimage;
import com.courseplatform.service.TeacherService;
import com.courseplatform.util.AES1;
import com.courseplatform.util.MailUtils;


@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherDao teacherDao;
    
	@Autowired
	private HomeworkTableDao homeworkdao;
	
	@Autowired
	private CourseTableDao coursetabledao;
	
	@Autowired
	private CourseinfoTableDao courseinfodao;
	
	@Autowired
	private CourseFileDao coursefiledao;
	
	@Autowired
	private HomeworkcommitTableDao homeworkcommitdao;
	
	@Autowired
	private TeacherCourseDao teachercoursedao;
	
	@Autowired
	private UserimageDao userimagedao;
	
	@Autowired
	private SharedfileTableDao stdao;

	//注册
	public void registerUser(User user) {
		
		teacherDao.registerUser(user);

	}

	// 根据code查找user
	public User findByCode(String  code,User user) {
		return teacherDao.findByCode(user);
	}

	// 更新code
	public void updateCode(String  code,User user) {
		teacherDao.updateCode(user);
	}


	public User getUser(User user) {
		
		return teacherDao.getUser(user);
	}
   
	/*
	 * 更新用户个人信息
	 */
	public void updateUser(User user) {
		teacherDao.updateUser(user);
	}
    
	/*
	 * 更新用户个人密码信息
	 */
	public boolean updateUserPassword(String old,User user,String news) {
		User user1=teacherDao.getUser(user);
		String pass=AES1.AESDncode("abcd", user1.getUserPassword());
		boolean flag=false;
		if(pass.equals(old)) {
			String newss=AES1.AESEncode("abcd", news);
			user1.setUserPassword(newss);
			teacherDao.updateUserPassword(user1);
			flag=true;
		}
		return flag;
	}
	
	//寻找密码
    public User searchPassword(User user) {
    	return teacherDao.searchPassword(user);
    }

  //发布作业
  	public void publshwork(HomeworkTable homework) {
  		homeworkdao.publshwork(homework);
  	}
    
  //得到教師任教課程
    public List<CourseTable> getTeacherCourse(CourseTable course){
    	List<CourseTable> courses=coursetabledao.getTeacherCourse(course);
		for(CourseTable c1:courses) {
			String co=c1.getCourse().getCourseinfono();
			CourseinfoTable cour=courseinfodao.getCourseByNo(co);
			c1.setCourse(cour);
		}
		return courses;
    }
    
    //存储文件
    public void courseFile(CourseFile coursefile) {
    	coursefiledao.courseFile(coursefile);
    }
    
  //查询教师发布的作业
    public List<HomeworkTable> teacherWork(HomeworkTable homework) {
    	return homeworkdao.teacherWork(homework);
    }
    
	//查询学生提交的作业
    public List<HomeworkcommitTable> studentUpWork(HomeworkcommitTable homeworkcommit){
		return homeworkcommitdao.studentUpWork(homeworkcommit);
    	
    }
    
    //批改作业
    public void checkStudentWork(HomeworkcommitTable homeworkcommit) {
    	homeworkcommitdao.checkStudentWork(homeworkcommit);
    }
    
    //显示作业信息
    public HomeworkcommitTable studentWorkbyno(HomeworkcommitTable homeworkcommit) {
    	return homeworkcommitdao.studentWorkbyno(homeworkcommit);
    }
    
    //得到所有课程信息
    public List<CourseinfoTable> getCourseAll(Map<String, Integer> param) {
    	return courseinfodao.getCourseAll(param);
    }
    
    //创建课程
 	public void createCourse(TeacherCourse teachercourse) {
 		teachercoursedao.createCourse(teachercourse);
 	}
 	
 	//添加课程信息
    public void addCourse(CourseinfoTable courseinfo) {
    	courseinfodao.addCourse(courseinfo);
    }
   
  //更换头像
    public void updataImage(Userimage userimage) {
    	userimagedao.updataImage(userimage);
    }
    
    //插入头像
    public void registerImage(Userimage userimage) {
    	userimagedao.registerImage(userimage);
    }
    
    //查询头像
    public Userimage imageTo(Userimage userimage) {
    	return userimagedao.imageTo(userimage);
    }
    
  //更据教师和课程查询学生
    public List<CourseTable> getsCourse(CourseTable course){
    	List<CourseTable> courses=coursetabledao.getsCourse(course);
    	for(CourseTable c1:courses) {
			String co=c1.getUsernos();
			User user=new User();
			user.setUserNo(co);
			User users=teacherDao.getUser(user);
			try {
				MailUtils.homeworkSendMail(users.getEmail());
				System.out.println("法商研究");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return courses;
    }
    
    /**
	 * 上传共享文件
	 * @param sharedFileNo
	 */
	public void upSharedFile(SharedfileTable sharedfileTable) {
		stdao.upSharedFile(sharedfileTable);
	}
	
	/**
	 * 下载共享文件
	 * @param sharedFileNo
	 */
	public List<SharedfileTable> downSharedFile(Map<String, Integer> param){
		return stdao.downSharedFile(param);
	}
	
	//根据账号查询课程 
	public TeacherCourse CourseByuser(TeacherCourse teachercourse) {
		return teachercoursedao.CourseByuser(teachercourse);
	}
	
	//根据账号查询课程 ,加载课程名称
	public  List<TeacherCourse> CourseByus(TeacherCourse teachercourse){
		return teachercoursedao.CourseByus(teachercourse);
	}
	
	//删除教师课程
	public void deletecourse(TeacherCourse teachercourse) {
		teachercoursedao.deletecourse(teachercourse);
	}
	/**
	 * 更据路径查找文件名
	 * @param sharedFileNo
	 */
	public SharedfileTable sSharedFile(SharedfileTable sharedfileTable) {
		return stdao.sSharedFile(sharedfileTable);
	}
	
	/**
	 * 文件搜索
	 * @param sharedFileNo
	 */
	public List<SharedfileTable> souSharedFile(String fileto){
		return stdao.souSharedFile(fileto);
	}
	
	/**
	 * 得到文件数量
	 */
	public Integer getfileNum() {
		return stdao.getfileNum();
	}
	
	 /**
		 * 得到课程数量
		 */
		public Integer getcourseNum() {
			return courseinfodao.getcourseNum();
		}
		
		
		//根据课程编号得到课程信息
	    public CourseinfoTable getCourseByNo(String no) {
			return courseinfodao.getCourseByNo(no);
	    	
	    }
	    
	    /**
		 * 更新文件下载次数
		 */
		public void updatafileNum(SharedfileTable sharedfileTable) {
			stdao.updatafileNum(sharedfileTable);
		}
}
