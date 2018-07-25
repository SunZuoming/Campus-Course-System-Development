package com.courseplatform.Controller.scheduling;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.courseplatform.po.User;
import com.courseplatform.service.ProhibitLoginRecordService;
import com.courseplatform.service.UserService;

public class DelUser2StateJob extends QuartzJobBean{

	private static Log log = LogFactory.getLog(DelUser2StateJob.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProhibitLoginRecordService proService;
	
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		try {
			log.info("-----删除2状态用户开始-----");
			List<User> userList = userService.getUserList2State();
			System.out.println(userList.size());
			if(! userList.isEmpty()) {
				proService.deleteProRecord2State(userList);
				userService.deleteUser2State();
				log.info("----删除成功------");
			}else {
				log.info("------无2状态用户------");
			}
			log.info("-------删除2状态用户结束-----");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("-----删除2状态用户失败："+e.getMessage());
		}
	}


}
