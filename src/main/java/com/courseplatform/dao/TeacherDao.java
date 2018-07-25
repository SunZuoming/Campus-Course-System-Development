package com.courseplatform.dao;

import java.util.List;

import com.courseplatform.po.CourseFile;
import com.courseplatform.po.User;

public interface TeacherDao {
    
	//更据账号搜索
    public User getUser(User user);
	  
    //注册
    public void registerUser(User user); 
      
      /*
  	 * 搜索用户个人信息
  	 */
  	//public User getUserInfo(User user);
  	/*
  	 * 更新用户个人信息
  	 */
  	public void updateUser(User user);
  	/*
  	 * 更新用户密码信息
  	 */
    public void updateUserPassword(User user);
  	/*
  	 * 搜索全部用户信息
  	 */
  //	public List<User> getUserList(User user);
  	
  	
  	//登录注册
  	
    //根据code查找user
	public User findByCode(User user);
    //更新code
	public void updateCode(User user);
	
	//寻找密码
	public User searchPassword(User user);
	
}
