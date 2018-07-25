
package com.courseplatform.dao;

import java.util.List;

import com.courseplatform.po.CourseTable;

public interface CourseTableDao {

	//得到学生的课程
    public List<CourseTable> getStudentCourse(CourseTable course);
    
    //得到教師任教課程
    public List<CourseTable> getTeacherCourse(CourseTable course);
    
    //更据教师和课程查询学生
    public List<CourseTable> getsCourse(CourseTable course);
}
