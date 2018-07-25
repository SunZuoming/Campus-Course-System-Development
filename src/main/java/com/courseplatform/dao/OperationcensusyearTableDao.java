package com.courseplatform.dao;

import java.util.List;
import java.util.Map;

import com.courseplatform.po.OperationcensusyearTable;
import com.courseplatform.po.report.OperationReport;

public interface OperationcensusyearTableDao {

	/**
	 * 插入年操作统计信息
	 * @param operationTable
	 */
	public void insertOperationYear(OperationcensusyearTable operationTable);
	
	/**
	 * 删除年操作统计信息
	 * @param year
	 */
	public void deleteOperationYear(String year);
	
	/**
	 * 获取年操作报表
	 * @return
	 */
	public List<OperationReport> getYearReportList(Map<String, String> param);
	
	/**
	 * 获取年操作报表，根据用户编号模糊查询
	 * @param param
	 * @return
	 */
	public List<OperationReport> getYearReportListByNo(Map<String, String> param);
}
