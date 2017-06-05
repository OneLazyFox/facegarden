<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#value").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/diancan/mealUserinfo/">用户列表</a></li>
		<li class="active"><a href="${ctx}/diancan/mealUserinfo/form?id=${mealUser.id}">用户${not empty mealUser.id?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="mealUser" action="${ctx}/diancan/mealUserinfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/> 
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">姓名:</label>
			<div class="controls">
				<form:input path="userName" htmlEscape="false" maxlength="50" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">帐号:</label>
			<div class="controls">
				<form:input path="account" htmlEscape="false" maxlength="50" class="required digits"/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">类型:</label>
			<div class="controls">
				<form:input path="type" htmlEscape="false" maxlength="50" class="required"/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">类型:</label>
			<div class="controls">
				<form:select path="type" class="required digits">
					<form:option value="" label="请选择:"/>
					<form:options items="${fns:getDictList('mealType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">状态:</label>
			<div class="controls">
				<form:input path="delFlag" htmlEscape="false" maxlength="11" class="required digits"/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">状态:</label>
			<div class="controls">
				<form:select path="delFlag" class="required digits">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('del_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>