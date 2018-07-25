package com.courseplatform.dao;

import java.util.Map;

public interface CollectionTableDao {

	/**
	 * 获取收藏总数目
	 * @return
	 */
	public long getCollectionNum();
	
	/**
	 * 获取某人在某日的收藏数目
	 * @param param
	 * @return
	 */
	public Integer getCollectionNumByNo(Map<String, String> param);
}
