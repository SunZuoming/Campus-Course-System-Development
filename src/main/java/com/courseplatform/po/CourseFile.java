package com.courseplatform.po;

public class CourseFile {
     private Integer id;
     private String courseuser;
     private String coursename;
     private String homeworkno;
     private String filedress;
     private String homeworkcommitno;
     
	public String getHomeworkcommitno() {
		return homeworkcommitno;
	}
	public void setHomeworkcommitno(String homeworkcommitno) {
		this.homeworkcommitno = homeworkcommitno;
	}
	public String getHomeworkno() {
		return homeworkno;
	}
	public void setHomeworkno(String homeworkno) {
		this.homeworkno = homeworkno;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCourseuser() {
		return courseuser;
	}
	public void setCourseuser(String courseuser) {
		this.courseuser = courseuser;
	}
	public String getFiledress() {
		return filedress;
	}
	public void setFiledress(String list) {
		this.filedress = list;
	}
     
}
