package com.courseplatform.dao;

import java.util.List;

import com.courseplatform.po.RoleTable;

public interface RoleTableDao {
	RoleTable  selectById(String roleid);
    
    List<RoleTable> list(); 
}
