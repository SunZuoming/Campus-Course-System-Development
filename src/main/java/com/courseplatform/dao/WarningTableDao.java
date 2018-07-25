package com.courseplatform.dao;

import java.util.List;

import com.courseplatform.po.WarningTable;

public interface WarningTableDao {

	/**
	 * 发布警告
	 * @param warning
	 */
	public void publicWarning(WarningTable warning);
	
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
	
	/**
	 * 获取警告总数目
	 * @return
	 */
	public long getWarningNum();
}
