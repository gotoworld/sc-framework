<%--
/*
 * 底部共通部分画面
 *
 * VERSION  DATE        BY           REASON
 * -------- ----------- ------------ ------------------------------------------
 * 1.00     2016-03-26  wuxiaogang        程序・发布
 * -------- ----------- ------------ ------------------------------------------
 * Copyright 2016 baseos System. - All Rights Reserved.
 *
 */
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div style="clear: both;"></div>
<!-- BEGIN FOOTER -->
<div align="center" style="text-align: center;height: 50px;">
	<!-- BEGIN FOOTER -->
	<div class="page-footer " style="height: 50px;">
		<div class="page-footer-inner">
			Copyright 2016 全景. - All Rights Reserved. .${onlineNumber}.
<%-- 			<a class="_language" l="zh_CN"><img alt="中文" src="${basePath}/img/flags/cn.gif"></a>  --%>
<%-- 			<a class="_language" l="en_US"><img alt="English" src="${basePath}/img/flags/us.gif"></a> --%>
		</div>
		<div class="scroll-to-top">
			<i class="icon-arrow-up"></i>
		</div>
	</div>
</div>
<!-- END FOOTER -->
<style type="text/css">
.row {
	margin-right: 0;
	margin-left: 0;
}

a {
	cursor: pointer;
}

.page-footer .page-footer-inner {
	float: none;
}
</style>
<script>
	function switchlanguage(language) {
		$.ajax({
			type : 'POST',
			url : '${basePath}/admin/switchlanguage',
			cache : false,
			data : 'language=' + language,
			async : false, //默认为true 异步   
			//dataType: dataType,
			success : function(data) {
				//return data;
				window.location.reload();
			},
			error : function(e) {

			}
		});
	}
	$('._language').click(function() {
		switchlanguage($(this).attr('l'));
	});
</script>