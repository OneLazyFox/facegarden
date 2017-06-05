<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>上传图片记录管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/imgUploadPeriodInfo/">上传图片记录列表</a></li>
	</ul>
	<br/>
		
	<div class="control-group">
		<label class="control-label">商家编码：${imgUploadPeriodInfo.merchantNo}</label>
	</div>
	<div class="control-group">
		<label class="control-label">上传周期：${imgUploadPeriodInfo.uploadPeriod}</label>
	</div>
	<div class="control-group">
		<label class="control-label">sku个数：${imgUploadPeriodInfo.skuNum}</label>
	</div>
	<div class="control-group">
		<label class="control-label">商家货号：${imgUploadPeriodInfo.skuCode}</label>
	</div>
	<div class="control-group">
		<label class="control-label">窗口图张数：${imgUploadPeriodInfo.windowImg}</label>
		<div class="controls">
			<ul style="margin: 0 0 10px 0;">
				<c:forEach var="comp" items="${windowImgList}">
					<li class="fl" style="text-align: left;">
						<div class="imgdiv" onclick="window.open('${comp }')">
							<img alt=""  width="300px" height="300px" src="${comp}">
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">客观图张数：${imgUploadPeriodInfo.objectiveImg}</label>
		<div class="controls">
			<ul  style="margin: 0 0 10px 0;">
				<c:forEach var="comp" items="${objectiveImgList}">
					<li class="fl" style="text-align: left;">
						<div class="imgdiv" onclick="window.open('${comp }')">
							<img alt="" width="300px" height="300px" src="${comp}">
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	
</body>
</html>