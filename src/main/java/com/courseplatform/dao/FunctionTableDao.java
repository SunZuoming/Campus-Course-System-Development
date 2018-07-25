package com.courseplatform.dao;

import java.util.List;

import com.courseplatform.po.FunctionTable;

public interface FunctionTableDao {

	String selectById(FunctionTable functionTable);

	FunctionTable selectById(String func);
	
	List<FunctionTable> listFun();
	//
	List<FunctionTable> selectS(FunctionTable sid);
	
	List<FunctionTable> selectJ(FunctionTable jid);
	
	List<FunctionTable> selectG(FunctionTable gid);

}
