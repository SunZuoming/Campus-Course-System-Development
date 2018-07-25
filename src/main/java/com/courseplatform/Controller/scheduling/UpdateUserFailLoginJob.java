package com.courseplatform.Controller.scheduling;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.courseplatform.service.UserService;

public class UpdateUserFailLoginJob extends QuartzJobBean{
	
	private static Log log=LogFactory.getLog(UpdateUserFailLoginJob.class);
	
	@Autowired
	private UserService userService;

	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		try {
			log.info("-------登录失败次数归零开始-----");
			userService.updateUserFailLogin();
			log.info("-------登录失败次数归零成功-----");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("-------登录失败次数归零失败："+e.getMessage());
		}
	}

	

}
