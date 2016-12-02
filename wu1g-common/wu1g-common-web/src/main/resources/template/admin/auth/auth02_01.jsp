<%--
/*
 * 权限_权限信息 后台管理 (信息编辑页面)
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
<link rel="stylesheet" href="${basePath}/plugins/bootstrapvalidator/vendor/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" href="${basePath}/plugins/bootstrapvalidator/dist/css/bootstrapValidator.css" />
<link href="${basePath}/plugins/metronic/v4.1.0/theme/assets/admin/layout/css/themes/light.css" rel="stylesheet" type="text/css" id="style_color"/>


</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed">
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		<!-- BEGIN PAGE CONTENT-->
		<div class="row">
			<div class="col-md-12">
				<form id="auth02_01" method="post" class="form-horizontal"  target="_top" accept-charset="UTF-8"  action="${basePath}/h/auth02.save">
				  <input type="hidden" name="pageNum" id="bean_pageNum" value="${bean.pageNum}">
				  <input type="hidden" name="id" value="${bean.id}" />
				  	<div class="form-group">
					  <label class="col-md-2 control-label" for="bean_parentid">父级ID</label>
					  <div class="col-md-8">
					  		<c:set var="_this_self" value="${bean.id}" scope="request" />  
					  		<c:set var="_band_flag" value="${bean.parentid}" scope="request" />  
					  		<select  class="form-control" name="parentid">
					  			<option value="">---顶级---</option>
					  			<c:if test="${beans!=null && fn:length(beans)>0}">
					  				<c:forEach items="${beans}" var="b" varStatus="i">
					  					<c:if test="${_this_self!=b.id}">
					  					<option  value="${b.id}" <c:if test="${_band_flag==b.id}">selected="selected"</c:if>>${b.name}</option>
					  					<c:set var="treeList" value="${b.beans}" scope="request" /> 
					  					<c:set var="numx" value="1" scope="request" />  
					  					<c:import url="auth02_r_select.jsp" />
					  					</c:if>
					  				</c:forEach>
					  			</c:if>
					  		</select>
							<span class="help-block"></span>
					  </div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label" for="bean_name">权限名称</label>
					  <div class="col-md-8">
					      <input type="text" class="form-control" x_placeholder="请输入权限名称" id="bean_name" name="name" value="${bean.name}"/>
							<span class="help-block"></span>
					  </div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label" for="bean_matchStr">权限匹配符</label>
					  <div class="col-md-8">
					      <input type="text" class="form-control" x_placeholder="请输入权限匹配符" id="bean_matchStr" name="matchStr" value="${bean.matchStr}"/>
							<span class="help-block"></span>
					  </div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label" for="bean_memo">备注</label>
					   <div class="col-md-8">
					     <textarea class="form-control" rows="3"  x_placeholder="请输入备注" id="bean_memo" name="memo">${bean.memo}</textarea>
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
<script type="text/javascript" src="${basePath}/plugins/bootstrapvalidator/vendor/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${basePath}/plugins/bootstrapvalidator/dist/js/bootstrapValidator.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$('#auth02_01').bootstrapValidator({
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
						 max: 50,
						 message: '字符不能超过50个'
					 }
				 }
			 }
			 , 
			"matchStr": {
				 validators: {
					notEmpty: {
						 message: '不能为空'
					 }
					 ,
					 stringLength: {
						 max: 50,
						 message: '字符不能超过50个'
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
		}
	});
});
</script>