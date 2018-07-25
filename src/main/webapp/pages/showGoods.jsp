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
    <script type="text/javascript" src="${basePath }/srcAdmin/js/templatemo-script.js"></script>
    <script src="${basePath }/srcAdmin/js/wangEditor.js" type="text/javascript"></script>
    <link href="${basePath }/srcAdmin/css/bootstrap.css" rel="stylesheet"/>
    <link href="${basePath }/srcAdmin/css/bootstrap.min.css" rel="stylesheet" />
    <link href="${basePath }/srcAdmin/css/bootstrap-theme.css" rel="stylesheet" />
    <link href="${basePath }/srcAdmin/css/bootstrap-theme.min.css" rel="stylesheet" />
    <link href="${basePath }/srcAdmin/css/font-awesome.min.css" rel="stylesheet">
    <link href="${basePath }/srcAdmin/css/templatemo-style.css" rel="stylesheet">
    <script type="text/javascript">
    	function back(){
    		window.history.back();
    	}
    	
    	$(function(){
            $('[data-toggle = "popover"]').popover();
        })
    </script>
</head>
<body class="light-gray-bg">
<div class="templatemo-flex-row flex-content-row col-lg-12" >
    <div class="col-1">
        <div class="panel panel-default margin-10">
            <div class="panel-heading panel-info">
                <h2 class="text-left text-primary" >被举报作品信息</h2>
            </div>
            <div class="panel-body">
                <div class="form-group col-md-12 col-lg-12">
                    <div class="col-md-3 col-lg-3 text-right">
                        <p>被举报作品类型：</p>
                    </div>
                    <div class="col-lg-2 col-md-2 text-left">
                        <c:if test="${goodsFlag == '0' }">
                        	文章
                        </c:if>
                        <c:if test="${goodsFlag == '1' }">
                        	问题
                        </c:if>
                        <c:if test="${goodsFlag == '2' }">
                        	共享文件
                        </c:if>
                        <c:if test="${goodsFlag == '3' }">
                        	评论
                        </c:if>
                        <c:if test="${goodsFlag == '4' }">
                        	回答
                        </c:if>
                    </div>
                </div>
                <c:if test="${goodsFlag == '0' }">
                	<!--文章-->
	                <div class="form-group col-md-12 col-lg-12">
	                    <div class="col-md-3 col-lg-3 text-right">
	                        <p>文章名：</p>
	                    </div>
	                    <div class="col-lg-2 col-md-2 text-left">
	                        <input type="text" class="form-control" readonly="readonly" value="${articleDTO.articlename }"/>
	                    </div>
	                    <div class="col-md-3 col-lg-3 text-right">
	                        <p>作者：</p>
	                    </div>
	                    <div class="col-lg-2 col-md-2 text-left">
	                        <input type="text" class="form-control" readonly="readonly" value="${articleDTO.articlepublisher.userName }"/>
	                    </div>
	                </div>
                </c:if>
                <c:if test="${goodsFlag == '3' }">
                	<!--评论-->
	                <div class="form-group col-lg-12 col-md-12">
	                    <div class="text-right col-lg-3 col-md-3">
	                        <p>评论人：</p>
	                    </div>
	                    <div class="text-left col-md-2 col-lg-2">
	                        <input type="text" class="form-control" readonly="readonly" value="${commentDTO.commenter.userName }"/>
	                    </div>
	                    <div class="text-right col-lg-3 col-md-3">
	                        <p>评论文章名：</p>
	                    </div>
	                    <div class="text-left col-md-2 col-lg-2 no-padding no-border">
	                        <input type="text" class="form-control" readonly="readonly" value="${commentDTO.article.articlename }">
	                    </div>
	                </div>
                </c:if>
                <c:if test="${goodsFlag == '2' }">
                	<!--文件-->
	                <div class="form-group col-lg-12 col-md-12">
	                    <div class="text-right col-lg-3 col-md-3">
	                        <p>文件名：</p>
	                    </div>
	                    <div class="text-left col-md-2 col-lg-2 no-padding no-border">
	                        <input type="text" class="form-control" readonly="readonly" value="${sharedFileDTO.sharedfilename }">
	                    </div>
	                    <div class="text-right col-lg-3 col-md-3">
	                        <p>文件上传人：</p>
	                    </div>
	                    <div class="text-left col-md-2 col-lg-2 no-padding no-border">
	                        <input type="text" class="form-control" readonly="readonly" value="${sharedFileDTO.uploader.userName }">
	                    </div>
                    </div>
                </c:if>
                <c:if test="${goodsFlag == '1' }">
                	<!--问题-->
	                <div class="form-group col-lg-12 col-md-12">
	                    <div class="text-right col-lg-3 col-md-3">
	                        <p>提问人：</p>
	                    </div>
	                    <div class="text-left col-md-2 col-lg-2 no-padding no-border">
	                        <input type="text" class="form-control" readonly="readonly" value="${questionDTO.questionpublisher.userName }">
	                    </div>
	                </div>
                </c:if>
                <c:if test="${goodsFlag == '4' }">
                	<!--回答-->
	                <div class="form-group col-lg-12 col-md-12">
	                    <div class="text-right col-lg-3 col-md-3">
	                        <p>回答人：</p>
	                    </div>
	                    <div class="text-left col-md-2 col-lg-2 no-padding no-border">
	                        <input type="text" class="form-control" readonly="readonly" value="${answerDTO.answerner.userNo }">
	                    </div>
	                </div>
	                <div class="form-group col-lg-12 col-md-12">
	                    <div class="text-right col-lg-3 col-md-3">
	                        <p>所回答问题：</p>
	                    </div>
	                    <div class="text-left col-md-2 col-lg-2 no-padding no-border">
	                        <input type="text" class="form-control" readonly="readonly" value="${answerDTO.question.questionno }">
	                    </div>
	                    <div class="text-center col-lg-3 col-md-3">
	                    	<a class="templatemo-blue-button width-100" data-toggle="popover"
                    			data-content="${answerDTO.question.questioncontent }" data-placement="left">
               				 问题内容</a>
	                    </div>
	                </div>
                </c:if>
            </div>
        </div>
        <div class="panel panel-default templatemo-content-widget white-bg no-padding templatemo-overflow-hidden">
            <i class="fa fa-times"></i>
            <div class="panel-heading panel-info">
                <h2 class="text-left text-primary" >内容</h2>
            </div>
            <div id="editor">
                <p>
                	<c:if test="${goodsFlag == '0' }">
                       	${articleDTO.articlecontent }
                    </c:if>
                    <c:if test="${goodsFlag == '1' }">
                        ${questionDTO.questioncontent }
                    </c:if>
                    <c:if test="${goodsFlag == '3' }">
                        ${commentDTO.commentcontent }
                    </c:if>
                    <c:if test="${goodsFlag == '4' }">
                        ${answerDTO.answercontent }
                    </c:if>
                </p>
            </div>
            <script type="text/javascript">
                var E = window.wangEditor
                var editor = new E('#editor')
                editor.create()
            </script>
        </div>
        <c:if test="${goodsFlag == '2' }">
        	<div class="panel panel-default templatemo-content-widget white-bg no-padding templatemo-overflow-hidden">
	            <i class="fa fa-times"></i>
	            <div class="panel-heading panel-info">
	                <h2 class="text-left text-primary" >所评论文章内容</h2>
	            </div>
	            <div id="editor2">
	                <p>
	                	${commentDTO.article.articlecontent }
	                </p>
	            </div>
	            <script type="text/javascript">
	                var E = window.wangEditor
	                var editor2 = new E('#editor2')
	                editor2.create()
	            </script>
	        </div>
        </c:if>
        <div class="panel panel-default margin-10 no-border no-padding">
            <div class="form-group" style="text-align: right">
                <button type="button" onclick="back()" class="templatemo-blue-button">返回</button>&nbsp;&nbsp;
            </div>
        </div>
    </div>
</div>
</body>
</html>