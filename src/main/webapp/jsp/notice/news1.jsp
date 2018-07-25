<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	session.setAttribute("basePath",basePath);
%>
<script type="text/javascript">
	function submit() {
		var msg="<%=session.getAttribute("msg")%>";
		var status="<%=session.getAttribute("status")%>";
		//alert(status);
		console.log(status);
		if (status == '1'){
			alert(msg);
			return false;
		}else{
			alert('11111111');
			return true;
		}
	}
</script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="utf-8">
        <title>
            公告管理
        </title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="format-detection" content="telephone=no">
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
          <%--  <form action = "${pageContext.request.contextPath}/notice/getNotice.do" method="get" layui-form x-center" action="" style="width:500px">
                <div class="layui-form-pane" style="margin-top: 15px;">
                  <div class="layui-form-item">
                    <div class="layui-input-inline" style="width:400px">
                      <input type="text" name="username"  placeholder="搜索内容" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-input-inline" style="width:80px">
                        <button type="submit"  onclick="" class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
                    </div>
                  </div>
                </div> 
            </form> --%> 
            <xblock>
            <a href="${pageContext.request.contextPath}/jsp/notice/New.jsp" class="layui-btn" style="margin-top: 6%"><i class="layui-icon">&#xe608;</i>添加</a>
            <!-- <button class="layui-btn layui-btn-danger" onclick="BatchOperate()"><i class="layui-icon">&#xe640;</i>批量删除</button> -->
            <%-- <button class="layui-btn" ><a href="${pageContext.request.contextPath}/notice/loadadd.do" "><i class="layui-icon">&#xe608;</i>添加</a></button> --%>
            <!-- <button class="layui-btn" onclick="role_management_edit('编辑','role_management_edit.html','900','500')"><i class="layui-icon">&#xe642;</i>编辑</button> -->
           </xblock>
            <table class="layui-table" style="margin-top: 0%">
                <thead>
                    <tr>
                        <th width="8%">
                            <input type="checkbox" name="" value="">
                        </th>
                        <th width="9%">
                           	公告发起者
                        </th>
                        <th width="30%">
                           	公告内容
                        </th>
                        <th width="11%">
                           	 开始时间
                        </th>
                        <th width="11%">
                        	  结束时间
                        </th>
                         <th width="11%">
                         	状态	
                        </th>
                         <th width="20%">
                           	 操作
                        </th>
                    </tr>
                </thead>
               <tbody>
                <span class="x-right" style="margin-left: 80%">共有数据：${count }条</span>
                <c:forEach items="${notice}" var="s">
                    <tr>
                        <td>
                            <input type="checkbox" value="${s.noticeid} " name="deleteCheckBox"  >
                        </td>
                        <td >
                           ${s.noticepublisher.userName } 
                        </td>
                        <td>${s.notcecontent}</td>
				        <td>${s.noticestartdate}</td>
				        <td>${s.noticeenddate }</td>
				        <td>
				        	<c:if test="${s.noticeUpdateFlag == '0' }">未公布，可修改</c:if>
				        	<c:if test="${s.noticeUpdateFlag == '1' }">公布中，不可修改</c:if>
				        	<c:if test="${s.noticeUpdateFlag == '2' }">已结束，不可修改</c:if>
				        	<c:if test="${s.noticeUpdateFlag == '3' }">已结束，不可修改</c:if>
				        </td>
                        <td class="td-manage">
                            <a title="查看" type="button" href="${pageContext.request.contextPath}/notice/getNoticeById.do?id=${s.noticeid}" class="ml-5" style="text-decoration:none">
                                <i class="layui-icon"><img src="${basePath}images/look3.png" width="15" height="15"></i>
                            </a>&nbsp;&nbsp;&nbsp;
                            <c:if test="${s.noticeUpdateFlag == '2' || s.noticeUpdateFlag == '3' }">
                            	<a title="删除" type="button" href="${pageContext.request.contextPath}/notice/deleteNotice.do?id=${s.noticeid}" onclick="comdel()";
                            		style="text-decoration:none" >
                                	<i class="layui-icon">&#xe640;</i>
                            	</a>
                            </c:if>
                            <c:if test="${s.noticeUpdateFlag == '0' || s.noticeUpdateFlag == '1' }">
                            	<a title="提前结束公告" type="button" href="${pageContext.request.contextPath}/notice/endNotice.do?id=${s.noticeid}" 
                            		style="text-decoration:none" >
                                	<i class="layui-icon"><img src="${basePath}images/quanxian.png" width="15" height="15"></i>
                            	</a>
                            </c:if>
                        </td>
                    </tr>
                    </c:forEach>
                  </tbody>
                        
            </table>

            <div id="page"></div>
        </div>
        <script>
        function BatchOperate(){
        	//批量删除数据  
            var checkBoxArray=[]; 
             $("input[name='deleteCheckBox']:checked").each(function () { 
                 checkBoxArray.push(this.value) 
             }); 
             $.ajax({ 
                 type : 'post', 
                 url : '${pageContext.request.contextPath }/notice/deleteM.do', 
                 traditional : true,//注意，必须要有个设置否则传递数组报400错误。默认为false深度序列化，在此改为true 
                 data : { 
                     "array" : checkBoxArray 
                 }, 
                 success : function(data) {//返回json结果 
                     console.log(data)//插入成功打印数字 1  
                     alert("批量删除成功"); 
                 }, 
                 error : function() { 
                     alert("查询失败"); 
                 } 
             }); 
        }
        </script>
    </body>
</html>
