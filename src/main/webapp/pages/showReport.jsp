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
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--<meta name="description" content="">-->
    <meta name="author" content="templatemo">
    <title></title>
    <script src="${basePath }/srcAdmin/js/jquery-3.2.1.js" type="text/javascript"></script>
    <script src="${basePath }/srcAdmin/js/bootstrap.js" type="text/javascript"></script>
    <script src="${basePath }/srcAdmin/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${basePath }/srcAdmin/js/jqPaginator.js" type="text/javascript"></script>
    <link href="${basePath }/srcAdmin/css/bootstrap.css" rel="stylesheet"/>
    <link href="${basePath }/srcAdmin/css/bootstrap.min.css" rel="stylesheet" />
    <link href="${basePath }/srcAdmin/css/bootstrap-theme.css" rel="stylesheet" />
    <link href="${basePath }/srcAdmin/css/bootstrap-theme.min.css" rel="stylesheet" />
    <link href="${basePath }/srcAdmin/css/font-awesome.min.css" rel="stylesheet">
    <link href="${basePath }/srcAdmin/css/templatemo-style.css" rel="stylesheet">
    <script type="text/javascript">

    </script>
</head>
<body class="light-gray-bg">
<div class="col-1">
    <div class="panel panel-default templatemo-content-widget white-bg no-padding templatemo-overflow-hidden">
        <form id = "makeRepForm" action="" method="post">
            <div class="panel-heading templatemo-position-relative">
                <h2 class="text-uppercase text-left text-primary" >
                    	未处理举报数：<label class="label label-info">${reportCount }</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </h2>
            </div>
            <div class="panel-heading ">
                <h2 class="text-uppercase text-center text-primary"></h2>
            </div>
            <div class="table-responsive">
                <table class="table table-striped table-bordered">
                    <thead >
	                    <tr>
	                        <td>编号</td>
	                        <td>举报人</td>
	                        <td>被举报人</td>
	                        <td>举报原因</td>
	                        <td>被举报作品</td>
	                        <td style="width: 150px">举报处理</td>
	                    </tr>
                    </thead>
                    <tbody>
	                    <c:forEach items="${reportList }" var="r" varStatus="g">
                    		<tr>
	                            <td>${g.count }</td>
	                            <td>
	                            	<a href="${basePath }pages/admin/showUser.do?userNo=${r.reporter.userNo}&repFlag=1&num=1">
	                            		${r.reporter.userName }
	                            	</a>
	                            </td>
	                            <td>
	                            	<a href="${basePath }pages/admin/showUser.do?userNo=${r.reporteer.userNo}&repFlag=0&num=1">
	                            		${r.reporteer.userName }
	                            	</a>
	                            </td>
	                            <td>${r.reportreason }</td>
	                            <td>
	                            	<a href="${basePath }pages/admin/showGoods.do?goodsNo=${r.reportgoodsno}&goodsFlag=${r.reportgoodstype}" >
	                            		${r.reportgoodsno }
	                            	</a>
	                            </td>
	                            <td>
	                            	<a href="${basePath }pages/admin/showWarn.do?reportNo=${r.reportno}&nowPage=${initPage}" class="templatemo-blue-button">
	                            		举报处理
	                            	</a>
	                            </td>
                        	</tr>
                    	</c:forEach>
                    </tbody>
                </table>
            </div>
            <div id="pagesNum" class="text-center">
                <ul id="pagination" class="pagination"></ul>
                <script type="text/javascript">
                    $.jqPaginator('#pagination', {
                        totalPages: ${totalPage},
                        visiblePages: 10,
                        currentPage: ${initPage},
                        prev: '<li class="prev"><a href="javascript:;">Previous</a></li>',
                        next: '<li class="next"><a href="javascript:;">Next</a></li>',
                        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
                        onPageChange: function (num, type) {
                            var comTeaForm = document.getElementById('makeRepForm');
                            if(type == 'change'){
                            	
                            	var url='${basePath}/pages/admin/showRep.do?num='+num;
                            	comTeaForm.action = url;
                            	comTeaForm.submit();
                            }
                        }
                    });
                </script>
            </div>
        </form>
    </div>
</div>
</body>
</html>