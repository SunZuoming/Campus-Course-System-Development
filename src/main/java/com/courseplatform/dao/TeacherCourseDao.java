package com.courseplatform.dao;

import java.util.List;
import java.util.Map;

import com.courseplatform.po.TeacherCourse;

public interface TeacherCourseDao {
     //创建课程
	public void createCourse(TeacherCourse teachercourse);
	
	//查询所有的课程
		public  List<TeacherCourse> CourseAll(Map<String, Integer> param);
	
	//查询id的课程
	public  TeacherCourse CourseByid(TeacherCourse teachercourse);
	
	//根据账号和课程名称查询课程 
		public  TeacherCourse CourseByuser(TeacherCourse teachercourse);
		
		//根据账号查询课程 ,加载课程名称
		public  List<TeacherCourse> CourseByus(TeacherCourse teachercourse);
	
	/**
	 * szm
	 * @param tc
	 * @return
	 */
	List<TeacherCourse> list(TeacherCourse tc);
	
	void updatestatus(TeacherCourse tc);
	
	TeacherCourse getMessage(int tc);
	
	//删除教师课程
		public void deletecourse(TeacherCourse teachercourse);
		
		/**
		 * 得到教师任课记录数
		 * @param watch
		 */
		public Integer getteacourseNum();
}
