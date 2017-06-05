<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>微信信息管理</title>
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
		<li class="active"><a href="${ctx}/diancan/msg/">微信用户列表</a></li>
		<li><a href="${ctx}/diancan/msg/form">用户账号添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="wechatMsg" action="${ctx}/diancan/msg/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>用户 ：</label><form:input path="account" htmlEscape="false" maxlength="50" class="input-medium"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>用户账号</th><th>内容</th><th>创建时间</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="wechatMsg">
			<tr>
				<td>${wechatMsg.account}</td>
				<td>${wechatMsg.content}</td>
				<td><fmt:formatDate value="${wechatMsg.createDate}" type="both"/></td>
				<td>
                   <a href="${ctx}/diancan/msg/form?id=${wechatMsg.id}">修改</a>
                     <a href="${ctx}/diancan/msg/delete?id=${wechatMsg.id}" onclick="return confirmx('确认要删除用户吗？', this.href)">删除</a>
                </td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>