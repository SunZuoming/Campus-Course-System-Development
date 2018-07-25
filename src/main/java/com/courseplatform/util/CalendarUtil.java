package com.courseplatform.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class CalendarUtil {

	private static Log log = LogFactory.getLog(CalendarUtil.class);
	/**
	 * 获取系统时间
	 * @return 返回系统当前时间字符串，字符串格式为：yyyy-mm-dd hh:mm:ss
	 */
	
	public static String getSysTimeYMDHMS(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
	}
	/**
	 * 获取系统时间
	 * @return 返回系统当前时间字符串，字符串格式为：MM-dd HH:mm:ss SSS
	 */
	public static String getSysTimeYMDHMS11(){
		return new SimpleDateFormat("MM-dd HH:mm:ss SSS").format(new Date(System.currentTimeMillis()));
	}
	
	/**
	 * 获取系统时间
	 * @return 返回系统当前时间字符串，字符串格式为：MM-dd HH:mm:ss SSS
	 */
	public static String getSysTimeYMDHMS111(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date(System.currentTimeMillis()));
	}
	
	/**
	 * 获取系统时间
	 * @return 返回系统当前时间字符串，字符串格式为：yyyyMMddHHmmss
	 */
	
	public static String getSysTimeYMDHMS1(){
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
	}
		
	/**
	 * 获取系统时间
	 * @return 返回系统当前时间字符串，字符串格式为：yyyyMMddHHmmssSS
	 */
	
	public static String getSysTimeYMDHMS2(){
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date(System.currentTimeMillis()));
	}	
	/**
	 * 获取系统时间
	 * @return 返回系统当前时间字符串，字符串格式为：yyyy-mm-dd hh:mm
	 */
	
	public static String getSysTimeYMDHM(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(System.currentTimeMillis()));
	}

	
	/**
	 * 获取系统时间
	 * @return 返回系统当前时间字符串，字符串格式为：yyyy-mm-dd
	 */
	
	public static String getSysTimeYMD(){
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
	}  
	
	/**
	 * 获取系统时间
	 * @return 返回系统当前时间字符串，字符串格式为：yyyy-mm
	 */
	
	public static String getSysTimeYM(){
		return new SimpleDateFormat("yyyy-MM").format(new Date(System.currentTimeMillis()));
	} 
	
	/**
	 * 获取系统时间
	 * @return 返回系统当前时间字符串，字符串格式为：hh:mm:ss
	 */
	
	public static String getSysTimeHMS(){
		return new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis()));
	} 
       
	/**取系统时间hhmmss
	 * @return String--hhmmss格式的时间字符串
	 */
	
	public static String getTime(){
		return new SimpleDateFormat("HHmmss").format(new Date().getTime());
	}
	
	/**取系统日期yyyymmdd
	 * @return String--yyyymmdd格式的日期字符串
	 */
	
	public static String getDate(){
		return new SimpleDateFormat("yyyyMMdd").format(new Date().getTime());
	}
	
	/**
	 * 根据传入的时间转为YYYYMMDDHHMMSS格式的字符串
	 * @param time 代表时间的长整数
	 * @return 代表时间的字符串
	 */
	
	public static String getDateTime(long time){		
		return new SimpleDateFormat("yyyyMMddHHmmss").format(time);
	}
	
	/**
	 * 根据传入的时间转为YYYY-MM-DD HH:MM:SS格式的字符串
	 * @param time 代表时间的长整数
	 * @return 代表时间的字符串
	 */
	public static String getYMDHMS(long time)
	{
		Calendar cal=Calendar.getInstance();
		cal.setTimeInMillis(time);
		int yearFile=cal.get(Calendar.YEAR);
		int monthFile=cal.get(Calendar.MONTH)+1;
		int dayFile=cal.get(Calendar.DATE);
		int hourFile=cal.get(Calendar.HOUR_OF_DAY);
		int minuteFile=cal.get(Calendar.MINUTE);
		int secondFile=cal.get(Calendar.SECOND);
		String ss=String.format("%04d",yearFile)+"-"
					+String.format("%02d",monthFile)+"-"+
					String.format("%02d",dayFile)+" "+
					String.format("%02d",hourFile)+":"+
					String.format("%02d",minuteFile)+":"+
					String.format("%02d",secondFile);
		return ss;
	}
	
	/**将字符型yyyy-MM-DD转成Date类型日期
	 * @param stringdate 以yyyyMMdd表示的日期字符串
	 * @return date类型日期
	 */
	public static Date str2Date(String stringdate) {	
		if(stringdate==null) return null;
		SimpleDateFormat format=null;
		if(stringdate!=null&&stringdate.length()<8) return null;
		
		if(stringdate!=null&&stringdate.length()==8) format = new SimpleDateFormat("yyyyMMdd");
		if(stringdate!=null&&stringdate.length()==10) format = new SimpleDateFormat("yyyy-MM-dd");
		if(stringdate!=null&&stringdate.length()==13) format = new SimpleDateFormat("yyyy-MM-dd HH");
		if(stringdate!=null&&stringdate.length()==14) format = new SimpleDateFormat("yyyyMMddHHmmss");
		if(stringdate!=null&&stringdate.length()==16) format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if(stringdate!=null&&stringdate.length()==19) format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		try{
			date = format.parse(stringdate);
			return date;
		} catch (Exception e) {
			log.error("日期格式错："+e);
			return null;		
		}
	}
	
	public static String toupdateTime(String datetime){
		String date=datetime.substring(0,datetime.lastIndexOf("-")+3);
		String[] dates1=date.split("-");
		String datetime1="";
		for(int i=0;i<dates1.length;i++){
			if(i==0){
				datetime1+=dates1[i]+"-";
			}else if(i==1){
				if(dates1[i].trim().length()==1){
					datetime1+="0"+dates1[i].trim()+"-";
				}else{
					datetime1+=dates1[i].trim()+"-";
				}
			}else{
				if(dates1[i].trim().length()==1){
					datetime1+="0"+dates1[i].trim()+" ";
				}else{
					datetime1+=dates1[i].trim()+" ";
				}
			}
		}
		String time=datetime.substring(datetime.lastIndexOf("-")+3);
		String[] dates2=time.split(":");
		for(int j=0;j<dates2.length;j++){
			if(j!=dates2.length-1){
				if(dates2[j].trim().length()==1){
					datetime1+="0"+dates2[j].trim()+":";
				}else{
					datetime1+=dates2[j].trim()+":";
				}
			}else{
				if(dates2[j].trim().length()==1){
					datetime1+="0"+dates2[j].trim();
				}else{
					datetime1+=dates2[j].trim();
				}
			}
		}
		return datetime1;
	}
	
	/**转换 date型数据为简要格式
	 * @param dateTime date类型日期
	 * @return "MM-dd HH:mm"字符型日期
	 */

	public static String  fmtMDHM(Date dateTime)
	{	
		
		String fmtTime="";
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
			if (dateTime!=null) fmtTime=sdf.format(dateTime);
			return fmtTime;
		}
		catch(Exception e){
			log.error("转换日期错："+e);
			return null;		
		}
	}
	/**转换 date型数据为简要格式
	 * @param dateTime date类型日期
	 * @return "yyyy-MM-dd HH:mm"字符型日期
	 */
	public static String  fmtYMDHM(Date dateTime)
	{	
		String fmtTime="";
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			if (dateTime!=null) fmtTime=sdf.format(dateTime);
			return fmtTime;
		}
		catch(Exception e){
			log.error("转换日期错:"+e);
			return null;		
		}
	}
	/**转换 yyyyMMddHHmmss型数据为简要格式
	 * @param dateTime String类型日期
	 * @return "yyyy-MM-dd HH:mm:ss"字符型日期
	 */
	public static String  fmtYMDHMS(String dateTime)
	{	
		StringBuffer fmtTime = new StringBuffer();
		
		try
		{
			fmtTime.append(dateTime.substring(0,4));
			fmtTime.append('-');
			
			fmtTime.append(dateTime.substring(4,6));
			fmtTime.append('-');
			
			fmtTime.append(dateTime.substring(6,8));
			fmtTime.append(' ');
			
			fmtTime.append(dateTime.substring(8,10));
			fmtTime.append(':');
			
			fmtTime.append(dateTime.substring(10,12));
			fmtTime.append(':');
			
			fmtTime.append(dateTime.substring(12,14));			
			
		}
		catch(Exception e){
			log.error("转换日期错:"+e);
			return null;		
		}

		return fmtTime.toString();
	}
	
	/**转换 date型数据为简要格式
	 * @param dateTime date类型日期
	 * @return "yyyy-MM-dd HH:mm:ss"字符型日期
	 */
	public static String  fmtYMDHMS(Date dateTime)
	{	
		String fmtTime="";
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (dateTime!=null) fmtTime=sdf.format(dateTime);
			return fmtTime;
		}
		catch(Exception e){
			log.error("转换日期错:"+e);
			return null;		
		}
	}
	
	/**转换 date型数据为简要格式
	 * @param dateTime date类型日期
	 * @return "yyyy-MM-dd"字符型日期
	 */
	public static String  fmtYMD(Date dateTime){	
		String fmtTime="";
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (dateTime!=null) fmtTime=sdf.format(dateTime);
			return fmtTime;
		} catch(Exception e){
			log.error("转换日期错:",e);
			return null;		
		}
	}
	/**
	 * 转换日期格式
	 * @param dateTime yyyy-MM-dd
	 * @return"yyyyMMdd"字符型日期
	 */
	public static String fmtYMD(String dateTime) {
		String fmtDate = "";
		try {
			fmtDate = dateTime.substring(0,4) + dateTime.substring(5, 7) + dateTime.substring(8,10);
			return fmtDate;
		} catch (Exception e) {
			log.error("转换日期出错：",e);
			return null;
		}
	}
	/**获取指定时间的小时
	 * @param dateTime date类型日期
	 * @return 小时(0-23)
	 */
	public static Integer getHours(Date dateTime){	
		try{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dateTime);
			return calendar.get(Calendar.HOUR_OF_DAY);
		} catch(Exception e){
			log.error("转换日期错:",e);
			return null;		
		}
	}
	
	/**
	 * 计算预期响应时间
	 * @param openTime  开始时间点
	 * @param interval 时间间隔
	 * @param respondGrade  响应级别(1-工作时间相应,2-7x24小时响应)
	 * @return 预期响应时间
	 */
	public static Date getPreWorkTime(Date openTime,int interval,int respondGrade)
	{	
		
		
		try{
			Date preExpireTime=new Date(openTime.getTime()+interval*60000);
			return preExpireTime;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;		
		}
	}
	/**
	 * 计算预期响应时间
	 * @param interval 时间间隔
	 * @param respondGrade  响应级别(1-工作时间相应,2-7x24小时响应)
	 * @return 预期响应时间
	 */
	public static Date getPreWorkTime2(int interval,int respondGrade)
	{	
		try{
			Calendar temp=Calendar.getInstance();
			temp.add(Calendar.MINUTE, interval);
			return temp.getTime();
		}
		catch(Exception e){
			e.printStackTrace();
			return null;		
		}
	}
	/**
	 * 获取当前日期前一天的日期，日期格式yyyy-mm-dd
	 * @return 当前日期前一天的日期，日期格式yyyy-MM-dd
	 */
	public static String getPreviousDate()
	{
		Calendar date = Calendar.getInstance();	
		date.add(Calendar.DAY_OF_MONTH,-1);						
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String sysDate = formatter.format(date.getTime()).toString();		
		return sysDate;
	}
	
	/**
	 * 获取与指定的日期相差i月的日期,日期格式为"yyyy-MM-dd"
	 * @param fixDate 指定的日期（格式为"yyyy-MM-dd"）
	 * @param i 相隔月数（i为整数）
	 * @return 计算后的日期
	 */
	public static String getFixedMonth(String fixDate,int i)
	{		
		int year=Integer.parseInt(fixDate.substring(0, 4));
		int month=Integer.parseInt(fixDate.substring(5, 7));
		int day=Integer.parseInt(fixDate.substring(8, 10));
		
		Calendar date = Calendar.getInstance();	
		date.set(year,month-1,day);
		date.add(Calendar.MONTH,i);						
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String sysDate = formatter.format(date.getTime()).toString();		
		return sysDate;
	}
	
	/**
	 * 获取与指定的日期相差i天的日期,日期格式为"yyyy-MM-dd"
	 * @param fixDate 指定的日期（格式为"yyyy-MM-dd"）
	 * @param i 相隔天数（i为整数）
	 * @return 计算后的日期
	 */
	public static String getFixedDate(String fixDate,int i)
	{		
		int year=Integer.parseInt(fixDate.substring(0, 4));
		int month=Integer.parseInt(fixDate.substring(5, 7));
		int day=Integer.parseInt(fixDate.substring(8, 10));
		
		Calendar date = Calendar.getInstance();	
		date.set(year,month-1,day);
		date.add(Calendar.DAY_OF_MONTH,i);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String sysDate = formatter.format(date.getTime()).toString();		
		return sysDate;
	}

	/**
	 * 获取与指定的日期相差i天的日期,日期格式为"yyyy-MM-dd"
	 * @param fixDate 指定的日期（格式为"yyyy-MM-dd"）
	 * @param i 相隔天数（i为整数）
	 * @return 计算后的日期
	 */
	public static String getFixedDateYYYYMMDD(String fixDate,int i)
	{		
		int year=Integer.parseInt(fixDate.substring(0, 4));
		int month=Integer.parseInt(fixDate.substring(5, 7));
		int day=Integer.parseInt(fixDate.substring(8, 10));
		
		Calendar date = Calendar.getInstance();	
		date.set(year,month-1,day);
		date.add(Calendar.DAY_OF_MONTH,i);						
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String sysDate = formatter.format(date.getTime()).toString();		
		return sysDate;
	}
	
	/**
	 * 获取与指定的日期相差i天的日期,日期格式为"yyyy-MM-dd"
	 * @param fixDate 指定的日期（格式为"yyyy-MM-dd"）
	 * @param i 相隔天数（i为整数）
	 * @return 计算后的日期
	 */
	public static String getFixedDateYYYY_MM_DD(String fixDate,int i)
	{		
		int year=Integer.parseInt(fixDate.substring(0, 4));
		int month=Integer.parseInt(fixDate.substring(5, 7));
		int day=Integer.parseInt(fixDate.substring(8, 10));
		
		Calendar date = Calendar.getInstance();	
		date.set(year,month-1,day);
		date.add(Calendar.DAY_OF_MONTH,i);						
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String sysDate = formatter.format(date.getTime()).toString();		
		return sysDate;
	}
	
	/**
	 * 获取前一个月前日期，日期格式yyyy-mm
	 * @return 前一个月前日期，日期格式yyyy-MM
	 */
	public static String getPreviousMonth() {
		Calendar date = Calendar.getInstance();	
		date.add(Calendar.MONTH,-1);						
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		String sysDate = formatter.format(date.getTime()).toString();		
		return sysDate;
	}
	
	/**
	 * 获取当前日期与参数间隔（分钟）的历史日期
	 * 
	 * @param minute
	 *            分钟
	 * @param pattern 
	 * @return 历史日期，日期格式yyyyMMddHHmmss
	 */
	public static String getPreMinuteDateTime(String minute, String pattern) {
		try {
			int min = Integer.parseInt(minute);
			Calendar date = Calendar.getInstance();
			date.add(Calendar.MINUTE, -min);
			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			String sysDate = formatter.format(date.getTime()).toString();
			return sysDate;
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 获取当前日期与参数间隔（分钟）的历史日期
	 * @param minute 分钟
	 * @return 历史日期，日期格式yyyyMMddHHmmss
	 */
	public static String getPreMinuteDateTime(String minute)
	{		
		try
		{			
			int min = Integer.parseInt(minute);
			Calendar date = Calendar.getInstance();				
			date.add(Calendar.MINUTE, -min);			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			String sysDate = formatter.format(date.getTime()).toString();			
			return sysDate;
		}
		catch(Exception e)
		{
			return null;
		}
	}

	/**
	 * 获取当前日期与参数间隔（分钟）的历史日期
	 * @param minute 分钟
	 * @return 历史日期，日期格式yyyy-MM-dd HH:mm:ss
	 */
	public static String getPreMinuteDateTime2(int minute)
	{		
		try
		{			
			Calendar date = Calendar.getInstance();				
			date.add(Calendar.MINUTE, -minute);			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String sysDate = formatter.format(date.getTime()).toString();			
			return sysDate;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	/**
	 * 获取当前日为周几
	 * @return 当前为周几
	 * */
	public static String getDayOfWeek()
	{		
		
		Calendar cal = Calendar.getInstance();
		switch(cal.get(Calendar.DAY_OF_WEEK))
		{
			case 2:return "Monday";
			case 3:return "Tuesday";
			case 4:return "Wednesday";
			case 5:return "Thursday";
			case 6:return "Friday";
			case 7:return "Saturday";
			case 1:return "Sunday";				
		}
		return null;
	}
	
	/**
	 * 获取指定日期为周几
	 * @param cal
	 * @return
	 */
	public static String getDayOfWeek(Calendar cal)
	{
		switch(cal.get(Calendar.DAY_OF_WEEK))
		{
			case 2:return "Monday";
			case 3:return "Tuesday";
			case 4:return "Wednesday";
			case 5:return "Thursday";
			case 6:return "Friday";
			case 7:return "Saturday";
			case 1:return "Sunday";				
		}
		return null;
	}
	
	/**
	 * 获取指定日为周几
	 * @param date yyyy-MM-dd
	 * @return
	 */
	public static String getDayOfWeek(String date)
	{
		try
		{
			Calendar cal = Calendar.getInstance();
			cal.set(Integer.parseInt(date.substring(0,4)), Integer.parseInt(date.substring(5,7))-1, Integer.parseInt(date.substring(8,10)));
			switch(cal.get(Calendar.DAY_OF_WEEK))
			{
				case 2:return "Monday";
				case 3:return "Tuesday";
				case 4:return "Wednesday";
				case 5:return "Thursday";
				case 6:return "Friday";
				case 7:return "Saturday";
				case 1:return "Sunday";				
			}
		} catch (Exception e)
		{
		}
		return null;
	}
	
	/**
	 * 获取时间差
	 * @return 时间差
	 * */
	public static String getDifOfTime(String time)
	{				
		long diftime = System.currentTimeMillis() - str2Date(time).getTime();
		String mark="";
		if(diftime<0)
		{
			mark="-";
			diftime = Math.abs(diftime);
		}
		long days = (long)Math.floor(diftime/(1000*3600*24));
		long hours = (long)Math.floor((diftime - days*1000*3600*24)/(1000*3600));
		long minitues = (long)Math.floor((diftime - days*1000*3600*24 - hours*1000*3600)/(1000*60));
		
		String difofTime =mark + days + "天" + hours + "小时" + minitues +"分";
		return difofTime;
	}
	/**
	 * 获取两者的时间差
	 * @param time1,time2 时间格式：yyyy-mm-dd hh:mm:ss
	 * @return double 单位：天 
	 */
	public static Double getSubTime(String endTime,String startTime){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return  (df.parse(endTime).getTime() - df.parse(startTime).getTime()) * 1.00 /(1000 * 60 * 60 * 24);
		} catch (ParseException e) {
			log.error("时间格式不正确");
		}
		return 0.00;
	}
	
	/**
	 * 获取两者的时间差
	 * @param time1,time2 时间格式：yyyy-mm-dd
	 * @return Long 单位：毫秒
	 */
	public static Long getSubLongTime(String time1,String time2){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date1 = df.parse(time1);
			Date date2 = df.parse(time2);
			return  date1.getTime() - date2.getTime();
		} catch (ParseException e) {
			log.error("时间格式不正确");
			e.printStackTrace();
		}
		return 0L;
	}
	
	/**
	 * 获取两者的时间差
	 * @param time1,time2 时间格式：yyyy-mm-dd HH:mm:ss
	 * @return double 单位：天 时 分 秒
	 */
	public static String getSubTimeFully(String time1,String time2){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			Date date1 = df.parse(time1);
			Date date2 = df.parse(time2);
			long time = date1.getTime()-date2.getTime() ;
			long day = time/(24*60*60*1000) ;
			long hour = (time/(60*60*1000)-day*24) ;
			long min = ((time/(60*1000))-day*24*60-hour*60) ;
			return day+"天"+hour+"时"+min+"分";
		} catch (ParseException e) {
			log.error("时间格式不正确");
			e.printStackTrace();
		}
		return "0天0时0分";
	}
	
	
	/**
	 * 获取两者的时间差
	 * @param time1,time2 时间格式：yyyy-mm-dd HH:mm:ss
	 * @return double 单位：天 时 分 秒
	 */
	public static String getSubTimeFully1(String time1,String time2){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date1 = df.parse(time1);
			Date date2 = df.parse(time2);
			long time = date1.getTime()-date2.getTime() ;
		 
			long hour = (time/(60*60*1000) ) ;
			long min = ((time/(60*1000)) -hour*60) ;
			long second = ((time/1000)-hour*60*60-min*60);
			return   hour+"小时"+min+"分钟"+second+"秒";
		} catch (ParseException e) {
			log.error("时间格式不正确");
			e.printStackTrace();
		}
		return " 0小时0分钟0秒";
	}
	
	/**
	 * 获取字符串日期中的day(一个月中的第几天)
	 * @param dateTime 日期（YYYY-MM-DD）
	 * @return 一个月中的第几天
	 */
	public static String getDayOfStrDateTime(String dateTime) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = df.parse(dateTime);
			Calendar cal=Calendar.getInstance();
			cal.setTime(date);
			return cal.get(Calendar.DAY_OF_MONTH) + "";
		} catch (ParseException e) {
			log.error("时间格式不正确dateTime=["+dateTime+"]");
			return null;
		}
	}
	
	public static String getMonthOfStrDateTime(String time) {
		DateFormat df = new SimpleDateFormat("yyyy-MM");
		try {
			Date date = df.parse(time);
			Calendar cal=Calendar.getInstance();
			cal.setTime(date);
			return cal.get(Calendar.MONTH) + 1 + "";
		} catch (ParseException e) {
			log.error("时间格式不正确dateTime=["+time+"]");
			return null;
		}
	}
}