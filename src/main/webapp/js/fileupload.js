$(document).ready(function() {
	var cutter = new jQuery.UtrialAvatarCutter();
	
			$("#file").uploadify({
						'uploader' : 'js/uploadify.swf',
						'script' : 'UploadServlet?userid=1', //默认用户ID为1
						'cancelImg' : 'js/cancel.png',
						'queueID' : 'fileList', // 和存放队列的DIV的id一致
						'fileDataName' : 'file', // 和以下input的name属性一致
						'auto' : false, // 是否自动开始
						'multi' : false, // 是否支持多文件上传
						'buttonText' : 'Brower', // 按钮上的文字
						'simUploadLimit' : 1, // 一次同步上传的文件数目
						'sizeLimit' : 3145728, // 设置单个文件大小限制 3MB
						'queueSizeLimit' : 1, // 队列中同时存在的文件个数限制
						'fileDesc' : '支持格式:jpg/jpeg', // 如果配置了以下的'fileExt'属性，那么这个属性是必须的
						'fileExt' : '*.jpg;*.jpeg;',// 允许的格式
						'displayData' : 'percentage',
						onComplete : function(event, queueID, fileObj,
								response, data) {
							$("#userimg").attr("src", response);
							$("#preview").attr("src", response);
						},
						onError : function(event, queueID, fileObj) {
							alert("文件:" + fileObj.name + "上传失败");
						},
						onCancel : function(event, queueID, fileObj) {
//							alert("取消了" + fileObj.name);
						}
					});
			
			$("#userimg").bind("load",function(){
			 cutter.init();
			});
			
			$("#submit").bind("click",function(){
				var x = $("#x").val();
				var y = $("#y").val();
				var w = $("#w").val();
				var h = $("#h").val();
				var img = $("#userimg").attr("src");
				var index =countInstances(img,'/',4);
				img = img.substring(index);
				
				
			$.ajax({
					cache : false,
					type : 'post',
					url : 'ModifyServlet',
					data :'userid=1&x='+x+'&y='+y+'&w='+w+'&h='+h+'&img='+img,
					success : function(callback){
						location.href=callback;
					}
				});
			});
	
			$("#uploadFile").click(function() {
						$("#file").uploadifyUpload();
					});
			$("#cancelUpload").click(function() {
						$("#file").uploadifyClearQueue();
					});
					
	function countInstances(mainStr, subStr,index)
    {
        var count = 0;
        var offset = 0;
        do
        {
            offset = mainStr.indexOf(subStr, offset);
            if(offset != -1)
            {
                count++;
                offset += subStr.length;
            }
        }while(offset != -1&&count<index)
        return offset;
    } 
	

		});
