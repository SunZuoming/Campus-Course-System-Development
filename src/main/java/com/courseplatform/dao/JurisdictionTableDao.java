package com.courseplatform.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.courseplatform.po.JurisdictionTable;
import com.courseplatform.po.DTO.JurisdictionTableDTO;

public interface JurisdictionTableDao {

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
	
	// 查询角色编号
	public JurisdictionTable getJurisdiction(JurisdictionTable jurisdiction);
	
	
	/**
	 * s
	 */
	/**
	 * 批量删除
	 * @param jurisdictionno
	 * @return
	 */
    int deleteMap(String[] array);
    /**
     * 删除
     * @param record
     */
    int delete(String record);
    /**
     * 插入
     * @param record
     */
    void insert(JurisdictionTable record);

    /**
     * 
     * @param roleid
     * @return
     */
	List<JurisdictionTableDTO> selectByRole(String roleid);
    /**
     * 
     * @param jurisdictionno
     * @return
     */
    List<JurisdictionTableDTO> select(JurisdictionTable jurisdictionno);
    
    
    
}
