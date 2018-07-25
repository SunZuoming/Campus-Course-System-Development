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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
        $(function () {
            $("#startDate").datepicker({
                        minDate: "-10Y",
                        changeMonth: true,
                        changeYear: true,
                        maxDate:-1,
                        dateFormat:"yy",
                        showOtherMonths: true,
                        selectOtherMonths: true});
            $("#endDate").datepicker({
                    minDate: "-10Y",
                    changeMonth: true,
                    changeYear: true,
                    maxDate:-1,
                    dateFormat:"yy",
                    showOtherMonths: true,
                    selectOtherMonths: true});
        })

        function selectAll() {
            var all = document.getElementById("all");
            /* var pdf = document.getElementById("pdf"); */
            var html = document.getElementById("html");
            var xls = document.getElementById("xls");
            if(all.checked == true){
                /* pdf.checked = true; */
                html.checked = true;
                xls.checked = true;
            }
            if(all.checked==false){
                /* pdf.checked = false; */
                html.checked = false;
                xls.checked = false;
            }
        }
        function checkForm() {
            var date1 = $("#startDate").val();
            var date2 = $("#endDate").val();
            var all = document.getElementById("all");
            /* var pdf = document.getElementById("pdf"); */
            var html = document.getElementById("html");
            var xls = document.getElementById("xls");
            var d1 = new Date(date1);
            var d2 = new Date(date2);
            if(d1.getTime() > d2.getTime()){
                $("#startDateErr").html("起始年份大于结束年份");
                return false;
            }else {/* pdf.checked == false&& */
            	if(html.checked == false&&xls.checked == false){
            		$("#format").html("至少选择一种格式");
            		return false;
            	}else{
            		$("#startDateErr").html("");
            		$("#format").html("");
                    $("#yearReportForm").submit();
                    return true;
            	}
                
            }

        }
    </script>
</head>
<body class="light-gray-bg">
<div class="col-md-7 col-lg-7 col-lg-offset-3 col-md-offset-3">

    <div class="panel panel-default templatemo-content-widget white-bg no-padding templatemo-overflow-hidden">
        <form id="yearReportForm" action="${basePath }pages/admin/yearReport.do" method="post">
            <div class="panel-heading templatemo-position-relative panel-info">
                <h2 class=" text-center text-primary">年操作统计报表</h2>
            </div>
            <div class="panel-body">
                <div class="col-md-12 col-lg-12">
                    <div class="col-lg-8 col-md-8">
                        <div class="col-md-4 col-lg-4 text-right">
                            <p>起始年份：</p>
                        </div>
                        <div class="col-lg-4 col-md-4 text-left">
                            <input type="text" name="startDate" id="startDate" placeholder="选择该年任意一天代表该年">
                        </div>
                    </div>
                </div>
                <div class="col-md-12 col-lg-12" style="height: 20px">
                    <div class="col-md-4 col-lg-4 text-right">
                    </div>
                    <div class="col-lg-2 col-md-2 text-left">
                        <label id="startDateErr" class="label label-danger"></label>
                    </div>
                </div>
                <div class="col-md-12 col-lg-12">
                    <div class="col-lg-8 col-md-8">
                        <div class="col-md-4 col-lg-4 text-right">
                            <p>结束年份：</p>
                        </div>
                        <div class=" col-lg-4 col-md-4 text-left">
                            <input type="text" name="endDate" id="endDate" placeholder="结束年份应大于起始年份">
                        </div>

                    </div>
                </div>
                <div class="col-md-12 col-lg-12" style="height: 20px">
                    <div class="col-md-4 col-lg-4 text-right">
                    </div>
                    <div class="col-lg-2 col-md-2 text-left">
                        <label id="endDateErr" class="label label-danger"></label>
                    </div>
                </div>
                <div class="col-md-12 col-lg-12">
                    <div class="col-lg-8 col-md-8">
                        <div class="col-md-4 col-lg-4 text-right">
                            <p>用户编号：</p>
                        </div>
                        <div class=" col-lg-4 col-md-4 text-left">
                            <input type="text" name="userNo" value="" placeholder="为空时，查询所有用户">
                        </div>
                    </div>
                </div>
                <div class="col-md-12 col-lg-12" style="height: 20px">
                </div>
                <div class="col-md-12 col-lg-12">
                    <div class="col-lg-12 col-md-12">
                        <div class="col-md-2 col-lg-2 text-right">
                            <p>格式：</p>
                        </div>
                        <div class=" col-lg-3 col-md-3 text-center">
                            <input type="checkbox" name="allSelect" id="all" onclick="selectAll()">
                            <label for="all"><span></span>全选</label>
                        </div>
                        <!-- <div class=" col-lg-2 col-md-2 text-center">
                            <input type="checkbox" name="PDF" id="pdf" >
                            <label for="pdf"><span></span>PDF</label>
                        </div> -->
                        <div class=" col-lg-3 col-md-3 text-center">
                            <input type="checkbox" name="HTML" id="html">
                            <label for="html"><span></span>HTML</label>
                        </div>
                        <div class=" col-lg-3 col-md-3 text-center">
                            <input type="checkbox" name="XLS" checked="checked" id="xls">
                            <label for="xls"><span></span>XLS</label>
                        </div>
                    </div>
                </div>
                <div class="col-md-12 col-lg-12" style="height: 20px">
                	<div class="col-md-4 col-lg-4 text-right">
                    </div>
                    <div class="col-lg-2 col-md-2 text-left">
                        <label id="format" class="label label-danger"></label>
                    </div>
                </div>
            </div>
            <div class="panel-footer">
                <div class="form-group" style="text-align: center">
                    <button type="button" id="button1" onclick="checkForm()" class="templatemo-blue-button">确定</button>&nbsp;&nbsp;
                    <button type="reset" id="button2" class="templatemo-blue-button">取消</button>&nbsp;&nbsp;
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>