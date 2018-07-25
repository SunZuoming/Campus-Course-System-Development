package com.courseplatform.po;

public class CourseTable {
	private String id;
	private String usernos;

	private String usernot;
	
	private String  username;
    
    private CourseinfoTable course;
    
    private String  coursename;
    private String  courseps;
    

    public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCourseps() {
		return courseps;
	}

	public void setCourseps(String courseps) {
		this.courseps = courseps;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public CourseinfoTable getCourse() {
		return course;
	}

	public void setCourse(CourseinfoTable course) {
		this.course = course;
	}

	public String getUsernot() {
        return usernot;
    }

    public void setUsernot(String usernot) {
        this.usernot = usernot == null ? null : usernot.trim();
    }

    public String getUsernos() {
		return usernos;
	}

	public void setUsernos(String usernos) {
		this.usernos = usernos;
	}

	
}