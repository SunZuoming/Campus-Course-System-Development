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
	<script type="text/javascript">
	$(function(){
	    $('[data-toggle = "popover"]').popover();
	})
	</script>
</head>
<style type="text/css">
a,li{
	cursor: pointer;
}
</style>

<body class="easyui-layout" style='overflow-y:auto;background-color:#fafafa;'>   
    <div data-options="region:'north'" style="height:150px;background:url(${pageContext.request.contextPath }/image/background.png);background-size:100% 150px;">
    	<div style="float:right;margin-right:20px;">
    		<a href="${pageContext.request.contextPath }/teacherJsp/teacherlogin.jsp">登录</a>
    		${myPageHtmlStr }
    		<a onclick="execute()">退出</a>
    	</div>
    </div>
    <div class="row" style='margin-top:150px;'>
  		<div class="col-md-2">
  			<div id="p1" class="easyui-panel" style="width:100%;height:700px;background:#fafafa;" data-options="">   
		        <span style="font-size:15px;margin-left:-10px;">课程文章类型</span><br><br>
		    	${articletypesHtmlstr }
		    	<hr><span style="font-size:15px;margin-left:-10px;">热门文章</span><br>
		    	<div id="hostsdiv">
		    	</div>
		    	<hr>
		    	<br><span style="font-size:15px;margin-left:-10px;">最新文章</span><br>
		    	<div id="newsdiv">
		    	</div>
		    	<hr>
			</div>
		</div>
  		<div class="col-md-8">
  			<div id="p2" class="easyui-panel" style="width:100%;height:700px;background:#fafafa;" data-options="">   
		        <div id="tt" class="easyui-tabs" style="width:100%;height:100%;background-color:#fafafa;">
		    	${articleTabsHtmlStr }
				</div>
			</div>
  		</div>
  		<div class="col-md-2">
  			<div id="p3" class="easyui-panel" style="width:100%;height:700px;background:#fafafa;" data-options="">   
	  			搜索:<input id="txtSelect" class="easyui-textbox" style="width:150px"> 
		    	<br><br>
		    	<div id='calendar'></div>
		    	<span style="font-size:15px;margin-left:-10px;">公告</span><br>
		    	<div id="noticesDiv">
		    	${noticeHtmlStr }
		    	</div>
		    	<br>
		    	<hr><span style="font-size:15px;margin-left:-10px;">收藏最多文章</span><br>
		    	<div id="collsdiv">
		    	</div>
		    	<hr>
			</div>
  		</div>
	</div>
 <!--    <div data-options="region:'west'" style="width:200px;">
    	
    </div>
    <div data-options="region:'east'" style="width:200px;">
    	
    </div> 
    <div data-options="region:'center'" style="padding:5px;background:#eee;">
	    
    </div> -->
</body>  
<script type="text/javascript">
	
	new calendar('calendar');

	$(document).ready(function(){
		$('#tt').tabs({    
		    border:false,    
		    onSelect:function(title){    
		        // 根据文章类型查询该类型下所有文章
		    	var param = {"articletypename":title};
				$.ajax({
					type:"post",
					url:"<%=basePath%>userViewMain/getArticlesByType.do",
					data:param,
					datatype:"json",
					success:function(data){
						data = eval('(' + data + ')');
						var tab = document.getElementById(data.articletypeid);
						tab.innerHTML = data.articeHtmlStr;
						// 进行热门文章、最新文章、收藏最多文章、公告的加载
						hostNewCollGg();
					},
					error:function(data){
						alert("请求失败!");
					}
				});
		    }    
		});
		
		$('#txtSelect').textbox({
			prompt:'输入文章标题关键字',
			icons: [{
				iconCls:'icon-search',
				handler: function(e){
					var val = $("#txtSelect").textbox('getValue');
					var tab = $('#tt').tabs('getSelected');
					// 获取选项卡id
					var typeid = tab.context.id;
					// 参数
					var param = {"val":val,"typeid":typeid};
					$.ajax({
						type:"post",
						url:"<%=basePath%>userViewMain/queryArticleByAname.do",
						data:param,
						datatype:"json",
						success:function(data){
							data = eval('(' + data + ')');
							var articeHtmlStr = data.articeHtmlStr;
							var contextdiv = document.getElementById(typeid);
							contextdiv.innerHTML = articeHtmlStr;
						},
						error:function(data){
							alert("请求失败!");
						}
					});
				}
			}]
		});
	});
	

	// 退出
	function execute(){
		location.href = "<%=basePath%>teacherJsp/teacherlogin.jsp";
	}
	
	//我的主页
	function myMainPage(){
		var userno = "<%=session.getAttribute("userno")%>";
		if(userno == "null"){
			alert("请先登录!");
			return;
		}
		window.open("<%=basePath%>userMainPage/userMainPageController.do","_self");
	}
	
	// 进行热门文章、最新文章、收藏最多文章、公告的加载
	hostNewCollGg();
	
	// 进行热门文章、最新文章、收藏最多文章、公告的加载
	function hostNewCollGg(){
		$.ajax({
			type:"post",
			url:"<%=basePath%>userViewMain/getArsHostNewCollmaxGg.do",
			success:function(data){
				data = eval('(' + data + ')');
				var hostsdiv = document.getElementById("hostsdiv");
				hostsdiv.innerHTML = data.hostArticlesHtmlstr;
				var newsdiv = document.getElementById("newsdiv");
				newsdiv.innerHTML = data.newArticlesHtmlstr;
				var collsdiv = document.getElementById("collsdiv");
				collsdiv.innerHTML = data.collmaxArticlesHtmlstr;
				var noticesDiv = document.getElementById("noticesDiv");
				noticesDiv.innerHTML = data.noticeHtmlStr;
			},
			error:function(data){
				alert("请求失败!");
			}
		});
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
				// 进行热门文章、最新文章、收藏最多文章、公告的加载
				hostNewCollGg();
			},
			error:function(data){
				alert("请求失败!");
			}
		});
	}
	
	// 点击公告
	function noticeClick(content){
		$.messager.alert('公告', content); 
	}
	
	// 浏览
	function readArctile(articleno){
		// 进入浏览页面
		window.open("<%=basePath%>userViewMain/viewArticleMsg.do?articleno=" + articleno,"_blank");
	}
	
	// 评论
	function comment(articleno){
		var commenter = "<%=session.getAttribute("username")%>"; // 评论人
		if(commenter == null){
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
			},
			error:function(data){
				alert("请求失败!");
			}
		});
	}
	
	// 文章类型点击
	function clickLi(tid){
		var index = tid.indexOf("_");
		var typeid = tid.substr(0,index);
		var typename = tid.substr(index+1);
		var param = {"articletypeid":typeid};
		$.ajax({
			type:"post",
			url:"<%=basePath%>userViewMain/getArticles.do",
			data:param,
			datatype:"json",
			success:function(data){
				data = eval('(' + data + ')');
				if(document.getElementById(typeid) == undefined){
					// 添加一个选中状态的选项卡面板
					$('#tt').tabs('add',{
						id:typeid,
						title:typename,
						selected:true,
						fit:true,
						closable:true,
						content: data.articeHtmlStr
					});
				}else{
					var tab = document.getElementById(typeid);
					tab.innerHTML = data.articeHtmlStr;
					$('#tt').tabs('select',typename);
				}
				// 进行热门文章、最新文章、收藏最多文章、公告的加载
				hostNewCollGg();
			},
			error:function(data){
				alert("请求失败!");
			}
		});
	}
	
	// 文章类型获取焦点
	function typeOnmouseover(obj){
		var childs = obj.getElementsByTagName("ul");
		if(childs.length > 0){
			childs[0].style.display = "";
		}
	}

	// 文章类型失去焦点
	function typeOnmouseout(obj){
		var childs = obj.getElementsByTagName("ul");
		if(childs.length > 0){
			childs[0].style.display = "none";
		}
	}
	
	// 点击页码
	function pageClick(obj){
		var objPage = obj.firstChild;
		var nowPage = objPage.innerHTML;
		var articletypeid = objPage.name;
		if(articletypeid == "pagehome"){
			articletypeid = "首页";
		}else {
			articletypeid = articletypeid.substr(4);
		}
		// 根据文章类型查询该类型下所有文章(分页查询)
	    var param = {"articletypeid":articletypeid, "nowPage":nowPage};
		$.ajax({
			type:"post",
			url:"<%=basePath%>userViewMain/getArticlesPageByType.do",
			data:param,
			datatype:"json",
			success:function(data){
				data = eval('(' + data + ')');
				var tab = document.getElementById(data.articletypeid);
				tab.innerHTML = data.articeHtmlStr;
				// 进行热门文章、最新文章、收藏最多文章、公告的加载
				hostNewCollGg();
			},
			error:function(data){
				alert("请求失败!");
			}
		});
	}
	
	// 上一页
	function previousPageClick(typeid){
		var pages = document.getElementsByName(typeid);
		for(var i = 0,len = pages.length; i < len; i++){
			if(pages[i].className == "active"){
				var page = pages[i].firstChild.innerHTML;
				var nowPage = parseInt(page) - 1;
				if(nowPage > 0){
					var articletypeid = typeid;
					if(typeid == "pagehome"){
						articletypeid = "首页";
					}else {
						articletypeid = articletypeid.substr(4);
					}
					// 根据文章类型查询该类型下所有文章(分页查询)
				    var param = {"articletypeid":articletypeid, "nowPage":nowPage};
					$.ajax({
						type:"post",
						url:"<%=basePath%>userViewMain/getArticlesPageByType.do",
						data:param,
						datatype:"json",
						success:function(data){
							data = eval('(' + data + ')');
							var tab = document.getElementById(data.articletypeid);
							tab.innerHTML = data.articeHtmlStr;
							// 进行热门文章、最新文章、收藏最多文章、公告的加载
							hostNewCollGg();
						},
						error:function(data){
							alert("请求失败!");
						}
					});
				}
			}
		}
	}
	
	// 下一页
	function nextPageClick(typeid){
		var pages = document.getElementsByName(typeid);
		for(var i = 0,len = pages.length; i < len; i++){
			if(pages[i].className == "active"){
				var page = pages[i].firstChild.innerHTML;
				var articletypeid = typeid;
				var nowPage = parseInt(page) + 1;
				if(typeid == "pagehome"){
					articletypeid = "首页";
				}else {
					articletypeid = articletypeid.substr(4);
				}
				// 根据文章类型查询该类型下所有文章(分页查询)
			    var param = {"articletypeid":articletypeid, "nowPage":nowPage};
				$.ajax({
					type:"post",
					url:"<%=basePath%>userViewMain/getArticlesPageByType.do",
					data:param,
					datatype:"json",
					success:function(data){
						data = eval('(' + data + ')');
						var tab = document.getElementById(data.articletypeid);
						tab.innerHTML = data.articeHtmlStr;
						// 进行热门文章、最新文章、收藏最多文章、公告的加载
						hostNewCollGg();
					},
					error:function(data){
						alert("请求失败!");
					}
				});
			}
		}
	}
</script>
</html>
