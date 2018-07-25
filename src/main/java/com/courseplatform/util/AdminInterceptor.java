package com.courseplatform.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.courseplatform.po.User;

public class AdminInterceptor implements HandlerInterceptor{

	/**
	 * 返回值说明是否需要将当前的请求拦截下来 
	 * false：请求将被终止 
	 * true：请求会被继续运行 
	 * Object handler 表示的是被拦截的请求的目标对象
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		User user =(User) request.getSession().getAttribute("adminSession");
		if("/pages/admin/adminlogin.do".equals(request.getServletPath())) {
			return true;
		}
		if(user == null){
			response.sendRedirect(request.getContextPath()+"/pages/adminLogin.jsp");
			return false;
		}else {
			return true;
		}
//		return true;
	}

	/**
	 * modelAndView： 可以通过modelAndView参数改变显示的视图，或者修改发往视图的方法
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 在视图加载完毕之后调用 常用于作于资源的销毁，流等 类似于析构函数
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
