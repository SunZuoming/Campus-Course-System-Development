<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>学生个人中心</title>

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
.tt11{
     position:absolute;
     left:90%;
     top:25px;
}
.tt13{
    position:absolute;
    left:1150px;
    top:15px;
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
<a onclick="quit()" role="button" class="tt13">退出登录</a>
<a href="teacherJsp/updataimage.jsp"><img width="50" height="50"  
 src="${im.imagedress}" class="tt11 img-circle"></a>
<br/>
<hr><br/>
 <div class="row">
 
  <div class="col-md-2">
  <ul class="list-group">
  <li class="list-group-item jp2"><a href="${basePath}student/getUserto.do">基本信息</a></li>
  <li class="list-group-item jp2"><a href="studentJsp/supdatepassword.jsp">修改密码</a></li>
  <li class="list-group-item jp2"><a href=" ${basePath}userMainPage/getFirstPage.do">个人首页</a></li>
  <li class="list-group-item jp2">账号绑定</li>
  <li class="list-group-item jp2">收货地址</li>
  </ul>
  </div>
  <div class="col-md-6">
  <h3 class="jb">基本信息</h3><br/>
  <form class="form-horizontal" action="${basePath}student/updateUser.do" method="post">
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">账号</label>
    <label name="userNo" class="jp3">${user.userNo}</label>
  </div>
  <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">名称</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="inputPassword3" name="userName"
       value="${user.userName}">
    </div>
  </div>
  <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">电话</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="inputPassword3" name="userPhone"
      value="${user.userPhone}" maxlength="11">
    </div>
  </div>
  <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">邮箱</label>
    <div class="col-sm-10">
    <input type="email" class="form-control" id="inputPassword3" name="email"
      value="${user.email}">
      <!-- <label name="email" class="jp4">${user.email}</label> -->
    </div>
  </div><br/>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default" 
      class="form-control" id="inputEmail3">保存修改</button>
    </div>
  </div>
</form>
</div>
<div class="col-md-3"></div>
</div>
</body>
</html>
