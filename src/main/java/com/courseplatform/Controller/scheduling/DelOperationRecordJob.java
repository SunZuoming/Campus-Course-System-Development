package com.courseplatform.Controller.scheduling;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.courseplatform.service.LoginTableService;
import com.courseplatform.service.OperationRecordService;
import com.courseplatform.util.CalendarUtil;

public class DelOperationRecordJob extends QuartzJobBean{

	private static Log log = LogFactory.getLog(DelOperationRecordJob.class);
	
	@Autowired
	private OperationRecordService operationService;
	
	@Autowired
	private LoginTableService loginService;
	
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		try {
			String nowDate = CalendarUtil.getSysTimeYMD();
			String startDate = CalendarUtil.getFixedDateYYYY_MM_DD(nowDate, -30);
			log.info("-------删除30天前的操作记录开始------");
			operationService.deleteOperationRecord(startDate);
			loginService.deleteLogin(startDate);
			log.info("-------删除30天前的操作记录结束------");
		} catch (Exception e) {
			log.info("-------删除30天前的操作记录失败："+e.getStackTrace());
		}
	}

}
