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
	font-size: 40px;
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
	font-size: 20px;
	font-family: 宋体;
}


</style>

<body style="background-image:url(images/52.jpg)">
	<script>
		today = new Date();

		var hours = today.getHours();

		var minutes = today.getMinutes();
		var seconds = today.getSeconds();

		var timeValue = "<FONT COLOR=black>"
				+ ((hours > 12) ? hours - 12 : hours);
		timeValue += ((minutes < 10) ? "<BLINK><FONT COLOR=black>:</FONT></BLINK>0"
				: "<BLINK><FONT COLOR=black>:</FONT></BLINK>")
				+ minutes + "</FONT></FONT>";
		timeValue += (hours >= 12) ? "<FONT COLOR=black>pm</FONT>"
				: "<FONT COLOR=black>am</FONT>";
		function initArray() {

			this.length = initArray.arguments.length
			for (var i = 0; i < this.length; i++)
				this[i + 1] = initArray.arguments[i]
		}
		var d = new initArray("<font color=black>星期日", "<font color=black>星期一",
				"<font color=black>星期二", "<font color=black>星期三",
				"<font color=black>星期四", "<font color=black>星期五",
				"<font color=black>星期六");
		var year = today.getYear();
		year = (year < 1900 ? (1900 + year) : year);
		document.write("<font color=black>", year, "<font color=black>年",
				"<font color=black>", today.getMonth() + 1,
				"<font color=black>月", "<font color=black>", today.getDate(),
				"<font color=black>日</FONT>", d[today.getDay() + 1], " ",
				timeValue);
	//-->
	</script>
	<br/>
	<a href="${basePath}userViewMain/userViewMainController.do" style="text-decoration:none"><font color="blue" size="4">回到首页</font></a>
	<div>
		<h1 id="ly">
			提示：${loginwo}<br/> <em id="lyd">
		</h1><br/><br/><br/>
		<h3><a href="teacherJsp/teacherlogin.jsp">请登录</a></h3>
		
	</div>
</body>
</html>
