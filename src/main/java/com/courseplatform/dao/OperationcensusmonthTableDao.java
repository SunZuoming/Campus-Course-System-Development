package com.courseplatform.dao;

import java.util.List;
import java.util.Map;

import com.courseplatform.po.OperationcensusmonthTable;
import com.courseplatform.po.OperationcensusyearTable;
import com.courseplatform.po.report.OperationReport;

public interface OperationcensusmonthTableDao {

	/**
	 * 插入月操作统计信息
	 * @param opeartionTable
	 */
	public void insertOperationMonth(OperationcensusmonthTable opeartionTable);
	
	/**
	 * 删除月操作统计信息
	 * @param month
	 */
	public void deleteOperationMonth(String month);
	
	/**
	 * 获取年操作统计
	 * @param year
	 * @return
	 */
	public List<OperationcensusyearTable> getYear(String year);
	
	/**
	 * 获取月操作报表
	 * @return
	 */
	public List<OperationReport> getMonthReportList(Map<String, String> param);
	
	/**
	 * 获取月操作报表，根据用户编号模糊查询
	 * @param param
	 * @return
	 */
	public List<OperationReport> getMonthReportListByNo(Map<String, String> param);
}
