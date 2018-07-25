package com.courseplatform.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courseplatform.dao.NoticeTableDao;
import com.courseplatform.po.NoticeTable;
import com.courseplatform.service.NoticeService;
@Service
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	private NoticeTableDao noticetablemapper;
	
	public void addNotice(NoticeTable noticetable) {
		noticetablemapper.addNotice(noticetable);

	}

	public List<NoticeTable> getNotice(NoticeTable notice) {
		
		return noticetablemapper.getNotice(notice);
	}

	public void updateNotice(NoticeTable notice) {
		
		noticetablemapper.updateNotice(notice);
		
	}

	public NoticeTable selectById(String notice) {
		
		return noticetablemapper.selectById(notice);
	}

	public void deleteNotice(String notice) {
		noticetablemapper.deleteNotice(notice);
		
	}

	public int deleteMap(Integer[] array) {
		
		return noticetablemapper.deleteMap(array);
	}

	public Long getNoticeNum() {
		return noticetablemapper.getNoticeNUm();
	}

	public void endNotice(String noticeId) {
		noticetablemapper.endNotice(noticeId);
	}

}
