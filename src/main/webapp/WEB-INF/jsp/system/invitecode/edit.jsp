<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/common.jspf"%>
<script type="text/javascript" src="${ctx}/js/system/invitecode/edit.js"></script>

<style type="text/css">
.col-sm-3 {
	width: 25%;
	float: left;
}

.col-sm-9 {
	width: 75%;
	float: left;
}
</style>
</head>
<body>
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="form" name="form" class="form-horizontal" method="post"
		action="${ctx}/invitecode/editEntity.shtml">
		<section class="panel panel-default">
		<input type="hidden" class="form-control"
			value="${inviteCode.id}" name="inviteCodeFormMap.id" id="id">
		<div class="panel-body">
			<div class="form-group">
				<label class="col-sm-3 control-label">请输入邀请码</label>
				<div class="col-sm-9">
					<input type="text" class="form-control checkinv" value="${inviteCode.InviteCode}"
						placeholder="请输入邀请码" name="inviteCodeFormMap.InviteCode" id="InviteCode">
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">请输入组织名称</label>
				<div class="col-sm-9">
					<input type="text" class="form-control checkgroup" value="${inviteCode.GroupName}"
						placeholder="请输入组织名称" name="inviteCodeFormMap.GroupName" id="GroupName">
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