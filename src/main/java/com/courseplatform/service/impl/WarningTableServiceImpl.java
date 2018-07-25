package com.courseplatform.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.courseplatform.dao.ProhibitLoginRecordTableDao;
import com.courseplatform.dao.UserTableDao;
import com.courseplatform.dao.WarningTableDao;
import com.courseplatform.po.ProhibitLoginRecordTable;
import com.courseplatform.po.User;
import com.courseplatform.po.WarningTable;
import com.courseplatform.service.WarningTableService;
import com.courseplatform.util.CalendarUtil;
import com.courseplatform.util.StringUtil;

@Service
public class WarningTableServiceImpl implements WarningTableService {

	@Autowired
	private WarningTableDao warningDao;
	
	@Autowired
	private UserTableDao userTableDao;
	
	@Autowired
	private ProhibitLoginRecordTableDao proTableDao;
	
	@Transactional
	public String publicWarning(WarningTable warning) {
		String flag = "2";
		User user = userTableDao.getUserByUserNo(warning.getWarningpeople());
		user.setWarningNum(user.getWarningNum()+1);
		if("1".equals(user.getUserState())) {
			warning.setWarningfinshflag("1");
			warning.setWarningenddate(CalendarUtil.getSysTimeYMD());
			flag = "3";
		}else {
			if(user.getWarningNum() % 5 == 0) {
				if(user.getProhibitLoginnum()==4) {
					user.setUserState("2");
					user.setProhibitLoginnum(5);
					flag="0";
				}else {
					ProhibitLoginRecordTable proTable = proTableDao.getProhibitByNo(user.getUserNo());
					if(proTable == null) {
						ProhibitLoginRecordTable prTableResult = new ProhibitLoginRecordTable();
						prTableResult.setProhibitLogineer(user.getUserNo());
						prTableResult.setProhibitLoginReason("警告次数超过5次");
						prTableResult.setProhibitLoginer("System");
						prTableResult.setProhibitLoginTime(CalendarUtil.getDateTime(new Date().getTime()));
						prTableResult.setProhibitLoginType(Integer.toString(user.getProhibitLoginnum()));
						prTableResult.setProhibitLoginFlag("0");
						prTableResult.setProhibitLoginOperations("");
						prTableResult.setProhibitCommitFlag("1");
						prTableResult.setProhibitCommitTime(CalendarUtil.getDateTime(new Date().getTime()));
						prTableResult.setProhibitLoginSurplusDays(StringUtil.getProhibitLoginSurplusDays(user));
						proTableDao.insertProRecord(prTableResult);
					}else {
						proTable.setProhibitLoginReason("警告次数超过5次");
						proTable.setProhibitLoginer("System");
						proTable.setProhibitLoginTime(CalendarUtil.getDateTime(new Date().getTime()));
						proTable.setProhibitLoginType(Integer.toString(user.getProhibitLoginnum()));
						proTable.setProhibitLoginFlag("0");
						proTable.setProhibitLoginOperations("");
						proTable.setProhibitCommitFlag("1");
						proTable.setProhibitCommitTime(CalendarUtil.getDateTime(new Date().getTime()));
						proTable.setProhibitLoginSurplusDays(StringUtil.getProhibitLoginSurplusDays(user));
						proTableDao.updateProRecord(proTable);
					}
					user.setProhibitLoginnum(user.getProhibitLoginnum()+1);
					user.setUserState("1");
					user.setUserSystemscore((float) -1);
					flag="1";
				}
			}
		}
		warningDao.publicWarning(warning);
		userTableDao.updateUser(user);
		return flag;
	}

	public List<WarningTable> getWarningList() {
		
		return warningDao.getWarningList();
	}

	public void endWarning(WarningTable warning) {
		warningDao.endWarning(warning);
	}

}
