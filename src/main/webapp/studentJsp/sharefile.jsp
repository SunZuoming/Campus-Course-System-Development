<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
session.setAttribute("basePath",basePath);
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>课件下载</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="./css/bootstrap.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="./js/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="./js/bootstrap.min.js"></script>
 <script src="${basePath }/srcAdmin/js/jquery-3.2.1.js" type="text/javascript"></script>
    <script src="${basePath }/srcAdmin/js/bootstrap.js" type="text/javascript"></script>
    <script src="${basePath }/srcAdmin/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${basePath }/srcAdmin/js/jqPaginator.js" type="text/javascript"></script>
<style type="text/css">
.pp1 {
	position: relative;
	top: 9.3%;
}
.pg {
	position: absolute;
	left: 300px;
	top: 30px;
}
.pq1{
   position: absolute;
   left: 900px;
}
.tt11{
     position:absolute;
     left:90%;
     top:20px;
}
.l1{
 color:#218868
}
.l2{
 color:#8E388E
}
.l3{
 color:#FFA500
}
.l4{
 color:#009ACD
}
.l5{
 color:#FF4500
}
.lo3{
  position:absolute;
     left:30%;
     
}
</style>
  </head>
  
  <body>
    <!-- Single button -->
	<a href="teacherJsp/updataimage.jsp"><img width="50" height="50"  
 src="${im.imagedress}" class="tt11 img-circle"></a>
	<hr class="pp1">
	<br/><br /><br />
	<div class="lo3">
	<form method="post" action="${bashpath}teacher/listshareFileshou.do?num=1">
	<input id="txtSelect" name="fileto"  type="text" class="easyui-textbox" style="width:300px;height:35px;"> 
	<input type="submit" class="btn btn-success tt5" style="width:100px;height:35px;" value="点击搜索" />
	</form>
	</div>
	<br/><br/><br/>
	<form id = "comTeaForm" action="" method="post">
	<div class="jumbotron">
	<div class="row">
     <div class="col-md-2"></div>
      <div class="col-md-8">
     <!-- 遍历Map集合 --> 
     <table class="table table-hover">
     <tr class="l1">
     <th>上传者</th>
     <th>文件</th>
     <th>上传时间</th>
     <th>下载次数</th>
     <th>操作</th>
     </tr> 
     
     <c:forEach var="f" items="${file}">

        <tr>
        <c:url value="${bashpath}teacher/downSharedFile.do" var="downurl">  
            <c:param name="filename" value="${f.sharedfileurl}"></c:param>  
        </c:url>
        <td class="l2">${f.uploader}</td>
        <td class="l3">${f.sharedfilename}</td>
        <td class="l4">${f.uploadtime}</td>
        <td class="l1">${f.sharedfiledownloadnum}</td>
        <td><a href="${downurl}">下载</a></td>
     </tr>  
    </c:forEach> 
    </table>
    </div>
     <div class="col-md-2"></div> 
     </div>
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
                            	var url='${basePath}/teacher/listshareFileto.do?num='+num;
                            	comTeaForm.action = url;
                            	comTeaForm.submit();
                            }
                        }
                    });
                </script>
            </div>
          </form>
          </body>
          </html>
 
