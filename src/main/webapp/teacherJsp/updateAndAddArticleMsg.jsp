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
<body class="easyui-layout" style='overflow-y:auto;'>
    <div data-options="region:'north',split:true" style="height:130px;background:url(${pageContext.request.contextPath }/image/headImage.png);background-size:100% 130px;">
    	<h2>作者:${username}</h2><br>
    </div>
    <div data-options="region:'west',split:true" style="width:250px;background-color:#fafafa;">
    	<br>
    	<a href='<%=basePath%>userMainPage/userMainPageController.do' >我的主页</a><br>
    	<a href='<%=basePath%>userMainPage/getAcadExcCenterPage.do' >课程问题吧</a><br>
    	<a href='<%=basePath%>userViewMain/userViewMainController.do' >返回首页</a><br><br><br>
   		<div id="calendar">
    	</div>
    </div>   
    <div data-options="region:'center'" style="padding:5px;background:#eee;">
    	<div style='margin-left:20px;'>
    		<div style='margin-left:20px;'>
    			<h4 style='font-size:20px;'>标题:</h4>
    			<input id="titileTxt" class="easyui-textbox" type="text" value="${articlename }" style="width:100%;height:30px;"><br>
    		</div><br>
    		<div style='margin-left:20px;'>
    			<h4 style='font-size:20px;'>简介:</h4>
    			<input id="abstructTxt" class="easyui-textbox" multiline="true" value="${articlecabstract }" style="width:100%;height:100px;"><br>
    		</div><br>
    		<div style='margin-left:20px;'>
    			<h4 style='font-size:20px;'>工具栏</h4>
	    		<button type="button" class="btn btn-default" onclick="addImage()">插入图片</button>
	    		<input type="file" id="imageFile" name="imageFile" style="display:none;" onchange="imageChange(this)">
    		</div><br>
    		<div style='margin-left:20px;'>
    			<h4 style='font-size:20px;'>内容:</h4>
    			<img id="img" alt="" src="" style="display:none;" height="50" width="100px;">
    			<div id="contentTxt" contenteditable="true" style="height:500px;border:1px solid;background-color:white;overflow-y:auto;">${articlecontent }</div>
    		</div><br>
    		<div style='margin-left:20px;'>
    			<h4 style='font-size:15px;'>发布文章类型</h4>
    			${articletypesHtmlStr }
    		</div><br>
    		<div style='margin-left:20px;text-align:center;'>
	    		<button type="button" class="btn btn-default" onclick="btnUpdate()" style="display:${signUpdate };">修改</button>
	    		<button type="button" class="btn btn-default" onclick="btnPublish()" style="display:${signSave };">发表</button>
	    		<button type="button" class="btn btn-default">取消</button>
    		</div><br>
    	</div>
    </div>
</body>  
<script type="text/javascript">

	new calendar('calendar');
	
	// 修改
	function btnUpdate(){
		// 文章编号
		var articleno = "${articleno}";
		if(!articleno.length > 0){
			alert("未获取到文章信息");
			return;
		}
		// 标题
		var articlename = document.getElementById("titileTxt").value;
		if(articlename == ""){
			alert("请输入文章标题");
			return;
		}
		// 简介
		var articlecabstract = document.getElementById("abstructTxt").value;
		if(articlecabstract == ""){
			alert("请输入文章简介");
			return;
		}
		// 内容
		var articlecontent = document.getElementById("contentTxt").innerHTML;
		if(articlecontent == null || articlecontent == ""){
			alert("请输入文章内容");
			return;
		}
		// 类型
		var types = document.getElementsByName("typeArt");
		var typeArray = new Array();
		for(var i = 0,len = types.length; i < len; i++){
			if(types[i].checked){
				typeArray.push(types[i].value);
			}
		}
		if(typeArray.length == 0){
			alert("至少选择一个发布文章类型");
			return;
		}
		// 参数
		var param = {"articleno":articleno,"articlename":articlename,"articlecabstract":articlecabstract,"articlecontent":articlecontent,"types":typeArray.join(",")};
		$.ajax({
			type:"post",
			url:"<%=basePath%>userMainPage/updateArtcleMsg.do",
			data:param,
			datatype:"json",
			success:function(data){
				data = eval('(' + data + ')');
				var msg = data.msg;
				alert(msg);
				window.location.href = "<%=basePath%>userMainPage/userMainPageController.do";
			}
		});
	}
	
	// 发表
	function btnPublish(){
		// 用户编号
		var userno = "<%=session.getAttribute("userno")%>";
		// 用户名
		var username = "<%=session.getAttribute("username")%>";
		if(userno == "null" || username == ""){
			alert("请先登录");
			return;
		}
		// 标题
		var articlename = document.getElementById("titileTxt").value;
		if(articlename == ""){
			alert("请输入文章标题");
			return;
		}
		// 简介
		var articlecabstract = document.getElementById("abstructTxt").value;
		if(articlecabstract == ""){
			alert("请输入文章简介");
			return;
		}
		// 内容
		var articlecontent = document.getElementById("contentTxt").innerHTML;
		if(articlecontent == null || articlecontent == ""){
			alert("请输入文章内容");
			return;
		}
		// 类型
		var types = document.getElementsByName("typeArt");
		var typeArray = new Array();
		for(var i = 0,len = types.length; i < len; i++){
			if(types[i].checked){
				typeArray.push(types[i].value);
			}
		}
		if(typeArray.length == 0){
			alert("至少选择一个发布文章类型");
			return;
		}
		// 参数
		var param = {"userno":userno,"username":username,"articlename":articlename,"articlecabstract":articlecabstract,"articlecontent":articlecontent,"types":typeArray.join(",")};
		$.ajax({
			type:"post",
			url:"<%=basePath%>userMainPage/addArtcles.do",
			data:param,
			datatype:"json",
			success:function(data){
				data = eval('(' + data + ')');
				var msg = data.msg;
				alert(msg);
				window.location.href = "<%=basePath%>userMainPage/userMainPageController.do";
			}
		});
	}
	
	// 插入图片
	function addImage(){
		document.getElementById("imageFile").click();
	}
	
	// 插入图片文件框选择图片进行验证,验证通过后进行插入
	function imageChange(obj){
		var file = (obj.files)[0];
		var reader = new FileReader();// 操作图片
		if(/image/.test(file.type)){
			reader.readAsDataURL(file); 
		}else {
			alert("请选择图片");
			obj.value = "";
		}
		// 图片加载错误
		reader.onerror = function(){
			alert("图片加载错误");
		}
		// 图片加载完成
		reader.onload = function(){
			var imgObj = document.createElement("img");
			imgObj.src = reader.result;
			document.getElementById("contentTxt").appendChild(imgObj);
			obj.value = "";
		}
	}
</script>
</html>
