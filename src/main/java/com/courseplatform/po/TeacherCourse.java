package com.courseplatform.po;

public class TeacherCourse {
    private String id;
    private String teacherno;
    private String teachername;
    private String coursename;
    private String coursefalg;
    private String courseinfo;
    
	public String getCourseinfo() {
		return courseinfo;
	}
	public void setCourseinfo(String courseinfo) {
		this.courseinfo = courseinfo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTeacherno() {
		return teacherno;
	}
	public void setTeacherno(String teacherno) {
		this.teacherno = teacherno;
	}
	public String getTeachername() {
		return teachername;
	}
	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public String getCoursefalg() {
		return coursefalg;
	}
	public void setCoursefalg(String coursefalg) {
		this.coursefalg = coursefalg;
	}
    
}
