package com.courseplatform.service;

import java.util.List;

import com.courseplatform.po.TeacherCourse;

public interface TCService {

	List<TeacherCourse> list(TeacherCourse tc);
	
	TeacherCourse getMessage(int tc);
	
	void updatestatus(TeacherCourse tc);
	
	
}
