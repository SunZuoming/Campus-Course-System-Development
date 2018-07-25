<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>提示</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <style type="text/css">
#ly {
	margin: auto;
	position: relative;
	padding: 0;
	display: inline;
	text-algin: center;
	position: relative;
	left: 200px;
	top: 100px;
	font-size: 50px;
	font-family: 宋体;
}

#lyd {
	margin: auto;
	position: relative;
	padding: 0;
	display: inline;
	text-algin: center;
	position: relative;
	left: 100px;
	top: 300px;
	font-size: 30px;
	font-family: 宋体;
}


</style>

<body>
	<a href="${basePath}userMainPage/getFirstPage.do" style="text-decoration:none"><font color="blue" size="4">回到首页</font></a>
	<div>
		<h1 id="ly">
			提示：${cour}<br /> <em id="lyd">
		</h1>
		<a href="${basePath}course/CourseAll.do?num=1" style="text-decoration:none"><font color="blue" size="4">确定</font></a>
	</div>
</body>
</html>
