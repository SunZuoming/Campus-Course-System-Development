<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	session.setAttribute("basePath", basePath);
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
        <script src="${basePath }/srcAdmin/js/wangEditor.js" type="text/javascript"></script>
        <link href="${basePath }/srcAdmin/css/jquery-ui.css" rel="stylesheet">
        <link rel="stylesheet" href="${basePath }/layui/css/layui.css" media="all">
        <script type="text/javascript">
		$(function () {
	        $("#startDate").datepicker({
	                    minDate: +1,
	                    dateFormat:"yy-mm-dd"});
	        $("#endDate").datepicker({
	                minDate: +1,
	                dateFormat:"yy-mm-dd"});
	    })
	   
		$(function() {
			$("#noticecontent").blur(function() {
				console.log("blur");	
				//验证姓名是否唯一
				//发送ajax请求
				$.post("${pageContext.request.contextPath}/notice/validate.do", {
					noticecontent : $(this).val()
				}, function(data) {
					$("#err1").html(data.content)
				}, "json")
			});//失去焦点
		})
		
	</script>
    </head>
    
    <body>
        <div class="x-body">
            <form id="newNoticeForm" class="layui-form" action="${pageContext.request.contextPath}/notice/addnotice.do" method="post">
                
            
               <!--  <div class="layui-form-item" style="margin-left: 9%">
                    <label for="L_city" class="layui-form-label">
                        公告发布人
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" name="noticepublisher" id="L_city " placeholder="请输入发布者"
                        class="layui-input">
                    </div>
                </div> -->
                <div class="layui-form-item" style="margin-left: 9%">
	                <div>
		                <label for="L_email" class="layui-form-label">
		                       	 公告内容
		                </label>
	                </div>
                    
                    <div  id="editor" style="background-color: white;">
                       <!--  <input type="text" id="noticecontent" name="noticecontent" class="layui-input" placeholder="请输入公告内容">
                        <span id="err1" class="layui-input-inline"
						style="color: red"></span></td> -->
						
                    </div>
                </div>
                <div class="layui-form-item " style="margin-left: 9%">
	                <div>
	                 	<label for="L_city" class="layui-form-label">
	                       	开始时间
	                    </label>
	                </div>
                   
                    <div class="layui-icon-date">
                         <input type="text" id="startDate"  name="noticestarttime"  
                        class="layui-input" style="width: 20%">
                    </div>
                  </div>
                    <div class="layui-form-item " style="margin-left: 9%">
                    <div>
	                    <label for="L_city" class="layui-form-label">
	                       	结束时间
	                    </label>
                    </div>
                    
                    <div class="layui-icon-date">
                         <input type="text" id="endDate"  name="noticeendtime"
                        class="layui-input" style="width: 20%">
                    </div>
                </div>
                
                <div class="layui-form-item" style="margin-left: 9%">
                    <label for="L_sign" class="layui-form-label">
                    </label>
                    <button class="layui-btn" key="set-mine" onclick="checkForm()"  >
                        提交
                    </button>
                </div>
              </form>
        </div>
        <script type="text/javascript">
	        var E = window.wangEditor
	    	var editor = new E('#editor')
	    	editor.create()
	    	 function checkForm() {
	            var date1 = $("#startDate").val();
	            var date2 = $("#endDate").val();
	            var d1 = new Date(date1);
	            var d2 = new Date(date2);
	            if(d1.getTime() > d2.getTime()){
	               alert("开始日期大于结束日期");
	                return false;
	            }else if(editor.txt.text()==""||editor.txt.text==null){
	            	alert("公告内容不能为空");
	            	return false;
	            }else {/* pdf.checked == false&& */
	                $("#newNoticeForm").submit();
	                var newNoticeForm = document.getElementById("newNoticeForm");
	                newNoticeForm.action="${pageContext.request.contextPath}/notice/addnotice.do?content="+editor.txt.text();
	                newNoticeForm.submit();
	                return true;
	            }
	        }
        </script>
    </body>

</html>