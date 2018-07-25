<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>欢迎注册</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery-1.2.6.min.js"></script>
<link href="css/style.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="css/bootstrap.min.css">
<style type="text/css">
#lyd {
	font-family: 宋体;
	font-size: 18px;
}

#yll {
	margin: auto;
	position: relative;
	padding: 0;
	display: inline;
	text-algin: center;
	position: relative;
	left: 15%;
	top: 1.5%;
}

#ylll {
	margin: auto;
	position: relative;
	padding: 0;
	display: inline;
	text-algin: center;
	position: relative;
	left: 51.2%;
	top: 13%;
	line-height: 60px;
	height: 35px;
	font-family: 宋体;
	font-size: 20px;
	text-align: center;
}
.pg{
   position: relative;
   left:16px;
}
</style>

<script type="text/javascript"> 
		   //验证密码是否成功
		   
		   function validate() {
       var pwd1 = document.getElementById("psw1").value;
       var pwd2 = document.getElementById("psw2").value;
       var cod=pwd1.length;
       if(cod<6){
    	   document.getElementById("upsw1").innerHTML="<font color='green'>最少输入6位密码</font>";
    	   document.getElementById("button").disabled = true;
    	   document.getElementById("button1").disabled = true;
       }else if(pwd1 == pwd2)
        {
        document.getElementById("upsw2").innerHTML="<font color='green'>两次密码相同</font>";
        document.getElementById("button").disabled = false;
 	    document.getElementById("button1").disabled = false;
        }else {
        document.getElementById("upsw2").innerHTML="<font color='red'>两次密码不相同</font>";
        document.getElementById("button").disabled = true;
        document.getElementById("button1").disabled = true;
        }
       
       
   }
		  
		   
	function isTel() {
			var valid =/^1[34578]\d{9}$/;
			var tel = document.getElementById("userPhone");
			var phone = tel.value.trim();
			if (valid.test(phone)==false) {
				document.getElementById("userPhone1").innerHTML="<font color='red'>请输入正确格式的手机号 </font>";
			    document.getElementById("button").disabled = true;
			    document.getElementById("button1").disabled = true;
			    return false;
			}else if (valid.test(phone)){
				document.getElementById("userPhone1").innerHTML="<font color='green'>手机号格式正确  </font>";
				document.getElementById("button").disabled =  false;
			    document.getElementById("button1").disabled =  false;
                 return true;
			} 
			
		}
	
	function isemail() {
		var valid =/^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;
		var tel = document.getElementById("email");
		var phone = tel.value.trim();
		if (valid.test(phone)==false) {
			document.getElementById("uemail").innerHTML="<font color='red'>请输入正确格式的邮箱 </font>";
		    document.getElementById("button").disabled = true;
		    document.getElementById("button1").disabled = true;
		    return false;
		}else if (valid.test(phone)){
			document.getElementById("uemail").innerHTML="<font color='green'>邮箱格式正确  </font>";
			document.getElementById("button").disabled =  false;
		    document.getElementById("button1").disabled =  false;
             return true;
		} 
		
	}

	

</script>
</head>
<body style="background:url(css/66.jpg) no-repeat scroll transparent;background-size:100% 100%" >
	
	<script>
		today = new Date();

		var hours = today.getHours();

		var minutes = today.getMinutes();
		var seconds = today.getSeconds();

		var timeValue = "<FONT COLOR=black>"
				+ ((hours > 12) ? hours - 12 : hours);
		timeValue += ((minutes < 10) ? "<BLINK><FONT COLOR=black>:</FONT></BLINK>0"
				: "<BLINK><FONT COLOR=black>:</FONT></BLINK>")
				+ minutes + "</FONT></FONT>";
		timeValue += (hours >= 12) ? "<FONT COLOR=black>pm</FONT>"
				: "<FONT COLOR=black>am</FONT>";
		function initArray() {

			this.length = initArray.arguments.length
			for (var i = 0; i < this.length; i++)
				this[i + 1] = initArray.arguments[i]
		}
		var d = new initArray("<font color=black>星期日", "<font color=black>星期一",
				"<font color=black>星期二", "<font color=black>星期三",
				"<font color=black>星期四", "<font color=black>星期五",
				"<font color=black>星期六");
		var year = today.getYear();
		year = (year < 1900 ? (1900 + year) : year);
		document.write("<font color=black>", year, "<font color=black>年",
				"<font color=black>", today.getMonth() + 1,
				"<font color=black>月", "<font color=black>", today.getDate(),
				"<font color=black>日</FONT>", d[today.getDay() + 1], " ",
				timeValue);
	//-->
	</script>
	<br/>
	
	<div class="row">
		<div class="col-md-3"></div>
		<div class="col-md-6">

			<form id="lyd"  method="post" action="${basePath }teacher/register.do"
				class="form-horizontal" onsubmit="return scheck(this);" name="message">
				<h1
					style="position: relative;left: 19.8%;top: 2.6%;font-size:50px;line-height: 100px;color:gray ">
					<em>欢迎注册</em>
				</h1>
				
				<div class="form-group">
					<label class="col-sm-2 control-label" >姓 名:</label>
					<div class="col-sm-10">
						<input type="text" id="userName" name="userName" class="form-control"
							placeholder="请填入姓名"  required><span
							></span>
					</div>
				</div>
				<div class="form-group">
					<label for="phone" class="col-sm-2 control-label" >电 话:</label>
					<div class="col-sm-10">
						<input type="text" id="userPhone" name="userPhone" class="form-control"
					onkeyup="isTel()"	  placeholder="电话(11位)" maxlength="11" required>
					<span id="userPhone1"></span>
					</div>
				</div>
				<div class="form-group">
					<label for="email" class="col-sm-2 control-label"> 邮 箱:</label>
					<div class="col-sm-10">
						<input type="email" id="email" name="email" class="form-control"
					 onkeyup="isemail()"   placeholder="该邮箱将作为追回密码重要依据，请认真填写！" maxlength="20" required><span
							id="uemail"></span>
					</div>
				</div>
				<div class="form-group">
					<label for="p1" class="col-sm-2 control-label"> 密 码:</label>
					<div class="col-sm-10">
						<input type="password" id="psw1" name="userPassword1" class="form-control"
							placeholder="密码" onkeyup="validate()" maxlength="16" required><span id="upsw1"></span>
					</div>
				</div>
				<div class="form-group">
					<label for="p2" class="col-sm-2 control-label"> 确认密码:</label>
					<div class="col-sm-10">
						<input type="password" id="psw2" name="userPassword" class="form-control"
							placeholder="确认密码" maxlength="16" onkeyup="validate()">
							<span id="upsw2" ></span>
					</div>
				</div>
				
			<div class="form-group">
				 <label for="phone" class="col-sm-2 control-label" >身份选择:</label>
				 <div class="btn-group pg" role="group" aria-label="...">
                 <button type="submit" id="button"  class="btn btn-default" value="js" name="js" >教师注册</button>
                 <button type="submit" id="button1" class="btn btn-default" value="xues" name="" >学生注册</button>
                </div>
             </div>
                
				<!--  <div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<input type="submit" value="注册" id="button" 
						style="position: relative;left: 160px;font-size:20px;line-height: 20px; width:200px;">
					</div>
				</div>
				--> 
          
			</form>
		</div>
		<div class="col-md-3"></div>
	</div>
	
	
</div>
	
	
</body>
</html>
