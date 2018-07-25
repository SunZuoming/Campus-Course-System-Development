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
</head>
<style type="text/css">
a,li{
	cursor: pointer;
}
</style>
<body class="easyui-layout" style='overflow-y:auto;'>
  	<div id="divcoll"style="width:100%;background-color:#fafafa;">   
    	${collArtsHtmlStr }
	</div>   
</body>
<script type="text/javascript">
	
	// 删除我发布的文章
	function deleteArticle(articlenoAndname){
		var userno = "<%=session.getAttribute("userno")%>"; // 用户编号
		if(userno == "null" || userno == ""){
			alert("请先登录");
			return;
		}
		var index = articlenoAndname.lastIndexOf("_");
		var articleno = articlenoAndname.substring(0,index);
		var articlename = articlenoAndname.substring(index+1);
		var con = confirm("是否删除【" + articlename + "】");
		if(con){
			// 参数
			var param = {"articleno":articleno,"userno":userno};
			$.ajax({
				type:"post",
				url:"<%=basePath%>userMainPage/deleteArticle.do",
				data:param,
				datatype:"json",
				success:function(data){
					data = eval('(' + data + ')');
					var msg = data.msg;
					if(msg == "删除失败"){
						alert(msg);
					}
					var collArtsHtmlStr = data.collArtsHtmlStr;
					document.getElementById("divcoll").innerHTML = collArtsHtmlStr;
				}
			});
		}
	}
	
	// 取消收藏
	function cancelCollection(articlenoAndname){
		var collector = "<%=session.getAttribute("userno")%>"; // 收藏人
		if(collector == "null" || collector == ""){
			alert("请先登录");
			return;
		}
		var index = articlenoAndname.lastIndexOf("_");
		var articleno = articlenoAndname.substring(0,index);
		var articlename = articlenoAndname.substring(index+1);
		var con = confirm("是否取消收藏【" + articlename + "】");
		if(con){
			// 收藏作品类型
			var collectiontype = "0"; // 0:文章
			// 参数
			var param = {"collector":collector,"collectionno":articleno,"collectiontype":collectiontype};
			$.ajax({
				type:"post",
				url:"<%=basePath%>userMainPage/cancelArticle.do",
				data:param,
				datatype:"json",
				success:function(data){
					data = eval('(' + data + ')');
					var msg = data.msg;
					if(msg == "取消收藏失败"){
						alert(msg);
					}
					var collArtsHtmlStr = data.collArtsHtmlStr;
					document.getElementById("divcoll").innerHTML = collArtsHtmlStr;
				}
			});
		}
	}

	// 编辑
	function updateArtcile(articleno){
		// 进入编辑页面
		window.open("<%=basePath%>userMainPage/updateArticles.do?articleno=" + articleno,"_blank");
	}

	// 收藏
	function collectionArctile(obj){
		var articleno = obj.name;
		if(articleno == null || articleno == ""){
			alert("该文章已失效");
			return;
		}
		var collector = "<%=session.getAttribute("userno")%>"; // 收藏人
		if(collector == "null"){
			alert("请先登录");
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
				alert(msg);
			}
		});
	}
	
	// 浏览
	function readArctile(articleno){
		// 进入浏览页面
		window.open("<%=basePath%>userViewMain/viewArticleMsg.do?articleno=" + articleno,"_blank");
	}
	
	// 评论
	function comment(articleno){
		var commenter = "<%=session.getAttribute("username")%>"; // 评论人
		if(commenter == "null"){
			alert("请先登录");
			return;
		}
		// 进入评论页面
		window.open("<%=basePath%>userViewMain/viewArticleMsg.do?articleno=" + articleno,"_blank");
	}
	
	// 举报
	function report(articleno){
		// 验证用户是否已经登录
		$.ajax({
			type:"post",
			url:"<%=basePath%>userViewMain/verifyUserLogin.do",
			success:function(data){
				data = eval('(' + data + ')');
				if(data.userno == ""){
					alert("请先登录");    
				}else {
					// 进入举报页面
					window.open("<%=basePath%>userViewMain/viewArticleMsg.do?articleno=" + articleno,"_blank");
				}
			}
		});
	}
</script>
</html>
