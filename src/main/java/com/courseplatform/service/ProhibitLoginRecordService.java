package com.courseplatform.service;

import java.util.List;

import com.courseplatform.po.ProhibitLoginRecordTable;
import com.courseplatform.po.User;

public interface ProhibitLoginRecordService {

	/**
	 * 获取封号记录
	 * @param prTable
	 * @return
	 */
	public ProhibitLoginRecordTable getProhibit(ProhibitLoginRecordTable prTable);
	
	/**
	 * 更新封号记录
	 * @param prTable
	 */
	public void updateProRecord(ProhibitLoginRecordTable prTable);
	
	/**
	 * 插入新的封号记录
	 * @param prTable
	 */
	public void insertProRecord(ProhibitLoginRecordTable prTable);
	
	/**
	 * 删除封号记录
	 * @param prTable
	 */
	public void deleteProRecord(ProhibitLoginRecordTable prTable);
	
	/**
	 * 封号剩余天数大于0的账号封号天数减一
	 */
	public void updateProPlusDays();
	
	/**
	 * 获取封号剩余天数为0的封号记录
	 * @return
	 */
	public List<ProhibitLoginRecordTable> getListPlus();
	
	/**
	 * 删除处于封号（2）状态的用户的封号记录
	 * @param userList
	 */
	public void deleteProRecord2State(List<User> userList);
}
