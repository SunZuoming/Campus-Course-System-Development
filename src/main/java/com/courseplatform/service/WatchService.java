package com.courseplatform.service;

import java.util.List;
import java.util.Map;

import com.courseplatform.po.CourseinfoTable;
import com.courseplatform.po.Pricecourse;
import com.courseplatform.po.Privatecourse;
import com.courseplatform.po.TeacherCourse;
import com.courseplatform.po.WatchTable;
/**
 * 课程关注服务
 */
public interface WatchService {

	/**
	 * 建立学生对某个老师某项课程的关注情况，
	 * <p>
	 * <pre>
	 * 1 首先查询学生是否存在当前选课记录
	 * 2 如果还没有学生对该老师的课程的关注，直接建立一条关注的记录
	 * 3 否则,执行如下更新操作</pre>
	 * </p>
	 * <ul>
	 * <li>if关注  then 1----> 0取消</li>
	 * <li>if取消  then 0----> 1关注</li>
	 * </ul>
	 * </p>
	 * @param watch 业务模型
	 * @return 
	 * {@code ResponseMessage.responseCode == 0},{@code ResponseMessage.message} 就是返回修改后的状态编码
	 * {@code ResponseMessage.responseCode != 0},{@code ResponseMessage.message} 就是错误信息
	 * @see com.courseplatform.response.ResponseMessage
	 */

	
	//查询所有的课程
		public  List<TeacherCourse> CourseAll(Map<String, Integer> param);
		
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
		
		//查询id的课程
		public  TeacherCourse CourseByid(TeacherCourse teachercourse);
		
		//根据课程名称得到课程信息
	    public CourseinfoTable getcourseno(String cname);
	    
	    /**
		 * 查询学生关注课程
		 * @param watch
		 */
		public List<WatchTable> userWatchCourse(WatchTable watch);
		
		/**
		 * 查询学生关注课程详细
		 * @param watch
		 */
		public WatchTable userWatCourse(WatchTable watch);
		
		
		/**
		 * 查询收费课程信息
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
		 * 查询特定学生关注课程
		 * @param watch
		 */
		public WatchTable uWatchCourse(WatchTable watch);
		
		/**
		 * 根据账号选择收费课程记录
		 * @param watch
		 */
		public Privatecourse  priCourse(Privatecourse pse);

		/**
		 * 得到教师任课记录数
		 * @param watch
		 */
		public Integer getteacourseNum();
		
		/**
		 * 删除视频
		 */
		
		public void deleteWatch(Privatecourse pse);
		
		
		/**
		 * 根据id选择
		 */
		public Privatecourse selectWatch(Privatecourse pse);
}

