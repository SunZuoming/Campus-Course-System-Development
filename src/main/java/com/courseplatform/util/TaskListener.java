package com.courseplatform.util;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.courseplatform.po.NoticeTable;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;  
public class TaskListener implements ServletContextListener{  
	
    public void contextDestroyed(ServletContextEvent arg0) {  
        //销毁时的代码  
    }  
      
        //在服务启动时，执行此方法。  
    public void contextInitialized(ServletContextEvent arg0) {  
        new TimerManager();  
    }  
}  

//要执行的任务  
class MyTimerTask extends TimerTask{  
	
    @Override  
    //此方法为具体要定时操作的方法  
    public void run() {  
    	try {	
			NoticeTable notice=new NoticeTable();
			System.out.println("cc"+System.currentTimeMillis());
			select s=new select();
			s.update();		
		} catch (Exception e) {
			// TODO: handle exception
		}
     //System.out.println("定时器测试:"+System.currentTimeMillis());  
    } 
  
}  
class TimerManager{  
    private static final long PERIOD_DAY=60*60 * 1000;  //每隔一小时执行一次  
    public TimerManager() {                     
        Timer timer = new Timer();     //定时器实例化   
         MyTimerTask task = new MyTimerTask();   //要执行的任务  
         //安排指定的任务在指定的时间开始进行重复的固定延迟执行。     
              timer.schedule(task,new Date(),PERIOD_DAY);    
       }      
} 
class select{
	@SuppressWarnings("resource")
	public void update(){
		//System.out.println("bb"+System.currentTimeMillis());
		 //声明Connection对象
		Connection con=null;
		Statement statement=null;
		//驱动程序名
		String driver = "com.mysql.jdbc.Driver";
		//URL指向要访问的数据库名
		String url = "jdbc:mysql://localhost:3306/courseplatform";
		//MySQL配置时的用户名
		String user = "root";
		//MySQL配置时的密码
		String password = "123456";
		try {
			Class.forName(driver);
			con=(Connection) DriverManager.getConnection(url, user, password);
			if(!con.isClosed()){
				//System.out.println("1111");
				statement=(Statement) con.createStatement();
				String sql="select * from notice_table";
				ResultSet res=statement.executeQuery(sql);
				//System.out.println(res.next());
				while(res.next()){
					
					String id=res.getString("NOTICEENDFLAG");
					String date =res.getString("NOTICEENDDATE");
					//System.out.println(id);
					//System.out.println(date);
					PreparedStatement pst=null;
					pst=(PreparedStatement) con.prepareStatement("update notice_table set NOTICEENDFLAG=? where str_to_date(NOTICEENDDATE,'%Y%m%d')<=str_to_date(?,'%Y%m%d')");
					pst.setString(1, "1");
					CalendarUtil cal=new CalendarUtil();
					String cal1=cal.getSysTimeYMDHMS1();
					//System.out.println("xxxxx"+cal1);
					pst.setString(2, cal1);
					pst.executeUpdate();
					pst.close();			
				}
				//执行res.next（）时已经是第二条数据了，所以要返回一次
				res.previous();
				res.close();
			}
		}catch(SQLException sql){
			//处理jdbc异常
			sql.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
