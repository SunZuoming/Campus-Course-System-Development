package com.courseplatform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courseplatform.dao.TeacherCourseDao;
import com.courseplatform.po.TeacherCourse;
import com.courseplatform.service.TCService;

@Service
public class TCServiceImpl implements TCService {

	@Autowired
	private TeacherCourseDao mapper;

	public List<TeacherCourse> list(TeacherCourse tc) {
		
		return mapper.list(tc);
	}

	public void updatestatus(TeacherCourse tc) {
		mapper.updatestatus(tc);
		
	}

	public TeacherCourse getMessage(int tc) {
		
		return mapper.getMessage(tc);
	}

}
