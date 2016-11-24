<%--
/*
 * 组织架构_用户 后台管理 (初始化页面  信息列表)
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
			<table class="table table-striped table-bordered table-hover" id="table_org02">
				<thead>
					<tr>
						<th style="width: 20px"></th>
						<th class="col-md-2">登录名</th>
						<th class="col-md-2">姓名</th>
						<th class="col-md-4">角色</th>
						<th class="col-md-2">状态</th>
						<th class="col-md-2"></th>
					</tr>
				</thead>
				<tbody>
				<c:choose>
				<c:when test="${beans!=null && fn:length(beans)>0}">
				<c:forEach items="${beans}" var="bean" varStatus="i">
					<tr class="odd gradeX">
						<td>${i.index+1+PAGEROW_OBJECT_KEY.startRow}<!--<input name="pk" type="checkbox" class="checkboxes" value="${bean.id}" />--></td>
						<td>${bean.userid}</td>
						<td>${bean.name}</td>
						<td>${bean.roleNames}</td>
						<td>
						<c:choose>
								<c:when test="${bean.enable=='0'}">
									启用
								</c:when>
								<c:otherwise>
									禁用
								</c:otherwise>
						</c:choose>
						</td>
						<td>
						<shiro:hasPermission name="orgUser:edit"> 
						<a href="javascript:;"  onclick="openMyBoxLayer('e','${basePath}/h/org02.edit/${bean.id}');" class="label label-success" ><i class="fa fa-edit"></i> 编辑</a>
						</shiro:hasPermission>
						<shiro:hasPermission name="orgUser:del"> 
						<a href="javascript:;"  onclick="if(confirm('确认删除吗?')){delInfoData('${basePath}/h/org02.del/${bean.id}');};" rel="nofollow" class="label label-danger" ><i class="fa fa-trash-o"></i> 删除</a>
						</shiro:hasPermission>
						</td>
					</tr>
					</c:forEach>
					</c:when>
					<c:otherwise>
					<tr>
						<td class="col-md-8" colspan="6">
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

 //判断表格中是否有数据
 <c:if test="${beans!=null && fn:length(beans)>0}">
 	//有坑提醒! 使用此功能表格必须为标准表格
 	//表格排序功能  参数1:表格id 参数2:json格式 哪些列不参与排序 null为参与  参数3:数组 隐藏列[0,1]表示隐藏1,2列
 </c:if>
 //判断是否尾页
 if(${PAGEROW_OBJECT_KEY.hasNextPage}){
 	$('#pageNumA').val(${PAGEROW_OBJECT_KEY.pageNum});
 }
});
</script>