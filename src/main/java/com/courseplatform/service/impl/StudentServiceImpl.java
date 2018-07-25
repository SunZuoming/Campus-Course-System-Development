package com.courseplatform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courseplatform.dao.CourseDao;
import com.courseplatform.dao.CourseTableDao;
import com.courseplatform.dao.CourseinfoTableDao;
import com.courseplatform.dao.HomeworkTableDao;
import com.courseplatform.dao.HomeworkcommitTableDao;
import com.courseplatform.po.Course;
import com.courseplatform.po.CourseTable;
import com.courseplatform.po.CourseinfoTable;
import com.courseplatform.po.HomeworkcommitTable;
import com.courseplatform.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private CourseTableDao coursetabledao;

	@Autowired
	private CourseinfoTableDao courseinfo;

	@Autowired
	private HomeworkTableDao homeworkdao;

	@Autowired
	private HomeworkcommitTableDao homeworkcommitdao;

	@Autowired
	private CourseDao coursedao;

	// 得到课程列表
	public List<CourseTable> getStudentCourse(CourseTable course) {
		// System.out.println(course.getUsernos());
		List<CourseTable> courses = coursetabledao.getStudentCourse(course);
		for (CourseTable c1 : courses) {
			String co = c1.getCourse().getCourseinfono();
			CourseinfoTable cour = courseinfo.getCourseByNo(co);
			c1.setCourse(cour);
		}
		return courses;
	}

	// 上传作业
	public void upwork(HomeworkcommitTable homeworkcommit) {
		homeworkcommitdao.upwork(homeworkcommit);
	}

	public List<Course> studentwork(Course course) {
		return coursedao.studentwork(course);

	}

	// 得到文件名
	public List<Course> getCourseFile(Course course) {
		return coursedao.getCourseFile(course);
	}

	// 學生查看已經提交的作業
	public List<HomeworkcommitTable> studenLook(HomeworkcommitTable homeworkcommit) {
		return homeworkcommitdao.studenLook(homeworkcommit);
	}

}
