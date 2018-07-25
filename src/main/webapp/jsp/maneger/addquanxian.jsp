<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	session.setAttribute("basePath", basePath);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../js/jquery.min.js"></script>
	<link rel="stylesheet" href="${basePath }/layui/css/my.css"> 
	
</head>
<body>
<form action="${basePath }/role/add1.do" method="post">

	<div class="wrap">
	<span class="tip">请输入角色:</span>
		<div class=" posirelative select-out-div">
		<select name="role" id="role" class="m-wrap  "  style="width: 180px; padding: 2px 0;">
						<option value="0" selected="selected">一一一一请选择一一一一</option>
						<c:forEach items="${list1 }" var="s">
							<option value="${s.roleid}">${s.rolename}</option>
						</c:forEach>
					</select>

		</div>

	</div>



	<div class="wrap">

		<span class="tip" >请输入功能</span> 
		<div class=" posirelative select-out-div">
		<select name="function" id = "function" class="m-wrap  "  style="width: 180px; padding: 2px 0;">
						<option value="0" selected="selected">一一一一请选择一一一一</option>
						<c:forEach items="${list2}" var="s">
							<option value="${ s.functionno}">${s.functionname}</option>
						</c:forEach>
					</select>
	</div>
		<p></p>

	</div>
	<div class="wrap">
		<button class="" key="set-mine" type="submit" style="margin-left: 50%">
					提交</button>
	</div>
	

	
</form>
</body>
<script type="text/javascript">
$(function(){
	$("#role").change(function(){
		$.ajax({
			type:"post",
			url:"<%=basePath%>role/showFunByRole.do",
			data:{"roleId":$("#role").val()},
			datatype:"json",
			success:function(data){
				data = eval('(' + data + ')');
				/* var functionListHtmlStr = data.functionListHtmlStr;
				if(functionListHtmlStr != undefined){ */
					document.getElementById("function").innerHTML =  data.functionListHtmlStr;
				/* } */

			},
			error:function(data){
				alert("请求失败!");
			}
		});
	});
})

</script>
</html>
<%-- <html>
    
    <head>
        <meta charset="utf-8">
        <title>
            公告管理-编辑
        </title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="format-detection" content="telephone=no">
        <script type="text/javascript" src="../js/jquery.min.js"></script>
        <script type="application/javascript" src="${basePath }/layui/layui.js"></script>
		<link rel="stylesheet" href="${basePath }/layui/css/layui.css"> 
        
    </head>

<body>
	<div class="x-body" style="margin-top: 5%">
		<form id="layui-form" class="layui-form" lay-filter="test1"
			action="${basePath }/role/add1.do" method="post">
			<div class="layui-form-item">
				<label class="layui-form-label"> 角色编号: </label>
				<div class="layui-form-select" align="left"
					style="margin-left: 9%; margin-right: 60%">
					<select name="role" id="role">
						<option value="0" selected="selected">---请选择---</option>
						<c:forEach items="${list1 }" var="s">
							<option value="${s.roleid}">${s.rolename}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-form-item ">
				<label for="L_city" class="layui-form-label"> 功能编号: </label>
				<div class="layui-form-select" id="functionDiv" align="left"
					style="margin-left: 9%; margin-right: 60%">
					<select name="function" id = "function">
						<option value="0" selected="selected">---请选择---</option>
						<c:forEach items="${list2}" var="s">
							<option value="${ s.functionno}">${s.functionname}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_sign" class="layui-form-label"> </label>
				<button class="layui-btn" key="set-mine" type="submit">
					提交</button>
			</div>
		</form>
	</div>

</body>
<script type="text/javascript">

	  layui.use('form', function(){
	   var form = layui.form();//高版本建议把括号去掉，有的低版本，需要加()
	   form.render();
	  });

    //监听提交
    form.on('submit(role)', function(data){
      console.log(data);
      //发异步，把数据提交给php
      layer.alert("保存成功", {icon: 6},function () {
          // 获得frame索引
          var index = parent.layer.getFrameIndex(window.name);
          //关闭当前frame
          parent.layer.close(index);
      });
      return false;
    });
	$(function(){
		$("#role").change(function(){
			$.ajax({
				type:"post",
				url:"<%=basePath%>role/showFunByRole.do",
				data:{"roleId":$("#role").val()},
				datatype:"json",
				success:function(data){
					data = eval('(' + data + ')');
					/* var functionListHtmlStr = data.functionListHtmlStr;
					if(functionListHtmlStr != undefined){ */
						document.getElementById("function").innerHTML =  data.functionListHtmlStr;
					/* } */
	
				},
				error:function(data){
					alert("请求失败!");
				}
			});
		});
	})

</script>
</html> --%>