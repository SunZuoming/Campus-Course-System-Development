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
        <link rel="stylesheet" href="${basePath }/layui/css/layui.css" media="all">
        <%-- <link rel="stylesheet" href="${basePath }/css1/x-admin.css" media="all"> --%>
         <script type="application/javascript" src="${basePath }/layui/layui.js"></script>
      <script type="text/javascript"  
    src="${pageContext.request.contextPath}/js1/jquery-1.9.1.min.js"></script>  
    <script type="text/javascript">
    	function S(){
    		var s=document.getElementByName("flag");
    		var svar;
    		for(var i=0;i<s.length;i++){
    			
    			if(s.item(i).checked){
    				svar=s.item(i).getAttribute("value");
    				alert(svar);
    				break;
    			}else{
    				continue;
    			}
    		}
    		
    	}
    </script>
    </head>
    <body>
        <div class="x-nav">
            <span class="layui-breadcrumb">
              <a><cite>首页</cite></a>
              <a><cite>课程审核管理</cite></a>
            </span>
            <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"  href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i></a>
        </div>
        <div class="x-body">
            <%-- <form action = "${pageContext.request.contextPath}/teachercourse/list.do" method="get" layui-form x-center" action="" style="width:1200px">
                <div class="layui-form-pane" style="margin-top: 15px;">
                  <div class="layui-form-item">
                    <div class="layui-input-inline" style="width:1000px">
                      <input type="text" name="username"  placeholder="搜索内容" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-input-inline" style="width:80px">
                        <button type="submit"  onclick="BatchOperate()" class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
                    </div>
                  </div>
                </div> 
             </form> --%>
            <xblock>         
            </xblock>
            <table class="layui-table">
                <thead>
                    <tr>
                        <th width="8%">
                            <input type="checkbox" name="" value="">
                        </th>
                        <th width="9%">
                              教师编号                
                        </th>
                        <th width="16%">
                              教师姓名                
                        </th>
                        <th width="22%">
                              课程名称           </th>
                        <th width="22%">
                           课程审核标志</th>
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
                            <input type="checkbox" value="${s.teacherno } " name="deleteCheckBox" >
                        </td>
                        <td type="hidden" id="flag">${s.id }</td>
                        <td id="record">
                           ${s.teachername} 
                        </td>
                         <td>
                            ${s.coursename}</td>
                        <td>
                           ${s.coursefalg}</td>
                        
                        <td class="td-manage">
                           <a href="${pageContext.request.contextPath}/teachercourse/getMessage.do?id=${s.id}" onclick="submit()">详情</a>                               
                        </td>
                    </tr>
                    </c:forEach>
                  </tbody>
                        
            </table>
        
   
            <div id="page"></div>
        </div>
        <br /><br /><br />
        <script>
        function ajaxfunction(value){
        	$.ajax({
        		url:'${pageContext.request.contextPath}/teachercourse/update.do',
        		data:{"value":value},
        		type:'post',
        		success:function(){
        			
        		}
        	})
        }
        </script>
    </body>
</html>