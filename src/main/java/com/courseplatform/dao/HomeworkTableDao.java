package com.courseplatform.dao;

import java.util.List;
import java.util.Map;

import com.courseplatform.po.HomeworkTable;

public interface HomeworkTableDao {

	/**
	 * 获取作业数目
	 * @return
	 */
	public long getHomeworkNum();
	
	/**
	 * 获取作业数目
	 * @return
	 */
	public Integer getHomeworkNumByNo(Map<String, String> param);
	
	// 发布作业
	public void publshwork(HomeworkTable homework);

	// 查询教师发布的作业
	public List<HomeworkTable> teacherWork(HomeworkTable homework);
}
