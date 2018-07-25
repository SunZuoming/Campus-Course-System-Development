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
        <meta charset="utf-8">
        <title>
            角色管理-添加
        </title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" href="${basePath }/css1/x-admin.css" media="all">
    </head>
    
    <body>
        <div class="x-body">
            <form action="" method="post" class="layui-form layui-form-pane">     
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">
                        拥有的权限
                    </label>
                    <table  class="layui-table layui-input-block">
                        <tbody>
                        
                            <tr>
                                <td width="10%">
                         学生功能        
                                </td>
                                <td width="81%">
                                    <div class="layui-input-block">
                                    <c:forEach items="${ slist}" var="s">
                                    	<input name="id[]" type="checkbox" >${s.functionname}
                                    </c:forEach>       
                                    </div>
                                </td>
                            </tr>
                        
                            <tr>
                                <td>
                                    教师功能
                                </td>
                                <td>
                                    <div class="layui-input-block">
                                        <c:forEach items="${ jlist}" var="s">
                                    	<input name="id[]" type="checkbox" value="2">${s.functionname}
                                    </c:forEach>   
                                    </div>
                                </td>
                            </tr>
                            
                            <tr>
                                <td>
                                    管理员功能
                                </td>
                                <td>
                                    <div class="layui-input-block">
                                       <c:forEach items="${ glist}" var="s">
                                    	<input name="id[]" type="checkbox" value="2">${s.functionname}
                                    </c:forEach>   
                                    </div>
                                </td>
                            </tr>
                            
              
                        </tbody>
                    </table>
                </div>
                <div class="layui-form-item layui-form-text">
                    <label for="desc" class="layui-form-label">
                        备注信息
                    </label>
                    <div class="layui-input-block">
                        <textarea placeholder="内容" id="desc" name="desc" class="layui-textarea"></textarea>
                    </div>
                </div>
                <div class="layui-form-item">
                <button class="layui-btn" lay-submit="" lay-filter="save">保存</button>
                <button class="layui-btn" lay-submit="" lay-filter="cancel">取消</button>
              </div>
            </form>
        </div>
        <script src="${basePath }/lib/layui/layui.js" charset="utf-8">
        </script>
        <script src="${basePath }/js/x-layui.js" charset="utf-8">
        </script>
        <script>
            layui.use(['form','layer'], function(){
                $ = layui.jquery;
              var form = layui.form()
              ,layer = layui.layer;

              //监听提交
              form.on('submit(save)', function(data){
                console.log(data);
                //发异步，把数据提交给php
                layer.alert("保存成功", {icon: 6},function () {
                    // 获得frame索引
                    var index = parent.layer.getFrameIndex(window.name);
                    //关闭当前frame
                    parent.layer.close(index);
                });
                return false;
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