package com.courseplatform.service;

import java.util.List;

import com.courseplatform.po.RoleTable;

public interface RoleService {
	
	RoleTable selectById(String role);
	
	List<RoleTable> list();
}
