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
    
    <title>学生作业</title>
    
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
.pg{
   position: absolute;
   left:300px;
   top:30px;
}
.jp{
    position: absolute;
    left:650px;
    
}
.jp1{
     color:#228B22;
}
.jp2{
    position: absolute;
    left:610px;
}
.pp1{
    position: relative;
    top:60px;
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
.l1{
     color:#009ACD
}
.l2{
 color:#FF7F24
}
</style>
  </head>
  
  <body>
    
	<!-- Single button -->
	<ul class="nav nav-tabs pg">
		<li role="presentation"><a href="${basePath}teacher/loadCourse.do">发布作业</a></li>
		<li role="presentation"><a href="${basePath}teacher/teacherwork.do">已发布作业</a></li>
		<li role="presentation" class="active"><a href="${basePath}teacher/studentupwork.do">批改作业</a></li>
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
			    <c:forEach var="w" items="${work}">
			      <tr>
			      <lable class="l1"> 提交者：  ${w.homeworkcommiter}</lable><br/>
			      <lable class="l2">提交时间：   ${w.commithomeworktime}</lable>
			         <a href="teacher/studentWorkbyno.do?homeworkcommitno=${w.homeworkcommitno}" class="pq1" >批改作业</a>
			    <br/>
			         <hr style=" height:1px;border:none;border-top:1px solid #2F4F4F;">
			      </tr>
			    </c:forEach>
			</table>
		</div>
		<div class="col-md-1"></div>
	  </div>
</div>
</body>
</html>
