package com.courseplatform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courseplatform.dao.SharedfileexamineTableDao;
import com.courseplatform.po.SharedfileexamineTable;
import com.courseplatform.service.SharedFileExamineTableService;

@Service
public class SharedFileExamineTableServiceImpl implements SharedFileExamineTableService {

	@Autowired
	private SharedfileexamineTableDao fileExamineDao;
	
	public void createFileExamine(SharedfileexamineTable fileTable) {
		fileExamineDao.createFileExamine(fileTable);
	}

	public void updateFileExamine(SharedfileexamineTable fileTable) {
		fileExamineDao.updateFileExamine(fileTable);
	}

	public List<SharedfileexamineTable> getFileExamineList(SharedfileexamineTable sharedfileexamineTable) {
		return fileExamineDao.getFileExamineList(sharedfileexamineTable);
	}

	
	public void insertRecord(SharedfileexamineTable sharefile) {
		fileExamineDao.insert(sharefile);
		
	}
}
