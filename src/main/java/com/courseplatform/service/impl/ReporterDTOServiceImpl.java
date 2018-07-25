package com.courseplatform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courseplatform.dao.ReportTableDao;
import com.courseplatform.dao.UserTableDao;
import com.courseplatform.po.DTO.ReporterDTO;
import com.courseplatform.service.ReporterDTOService;

@Service
public class ReporterDTOServiceImpl implements ReporterDTOService {

	@Autowired
	private UserTableDao userTableDao;
	
	@Autowired
	private ReportTableDao reportTableDao;

	public ReporterDTO getReportDTO(String reporter) {
		ReporterDTO reporterDTO = new ReporterDTO();
		reporterDTO.setReporter(userTableDao.getUserByUserNo(reporter));
		reporterDTO.setReporterNum(reportTableDao.getReportNumOfUser(reporter));
		return reporterDTO;
	}

}
