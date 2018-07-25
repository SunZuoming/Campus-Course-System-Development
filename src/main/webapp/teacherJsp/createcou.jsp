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

<title>课程创建</title>

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
.jbb {
	color: #000000;
}

.jpp2 {
	text-align: center;
}

.tt2 {
	position: absolute;
	left: 7.3%;
}

.tt1 {
	width: 120px;
}

.tt3 {
	font-size: 20px;
}

.tt5 {
	position: absolute;
	top: 39.1%;
	left: 6.6%;
	width: 120px;
}

.tt6 {
	position: absolute;
	top: 23.7%;
	left: 36.6%;
	height: 1px;
}

.text-area {
	width: 660px;
	max-width: 800px;
	max-height: 300px;
	border: 5px #ebebeb solid;
	height: 230px;
	overflow: hidden;
	padding: 5px 5px 5px 5px;
	color: #999999;
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
	top: 3.9%;
}

.jp {
	position: absolute;
	left: 47.6%;
}

.jp1 {
	color: #228B22;
}

.jp2{
text-align:center;
}

.pp1 {
	position: relative;
	top: 7.8%;
}

.tt7 {
	position:relative;
	left: -8%;
}

.tt8{
	position:relative;
	left: -10%;
}
.tt9{
   position:relative;
   left: -26.1%;
}
.tt11{
     position:absolute;
     left:90%; 
     top:3.9%;
}
</style>

<script type="text/javascript">
	$(function() {
		$('.text-area-input').click(function() {
			if ($(this).val() == '请输入作业内容......') {
				$(this).css({
					color : '#000000'
				}).val('')
			}
		});
		//离开的时候
		$('.text-area-input').blur(function() {
			var iNum = $(this).val().length;
			var fixedLength = 500;//固定长度
			if (iNum < fixedLength) {
				$('.text-area-input-length span').html(fixedLength - iNum);
			} else {
				$(this).val($(this).val().substr(0, fixedLength - 1));//截取长度
				$('.text-area-input-length span').html(iNum - fixedLength);
			}
		});
		//按下的时候
		$('.text-area-input').keydown(function() {
			var iNum = $(this).val().length;
			var fixedLength = 500;//固定长度
			if (iNum < fixedLength) {
				$('.text-area-input-length span').html(fixedLength - iNum);
			} else {
				$(this).val($(this).val().substr(0, fixedLength - 1));//截取长度
				$('.text-area-input-length span').html(iNum - fixedLength);
			}
		});
		$('.text-area-bottom a').click(function() {
			var star = $('input[name=star]:checked').val();
			var content = $('textarea[name=content]').val();
			alert(content);
		});
		$('.text-area-star label').click(function() {
			var labelLength = $('.text-area-star label').length;
			for (var i = 0; i < labelLength; i++) {
				if (this == $('.text-area-star label').get(i)) {
					$('.text-area-star label').eq(i).addClass('red');
				} else {
					$('.text-area-star label').eq(i).removeClass('red');
				}
			}
		});
	});
</script>
</head>


<body style="background: url(css/40.jpg) no-repeat scroll transparent;background-size:100% 100%">
	<h1 style="color: #4682B4; position: relative; top: 10px;">个人 中 心</h1>
	<a href="teacherJsp/updataimage.jsp"><img width="50" height="50"  
 src="${im.imagedress}" class="tt11 img-circle"></a>
	<br />
	<hr>
	<br />
	<div class="row">

 <div class="col-md-2">
  <ul class="list-group">
  <li class="list-group-item jp2"><a href="${basePath}teacher/getUserto.do">基本信息</a></li>
  <li class="list-group-item jp2"><a href="teacher/updatep.do">修改密码</a></li>
  <li class="list-group-item jp2"><a href="${basePath}userMainPage/getFirstPage.do">个人首页</a></li>
  <c:if test="${user.roleId=='JS002'}">
  <li class="list-group-item jp2" ><a href="${basePath}teacher/loadCreateCourse.do?num=1 ">创建课程</a></li>
  </c:if>
  <c:if test="${user.roleId=='JS002'}">
  <li class="list-group-item jp2" ><a href="${basePath}teacher/loadCoursebyuser.do  ">课程管理</a></li>
  </c:if>
  </ul>
  </div>
		<div class="col-md-6">
			<h3 class="jbb">创建课程</h3>
			<br/>
			
			<form class="form-horizontal" action="${basePath}teacher/AddCourse.do"
				method="post">
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-3 control-label tt7">课程名称:</label>
					<div class="col-sm-6">
						<input type="text" class="form-control tt9" id="inputPassword3"
							name="coursename">
					</div>
				</div>

				<div>
				<label for="inputPassword3" class="col-sm-3 control-label tt8">课程介绍:</label>
					<textarea class="text-area text-area-input" name="courseinfo"
						id="notedetial" required></textarea>
					<div class="text-area-input-length">
						您还可输入<span>500</span>个字
					</div>
				</div>
				<input type="submit" class="btn btn-success " value="提交" />
			</form>
		</div>
		<div class="col-md-3"></div>
	</div>
</body>
</html>
