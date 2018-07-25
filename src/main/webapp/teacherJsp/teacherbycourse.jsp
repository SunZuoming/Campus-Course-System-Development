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

<title>个人中心</title>

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
<script src="./js/bootstrap.min.js)"></script>

<style type="text/css">
.jb{
   color:#000000;
}
.jp1{
    margin-left: 2000px;
	font-size: 50px;
	color: #3366FF;
}
.jp2{
text-align:center;
}
.jp3{
   position: relative;
   left:20px;
   top:6px;
}
.jp4{
   position: relative;
   left:5px;
   top:6px;
}
.tt10{
   width:50px; highth:100px;
}
.tt11{
     position:absolute;
     left:90%; 
     top:3.9%;
}
.tt13{
    position:absolute;
    left:84.2%;
    top:2%;
}
.lo1{
  position:absolute;
  left:10%;
   
}
</style>

<script type="text/javascript" language="javaScript">
		function quit() {

			if (confirm("确定要退出登陆吗？")) {

				window.location.href = "teacher/zxdl.do";

			}

		}
	</script>
</head>
<body style="background:url(css/40.jpg) no-repeat scroll transparent;background-size:100% 100%">
<h1 style="color:#4682B4;position: relative;top:10px;">个   人   中   心</h1>
<!-- <a onclick="quit()" role="button" class="tt13">退出登录</a> -->
 <a href="teacherJsp/updataimage.jsp"><img width="50" height="50"  
 src="${im.imagedress}" class="tt11 img-circle"></a>
<br/>
<hr><br/>
 <div class="row">
 <!-- <div class="col-md-1"></div> -->
  <div class="col-md-2">
  <ul class="list-group">
  <li class="list-group-item jp2"><a href="${basePath}teacher/getUserto.do">基本信息</a></li>
  <li class="list-group-item jp2"><a href="teacher/updatep.do">修改密码</a></li>
  <li class="list-group-item jp2"><a href="${basePath}userMainPage/getFirstPage.do">个人首页</a></li>
  <c:if test="${user.roleId=='JS002'}">
  <li class="list-group-item jp2" ><a href="${basePath}teacher/loadCreateCourse.do?num=1 ">创建课程</a></li>
  </c:if>
  <c:if test="${user.roleId=='JS002'}">
  <li class="list-group-item jp2" ><a href="${basePath}teacher/loadCoursebyuser.do ">课程管理</a></li>
  </c:if>
  </ul>
  </div>
  <div class="col-md-6">
  <h3 class="jb">课程管理</h3><br/>
    <div class="table-responsive" >
			<table class="table table-hover lo1">
				<thead>
					<tr>
						<td align="center">课程名称</td>
						<td align="center"></td>
					</tr>
				</thead>
				<c:forEach var="co1" items="${co1}">
				<tr>
					<td align="center" valign="center">${co1.coursename}</td>
					<td align="right">
		         	<button type="button" class="btn btn-warning" onclick="window.location.href = '${bashpath}teacher/deletecourseby.do?id=${co1.id}'">取消</button>
		         	</td>
				</tr>
				
				</c:forEach>
			</table>
		</div>
</div>
<div class="col-md-3"></div>
</div>
</body>
</html>
