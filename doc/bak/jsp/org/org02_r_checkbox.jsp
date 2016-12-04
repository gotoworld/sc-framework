<%--
/*
 * 权限_权限信息 后台管理  
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
<c:if test="${treeList!=null && fn:length(treeList)>0}">
	<c:forEach var="bean" items="${treeList}" varStatus="n">
		<c:set var="checkBoxX" value="" />
		<c:if test="${deptBeansCopy!=null && fn:length(deptBeansCopy)>0 }">
			<c:forEach var="myDept" items="${deptBeansCopy}">
				<c:if test="${myDept.id==bean.id}">
					<c:set var="checkBoxX" value='oldc="1" checked="checked"' />
				</c:if>
			</c:forEach>
		</c:if>
		<c:choose>
		  <c:when test="${fn:length(bean.beans)>0}">
				<div class="tree-folder" style="display: block;">
					<div class="tree-folder-header">
						<i class="fa fa-folder-open"></i>
						<div class="tree-folder-name">
							<label>
							<input type="checkbox" class="${dcode} ${bean.id}" level="${numx}" ${checkBoxX} id="x_${bean.id}" value="${bean.id}" name="deptIdArray">
							${bean.name}
							</label>
						</div>
					</div>
					<c:if test="${fn:length(bean.beans)>0}">
						<div class="tree-folder-content">
							<c:set var="treeList" value="${bean.beans}" scope="request" />  
							<c:set var="numx" value="${numx+1}" scope="request" />  
							<c:set var="dcodex" value="${dcode}" />   
							<c:set var="dcode" value="${dcode} ${bean.id}" scope="request" />  
							<c:import url="org02_r_checkbox.jsp" />
							<c:set var="numx" value="${numx-1}" scope="request" />
							<c:set var="dcode" value="${dcodex}" scope="request" />  
						</div>
					</c:if>
				</div>
			</c:when>
			<c:otherwise>
				<div class="tree-item">
					<i class="tree-dot"></i>
					<div class="tree-item-name">
						<label>
						<input type="checkbox" class="${dcode} ${bean.id}" level="${numx}" ${checkBoxX}  id="x_${bean.id}" value="${bean.id}" name="deptIdArray">
						${bean.name}
						</label>
					</div>
				</div>
				<c:if test="${fn:length(bean.beans)>0}">
					<div class="tree-folder-content">
						<c:set var="treeList" value="${bean.beans}" scope="request" />
						<c:set var="numx" value="${numx+1}" scope="request" /> 
						<c:set var="dcodex" value="${dcode}" />   
						<c:set var="dcode" value="${dcode} ${bean.id}" scope="request" />  
						<c:import url="org02_r_checkbox.jsp" />
						<c:set var="numx" value="${numx-1}" scope="request" />
						<c:set var="dcode" value="${dcodex}" scope="request" />  
					</div>
				</c:if>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</c:if>
