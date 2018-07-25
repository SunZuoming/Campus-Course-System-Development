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
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="./css/bootstrap.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="./js/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="./js/bootstrap.min.js"></script>
<style type="text/css">
		.m-top {
		    padding: 25px 0 55px;
		    min-height: 288px;
		    background-color: white;
		}
		.g-doc, .g-flow {
		    width: 960px;
		    margin: 0 auto;
		}
		.g-sd1 {
		    position: relative;
		    float: left;
		    width: 225px;
		    margin-right: -225px;
		}
		.f-pa {
		    position: absolute;
		}

		.m-top .course-enroll-info-wrapper {
		    width: -webkit-calc(100% - 510px - 30px);
		    width: -moz-calc(100% - 510px - 30px);
		    width: calc(100% - 510px - 30px);
		    font-size: 0px;
		}
		.g-mn1 {
		    float: right;
		    width: 100%;
		}
		.m-top .course-enroll-info-wrapper .course-title-wrapper {
		    max-width: 500px;
		}
		.f-fl {
		    float: left;
		}
		.m-top .course-enroll-info-wrapper .course-title-wrapper .course-title {
		    font-size: 24px;
		    color: #333333;
		    margin-right: 6px;
		}
		.f-vam {
		    vertical-align: middle;
		}
		.f-ib {
		    display: inline-block;
		    zoom: 1;
		}
		.f-fcf {
		    color: #fff;
		}
		.f-f0, .term-password-modal {
			font-family: "Microsoft YaHei", \5fae\8f6f\96c5\9ed1, "Helvetica", "sans-serif";
		}
		.m-top .course-enroll-info-wrapper #course-enroll-info {
		    margin-top: 10px;
		    max-width: 540px;
		}
		.course-enroll-info_course-enroll {
		    font-size: 13px;
		    color: #999999;
		}
		.course-enroll-info_course-enroll_buttons_enroll-btn {
		    margin-bottom: 10px;
		}
		.course-enroll-info_course-enroll_buttons_enroll-btn .ux-btn {
		    -webkit-border-radius: 2px;
		    -moz-border-radius: 2px;
		    border-radius: 2px;
		    height: 52px;
		    line-height: 52px;
		    font-size: 18px;
		    background-color: #ff7a3e;
		    border-color: #ff7a3e;
		}
		.ux-btn {
		    -webkit-appearance: none;
		    margin: 0;
		    overflow: visible;
		    text-transform: none;
		    text-decoration: none;
		    cursor: pointer;
		    -webkit-box-sizing: border-box;
		    -moz-box-sizing: border-box;
		    box-sizing: border-box;
		    display: inline-block;
		    vertical-align: middle;
		    text-align: center;
		    padding: 0 12px;
		    height: 34px;
		    line-height: 34px;
		    border-width: 1px;
		    border-style: solid;
		    font-size: 14px;
		    border-radius: 2px;
		}
		.ux-btn-w220 {
		    width: 220px;
		}
		.ux-btn, .th-bk-main {
		    color: white;
		}
		.g-mn2 {
		    float: left;
		    width: 100%;
		}
		.m-infomation {
		    margin-right: 50px;
		    padding: 0 41px 1px;
		}
		.m-infomation_content-section {
		    padding-top: 30px;
		}
		.m-infomation .category-title {
		    padding: 0 0 10px 0;
		    font-size: 18px;
		    font-weight: bold;
		}
		.f-f0, .term-password-modal {
		    font-family: "Microsoft YaHei", \5fae\8f6f\96c5\9ed1, "Helvetica", "sans-serif";
		}
		.m-infomation .category-title_icon {
		    font-size: 16px;
		    color: #56B929;
		    margin-right: 5px;
		}
		.f-vam {
		    vertical-align: middle;
		}
		.m-infomation .category-content {
		    margin-bottom: 50px;
		}
		.j-cover-overflow {
		    position: relative;
		}
		.f-richEditorText {
		    margin: 0;
		    padding: 0;
		    border: 0;
		    text-align: left;
		    color: #666;
		    line-height: 22px;
		    word-break: break-word;
		    word-wrap: break-word;
		}
	</style>


</head>
<body>
<a href="${basePath}userMainPage/getFirstPage.do" style="text-decoration:none"><font color="blue" size="4">回到首页</font></a>
<div style = "width: 60%; margin: 0 auto" class="main">
		<div class="m-top f-cb f-pr f-f0">
			<div class="g-flow">
				<div class="g-sd1 video-intro">
					<div id="j-courseImg" class="m-recimg canlick">
						<img class="img" src="images/KC.jpg" width="510" height="288" alt="数据挖掘：理论与算法">
					</div>
				</div>
				<div class="g-mn1 course-enroll-info-wrapper">
					<div class="title-wrapper">
						<div class="f-cb f-pr">
							<div class="f-fl course-title-wrapper">
								<span class="course-title f-ib f-vam"> ${pric.coname}</span>
								<span id="j-teacherAssistMark" class="f-fcf f-f0 taMark" style="display:none;"></span>
							</div>
							<div class="f-fr f-f0 f-fs1 course-share f-pa" id="j-coushare" style="margin-top: 200px;">
								<a href="${bashpath}course/inpricourse.do">
									<div class="course-enroll-info_course-enroll_buttons_enroll-btn">
										<span class="ux-btn  ux-btn- ux-btn-w220">${pric.price}&nbsp;加入关注</span>
									</div>
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="g-wrap f-cb">
			<div class="g-mn2">
				<div class="g-mn2c f-bgw m-infomation f-f0">
					<div id="content-section" class="m-infomation_content-section">
						<div class="category-title f-f0">
						<span class="category-title_icon f-ib f-vam u-icon-categories"></span>
						<span class="f-ib f-vam">课程概述</span>
						</div>
						<div class="category-content j-cover-overflow">
							<div class="f-richEditorText">
								<p>${pric.coinfo}</p>
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>