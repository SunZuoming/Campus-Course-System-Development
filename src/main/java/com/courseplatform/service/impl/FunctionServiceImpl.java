package com.courseplatform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courseplatform.dao.FunctionTableDao;
import com.courseplatform.po.FunctionTable;
import com.courseplatform.service.FunctionService;

@Service
public class FunctionServiceImpl implements FunctionService {
	@Autowired
	private FunctionTableDao functionmapper;

	
	public FunctionTable selectById(String func) {
	
		return functionmapper.selectById(func);
	}

	
	public List<FunctionTable> listFun() {
		
		return functionmapper.listFun();
	}

	
	public List<FunctionTable> selectS(FunctionTable sid) {
		
		return functionmapper.selectS(sid);
	}

	
	public List<FunctionTable> selectJ(FunctionTable jid) {
		
		return functionmapper.selectJ(jid);
	}

	
	public List<FunctionTable> selectG(FunctionTable gid) {
	
		return functionmapper.selectG(gid);
	}

	
	

}
