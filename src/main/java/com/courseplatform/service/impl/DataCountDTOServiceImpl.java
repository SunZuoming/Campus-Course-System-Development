package com.courseplatform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.courseplatform.dao.AnswerTableDao;
import com.courseplatform.dao.ArticleTableDao;
import com.courseplatform.dao.CollectionTableDao;
import com.courseplatform.dao.CommentTableDao;
import com.courseplatform.dao.HomeworkTableDao;
import com.courseplatform.dao.HomeworkcommitTableDao;
import com.courseplatform.dao.NoticeTableDao;
import com.courseplatform.dao.OperationcensusdayTableDao;
import com.courseplatform.dao.OperationcensusmonthTableDao;
import com.courseplatform.dao.OperationcensusyearTableDao;
import com.courseplatform.dao.ProhibitLoginRecordTableDao;
import com.courseplatform.dao.QuestionTableDao;
import com.courseplatform.dao.ReportTableDao;
import com.courseplatform.dao.SharedfileTableDao;
import com.courseplatform.dao.UserTableDao;
import com.courseplatform.dao.WarningTableDao;
import com.courseplatform.po.OperationcensusdayTable;
import com.courseplatform.po.OperationcensusmonthTable;
import com.courseplatform.po.OperationcensusyearTable;
import com.courseplatform.po.DTO.DataCountDTO;
import com.courseplatform.po.report.OperationReport;
import com.courseplatform.service.DataCountDTOService;
import com.courseplatform.util.CalendarUtil;
import com.courseplatform.util.UUIDUtils;

@Service
public class DataCountDTOServiceImpl implements DataCountDTOService {

	@Autowired
	private ArticleTableDao articleTableDao;
	
	@Autowired
	private AnswerTableDao answerTableDao;
	
	@Autowired
	private CollectionTableDao collectionTableDao;
	
	@Autowired
	private HomeworkTableDao homeworkTableDao;
	
	@Autowired
	private NoticeTableDao noticeTableDao;
	
	@Autowired
	private QuestionTableDao questionTableDao;
	
	@Autowired
	private ReportTableDao reportTableDao;
	
	@Autowired
	private SharedfileTableDao sharedfileTableDao;
	
	@Autowired
	private WarningTableDao warningTableDao;
	
	@Autowired
	private CommentTableDao commentTableDao;
	
	@Autowired
	private ProhibitLoginRecordTableDao prohibitLoginRecordTableDao;
	
	@Autowired
	private OperationcensusdayTableDao operationcensusdayTableDao;
	
	@Autowired
	private OperationcensusmonthTableDao operationcensusmonthTableDao;
	
	@Autowired
	private HomeworkcommitTableDao homeworkcommitTableDao;
	
	@Autowired
	private OperationcensusyearTableDao operationcensusyearTableDao;
	
	@Autowired
	private UserTableDao userTableDao;
	
	public DataCountDTO getDataDTO() {
		DataCountDTO dataDTO = new DataCountDTO();
		dataDTO.setAnswerNum(answerTableDao.getAnswerNum());
		dataDTO.setArticleNum(articleTableDao.getArticleNum());
		dataDTO.setCollectionNum(collectionTableDao.getCollectionNum());
		dataDTO.setCommentNum(commentTableDao.getCommnetNum());
		dataDTO.setFileNum(sharedfileTableDao.getSharedFileNum());
		dataDTO.setHomeworkNum(homeworkTableDao.getHomeworkNum());
		dataDTO.setNoticeNum(noticeTableDao.getNoticeNUm());
		dataDTO.setQuestionNum(questionTableDao.getQuestionNum());
		dataDTO.setReportNum(reportTableDao.getReportNum());
		dataDTO.setWarningNum(warningTableDao.getWarningNum());
		dataDTO.setProhibitNum(prohibitLoginRecordTableDao.getProhibitNum());
		return dataDTO;
	}

	@Transactional
	public void insertOperationDay(String userNo) {
		String dateNow = CalendarUtil.getSysTimeYMD();
		Map<String, String> param = new HashMap<String, String>();
		param.put("userNo", userNo);
		for (int i = 1; i <= 7; i++) {
			String datePre = CalendarUtil.getFixedDate(dateNow, -i);
			String date = datePre+"%";
			param.put("date", date);
			operationcensusdayTableDao.deleteOperationDay(param);
			Integer articleNum = articleTableDao.getArticleNumByNo(param);
			Integer commnetNum = commentTableDao.getCommnetNumByNo(param);
			Integer questionNum = questionTableDao.getQuestionNumByNo(param);
			Integer answerNum = answerTableDao.getAnswerNumByNo(param);
			Integer homeWorkNum = homeworkTableDao.getHomeworkNumByNo(param);
			Integer homeWorkCommitNum = homeworkcommitTableDao.getHomeWorkCommitByNo(param);
			Integer collectionNum = collectionTableDao.getCollectionNumByNo(param);
			Integer sharedFileNum = sharedfileTableDao.getSharedFileNumByNo(param);
			Integer operationNum = articleNum + commnetNum + questionNum + answerNum + homeWorkNum+
					homeWorkCommitNum + collectionNum + sharedFileNum;
			OperationcensusdayTable operationcensusdayTable = new OperationcensusdayTable();
			operationcensusdayTable.setOperationcensusdayno("R"+UUIDUtils.getUUID());
			operationcensusdayTable.setOperationcensusdate(datePre);
			operationcensusdayTable.setOperator(userNo);
			operationcensusdayTable.setOperationdaynum(operationNum);
			operationcensusdayTable.setAddarticlenum(articleNum);
			operationcensusdayTable.setAnswernum(answerNum);
			operationcensusdayTable.setCollectnum(collectionNum);
			operationcensusdayTable.setCommithomeworknum(homeWorkCommitNum);
			operationcensusdayTable.setUploadsharedfilenum(sharedFileNum);
			operationcensusdayTable.setReadarticlenum(commnetNum);
			operationcensusdayTable.setQuestionnum(questionNum);
			operationcensusdayTable.setPublishhomeworknum(homeWorkNum);
			if(datePre.equals(CalendarUtil.getFixedDate(dateNow, -1))){
				Map<String, Object> param2 = new HashMap<String, Object>();
				param2.put("score", (float) operationcensusdayTable.getOperationdaynum() / 10);
				param2.put("userNo", operationcensusdayTable.getOperator());
				userTableDao.updateSysScore(param2);
			}
			operationcensusdayTableDao.insertOperationDay(operationcensusdayTable);
		}
		
		
	}

	@Transactional
	public void insertOperationMonth() {
		String nowdate = CalendarUtil.getSysTimeYMD();
		String predate = CalendarUtil.getFixedDate(nowdate, -1);
		String monthT = predate.substring(0, 7);
		String month = monthT+"%";
		operationcensusmonthTableDao.deleteOperationMonth(month);
		List<OperationcensusmonthTable> monthList = operationcensusdayTableDao.getMonth(month);
		System.out.println(monthList.size());
		for (OperationcensusmonthTable operationcensusmonthTable : monthList) {
			operationcensusmonthTable.setOperationcensusmonthno("M"+UUIDUtils.getUUID());
			operationcensusmonthTable.setOperationcensusmonth(monthT+"-01");
			operationcensusmonthTableDao.insertOperationMonth(operationcensusmonthTable);
		}
	}

	@Transactional
	public void insertOperationYear() {
		String nowDate = CalendarUtil.getSysTimeYMD();
		String preDate = CalendarUtil.getFixedDate(nowDate, -1);
		String yearT = preDate.substring(0, 4);
		String year = yearT+"%";
		operationcensusyearTableDao.deleteOperationYear(year);
		List<OperationcensusyearTable> yearList = operationcensusmonthTableDao.getYear(year);
		System.out.println(yearList.size());
		for (OperationcensusyearTable operationcensusyearTable : yearList) {
			operationcensusyearTable.setOperationcensusyearno("Y"+UUIDUtils.getUUID());
			operationcensusyearTable.setOperationcensusyear(yearT+"-01-01");
			operationcensusyearTableDao.insertOperationYear(operationcensusyearTable);
		}
	}

	public List<OperationReport> getDayReport(Map<String, String> param) {
		return operationcensusdayTableDao.getDayReportList(param);
	}

	public List<OperationReport> getMonthReportList(Map<String, String> param) {
		return operationcensusmonthTableDao.getMonthReportList(param);
	}

	public List<OperationReport> getYearReportList(Map<String, String> param) {
		return operationcensusyearTableDao.getYearReportList(param);
	}

	public List<OperationReport> getDayReportListByNo(Map<String, String> param) {
		return operationcensusdayTableDao.getDayReportListByNo(param);
	}


	public List<OperationReport> getMonthReportListByNo(Map<String, String> param) {
		return operationcensusmonthTableDao.getMonthReportListByNo(param);
	}


	public List<OperationReport> getYearReportListByNo(Map<String, String> param) {
		return operationcensusyearTableDao.getYearReportListByNo(param);
	}
}
