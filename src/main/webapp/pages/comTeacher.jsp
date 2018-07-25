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
		function showReason(data1,data2) {
            $("#teacherNo").val(data1);
            $("#nowPage").val(data2);
            $("#reasonDiv").show();
        }
        var reasonFlag = true;
        $(function () {
            $("#reason").focus(function () {
                $("#reason").attr("rows","5");
            });
        })
        
        $(function (){
        	$("#reason").blur(function () {
                if(trim($("#reason").val())==""||$("#reason").val()==null){
                    $("#errorInfo").show();
                    reasonFlag = false;
                }else {
                    $("#errorInfo").hide();
                    reasonFlag = true;
                }
            });
        })
        
        function submitReason(){
            if(reasonFlag == true){
                $.ajax({
                    type:"post",
                    url:"${basePath}pages/admin/delComTeacher.do",
                    data:{
                        "userNo":$("#teacherNo").val(),
                        "reason":$("#reason").val()
                    },
                    success:function (data) {
                    	data = eval('(' + data + ')');
                        if(data.retCode == "111"){
                            window.location = "${basePath}/pages/admin/showTea.do?num="+$("#nowPage").val();
                        }else{
                            alert(data.retMsg);
                        }
                    },
                    error:function (data) {
                        alert("系统繁忙，请稍后再试！")
                    }
                })
            }
        }
        function displayReason(){
        	$("#teacherNo").val("");
        	$("#nowPage").val("");
        	$("#reason").val("");
        	$("#reason").attr("rows","3");
            $("#reasonDiv").hide();
        }
    </script>
</head>
<body class="light-gray-bg">
<div class="col-1">
    <div class="panel panel-default templatemo-content-widget white-bg no-padding templatemo-overflow-hidden">
        <form id = "comTeaForm" action="" method="post">
            <div class="panel-heading templatemo-position-relative">
                <h2 class="text-uppercase text-left text-primary" >
                    	未审核教师总数：<label class="label label-info">${teacherCount }</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </h2>
            </div>
            <div class="panel-heading ">
                <h2 class="text-uppercase text-center text-primary">未审核教师表</h2>
            </div>
            <div class="table-responsive">
                <table class="table table-striped table-bordered">
                    <thead >
	                    <tr>
	                        <td style="width: 10%">编号</td>
	                        <td style="width: 15%">教师姓名</td>
	                        <td style="width: 25%">教师电话</td>
	                        <td style="width: 25%">教师邮箱</td>
	                        <td style="width: 25%">确认</td><!-- style="width: 150px" -->
	                    </tr>
                    </thead>
                    <tbody>
	                    <c:forEach items="${teacherList }" var="t" varStatus="g">
                    		<tr>
	                            <td>${g.count }</td>
	                            <td>${t.userName }</td>
	                            <td>${t.userPhone }</td>
	                            <td>${t.email }</td>
	                            <td>
	                            	<a href="${basePath }pages/admin/comTeacher2.do?userNo=${t.userNo}&nowPage=${initPage}" class="templatemo-blue-button">
	                            		通过
	                            	</a>&nbsp;&nbsp;&nbsp;
	                            	<a  class="templatemo-blue-button" onclick="showReason('${t.userNo}','${initPage }')">
	                            		删除
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
                            var comTeaForm = document.getElementById('comTeaForm');
                            if(type == 'change'){
                            	var url='${basePath}/pages/admin/showTea.do?num='+num;
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
<div class="col-1" style="display: none" id="reasonDiv">
    <input type="hidden" id="teacherNo">
    <input type="hidden" id="nowPage">
    <div class="col-lg-12 col-md-12">
        <textarea style="width: 100%;" class="no-border" aria-multiline="true" placeholder="输入审核失败原因" cols="100%" rows="3" id="reason" name="reason">

        </textarea>
    </div>
    <div class="col-md-12 col-lg-12 text-center">
        <div class="col-lg-offset-5 col-md-offset-5 text-center">
            <input type="button" class="templatemo-blue-button col-lg-2 col-md-2 text-center" value="确认" onclick="submitReason()">&nbsp;&nbsp;
            <input type="button" class="templatemo-blue-button col-lg-2 col-md-2 text-center" value="取消" onclick="displayReason()">
        </div>
        <div class="col-md-3 col-lg-3" style="display: none" id="errorInfo">
            <p style="color: red">原因不可为空</p>
        </div>
    </div>
</div>
</body>
</html>