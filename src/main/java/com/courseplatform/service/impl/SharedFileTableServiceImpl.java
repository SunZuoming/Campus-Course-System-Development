package com.courseplatform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courseplatform.dao.SharedfileTableDao;
import com.courseplatform.po.SharedfileTable;
import com.courseplatform.service.SharedFileTableService;

@Service
public class SharedFileTableServiceImpl implements SharedFileTableService {

	@Autowired
	private SharedfileTableDao sharedfileTableDao;
	
	public void examineFile(SharedfileTable fileTable) {
		sharedfileTableDao.examineFile(fileTable);
	}

	public List<SharedfileTable> noExamineFileList(SharedfileTable sharedfileTable) {
		return sharedfileTableDao.noExamineFileList(sharedfileTable);
	}
	public void addfile(SharedfileTable sharefile) {
		sharedfileTableDao.addfile(sharefile);

	}

	public SharedfileTable queryById(String sharefile) {
		
		return sharedfileTableDao.queryById(sharefile);
	}

	public List<SharedfileTable> listShare() {
		
		return sharedfileTableDao.listShare();
	}

	
	public void update(SharedfileTable sharefile) {
		
		sharedfileTableDao.update(sharefile);
	}

	public void delete(SharedfileTable sharefile) {
		sharedfileTableDao.delete(sharefile);
		
	}

}
