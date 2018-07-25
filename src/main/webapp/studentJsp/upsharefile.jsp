<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>共享文件</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<style type="text/css">
	#file2{
	  color:#000066;
	  font-size:20px;
	}
	.file1{
	
	  margin-top:50px;
	}
	.tt11{
     position:absolute;
     left:90%;
     top:20px;
}
	</style>
   <script>
     function Myfile(){
     
     var a=confirm("确定要上传文件吗？");
     if(a==false){
        window.location.href="studentJsp/upsharefile.jsp";
        return false;
     }
     else{
         document.getElementById("file2").innerHTML="文件正在传输.....";
         return true;
     }
     }
   </script>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="css/bootstrap.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="jQuery/jquery-1.8.0.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="js/bootstrap.js"></script>

<meta name="viewport" content="width=device-width, initial-scale=1">
  </head>
  
  <body>
  <a href="teacherJsp/updataimage.jsp"><img width="50" height="50"  
 src="${im.imagedress}" class="tt11 img-circle"></a>
     <center> 
     <br/>  <br/>  <br/> 
        <form action="${bashpath}teacher/upSharedFile.do" method="post" class="file1" 
            enctype="multipart/form-data">  
            <input class="btn btn-default"  type="file" name="file" /><br /> 
            <input class="btn btn-primary" OnClick="return Myfile()" type="submit" value="上 传" />
             <br/><br/>
             <p id="file2">${file1}</p>
        </form>  
       </center>
  </body>
</html>
