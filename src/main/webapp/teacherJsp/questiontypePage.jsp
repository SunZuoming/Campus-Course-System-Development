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
  					<a href="<%=basePath %>userMainPage/getAcadExcCenterPage.do">返回主页</a>
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
						<img alt="" src="${pageContext.request.contextPath }/image/headImage.png" height="90" width="100%">
						<div>
							<div id="tt1" class="easyui-tabs" style="width:100%;height:310px;">   
							    <div title="本月最佳" style="padding:20px;display:none;">
						    		<div>
						    			<span>问题</span>
						    			<span style='float:right;margin-right:81px;'>发表时间</span>
						    			<span style='float:right;margin-right:35px;'>评论</span>
						    			<span style='float:right;margin-right:85px;'>作者</span>
						    		</div><hr>
									${monthCommsHtmlStr }
							    </div>   
							    <div title="本年最佳" style="overflow:auto;padding:20px;display:none;">
						    		<div>
						    			<span>问题</span>
						    			<span style='float:right;margin-right:81px;'>发表时间</span>
						    			<span style='float:right;margin-right:35px;'>评论</span>
						    			<span style='float:right;margin-right:85px;'>作者</span>
						    		</div><hr>
									${yearCommsHtmlStr }
							    </div>   
							</div>
						</div>
						<div>
							<div id="tt2" class="easyui-tabs" style="width:100%;height:900px;margin-top:-20px;">
								<div title="全部" style="overflow:auto;padding:20px;display:none;">   
							    	<div style='float:right;margin-top:-15px;'>
							    		<input id="allQuery" class="easyui-textbox" prompt='搜索' style="width:150px;"> 
							    	</div>
							    	<div style='margin-top:15px;'>
							    		<div>
							    			<span>问题</span>
							    			<span style='float:right;margin-right:81px;'>发表时间</span>
							    			<span style='float:right;margin-right:35px;'>评论</span>
							    			<span style='float:right;margin-right:85px;'>作者</span>
							    		</div><hr>
							    		<div id="allDiv">
											${questionTables1HtmlStr }
							    		</div>
							    	</div>
							    	<div style="text-align:center;">
							    		
							    	</div>
							    </div>
							    <div title="最热" style="overflow:auto;padding:20px;display:none;">   
							    	<div style='float:right;margin-top:-15px;'>
							    		<input id="hostQuery" class="easyui-textbox" prompt='搜索' style="width:150px;"> 
							    	</div>
							    	<div style='margin-top:15px;'>
							    		<div>
							    			<span>问题</span>
							    			<span style='float:right;margin-right:81px;'>发表时间</span>
							    			<span style='float:right;margin-right:35px;'>评论</span>
							    			<span style='float:right;margin-right:85px;'>作者</span>
							    		</div><hr>
							    		<div id="hostDiv">
											${questionTables2HtmlStr }
							    		</div>
							    	</div>
							    </div>   
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-2"></div>
	</div>
</body>  
<script type="text/javascript">
	$(document).ready(function(){
		$('#allQuery').textbox({
			icons: [{
				iconCls:'icon-search',
				handler: function(e){
					var typeno = "${typeno}";
					var val = $("#allQuery").textbox('getValue');
					// 参数
					var param = {"typeno":typeno,"val":val};
					$.ajax({
						type:"post",
						url:"<%=basePath%>userMainPage/queryAllQueByQname.do",
						data:param,
						datatype:"json",
						success:function(data){
							data = eval('(' + data + ')');
							var questionHtmlStr = data.questionHtmlStr;
							var alldiv = document.getElementById("allDiv");
							alldiv.innerHTML = questionHtmlStr;
						}
					});
				}
			}]
		});
		
		$('#hostQuery').textbox({
			icons: [{
				iconCls:'icon-search',
				handler: function(e){
					var typeno = "${typeno}";
					var val = $("#hostQuery").textbox('getValue');
					// 参数
					var param = {"typeno":typeno,"val":val};
					$.ajax({
						type:"post",
						url:"<%=basePath%>userMainPage/queryHostQueByQname.do",
						data:param,
						datatype:"json",
						success:function(data){
							data = eval('(' + data + ')');
							var questionHtmlStr = data.questionHtmlStr;
							var hostDiv = document.getElementById("hostDiv");
							hostDiv.innerHTML = questionHtmlStr;
						}
					});
				}
			}]
		});
	});

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
