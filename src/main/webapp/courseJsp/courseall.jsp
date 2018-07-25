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
    
    <title></title>
    
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
<script src="./srcAdmin/js/jquery-3.2.1.js" type="text/javascript"></script>
    <script src="./srcAdmin/js/bootstrap.js" type="text/javascript"></script>
    <script src="./srcAdmin/js/bootstrap.min.js" type="text/javascript"></script>
<script src="./srcAdmin/js/jqPaginator.js" type="text/javascript"></script>

<style type="text/css">
		.pri_course_list ul 
		{
			width: 100%;
			overflow: hidden;
			padding-top: 20px;
		}
		.cf
		{
			list-style: none;
			margin-bottom: 0px;
		}
		.cf li 
		{
		    box-sizing: border-box;
		    margin-right: 20px;
		    margin-bottom: 30px;
		    width: 180px;
		    height: 150px;
		    float: left;
		    border-radius: 0 0 2px 2px;
		}
		.btn
		{
			width: 82px;
		}
		.btn-success:hover,.btn-success:focus
		{
			background-color: #5cb85c;
			border-color: #4cae4c;
		}
		.btn-success:active
		{
			background-color: #5cb85c;
			border-color: #4cae4c;
		}
		.yu{
		    
		}

	</style>

</head>
<body>

<div style = "width: 60%; margin: 0 auto" class="main">
	<h3>公共课程</h3>
	<form id = "comTeaForm" action="" method="post">
	<div class="table-responsive" >
	    <table class="table table-striped" style="width: 85%; margin:20 0 0 20">
			<thead>
			<tr>
				<td align="center">课程名称</td>
				<td align="center">教师姓名</td>
				<td align="center">课程介绍</td>
			<tr>
			</thead>
		    <c:forEach var="tc" items="${tc}">
		      <tr>
		         <td align="center" valign="center">${tc.coursename}</td>
		         <td align="center" valign="center">${tc.teachername}</td>
		          <td align="center" valign="center">${tc.courseinfo}</td>
		         <td align="right">
		         	
		         	<button type="button" class="btn btn-primary" onclick="window.location.href = '${bashpath}course/insertCourse.do?id=${tc.id}'"  target="_blank">关注课程</button>

		         </td>
		      </tr>
		      
		    </c:forEach>
		</table>
        </div>
        <br/>
        <div id="pagesNum" class="text-center call yu">
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
                            	var url='${basePath}/course/CourseAll.do?num='+num;
                            	comTeaForm.action = url;
                            	comTeaForm.submit();
                            }
                        }
                    });
                </script>
            </div>
        
	</form>
	<br/>
	<h3>已关注课程</h3>
	<div class="table-responsive" >
	    <table class="table table-striped" style="width: 85%; margin:20 0 0 20">
			<thead>
			<tr>
				<td align="center">课程名称</td>
				<td align="center">教师姓名</td>
			<tr>
			</thead>
		    <c:forEach var="wa" items="${wa}">
		      <tr>
		         <td align="center" valign="center">${wa.coursename}</td>
		         <td align="center" valign="center">${wa.username}</td>
		         <td align="right">
		         	<button type="button" class="btn btn-primary">已经关注</button>
		         </td>
		      </tr>
		    </c:forEach>
		</table>
	</div>
	
	
	<h3 style="margin-top: 80px; margin-bottom: 20px;">视频课程</h3>
	<div class="pri_course_list" style="margin:30 auto">
		<ul class="cf" >
			<li>
				<a href="${bashpath}course/pricourse.do?id=1">
				<div class="course_pic">
					<img src="images/pri_1_zuheshuxue.png" width="178" height="113" alt="组合数学">
					</div>
				<p class="course_name">组合数学</p>
				</a>
			</li>
			<li>
				<!-- <a href="${bashpath}course/pricourse.do?id=2"> -->
				<a href="${bashpath}course/pricourse.do?id=2">	
				<div class="course_pic">
					<img src="images/pri_2_shujuwajue.jpg" width="178" height="113" alt="数据挖掘：理论与算法">
					</div>
				<p class="course_name" >数据挖掘：理论与算法</p>
				</a>
			</li>
			<li>
				<a href="${bashpath}course/pricourse.do?id=3">
				<div class="course_pic">
					<img src="images/pri_3_caozuoxitong.jpg" width="178" height="113" alt="操作系统">
					</div>
				<p class="course_name">操作系统</p>
				</a>
			</li>
			<li>
				<a href="${bashpath}course/pricourse.do?id=4">
				<div class="course_pic">
					<img src="images/pri_4_shujujiegou.jpg" width="178" height="113" alt="数据结构(上)">
					</div>
				<p class="course_name">数据结构(上)</p>
				</a>
			</li>
			<li>
				<a href="${bashpath}course/pricourse.do?id=5">
				<div class="course_pic">
					<img src="images/pri_5_zhonghuachayichengxian.jpg" width="178" height="113" alt="中华茶艺呈现">
					</div>
				<p class="course_name">中华茶艺呈现</p>
				</a>
			</li>
		</ul>
	</div>
	</div>
	

</body>
</html>