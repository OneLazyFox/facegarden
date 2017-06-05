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
		<li class="active"><a href="${ctx}/diancan/mealUserinfo/">用户信息</a></li>
		<li><a href="${ctx}/diancan/mealUserinfo/form">用户添加</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="mealUser" action="${ctx}/diancan/mealUserinfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>姓名：</label> <form:input path="userName" htmlEscape="false" class="input-medium"/><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>姓名</th><th>帐号</th><th>类型</th><th>状态</th><th>创建时间</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="user">
			<tr>
				<td>${user.userName}</td>
				<td>${user.account}</td>
				<%-- <td>${user.type}</td> --%>
				<td>${fns:getDictLabel(user.type, 'mealType', '')}</td>
				<td>${fns:getDictLabel(user.delFlag, 'del_flag', '')}</td>
				<td><fmt:formatDate value="${user.createDate}" type="both"/></td>
				<td>
    				<a href="${ctx}/diancan/mealUserinfo/form?id=${user.id}">修改</a>
					<a href="${ctx}/diancan/mealUserinfo/delete?id=${user.id}" onclick="return confirmx('确认要删除该用户吗？', this.href)">删除</a>
    			</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>