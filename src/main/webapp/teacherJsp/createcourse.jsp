<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
session.setAttribute("basePath",basePath);
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>课程选择</title>
    
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
<script src="${basePath }/srcAdmin/js/jquery-3.2.1.js" type="text/javascript"></script>
    <script src="${basePath }/srcAdmin/js/bootstrap.js" type="text/javascript"></script>
    <script src="${basePath }/srcAdmin/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${basePath }/srcAdmin/js/jqPaginator.js" type="text/javascript"></script>
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
.tt2{
    position:absolute;
    left:100px;
}
.tt1{
    width: 160px;
    height:30px;
}
.tt3{
   font-size: 20px;
}
.tt5{
    position:absolute;
    top:300px;
    left:16%;
    width: 120px;
}
.tt6{
    position:relative;
    top:230px;
    left:86%;
    height:1px;
}
.tt11{
     position:absolute;
     left:90%; 
     top:3.9%;
}
.tt16{
    position:absolute;
    top:90%;
   width:120px;
}
.call{
     position:absolute; 
     top:90%;
}
</style>

<script type="text/javascript">

function tl(){
	 var tll=document.getElementById("c.coursename");
	 window.location.href="你所要跳转的页面";
}
</script>
 </head>
  
  <body style="background:url(css/40.jpg) no-repeat scroll transparent;background-size:100% 100%">
<h1 style="color:#4682B4;position: relative;top:10px;">个   人   中   心</h1>
<a href="teacherJsp/updataimage.jsp"><img width="50" height="50"  
 src="${im.imagedress}" class="tt11 img-circle"></a>
<br/>
<hr><br/>
 <div class="row">

  <div class="col-md-2">
  <ul class="list-group">
  <li class="list-group-item jp2"><a href="${basePath}teacher/getUserto.do">基本信息</a></li>
  <li class="list-group-item jp2"><a href="teacher/updatep.do">修改密码</a></li>
  <li class="list-group-item jp2"><a href="${basePath}userMainPage/getFirstPage.do">个人首页</a></li>
  <c:if test="${user.roleId=='JS002'}">
  <li class="list-group-item jp2" ><a href="${basePath}teacher/loadCreateCourse.do?num=1">创建课程</a></li>
  </c:if>
  <c:if test="${user.roleId=='JS002'}">
  <li class="list-group-item jp2" ><a href="${basePath}teacher/loadCoursebyuser.do  ">课程管理</a></li>
  </c:if>
  </ul>
  </div>
  <div class="col-md-9">
  <h3 class="jb">创建课程</h3><br/><br/>
  <form id = "comTeaForm" action="" method="post">
      <div class="form-group tt2">
			<table class="table table-hover lo1">
				<thead>
					<tr>
						<th>课程</th>
					    <th>课程简介</th>
					</tr>
				</thead>
				<c:forEach var="c" items="${cou}">
				<tr>
					<td >${c.coursename}</td>
					<td >${c.courseinfo}</td>
					<td>
		         	<button type="button" class="btn btn-warning" onclick="window.location.href = '${bashpath}teacher/CreateCourse.do?courseinfono=${c.courseinfono}'">添加课程</button>
		         	</td>
				</tr>
				
				</c:forEach>
			</table>
		<br/>
			 <div id="pagesNum" class="text-center call">
                <ul id="pagination" class="pagination"></ul>
                <script type="text/javascript">
                    $.jqPaginator('#pagination', {
                        totalPages: ${totalPage},
                        visiblePages: 10,
                        currentPage: ${initPage},
                        prev: '<li class="prev"><a href="javascript:;">Previous</a></li>',
                        next: '<li class="next"><a href="javascript:;">Next</a></li>',
                        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
                        onPageChange: function (num, type) {
                            var comTeaForm = document.getElementById('comTeaForm');
                            if(type == 'change'){
                            	var url='${basePath}/teacher/loadCreateCourse.do?num='+num;
                            	comTeaForm.action = url;
                            	comTeaForm.submit();
                            }
                        }
                    });
                </script>
            </div>
			
	 </div><br/>
	</form>
	 <ul class="nav nav-pills pg">
	 <li role="presentation" class="active tt6"><a href="teacher/updatep1.do">自建课程</a></li>
	 </ul>

</div>
<div class="col-md-1"></div>
</div>
</body>
</html>

