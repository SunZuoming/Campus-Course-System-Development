package com.courseplatform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courseplatform.dao.LoginTableDao;
import com.courseplatform.po.LoginTable;
import com.courseplatform.service.LoginTableService;

@Service
public class LoginTableServiceImpl implements LoginTableService {

	@Autowired
	private LoginTableDao loTableDao;
	
	public void insertLogin(LoginTable loTable) {
		loTableDao.insertLogin(loTable);
	}

	public void updateLogin(LoginTable loTable) {

		loTableDao.updateLogin(loTable);
	}

	public void deleteLogin(String logintime) {

		loTableDao.deleteLogin(logintime);
	}

}
