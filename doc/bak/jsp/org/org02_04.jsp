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
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" session="false"%>
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
<link
	href="${basePath}/static/plugins/metronic/v4.1.0/theme/assets/admin/layout/css/themes/light.css"
	rel="stylesheet" type="text/css" id="style_color" />

</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed">
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		<!-- BEGIN PAGE CONTENT-->
		<div class="row">
			<div class="col-md-12">
				<form id="org02_04" method="post" class="form-horizontal note" accept-charset="UTF-8"
					action="javascript:submitUserInfo();">
					<input type="hidden"
						name="id" value="${bean.id}" />
					<input type="hidden" name="type" id="bean_type" value="0">
					<div class="col-md-6 alert alert-info">
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
								<span class="help-block">不修改请勿填写</span>
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
					</div>
					<div class="form-actions">
						<div style="width: 100px; margin: 0 auto;">
							<input class="btn btn-primary"  type="submit" value="保存" /> | <a
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
<script type="text/javascript">
function cPwd(val){
	if(val){
		$('#bean_pwd').val($.md5($.trim(val)));
	}else{
		$('#bean_pwd').val('');
	}
}
function submitUserInfo(){
	//jQuery("#org02_04").attr('action','${basePath}/h/org02.update');
	jQuery("#org02_04").ajaxSubmit({  
        type:"post",  //提交方式  
        url:"${basePath}/h/org02.update", //请求url  
        success:function(data){ //提交成功的回调函数  
            if(data==1){
    			//myAlert_success('修改成功!');
    			alert('修改成功!');
    			closeMyBoxLayer();
    		}else{
    			myAlert_error(data);
    		}
        }  
    });
}
$(document).ready(function() {
	$('#org02_04').bootstrapValidator({
		message: 'This value is not valid',
		feedbackIcons: {
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {
			 <c:if test="${bean.userid==null}">
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
