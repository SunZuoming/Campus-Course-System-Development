jQuery.UtrialAvatarCutter = function(){
	
	var api = null;

	var sel = this;

	this.reload = function(img_url){
		sel.init();
	}
	
	this.init = function(){
		if(api!=null){
			api.destroy();
		}
	
	api = $.Jcrop('#userimg',{ 
			onChange : showPreview,
			onSelect : showPreview,
			aspectRatio : 1,
			allowResize : true
		});
	
	}
	
	var showPreview =	function(coords)
{
	var rx = 120 / coords.w;
	var ry = 120 / coords.h;
	var ow = $("#userimg").width();
	var oh = $("#userimg").height();
	
	
	$('#preview').css({
		width: 	Math.round(rx * ow) + 'px',
		height: Math.round(ry * oh) + 'px',
		marginLeft: '-' + Math.round(rx * coords.x) + 'px',
		marginTop: '-' + Math.round(ry * coords.y) + 'px'
	});
	$("#w").attr("value",Math.round(rx * ow));
	$("#h").attr("value",Math.round(ry * oh));
	$("#x").attr("value",Math.round(rx * coords.x));
	$("#y").attr("value",Math.round(ry * coords.y));
	
};
	
}