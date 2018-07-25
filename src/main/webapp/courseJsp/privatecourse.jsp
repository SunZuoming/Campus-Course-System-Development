<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="./css/bootstrap.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="./js/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="./js/bootstrap.min.js"></script>
<style type="text/css">
		.pri_course_list ul 
		{
			width: 100%;
			overflow: hidden;
			padding-top: 20px;
		}
		.cf
		{
			list-style: none;
			margin-bottom: 0px;
		}
		.cf li 
		{
		    box-sizing: border-box;
		    margin-right: 20px;
		    margin-bottom: 30px;
		    width: 180px;
		    height: 150px;
		    float: left;
		    border-radius: 0 0 2px 2px;
		}
		.btn
		{
			width: 82px;
		}
		.btn-success:hover,.btn-success:focus
		{
			background-color: #5cb85c;
			border-color: #4cae4c;
		}
		.btn-success:active
		{
			background-color: #5cb85c;
			border-color: #4cae4c;
		}

</style>
</head>
<body>
<a href="${basePath}userMainPage/getFirstPage.do" style="text-decoration:none"><font color="blue" size="4">回到首页</font></a>
<div style = "width: 60%; margin: 0 auto" class="main">
		<h3>我的视频课程</h3>
		<div class="table-responsive" >
			<table class="table table-striped">
				<thead>
					<tr>
						<td align="center">课程名称</td>
					</tr>
				</thead>
				<c:forEach var="psee" items="${psee}">
					<tr>
						<!-- <td align="center" valign="center">${psee.coname}</td> -->
						<td align="center" valign="center">${psee.coname}</td>
				<td align="right">
		         	<button type="button" class="btn btn-primary"
		         	onclick="window.location.href = '${bashpath}course/Work.do?id=${psee.id}'">观看</button>
		         </td>
		         <td align="right">
		        <button type="button" class="btn btn-danger" onclick="window.location.href = '${bashpath}course/deleteWork.do?id=${psee.id}'">取消关注</button>
		         </td>
		         
					</tr>
					
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>