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

<title>提问</title>
</head>
<body class="easyui-layout" style='overflow-y:auto;'>
    <div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<nav class="navbar navbar-default navbar-static-top">
  				<div class="container" style="margin-top:10px;">
  					<a href="<%=basePath %>userMainPage/userMainPageController.do">首页</a>
  					<a href="<%=basePath %>userMainPage/getAcadExcCenterPage.do">主页</a>
  				</div>
			</nav>
			<div style="margin-left:20px;">
				<span style="font-size:20px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;问题:</span>
				<div style="margin-left:86px;margin-top:-24px;">
					<input type="text" id="qname" value="" style="width:100%;height:30px;" placeholder="最多输入30个字" onchange="checkField(this)">
				</div><br>
				<span style="font-size:20px;">问题说明:</span><br>
				<div style="margin-left:86px;">
					<textarea id="qconcant" style="width:100%;height:300px;"></textarea><br>
				</div>
				<span style="font-size:20px;">问题类型</span><br>
				<div style="margin-left:86px;">
					${questiontypeHtmlStr }
				</div>
				<div style="float:right;margin-right:14px;">
					<button type="button" class="btn btn-default" onclick="adQuestion()">提问</button>
    				<button type="button" class="btn btn-default" onclick="cancel()">取消</button>
				</div>
			</div>
		</div>
		<div class="col-md-2"></div>
	</div>
</body>  
<script type="text/javascript">
	
	// 提问按钮
	function adQuestion(){
		var username = "<%=session.getAttribute("username")%>"; // 评论人
		var userno = "<%=session.getAttribute("userno")%>"; // 评论人
		if(userno == "null" || userno == ""){
			alert("请先登录");
			return;
		}
		// 问题
		var qname = document.getElementById("qname").value;
		if(qname == ""){
			alert("请输入问题");
			return;
		}
		// 问题说明
		var qconcant = document.getElementById("qconcant").value;
		// 问题类型
		var qtypes = document.getElementsByName("qtype");
		var qtype = "";
		var qtypename = "";
		for(var i = 0,len = qtypes.length; i < len; i++){
			if(qtypes[i].checked){
				var val = qtypes[i].value;
				var index = val.lastIndexOf("_");
				qtype = val.substring(0,index);
				qtypename = val.substring(index + 1);
				continue;
			}
		}
		if(qtype == ""){
			alert("请选择问题类型");
			return;
		}
		// 根据文章类型查询该类型下所有文章
    	var param = {"username":username,"userno":userno,"qname":qname,"qconcant":qconcant,"qtype":qtype,"qtypename":qtypename};
		$.ajax({
			type:"post",
			url:"<%=basePath%>userMainPage/insertQuestion.do",
			data:param,
			datatype:"json",
			success:function(data){
				data = eval('(' + data + ')');
				var sign = confirm(data.msg + ",是否返回主页?");
				if(sign){
					window.location.href = "<%=basePath %>userMainPage/getAcadExcCenterPage.do";
				}
			},
			error:function(data){
				alert("请求失败!");
			}
		});
	}

	// 问题输入框改变值
	function checkField(obj){
		var val = obj.value;
		var len = val.length;
		if(len > 20){
			obj.value = val.substr(0,30);
		}
	}
	
	// 取消按钮
	function cancel(){
		window.location.href = "<%=basePath %>userMainPage/getAcadExcCenterPage.do";
	}
</script>
</html>
