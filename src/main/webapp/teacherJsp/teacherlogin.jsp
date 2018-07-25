<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<style type="text/css">
body {
	background: url(${pageContext.request.contextPath }/css/53.jpg) 
     no-repeat ;
	
}

.form {
	background: rgba(255, 255, 255, 0.2);
	width: 29.3%;
	margin: 100px auto;
}

#login_form {
	display: block;
}

#register_form {
	display: none;
}

.fa {
	display: inline-block;
	top: 10px;
	left: 43%;
	position: relative;
	color: #ccc;
}

input[type="text"], input[type="password"] {
	padding-left: 26px;
}

.checkbox {
	padding-left: 21px;
}
</style>
<head>
<base href="<%=basePath%>">

<title>欢迎登陆</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery-1.2.6.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/jquery-1.8.0.js"></script>
<link rel="stylesheet" href="css/styles.css">
<link rel="stylesheet" href="css/style.css">
<script src="js/pikaday.js"></script>
<link rel="stylesheet" href="css/pikaday.css">
<link rel="stylesheet" href="css/site.css">
<script src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
</head>

<body>
	<%
String id="";
String password="";
Cookie[] cookies = request.getCookies();
					if (cookies != null && cookies.length > 0) {
						for (Cookie c : cookies) {
							if (c.getName().equals("userNo")) {
								id=c.getValue();
							}
							if (c.getName().equals("password")) {
								password=c.getValue();
							}
						}
					}
			
 %>


	<div class="row">
		<div class="col-md-2">
			
		</div>
		<div class="col-md-6">
			<div class="container">
				<div class="col-md-3"></div>
				<div class="form row col-md-6">
					<form class="form-horizontal col-sm-offset-3 col-md-offset-3"
						id="login_form" action="${basePath }userMainPage/login.do" method="post">
						<input type="hidden" name="msg" value="login">
						<h3 class="form-title">欢迎登陆</h3>
						<div class="col-sm-9 col-md-9">
							<div class="form-group">
								<i class="fa fa-user fa-lg"></i>帐户名： <input onkeyup="funClick()"
									class="form-control required" type="text" name="userNo" id="id"
									maxlength="25" placeholder="请输入账号" value="<%=id %>" required
									style="padding-left:10px;margin-bottom:10px;"> 
									<span id="uid"style="font-size:20px;font-family:宋体；"> </span>
							</div>
							<div class="form-group">
								<i class="fa fa-lock fa-lg"></i>密 码： <input
									class="form-control required" type="password" name="userPassword"
									id="password" maxlength="15" placeholder="密码"
									value="<%=password %>" required style="padding-left:10px;margin-bottom:10px;" />
								
							</div>
							<div>
								<input type="text" name="identify" id="identify" maxlength="4"
									placeholder="验证码" required
									style="padding-left:10px;width:100px;height:30px;position:relative;top:0px;left:-15px;" />
							</div>
							<div style="position:relative;top:-65px;left:100px;">
								<img id="imgObj" src="${basePath }teacher/yzm.do" width="90" height="30"
									onclick="this.src=this.src+'?'" style="cursor:hand;" />
									
							</div>
							<div
								style="position:relative;top:-90px;left:200px;font-size:6px; color:gray;font-family:宋体">
								<label>点击图片更换</label>
                                 
								<div class="checkbox" style="position:relative;top:10px;left:-230px;color:#000000;">
									 <labe style="font-size: 12px;"><a href="${basePath }teacherJsp/searchpassword.jsp">忘记密码</a></label>

								</div>

								<div style="position:relative;top:-10px;left:-200px;">
									<hr />

									<div class="form-group">
										<input type="submit"  class="btn btn-success " value="登录 " />
										<a href="teacherJsp/tearegister.jsp" class="btn btn-success pull-right">注册</a>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="col-md-3"></div>
			</div>

		</div>
		<div id="gray"></div>


		<script type="text/javascript">
			//窗口效果
			//点击登录class为tc 显示
			$(".tc").click(function() {
				$("#gray").show();
				$("#popup").show();//查找ID为popup的DIV show()显示#gray
				tc_center();
			});
			//点击关闭按钮
			$("a.guanbi").click(function() {
				$("#gray").hide();
				$("#popup").hide();//查找ID为popup的DIV hide()隐藏
			})

			//窗口水平居中
			$(window).resize(function() {
				tc_center();
			});

			function tc_center() {
				var _top = ($(window).height() - $(".popup").height()) / 2;
				var _left = ($(window).width() - $(".popup").width()) / 2;

				$(".popup").css({
					top : _top,
					left : _left
				});
			}
		</script>

		<script type="text/javascript">
			$(document).ready(function() {

				$(".top_nav").mousedown(function(e) {
					$(this).css("cursor", "move");//改变鼠标指针的形状 
					var offset = $(this).offset();//DIV在页面的位置 
					var x = e.pageX - offset.left;//获得鼠标指针离DIV元素左边界的距离 
					var y = e.pageY - offset.top;//获得鼠标指针离DIV元素上边界的距离 
					$(document).bind("mousemove", function(ev) { //绑定鼠标的移动事件，因为光标在DIV元素外面也要有效果，所以要用doucment的事件，而不用DIV元素的事件 

						$(".popup").stop();//加上这个之后 

						var _x = ev.pageX - x;//获得X轴方向移动的值 
						var _y = ev.pageY - y;//获得Y轴方向移动的值 

						$(".popup").animate({
							left : _x + "px",
							top : _y + "px"
						}, 10);
					});

				});

				$(document).mouseup(function() {
					$(".popup").css("cursor", "default");
					$(this).unbind("mousemove");
				});
			})
			
		</script>
		
		<%
//out.clear();
//out = pageContext.pushBody();
%>
</body>
</html>