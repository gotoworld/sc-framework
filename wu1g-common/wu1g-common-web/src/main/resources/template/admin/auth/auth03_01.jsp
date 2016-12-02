<%--
/*
 * 权限_角色信息 后台管理 (信息编辑页面)
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
<link rel="stylesheet" type="text/css" href="${basePath}/plugins/metronic/v4.1.0/theme/assets/global/plugins/jquery-multi-select/css/multi-select.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/plugins/metronic/v4.1.0/theme/assets/global/plugins/select2/select2-bootstrap.css" />
<link href="${basePath}/plugins/metronic/v4.1.0/theme/assets/admin/layout/css/themes/light.css" rel="stylesheet" type="text/css" id="style_color"/>
<link rel="stylesheet" type="text/css" href="${basePath}/plugins/metronic/v4.1.0/theme/assets/global/plugins/fuelux/css/tree-metronic.css" />

<style type="text/css">
.list1 label{font-weight:normal; }
.list2{margin-left: 20px;}
.list2 li{list-style: none;float: left;}
</style>

</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed">
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		<!-- BEGIN PAGE CONTENT-->
		<div class="row">
			<div class="col-md-12">
				<form id="auth03_01" method="post" class="form-horizontal"  target="_top" accept-charset="UTF-8"  action="${basePath}/h/auth03.save">
				  <input type="hidden" name="pageNum" id="bean_pageNum" value="${bean.pageNum}">
				  <input type="hidden" name="id" value="${bean.id}" />
					<div class="form-group note note-info">
					  <label class="col-md-2 control-label" for="bean_name">角色名称</label>
					  <div class="col-md-4">
					      <input type="text" class="form-control" x_placeholder="请输入角色名称" id="bean_name" name="name" value="${bean.name}"/>
							<span class="help-block"></span>
					  </div>
					  <shiro:hasPermission name="authRole:super">  
					  <label class="col-md-2 control-label checkbox-inline">
							<input id="bean_isSuper" <c:if test="${bean.isSuper=='1'}"> checked="checked"</c:if> name="isSuper" value="1" type="checkbox"> 超级管理员
					  	</label>
					  </shiro:hasPermission>
					</div>
					<shiro:hasPermission name="authRole:parm">  
					<div class="form-group">
					  <label class="col-md-2 control-label ">
					  	功能列表
					  </label>
					  <div class="col-md-10">
							  <div class="tree tree-plus-minus tree-solid-line tree-unselectable">
								<c:if test="${permTree!=null && fn:length(permTree)>0}">
									<c:if test="${myPermList!=null && fn:length(myPermList)>0 }">
										<c:set var="myPermListx" value="${myPermList}" scope="request" />
									</c:if>
									<c:forEach items="${permTree}" var="b">
										<c:set var="checkBoxX" value="" />
										<c:if test="${myPermList!=null && fn:length(myPermList)>0 }">
											<c:forEach var="myperm" items="${myPermList}">
												<c:if test="${myperm.id==b.id}">
													<c:set var="checkBoxX" value='checked="checked"' />
												</c:if>
											</c:forEach>
										</c:if>
										<c:choose>
										  <c:when test="${fn:length(b.beans)>0}">
												<div class="tree-folder" style="display: block;">
													<div class="tree-folder-header">
														<i class="fa fa-folder-open"></i>
														<div class="tree-folder-name">
															<label>
															<i c></i>
															<input type="checkbox" ${checkBoxX} value="${b.id}" name="permIdArray">
															${b.name}
															</label>
														</div>
													</div>
													<c:if test="${fn:length(b.beans)>0}">
														<div class="tree-folder-content">
															<c:set var="treeList" value="${b.beans}" scope="request" />  
															<c:import url="auth03_r_checkbox.jsp" />
														</div>
													</c:if>
												</div>
											</c:when>
											<c:otherwise>
												<div class="tree-item">
													<i class="tree-dot"></i>
													<div class="tree-item-name">
														<label>
														<input type="checkbox" ${checkBoxX} value="${b.id}" name="permIdArray">
														${b.name}
														</label>
													</div>
												</div>
												<c:if test="${fn:length(b.beans)>0}">
													<div class="tree-folder-content">
														<c:set var="treeList" value="${b.beans}" scope="request" />  
														<c:import url="auth03_r_checkbox.jsp" />
													</div>
												</c:if>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:if>
							</div>
					  </div>
					</div>
					</shiro:hasPermission>
					
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
<script type="text/javascript" src="${basePath}/plugins/metronic/v4.1.0/theme/assets/global/plugins/select2/select2.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$('#auth03_01').bootstrapValidator({
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
		}
	});
});
</script>