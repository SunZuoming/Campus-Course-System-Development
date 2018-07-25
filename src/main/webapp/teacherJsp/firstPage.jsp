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
	<!-- Bootstrap 核心 JavaScript 文件 -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<!-- Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css">



<!-- 引入日历控件js -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/calendar.js"></script>
</head>
<style type="text/css">
a,li{
	cursor: pointer;
}
</style>

<body class="easyui-layout" style='overflow-y:auto;background-color:#fafafa;'>
	<div class="row">
  		<div class="col-md-1"></div>
  		<div class="col-md-10">
  			<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
			  <ol class="carousel-indicators" style="">
			    <li data-target="#carousel-example-generic" data-slide-to="0"></li>
			    <li data-target="#carousel-example-generic" data-slide-to="1" class="active"></li>
			    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
			  </ol>
	
			  <div class="carousel-inner" role="listbox">
			    <div class="item active">
			      <img src="${pageContext.request.contextPath }/image/headImage.png" alt="..." style="width:100%;">
			      <div class="carousel-caption">
			        
			      </div>
			    </div>
			    <div class="item">
			      <img src="${pageContext.request.contextPath }/image/headImage.png" alt="..." style="width:100%;">
			      <div class="carousel-caption">
			        
			      </div>
			    </div>
			    <div class="item">
			      <img src="${pageContext.request.contextPath }/image/headImage.png" alt="..." style="width:100%;">
			      <div class="carousel-caption">
			        
			      </div>
			    </div>
			  </div>
			</div>
			<div>
				<div class="row">
  					<div class="col-md-6">
						热门问题<hr>
						${commentnumQuesHtmlStr }
  					</div>
  					<div class="col-md-6">
  						公告<hr>
  						${noticeHtmlStr }
  					</div>
				</div>
				<div class="row">
  					<div class="col-md-6">
						热门文章<hr>
  						<div style="margin-left:15px;">
							${hostArticlesHtmlstr }
						</div>
  					</div>
  					<div class="col-md-6">
  						常用链接<hr>
  						<ul style='margin-left:-15px;'>
	  						<li style='margin-top:5px;'><a href="" onclick="getSysfirstpage()">系统首页</a></li>
	  						<li style='margin-top:5px;'><a href="" onclick="getAddArt()">发布文章</a></li>
	  						<li style='margin-top:5px;'><a href="getMyArtcles.do">我发布的文章</a></li>
	  						<li style='margin-top:5px;'><a href="">我的作业</a></li>
	  						<li style='margin-top:5px;'><a href="getInfoPage.do">个人消息</a></li>
  						</ul>
  					</div>
				</div>
			</div>
		</div>
  		<div class="col-md-1"></div>
	</div>
</body>  
<script type="text/javascript">
	//加载功能页面信息
	function funClick(url){
		var userno = "<%=session.getAttribute("userno")%>";
		if(userno == "null"){
			alert("请先登录");
			return;
		}else{
			if(url == "userMainPage/getAddArtclesPage.do" || url == "userMainPage/getAcadExcCenterPage.do"){
				window.location.href = "<%=basePath%>" + url + "?userno=" + userno;
			}else{
				document.getElementById("iframe1").src = "<%=basePath%>" + url + "?userno=" + userno;
			}
		}
	}
	
	// 点击公告
	function noticeClick(content){
		$.messager.alert('公告', content); 
	}

	// 发布文章
	function getAddArt(){
		window.open("<%=basePath%>userMainPage/getAddArtclesPage.do","_blank");
	}
	
	// 系统首页
	function getSysfirstpage(){
		window.open("<%=basePath%>userViewMain/userViewMainController.do","_blank");
	}

	//点击问题类型
	function typeClick(obj){
		var typeid = obj.id;
		var typename = obj.innerHTML;
		// 根据问题类型查询该类型下信息
		window.open("<%=basePath%>userMainPage/getQuetypePage.do?typeid=" + typeid + "&typename=" + typename,"_blank");
	}
	
	// 点击问题
	function questionClick(obj){
		var questionno = obj.id;
		window.open("<%=basePath%>userMainPage/getQuestionPage.do?questionno=" + questionno,"_blank");
	}
</script>
</html>
