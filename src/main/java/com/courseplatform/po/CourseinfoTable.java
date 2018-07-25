package com.courseplatform.po;

public class CourseinfoTable {
    private String courseinfono;

    private String coursename;

    private String courseinfo;
    
    
    
    public String getCourseinfo() {
		return courseinfo;
	}

	public void setCourseinfo(String courseinfo) {
		this.courseinfo = courseinfo;
	}

	public String getCourseinfono() {
        return courseinfono;
    }

    public void setCourseinfono(String courseinfono) {
        this.courseinfono = courseinfono == null ? null : courseinfono.trim();
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename == null ? null : coursename.trim();
    }
}