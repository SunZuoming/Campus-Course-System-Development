<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>提交作业</title>
    
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
	max-height: 300px;
	border: 5px #ebebeb solid;
	height: 260px;
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

.pg{
   position: absolute;
   left: 30%;
   top:30px;
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
	left: 610px;
}

.pp1 {
	position: relative;
	top: 9.3%;
}
.pg2{
    position: absolute;
    top:108%;
	left: 15px;
}
.tt11{
     position:absolute;
     left:90%;
     top:1.9%;
}
.l5{
   height:520px;
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
			var fixedLength = 1000;//固定长度
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
			var fixedLength = 1000;//固定长度
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

	function Myfile() {

		var a = confirm("确定要上传文件吗？");
		if (a == false) {
			window.location.href = "${bashpath}teacher/uploadFile.do";
			return false;
		} else {
			document.getElementById("file2").innerHTML = "文件正在传输.....";
			return true;
		}
	}
</script>

</head>
  
  <body>
 <!-- Single button -->
	<ul class="nav nav-tabs pg">
		<li role="presentation"><a href="${basePath}student/getStudentWork.do">查看作业</a></li>
		<li role="presentation"><a href="${basePath}student/studenLook.do">已提交的作业</a></li>
		<li role="presentation"><a href="${basePath}student/listFile.do">课件下载</a></li>
		<li role="presentation"><a href="${basePath}userMainPage/getFirstPage.do">首页</a></li>
	</ul>
	<a href="teacherJsp/updataimage.jsp"><img width="50" height="50"  
 src="${im.imagedress}" class="tt11 img-circle"></a>
	<hr class="pp1">
	<br/><br/><br/>
	<div class="jumbotron l5">
	<div class="row">
    <div class="col-md-2"></div>
    <div class="col-md-9">
	<form class="form-inline" action="${basePath}student/upStudentWork.do" method="post">
				<div>
					<textarea class="text-area text-area-input" name="homeworktxtcontent"
						id="notedetial" required></textarea>
					<div class="text-area-input-length">
						您还可输入<span>1000</span>个字
					</div>
				</div>
				<input type="submit" class="btn btn-success pg2" value="提交" />
	</form>
	
	<form class="form-inline" action="${basePath}student/upStudentfile.do" method="post"
	class="file1" enctype="multipart/form-data">
				<input class="btn btn-default" type="file" name="file"
				style="display: inline;" /> <input class="btn btn-default"
				OnClick="return Myfile()" style="display: inline;" type="submit"
				value="上 传文件" />
			    <p id="file2" class="jp1">${file1}</p>
	</form>
     </div>
		<div class="col-md-1"></div>
	</div>
  </div>
  </body>
  </html>
