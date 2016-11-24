<%--
/*
 * 系统_管理员操作日志 后台管理 (初始化页面  信息列表)
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
	<!-- BEGIN PAGE -->
		<div class="matter">
			<table class="table table-striped table-bordered table-hover" id="table_sys02">
				<thead>
					<tr>
						<th style="width: 20px"></th>
						<th class="col-md-1">类型</th>
						<th class="col-md-9">内容</th>
						<th class="col-md-2">时间</th>
					</tr>
				</thead>
				<tbody>
				<c:choose>
				<c:when test="${beans!=null && fn:length(beans)>0}">
				<c:forEach items="${beans}" var="bean" varStatus="i">
					<tr class="odd gradeX">
						<td>${PAGEROW_OBJECT_KEY.startRow+1+i.index}</td>
						<td>${bean.type}</td>
						<td>${bean.description}</td>
						<td>${bean.dateCreated}</td>
					</tr>
					</c:forEach>
					</c:when>
					<c:otherwise>
					<tr>
						<td class="col-md-8" colspan="4">
							<div class="alert"><strong>暂无数据!</strong></div>
						</td>
					</tr>
					</c:otherwise>
				</c:choose>
				</tbody>
			</table>
			<customtag:pagingext func="loadUrlPage" params="'info_search_form','info_list_div'" />
		</div>
<!-- END PAGE CONTENT-->
<script type="text/javascript">
$(document).ready(function() {
//js code 
 //判断是否尾页
 if(${PAGEROW_OBJECT_KEY.hasNextPage}){
 	$('#pageNumA').val(${PAGEROW_OBJECT_KEY.pageNum});
 }
});
</script>