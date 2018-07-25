package com.courseplatform.Controller.scheduling;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.TriggerBuilder;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class StartJob extends QuartzJobBean {
	
	private static Log log = LogFactory.getLog(StartJob.class);

	private Scheduler schedule;
	private JobDetail updateUserFailLoginJob;
	private JobDetail updateProPlusDaysJob;
	private JobDetail generateReportJob;
	private JobDetail delOperationRecordJob;
	private JobDetail delUser2StateJob;

	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		
		try {
			log.info("--------StartJob-----------");
//			0 15 17 * * *
			//登录失败次数归零
			try {
				log.info("-----create updateLoginNumJob start-------");
				CronTrigger updateUserFailLoginTrigger = TriggerBuilder
						.newTrigger()
						.withIdentity("updateUserFailLoginTrigger", "updateUserFailLoginTriggerGroup")
						.withSchedule(CronScheduleBuilder.cronSchedule("0 10 0 ? * *"))
						.build();
				this.getSchedule().scheduleJob(this.getUpdateUserFailLoginJob(),updateUserFailLoginTrigger);
				log.info("-----create updateLoginNumJob end-------");
			} catch (Exception e) {
				log.info("Start updateUserFailLoginJob fail:"+e.getMessage());
			}
			
			//封号剩余天数减1
			try {
				log.info("-----create updateProPlusDaysJob start-------");
				CronTrigger updateProPlusDaysTrigger = TriggerBuilder
						.newTrigger()
						.withIdentity("updateProPlusDaysTrigger", "updateProPlusDaysTriggerGroup")
						.withSchedule(CronScheduleBuilder.cronSchedule("0 30 0 ? * *"))
						.build();
				this.getSchedule().scheduleJob(this.getUpdateProPlusDaysJob(),updateProPlusDaysTrigger);
				log.info("-----create updateProPlusDaysJob end-------");
			} catch (Exception e) {
				log.info("Start updateProPlusDaysJob fail:"+e.getMessage());
			}
			
			//删除前30天的操作记录
			try {
				log.info("-----create delOperationRecordJob start-------");
				CronTrigger delOperationRecordTrigger = TriggerBuilder
						.newTrigger()
						.withIdentity("delOperationRecordTrigger", "delOperationRecordTriggerGroup")
						.withSchedule(CronScheduleBuilder.cronSchedule("0 50 0 ? * *"))
						.build();
				this.getSchedule().scheduleJob(this.getDelOperationRecordJob(),delOperationRecordTrigger);
				log.info("-----create delOperationRecordJob end-------");
			} catch (Exception e) {
				log.info("Start delOperationRecordJob fail:"+e.getMessage());
			}
			
			//删除2状态的用户信息和封号记录
			try {
				log.info("-----create delUser2StateJob start-------");
				CronTrigger delUser2StateTrigger = TriggerBuilder
						.newTrigger()
						.withIdentity("delUser2StateTrigger", "delUser2StateTriggerGroup")
						.withSchedule(CronScheduleBuilder.cronSchedule("0 5 0 ? * *"))
						.build();
				this.getSchedule().scheduleJob(this.getDelUser2StateJob(),delUser2StateTrigger);
				log.info("-----create delUser2StateJob end-------");
			} catch (Exception e) {
				log.info("Start delUser2StateJob fail:"+e.getMessage());
				e.printStackTrace();
			}
			
			//普通报表统计
			try {
				log.info("-----create generateReportJob start-------");
				CronTrigger generateReportTrigger = TriggerBuilder
						.newTrigger()
						.withIdentity("generateReportTrigger", "generateReportTriggerGroup")
						.withSchedule(CronScheduleBuilder.cronSchedule("0 0 2 ? * *"))
						.build();
				this.getSchedule().scheduleJob(this.getGenerateReportJob(),generateReportTrigger);
				log.info("-----create generateReportJob end-------");
			} catch (Exception e) {
				log.info("Start generateReportJob fail:"+e.getMessage());
				e.printStackTrace();
			}
			log.info("--------StartJob end--------");
		} catch (Exception e) {
			log.info("---------StartJob Fail："+e.getMessage());
		}
	}

	public Scheduler getSchedule() {
		return schedule;
	}

	public void setSchedule(Scheduler schedule) {
		this.schedule = schedule;
	}

	public JobDetail getUpdateUserFailLoginJob() {
		return updateUserFailLoginJob;
	}

	public void setUpdateUserFailLoginJob(JobDetail updateUserFailLoginJob) {
		this.updateUserFailLoginJob = updateUserFailLoginJob;
	}

	public JobDetail getUpdateProPlusDaysJob() {
		return updateProPlusDaysJob;
	}

	public void setUpdateProPlusDaysJob(JobDetail updateProPlusDaysJob) {
		this.updateProPlusDaysJob = updateProPlusDaysJob;
	}

	public JobDetail getDelOperationRecordJob() {
		return delOperationRecordJob;
	}

	public void setDelOperationRecordJob(JobDetail delOperationRecordJob) {
		this.delOperationRecordJob = delOperationRecordJob;
	}

	public JobDetail getDelUser2StateJob() {
		return delUser2StateJob;
	}

	public void setDelUser2StateJob(JobDetail delUser2StateJob) {
		this.delUser2StateJob = delUser2StateJob;
	}

	public JobDetail getGenerateReportJob() {
		return generateReportJob;
	}

	public void setGenerateReportJob(JobDetail generateReportJob) {
		this.generateReportJob = generateReportJob;
	}
}
