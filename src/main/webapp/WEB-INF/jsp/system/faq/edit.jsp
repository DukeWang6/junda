<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/common.jspf"%>
<script type="text/javascript" src="${ctx}/js/system/faq/edit.js"></script>

<style type="text/css">
.col-sm-4 {
	width: 18%;
	float: left;
}

.col-sm-8 {
	width: 82%;
	float: left;
}
</style>
</head>
<body>
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="form" name="form" class="form-horizontal" method="post"
		action="${ctx}/faq/editEntity.shtml">
		<section class="panel panel-default">
		<div class="panel-body">
			<div class="form-group">
			<input type="hidden" class="form-control checkacc" value="${faq.id}"
			name="faqFormMap.id" id="id">
				<label class="col-sm-4 control-label">请输入标题</label>
				<div class="col-sm-8">
					<input type="text" class="form-control checktitle"
						placeholder="请输入标题" name="faqFormMap.title" id="title" value=${faq.title} >
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-4 control-label">请输入内容</label>
				<div class="col-sm-8">
					<textarea class="form-control checkcontent" placeholder="请输入内容" name="faqFormMap.content" id="content" rows="15">${faq.content}</textarea>
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