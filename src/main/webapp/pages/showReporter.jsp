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
    <title></title>
    <meta name="description" content="">
    <meta name="author" content="templatemo">
    <script src="${basePath }/srcAdmin/js/jquery-3.2.1.js" type="text/javascript"></script>
    <script src="${basePath }/srcAdmin/js/bootstrap.js" type="text/javascript"></script>
    <script src="${basePath }/srcAdmin/js/bootstrap.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${basePath }/srcAdmin/js/templatemo-script.js"></script>
    <script src="${basePath }/srcAdmin/js/jqPaginator.js" type="text/javascript"></script>
    <script src="${basePath }/srcAdmin/js/echarts.js" type="text/javascript"></script>
    <link href="${basePath }/srcAdmin/css/bootstrap.css" rel="stylesheet"/>
    <link href="${basePath }/srcAdmin/css/bootstrap-theme.css" rel="stylesheet" />
    <link href="${basePath }/srcAdmin/css/font-awesome.min.css" rel="stylesheet">
    <link href="${basePath }/srcAdmin/css/templatemo-style.css" rel="stylesheet">
    <script type="text/javascript">
    	function back(){
    		window.history.back();
    	}
    </script>
</head>
<body class="light-gray-bg">
<div class="templatemo-flex-row flex-content-row col-lg-12" >
	<form action="" id="reportForm" method="post">
	    <div class="col-1">
	        <div class="panel panel-default margin-10">
	            <div class="panel-heading panel-info">
	                <h2 class="text-left text-primary" >
	                	<c:if test="${repFlag == '1' }">
	                		举报人信息
	                	</c:if>
	                	<c:if test="${repFlag == '0' }">
	                		被举报人信息
	                	</c:if>
	                </h2>
	            </div>
	            <div class="panel-body">
	                <div class="form-group col-md-12 col-lg-12">
	                	<div class="col-md-3 col-lg-3 text-right">
	                		<p>姓名：</p>
	                    </div>
	               	    <div class="col-lg-2 col-md-2 text-left">
	        	            <input type="text" class="form-control" readonly="readonly" id="userName" name="userName" value="${reporterDTO.reporter.userName }"/>
	                    </div>
	                    <div class="col-md-3 col-lg-3 text-right">
	                        <p>系统评分：</p>
	                    </div>
	                    <div class="col-lg-2 col-md-2 text-left">
	            	        <input type="text" class="form-control" readonly="readonly" id="sysScore" name="sysScore" value="${reporterDTO.reporter.userSystemscore }"/>
	                    </div>
	       	        </div>
	                <div class="form-group col-lg-12 col-md-12">
	                     <div class="text-right col-lg-3 col-md-3">
	                        <p>警告次数：</p>
	                     </div>
	                     <div class="text-left col-md-2 col-lg-2">
	                        <input type="text" class="form-control" readonly="readonly" id="warningNum" name="warningNum" value="${reporterDTO.reporter.warningNum }"/>
	                     </div>
	                     <div class="text-right col-lg-3 col-md-3">
	                        <p>被举报次数：</p>
	                     </div>
	                     <div class="text-left col-md-2 col-lg-2 no-padding no-border">
	                     	<input type="text" class="form-control" readonly="readonly" id="reportNum" name="reportNum" value="${reporterDTO.reporterNum }">
	                     </div>
	                 </div>
	            </div>
	        </div>
	        <div class="panel panel-default margin-10">
	            <div class="panel-heading panel-info">
	                <h2 class="text-left text-primary" >被举报记录</h2>
	            </div>
	        </div>
	        <div class="panel panel-default templatemo-content-widget white-bg no-padding templatemo-overflow-hidden">
	            <i class="fa fa-times"></i>
	            <div class="table-responsive">
	                    <table class="table table-striped table-bordered">
	                        <thead class="blue-bg" >
		                        <tr>
		                            <td>编号</td>
		                            <td>举报人</td>
		                            <td>举报作品</td>
		                            <td>举报原因</td>
		                            <td>举报是否结束</td>
		                        </tr>
	                        </thead>
	                        <tbody>
	                        	<c:forEach items="${reportList }" var="r" varStatus="g">
	                        		<tr>
	                        			<td>${g.count }</td>
	                        			<td>${r.reporter.userName }</td>
	                        			<td>${r.reportgoodsno }</td>
	                        			<td>${r.reportreason }</td>
	                        			<td>
	                        				<c:if test="${r.reportfinishflag == '1'}">
	                        					已结束
	                        				</c:if>
	                        				<c:if test="${r.reportfinishflag == '0' }">
	                        					未结束
	                        				</c:if>
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
	                            var reportForm = document.getElementById('reportForm');
	                            if(type == 'change'){
	                            	var url='${basePath}/pages/admin/showUser.do?num='+num+'&userNo='+${reporterDTO.reporter.userNo}+'&repFlag='+${repFlag};
	                            	reportForm.action = url;
	                            	reportForm.submit();
	                            }
	                        }
	                    });
	                </script>
	            </div>
	        </div>
	        <div class="panel panel-default margin-10 no-border no-padding">
	            <div class="form-group" style="text-align: right">
	                <button type="button" onclick="back()" class="templatemo-blue-button">返回</button>&nbsp;&nbsp;
	            </div>
	        </div>
	    </div>
    </form>
</div>
</body>
</html>