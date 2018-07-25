package com.courseplatform.service;

import java.util.List;

import com.courseplatform.po.NoticeTable;

public interface NoticeService {
	/**
	 * 
	 * @return
	 */
	List<NoticeTable> getNotice(NoticeTable notice);
	
	Long getNoticeNum();
	
	void addNotice(NoticeTable noticetable);
	
	void updateNotice(NoticeTable notice);
	
	NoticeTable selectById(String notice);
	
	void deleteNotice(String notice);
	
	public int deleteMap(Integer[] array);
	
	/**
	 * 提前结束公告
	 * @param noticeId
	 */
	void endNotice(String noticeId);
}
