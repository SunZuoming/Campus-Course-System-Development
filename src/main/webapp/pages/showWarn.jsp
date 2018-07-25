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
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--<meta name="description" content="">-->
    <meta name="author" content="templatemo">
    <title></title>
    <script src="${basePath }/srcAdmin/js/jquery-3.2.1.js" type="text/javascript"></script>
    <script src="${basePath }/srcAdmin/js/bootstrap.js" type="text/javascript"></script>
    <script src="${basePath }/srcAdmin/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${basePath }/srcAdmin/js/jqPaginator.js" type="text/javascript"></script>
    <script type="text/javascript" src="${basePath }/srcAdmin/js/templatemo-script.js"></script>
    <script src="${basePath }/srcAdmin/js/wangEditor.js" type="text/javascript"></script>
    <link href="${basePath }/srcAdmin/css/bootstrap.css" rel="stylesheet"/>
    <link href="${basePath }/srcAdmin/css/bootstrap.min.css" rel="stylesheet" />
    <link href="${basePath }/srcAdmin/css/bootstrap-theme.css" rel="stylesheet" />
    <link href="${basePath }/srcAdmin/css/bootstrap-theme.min.css" rel="stylesheet" />
    <link href="${basePath }/srcAdmin/css/font-awesome.min.css" rel="stylesheet">
    <link href="${basePath }/srcAdmin/css/templatemo-style.css" rel="stylesheet">
</head>
<body class="light-gray-bg">
    <div class="templatemo-flex-row flex-content-row col-lg-12">
        <div class="col-1">
            <form id="warnForm" method="post" >
            	<input type="hidden" value="${nowPage }" name="nowPage" />
                <div class="panel panel-default">
                    <div class="panel-heading panel-info">
                        <h2 class="text-left text-primary" >警告</h2>
                    </div>
                    <div class="panel-body">
                        <div class="col-md-12 col-lg-12" style="height: 20px">
                            <div class="col-md-3 col-lg-3 text-right">
                                <p>被警告人：</p>
                            </div>
                            <div class="col-lg-2 col-md-2 text-left" >
                                <select name="warnningner" style="width: 150px">
                                    <option value="${reportDTO.reporter.userNo }">举报人-${reportDTO.reporter.userName }</option>
                                    <option value="${reportDTO.reporteer.userNo }">被举报人-${reportDTO.reporteer.userName }</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-12 col-lg-12" style="height: 20px">
                        </div>
                        <div class="col-md-12 col-lg-12" style="height: 20px">
                            <!-- <div class="col-md-3 col-lg-3 text-right">
                                <p>是否删除：</p>
                            </div>
                            <div class="col-lg-2 col-md-2 text-left">
                                <select name="deleteFlag" style="width: 150px">
                                    <option value="Y">是</option>
                                    <option value="N" selected="selected">否</option>
                                </select>
                            </div> -->
                            <div class="col-md-3 col-lg-3 text-right">
                                <p>是否发送邮件提醒：</p>
                            </div>
                            <div class="col-lg-2 col-md-2 text-left">
                                <select name="emailFlag" style="width: 150px">
                                    <option value="Y">是</option>
                                    <option value="N">否</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-12 col-lg-12" style="height: 20px">
                        </div>
                        <div class="col-md-12 col-lg-12" style="height: 20px">
                            <div class="col-md-3 col-lg-3 text-right">
                                <p>举报编号：</p>
                            </div>
                            <div class="col-lg-2 col-md-2 text-left">
                                <input type="text" readonly="readonly" name="reportNo" value="${reportDTO.reportno }">
                            </div>
                            <div class="col-md-3 col-lg-3 text-right">
                                <p>被举报作品编号：</p>
                            </div>
                            <div class="col-lg-2 col-md-2 text-left">
                                <input type="text" readonly="readonly" name="goodsNo" value="${reportDTO.reportgoodsno }">
                            </div>
                        </div>
                        <div class="form-group col-md-12 col-lg-12">
                            <div class="panel-heading panel-info">
                                <h2 class="text-left text-primary" >内容</h2>
                            </div>
                            <div id="editor">
                            </div>
                        </div>
                        <div class="form-group" style="text-align: center">
                            <button type="button" id="button1" class="templatemo-blue-button">确定</button>&nbsp;&nbsp;
                            <button type="reset" id="button2" class="templatemo-blue-button">取消</button>&nbsp;&nbsp;
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <script type="text/javascript">
    	var E = window.wangEditor
    	var editor = new E('#editor')
    	editor.create()
    	$(function () {
            $("#button2").click(function () {
                editor.txt.text("");
            });
        })
        $(function () {
            $("#button1").click(function () {
               var msg = confirm("确认要发出警告吗？");
               if(msg == true){
                   var warnForm = document.getElementById("warnForm");
                   warnForm.action="${basePath}pages/admin/pubWarn.do?content="+editor.txt.text();
                   warnForm.submit();
               }else {

               }
            });
        })
    </script>
</body>
</html>