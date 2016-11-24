<%--
/*
 * 组织架构_部门 后台管理 (初始化页面)
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

<%--  ---${fn:length(treeList)}--- --%>
<c:if test="${treeList!=null && fn:length(treeList)>0}">
	<c:forEach var="bean" items="${treeList}" varStatus="n">
		<c:choose>
		  <c:when test="${fn:length(bean.beans)>0}">
				<div class="tree-folder" style="display: block;">
					<div class="tree-folder-header">
						<i class="fa fa-folder-open"></i>
						<div class="tree-folder-name">
							${bean.name}
							<div class="tree-actions">
							<shiro:hasPermission name="orgDept:edit">
								<a style="display: block;float: left;" href="javascript:;"  onclick="openMyBoxLayer('e','${basePath}/h/org01.edit/${bean.id}');" class="label label-success" ><i class="fa fa-pencil"></i></a>
							</shiro:hasPermission>
							<shiro:hasPermission name="orgDept:del">
								<c:if test="${fn:length(bean.beans)==0}">
								<a href="javascript:;"  onclick="if(confirm('确认删除吗?')){delInfoData('${basePath}/h/org01.del/${bean.id}');};" rel="nofollow" class="label label-danger" ><i class="fa fa-trash-o"></i></a>
								</c:if>
							</shiro:hasPermission>
							</div>
						</div>
					</div>
					<c:if test="${fn:length(bean.beans)>0}">
						<div class="tree-folder-content">
							<c:set var="treeList" value="${bean.beans}" scope="request" />  
							<c:import url="org01_r_init.jsp" />
						</div>
					</c:if>
				</div>
			</c:when>
			<c:otherwise>
				<div class="tree-item">
					<i class="tree-dot"></i>
					<div class="tree-item-name">
						${bean.name}
						<div class="tree-actions">
						<shiro:hasPermission name="orgDept:edit">
							<a style="display: block;float: left;" href="javascript:;"  onclick="openMyBoxLayer('e','${basePath}/h/org01.edit/${bean.id}');" class="label label-success" ><i class="fa fa-pencil"></i></a>
						</shiro:hasPermission>
						<shiro:hasPermission name="orgDept:del">
							<c:if test="${fn:length(bean.beans)==0}">
							<a href="javascript:;"  onclick="if(confirm('确认删除吗?')){delInfoData('${basePath}/h/org01.del/${bean.id}');};" rel="nofollow" class="label label-danger" ><i class="fa fa-trash-o"></i></a>
							</c:if>
						</shiro:hasPermission>
						</div>
					</div>
				</div>
				<c:if test="${fn:length(bean.beans)>0}">
					<div class="tree-folder-content">
						<c:set var="treeList" value="${bean.beans}" scope="request" />  
						<c:import url="org01_r_init.jsp" />
					</div>
				</c:if>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</c:if>