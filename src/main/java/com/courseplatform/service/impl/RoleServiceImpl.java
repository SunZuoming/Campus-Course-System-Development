package com.courseplatform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courseplatform.dao.RoleTableDao;
import com.courseplatform.po.RoleTable;
import com.courseplatform.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleTableDao rolemapper;
	
	public RoleTable selectById(String roleid) {
		
		return rolemapper.selectById(roleid);
	}


	public List<RoleTable> list() {

		return rolemapper.list();
	}

}
