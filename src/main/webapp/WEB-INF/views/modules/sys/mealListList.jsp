<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>菜单组合列表</title>
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
		<li class="active"><a href="${ctx}/diancan/mealList/">菜单组合列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="mealList" action="${ctx}/diancan/mealList/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<div >
		<label>组合名称：</label><form:input path="listName" htmlEscape="false"  class="input-medium"/>
		</div>
		
		<div style="margin-top:8px;">
		<label>日期范围：</label><input id="beginDate" name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${mealList.beginDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
		<label>&nbsp;--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><input id="endDate" name="endDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:formatDate value="${mealList.endDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
		&nbsp;&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
		</div>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>菜单组合名称 </th><th>序号</th><th>显示时间</th><th>创建时间</th><%-- <shiro:hasPermission name="diancan:mealList:edit"> --%><th>操作</th><%-- </shiro:hasPermission> --%></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="mealList">
			<tr>
				<td>${mealList.listName}</td>
				<td>${mealList.mealNo}</td>
				<td>${mealList.showDate}</td>
				<td><fmt:formatDate value="${mealList.createDate}" type="both"/></td>
				<%-- <shiro:hasPermission name="diancan:mealList:edit"> --%><td>		
				<a href="${ctx}/diancan/mealList/delete?id=${mealList.id}" onclick="return confirmx('确认要删除该菜单组合吗？', this.href)">删除</a>
				</td><%-- </shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>