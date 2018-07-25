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
    
    <title>课件下载</title>
    
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
   left: 900px;
}
.tt11{
     position:absolute;
     left:90%;
     top:1.9%;
}
.l1{
 color:#218868
}
.l2{
 color:#8E388E
}
.l3{
 color:#FFA500
}
</style>
  </head>
  
  <body>
    <!-- Single button -->
	<ul class="nav nav-tabs pg">
		<li role="presentation"><a href="${basePath}student/getStudentWork.do">查看作业</a></li>
		<li role="presentation"><a href="${basePath}student/studenLook.do">已提交的作业</a></li>
		<li role="presentation" class="active"><a href="${basePath}student/listFile.do">课件下载</a></li>
		<li role="presentation"><a href="${basePath}userMainPage/getFirstPage.do">首页</a></li>
	</ul>
	<a href="teacherJsp/updataimage.jsp"><img width="50" height="50"  
 src="${im.imagedress}" class="tt11 img-circle"></a>
	<hr class="pp1">
	<br/><br /><br />
	<div class="jumbotron">
	<div class="row">
     <div class="col-md-2"></div>
      <div class="col-md-8">
     <!-- 遍历Map集合 --> 
     <table class="table table-hover">
     <tr class="l1">
     <th>课程</th>
     <th>文件</th>
     <th>操作</th>
     </tr> 
     
     <c:forEach var="f" items="${file}">

        <tr>
        <c:url value="${bashpath}student/downFile.do" var="downurl">  
            <c:param name="filename" value="${f.filedress}"></c:param>  
        </c:url>
        <td class="l2">${f.coursename}</td>
        <td class="l3">${f.filedress}</td>
        <td><a href="${downurl}">下载</a></td>
     </tr>  
    </c:forEach> 
    </table>
    </div>
     <div class="col-md-2"></div> 
     </div>
     </div>
  </body>
</html>
