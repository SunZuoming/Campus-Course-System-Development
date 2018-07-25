<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>
    <script src="${basePath }/srcAdmin/js/jquery-3.2.1.js" type="text/javascript"></script>
    <script src="${basePath }/srcAdmin/js/bootstrap.js" type="text/javascript"></script>
    <script src="${basePath }/srcAdmin/js/bootstrap.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${basePath }/srcAdmin/js/templatemo-script.js"></script>
    <link href="${basePath }/srcAdmin/css/bootstrap.css" rel="stylesheet"/>
    <link href="${basePath }/srcAdmin/css/bootstrap-theme.css" rel="stylesheet" />
    <link href="${basePath }/srcAdmin/css/font-awesome.min.css" rel="stylesheet">
    <link href="${basePath }/srcAdmin/css/templatemo-style.css" rel="stylesheet">
    <script type="text/javascript">
    	<%String backFlag = (String)request.getParameter("backFlag");%>
        function closeForm() {
        	var s = '${backFlag}';
        	if(s == 'home'){
        		window.location  = "${basePath}pages/mainFrame.jsp";
        	}else if(s=='login'){
        		window.parent.location = "${basePath}pages/adminLogin.jsp";
        	}else {
        		window.history.back();
        	}
        }
    </script>
</head>
<body class="light-gray-bg">
    <div class="templatemo-content-widget ">
        <div class="col-sm-3"></div>
        <div class="text-center col-sm-6 " >
            <table class="table border-radius-10 white-bg">
                <thead class="">
                    <p class="text-center text-info">提示信息</p>
                </thead>
                <tbody >
                    <tr>
                        <td>${message }</td>
                    </tr>
                    <tr>
                        <td class="text-center">
                            <button class="btn templatemo-edit-btn" onclick="closeForm()">确定</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>