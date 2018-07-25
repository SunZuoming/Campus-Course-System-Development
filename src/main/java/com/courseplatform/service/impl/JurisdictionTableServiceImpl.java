package com.courseplatform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courseplatform.dao.JurisdictionTableDao;
import com.courseplatform.po.JurisdictionTable;
import com.courseplatform.po.DTO.JurisdictionTableDTO;
import com.courseplatform.service.JurisdictionTableService;

@Service
public class JurisdictionTableServiceImpl implements JurisdictionTableService {

	@Autowired
	private JurisdictionTableDao jurTableDao;

	public void setJurisdiction(List<JurisdictionTable> jurList) {
		jurTableDao.setJurisdiction(jurList);
	}

	public void delJurisdiction(List<JurisdictionTable> jurListDel) {
		jurTableDao.delJurisdiction(jurListDel);
	}

	/**
	 * szm
	 */
	public List<JurisdictionTableDTO> select(JurisdictionTable jurisdictionno) {
		return jurTableDao.select(jurisdictionno);
	}

	public void insert(JurisdictionTable record) {
		jurTableDao.insert(record);

	}

	public int deleteMap(String[] array) {

		return jurTableDao.deleteMap(array);
	}

	public int delete(String record) {

		return jurTableDao.delete(record);
	}

	public List<JurisdictionTableDTO> selectByRole(String roleid) {

		return jurTableDao.selectByRole(roleid);
	}

}
