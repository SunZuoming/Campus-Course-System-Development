package com.courseplatform.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courseplatform.dao.UserTableDao;
import com.courseplatform.po.ProhibitLoginRecordTable;
import com.courseplatform.po.User;
import com.courseplatform.po.DTO.UserDTO;
import com.courseplatform.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserTableDao userTableDao;

	public User checkLogin(User user) {
		User userResult = userTableDao.checkLogin(user);
		return userResult;
	}

	public void insertUser(User user) {

		userTableDao.insertUser(user);
	}

	public void updateUser(User user) {
		userTableDao.updateUser(user);
	}

	public void updateUserFailLogin() {
		userTableDao.updateUserFailLogin();
	}

	public void updateUserState(List<ProhibitLoginRecordTable> proList) {
		userTableDao.updateUserState(proList);
	}

	public void deleteUser2State() {
		userTableDao.deleteUser2State();
	}

	public List<User> getUserList2State() {
		return userTableDao.getUserList2State();
	}

	public void changePassword(User user) {
		userTableDao.changePassword(user);
	}

	public User getUserByUserNo(String userNo) {
		return userTableDao.getUserByUserNo(userNo);
	}

	public List<User> getUserList3State(Map<String,Integer> para) {
		
		return userTableDao.getUserList3State(para);
	}

	public void updateTeacherState(String userNo) {
		userTableDao.updateTeacherState(userNo);
	}

	public Integer getTeacherNum() {
		return userTableDao.getTeacherNum();
	}

	public Integer getProNum() {
		return userTableDao.getProNum();
	}

	public List<User> getAllUser() {
		return userTableDao.getAllUser2();
	}

	public void deleteTeacher(String userNo) {
		userTableDao.deleteTeacher(userNo);
	}

	public List<UserDTO> getUser(User user) {
		
		return userTableDao.getUser(user);
	}

	

}
