package com.courseplatform.dao;

import java.util.List;

import com.courseplatform.po.SharedfileexamineTable;

public interface SharedfileexamineTableDao {

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
	
	int insert(SharedfileexamineTable record);
}
