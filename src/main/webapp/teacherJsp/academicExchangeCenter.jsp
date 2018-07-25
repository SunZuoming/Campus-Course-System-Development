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
<title>课程问题吧</title>
</head>
<body class="easyui-layout" style="overflow-y:auto;">   
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<nav class="navbar navbar-default navbar-static-top">
  				<div class="container" style="margin-top:10px;">
  					<a href='<%=basePath %>userMainPage/userMainPageController.do'>首页</a>
  					<!-- &nbsp;&nbsp;&nbsp;搜索&nbsp;<input class="easyui-textbox" data-options="iconCls:'icon-search'" style="width:200px;height:30px;">  -->
  				</div>
			</nav>
			<div class="row">
				<div class="col-md-2" style="overflow-y:auto;">
					<span>问题分类</span>
					${questionHtmlStr }
				</div>
				<div class="col-md-10">
					<div id="divColmd10">
						<%-- <img alt="" src="${pageContext.request.contextPath }/image/headImage.png" height="90" width="100%"><br><br> --%>
						<img alt="" src="${pageContext.request.contextPath }/image/headImage.png" height="90" width="100%">
						<div style="margin-top:20px;">
							<span style="font-size:25px;font-style:粗体;">问答</span>&nbsp;&nbsp;
							<img alt="" src="${pageContext.request.contextPath }/image/image1.png">
							<a href="<%=basePath %>userMainPage/getAddQuestionPage.do" style="font-size:20px;font-style:粗体;">&nbsp;提问</a>
							<%-- <div style="float:right;">
								<img alt="" src="${pageContext.request.contextPath }/image/image2.png">
								<a style="font-size:20px;font-style:粗体;">&nbsp;热门问题</a>&nbsp;&nbsp;
								<img alt="" src="${pageContext.request.contextPath }/image/image3.png">
								<a style="font-size:20px;font-style:粗体;">&nbsp;等你来答</a>
							</div> --%>
						</div>
						<div style="margin-top:20px;border:3px solid blue;">
							<br>
							${newQuestionsHtmlStr }
						</div><br><br>
						<div style="font-size:20px;background-color:blue;width:67px;">热评榜</div>
						<div style="margin-top:20px;border:3px solid blue;">
							<br>
							${commentnumQuesHtmlStr }
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-2"></div>
	</div>
</body>  
<script type="text/javascript">
	// 点击问题类型
	function typeClick(obj){
		var typeid = obj.id;
		var typename = obj.innerHTML;
		// 根据问题类型查询该类型下信息
		window.location.href = "<%=basePath%>userMainPage/getQuetypePage.do?typeid=" + typeid + "&typename=" + typename;
	}
	
	// 点击问题
	function questionClick(obj){
		var questionno = obj.id;
		window.location.href = "<%=basePath%>userMainPage/getQuestionPage.do?questionno=" + questionno;
	}
</script>
</html>
