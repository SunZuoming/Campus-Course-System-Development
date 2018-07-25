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
<title>${articlename}</title>
</head>
<body class="easyui-layout"  style='overflow-y:auto;'>
    <div data-options="region:'north',split:true" style="height:130px;">
    	<h2>作者:${articlepublisher}</h2><br>
    	<span style='float:right;'>发布时间&nbsp;&nbsp;${articlepublishtime}&nbsp;&nbsp;评论(${articlecomnum})
    	&nbsp;&nbsp;收藏(${articlecollectnum})&nbsp;&nbsp;举报(${articlereportnum})&nbsp;&nbsp;浏览(${articlereadnum})</span>
    </div>   
    <div data-options="region:'west',split:true" style="width:250px;">
    	<br>
   		<div id="calendar">
    	</div>
    	<span style="font-size:15px;margin-left:-10px;">最新发表</span>
    		${newArtsHtmlStr}
    	<hr>
    	<span style="font-size:15px;margin-left:-10px;">浏览排行榜</span>
    		${readArtsHtmlStr}
    	<hr>
    	<span style="font-size:15px;margin-left:-10px;">推荐排行榜</span>
    		${collArtsHtmlStr}
    	<hr>
    	<span style="font-size:15px;margin-left:-10px;">评论排行榜</span>
    		${commArtsHtmlStr}
    	<hr>
    </div>   
    <div data-options="region:'center'" style="padding:5px;background:#eee;">
    	<div style='margin-left:20px;'>
    		<h3 style='color:blue;'>${articlename }</h3><br>
    		<h4 style='margin-left:20px;'>简介:</h4>
    		<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${articlecabstract}</p><br>
    		<h4 style='margin-left:20px;'>内容:</h4>
    		<div style="overflow-y:auto;margin-left:20px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${articlecontent}</div><br><br><br>
    		<div style='float:right;margin-right:20px;font-size:15px;'>
    			<a href="#" onclick="reportClick()">举报</a>
    			<a href="#" onclick="collectionClick(this)">收藏</a>
    		</div>
    		<br>
   			<span style='font-size:15px;margin-left:20px;'>举报原因</span><br>
    		<div id="reportDiv" style='margin-left:20px;'>
    			<input id="reportreason" class="easyui-textbox" multiline="true" style="width:100%;height:100px;"><br>
    			<button type="button" class="btn btn-default" onclick="btnReport()">举报</button>
    			<button type="button" class="btn btn-default">清空</button>
    		</div><br>
    		<h4 style='margin-left:20px;'>发表评论:</h4><br>
    		<span style='font-size:15px;margin-left:20px;'>评论内容</span><br>
    		<div style='margin-left:20px;'>
    			<input id="comment" class="easyui-textbox" multiline="true" style="width:100%;height:100px;"> 
    			<button type="button" class="btn btn-default" onclick="btnComment()">发表</button>
    			<button type="button" class="btn btn-default">清空</button>
    		</div><br>
    		<h4 style='margin-left:20px;'>评论:</h4><br>
    		<div id="commentconten" style='margin-left:40px;'>
    			${commentHtmlStr }
    		</div>
    	</div>
    </div>
    <a href="#"></a>
</body>  
<script type="text/javascript">

	new calendar('calendar');
	
	// 收藏
	function collectionClick(obj){
		var collector = "<%=session.getAttribute("userno")%>"; // 收藏人
		if(collector == "null"){
			alert("请先登录");
			return;
		}
		var articleno = "${articleno }"; // 文章编号
		if(articleno == null || articleno == ""){
			alert("该文章已失效");
			return;
		}
		// 收藏作品类型
		var collectiontype = "0"; // 0:文章
		// 参数
		var param = {"collector":collector,"collectionno":articleno,"collectiontype":collectiontype};
		$.ajax({
			type:"post",
			url:"<%=basePath%>userViewMain/cllectionArticle.do",
			data:param,
			datatype:"json",
			success:function(data){
				data = eval('(' + data + ')');
				var msg = data.msg;
				obj.innerHTML = msg;
			}
		});
	}
	
	// 评论
	function btnComment(){
		var commenter = "<%=session.getAttribute("username")%>"; // 评论人姓名
		var commenterno = "<%=session.getAttribute("userno")%>"; // 评论人
		if(commenterno == "null"){
			alert("请先登录");
			return;
		}
		var articleno = "${articleno }"; // 文章编号
		if(articleno == null || articleno == ""){
			alert("该文章已失效");
			return;
		}
		var comment = $("#comment").val();// 评论内容
		if(comment == ""){
			alert("请输入评论内容");
			return;
		}
		// 参数
		var param = {"commenter":commenter,"commenterno":commenterno,"articleno":articleno,"commentcontent":comment};
		$.ajax({
			type:"post",
			url:"<%=basePath%>userViewMain/comment.do",
			data:param,
			datatype:"json",
			success:function(data){
				data = eval('(' + data + ')');
				var msg = data.msg;
				$("#comment").val("");
				alert(msg);
				// 文章评论
				var commentHtmlStr = data.commentHtmlStr;
				if(commentHtmlStr != undefined){
					document.getElementById("commentconten").innerHTML = commentHtmlStr;
				}
			}
		});
	}
	
	// 举报按钮
	function btnReport(){
		var reporter = "<%=session.getAttribute("userno")%>"; // 举报人
		if(reporter == "null"){
			alert("请先登录");
			return;
		}
		var reporteer = "${articlepublisherno }";// 被举报人
		var reportgoodsno = "${articleno }"; // 被举报文章编号
		if(reportgoodsno == null || reportgoodsno == ""){
			alert("该文章已失效");
			return;
		}
		var reportreason = $("#reportreason").val();// 被举报原因
		if(reportreason == ""){
			alert("请输入举报原因");
			return;
		}
		// 被举报类型
		var reportgoodstype = "0";// 0：文章
		// 参数
		var param = {"reporter":reporter,"reporteer":reporteer,"reportgoodsno":reportgoodsno,"reportreason":reportreason,"reportgoodstype":reportgoodstype};
		$.ajax({
			type:"post",
			url:"<%=basePath%>userViewMain/reportArticle.do",
			data:param,
			datatype:"json",
			success:function(data){
				data = eval('(' + data + ')');
				var msg = data.msg;
				$("#reportreason").val("");
				alert(msg);
			}
		});
		
	}
	
	// 点击举报
	function reportClick(){
		document.getElementById("reportDiv").style.display = "";
	}
</script>
</html>
