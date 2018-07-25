package com.courseplatform.service;

import java.util.List;
import java.util.Map;

import com.courseplatform.po.DTO.DataCountDTO;
import com.courseplatform.po.report.OperationReport;

public interface DataCountDTOService {

	/**
	 * 数据统计数目
	 * @return
	 */
	public DataCountDTO getDataDTO();
	
	/**
	 * 插入日统计信息
	 * @param userNo
	 */
	public void insertOperationDay(String userNo);
	
	/**
	 * 插入月统计信息
	 */
	public void insertOperationMonth();
	
	/**
	 * 插入年统计信息
	 */
	public void insertOperationYear();
	
	/**
	 * 获取日操作统计报表
	 * @return
	 */
	public List<OperationReport> getDayReport(Map<String, String> param);
	
	/**
	 * 获取日操作报表，通过用户模糊查询
	 * @return
	 */
	public List<OperationReport> getDayReportListByNo(Map<String, String> param);
	
	/**
	 * 获取月操作报表
	 * @return
	 */
	public List<OperationReport> getMonthReportList(Map<String, String> param);
	
	/**
	 * 获取月操作报表 通过用户模糊查询
	 * @param param
	 * @return
	 */
	public List<OperationReport> getMonthReportListByNo(Map<String, String> param);
	
	/**
	 * 获取年操作报表
	 * @return
	 */
	public List<OperationReport> getYearReportList(Map<String, String> param);
	
	/**
	 * 获取年操作报表，通过用户模糊查询
	 * @param param
	 * @return
	 */
	public List<OperationReport> getYearReportListByNo(Map<String, String> param);
}
