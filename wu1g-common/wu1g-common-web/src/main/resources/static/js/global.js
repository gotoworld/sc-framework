// guid
function guid() {
	var S4 = function() {
		return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
	};
	return 'uuid'+(S4() + S4() +  S4() +  S4() +  S4() +  S4()
			+ S4() + S4());
}

//是否被选中验证有选中的return true,否return false 
function checkInfo(name,type){ 
	var falg = 0; 
	$("input[name="+name+"]:"+type).each(function(){ 
		if($(this).attr("checked")){
			falg +=1;
			return false;
		} 
	}); 
	if(falg>0){
		return true; 
	} 
	return false; 
}
function autoOnClick(id){
	if(document.all) {
		document.getElementById(id).click();
	}
	// 其它浏览器
	else {
		var e = document.createEvent("MouseEvents");
		e.initEvent("click", true, true);
		document.getElementById(id).dispatchEvent(e);
	}
}
//noty
function myAlert(text) {
	noty({
		text : text,
		type : 'information',
		dismissQueue : true,
		layout : 'top',
		theme : 'default'
	});
}

function myAlert_error(text) {
	noty({
		text : text,
		type : 'error',
		dismissQueue : true,
		layout : 'top',
		theme : 'default'
	});
}

function myAlert_success(text) {
	noty({
		text : text,
		type : 'success',
		dismissQueue : true,
		layout : 'top',
		theme : 'default'
	});
}

function myAlert_warning(text) {
	noty({
		text : text,
		type : 'warning',
		dismissQueue : true,
		layout : 'top',
		theme : 'default'
	});
}

function myAlert_confirm(text) {
	noty({
		text : text,
		 type: 'alert',
	      dismissQueue: true,
	      layout: 'top',
	      theme: 'default',
	      buttons: [
	        {addClass: 'btn btn-primary', text: '确定', onClick: function($noty) {
	            $noty.close();
	            
	            return true;
	          }
	        },
	        {addClass: 'btn btn-danger', text: '取消', onClick: function($noty) {
	            $noty.close();
	            return false;
	          }
	        }
	      ]
	});
}
jQuery(document).ready(function() {
	try {
		$('.tree-folder-content').prev().find('i').click(function() {
			if ($(this).hasClass('fa-folder')) {
				$(this).removeClass('fa-folder');
				$(this).addClass('fa-folder-open');
			} else {
				$(this).removeClass('fa-folder-open');
				$(this).addClass('fa-folder');
			}
			$(this).parent().next().toggle();
		});
	} catch (e) {
	}
});
/* $(document).ajaxStart(
				function() {
					$.blockUI({
								message : '<img src="${basePath}img/ajax-loading.gif" />',
								css : {
									top : '50%',
									border : 'none',
									padding : '2px',
									backgroundColor : 'none'
								},
								overlayCSS : {
									backgroundColor : '#000',
									opacity : 0.05,
									cursor : 'wait'
								}
							});
				});
$(document).ajaxStop(function() {
	// 直接调用，无延时
	$.unblockUI();
}); */

function openMyBoxLayer(mytitle, myurl) {
	if (mytitle != 'a') {
		var pageNumA = 1;
		if ($('#pageNumA').val()) {
			pageNumA = $('#pageNumA').val();
		}
		pageNumA = parseInt(pageNumA);
		if (!!myurl) {
			if (myurl.indexOf('?') != -1) {
				myurl += '&bean.pageNum=' + pageNumA
			} else {
				myurl += '?bean.pageNum=' + pageNumA
			}
		}
	}
	if (mytitle == 'a') {
		mytitle = '信息新增';
	}
	if (mytitle == 'e') {
		mytitle = '信息修改';
	}
	layer.open({
		type : 2,
		title : [ mytitle,
				'background:#eee; height:40px; color:#333; border:none;' //自定义标题样式
		],
		border : [ 0 ],
		area : [ '70%', '70%' ],
		content: myurl
	});
	$('body').css("overflow", "hidden");
	$('#bg').bind("touchmove mousewheel", function(e) {
//		console.info('--------');
		e.preventDefault();
	});
	$('.xubox_close').click(function() {
		closeMyBoxLayer();
	});
}
function closeMyBoxLayer() {
	$('body', parent.document).css("overflow", "auto");
	$('body').css("overflow", "auto");
	try {
		//获取当前窗口索引
		var index = parent.layer.getFrameIndex(window.name);
		//关闭iframe
		parent.layer.close(index);
	} catch (e) {
	}
}
function loadUrlPage(pageNumA, formId, divId) {
	// console.info('pageNumA='+pageNumA+',formId='+formId+',divId='+divId)
	//判断是否登录超时
	//if (cheackLogin(req)) {
	if (pageNumA != null) {
		$('#pageNumA').val(pageNumA);
	}
	$("#" + formId).ajaxSubmit({
			type:"post",  //提交方式
            //dataType:"json", //数据类型
            url:$("#" + formId).attr('data-action'), //请求url
            success:function(data) {
            	$('#' + divId).html(data);
            },error: function(e) {
				alert(JSON.stringify(e));
			}
    });
	//}
}
function delInfoData(myurl) {
	//判断是否登录超时
	//if (cheackLogin(req)) {
	location.href = myurl;
	//}
}
