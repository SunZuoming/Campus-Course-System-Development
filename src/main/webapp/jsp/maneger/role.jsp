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
        <script type="application/javascript" src="${basePath }/layui/layui.js"></script>
		<link rel="stylesheet" href="${basePath }/layui/css/layui.css">  
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
             <%-- <form action = "${pageContext.request.contextPath}/role/list.do" method="get" layui-form x-center" action="" style="width:500px">
                <div class="layui-form-pane" style="margin-top: 15px;">
                  <div class="layui-form-item">
                    <div class="layui-input-inline" style="width:400px">
                      <input type="text" name="username"  placeholder="搜索内容" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-input-inline" style="width:80px">
                        <button type="submit"  onclick="BatchOperate()" class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
                    </div>
                  </div>
                </div> 
            </form>  --%>
            <xblock>
            <button class="layui-btn layui-btn-danger" onclick="BatchOperate()"><i class="layui-icon">&#xe640;</i>批量删除</button>
            <!-- <button class="layui-btn" > --><a href="${pageContext.request.contextPath}/role/addquanxian.do" class="layui-btn" ><i class="layui-icon">&#xe608;</i>添加</a><!-- </button> -->
            <%-- <button class="layui-btn" ><a href="${basePath}/role/getuser.do" class="layui-btn" ><i class="layui-icon">&#xe608;</i>用户管理</a></button> --%>
        
            </xblock>
            <table class="layui-table">
                <thead>
                    <tr>
                        <th width="8%">
                            <input type="checkbox" name="" value="">
                        </th>
                        <th width="9%">
                            	功能编号
                        </th>
                        <th width="16%">
                            	功能名
                        </th>
                        <th width="22%">
                            	所属角色名称
                        </th>
                        <th width="11%">
                            	操作
                        </th>
                    </tr>
                </thead>
               <tbody>
               <span class="x-right" style="line-height:25px">共有数据：${count } 条</span>
                <c:forEach items="${list}" var="s">
                
                    <tr>
                        <td>
                            <input type="checkbox" value="${s.jurisdictionno } " name="deleteCheckBox" >
                        </td>
                        <td id="record">
                           ${s.jurisdictionno } 
                        </td>
                         <td>
                            ${s.functionName}</td>
                        <td>
                           ${s.roleName}
                        </td>
                        <td class="td-manage">
                            <%-- <a title="分配权限" href="javascript:;" onclick="role_management_permissions('分配权限','role_management_permissions.html','4','','510')"
                            class="ml-5" style="text-decoration:none">
                                <i class="layui-icon"><img src="${basePath}images/quanxian.png" width="15" height="15"></i>
                            </a>
                            <a title="查看" href="javascript:;" onclick="role_management_look('查看','role_management_look.html','4','','510')"
                            class="ml-5" style="text-decoration:none">
                                <i class="layui-icon"><img src="${basePath}images/look3.png" width="15" height="15"></i>
                            </a> --%>
                            <a title="删除" type="button" href="${pageContext.request.contextPath}/role/delete.do=${s.jurisdictionno}" onclick="submit()" 
                            style="text-decoration:none" >
                                <i class="layui-icon">&#xe640;</i>
                            </a>
                        </td>
                    </tr>
                    </c:forEach>
                  </tbody>
                        
            </table>

            <div id="page"></div>
        </div>
        <br /><br /><br />
        <script>
        function BatchOperate(){
        	//批量删除数据  
            var checkBoxArray=[]; 
             $("input[name='deleteCheckBox']:checked").each(function () { 
                 checkBoxArray.push(this.value) 
             }); 
             $.ajax({ 
                 type : 'post', 
                 url : '${pageContext.request.contextPath }/role/deleteMap.do', 
                 traditional : true,//注意，必须要有个设置否则传递数组报400错误。默认为false深度序列化，在此改为true 
                 data : { 
                     "array" : checkBoxArray 
                 }, 
                 success : function(data) {//返回json结果 
                     console.log(data)//插入成功打印数字 1  
                     alert("批量删除成功"); 
                 }

             }); 
        }
        </script>
    </body>
</html>