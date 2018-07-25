<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	session.setAttribute("basePath",basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--<meta name="description" content="">-->
    <meta name="author" content="templatemo">
    <title>课程通后台管理系统</title>
    <script src="${basePath }/srcAdmin/js/jquery-3.2.1.js" type="text/javascript"></script>
    <script src="${basePath }/srcAdmin/js/bootstrap.min.js" type="text/javascript"></script>
    <link href="${basePath }/srcAdmin/css/bootstrap.min.css" rel="stylesheet" />
    <link href="${basePath }/srcAdmin/css/bootstrap-theme.min.css" rel="stylesheet" />
    <link href="${basePath }/srcAdmin/css/font-awesome.min.css" rel="stylesheet">
    <link href="${basePath }/srcAdmin/css/templatemo-style.css" rel="stylesheet">

	<script type="text/javascript">
		//登录请求		 
		function checkForm(){
			var userNo = document.getElementById("userNo");
			var userPassword = document.getElementById("userPassword");
			var form1 = document.getElementById("form1");
			if(userNo.value == ""){
				alert(document.getElementById("input_userno").value);
				userNo.focus();
				return false;
			}
			if(userPassword.value == ""){
				alert(document.getElementById("input_psw").value);
				userPassword.focus();
				return false;
			}
			allowFlag = true;
			form1.submit();
			return true;
		}
	
		function Clear(){
			document.getElementById("userNo").value = '';
			document.getElementById("userPassword").value = '';
			document.form1.account.focus();
		}
			
		window.onload = function () {
			$("#userNo").focus();
		}
	</script>
</head>
<body class="light-gray-bg">
<div class="templatemo-content-widget templatemo-login-widget white-bg">
    <header class="text-center">
        <div class="square"></div>
        <h1>登录</h1>
    </header>
    <form action="${basePath }pages/admin/adminlogin.do" id="form1" method="post" class="templatemo-login-form">
        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon"><i class="fa fa-user fa-fw"></i></div>
                <input type="text" class="form-control" name="userNo" id="userNo" placeholder="账号">
            </div>
        </div>
        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon"><i class="fa fa-key fa-fw"></i></div>
                <input type="password" name="userPassword" id="userPassword" class="form-control" placeholder="******">
            </div>
        </div>
        <div class="form-group" >
            <div class="checkbox squaredTwo">
                <p class="alert-dismissible" style="color: red; text-align: center;">
                	<c:if test="${!empty message}">
						<c:out value="${message}"/>
					</c:if>
                </p>
            </div>
        </div>
        <div class="form-group" style="text-align: center;">
            <button type="button" class="templatemo-blue-button" onclick="checkForm()">登录</button>&nbsp;&nbsp;&nbsp;
            <button type="button" class="templatemo-blue-button" onclick="Clear()">取消</button>
        </div>
    </form>
</div>

<div class="templatemo-content-widget templatemo-login-widget templatemo-register-widget white-bg">
    <p>@版权所有  重庆交通大学毕设小组</p>
</div>
<div id="javascriptI18n">
	<input type="hidden" id="input_psw" value="请输入密码"/>
	<input type="hidden" id="input_userno" value="请输入账号"/>
</div>
</body>
</html>