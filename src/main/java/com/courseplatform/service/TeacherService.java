package com.courseplatform.service;

import java.util.List;
import java.util.Map;

import com.courseplatform.po.CourseFile;
import com.courseplatform.po.CourseTable;
import com.courseplatform.po.CourseinfoTable;
import com.courseplatform.po.HomeworkTable;
import com.courseplatform.po.HomeworkcommitTable;
import com.courseplatform.po.SharedfileTable;
import com.courseplatform.po.TeacherCourse;
import com.courseplatform.po.User;
import com.courseplatform.po.Userimage;

public interface TeacherService {
   
   
   /**
	 * 用户注册
	 * 
	 * @param user
	 */
	public void registerUser(User user);
	

	/*
	 * 跳转个人中心
	 */
	//public User getUserSafety(User user);

	/*
	 * 更新用户个人信息
	 */
	public void updateUser(User user);

	/*
	 * 更新用户个人密码信息
	 */
	public boolean updateUserPassword(String old,User user,String news);

	
	
	//根据code查找user
	public User findByCode(String  code,User user); 
	 //更新code
	public void updateCode(String  code,User user);
	
	
	//更据账号搜索
	public User getUser(User user);
	
	//寻找密码
	public User searchPassword(User user);
	
	//发布作业
	public void publshwork(HomeworkTable homework);
	
	//得到教師任教課程
    public List<CourseTable> getTeacherCourse(CourseTable course);
    
    //存储文件
    public void courseFile(CourseFile coursefile);
	
    //查询教师发布的作业
    public List<HomeworkTable> teacherWork(HomeworkTable homework);
    
	//查询学生提交的作业
    public List<HomeworkcommitTable> studentUpWork(HomeworkcommitTable homeworkcommit);
    
    //批改作业
    public void checkStudentWork(HomeworkcommitTable homeworkcommit);
    
    //显示作业信息
    public HomeworkcommitTable studentWorkbyno(HomeworkcommitTable homeworkcommit);
    
    //得到所有课程信息
    public List<CourseinfoTable> getCourseAll(Map<String, Integer> param);
    
    //创建课程
 	public void createCourse(TeacherCourse teachercourse);
 	
 	//添加课程信息
    public void addCourse(CourseinfoTable courseinfo);
    
  //更换头像
    public void updataImage(Userimage userimage);
    
    //插入头像
    public void registerImage(Userimage userimage);
    
    //查询头像
    public Userimage imageTo(Userimage userimage);
    
    //更据教师和课程查询学生
    public List<CourseTable> getsCourse(CourseTable course);
    
    /**
	 * 上传共享文件
	 * @param sharedFileNo
	 */
	public void upSharedFile(SharedfileTable sharedfileTable);
	
	/**
	 * 下载共享文件
	 * @param sharedFileNo
	 */
	public List<SharedfileTable> downSharedFile(Map<String, Integer> param);
	
	//根据账号查询课程 
		public  TeacherCourse CourseByuser(TeacherCourse teachercourse);
		
		//根据账号查询课程 ,加载课程名称
		public  List<TeacherCourse> CourseByus(TeacherCourse teachercourse);
		
		//删除教师课程
		public void deletecourse(TeacherCourse teachercourse);
		
		/**
		 * 更据路径查找文件名
		 * @param sharedFileNo
		 */
		public SharedfileTable sSharedFile(SharedfileTable sharedfileTable);
		
		/**
		 * 文件搜索
		 * @param sharedFileNo
		 */
		public List<SharedfileTable> souSharedFile(String fileto);
		
		/**
		 * 得到文件数量
		 */
		public Integer getfileNum();
		
		 /**
		 * 得到课程数量
		 */
		public Integer getcourseNum();
		
		//根据课程编号得到课程信息
	    public CourseinfoTable getCourseByNo(String no);
	    
	    /**
		 * 更新文件下载次数
		 */
		public void updatafileNum(SharedfileTable sharedfileTable);
}

