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
		<li><a href="${ctx}/diancan/MealInfo/">用户列表</a></li>
		<li class="active"><a href="${ctx}/diancan/MealInfo/form?id=${mealInfo.id}">用户${not empty mealInfo.id?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="mealInfo" action="${ctx}/diancan/MealInfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/> 
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">菜谱名称:</label>
			<div class="controls">
				<form:input path="mealName" htmlEscape="false" maxlength="50" class="required"/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">菜谱类型:</label>
			<div class="controls">
				<form:input path="mealType" htmlEscape="false" maxlength="50" class="required digits"/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">菜谱类型:</label>
			<div class="controls">
				<form:select path="mealType" class="required digits">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('meal_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div style="margin-top:8px;">
			<label class="control-label">日期范围:</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="showDate" name="showDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${user.showDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>