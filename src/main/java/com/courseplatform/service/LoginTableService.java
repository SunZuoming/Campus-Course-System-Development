package com.courseplatform.service;

import com.courseplatform.po.LoginTable;

public interface LoginTableService {

	/**
	 * 插入登录信息
	 * @param loTable
	 */
	public void insertLogin(LoginTable loTable);
	
	/**
	 * 更新登录结果
	 * @param loTable
	 */
	public void updateLogin(LoginTable loTable);
	
	/**
	 * 删除登录记录
	 * @param loTable
	 */
	public void deleteLogin(String logintime);
}
