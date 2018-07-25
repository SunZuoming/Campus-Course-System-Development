<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	session.setAttribute("basePath", basePath);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>  
<head>  
    <title>Title</title>  
</head>  
<body>   
    <hr/>  
    <div style="margin-top: 30%">  
        <span>审核</span>  
        <form action="${pageContext.request.contextPath}/file/fileCheck" method="get"  target="_top" class="form form-horizontal" >  
            <input type="text" class="input-text"   id="filename" name="filename" style="width:250px"  required="required">  
            <input class="btn btn-primary radius" type="submit" id="submit" value="  提交  ">  
        </form>  
    </div>  

  
</body>  
</html>  