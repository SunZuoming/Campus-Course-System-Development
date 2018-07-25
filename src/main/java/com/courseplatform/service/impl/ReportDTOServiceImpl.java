package com.courseplatform.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courseplatform.dao.ReportTableDao;
import com.courseplatform.dao.UserTableDao;
import com.courseplatform.po.ReportTable;
import com.courseplatform.po.DTO.ReportDTO;
import com.courseplatform.service.ReportDTOService;

@Service
public class ReportDTOServiceImpl implements ReportDTOService {
	
	@Autowired
	private ReportTableDao reportTableDao;
	
	@Autowired
	private UserTableDao userTableDao;

	public List<ReportDTO> getReportDTOList(Map<String, Integer> param) {
		List<ReportDTO> reportDTOList = new ArrayList<ReportDTO>();
		
		List<ReportTable> reportTableList = reportTableDao.getReportTableList(param);
		
		for (ReportTable reportTable : reportTableList) {
			ReportDTO reportDTO = new ReportDTO();
			reportDTO.setReportfinishflag(reportTable.getReportfinishflag());
			reportDTO.setReportgoodsno(reportTable.getReportgoodsno());
			reportDTO.setReportgoodstype(reportTable.getReportgoodstype());
			reportDTO.setReportno(reportTable.getReportno());
			reportDTO.setReportreason(reportTable.getReportreason());
			reportDTO.setReporttime(reportTable.getReporttime());
			reportDTO.setReporteer(userTableDao.getUserByUserNo(reportTable.getReporteer()));
			reportDTO.setReporter(userTableDao.getUserByUserNo(reportTable.getReporter()));
			reportDTOList.add(reportDTO);
		}
		return reportDTOList;
	}

	public Integer getReportNumFlag() {
		return reportTableDao.getReportNumFlag();
	}

	public List<ReportDTO> getReporterList(Map<String, Object> param) {
		List<ReportDTO> reportDTOList = new ArrayList<ReportDTO>();
		
		List<ReportTable> reportTableList = reportTableDao.getReporterList(param);
		
		for (ReportTable reportTable : reportTableList) {
			ReportDTO reportDTO = new ReportDTO();
			reportDTO.setReportfinishflag(reportTable.getReportfinishflag());
			reportDTO.setReportgoodsno(reportTable.getReportgoodsno());
			reportDTO.setReportgoodstype(reportTable.getReportgoodstype());
			reportDTO.setReportno(reportTable.getReportno());
			reportDTO.setReportreason(reportTable.getReportreason());
			reportDTO.setReporttime(reportTable.getReporttime());
			reportDTO.setReporteer(userTableDao.getUserByUserNo(reportTable.getReporteer()));
			reportDTO.setReporter(userTableDao.getUserByUserNo(reportTable.getReporter()));
			reportDTOList.add(reportDTO);
		}
		return reportDTOList;
	}

	public ReportDTO getReportByNo(String reportNo) {
		ReportDTO reportDTO = new ReportDTO();
		ReportTable reportTable = reportTableDao.getReportByNo(reportNo);
		reportDTO.setReportfinishflag(reportTable.getReportfinishflag());
		reportDTO.setReportgoodsno(reportTable.getReportgoodsno());
		reportDTO.setReportgoodstype(reportTable.getReportgoodstype());
		reportDTO.setReportno(reportTable.getReportno());
		reportDTO.setReportreason(reportTable.getReportreason());
		reportDTO.setReporttime(reportTable.getReporttime());
		reportDTO.setReporteer(userTableDao.getUserByUserNo(reportTable.getReporteer()));
		reportDTO.setReporter(userTableDao.getUserByUserNo(reportTable.getReporter()));
		return reportDTO;
	}

	public void endReport(String reportNo) {
		reportTableDao.endReport(reportNo);
	}

}
