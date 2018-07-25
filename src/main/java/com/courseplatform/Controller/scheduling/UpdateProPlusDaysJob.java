package com.courseplatform.Controller.scheduling;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.courseplatform.po.ProhibitLoginRecordTable;
import com.courseplatform.service.ProhibitLoginRecordService;
import com.courseplatform.service.UserService;

public class UpdateProPlusDaysJob extends QuartzJobBean{

	private static Log log = LogFactory.getLog(UpdateProPlusDaysJob.class);
	
	@Autowired
	private ProhibitLoginRecordService proService;
	
	@Autowired
	private UserService userService;
	
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		
		try {
			log.info("-------封号剩余天数减1---------");
			proService.updateProPlusDays();
			List<ProhibitLoginRecordTable> proList = proService.getListPlus();
			if(!proList.isEmpty()) {
				log.info("-------封号结束用户解除封号状态-------");
				userService.updateUserState(proList);
				log.info("-------封号结束用户解除封号状态成功-------");
			}
			log.info("-------封号剩余天数减1成功---------");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("---封号相关更新失败："+e.getStackTrace());
		}
	}

	

}
