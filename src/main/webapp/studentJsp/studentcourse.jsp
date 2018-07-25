<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'studentcourse.jsp' starting page</title>

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
.jb{
    position: absolute;
    left:150px;
}
</style>
</head>

<body style="background:url(images/56.jpg) no-repeat">
<h1 style="position: absolute;left:900px;top:30px;">个    人    课    表</h1><br/><br/><br/><br/><br/>
<br/><br/>>
	<div class="row">
		<div class="col-md-1"></div>
		<div class="col-md-10 jb">
			<table class="table table-hover">
				<tr>
					<th>课程名称</th>
					<th>任课教师</th>
					<th>上课时间</th>
					<th>课程起始周</th>
					<th>课程学分</th>
					<th>总学时</th>
				</tr>
			    <c:forEach var="c" items="${co }">
			      <tr>
			         <td>${c.course.coursename}</td>
			         <td>${c.username}</td>
			         <td>${c.classtime }</td>
			         <td>${c.classweek }</td>
			         <td>${c.course.credit}</td>
			         <td>${c.course.coursetime}</td>
			      </tr>
			    </c:forEach>
			</table>
		</div>
		<div class="col-md-1"></div>
		</div>
</body>
</html>
