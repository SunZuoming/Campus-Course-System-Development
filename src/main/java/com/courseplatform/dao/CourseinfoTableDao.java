package com.courseplatform.dao;

import java.util.List;
import java.util.Map;

import com.courseplatform.po.CourseinfoTable;

public interface CourseinfoTableDao {

	//根据课程编号得到课程信息
    public CourseinfoTable getCourseByNo(String no);
    
  //得到所有课程信息
    public List<CourseinfoTable> getCourseAll(Map<String, Integer> param);
    
    //添加课程信息
    public void addCourse(CourseinfoTable courseinfo);
    
    //根据课程名称得到课程信息
    public CourseinfoTable getcourseno(String cname);
    
    /**
   	 * 得到课程数量
   	 */
   	public Integer getcourseNum();
 
}
