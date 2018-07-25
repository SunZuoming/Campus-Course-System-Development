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
    
    <title>批改作业</title>
    
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
.text-area {
	width: 1000px;
	max-width: 800px;
	max-height: 600px;
	border: 5px #ebebeb solid;
	height: 300px;
	overflow: hidden;
	padding: 5px 5px 5px 5px;
	color: #1A1A1A;
}

.text-area-input-length {
	font-size: 12px;
	line-height: 30px;
}

.text-area-input-length span {
	margin: 0px 5px 0px 5px;
	color: red;
}

.text-area-bottom {
	text-align: right;
	margin: 5px 0px 0px 0px;
	float: right;
	padding: 0px 0px 0px 0px;
}

.text-area-bottom a {
	border: #ebebeb 2px solid;
	padding: 10px 20px 10px 20px;
	text-decoration: none;
	color: #000000;
	font-size: 14px;
}

.text-area-star {
	overflow: hidden;
	text-align: center;
}

.text-area-star label {
	float: left;
	line-height: 35px;
	height: 35px;
	font-size: 12px;
	margin: 0px 10px 10px 0px;
	padding: 0px 5px 0px 5px;
	cursor: pointer;
	border: 1px solid #ebebeb;
}

.red {
	color: red;
	border: 1px solid red !important;
}

.text-area-star label input {
	filter: alpha(opacity = 0);
	-moz-opacity: 0;
	opacity: 0;
	position: absolute;
}

.text-area-star label span {
	padding: 10px 10px 10px 10px;
	position: relative;
}

.pg {
	position: absolute;
	left: 30%;
	top: 30px;
}

.jp {
	position: absolute;
	left: 650px;
}

.jp1 {
	color: #228B22;
}

.jp2 {
	position: absolute;
	left: 18%;
}

.pp1 {
	position: relative;
	top: 9.3%;
}
.tt11{
     position:absolute;
     left:90%;
     top:1.9%;
}
</style>
  </head>
  
  <body>
    <!-- Single button -->
	<ul class="nav nav-tabs pg">
		<li role="presentation"><a
			href="${basePath}teacher/loadCourse.do">发布作业</a></li>
		<li role="presentation"><a href="${basePath}teacher/teacherwork.do">已发布作业</a></li>
			<li role="presentation" class="active"><a href="${basePath}teacher/studentupwork.do">批改作业</a></li>
		<li role="presentation"><a href="${basePath}userMainPage/getFirstPage.do">首页</a></li>
	</ul>
	<a href="teacherJsp/updataimage.jsp"><img width="50" height="50"  
 src="${im.imagedress}" class="tt11 img-circle"></a>
	<hr class="pp1">
	<br /><br /><br />
	<div class="jumbotron">
	<form class="form-inline" action="${basePath}teacher/checkStudentWork.do"
		method="post">
		<div class="jp2">
			<div class="form-group">
				<label for="exampleInputName2">作业评分:</label> <input type="text"
					class="form-control" name="homeworkscorec">
			</div>
		</div>
		<br /> <br /> <br />
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-9">
				<div>
					<textarea class="text-area text-area-input" name="homeworktxtcontent"
						id="notedetial" required>${ho.homeworktxtcontent}</textarea>
					<div class="text-area-input-length">
					</div>
				</div><br/>
				<a href="${basePath}teacher/downFile.do" name="filename" value="filename">${ho.homeworkfileurl}</a><br/><br/>
				<input type="submit" class="btn btn-success " value="保存" />
			</div>
			<div class="col-md-1"></div>
		</div>
	</form> 
	</div>
  </body>
</html>
