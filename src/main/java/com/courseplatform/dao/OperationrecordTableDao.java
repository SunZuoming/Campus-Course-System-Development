package com.courseplatform.dao;

import com.courseplatform.po.OperationrecordTable;

public interface OperationrecordTableDao {

	/**
	 * 插入操作记录
	 * @param opTable
	 */
	public void insertOperationRecord(OperationrecordTable opTable);
	
	/**
	 * 更新操作记录
	 * @param opTable
	 */
	public void updateOperationRecord(OperationrecordTable opTable);
	
	/**
	 * 根据时间删除操作记录
	 * @param operationtime
	 */
	public void deleteOperationRecord(String operationtime);
}
