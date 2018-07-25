package com.courseplatform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.courseplatform.dao.ProhibitLoginRecordTableDao;
import com.courseplatform.dao.UserTableDao;
import com.courseplatform.po.ProhibitLoginRecordTable;
import com.courseplatform.po.User;
import com.courseplatform.service.ProhibitLoginRecordService;

@Service
public class ProhibitLoginRecordServiceImpl implements ProhibitLoginRecordService {
	
	@Autowired
	private ProhibitLoginRecordTableDao prTableDao;
	
//	@Autowired
//	private UserTableDao userTableDao;

	public ProhibitLoginRecordTable getProhibit(ProhibitLoginRecordTable prTable) {
		return prTableDao.getProhibit(prTable);
	}

	/**
	 * 插入封号信息与更新用户状态同时完成
	 */
	@Transactional
	public void updateProRecord(ProhibitLoginRecordTable prTable) {
		prTableDao.updateProRecord(prTable);
//		userTableDao.comProByNo(prTable.getProhibitLogineer());
	}

	@Transactional
	public void insertProRecord(ProhibitLoginRecordTable prTable) {
		prTableDao.insertProRecord(prTable);
//		userTableDao.comProByNo(prTable.getProhibitLogineer());
	}

	public void deleteProRecord(ProhibitLoginRecordTable prTable) {
		prTableDao.deleteProRecord(prTable);
	}

	public void updateProPlusDays() {
		prTableDao.updateProPlusDays();
	}

	public List<ProhibitLoginRecordTable> getListPlus() {
		return prTableDao.getListPlus();
	}

	public void deleteProRecord2State(List<User> userList) {
		prTableDao.deleteProRecord2State(userList);
	}

}
