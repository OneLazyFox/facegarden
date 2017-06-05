<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>上传图片记录管理</title>
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
		<li class="active"><a href="${ctx}/sys/imgUploadPeriodInfo/">上传图片记录列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="imgUploadPeriodInfo" action="${ctx}/sys/imgUploadPeriodInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>商家编码：</label>
				<form:input path="merchantNo" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>是否上传：</label>
				<form:checkboxes path="isHandle" items="${fns:getDictList('is_handle')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>商家编码</th>
				<th>上传周期</th>
				<th>sku个数</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<th>是否上传</th>
				<th>商家货号</th>
				<th>窗口图张数</th>
				<th>客观图张数</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="imgUploadPeriodInfo">
			<tr>
				<!-- <td><a href="${ctx}/sys/imgUploadPeriodInfo/form?id=${imgUploadPeriodInfo.id}">
					${imgUploadPeriodInfo.merchantNo}
				</a></td> -->
				<td>
					${imgUploadPeriodInfo.merchantNo}
				</td>
				<td>
					${imgUploadPeriodInfo.uploadPeriod}
				</td>
				<td>
					${imgUploadPeriodInfo.skuNum}
				</td>
				<td>
					<fmt:formatDate value="${imgUploadPeriodInfo.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${imgUploadPeriodInfo.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(imgUploadPeriodInfo.isHandle, 'is_handle', '')}
				</td>
				<td>
					${imgUploadPeriodInfo.skuCode}
				</td>
				<td>
					${imgUploadPeriodInfo.windowImg}
				</td>
				<td>
					${imgUploadPeriodInfo.objectiveImg}
				</td>
				<td>
    				<a href="${ctx}/sys/imgUploadPeriodInfo/viewPic?id=${imgUploadPeriodInfo.id}">查看图片</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>