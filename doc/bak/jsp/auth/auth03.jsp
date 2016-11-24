<%--
/*
 * 权限_角色信息 后台管理 (初始化页面)
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
				$('#menu_sys,#menu_auth_sub_3').addClass('active');
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
							权限_角色信息管理 <small><span class="help-inline">在这里你可以查看未删除的信息</span></small>
						</h3>
						<ul class="page-breadcrumb breadcrumb">
							
							<li><a href="${basePath}/h/auth03.init">权限_角色信息管理</a> <i class="fa fa-angle-right"></i></li>
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
							<div class="form-actions">
								<form id="info_search_form" method="post" class="form-horizontal" accept-charset="UTF-8" action="${basePath}/h/auth03.infoList">
									<input type="hidden" name="pageNum" id="pageNumA" value="${bean.pageNum}">
									<div class="row clearfix">
										<div class="input-group col-xs-12 col-sm-6 col-md-4 col-lg-3 mt10">
											<span class="input-group-addon"> 角色名称 </span> <input x_placeholder="角色名称" class="form-control" name="name" value="" type="text">
										</div>
										<div class="input-group col-xs-12 col-sm-6 col-md-4 col-lg-3 mt10">
											<span class="input-group-addon"> 起始时间 </span> <input x_placeholder="检索起始时间" name="date1" value="${bean.date1}" type="text" class="form-control laydate-icon" readonly="readonly"
												onclick="laydate({istime: true, festival: true, istoday: true, format: 'YYYY-MM-DD hh:mm:ss'})"
											/>
										</div>
										<div class="input-group col-xs-12 col-sm-6 col-md-4 col-lg-3 mt10">
											<span class="input-group-addon"> 终止时间 </span> <input x_placeholder="检索终止时间" name="date2" value="${bean.date2}" type="text" class="form-control laydate-icon" readonly="readonly"
												onclick="laydate({istime: true, festival: true, istoday: true, format: 'YYYY-MM-DD hh:mm:ss'})"
											/>
										</div>
										<div class="input-group">
											<input class="btn default green-stripe" type="button" onclick="loadUrlPage(null,'info_search_form','info_list_div');" value="检索" /> <input class="btn default dark-stripe" type="reset" value="重置" />
										</div>
									</div>
								</form>
							</div>
							<div class="mt10 clearfix"></div>
							<shiro:hasPermission name="authRole:add">
								<a href="javascript:;" onclick="openMyBoxLayer('a','${basePath}/h/auth03.edit/null')" class="label label-primary"><i class="fa fa-plus"></i> 新增</a>
							</shiro:hasPermission>
							<div id="info_list_div">
								<div class="alert alert-warning mt10">
									<strong>数据加载中!</strong>data loading...
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
			loadUrlPage(null, 'info_search_form', 'info_list_div');
		});
	</script>
</body>
<!-- END BODY -->
</html>
