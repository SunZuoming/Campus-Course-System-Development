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
	<video width="320" height="240" controls>
	<source src="D:/用户目录/我的文档/Tencent Files/1732145192/Video/D13309B3DDA33B6D3C4C7AE1ACB763A2.mp4" type="video/mp4">
	
	</video>
</body>
</html>
