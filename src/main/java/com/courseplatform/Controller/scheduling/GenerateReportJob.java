package com.courseplatform.Controller.scheduling;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.courseplatform.po.User;
import com.courseplatform.service.DataCountDTOService;
import com.courseplatform.service.UserService;

public class GenerateReportJob extends QuartzJobBean {

	private static Log log = LogFactory.getLog(GenerateReportJob.class);
	
	@Autowired
	private DataCountDTOService dataCountDTOService;
	
	@Autowired
	private UserService userService;
	
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		try {
			log.info("--------报表统计开始--------");
			try {
				log.info("---------------日操作统计开始：");
				List<User> userList = userService.getAllUser();
				for (User user : userList) {
					dataCountDTOService.insertOperationDay(user.getUserNo());
				}
				log.info("---------------日操作统计结束--------");
			} catch (Exception e) {
				log.info("---------------日操作统计失败："+e.getMessage());
			}
			try {
				log.info("---------------月操作统计开始：");
				dataCountDTOService.insertOperationMonth();
				log.info("---------------月操作统计结束");
			} catch (Exception e) {
				log.info("---------------月操作统计失败："+e.getMessage());
			}
			try {
				log.info("---------------年操作统计开始：");
				dataCountDTOService.insertOperationYear();
				log.info("---------------年操作统计结束");
			} catch (Exception e) {
				log.info("---------------年操作统计失败："+e.getMessage());
			}
			log.info("--------报表统计结束--------");
		} catch (Exception e) {
			log.info("--------报表统计失败："+e.getMessage());
		}
	}
}
