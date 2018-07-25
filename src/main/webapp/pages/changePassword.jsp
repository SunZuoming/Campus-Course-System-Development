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
	<base href="<%= basePath %>">
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="author" content="templatemo">
	<title>修改密码</title>
    <script src="${basePath }/srcAdmin/js/jquery-3.2.1.js" type="text/javascript"></script>
    <script src="${basePath }/srcAdmin/js/bootstrap.min.js" type="text/javascript"></script>
    <link href="${basePath }/srcAdmin/css/bootstrap.min.css" rel="stylesheet" />
    <link href="${basePath }/srcAdmin/css/bootstrap-theme.min.css" rel="stylesheet" />
    <link href="${basePath }/srcAdmin/css/font-awesome.min.css" rel="stylesheet">
    <link href="${basePath }/srcAdmin/css/templatemo-style.css" rel="stylesheet">
    <script type="text/javascript">
        var submitFlag = true;
        /*原密码判断*/
        $(function () {
            $("#oldPassword").blur(function () {
                if($(this).val() == null || $(this).val() == ""){
                    $("#oldPasswordEer").html($("#input_old_psw").val());
                    $("#oldPassword").focus();
                    submitFlag=false;
                } else {
                    $.ajax({
                    	type:"post",
                        url:"${basePath}pages/admin/ifn.do",
                        data:{oldPassword : $("#oldPassword").val()},
                        success:function(data) {
                        	data = eval('(' + data + ')');
                            if(data.retCode == '0000'){
                            	$("#oldPasswordEer").html(data.retMsg);
                                submitFlag = true;
                            }else if(data.retCode == '9999'){
                            	$("#oldPasswordEer").html(data.retMsg);
                            	setTimeout(function(){
                            		window.location = "${basePath}pages/adminLogin.jsp";
                            	},3000);
                            }else{
                                submitFlag = false;
                                $("#oldPasswordEer").html(data.retMsg);
                                $("#oldPassword").focus();
                                $("#oldPassword").val("");
                            }
                        },
                        error:function(data){
                        	$("#oldPasswordEer").html("请求发送失败，请确认原密码后进行提交");
                        },
                        datatype:"json"
                    });
                }
            });
        })
        /*新密码判断*/
        $(function () {

                $("#newPassord").blur(function () {
                    var reg=/^(?!([a-zA-Z]+|\d+)$)[a-zA-Z\d]{6,}$/;
                    if($(this).val() =="" || $(this).val() == null){
                        $("#newPasswordEer").html($("#input_new_psw").val());
                        $("#newPassord").focus();
                        submitFlag = false;
                    }else if($(this).val() == $("#oldPassword").val()){
                        $("#newPasswordEer").html($("#same_psw").val());
                        $(this).focus();
                        $(this).val("");
                        submitFlag = false;
                    }else {
                        if(reg.test($(this).val()) == false){
                            $("#newPasswordEer").html($("#error_new_psw").val());
                            $("#newPassord").focus();
                            $(this).val("");
                            submitFlag = false;
                        } else {
                        	$("#newPasswordEer").html("");
                            submitFlag = true;
                        }
                    }
                });

        })
        /*确认密码判断*/
        $(function () {

                $("#comPassord").blur(function () {
                    if($(this).val() == "" || $(this).val()==null){
                        $("#comPasswordEer").html($("#input_com_psw").val());
                        $("#comPassord").focus();
                        submitFlag = false;
                    }else if($(this).val() != $("#newPassord").val()){
                        $("#comPasswordEer").html($("#different_psw").val());
                        $("#comPassord").focus();
                        $(this).val("");
                        submitFlag =false;
                    }else{
                    	$("#comPasswordEer").html();
                        submitFlag = true;
                    }
                });

        })
        /*form表单提交*/
        function confrimSubmit() {
            if(submitFlag == true){
                $("#form2").submit();
            }
        }
        
        function cancel(){
        	submitFlag = true;
        	$("#oldPassword").val("");
        	$("#newPassord").val("");
        	$("#comPassord").val("");
        	$("#oldPasswordEer").val("");
        	$("#newPasswordEer").val("");
        	$("#comPasswordEer").val("");
        }
        
        function back(){
        	var flag = '${flag}';
        	if(flag == '1'){
        		window.location = "${basePath}pages/adminLogin.jsp";
        	}else{
        		/* window.location = "${basePath}pages/admin/showMainData.do"; */
        		window.history.back();
        	}
        	
        }
    </script>
</head>
<body class="light-gray-bg">
    <div class="templatemo-flex-row col-lg-5 col-lg-offset-3 flex-content-row">
        <div class="col-1">
            <div class="panel panel-default margin-10">
                <div class="panel-heading">
                	<h2 class="text-center" style="color: #46b8da">
                		<c:if test="${flag=='1' }">第一次登录，修改密码</c:if>
                		<c:if test="${flag!='1' }">修改密码</c:if>
                	</h2>
                </div>
                <div class="panel-body">
                    <form action="${basePath}pages/admin/changePassword.do" id="form2" class="templatemo-login-form" method="post">
                    	<input type="hidden" value="${flag }" name="flag" id="flag">
                        <div class="form-group">
                            <p id="userNo" class="text-left">账号:${adminSession.userNo }</p>
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" id="oldPassword" name="oldPassword" placeholder="原密码"/>
                            <label id="oldPasswordEer" class="label label-danger"></label>
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" id="newPassord" name="newPassword" maxlength="12" placeholder="6-12位数字和字母 新密码"/>
                            <label id="newPasswordEer" class="label label-danger"></label>
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" id="comPassord" name="comPassword" maxlength="12" placeholder="确认密码" />
                            <label id="comPasswordEer" class="label label-danger"></label>
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
                        <div class="form-group" style="text-align: center">
                            <button type="button" onclick="confrimSubmit()" class="templatemo-blue-button">确定</button>&nbsp;&nbsp;
                            <button type="button" onclick="cancel()" class="templatemo-blue-button">取消</button>&nbsp;&nbsp;
                            <button type="button" onclick="back()" class="templatemo-blue-button">返回</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div name="javascriptI18n">
        <input type="hidden" id="different_psw" value="确认密码与新密码不同"/>
        <input type="hidden" id="input_old_psw" value="请输入原密码"/>
        <input type="hidden" id="input_new_psw" value="请输入新密码"/>
        <input type="hidden" id="error_new_psw" value="新密码格式不正确"/>
        <input type="hidden" id="input_com_psw" value="请输入确认密码"/>
        <input type="hidden" id="psw_longer_16" value="密码长度大于12"/>
        <input type="hidden" id="error_old_psw" value="原密码输入错误" />
        <input type="hidden" id="same_psw" value="新密码与原密码相同">
    </div>
</body>
</html>