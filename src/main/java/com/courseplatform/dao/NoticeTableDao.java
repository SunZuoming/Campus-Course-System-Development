package com.courseplatform.dao;

import java.util.List;

import com.courseplatform.po.NoticeTable;

public interface NoticeTableDao {

	/**
	 * 获取公告总数目
	 * @return
	 */
	public long getNoticeNUm();
	/**
	 * 列出公告信息
	 * @param noticeid
	 * @return
	 */
	List<NoticeTable> getNotice(NoticeTable notice);
	/**
	 * 添加公告
	 * @param noticeid
	 * @return
	 */
	void addNotice(NoticeTable notice);
	/**
	 * 修改公告
	 * @param notice
	 * @return
	 */
	int updateNotice(NoticeTable notice);
	
	/**
	 * 提前结束公告
	 * @param noticeId
	 */
	void endNotice(String noticeId);

    /**
     * 
     * @param notice
     * @return
     */
    NoticeTable selectById(String notice);
    
    void deleteNotice(String notice); 
    
    int deleteMap(Integer[] noticeid);
    
}
