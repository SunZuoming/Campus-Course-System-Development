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

<title>个人作业</title>

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
.pp1 {
	position: relative;
	top: 9.3%;
}
.pg{
   position: absolute;
   left: 30%;
   top:30px;
}
.pq1{
   position: absolute;
   left: 90%;
}
.tt11{
     position:absolute;
     left:90%;
     top:1.9%;
}
.l2{
 color:#8E388E
}
.l3{
 color:#FFA500
}
.l4{
 color:#009ACD
}
.l5{
 color:#FF4500
}
</style>
</head>

<body>
	<!-- Single button -->
	<ul class="nav nav-tabs pg">
		<li role="presentation" class="active"><a href="${basePath}student/getStudentWork.do">查看作业</a></li>
		<li role="presentation"><a href="${basePath}student/studenLook.do">已提交的作业</a></li>
		<li role="presentation"><a href="${basePath}student/listFile.do">课件下载</a></li>
		<li role="presentation"><a href="${basePath}userMainPage/getFirstPage.do">首页</a></li>
	</ul>
	<a href="teacherJsp/updataimage.jsp"><img width="50" height="50"  
 src="${im.imagedress}" class="tt11 img-circle"></a>
	<hr class="pp1">
	<br /><br /><br />
	  <div class="jumbotron">
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-9 jb">
			<table class="table table-hover">
			    <c:forEach var="w" items="${work }">
			      <tr>
			      <lable class="l2">课程名称：  ${w.homeworkcourse}</lable><br/>
			      <lable class="l3">作业发布时间：    ${w.homeworkpublishtime}</lable><br/>
			     <lable class="l4">作业内容：     ${w.homeworkcontent }</lable><br/>
			    <lable class="l5"> 提交截止时间：     ${w.homeworkcommittime}</lable>
			         <a href="student/up.do?homeworkno=${w.homeworkno}" class="pq1">提交作业</a>
			    <br/>
			         <hr style=" height:1px;border:none;border-top:1px solid #00C5CD;">
			      </tr>
			    </c:forEach>
			</table>
		</div>
		<div class="col-md-1"></div>
	</div>
</body>

</html>
