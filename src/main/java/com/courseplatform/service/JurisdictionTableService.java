package com.courseplatform.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.courseplatform.po.JurisdictionTable;
import com.courseplatform.po.DTO.JurisdictionTableDTO;

public interface JurisdictionTableService {

	
	/**
	 * 批量插入
	 * @param jurList
	 */
	public void setJurisdiction(@Param("jurList")List<JurisdictionTable> jurList);
	
	/**
	 * 批量删除
	 * @param jurListDel
	 */
	public void delJurisdiction(List<JurisdictionTable> jurListDel);
	
	
	/**
	 * szm
	 * @param jurisdictionno
	 * @return
	 */
	List<JurisdictionTableDTO> select(JurisdictionTable jurisdictionno);

	//List<String> selectFunc(JurisdictionTable jurisdictionno);
	void insert(JurisdictionTable record);
	/**
	 * 批量删除
	 */
	public int deleteMap(String[] array);
	/**
	 * 删除
	 * @param record
	 * @return
	 */
	int delete(String record);
	
	List<JurisdictionTableDTO> selectByRole(String roleid);
}
