<%--
/*
 * 权限_权限信息 后台管理 (初始化页面)
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
<%@ include file="../include/public_tags.jsp"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="zh-CN" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="zh-CN" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="zh-CN" class="no-js">
<!--<![endif]-->
<head>
<meta charset="utf-8" />
<%@ include file="../include/meta.jsp"%>
<%@ include file="../include/admin_title.jsp"%>
<%@ include file="../include/public_init_js_css.jsp"%>
<link rel="stylesheet" type="text/css" href="${basePath}/plugins/metronic/v4.1.0/theme/assets/global/plugins/fuelux/css/tree-metronic.css" />
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-container-bg-solid page-sidebar-closed-hide-logo page-sidebar-fixed">
	<!-- BEGIN HEADER -->
	
	<!-- END HEADER -->
	<div class="clearfix"></div>
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		<!-- BEGIN SIDEBAR MENU -->
		
		<!-- END SIDEBAR MENU -->
		<script type="text/javascript">
			jQuery(document).ready(function() {
				$('#menu_sys,#menu_auth_sub_2').addClass('active');
				$('#menu_sys_arrow').addClass('open');
				$('#menu_sys_sub_menu').show();
			});
		</script>
		<!-- BEGIN PAGE -->
		<div class="">
			<div class="page-content">
				<!-- BEGIN PAGE HEADER-->
				<div class="row">
					<div class="col-md-12">
						<!-- BEGIN PAGE TITLE & BREADCRUMB-->
						<h3 class="page-title">
							权限_权限信息管理 <small><span class="help-inline">在这里你可以查看未删除的信息</span></small>
						</h3>
						<ul class="page-breadcrumb breadcrumb">
							
							<li><a href="${basePath}/h/auth02.init">权限_权限信息管理</a> <i class="fa fa-angle-right"></i></li>
							<li>列表</li>
						</ul>
						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				</div>
				<!-- END PAGE HEADER-->

				<!-- BEGIN PAGE CONTENT-->
				<!-- BEGIN PAGE CONTENT-->
				<div class="row">
					<div class="col-md-12">
						<div class="matter">
							<c:if test="${msg!=null}">
								<c:choose>
									<c:when test="${msg!='1'}">
										<div class="alert alert-danger">
											<button class="close" data-dismiss="alert"></button>
											<strong>Error!</strong> ${msg}
										</div>
									</c:when>
									<c:otherwise>
										<div class="alert alert-success">
											<button class="close" data-dismiss="alert"></button>
											<strong>Success!</strong> 操作成功。
										</div>
									</c:otherwise>
								</c:choose>
							</c:if>
							<div id="msg"></div>
							<shiro:hasPermission name="authPerm:add">
								<div class="form-actions">
									<a href="javascript:;" onclick="openMyBoxLayer('a','${basePath}/h/auth02.edit/add')" class="label label-primary"><i class="fa fa-pencil"></i> 新增</a>
								</div>
							</shiro:hasPermission>
							<div class="mt10 clearfix"></div>
							<div id="info_list_div" style="margin-top: 30px">
								<div class="tree tree-plus-minus tree-solid-line tree-unselectable">
									<c:if test="${beans!=null && fn:length(beans)>0}">
										<c:forEach items="${beans}" var="b" varStatus="i">
											<c:choose>
												<c:when test="${fn:length(b.beans)>0}">
													<div class="tree-folder" style="display: block;">
														<div class="tree-folder-header">
															<i class="fa fa-folder-open"></i>
															<div class="tree-folder-name">
																${b.name} - ${b.matchStr}
																<div class="tree-actions">
																	<shiro:hasPermission name="authPerm:edit">
																		<a style="display: block; float: left;" href="javascript:;" onclick="openMyBoxLayer('e','${basePath}/h/auth02.edit/${b.id}');" class="label label-success"><i class="fa fa-pencil"></i></a>
																	</shiro:hasPermission>
																	<shiro:hasPermission name="authPerm:del">
																		<c:if test="${fn:length(b.beans)==0}">
																			<a href="javascript:;" onclick="if(confirm('确认删除吗?')){delInfoData('${basePath}/h/auth02.del/${b.id}');};" rel="nofollow" class="label label-danger"><i class="fa fa-trash-o"></i></a>
																		</c:if>
																	</shiro:hasPermission>
																</div>
															</div>
														</div>
														<c:if test="${fn:length(b.beans)>0}">
															<div class="tree-folder-content">
																<c:set var="treeList" value="${b.beans}" scope="request" />
																<c:import url="auth02_r_init.jsp" />
																<!-- 递归 -->
															</div>
														</c:if>
													</div>
												</c:when>
												<c:otherwise>
													<div class="tree-item">
														<i class="tree-dot"></i>
														<div class="tree-item-name">
															${b.name} - ${b.matchStr}
															<div class="tree-actions">
																<shiro:hasPermission name="authPerm:edit">
																	<a style="display: block; float: left;" href="javascript:;" onclick="openMyBoxLayer('e','${basePath}/h/auth02.edit/${b.id}');" class="label label-success"><i class="fa fa-pencil"></i></a>
																</shiro:hasPermission>
																<shiro:hasPermission name="authPerm:del">
																	<c:if test="${fn:length(b.beans)==0}">
																		<a href="javascript:;" onclick="if(confirm('确认删除吗?')){delInfoData('${basePath}/h/auth02.del/${b.id}');};" rel="nofollow" class="label label-danger"><i class="fa fa-trash-o"></i></a>
																	</c:if>
																</shiro:hasPermission>
															</div>
														</div>
													</div>
													<c:if test="${fn:length(b.beans)>0}">
														<div class="tree-folder-content">
															<c:set var="treeList" value="${b.beans}" scope="request" />
															<c:import url="auth02_r_init.jsp" />
															<!-- 递归 -->
														</div>
													</c:if>
												</c:otherwise>
											</c:choose>

										</c:forEach>
									</c:if>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- END PAGE CONTENT-->
			</div>
		</div>
	</div>
	<!-- BEGIN FOOTER -->
	<%@ include file="../include/footer.jsp"%>
	<!-- END FOOTER -->
	<%@ include file="../include/public_last_js_css.jsp"%>
	<script type="text/javascript">
		$(document).ready(function() {
			//js code 
			//初始加载..
		});
	</script>
</body>
<!-- END BODY -->
</html>
