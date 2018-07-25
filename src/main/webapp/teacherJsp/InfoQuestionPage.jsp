<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引入主题样式 -->
<link
	href="${pageContext.request.contextPath }/themes/bootstrap/easyui.css"
	rel="stylesheet">
<!-- 引入图标的样式 -->
<link href="${pageContext.request.contextPath }/themes/icon.css"
	rel="stylesheet">
<!-- 先引入jquery -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.10.2.min.js"></script>
<!-- 引入easyui.js -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui-lang-zh_CN.js"></script>
<!-- Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css">

<!-- Bootstrap 核心 JavaScript 文件 -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<title>学术交流中心</title>
</head>
<body class="easyui-layout" style="overflow-y:auto;">   
	<div class="row">
		<div class="col-md-1"></div>
		<div class="col-md-10">
			<nav class="navbar navbar-default navbar-static-top">
  				<ul class="nav navbar-nav navbar-right" style='margin-right:20px;'>
  					<li><a onclick='hisPage()'>返回</a></li>
  				</ul>
			</nav>
			<img alt="" src="${pageContext.request.contextPath }/image/headImage.png" height="90" width="100%"><br><br>
			<div style="text-align:center;font-size:20px;font-weight: bold;">
				<a href='<%=basePath%>userMainPage/getQuestionPage.do?questionno=${questionno}'>${questionname}</a>
			</div><br>
			<div style="text-align:center;font-size:13px;">
				提问者:<a>${questionpublisher}</a>&nbsp;&nbsp;&nbsp;时间:${questiontime}&nbsp;&nbsp;&nbsp;评论:&nbsp;<span id='queCommnum'>${questioncommentnum}</span>
			</div><br>
			<div style="background-color:#EDEDED;">
				${questioncontent}
			</div>
			<div id="commentDiv" style='width:100%;'>
				<br>
				<input type="text" class="form-control" placeholder="评论" onfocus='commentClick(this)' aria-describedby="basic-addon1">
			</div><br>
			评论<br><div id="answerDiv">
				${answerHtmlStr}
			</div>
		</div>
		<div class="col-md-1"></div>
	</div>
</body>  
<script type="text/javascript">
	
	// 回复
	function replay(obj){// 评论id
		var objname = obj.name;
		var index1 = objname.lastIndexOf("_");
		var str = objname.substr(0,index1);
		var index2 = str.lastIndexOf("_");
		var answerno = str.substr(0,index2);
		var replyDiv = document.getElementById(answerno);
		replyDiv.innerHTML = "<br><div id='replay_" + answerno + "' contenteditable='true' style='height:100px;border:1px solid;background-color:white;overflow-y:auto;'></div>" + 
		"<div style='float:right;'><a name='" + objname + "' onclick='replaySave(this.name)'>回复</a>&nbsp;&nbsp;<a name='" + answerno + "' onclick='cancelReplay(this.name)'>取消</a></div>";
	}
	
	// 回复保存
	function replaySave(objname){
		var userno = "<%=session.getAttribute("userno")%>"; // 回复人编号
		if(userno == "null"){
			alert("请先登录");
			return;
		}
		var username = "<%=session.getAttribute("username")%>"; // 回复人姓名
		// 问题编号
		var questionno = "${questionno }"; 
		if(questionno == null || questionno == ""){
			alert("该问题已失效");
			return;
		}
		var index1 = objname.lastIndexOf("_");
		var replayPname = objname.substr(parseInt(index1) + 1);// 要回复人姓名
		var str = objname.substr(0,index1);
		var index2 = str.lastIndexOf("_");
		var replayPno = str.substr(parseInt(index2) + 1);// 要回复人编号
		var answerno = str.substr(0,index2); // 评论编号
		var replayconcant = document.getElementById("replay_" + answerno).innerHTML; // 回复内容
		if(replayconcant == ""){
			alert("请输入回复内容");
			return;
		}
		// 参数
		var param = {"questionno":questionno,"userno":userno,"username":username,"replayPno":replayPno,"replayPname":replayPname,"answerno":answerno,"replayconcant":replayconcant};
		$.ajax({
			type:"post",
			url:"<%=basePath%>userMainPage/saveReplay.do",
			data:param,
			datatype:"json",
			success:function(data){
				data = eval('(' + data + ')');
				// 问题评论
				var answerHtmlStr = data.answerHtmlStr;
				document.getElementById("answerDiv").innerHTML = answerHtmlStr;
				// 取消回复
				cancelReplay(answerno);
			}
		});
	}
	
	// 取消回复
	function cancelReplay(answerno){
		document.getElementById(answerno).innerHTML = "";
	}
	
	// 评论
	function commentSave(){
		var userno = "<%=session.getAttribute("userno")%>"; // 评论人编号
		if(userno == "null"){
			alert("请先登录");
			return;
		}
		var username = "<%=session.getAttribute("username")%>"; // 评论人名称
		// 问题编号
		var questionno = "${questionno }"; 
		if(questionno == null || questionno == ""){
			alert("该问题已失效");
			return;
		}
		// 评论内容
		var commentConcent = document.getElementById("commentTxt").innerHTML;
		if(commentConcent == ""){
			alert("请填写评论内容");
			return;
		}
		// 参数
		var param = {"userno":userno,"username":username,"questionno":questionno,"commentConcent":commentConcent};
		$.ajax({
			type:"post",
			url:"<%=basePath%>userMainPage/commentQuestion.do",
			data:param,
			datatype:"json",
			success:function(data){
				data = eval('(' + data + ')');
				// 问题评论
				var answerHtmlStr = data.answerHtmlStr;
				document.getElementById("answerDiv").innerHTML = answerHtmlStr;
				// 取消评论
				cancelcomment();
				document.getElementById("queCommnum").innerHTML = data.questioncommentnum;
				var msg = data.msg;
				alert(msg);
			}
		});
	}

	// 评论输入框获取焦点
	function commentClick(obj){
		var commentDiv = document.getElementById("commentDiv");
		commentDiv.removeChild(obj);
		commentDiv.innerHTML = "<br><div id='commentTxt' contenteditable='true' style='height:100px;border:1px solid;background-color:white;overflow-y:auto;'></div>" + 
		"<div style='float:right;'><a onclick='commentSave()'>发表</a>&nbsp;&nbsp;<a onclick='cancelcomment()'>取消</a></div>";
		document.getElementById("commentTxt").focus();
	}
	
	// 取消评论
	function cancelcomment(){
		var commentDiv = document.getElementById("commentDiv");
		commentDiv.innerHTML = "<br><input type='text' class='form-control' placeholder='回复' onfocus='commentClick(this)' aria-describedby='basic-addon1'>";
	}
	
	// 上一页
	function hisPage(){
		history.go(-1);
	}
</script>
</html>
