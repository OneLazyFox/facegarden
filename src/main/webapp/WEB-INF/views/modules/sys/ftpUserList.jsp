<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>FTP用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
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
		<li class="active"><a href="${ctx}/sys/ftpUser/">FTP用户列表</a></li>
		<shiro:hasPermission name="sys:ftpUser:edit"><li><a href="${ctx}/sys/ftpUser/form">FTP用户添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ftpUser" action="${ctx}/sys/ftpUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>ftp帐号：</label>
				<form:input path="userid" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>ftp帐号</th>
				<th>是否有权限</th>
				<th>是否有写权限</th>
				<th>idle时间</th>
				<th>上传速率</th>
				<th>下载速率</th>
				<th>最大登录用户数</th>
				<th>最大登录管道数</th>
				<shiro:hasPermission name="sys:ftpUser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ftpUser">
			<tr>
				<td><a href="${ctx}/sys/ftpUser/form?id=${ftpUser.id}">
					${ftpUser.userid}
				</a></td>
				<td>
					${fns:getDictLabel(ftpUser.enableflag, 'enableflag', '')}
				</td>
				<td>
					${fns:getDictLabel(ftpUser.writepermission, 'writepermission', '')}
				</td>
				<td>
					${ftpUser.idletime}
				</td>
				<td>
					${ftpUser.uploadrate}
				</td>
				<td>
					${ftpUser.downloadrate}
				</td>
				<td>
					${ftpUser.maxloginnumber}
				</td>
				<td>
					${ftpUser.maxloginperip}
				</td>
				<shiro:hasPermission name="sys:ftpUser:edit"><td>
    				<a href="${ctx}/sys/ftpUser/form?id=${ftpUser.id}">修改</a>
					<a href="${ctx}/sys/ftpUser/delete?id=${ftpUser.id}" onclick="return confirmx('确认要删除该FTP用户吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>