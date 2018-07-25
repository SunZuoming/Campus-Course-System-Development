package com.courseplatform.service;

import java.util.List;

import com.courseplatform.po.SharedfileTable;

public interface SharedFileTableService {

	/**
	 * 文件审核通过
	 * @param fileTable
	 */
	public void examineFile(SharedfileTable fileTable);
	
	/**
	 * 获取未审核的文件信息
	 * @return
	 */
	public List<SharedfileTable> noExamineFileList(SharedfileTable sharedfileTable);
	
	
	/**
	 * szm
	 * @param sharefile
	 */
	void addfile(SharedfileTable sharefile);
	
	SharedfileTable queryById(String sharefile);
	
	List<SharedfileTable> listShare();
	
	void update(SharedfileTable sharefile);
	
	void delete(SharedfileTable sharefile);
}
