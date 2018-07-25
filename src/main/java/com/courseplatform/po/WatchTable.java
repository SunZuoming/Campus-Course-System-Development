package com.courseplatform.po;

public class WatchTable {
	/** id*/
	private Integer id;
	/** 学生编号 */
	private String usernos;
	/** 任课老师编号 */
	private String usernot;
	/** 课程编号 */
    private String courseinfono;
	/** 关注标识 1-关注  0-未关注*/
    private String courseps;
    
    private String username;
    
    private String coursename;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsernos() {
		return usernos;
	}

	public void setUsernos(String usernos) {
		this.usernos = usernos;
	}

	public String getUsernot() {
		return usernot;
	}

	public void setUsernot(String usernot) {
		this.usernot = usernot;
	}

	public String getCourseinfono() {
		return courseinfono;
	}

	public void setCourseinfono(String courseinfono) {
		this.courseinfono = courseinfono;
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

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
    
   
}
