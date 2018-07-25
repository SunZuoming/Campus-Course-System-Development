package com.courseplatform.dao;

import com.courseplatform.po.Pricecourse;
import com.courseplatform.po.Privatecourse;
import com.courseplatform.po.WatchTable;
import java.util.List;
public interface WatchDao {
	/**
	 * 插入一条带有关注标记的课程记录
	 * @param watch
	 */
	public void insertWatchCourse(WatchTable watch);
	
	/**
	 * 删除学生课程
	 * @param watch
	 */
	public void deleteWatchCourse(WatchTable watch);
	
	
	/**
	 * 查询学生关注课程
	 * @param watch
	 */
	public List<WatchTable> userWatchCourse(WatchTable watch);
	
	/**
	 * 查询特定学生关注课程
	 * @param watch
	 */
	public WatchTable uWatchCourse(WatchTable watch);
	
	/**
	 * 查询学生关注课程详细
	 * @param watch
	 */
	public WatchTable userWatCourse(WatchTable watch);
	
	/**
	 * id查询收费课程信息
	 * @param watch
	 */
	public Pricecourse prCourse(Pricecourse pri);
	
	/**
	 * 插入收费课程记录
	 * @param watch
	 */
	public void insertpriCourse(Privatecourse pse);
	
	/**
	 * 选择收费课程记录
	 * @param watch
	 */
	public List<Privatecourse>  AllpriCourse(Privatecourse pse);
	
	/**
	 * 根据账号选择收费课程记录
	 * @param watch
	 */
	public Privatecourse  priCourse(Privatecourse pse);
	
	/**
	 * 删除视频
	 */
	
	public void deleteWatch(Privatecourse pse);
	/**
	 * 根据id选择
	 */
	public Privatecourse selectWatch(Privatecourse pse);
	
	
}

