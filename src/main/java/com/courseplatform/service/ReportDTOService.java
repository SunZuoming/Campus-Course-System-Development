package com.courseplatform.service;

import java.util.List;
import java.util.Map;

import com.courseplatform.po.ReportTable;
import com.courseplatform.po.DTO.ReportDTO;

public interface ReportDTOService {

	/**
	 * 获取所有未结束的举报
	 * @return
	 */
	public List<ReportDTO> getReportDTOList(Map<String, Integer> param);
	
	/**
	 * 获取未结束的举报数目
	 * @return
	 */
	public Integer getReportNumFlag();
	
	/**
	 * 获得某用户的被举报记录（分页）
	 * @param reporteer
	 * @return
	 */
	public List<ReportDTO> getReporterList(Map<String, Object> param);
	
	/**
	 * 根据Id获取举报信息
	 * @param reportNo
	 * @return
	 */
	public ReportDTO getReportByNo(String reportNo);
	
	/**
	 * 更新举报信息，举报已处理
	 * @param reportNo
	 */
	public void endReport(String reportNo);
}
