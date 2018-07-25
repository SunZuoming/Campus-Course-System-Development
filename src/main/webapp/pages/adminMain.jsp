<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	session.setAttribute("basePath",basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%= basePath %>">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>主页</title>
    <meta name="description" content="">
    <meta name="author" content="templatemo">
    <script src="${basePath }/srcAdmin/js/jquery-3.2.1.js" type="text/javascript"></script>
    <script src="${basePath }/srcAdmin/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${basePath }/srcAdmin/js/layer.js" type="text/javascript"></script>
    <script type="text/javascript" src="${basePath }/srcAdmin/js/templatemo-script.js"></script>
    <link href="${basePath }/srcAdmin/css/bootstrap.css" rel="stylesheet"/>
    <link href="${basePath }/srcAdmin/css/bootstrap-theme.css" rel="stylesheet" />
    <link href="${basePath }/srcAdmin/css/font-awesome.min.css" rel="stylesheet">
    <link href="${basePath }/srcAdmin/css/templatemo-style.css" rel="stylesheet">
    <script type="text/javascript">
	    layer.msg('加载中', {
	        icon: 16
	        ,shade: 0.01
	    });
        
        function showView(obj) {
            var liList = document.getElementsByTagName("L2");
            for(i=0;i<liList.length;i++){
                liList[i].style("display","block");
            }
        }
        function onloadReq() {
            window.location="${basePath}pages/admin/showMainData.do";
        }
    </script>
    <style type="text/css">
        .liDisPlay{
            display: none;
        }
    </style>
</head>
<body >
<!-- Left column -->
<div class="templatemo-flex-row" style="height: 100%">
    <div class="templatemo-sidebar" style="height: 100%">
        <header class="templatemo-site-header">
            <div class="square"></div>
            <h1>欢迎您 ${adminSession.userName }</h1>
        </header>
        <div class="profile-photo-container">
            <img src="${basePath }/srcAdmin/images/profile-photo.jpg" alt="Profile Photo" class="img-responsive">
            <div class="profile-photo-overlay"></div>
        </div>

        <div class="mobile-menu-icon">
            <i class="fa fa-bars"></i>
        </div>
        <nav class="templatemo-left-nav dropdown">
            <ul>
				${sMenu }
				<!-- <li><i class="fa fa-eject fa-fw"></i></li>
				<li><i class="fa fa-eject fa-fw"></i></li> -->
				<!-- <li><a ><i class="fa fa-eject fa-fw"></i></a></li>
				<li><a ><i class="fa fa-eject fa-fw"></i></a></li> -->
                <%-- <li><a href="${basePath}/pages/admin/showTea.do?num=1" target="iframe"><i class="fa fa-database fa-fw"></i>教师身份审核</a></li>
                <li><a href="${basePath }/role/list.do" target="iframe"><i class="fa fa-map-marker fa-fw"></i>权限配置</a></li>
                <li><a href="${basePath }teachercourse/list.do" target="iframe"><i class="fa fa-map-marker fa-fw"></i>课程审核</a></li>
                <c:if test="${adminSession.userNo == 'admin' }">
                	<li><a href="${basePath }pages/admin/showAddView.do" target="iframe"><i class="fa fa-users fa-fw"></i>添加管理员</a></li>
                </c:if>
                <li><a href="${basePath }pages/admin/showPro.do?num=1" target="iframe"><i class="fa fa-eject fa-fw"></i>解除封号</a></li>
                <li><a href="${basePath }pages/changePassword.jsp"><i class="fa fa-sliders fa-fw"></i>修改密码</a></li>
                <li><a href="${basePath }pages/admin/loginout.do"><i class="fa fa-eject fa-fw"></i>退出</a></li> --%>
                <!-- <li><a ><i class="fa fa-eject fa-fw"></i></a></li> -->
            </ul>
        </nav>
    </div>
    <!-- Main content -->
    <div class="templatemo-content col-1 light-gray-bg" >
        <div class="container">
            <div class="row">
                <div class="col-lg-10 col-md-12 ">
                    <ul class="nav nav-tabs">
                    	${hMenu }
                        <%-- <li>
                            <a class="dropdown-toggle" id="l1" data-toggle="dropdown">
                                	公告管理 <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="${basePath }/jsp/notice/New.jsp" target="iframe">创建公告</a></li>
                                <li><a href="${basePath }/notice/getNotice.do" target="iframe">查看公告</a></li>
                                <li><a href="${basePath }/notice/exist.do" target="iframe">关闭公告</a></li>
                            </ul>
                        </li>
                        <li>
                            <a class="dropdown-toggle" id="l2" data-toggle="dropdown" >
                               	 举报 <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu" role="menu" >
                                <li name="L2" ><a href="${basePath }pages/admin/showRep.do?num=1" target="iframe">举报处理</a></li>
                            </ul>
                        </li>
                        <li><a href="${basePath }/file/filelist.do">文件审核</a></li>
                        <li>
                        	<a class="dropdown-toggle" data-toggle="dropdown">
                        		报表<span class="caret"></span>
                        	</a>
                        	<ul class="dropdown-menu" role="menu">
                        		<li><a href="${basePath }pages/dayReport.jsp" target = "iframe">日操作统计报表</a></li>
                        		<li><a href="${basePath }pages/monthReport.jsp" target = "iframe">月操作统计报表</a></li>
                        		<li><a href="${basePath }pages/yearReport.jsp" target = "iframe">年操作统计报表</a></li>
                        	</ul>
                        </li> --%>
                    </ul>
                </div>
            </div>
        </div>
        <div class="container" style="height: 100%">
        <iframe name="iframe" src="${basePath }pages/mainFrame.jsp" 
        	style="width: 100%;height: 90%;border: 0px;padding: 0px" >
        </iframe>
        </div>
    </div>
</div>
</body>
</html>