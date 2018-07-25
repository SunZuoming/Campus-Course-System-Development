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
    <title></title>
    <meta name="description" content="">
    <meta name="author" content="templatemo">
    <script src="${basePath }/srcAdmin/js/jquery-3.2.1.js" type="text/javascript"></script>
    <script src="${basePath }/srcAdmin/js/bootstrap.js" type="text/javascript"></script>
    <script src="${basePath }/srcAdmin/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${basePath }/srcAdmin/js/layer.js" type="text/javascript"></script>
    <script type="text/javascript" src="${basePath }/srcAdmin/js/templatemo-script.js"></script>
    <script src="${basePath }/srcAdmin/js/echarts.js" type="text/javascript"></script>
    <link href="${basePath }/srcAdmin/css/bootstrap.css" rel="stylesheet"/>
    <link href="${basePath }/srcAdmin/css/bootstrap-theme.css" rel="stylesheet" />
    <link href="${basePath }/srcAdmin/css/font-awesome.min.css" rel="stylesheet">
    <link href="${basePath }/srcAdmin/css/templatemo-style.css" rel="stylesheet">
    <script type="text/javascript">
        function onloadReq() {
        	window.location="${basePath}pages/admin/showMainData.do";
        }
        function showReason(data) {
            $("#teacherNo").val(data);
            $("#reasonDiv").show();
        }
        var reasonFlag = true;
        $(function () {
            $("#reason").focus(function () {
                $("#reason").attr("rows","5");
            });
        })
        $(function (){
        	$("#reason").blur(function () {
                if(trim($("#reason").val())==""||$("#reason").val()==null){
                    $("#errorInfo").show();
                    reasonFlag = false;
                }else {
                    $("#errorInfo").hide();
                    reasonFlag = true;
                }
            });
        })
        function submitReason(){
            if(reasonFlag == true){
                $.ajax({
                    type:"post",
                    url:"${basePath}pages/admin/delComTeacher.do",
                    data:{
                        "userNo":$("#teacherNo").val(),
                        "reason":$("#reason").val()
                    },
                    success:function (data) {
                    	data = eval('(' + data + ')');
                        if(data.retCode == "111"){
                            window.location = "${basePath}pages/admin/showMainData.do";
                        }else{
                            alert(data.retMsg);
                        }
                    },
                    error:function (data) {
                        alert("系统繁忙，请稍后再试！")
                    }
                })
            }
        }
        
        function displayReason(){
        	$("#teacherNo").val("");
        	$("#reason").val("");
        	$("#reason").attr("rows","3");
            $("#reasonDiv").hide();
        }
    </script>
</head>
<body class="light-gray-bg" onload='<c:if test="${flag==null}">
										onloadReq()
									</c:if>'							
><!--  -->
<div class="col-1">
    <div class="panel panel-default templatemo-content-widget white-bg no-padding templatemo-overflow-hidden">
        <i class="fa fa-times"></i>
        <div class="panel-heading templatemo-position-relative">
        	<h2 class="text-uppercase text-left text-primary">
                	未审核教师总数：<label class="label label-info">${teacherCount }</label>
            </h2>
            <h2 class="text-uppercase text-center text-primary">未审核教师表</h2>
        </div>
        <div class="table-responsive">
            <form>
                <table class="table table-striped table-bordered">
                    <thead >
                    <tr>
                        <td style="width: 10%">编号</td>
                        <td style="width: 15%">教师姓名</td>
                        <td style="width: 25%">教师电话</td>
                        <td style="width: 25%">教师邮箱</td>
                        <td style="width: 25%">确认</td><!-- style="width: 150px" -->
                    </tr>
                    </thead>
                    <tbody>
                    	<c:forEach items="${teacherList }" var="t" varStatus="g">
                    		<tr>
	                            <td>${g.count }</td>
	                            <td>${t.userName }</td>
	                            <td>${t.userPhone }</td>
	                            <td>${t.email }</td>
	                            <td>
	                            	<a href="${basePath }pages/admin/comTeacher.do?userNo=${t.userNo}" style="width: 8%" class="templatemo-blue-button">
	                            		通过
	                            	</a>&nbsp;&nbsp;&nbsp;
	                            	<a  onclick="showReason('${t.userNo}')" style="width: 8%" class="templatemo-blue-button">
	                            		删除
	                            	</a>
	                            </td>
                        	</tr>
                    	</c:forEach>
                    </tbody>
                </table>
            </form>
        </div>
    </div>
</div>
<div class="col-1" style="display: none" id="reasonDiv">
    <input type="hidden" id="teacherNo">
    <div class="col-lg-12 col-md-12">
        <textarea style="width: 100%;" class="no-border" aria-multiline="true" cols="100%" rows="3" id="reason" name="reason">

        </textarea>
    </div>
    <div class="col-md-12 col-lg-12 text-center">
        <div class="col-lg-offset-5 col-md-offset-5 text-center">
            <input type="button" class="templatemo-blue-button col-lg-2 col-md-2 text-center" value="确认" onclick="submitReason()">&nbsp;&nbsp;
            <input type="button" class="templatemo-blue-button col-lg-2 col-md-2 text-center" value="取消" onclick="displayReason()">
        </div>
        <div class="col-md-3 col-lg-3" style="display: none" id="errorInfo">
            <p style="color: red">原因不可为空</p>
        </div>
    </div>
</div>
<div class="col-1">
    <div class="panel panel-default templatemo-content-widget white-bg no-padding templatemo-overflow-hidden">
        <i class="fa fa-times"></i>
        <div class="panel-heading templatemo-position-relative">
            <h2 class="text-uppercase text-primary">数据统计</h2>
        </div>

        <div id="charts" style="width: 500px;height:400px;float: left;border: white 1px solid" class="container">
        </div>
        <script type="text/javascript">
            var myChart = echarts.init(document.getElementById('charts'));
            var dataAxis = ["文章", '评论', '问题', '回答', '文件','收藏','作业'];
            var data = ['${dataDTO.articleNum}', 
            			'${dataDTO.commentNum}', 
            			'${dataDTO.questionNum}', 
            			'${dataDTO.answerNum}', 
            			'${dataDTO.fileNum}',
            			'${dataDTO.collectionNum}',
            			'${dataDTO.homeworkNum}'];
            var yMax = 200;
            var dataShadow = [];

            for (var i = 0; i < data.length; i++) {
                dataShadow.push(yMax);
            }

            option = {
                title: {
                    text: '用户统计',
                    subtext: ''
                },
                xAxis: {
                    data: dataAxis,
                    axisLabel: {
                        inside: true,
                        textStyle: {
                            color: '#fff'
                        }
                    },
                    axisTick: {
                        show: false
                    },
                    axisLine: {
                        show: false
                    },
                    z: 10
                },
                yAxis: {
                    axisLine: {
                        show: false
                    },
                    axisTick: {
                        show: false
                    },
                    axisLabel: {
                        textStyle: {
                            color: '#999'
                        }
                    }
                },
                dataZoom: [
                    {
                        type: 'inside'
                    }
                ],
                series: [
                    { // For shadow
                        type: 'bar',
                        itemStyle: {
                            normal: {color: 'rgba(0,0,0,0.05)'}
                        },
                        barGap:'-100%',
                        barCategoryGap:'40%',
                        data: dataShadow,
                        animation: false
                    },
                    {
                        type: 'bar',
                        itemStyle: {
                            normal: {
                                color: new echarts.graphic.LinearGradient(
                                    0, 0, 0, 1,
                                    [
                                        {offset: 0, color: '#83bff6'},
                                        {offset: 0.5, color: '#188df0'},
                                        {offset: 1, color: '#188df0'}
                                    ]
                                )
                            },
                            emphasis: {
                                color: new echarts.graphic.LinearGradient(
                                    0, 0, 0, 1,
                                    [
                                        {offset: 0, color: '#2378f7'},
                                        {offset: 0.7, color: '#2378f7'},
                                        {offset: 1, color: '#83bff6'}
                                    ]
                                )
                            }
                        },
                        data: data
                    }
                ]
            };

            myChart.setOption(option);
            // Enable data zoom when user click bar.
            var zoomSize = 6;
            myChart.on('click', function (params) {
                console.log(dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)]);
                myChart.dispatchAction({
                    type: 'dataZoom',
                    startValue: dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)],
                    endValue: dataAxis[Math.min(params.dataIndex + zoomSize / 2, data.length - 1)]
                });
            });
        </script>
        <div id="charts2" style="width: 500px;height: 400px;float: left">
        </div>
        <script type="text/javascript">
            var myChart2 = echarts.init(document.getElementById('charts2'));
            option2 = {
            		tooltip: {
            	        trigger: 'item',
            	        formatter: "{a} <br/>{b}: {c} ({d}%)"
            	    },
            	    legend: {
            	        orient: 'vertical',
            	        x: 'left',
            	        data:['公告','举报','警告','封号']
            	    },
            	    series: [
            	        {
            	            name:'操作选项',
            	            type:'pie',
            	            radius: ['50%', '70%'],
            	            avoidLabelOverlap: false,
            	            label: {
            	                normal: {
            	                    show: false,
            	                    position: 'center'
            	                },
            	                emphasis: {
            	                    show: true,
            	                    textStyle: {
            	                        fontSize: '30',
            	                        fontWeight: 'bold'
            	                    }
            	                }
            	            },
            	            labelLine: {
            	                normal: {
            	                    show: false
            	                }
            	            },
            	            data:[
            	                {value:'${dataDTO.noticeNum}', name:'公告'},
	                            {value:'${dataDTO.reportNum}', name:'举报'},
	                            {value:'${dataDTO.warningNum}', name:'警告'},
	                            {value:'${dataDTO.prohibitNum}', name:'封号'}
            	            ]
            	        }
            	    ]
            };
            myChart2.setOption(option2);
        </script>
    </div>
</div>
</body>
</html>