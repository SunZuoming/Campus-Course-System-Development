package com.courseplatform.dao;

import java.util.List;
import java.util.Map;

import com.courseplatform.po.OperationcensusdayTable;
import com.courseplatform.po.OperationcensusmonthTable;
import com.courseplatform.po.report.OperationReport;

public interface OperationcensusdayTableDao {

	/**
	 * 插入日操作统计信息
	 * @param operationTable
	 */
	public void insertOperationDay(OperationcensusdayTable operationTable);
	
	/**
	 * 删除日操作统计信息
	 * @param date
	 */
	public void deleteOperationDay(Map<String, String> param);
	
	/**
	 * 获取本月操作统计信息
	 * @param month
	 * @return
	 */
	public List<OperationcensusmonthTable> getMonth(String month);
	
	/**
	 * 获取日操作报表
	 * @return
	 */
	public List<OperationReport> getDayReportList(Map<String, String> param);
	
	/**
	 * 获取日操作报表，通过用户模糊查询
	 * @return
	 */
	public List<OperationReport> getDayReportListByNo(Map<String, String> param);
}
