<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	session.setAttribute("basePath", basePath);
%>
<%
  String id=request.getParameter("id");
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    
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
        <script src="${basePath }/srcAdmin/js/jquery-3.2.1.js" type="text/javascript"></script>
        <script src="${basePath }/srcAdmin/js/jquery-ui.js" type="text/javascript"></script>
        <link rel="stylesheet" href="${basePath }/css1/x-admin.css" media="all">
        <link href="${basePath }/srcAdmin/css/jquery-ui.css" rel="stylesheet">
        <link rel="stylesheet" href="${basePath }/layui/css/layui.css" media="all">
        <script type="text/javascript">
        $(function () {
	        $("#startDate").datepicker({
	                    dateFormat:"yy-mm-dd"});
	        $("#endDate").datepicker({
	                dateFormat:"yy-mm-dd"});
	    })
	    function back(){
        	window.history.back();
        }
        </script>
    </head>
    
    <body>
        <div class="x-body">
            <form class="layui-form" action="${pageContext.request.contextPath }/notice/updateNotice.do" method="get">
                <input type="hidden"  value="${notices.noticeid}" name="noticeid"/>
                <div class="layui-form-item">
                    <label for="L_email" class="layui-form-label">
                        	公告内容
                    </label>
                   <!--  <div class="layui-input-inline"> -->
	                    <textarea style="width: 50%;" class="no-border" aria-multiline="true" cols="100%" rows="3" id="noticecontent" name="noticecontent">
							${notices.notcecontent}
        				</textarea>
                        <%-- <input type="text"  name="noticecontent" value="${notices.notcecontent}" class="layui-input"> --%>
                   <!--  </div> -->
                </div>
            
                <div class="layui-form-item">
                    <label for="L_city" class="layui-form-label">
                        	公告发布人
                    </label>
                    <div class="layui-input-inline">
                        <input type="text"  name="noticepublisher"  value="${notices.noticepublisher.userName}" id="L_city" 
                        class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item ">
                    <label for="L_city" class="layui-form-label">
                       	开始时间
                    </label>
                    <div class="layui-input-inline">
                         <input type="text" readonly="readonly" name="noticestarttime" value="${notices.noticestartdate}"  id="startDate" 
                        class="layui-input">
                    </div>
                     <div class="layui-form-item ">
                    <label for="L_city" class="layui-form-label">
                      	 结束时间
                    </label>
                    <div class="layui-input-inline">
                         <input type="text"  id="endDate" name="noticeendtime" value="${notices.noticeenddate}"
                        class="layui-input">
                    </div>
                </div>
                 <div class="layui-form-item ">
                    <label for="L_city" class="layui-form-label">
                       	发起时间
                    </label>
                    <div class="layui-input-inline">
                         <input type="text"  id="L_city" readonly="readonly" name="noticepublishtime" value="${notices.noticepublisherdate}"
                        class="layui-input">
                    </div>
                    <div class="layui-form-item">
                    <label for="L_username" class="layui-form-label">
                    	 公告状态
                    </label>
                    <div class="layui-inline">
                        <div class="layui-input-inline">
                            
                      		<c:if test="${notices.noticeUpdateFlag == '0' }">
                      			<input type="text" readonly="readonly" name = "updateFlag" id="updateFlag" value="未公布，可修改">
                      		</c:if>
				        	<c:if test="${notices.noticeUpdateFlag == '1' }">
				        		<input type="text" readonly="readonly" name = "updateFlag" id="updateFlag" value="公布中，不可修改">
				        	</c:if>
				        	<c:if test="${notices.noticeUpdateFlag == '2' }">
				        		<input type="text" readonly="readonly" name = "updateFlag" id="updateFlag" value="已结束，不可修改">
				        	</c:if>
				        	<c:if test="${notices.noticeUpdateFlag == '3' }">
				        		<input type="text" readonly="readonly" name = "updateFlag" id="updateFlag" value="已结束，不可修改">
				        	</c:if>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
	                <c:if test="${notices.noticeUpdateFlag == '0' }">
	                	<label for="L_sign" class="layui-form-label">
	                    </label>
	                    <button class="layui-btn" key="set-mine" type="submit" lay-filter="save" lay-submit>
	                        	提交
	                    </button>
	                </c:if>
	                &nbsp;&nbsp;&nbsp;
	                <label for="L_sign" class="layui-form-label">
	                    </label>
	                    <button class="layui-btn" key="set-mine" onclick="back()" lay-filter="save" lay-submit>
	                        	返回
	                    </button>
                </div>
              </form>
        </div>
            
            
        <script src="${basePath }/layui/layui.js" charset="utf-8">
        </script>
        <script src="${basePath }/js1/x-layui.js" charset="utf-8">
        </script>
        <script>
            layui.use(['form','layer'], function(){
                $ = layui.jquery;
              var form = layui.form()
              ,layer = layui.layer;
            
             
              //监听提交
              form.on('submit(save)', function(data){
                console.log(data);
                
                return true;
              });
              
              
            });
        </script>
        <script>
        var _hmt = _hmt || [];
        (function() {
          var hm = document.createElement("script");
          var s = document.getElementsByTagName("script")[0]; 
          s.parentNode.insertBefore(hm, s);
        })();
        </script>
    </body>

</html>