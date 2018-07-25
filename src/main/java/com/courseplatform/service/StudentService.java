package com.courseplatform.service;

import java.util.List;

import com.courseplatform.po.Course;
import com.courseplatform.po.CourseTable;
import com.courseplatform.po.HomeworkcommitTable;

public interface StudentService {
	//得到课程列表
	public List<CourseTable> getStudentCourse(CourseTable course);

	//学生查询自己的作业
	public List<Course> studentwork(Course course);
	
	//上传作业
	public void upwork(HomeworkcommitTable homeworkcommit);
	
	//得到文件名
    public List<Course> getCourseFile(Course course);
    
  //學生查看已經提交的作業
    public List<HomeworkcommitTable> studenLook(HomeworkcommitTable homeworkcommit);
}
