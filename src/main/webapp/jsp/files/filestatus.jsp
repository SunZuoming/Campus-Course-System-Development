<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	session.setAttribute("basePath", basePath);
%>
<%
  String id=request.getParameter("id");
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
        <link rel="stylesheet" href="${basePath }/css1/x-admin.css" media="all">
    </head>
    
    <body>
        <div class="x-body">
            <form class="layui-form" action="${pageContext.request.contextPath }/file/shenhe.do" method="get">
                <input type="hidden" value="${shTable.sharedfileno}" name="sharedfileno"/>
                <input type="hidden" value="${shTable.uploader}" name="uploader"/>
                <div class="layui-form-item">
                    <label for="L_city" >是否违禁(0表示非违禁，1表示违禁，2表示建议人工复审) : <h style="font-size: large;"/>${spam}
                        </label>
                    <div class="layui-input-inline">
                  
                      
                    </div>
                </div>
            
                <div class="layui-form-item">
                    <label for="L_city" class="layui-form-label">
                        违禁类型（labels）值:<h style="font-size: large;"/>${obj}
                    </label>
                    
                </div>
                <div class="layui-form-item ">
                    <label for="L_city" class="layui-form-label">
                       敏感词（hit）:
                    </label>
                    <input name="sense" value='${list }' type="hidden"><h style="font-size: large;" name="sense">${list}</h>
                  </div> 
                  <div class="layui-form-item">
                    <label for="L_email" class="layui-form-label">
                       审核人</label>
                    <div class="layui-input-inline">
                        <input type="text"  name="examiner"  class="layui-input">
                    </div>
                </div>  
                  <div class="layui-form-item">
                    <label for="L_username" class="layui-form-label">
                     审核：
                    </label>
                    <div class="layui-inline">
                        <div class="layui-input-inline">
                            <input type="radio" name="flag" value="0" checked title="不通过">
                            <input type="radio" name="flag" value="1" title="通过">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="L_sign" class="layui-form-label">
                    </label>
                    <button class="layui-btn" key="set-mine" type="submit" lay-filter="save" lay-submit>
                        提交
                    </button>
                </div>
              </form>
        </div>
            
            
        <script src="${basePath }/lib/layui/layui.js" charset="utf-8">
        </script>
        <script src="${basePath }/js1/x-layui.js" charset="utf-8">
        </script>
        <script>
            layui.use(['form','layer'], function(){
                $ = layui.jquery;
              var form = layui.form()
              ,layer = layui.layer;
            
             
              //监听提交
              form.on('submit(save)', function(data){
                console.log(data);
                
                return true;
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