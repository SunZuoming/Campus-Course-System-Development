package com.courseplatform.po;

public class CourseTableKey {
    private String usernos;

    private String courseinfono;

    public String getUsernos() {
        return usernos;
    }

    public void setUsernos(String usernos) {
        this.usernos = usernos == null ? null : usernos.trim();
    }

    public String getCourseinfono() {
        return courseinfono;
    }

    public void setCourseinfono(String courseinfono) {
        this.courseinfono = courseinfono == null ? null : courseinfono.trim();
    }
}