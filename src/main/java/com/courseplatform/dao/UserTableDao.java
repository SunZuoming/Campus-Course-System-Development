package com.courseplatform.dao;

import java.util.List;
import java.util.Map;

import com.courseplatform.po.ProhibitLoginRecordTable;
import com.courseplatform.po.User;
import com.courseplatform.po.DTO.UserDTO;

public interface UserTableDao {

	/**
	 * 验证登录信息是否输入正确
	 * @param userNo
	 * @return
	 */
	public User checkLogin(User user);
	
	/**
	 * 获取全部用户（除去管理员）
	 * @return
	 */
	public List<User> getAllUser();
	
	public List<User> getAllUser2();
	
	/**
	 * 根据用户号查询用户信息
	 * @param user
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
	 * 做修改登录标志、警告次数、封号次数
	 * @param user
	 */
	public void updateUser(User user);
	
	/**
	 * 将用户失败登录次数归零
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
	 * 将用户的状态改为1封号状态
	 * @param userNo
	 */
	public void comProByNo(String userNo);
	
	/**
	 * 获取处于封号状态的用户个数
	 * @return
	 */
	public Integer getProNum();
	
	/**
	 * 修改状态置0
	 * @param userNo
	 */
	public void cancelProState(String userNo);
	
	/**
	 * 修改系统分数
	 * @param score
	 */
	public void updateSysScore(Map<String, Object> param);
	
	/**
	 * 删除未通过审核的教师信息
	 * @param userNo
	 */
	public void deleteTeacher(String userNo);
	/**
	 * 获取用户列表
	 * @param usertable
	 * @return
	 */
	List<UserDTO> getUser(User usertable);
}
