<%--
/*
 * 组织架构_用户 后台管理 (信息编辑页面)
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
<%@ include file="../include/meta.jsp"%>
<%@ include file="../include/admin_title.jsp"%>
<%@ include file="../include/public_init_js_css.jsp"%>
<link rel="stylesheet"
	href="${basePath}/static/plugins/bootstrapvalidator/vendor/bootstrap/css/bootstrap.css" />
<link rel="stylesheet"
	href="${basePath}/static/plugins/bootstrapvalidator/dist/css/bootstrapValidator.css" />
<link rel="stylesheet" type="text/css"
	href="${basePath}/static/plugins/metronic/v4.1.0/theme/assets/global/plugins/jquery-multi-select/css/multi-select.css" />
<link rel="stylesheet" type="text/css"
	href="${basePath}/static/plugins/metronic/v4.1.0/theme/assets/global/plugins/select2/select2-bootstrap.css" />
<link
	href="${basePath}/static/plugins/metronic/v4.1.0/theme/assets/admin/layout/css/themes/light.css"
	rel="stylesheet" type="text/css" id="style_color" />

<link rel="stylesheet" type="text/css" href="${basePath}/static/plugins/metronic/v4.1.0/theme/assets/global/plugins/fuelux/css/tree-metronic.css" />

</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed">
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		<!-- BEGIN PAGE CONTENT-->
		<div class="row">
			<div class="col-md-12">
				<form id="org02_01" method="post" class="form-horizontal note"
					target="_top" accept-charset="UTF-8"
					action="${basePath}/h/org02.save">
					<input type="hidden" name="pageNum" id="bean_pageNum"
						value="${bean.pageNum}"> <input type="hidden"
						name="id" value="${bean.id}" />
					<input type="hidden" name="type" id="bean_type" value="0">
					<c:if test="${bean.userid!='admin' }">
					<shiro:hasPermission name="orgUser:role.edit"> 
					<div class="col-md-12 alert alert-info">
						<label class="control-label" for="bean_memo">角色=></label>
						<div class="form-group ">
								<c:if test="${roleBeans!=null}">
									<c:forEach var="role" items="${roleBeans}">
										<c:set var="myrolex" value='' />
										<c:if test="${myRoleBeans!=null}">
											<c:forEach var="myrole" items="${myRoleBeans}">
												<c:if test="${role.id==myrole.id}">
													<c:set var="myrolex" value='checked="checked"' />
												</c:if>
											</c:forEach>
										</c:if>
										<label class="checkbox-inline"> <input id="${role.id}"
											value="${role.id}" ${myrolex} type="radio"
											name="roleIdArray">${role.name}
										</label>
									</c:forEach>
								</c:if>
						</div>
					</div>
					</shiro:hasPermission>
					</c:if>
					<div class="col-md-6 alert alert-info">
						<div class="form-group">
							<label class="col-md-2 control-label" for="bean_userid">登录名</label>
							<div class="col-md-8">
							<c:if test="${bean.userid==null}">
								<input type="text" class="form-control" x_placeholder="请输入用户登录名"
									id="bean_userid" name="userid" value="${bean.userid}" />
							</c:if>
							<c:if test="${bean.userid!=null}">
								<input type="text" class="form-control" disabled="disabled" value="${bean.userid}" />
							</c:if>
								<span class="help-block"></span>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-2 control-label" for="bean_name">成员名称</label>
							<div class="col-md-8">
								<input type="text" class="form-control" x_placeholder="请输入成员名称"
									id="bean_name" name="name" value="${bean.name}" /> <span
									class="help-block"></span>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-2 control-label" for="bean_mobile">移动电话</label>
							<div class="col-md-8">
								<input type="text" class="form-control" x_placeholder="请输入移动电话"
									id="bean_mobile" name="mobile" value="${bean.mobile}" />
								<span class="help-block"></span>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-2 control-label" for="bean_email">邮箱</label>
							<div class="col-md-8">
								<input type="text" class="form-control" x_placeholder="请输入邮箱"
									id="bean_email" name="email" value="${bean.email}" /> <span
									class="help-block"></span>
							</div>
						</div>
					</div>
					<div class="col-md-6 alert alert-info">
						<div class="form-group">
							<label class="col-md-2 control-label" for="bean_pwd">登录密码</label>
							<div class="col-md-8">
								<input type="hidden" name="pwd"  id="bean_pwd" value="" />
								<input type="password" class="form-control" name="pwd_x" onchange="cPwd(this.value)"  onblur="cPwd(this.value)" x_placeholder="不修改请留空" value="" />
								<span class="help-block"></span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label" for="bean_jobNo">工号</label>
							<div class="col-md-8">
								<input type="text" class="form-control" x_placeholder="请输入工号"
									id="bean_jobNo" name="jobNo" value="${bean.jobNo}" /> <span
									class="help-block"></span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label" for="bean_tel">固定电话</label>
							<div class="col-md-8">
								<input type="text" class="form-control" x_placeholder="请输入固定电话"
									id="bean_tel" name="tel" value="${bean.tel}" /> <span
									class="help-block"></span>
							</div>
						</div>
						<div class="form-group">
						<label class="col-md-2 control-label" for="bean_enable">是否启用</label>
						<div class="col-md-8 radio-list">
							<c:choose>
								<c:when test="${bean.enable=='1'}">
									<label class="radio-inline"> 
										<input id="bean_enable_0" class="form-control" value="0" type="radio" name="enable">启用
									</label>
									<label class="radio-inline"> 
										<input id="bean_enable_1" checked="checked" class="form-control" value="1" type="radio" name="enable">禁用
									</label>
								</c:when>
								<c:otherwise>
									<label class="radio-inline"> 
										<input id="bean_enable_0" checked="checked" class="form-control" value="0" type="radio" name="enable">启用
									</label>
									<label class="radio-inline"> 
										<input id="bean_enable_1" class="form-control" value="1" type="radio" name="enable">禁用
									</label>
								</c:otherwise>
							</c:choose>
						
							
						</div>
						</div>
					</div>
					<shiro:hasPermission name="orgUser:dept.edit"> 
					<c:if test="${deptTree!=null && fn:length(deptTree)>0}">
					<div class="form-group col-md-12">
						  <div class="col-md-10">
							  <label class="col-md-2 control-label"  style="text-align: left;float:left ">
							  	部门列表
							  </label>
							  <div class="tree tree-plus-minus tree-solid-line tree-unselectable deptTree">
								
									<c:if test="${myDeptBeans!=null && fn:length(myDeptBeans)>0 }">
										<c:set var="myDeptBeansx" value="${myDeptBeans}" scope="request" />
									</c:if>
									<c:forEach items="${deptTree}" var="b">
										<c:set var="dcode" value="${b.id}" scope="request" />  
										<c:set var="checkBoxX" value="" />
										<c:if test="${myDeptBeans!=null && fn:length(myDeptBeans)>0 }">
											<c:forEach var="myDept" items="${myDeptBeans}">
												<c:if test="${myDept.id==b.id}">
													<c:set var="checkBoxX" value='oldc="1" checked="checked"' />
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
															<input type="checkbox" class="${dcode}" level="0" ${checkBoxX} id="x_${b.id}" value="${b.id}" name="deptIdArray">
															${b.name}
															</label>
														</div>
													</div>
													<c:if test="${fn:length(b.beans)>0}">
														<div class="tree-folder-content">
															<c:set var="treeList" value="${b.beans}" scope="request" />  
															<c:set var="numx" value="1" scope="request" />  
															<c:import url="org02_r_checkbox.jsp" />
															<c:set var="dcode" value="" scope="request" />  
														</div>
													</c:if>
												</div>
											</c:when>
											<c:otherwise>
												<div class="tree-item">
													<i class="tree-dot"></i>
													<div class="tree-item-name">
														<label>
														<input type="checkbox" class="${dcode}" level="0" ${checkBoxX} id="x_${b.id}" value="${b.id}" name="deptIdArray">
														${b.name}
														</label>
													</div>
												</div>
												<c:if test="${fn:length(b.beans)>0}">
													<div class="tree-folder-content">
														<c:set var="treeList" value="${b.beans}" scope="request" />  
														<c:set var="numx" value="1" scope="request" />  
														<c:import url="org02_r_checkbox.jsp" />
														<c:set var="dcode" value="" scope="request" />  
													</div>
												</c:if>
											</c:otherwise>
										</c:choose>
									</c:forEach>
							
							</div>
					  </div>
					</div>
					</c:if>
					</shiro:hasPermission>
					<%-- <div class="form-group col-md-12">
						<label class="col-md-1 control-label" for="bean_type">用户类型</label>
						<div class="col-md-2">
							<label class=""> 
								<input id="bean_type0" class="form-control" value="0" type="radio" name="type">管理员
							</label> 
							<label class=""> 
								<input id="bean_type1" class="form-control" value="1" type="radio" name="type">普通会员
							</label>
						</div>
						<label class="col-md-1 control-label" for="bean_lastLogin">最后登录日期</label>
						<div class="col-md-2">${bean.lastLogin}</div>
						<label class="col-md-1 control-label" for="bean_count">登录次数</label>
						<div class="col-md-1">${bean.count}</div>
					</div> --%>

					<div class="form-actions">
						<div style="width: 100px; margin: 0 auto;">
							<input class="btn btn-primary" type="submit" value="保存" /> | <a
								href="javascript:;" onclick="closeMyBoxLayer();">返回</a>
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
<%@ include file="../include/public_last_js_css.jsp"%>
<script type="text/javascript"
	src="${basePath}/static/plugins/bootstrapvalidator/vendor/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${basePath}/static/plugins/bootstrapvalidator/dist/js/bootstrapValidator.js"></script>
<script type="text/javascript"
	src="${basePath}/static/plugins/metronic/v4.1.0/theme/assets/global/plugins/select2/select2.min.js"></script>
<script type="text/javascript">
function cPwd(val){
	if(val){
		$('#bean_pwd').val($.md5($.trim(val)));
	}else{
		$('#bean_pwd').val('');
	}
}
//部门树级联选中
function deptTreeCheck(){
	$('.deptTree').find('input[type=checkbox]').click(function(){
		//var level=$(this).attr('level');
		if($(this).is(':checked')){
			var _class=$(this).attr('class');
			if(_class){
				var _classArray=_class.split(" ");
				if(_classArray){
					//--当前节点选中--自动选中上级节点
					for(var n=0;n<_classArray.length;n++){
						$('#x_'+_classArray[n]).attr('checked','checked');
						$('#x_'+_classArray[n]).parent().attr('class','checked');
					}
				}
			}
		}else{
// 			1.--当前节点-去除选中状态--恢复上级节点-默认状态--功能废弃
// 			for(var n=0;n<_classArray.length-1;n++){
// 				if($('#x_'+_classArray[n]).attr('oldc')){
// 					$('#x_'+_classArray[n]).attr('checked','checked');
// 					$('#x_'+_classArray[n]).parent().attr('class','checked');
// 				}else{
// 					$('#x_'+_classArray[n]).removeAttr('checked');
// 					$('#x_'+_classArray[n]).parent().removeAttr('class');
// 				}
// 			}
			//2.去除下级节点选中状态
			$(this).parent().parent().parent().parent().parent().next().find('span[class=checked]').removeAttr('class');
			$(this).parent().parent().parent().parent().parent().next().find('input[type=checkbox]').removeAttr('checked');
		}
	});
}



$(document).ready(function() {
	deptTreeCheck();
	$('#org02_01').bootstrapValidator({
		message: 'This value is not valid',
		feedbackIcons: {
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {
			 <c:if test="${bean.userid==null}">
			"userid": {
				 validators: {
					notEmpty: {
						 message: '不能为空'
					 }
					 ,
					 stringLength: {
						 max: 64,
						 message: '字符不能超过64个'
					 }
					 ,
                     remote: {
                       type: "post",
                         url: '${basePath}/h/org02.isUidYN',
                         data: function (validator) {
                             return {
                                 uid: validator.getFieldElements('bean.userid').val()
                             };
                         },
                         message: '此id已存在!请重新输入!',
                         delay: 2000
                    }
				 }
			 }
			 , 
			"pwd_x": {
				 validators: {
					 notEmpty: {
						 message: '不能为空'
					 }
					 ,
					 stringLength: {
						 max: 64,
						 message: '字符不能超过64个'
					 }
				 }
			 }
			 ,
			 </c:if>
			"name": {
				 validators: {
					 notEmpty: {
						 message: '不能为空'
					 }
					 ,
					 stringLength: {
						 max: 100,
						 message: '字符不能超过100个'
					 }
				 }
			 }
			 , 
			"jobNo": {
				 validators: {
					 stringLength: {
						 max: 64,
						 message: '字符不能超过64个'
					 }
				 }
			 }
			 , 
			"mobile": {
				 validators: {
					 stringLength: {
						 max: 50,
						 message: '字符不能超过50个'
					 }
				 }
			 }
			 , 
			"tel": {
				 validators: {
					 stringLength: {
						 max: 50,
						 message: '字符不能超过50个'
					 }
				 }
			 }
			 , 
			"email": {
				 validators: {
					 stringLength: {
						 max: 64,
						 message: '字符不能超过64个'
					 }
				 },
				 emailAddress: {
                     message: '请输入正确的邮箱地址!'
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
</html>
