package com.courseplatform.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.courseplatform.dao.ProhibitLoginRecordTableDao;
import com.courseplatform.dao.UserTableDao;
import com.courseplatform.po.ProhibitLoginRecordTable;
import com.courseplatform.po.DTO.ProhibitDTO;
import com.courseplatform.service.ProhibitDTOService;

@Service
public class ProhibitDTOServiceImpl implements ProhibitDTOService {

	@Autowired
	private ProhibitLoginRecordTableDao proTableDao;
	
	@Autowired
	private UserTableDao userTableDao;
	
	public List<ProhibitDTO> getProhibitDTO(Map<String, Integer> param) {
		List<ProhibitDTO> prohibitDTOList = new ArrayList<ProhibitDTO>();
		List<ProhibitLoginRecordTable> proTableList = proTableDao.getProHibitTableList(param);
		for (ProhibitLoginRecordTable prohibitLoginRecordTable : proTableList) {
			ProhibitDTO prohibitDTO = new ProhibitDTO();
			prohibitDTO.setProhibitCommitFlag(prohibitLoginRecordTable.getProhibitCommitFlag());
			prohibitDTO.setProhibitCommitTime(prohibitLoginRecordTable.getProhibitCommitTime());
			prohibitDTO.setProhibitLoginer(prohibitLoginRecordTable.getProhibitLoginer());
			prohibitDTO.setProhibitLoginFlag(prohibitLoginRecordTable.getProhibitLoginFlag());
			prohibitDTO.setProhibitLoginOperations(prohibitLoginRecordTable.getProhibitLoginOperations());
			prohibitDTO.setProhibitLoginReason(prohibitLoginRecordTable.getProhibitLoginReason());
			prohibitDTO.setProhibitLoginSurplusDays(prohibitLoginRecordTable.getProhibitLoginSurplusDays());
			prohibitDTO.setProhibitLoginTime(prohibitLoginRecordTable.getProhibitLoginTime());
			prohibitDTO.setProhibitLoginType(prohibitLoginRecordTable.getProhibitLoginType());
			prohibitDTO.setProhibitLogineer(userTableDao.getUserByUserNo(prohibitLoginRecordTable.getProhibitLogineer()));
			prohibitDTOList.add(prohibitDTO);
		}
		return prohibitDTOList;
	}

	public ProhibitDTO getProhibitDTOByNo(String userNo) {
		ProhibitDTO prohibitDTO = new ProhibitDTO();
		ProhibitLoginRecordTable prohibitLoginRecordTable = proTableDao.getProhibitByNo(userNo);

		prohibitDTO.setProhibitCommitFlag(prohibitLoginRecordTable.getProhibitCommitFlag());
		prohibitDTO.setProhibitCommitTime(prohibitLoginRecordTable.getProhibitCommitTime());
		prohibitDTO.setProhibitLoginer(prohibitLoginRecordTable.getProhibitLoginer());
		prohibitDTO.setProhibitLoginFlag(prohibitLoginRecordTable.getProhibitLoginFlag());
		prohibitDTO.setProhibitLoginOperations(prohibitLoginRecordTable.getProhibitLoginOperations());
		prohibitDTO.setProhibitLoginReason(prohibitLoginRecordTable.getProhibitLoginReason());
		prohibitDTO.setProhibitLoginSurplusDays(prohibitLoginRecordTable.getProhibitLoginSurplusDays());
		prohibitDTO.setProhibitLoginTime(prohibitLoginRecordTable.getProhibitLoginTime());
		prohibitDTO.setProhibitLoginType(prohibitLoginRecordTable.getProhibitLoginType());
		prohibitDTO.setProhibitLogineer(userTableDao.getUserByUserNo(prohibitLoginRecordTable.getProhibitLogineer()));

		return prohibitDTO;
	}

	@Transactional
	public void cancelPro(String userNo) {
		userTableDao.cancelProState(userNo);
		proTableDao.cancelProDays(userNo);
	}

}
