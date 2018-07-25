<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title></title>
<meta name="description" content="">
<meta name="author" content="templatemo">
<script src="${basePath }/srcAdmin/js/jquery-3.2.1.js" type="text/javascript"></script>
<script src="${basePath }/srcAdmin/js/bootstrap.js" type="text/javascript"></script>
<script src="${basePath }/srcAdmin/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${basePath }/srcAdmin/js/templatemo-script.js"></script>
<script src="${basePath }/srcAdmin/js/jqPaginator.js" type="text/javascript"></script>
<script src="${basePath }/srcAdmin/js/echarts.js" type="text/javascript"></script>
<link href="${basePath }/srcAdmin/css/bootstrap.css" rel="stylesheet" />
<link href="${basePath }/srcAdmin/css/bootstrap-theme.css" rel="stylesheet" />
<link href="${basePath }/srcAdmin/css/font-awesome.min.css" rel="stylesheet">
<link href="${basePath }/srcAdmin/css/templatemo-style.css" rel="stylesheet">
</head>
<body class="light-gray-bg">
	<div class="col-1">
		<form id="queryForm" method="post">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="col-lg-12 col-md-12">
						<div class="col-lg-2 col-md-2 text-right">用户编号：</div>
						<div class="col-lg-3 col-md-3 text-left">
							<input type="text" id="userNo" name="userNo" class="form-control">
						</div>
						<div class="col-md-7 col-lg-7 text-right">
							<button onclick="submitQuery()" class="templatemo-blue-button col-md-2 col-lg-2"
								id="select">查询</button>
						</div>
					</div>
				</div>
			</div>

			<div class="panel panel-default templatemo-content-widget white-bg no-padding templatemo-overflow-hidden">
				<div class="panel-heading ">
					<h2 class="text-uppercase text-center text-primary">已封号用户表</h2>
				</div>
				<div class="table-responsive">
					<table class="table table-striped table-bordered">
						<thead>
							<tr>
								<td>序号</td>
								<td>姓名</td>
								<td>封号类型</td>
								<td>封号剩余天数</td>
								<td>封号日期</td>
								<td style="width: 150px">解除封号</td>
							</tr>
						</thead>
						<tbody>
							<c:if test="${forEachFlag == '1' }">
								<c:forEach items="${proDTOList }" var="p" varStatus="g">
									<tr>
										<td>${g.count }</td>
										<td>${p.prohibitLogineer.userName }</td>
										<td>${p.prohibitLoginType }</td>
										<td>${p.prohibitLoginSurplusDays }</td>
										<td>${p.prohibitLoginTime }</td>
										<td><a
											href="${basePath }pages/admin/cancelPro.do?userNo=${p.prohibitLogineer.userNo}&nowPage=${initPage}"
											class="templatemo-blue-button">确认</a>
										</td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${forEachFlag == '0' }">
								<tr>
									<td>1</td>
									<td>${proDTOList.prohibitLogineer.userName }</td>
									<td>${proDTOList.prohibitLoginType }</td>
									<td>${proDTOList.prohibitLoginSurplusDays }</td>
									<td>${proDTOList.prohibitLoginTime }</td>
									<td><a
										href="${basePath }pages/admin/cancelPro.do?userNo=${proDTOList.prohibitLogineer.userNo}&nowPage=${initPage}"
										class="templatemo-blue-button">确认</a>
									</td>
								</tr>
							</c:if>
						</tbody>
					</table>
				</div>
				<div id="pagesNum" class="text-center">
					<ul id="pagination" class="pagination"></ul>
					<script type="text/javascript">
                    $.jqPaginator('#pagination', {
                        totalPages: ${totalPage},
                        visiblePages: 10,
                        currentPage:${initPage},
                        prev: '<li class="prev"><a href="javascript:;">Previous</a></li>',
                        next: '<li class="next"><a href="javascript:;">Next</a></li>',
                        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
                        onPageChange: function (num, type) {
                        	var queryForm = document.getElementById('queryForm');
                            if(type == 'change'){
                            	var url='${basePath}/pages/admin/showPro.do?num='+num;
                            	queryForm.action = url;
                            	queryForm.submit();
                            }
                        }
                    });
                    function submitQuery(){
                    	var queryForm = document.getElementById('queryForm');
                    	var url='${basePath}/pages/admin/showPro2.do?userNo='+$("#userNo").val();
                    	queryForm.action = url;
                    	queryForm.submit();
                    }
                </script>
				</div>
			</div>
		</form>
	</div>
</body>
</html>