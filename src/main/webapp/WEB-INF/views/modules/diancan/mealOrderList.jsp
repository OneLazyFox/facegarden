<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/diancan/order/">订单用户列表</a></li>
		<li><a href="${ctx}/diancan/order/form">订单用户添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="mealOrder" action="${ctx}/diancan/order/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>订单用户 ：</label><form:input path="userId" htmlEscape="false" maxlength="50" class="input-medium"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>订单用户</th><th>菜谱序号</th><th>订餐状态</th><th>价格</th><th>付款状态</th><th>点餐时间</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="mealOrder">
			<tr>
				<td>${mealOrder.userId}</td>
				<td>${mealOrder.mealNo}</td>
				<td>${fns:getDictLabel(mealOrder.status, 'Status', '')}</td>
				<td>${mealOrder.price}</td>
				<td>${fns:getDictLabel(mealOrder.paymentStatus, 'PaymentStatus', '')}</td>
				<td><fmt:formatDate value="${mealOrder.createDate}" type="both"/></td>
				<td>
                     <a href="${ctx}/diancan/order/form?id=${mealOrder.id}">修改</a>
                     <a href="${ctx}/diancan/order/delete?id=${mealOrder.id}" onclick="return confirmx('确认要删除订单用户吗？', this.href)">删除</a>
                </td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>