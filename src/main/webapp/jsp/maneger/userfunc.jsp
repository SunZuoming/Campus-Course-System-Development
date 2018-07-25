<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	session.setAttribute("basePath", basePath);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="utf-8">
        <title>
            角色管理
        </title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" href="${basePath }/css1/x-admin.css" media="all">
        
      <script type="text/javascript"  
    src="${pageContext.request.contextPath}/js1/jquery-1.9.1.min.js"></script>  
    </head>
    <body>
        <div class="x-nav">
            <span class="layui-breadcrumb">
              <a><cite>首页</cite></a>
              <a><cite>角色管理</cite></a>
            </span>
            <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"  href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i></a>
        </div>
        <div class="x-body">
            <form layui-form x-center" action="" style="width:500px">
                <div class="layui-form-pane" style="margin-top: 15px;">
                  <div class="layui-form-item">
                    <div class="layui-input-inline" style="width:400px">
                      <input type="text" name="username"  placeholder="搜索内容" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-input-inline" style="width:80px">
                        <button type="submit" class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
                    </div>
                  </div>
                </div> 
            </form>
            <xblock>
            <span class="x-right" style="line-height:25px">共有数据： 条</span></xblock>
            <table class="layui-table">
                <thead>
                    <tr>
                       <!--  <th width="8%">
                            <input type="checkbox" name="" value="">
                        </th> -->
                        <th width="9%">
                            用户编号
                        </th>
                        <th width="16%">
                            用户名
                        </th>
                         <th width="16%">
                            用户标识
                        </th>
                        <th width="11%">
                            操作
                        </th>
                    </tr>
                </thead>
               <tbody>
                <c:forEach items="${user}" var="s">
                    <tr>
                        <%-- <td>
                            <input type="checkbox" value="${s.jurisdictionno } " name="deleteCheckBox" >
                        </td> --%>
                        <td id="record">
                           ${s.userno } 
                        </td>
                         <td>
                            ${s.username}</td>
                        <td>
                           ${s.roleid.rolename}
                        </td>
                        <td class="td-manage">
                        	<a title="查看" href="${pageContext.request.contextPath}/role/queryfunc.do?id=${roleid}" onclick="role_management_look('查看','role_management_look.html','4','','510')"
                            class="ml-5" style="text-decoration:none">
                                <i class="layui-icon"><img src="${basePath}images/look3.png" width="15" height="15"></i>
                            </a>
                            <a title="权限" href="${pageContext.request.contextPath}/role/funcinfo.do?id=${s.userno}" onclick="submit()" 
                            style="text-decoration:none" >
                                <i class="layui-icon"><img src="${basePath}images/quanxian.png" width="15" height="15"></i>
                            </a>
                        </td>
                    </tr>
                    </c:forEach>
                  </tbody>
                        
            </table>

            <div id="page"></div>
        </div>
        <br /><br /><br />
      
    </body>
</html>