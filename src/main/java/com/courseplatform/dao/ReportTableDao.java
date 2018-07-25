package com.courseplatform.dao;

import java.util.List;
import java.util.Map;

import com.courseplatform.po.ReportTable;

public interface ReportTableDao {

	/**
	 * 获取举报总数目
	 * @return
	 */
	public long getReportNum();
	
	/**
	 * 获取所有未结束的举报
	 * @return
	 */
	public List<ReportTable> getReportTableList(Map<String, Integer> param);
	
	/**
	 * 获取未结束的举报数目
	 * @return
	 */
	public Integer getReportNumFlag();
	
	/**
	 * 获取某用户的被举报数
	 * @param reporter
	 * @return
	 */
	public Integer getReportNumOfUser(String reporter);
	
	/**
	 * 获得某用户的被举报记录（分页）
	 * @param reporteer
	 * @return
	 */
	public List<ReportTable> getReporterList(Map<String, Object> param);
	
	/**
	 * 根据举报编号获取举报信息
	 * @param reportNo
	 * @return
	 */
	public ReportTable getReportByNo(String reportNo);
	
	/**
	 * 更新举报信息，举报已处理
	 * @param reportNo
	 */
	public void endReport(String reportNo);
}
