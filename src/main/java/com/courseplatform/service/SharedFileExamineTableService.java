package com.courseplatform.service;

import java.util.List;

import com.courseplatform.po.SharedfileexamineTable;

public interface SharedFileExamineTableService {

	/**
	 * 创建文件审核记录
	 * @param fileTable
	 */
	public void createFileExamine(SharedfileexamineTable fileTable);
	
	/**
	 * 更新文件审核记录
	 * @param fileTable
	 */
	public void updateFileExamine(SharedfileexamineTable fileTable);
	
	/**
	 * 获取文件审核记录
	 * @param sharedfileexamineTable
	 * @return
	 */
	public List<SharedfileexamineTable> getFileExamineList(SharedfileexamineTable sharedfileexamineTable);
	/**
	 * 
	 * 
	 * 添加文件操作记录
	 * @param sharefile
	 */
	void insertRecord(SharedfileexamineTable sharefile);
}
