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
	<div id="usernoDiv" style="display:none;">${userno }</div>
    <div data-options="region:'north'" style='background-color:#fafafa;'>
    	<div style='width:100%;height:150px;background:url(${pageContext.request.contextPath }/image/background.png);background-size:100% 150px;'>
    		<div style="float:right;margin-right:20px;margin-top:5px;">
    			<a onclick="sysFirst()">系统首页</a>
	    		<a onclick="execute()">退出</a>
	    	</div><br><br>
			<div style="margin-top:85px;">
	    		${functionHtmlStr }
			</div><br>
    	</div><br>
		<div class="row">
		  	<div class="col-md-10">
	  			<div id="p1" class="easyui-panel" style="width:100%;height:700px;background:#fafafa;" data-options="">
			  		<div id="iframeDiv" style='width:100%;height:100%;'>
						<iframe id="iframe1" src="${pageContext.request.contextPath }/userMainPage/getFirstPage.do" frameborder="0" style='width:100%;height:100%;background-color:#fafafa;'></iframe>
					</div>
	  			</div>
		  	</div>
		  	<div class="col-md-2" style='background:#fafafa;'>
		  		昵称:${username }<br>
	    		电话:${userphone }<br><br>
	    		<div id="calendar">
		    	</div><br>
		    	<span style='font-size:15px;'>我的课程文章</span>
		    	<div id='myCourseArt'>
		    	</div>
		  	</div>
		</div>
    </div>
   <!--  <div data-options="region:'east'" style="width:200px;">
    	
    </div> 
    <div data-options="region:'center'" style="padding:5px;background:#eee;">
		
    </div> -->
</body>  
<script type="text/javascript">
	new calendar('calendar');
	// 查询我的课程文章分类信息
	myArt("<%=session.getAttribute("userno")%>");
	
	// 定义定时器，查询个人最新消息记录
	setInterval("queryNewInfonum()",60000);
	queryNewInfonum();
	
	function queryNewInfonum(){
		var userno = "<%=session.getAttribute("userno")%>";
		if(userno != "null"){
			var param = {"userno":userno};// 参数
			$.ajax({
				type:"post",
				url:"<%=basePath%>userMainPage/queryMyInfonums.do",
				data:param,
				datatype:"json",
				success:function(data){
					data = eval('(' + data + ')');
					var infonum = data.infonum;
					var myInfomation = document.getElementById("myInfomation").innerHTML = infonum;
				}
			});
		}
	}
	
	// 点击系统首页
	function sysFirst(){
		window.location.href = "<%=basePath%>userViewMain/userViewMainController.do";
	}
	
	// 加载功能页面信息
	function funClick(url){
		var userno = "<%=session.getAttribute("userno")%>";
		if(userno == "null"){
			alert("请先登录");
			return;
		}else{
			if(url == "userMainPage/getAddArtclesPage.do" || url == "userMainPage/getAcadExcCenterPage.do"){
				window.location.href = "<%=basePath%>" + url + "?userno=" + userno;
			}else if(url == "teacher/listshareFileto.do"||url == "course/CourseAll.do"){
				document.getElementById("iframe1").src = "<%=basePath%>" + url + "?userno=" + userno+ "&num=1";
			}else{
				document.getElementById("iframe1").src = "<%=basePath%>" + url + "?userno=" + userno;
			}
		}
		// 查询我的课程文章分类信息
		myArt(userno);
	}

	// 查询我的课程文章分类信息
	function myArt(userno){
		var param = {"userno":userno};// 参数
		if(userno == "null"){
			alert("请先登录");
			return;
		}
		$.ajax({
			type:"post",
			url:"<%=basePath%>userMainPage/getMyCourseArt.do",
			data:param,
			datatype:"json",
			success:function(data){
				data = eval('(' + data + ')');
				var myCourseArtHtmlStr = data.myCourseArtHtmlStr;
				document.getElementById("myCourseArt").innerHTML = myCourseArtHtmlStr;
			}
		});
	}
	
	// 退出
	function execute(){
		location.href = "<%=basePath%>teacherJsp/teacherlogin.jsp";
	}
	
	// 点击我的课程文章
	function typeClick(typeid){
		var userno = "<%=session.getAttribute("userno")%>";
		if(userno == "null"){
			alert("请先登录");
			return;
		}
		document.getElementById("iframe1").src = "<%=basePath%>userMainPage/getMyArtclesByTypeno.do?userno=" + userno + "&typeid=" + typeid;
	}
</script>
</html>
