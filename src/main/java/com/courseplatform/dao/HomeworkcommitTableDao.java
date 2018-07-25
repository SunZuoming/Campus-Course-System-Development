package com.courseplatform.dao;

import java.util.List;
import java.util.Map;

import com.courseplatform.po.HomeworkcommitTable;

public interface HomeworkcommitTableDao {

	/**
	 * 获取某人在某日的提交作业数目
	 * @param param
	 * @return
	 */
	public Integer getHomeWorkCommitByNo(Map<String, String> param);
	
	// 上传作业
	public void upwork(HomeworkcommitTable homeworkcommit);

	// 查询学生提交的作业
	public List<HomeworkcommitTable> studentUpWork(HomeworkcommitTable homeworkcommit);

	// 批改作业
	public void checkStudentWork(HomeworkcommitTable homeworkcommit);

	// 显示作业信息
	public HomeworkcommitTable studentWorkbyno(HomeworkcommitTable homeworkcommit);

	// 學生查看已經提交的作業
	public List<HomeworkcommitTable> studenLook(HomeworkcommitTable homeworkcommit);
}
