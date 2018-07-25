package com.courseplatform.service;

import java.util.List;

import com.courseplatform.po.WarningTable;

public interface WarningTableService {

	/**
	 * 发布警告,更信用户状态，判断是否需要封号，返回标志flag
	 * 0-永久封号
	 * 1-封号
	 * 2-没有封号
	 * 3-已处于封号状态
	 * @param warning
	 * @return String
	 */
	public String publicWarning(WarningTable warning);
	
	/**
	 * 获取所有已读并且未结束的警告
	 * @return
	 */
	public List<WarningTable> getWarningList();
	
	/**
	 * 结束警告
	 * @param warning
	 */
	public void endWarning(WarningTable warning);
}
