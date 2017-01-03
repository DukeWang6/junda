<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/common.jspf"%>
<script type="text/javascript" src="${ctx}/js/system/protocol/edit.js"></script>

<style type="text/css">
.col-sm-3 {
	width: 20%;
	float: left;
}

.col-sm-9 {
	width: 80%;
	float: left;
}
</style>
</head>
<body>
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="form" name="form" class="form-horizontal" method="post"
		action="${ctx}/protocol/editEntity.shtml">
		<section class="panel panel-default">
		<input type="hidden" class="form-control checkacc"
			value="${protocol.id}" name="protocolFormMap.id" id="id">
		<div class="panel-body">
			<div class="form-group">
				<label class="col-sm-3 control-label">用户类型:</label>
				<div class="col-sm-9">
					<button data-toggle="dropdown"
						class="btn btn-sm btn-default dropdown-toggle">
						<span class="dropdown-label"></span>${protocol.modelValue}<span class="caret"></span>
					</button>
					<ul class="dropdown-menu dropdown-select">
						<li class=""><a href="#"><input type="radio"
								name="protocolFormMap.modelType" value="0" checked="checked">乘客</a></li>
						<li class=""><a href="#"><input type="radio"
								name="protocolFormMap.modelType" value="1">出租车司机</a></li>
						<li class=""><a href="#"><input type="radio"
								name="protocolFormMap.modelType" value="2">小件快运司机</a></li>
						<li class=""><a href="#"><input type="radio"
								name="protocolFormMap.modelType" value="3">代驾司机</a></li>
					</ul>
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">请输入标题</label>
				<div class="col-sm-9">
					<input type="text" class="form-control checkTitle" value="${protocol.title}"
						placeholder="请输入邀请码" name="protocolFormMap.title" id="title">
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">请输入协议文件URL</label>
				<div class="col-sm-9">
					<input type="text" class="form-control checkgroup" value="${protocol.content}"
						placeholder="协议文件URL" name="protocolFormMap.content" id="content">
				</div>
			</div>
		</div>	
		<footer class="panel-footer text-right bg-light lter">
		<button type="submit" class="btn btn-success btn-s-xs">提交</button>
		</footer> </section>
	</form>
	<script type="text/javascript">
	onloadurl();
</script>
</body>
</html>