package com.courseplatform.dao;

import java.util.List;

import com.courseplatform.po.Course;

public interface CourseDao {
	// 学生查询自己的作业
	public List<Course> studentwork(Course course);

	// 得到文件名
	public List<Course> getCourseFile(Course course);
}
