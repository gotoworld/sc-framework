<%--
/*
 * jsp左侧菜单
 *
 * VERSION  DATE        BY           REASON
 * -------- ----------- ------------ ------------------------------------------
 * 1.00     2016-09-12  wuxiaogang   程序・发布
 * -------- ----------- ------------ ------------------------------------------
 * Copyright 2016 baseos System. - All Rights Reserved.
 *
 */
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- BEGIN SIDEBAR -->
<div class="page-sidebar-wrapper">
	<div class="page-sidebar navbar-collapse collapse">
		<!-- BEGIN SIDEBAR MENU -->
		<ul class="page-sidebar-menu page-sidebar-menu-fixed" data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">
			<!--<li class="sidebar-toggler-wrapper">
							<div class="sidebar-toggler">
							</div>
						</li>-->
			<li id="menu_home" class="start"><a href="${basePath}/h/index">
					<i class="icon-home"></i> <span class="title">首页</span> <span class="selected"></span>
				</a></li>
			
			<li id="menu_pano" class="">
				<a class="active" href="javascript:;">
					<i class="fa fa-cogs"></i> 
					<span class="title">全景</span>
					<span id="menu_pano_arrow" class="arrow"></span>
				</a>
				<ul id="menu_pano_sub" style="display: none;" class="sub-menu">
					<shiro:hasPermission name="pano01:init">
					  <%--全景_类目 --%>
					  <li id="menu_pano_sub_1"><a href="${basePath }/h/pano01.init">全景_类目</a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="pano02:init">
					  <%--全景_项目 --%>
					  <li id="menu_pano_sub_2"><a href="${basePath }/h/pano02.init">全景_项目</a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="pano03:init">
					  <%--全景_场景 --%>
					  <li id="menu_pano_sub_3"><a href="${basePath }/h/pano03.init">全景_场景</a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="pano04:init">
					  <%--全景_场景_评论 --%>
					  <li id="menu_pano_sub_4"><a href="${basePath }/h/pano04.init">全景_场景_评论</a></li>
					</shiro:hasPermission>
				</ul>
			</li>
		<shiro:hasPermission name="sys:menu"> 
			<li id="menu_sys" class="">
				<a class="active" href="javascript:;">
				<i class="fa fa-cogs"></i> 
				<span class="title">系统管理</span>
				<span id="menu_sys_arrow" class="arrow"></span>
				</a>
				<ul id="menu_sys_sub_menu" style="display: none;" class="sub-menu">
					<shiro:hasPermission name="orgDept:menu"> 
					<li id="menu_org_sub_1"><a href="${basePath }/h/org01.init">组织架构_部门</a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="orgUser:menu"> 
					<li id="menu_org_sub_2"><a href="${basePath }/h/org02.init">组织架构_用户</a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="authPerm:menu"> 
					<li id="menu_auth_sub_2"><a href="${basePath }/h/auth02.init">权限_权限信息</a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="authRole:menu"> 
					<li id="menu_auth_sub_3"><a href="${basePath }/h/auth03.init">权限_角色信息</a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="sysLog:menu"> 
					<li id="menu_sys_sub_2"><a href="${basePath }/h/sys02.init">系统_操作日志</a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="sysDic:menu"> 
					<%-- <li id="menu_sys_sub_3"><a href="${basePath }/h/sys03.init">系统_数据字典</a></li> --%>
					</shiro:hasPermission>
				</ul>
			</li>
		</shiro:hasPermission>
		</ul>
		<!-- END SIDEBAR MENU -->
	</div>
</div>
<!-- END SIDEBAR -->
