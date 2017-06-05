<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	function page(n,s){
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#inputForm").submit();
    	return false;
    }
	</script>
</head>
<body> 
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/diancan/MealInfo/">用户信息</a></li>
		<li><a href="${ctx}/diancan/MealInfo/form">用户添加</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="mealInfo" action="${ctx}/diancan/MealInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>菜谱名称：</label> <form:input path="mealName" htmlEscape="false" class="input-medium"/><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>菜谱名称</th><th>菜谱类型</th><th>显示时间</th><th>创建人</th><th>创建时间</th><th>修改人</th><th>修改时间</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="user">
			<tr>
				<td>${user.mealName}</td>
				<%-- <td>${user.mealType}</td> --%>
				<td>${fns:getDictLabel(user.mealType, 'meal_type', '')}</td>
				<td>${user.showDate}</td>
				<td>${user.createBy.name}</td>
				<%-- <td>${user.createDate}</td> --%>
				<td><fmt:formatDate value="${user.createDate}" type="both"/></td>
				<td>${user.updateBy.name}</td>
				<%-- <td>${user.updateDate}</td> --%>
				<td><fmt:formatDate value="${user.updateDate}" type="both"/></td>
				<td>
    				<a href="${ctx}/diancan/MealInfo/form?id=${user.id}">修改</a>
					<a href="${ctx}/diancan/MealInfo/delete?id=${user.id}" onclick="return confirmx('确认要删除该菜谱吗？', this.href)">删除</a>
    			</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>