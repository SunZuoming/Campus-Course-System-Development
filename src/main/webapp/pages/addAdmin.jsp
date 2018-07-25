<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
    <!--<meta name="description" content="">-->
    <meta name="author" content="templatemo">
    <title></title>
    <script src="${basePath }/srcAdmin/js/jquery-3.2.1.js" type="text/javascript"></script>
    <script src="${basePath }/srcAdmin/js/bootstrap.min.js" type="text/javascript"></script>
    <link href="${basePath }/srcAdmin/css/bootstrap.min.css" rel="stylesheet" />
    <link href="${basePath }/srcAdmin/css/bootstrap-theme.min.css" rel="stylesheet" />
    <link href="${basePath }/srcAdmin/css/font-awesome.min.css" rel="stylesheet">
    <link href="${basePath }/srcAdmin/css/templatemo-style.css" rel="stylesheet">
    <script type="text/javascript">
        var submitFlag = true ;
        //验证用户名
        $(function () {
            $("#name").blur(function () {
                if($(this).val() == "" || $(this).val() == null){
                    $("#nameErr").html("用户名不能为空");
                    $(this).focus();
                    submitFlag = false;
                }else{
                	$("#nameErr").html("");
                    submitFlag = true;
                }
            });
        })
        //验证手机号码
        $(function () {
            $("#phone").blur(function () {
                var reg = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$";
                if($(this).val() == "" || $(this).val() == null){
                    $("#phoneErr").html("用户手机号不能为空");
                    $(this).focus();
                    submitFlag = false;
                }else if (reg.test($(this).val()) == false){
                    $("#phoneErr").html("手机号码格式不正确");
                    $(this).focus();
                    submitFlag = false;
                }else{
                	$("#phoneErr").html("");
                    submitFlag = true;
                }
            });
        })
        //验证邮箱
        $(function () {
            $("#email").blur(function () {
                var rege = "/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/";
                if($(this).val() == "" || $(this).val() == null){
                    $("#eamilErr").html("邮箱不能为空");
                    $(this).focus();
                    submitFlag = false;
                }else if (rege.test($(this).val()) == false){
                    $("#eamilErr").html("邮箱格式不正确");
                    $(this).focus();
                    submitFlag = false;
                }else{
                	$("#eamilErr").html("");
                    submitFlag = true;
                }
            });
        })
        function comSubmit(){
        	if(submitFlag == true){
        		$("#addAdminForm").submit();
        	}
        } 
        /* function showMsg() {
			if(${message} != "" || ${message} != null){
				alert(${message});
			}
		} */
    </script>
</head>
<body class="light-gray-bg" >
    <div class="container">
        <div class="container col-md-12 col-lg-12">
            <div class="col-md-12 col-lg-12 nopadding" style="margin-top: 10px;">
                <div class="col-md-2 col-lg-2 no-padding"></div>
                <div class="col-md-10 col-lg-10 nopadding" >
                    <div class="tab-content col-lg-12 col-md-12 nopadding" style="margin-top: 5px;">
                        <div class="tab-pane col-lg-12 col-md-12 active nopadding" id="fxsxstsq" style="margin-bottom: 0px;">
                            <form action="${basePath }pages/admin/addAdmin.do" method="post" id="addAdminForm">
                                <div class="col-lg-12 col-md-12 nopadding" style="margin-top: 10px">
                                    <p><h4>添加管理员</h4></p>
                                </div>
                                <div class="col-lg-12 col-md-12 nopadding">
                                    <hr>
                                </div>
                                <div class="col-lg-12 col-md-12">
                                    <div class="col-lg-3 col-md-3 no-padding" >
                                        <p style="float: right">账号:</p>
                                    </div>
                                    <div class="col-lg-9 col-md-9 no-padding" >
                                        <p><input id="userNo" name="userNo" style="background-color: #cccccc" value="${userNo }" type="text" readonly="readonly"></p>
                                    </div>
                                </div>
                                <div class="col-lg-12 col-md-12">
                                    <div class="col-lg-3 col-md-3">
                                        <p style="float: right">用户名：</p>
                                    </div>
                                    <div class="col-lg-9 col-md-9 nopadding">
                                        <p>
                                            <input type="text" placeholder="用户名" name="name" id="name">
                                            <label class="label label-danger" id="nameErr" ></label>
                                        </p>
                                    </div>
                                </div>

                                <div class="col-lg-12 col-md-12">
                                    <div class="col-lg-3 col-md-3">
                                        <p style="float: right">密码:</p>
                                    </div>
                                    <div class="col-lg-9 col-md-9 nopadding">
                                        <p><input type="text" value="1234abcd" style="background-color: #cccccc" readonly="readonly" id="password" name="password"></p>
                                    </div>
                                </div>

                                <div class="col-lg-12 col-md-12">
                                    <div class="col-lg-3 col-md-3">
                                        <p style="float: right">电话：</p>
                                    </div>
                                    <div class="col-lg-9 col-md-9 nopadding">
                                        <p>
                                            <input type="tel" placeholder="管理员电话" name="phone" id="phone"/>
                                            <label class="label label-danger" id="phoneErr" ></label>
                                        </p>
                                    </div>
                                </div>

                                <div class="col-lg-12 col-md-12">
                                    <div class="col-lg-3 col-md-3">
                                        <p style="float: right">邮箱：</p>
                                    </div>
                                    <div class="col-lg-9 col-md-9 nopadding">
                                        <p>
                                            <input type="email" placeholder="邮箱" id="email" name="email"/>
                                            <label class="label label-danger" id="eamilErr" ></label>
                                        </p>
                                    </div>
                                </div>
                                <div class="col-lg-12 col-md-12">
                                    <div class="col-lg-3 col-md-3">

                                    </div>
                                    <div class="col-lg-7 col-md-7 ">
                                        <p>
                                            <button class="btn-success" onclick="comSubmit()" style="border-radius: 3px">确定</button>&nbsp;&nbsp;&nbsp;&nbsp;
                                            <input class="btn-danger" type="reset" value="取消">
                                        </p>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-lg-12 col-md-12 nopadding">
                    <div class="col-lg-12 nopadding" style="margin-top: 5px;">
                        <div class="col-md-12 nopadding">
                            <hr style="background-color: #ce8483;border: none;height: 2px">
                            <h4 style="color: #3c763d">/*</h4>
                            <h4 style="color: #3c763d"> *  管理员职责重大，请谨慎添加</h4>
                            <h4 style="color: #3c763d"> */</h4>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>