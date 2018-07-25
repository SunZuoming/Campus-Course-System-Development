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
<title>个人消息中心</title>
</head>
<style type="text/css">
a,li{
	cursor: pointer;
}
</style>
<body class="easyui-layout" style="overflow-y:auto;background-color:#fafafa;">   
	<table id="tb" class="table table-hover">
		<tr>
			<td width='15%' align="center">发送人</td>
			<td width='50%' align="center">内容</td>
			<td width='10%' align="center">消息状态</td>
			<td width='15%' align="center">发送时间</td>
			<td width='10%' align="center">处理</td>
		</tr>${infomationHtmlStr }
	</table><br>
	<div style='width:100%;text-align:right;'>
		<input type="checkbox" id='chAll' onclick="checkboxAll(this)" />选中所有&nbsp;&nbsp;
		<button class="btn btn-default" type="button" onclick="markread()">标记已读</button>
		<button class="btn btn-default" type="button" onclick="deleteCb()">删除选中</button>&nbsp;&nbsp;&nbsp;&nbsp;
	</div>
</body>  
<script type="text/javascript">
	
	// 点击消息删除按钮
	function deleteAnswer(answerno){
		var userno = "<%=session.getAttribute("userno")%>"; // 用户编号
		if(userno == "null" || userno == ""){
			alert("请先登录");
			return;
		}
		var param = {"infonos":answerno,"userno":userno};
		$.ajax({
			type:"post",
			url:"<%=basePath%>userMainPage/deleteCb.do",
			data:param,
			datatype:"json",
			success:function(data){
				data = eval('(' + data + ')');
				var infomationHtmlStr = data.infomationHtmlStr;
				var htmlStr = "<tr><td width='15%' align='center'>发送人</td><td width='50%' align='center'>内容</td><td width='10%' align='center'>消息状态</td><td width='15%' align='center'>发送时间</td><td width='10%' align='center'>处理</td></tr>";
				document.getElementById("tb").innerHTML = (htmlStr + infomationHtmlStr).replace(",","");
				var cbs = document.getElementById("chAll").checked = false;
			}
		});
	}
	
	// 点击消息中的评论类型消息记录
	function infoAnswer(name){
		var index = name.lastIndexOf("_");
		var answerno = name.substring(0,index);
		var infono = name.substring(index+1);
		location.href = "<%=basePath%>userMainPage/infoAnswer.do?answerno=" + answerno + "&infono=" + infono;
	}
	
	// 点击消息中的回复类型消息记录
	function infoReplay(name){
		var index = name.lastIndexOf("_");
		var replayno = name.substring(0,index);
		var infono = name.substring(index+1);
		var userno = "<%=session.getAttribute("userno")%>"; // 用户编号
		if(userno == "null" || userno == ""){
			alert("请先登录");
			return;
		}
		location.href = "<%=basePath%>userMainPage/infoReplay.do?replayno=" + replayno + "&userno=" + userno + "&infono=" + infono;
	}
	
	// 标记已读
	function markread(){
		var userno = "<%=session.getAttribute("userno")%>"; // 用户编号
		if(userno == "null" || userno == ""){
			alert("请先登录");
			return;
		}
		var cbs = document.getElementsByName("cbInfo");
		var cbArray = new Array();
		for(var i = 0, len = cbs.length; i < len; i++){
			if(cbs[i].checked){
				cbArray.push(cbs[i].value);
			}
		}
		if(cbArray.length < 1){
			alert("至少选中一条消息");
			return;
		}
		var param = {"infonos":cbArray.join(","),"userno":userno};
		$.ajax({
			type:"post",
			url:"<%=basePath%>userMainPage/markread.do",
			data:param,
			datatype:"json",
			success:function(data){
				data = eval('(' + data + ')');
				var infomationHtmlStr = data.infomationHtmlStr;
				var htmlStr = "<tr><td width='15%' align='center'>发送人</td><td width='50%' align='center'>内容</td><td width='10%' align='center'>消息状态</td><td width='15%' align='center'>发送时间</td><td width='10%' align='center'>处理</td></tr>";
				document.getElementById("tb").innerHTML = (htmlStr + infomationHtmlStr).replace(",","");
				var cbs = document.getElementById("chAll").checked = false;
			}
		});
	}
	
	// 删除选中
	function deleteCb(){
		var userno = "<%=session.getAttribute("userno")%>"; // 用户编号
		if(userno == "null" || userno == ""){
			alert("请先登录");
			return;
		}
		var cbs = document.getElementsByName("cbInfo");
		var cbArray = new Array();
		for(var i = 0, len = cbs.length; i < len; i++){
			if(cbs[i].checked){
				cbArray.push(cbs[i].value);
			}
		}
		if(cbArray.length < 1){
			alert("至少选中一条消息");
			return;
		}
		var param = {"infonos":cbArray.join(","),"userno":userno};
		$.ajax({
			type:"post",
			url:"<%=basePath%>userMainPage/deleteCb.do",
			data:param,
			datatype:"json",
			success:function(data){
				data = eval('(' + data + ')');
				var infomationHtmlStr = data.infomationHtmlStr;
				var htmlStr = "<tr><td width='15%' align='center'>发送人</td><td width='50%' align='center'>内容</td><td width='10%' align='center'>消息状态</td><td width='15%' align='center'>发送时间</td><td width='10%' align='center'>处理</td></tr>";
				document.getElementById("tb").innerHTML = (htmlStr + infomationHtmlStr).replace(",","");
				var cbs = document.getElementById("chAll").checked = false;
			}
		});
	}
	
	// 选中所有
	function checkboxAll(obj){
		var cbs = document.getElementsByName("cbInfo");
		var checked = obj.checked;
		for(var i = 0, len = cbs.length; i < len; i++){
			cbs[i].checked = checked;
		}
	}
	
	// 消息点击checkbox
	function infoCbClick(){
		var chAll = document.getElementById("chAll");
		var cbs = document.getElementsByName("cbInfo");
		var count = 0;
		var len = cbs.length;
		for(var i = 0; i < len; i++){
			if(!cbs[i].checked){
				chAll.checked = false;
				return;
			}else {
				count = parseInt(count) + 1;
				if(count == len){
					chAll.checked = true;
				}
			}
		}
	}
</script>
</html>
