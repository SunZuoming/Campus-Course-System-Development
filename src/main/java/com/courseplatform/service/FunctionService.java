package com.courseplatform.service;

import java.util.List;

import com.courseplatform.po.FunctionTable;



public interface FunctionService {
	
	FunctionTable selectById(String func);
	
	List<FunctionTable> selectS(FunctionTable sid);
	
	List<FunctionTable> selectJ(FunctionTable jid);
	
	List<FunctionTable> selectG(FunctionTable gid);
	
	List<FunctionTable> listFun();
}
