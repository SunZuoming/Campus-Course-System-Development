package com.courseplatform.service;

import java.util.List;
import java.util.Map;

import com.courseplatform.po.ProhibitLoginRecordTable;
import com.courseplatform.po.User;
import com.courseplatform.po.DTO.UserDTO;

public interface UserService {

	/**
	 * 验证登录
	 * @param user
	 * @return
	 */
	public User checkLogin(User user);
	
	/**
	 * 根据用户号查询用户信息
	 * @return
	 */
	public User getUserByUserNo(String userNo);
	
	/**
	 * 添加管理员
	 * @param user
	 */
	public void insertUser(User user);
	
	/**
	 * 更新用户信息
	 * @param user
	 */
	public void updateUser(User user);
	
	/**
	 * 将用户失败登陆次数归零
	 */
	public void updateUserFailLogin();
	
	/**
	 * 批量更新用户状态
	 * @param proList
	 */
	public void updateUserState(List<ProhibitLoginRecordTable> proList);
	
	/**
	 * 删除用户状态为2的用户记录
	 */
	public void deleteUser2State();
	
	/**
	 * 获取用户状态为2的用户信息
	 * @return
	 */
	public List<User> getUserList2State();
	
	/**
	 * 修改密码
	 * @param user
	 */
	public void changePassword(User user);
	
	/**
	 * 获取未审核的注册教师列表
	 * @return
	 */
	public List<User> getUserList3State(Map<String,Integer> para);
	
	/**
	 * 审核通过注册教师
	 * @param teacherList
	 */
	public void updateTeacherState(String userNo);
	
	/**
	 * 获取未审核的注册教师的数量
	 * @return
	 */
	public Integer getTeacherNum();
	
	/**
	 * 获取处于封号状态的用户个数
	 * @return
	 */
	public Integer getProNum();
	
	/**
	 * 获取全部用户（除去管理员）
	 * @return
	 */
	public List<User> getAllUser();
	
	/**
	 * 删除未通过审核的教师信息
	 * @param userNo
	 */
	public void deleteTeacher(String userNo);
	
	List<UserDTO> getUser(User user);
	
}
