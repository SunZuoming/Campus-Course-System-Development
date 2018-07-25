package com.courseplatform.service;

import com.courseplatform.po.DTO.ReporterDTO;

public interface ReporterDTOService {

	/**
	 * 获取举报人、被举报人信息
	 * @param reporter
	 * @return
	 */
	public ReporterDTO getReportDTO(String reporter);
}
