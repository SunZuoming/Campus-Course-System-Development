<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <script type="text/javascript" src="${basePath }/srcAdmin/js/templatemo-script.js"></script>
    <script src="${basePath }/srcAdmin/js/jqPaginator.js" type="text/javascript"></script>
    <script src="${basePath }/srcAdmin/js/jquery-ui.js" type="text/javascript"></script>
    <link href="${basePath }/srcAdmin/css/bootstrap.css" rel="stylesheet"/>
    <link href="${basePath }/srcAdmin/css/bootstrap.min.css" rel="stylesheet" />
    <link href="${basePath }/srcAdmin/css/bootstrap-theme.css" rel="stylesheet" />
    <link href="${basePath }/srcAdmin/css/bootstrap-theme.min.css" rel="stylesheet" />
    <link href="${basePath }/srcAdmin/css/font-awesome.min.css" rel="stylesheet">
    <link href="${basePath }/srcAdmin/css/templatemo-style.css" rel="stylesheet">
    <link href="${basePath }/srcAdmin/css/jquery-ui.css" rel="stylesheet">
    <script type="text/javascript">
		function displayFileType() {
			if($("#pdftag").val() > 0) {
				$("#pdf").css("display","");
			}
			if($("#htmltag").val() > 0) {
				$("#html").css("display","");
			}
			if($("#xlstag").val() > 0) {
				$("#xls").css("display","");
			}
		}
		function back(){
			url = '${basePath}'+'${url}';
			window.location = url;
		}
    </script>
</head>
<body onload="displayFileType()" class="light-gray-bg">
  	<form name="form1" id="downForm" method="post" >
  		<input type="hidden" id="pdftag" value="${resultMap['pdftag'] }"/>
		<input type="hidden" id="htmltag" value="${resultMap['htmltag'] }"/>
		<input type="hidden" id="xlstag" value="${resultMap['xlstag'] }"/>
		<input type="hidden" id="fileH" value="${resultMap['html'] }"/>
		<input type="hidden" id="fileX" value="${resultMap['xls'] }"/>
		<table width="100%">
			<tr><td height="30" align="center" nowrap><b><c:out value="${title}"/></b></td></tr> 
			<tr>
				<td align="center" valign="top">	  
					<table width="60%" cellspacing=1 cellpadding=0 class="table1">
						<tr class="tr3">
							<td align="center" valign="middle" height="120">
								<%-- <div name="pdf" id="pdf" style="display:none">
									&nbsp;<a href="${basePath }pages/downReportFile.jsp?file='${resultMap['pdf']}'&&title2='${title2}'&&title='${title}'&&property=.pdf" >${title}.pdf</a>
									onclick="JavaScript:download('<c:out value="${fn:replace(pdf, '\\\\','/')}"/>','${title2}','${title2}','pdf')" ><c:out value="${title}"
								</div> --%>
								<p />
								<div name="html" id="html" style="display:none">
									&nbsp;<a id="html" href="${basePath }pages/admin/downReportFile.do?file=${resultMap['html']}&title=${title}&property=.html">${title}.html</a>
								</div>
								<p />
								<div name="xls" id="xls" style="display:none">
									&nbsp;<a id="xls" href="${basePath }pages/admin/downReportFile.do?file=${resultMap['xls']}&title=${title}&property=.xls">${title}.xls</a>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr><td align="center"><input  class="templatemo-blue-button" type="button" value='返回' onClick="back()"></td></tr>
		</table>
  	</form>
  </body>
</html>