package com.courseplatform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.courseplatform.dao.OperationrecordTableDao;
import com.courseplatform.po.OperationrecordTable;
import com.courseplatform.service.OperationRecordService;

@Service
public class OperationRecordServiceImpl implements OperationRecordService {
	
	@Autowired
	private OperationrecordTableDao opTableDao;

	public void insertOperationRecord(OperationrecordTable opTable) {
		opTableDao.insertOperationRecord(opTable);
	}

	public void updateOperationRecord(OperationrecordTable opTable) {
		opTableDao.updateOperationRecord(opTable);
	}

	public void deleteOperationRecord(String operationtime) {
		opTableDao.deleteOperationRecord(operationtime);
	}

}
