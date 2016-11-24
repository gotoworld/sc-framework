<%--
/*
 * 系统_数据字典 后台管理 (信息编辑页面)
 *
 * VERSION  DATE		BY		   REASON
 * -------- ----------- ------------ ------------------------------------------
 * 1.00	 2015.10.01  easycode   程序.发布
 * -------- ----------- ------------ ------------------------------------------
 * Copyright 2015 isd System. - All Rights Reserved.
 *
 */
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>
<%@ include file="../include/public_tags.jsp" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="zh-CN" class="ie8 no-js"> <![endif]--><!--[if IE 9]> <html lang="zh-CN" class="ie9 no-js"> <![endif]--><!--[if !IE]><!--> <html lang="zh-CN" class="no-js"> <!--<![endif]-->
<head>
<meta charset="utf-8" />
<%@ include file="../include/meta.jsp" %>
<%@ include file="../include/admin_title.jsp" %>
<%@ include file="../include/public_init_js_css.jsp" %>
<link rel="stylesheet" href="${basePath}/static/plugins/bootstrapvalidator/vendor/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" href="${basePath}/static/plugins/bootstrapvalidator/dist/css/bootstrapValidator.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/static/plugins/metronic/v4.1.0/theme/assets/global/plugins/jquery-multi-select/css/multi-select.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/static/plugins/metronic/v4.1.0/theme/assets/global/plugins/select2/select2-bootstrap.css" />
<link href="${basePath}/static/plugins/metronic/v4.1.0/theme/assets/admin/layout/css/themes/light.css" rel="stylesheet" type="text/css" id="style_color"/>


</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed">
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		<!-- BEGIN PAGE CONTENT-->
		<div class="row">
			<div class="col-md-12">
				<form id="sys03_01" method="post" class="form-horizontal"  target="_top" accept-charset="UTF-8"  action="${basePath}/h/sys03.save">
				  <input type="hidden" name="pageNum" id="bean_pageNum" value="${bean.pageNum}">
				  <input type="hidden" name="id" value="${bean.id}" />
					<div class="form-group">
						<label class="col-md-2 control-label" for="bean_parentid">父级</label>
					  <div class="col-md-8">
					      <input type="text" class="form-control" x_placeholder="请输入父级" id="bean_parentid" name="parentid" value="${bean.parentid}"/>
							<span class="help-block"></span>
					  </div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label" for="bean_code">编码</label>
					  <div class="col-md-8">
					      <input type="text" class="form-control" x_placeholder="请输入编码" id="bean_code" name="code" value="${bean.code}"/>
							<span class="help-block"></span>
					  </div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label" for="bean_name">名称</label>
					  <div class="col-md-8">
					      <input type="text" class="form-control" x_placeholder="请输入名称" id="bean_name" name="name" value="${bean.name}"/>
							<span class="help-block"></span>
					  </div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label" for="bean_memo">备注</label>
					   <div class="col-md-8">
					     <textarea class="form-control" rows="3" x_placeholder="请输入备注" id="bean_memo" name="memo">${bean.memo}</textarea>
							<span class="help-block"></span>
					   </div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label" for="bean_orderNo">排序</label>
					  <div class="col-md-8">
					      <input type="number" class="form-control" x_placeholder="请输入排序" id="bean_orderNo" name="orderNo" value="${bean.orderNo==null?0:bean.orderNo}"/>
							<span class="help-block"></span>
					  </div>
					</div>
					
					<div class="form-actions">
						<div style="width: 100px;margin: 0 auto;">
						<input class="btn btn-primary" type="submit" value="保存" /> | <a href="javascript:;" onclick="closeMyBoxLayer();">返回</a>
					</div>
					</div>
				</form>
			</div>
		</div>
	</div>
		<!-- END CONTAINER -->
	<!-- BEGIN FOOTER -->
	<%@ include file="../include/footer.jsp"%>
	<!-- END FOOTER -->
</body>
<!-- END BODY -->
</html>
<%@ include file="../include/public_last_js_css.jsp"%>
<script type="text/javascript" src="${basePath}/static/plugins/bootstrapvalidator/vendor/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${basePath}/static/plugins/bootstrapvalidator/dist/js/bootstrapValidator.js"></script>
<script type="text/javascript" src="${basePath}/static/plugins/metronic/v4.1.0/theme/assets/global/plugins/select2/select2.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$('#sys03_01').bootstrapValidator({
		message: 'This value is not valid',
		feedbackIcons: {
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {
			"name": {
				 validators: {
					 notEmpty: {
						 message: '不能为空'
					 }
					 ,
					 stringLength: {
						 max: 100,
						 message: '字符不能超过100个'
					 }
				 }
			 }
			 , 
			"code": {
				 validators: {
					 stringLength: {
						 max: 100,
						 message: '字符不能超过100个'
					 }
				 }
			 }
			 , 
			"memo": {
				 validators: {
					 stringLength: {
						 max: 255,
						 message: '字符不能超过255个'
					 }
				 }
			 }
			 , 
			"orderNo": {
				 validators: {
					 numeric: {
						 message: '必须是数字'
					 }
				 }
			 }
		}
	});
});
</script>