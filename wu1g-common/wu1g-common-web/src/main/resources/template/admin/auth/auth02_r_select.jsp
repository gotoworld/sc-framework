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
		<c:if test="${_this_self!=bean.id}">
		<option value="${bean.id}" <c:if test="${_band_flag==bean.id}">selected="selected"</c:if>>
		<c:forEach begin="1" end="${numx}" step="1">
		|-
		</c:forEach>
		${bean.name}</option>
		<c:if test="${bean.beans!=null && fn:length(bean.beans)>0}">
			<c:set var="numx" value="${numx+1}" scope="request" />
			<c:set var="treeList" value="${bean.beans}" scope="request" /> 
			<c:import url="auth02_r_select.jsp" />
			<c:set var="numx" value="${numx-1}" scope="request" />
		</c:if>
		</c:if>
	</c:forEach>
</c:if>
