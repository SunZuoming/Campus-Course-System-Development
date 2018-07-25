package com.courseplatform.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courseplatform.dao.CourseinfoTableDao;
import com.courseplatform.dao.TeacherCourseDao;
import com.courseplatform.dao.WatchDao;
import com.courseplatform.po.CourseinfoTable;
import com.courseplatform.po.Pricecourse;
import com.courseplatform.po.Privatecourse;
import com.courseplatform.po.TeacherCourse;
import com.courseplatform.po.WatchTable;
import com.courseplatform.service.WatchService;

@Service
public class WatchServiceImpl implements WatchService{
	/** 取消关注课程标记*/
	public static final String UNWATCH_ONE_COURSE = "0";
	/** 关注课程标记*/
	public static final String WATHED_ONE_COURSE = "1";
	
	/** 取消关注课程标记*/
	public static final String DEFUALT_WATCH_STATE = WATHED_ONE_COURSE;

	@Autowired
	private WatchDao watchDao;
	
	@Autowired
	private TeacherCourseDao teachercoursedao;
	
	@Autowired
	private  CourseinfoTableDao courseinfo;
	
	
	//查询所有的课程
		public  List<TeacherCourse> CourseAll(Map<String, Integer> param) {
			return teachercoursedao.CourseAll(param);
		}
		
		/**
		 * 插入一条带有关注标记的课程记录
		 * @param watch
		 */
		public void insertWatchCourse(WatchTable watch) {
			watchDao.insertWatchCourse(watch);
		}
		
		/**
		 * 删除学生课程
		 * @param watch
		 */
		public void deleteWatchCourse(WatchTable watch) {
			watchDao.deleteWatchCourse(watch);
		}
		
		//查询id的课程
		public  TeacherCourse CourseByid(TeacherCourse teachercourse) {
			return teachercoursedao.CourseByid(teachercourse);
		}

		//根据课程名称得到课程信息
	    public CourseinfoTable getcourseno(String cname) {
	    	return courseinfo.getcourseno(cname);
	    }
	    
	    /**
		 * 查询学生关注课程
		 * @param watch
		 */
		public List<WatchTable> userWatchCourse(WatchTable watch){
			return watchDao.userWatchCourse(watch);
		}
		
		/**
		 * 查询收费课程信息
		 * @param watch
		 */
		public Pricecourse prCourse(Pricecourse pri) {
			return watchDao.prCourse(pri);
		}
		
		/**
		 * 插入收费课程记录
		 * @param watch
		 */
		public void insertpriCourse(Privatecourse pse) {
			watchDao.insertpriCourse(pse);
		}
		
		/**
		 * 选择收费课程记录
		 * @param watch
		 */
		public List<Privatecourse>  AllpriCourse(Privatecourse pse){
			return watchDao.AllpriCourse(pse);
		}
		
		/**
		 * 查询学生关注课程详细
		 * @param watch
		 */
		public WatchTable userWatCourse(WatchTable watch){
			return watchDao.userWatCourse(watch);
		}
		
		/**
		 * 查询特定学生关注课程
		 * @param watch
		 */
		public WatchTable uWatchCourse(WatchTable watch) {
			return watchDao.uWatchCourse(watch);
			
		}
		
		/**
		 * 根据账号选择收费课程记录
		 * @param watch
		 */
		public Privatecourse  priCourse(Privatecourse pse) {
			return watchDao.priCourse(pse);
		}
		
		/**
		 * 得到教师任课记录数
		 * @param watch
		 */
		public Integer getteacourseNum() {
			return teachercoursedao.getteacourseNum();
		}
		
		/**
		 * 删除视频
		 */
		
		public void deleteWatch(Privatecourse pse) {
			watchDao.deleteWatch(pse);
		}
		
		/**
		 * 根据id选择
		 */
		public Privatecourse selectWatch(Privatecourse pse) {
			return watchDao.selectWatch(pse);
		}
}