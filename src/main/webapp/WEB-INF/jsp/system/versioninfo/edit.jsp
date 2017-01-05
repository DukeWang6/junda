<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/common.jspf"%>
<script type="text/javascript" src="${ctx}/js/system/versioninfo/edit.js"></script>

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
		action="${ctx}/versioninfo/editEntity.shtml">
		<section class="panel panel-default">
		<input type="hidden" class="form-control checkacc"
			value="${versioninfo.ID}" name="versionInfoFormMap.ID" id="ID">
		<div class="panel-body">
			<div class="form-group">
				<label class="col-sm-3 control-label">请输入版本号</label>
				<div class="col-sm-9">
					<input type="text" class="form-control checkversion"
						name="versionInfoFormMap.VersionNumber" id="VersionNumber" value='${versioninfo.VersionNumber}'>
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
		
			<div class="form-group">
				<label class="col-sm-3 control-label">请输入标题</label>
				<div class="col-sm-9">
					<input type="text" class="form-control checktitle"
						name="versionInfoFormMap.Title" id="Title"  value='${versioninfo.Title}'>
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			
			<div class="form-group">
				<label class="col-sm-3 control-label">请输入文件URL</label>
				<div class="col-sm-9">
					<input type="text" class="form-control checkfile"
						name="versionInfoFormMap.FileUrl" id="FileUrl" value='${versioninfo.FileUrl}'>
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			
			<div class="form-group">
				<label class="col-sm-3 control-label">请输入应用类型</label>
				<div class="col-sm-9">
					<button data-toggle="dropdown"
						class="btn btn-sm btn-default dropdown-toggle">
						<span class="dropdown-label">${versioninfo.Platform}</span><span class="caret"></span>
					</button>
					<ul class="dropdown-menu dropdown-select">
						<li class=""><a href="#"><input type="radio"
								name="versionInfoFormMap.Platform" value="0" checked="checked">安卓</a></li>
						<li class=""><a href="#"><input type="radio"
								name="versionInfoFormMap.Platform" value="1">苹果</a></li>
					</ul>
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>

			<div class="form-group">
				<label class="col-sm-3 control-label">请输入用户类型</label>
				<div class="col-sm-9">
					<button data-toggle="dropdown"
						class="btn btn-sm btn-default dropdown-toggle">
						<span class="dropdown-label">${versioninfo.ModelType}</span><span class="caret"></span>
					</button>
					<ul class="dropdown-menu dropdown-select">
						<li class=""><a href="#"><input type="radio"
								name="versionInfoFormMap.ModelType" value="0" checked="checked">乘客</a></li>
						<li class=""><a href="#"><input type="radio"
								name="versionInfoFormMap.ModelType" value="1">出租车司机</a></li>
						<li class=""><a href="#"><input type="radio"
								name="versionInfoFormMap.ModelType" value="2">小件快运司机</a></li>
						<li class=""><a href="#"><input type="radio"
								name="versionInfoFormMap.ModelType" value="3">代驾司机</a></li>
					</ul>
				</div>
			</div>
			
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">版本连接地址(安卓需要)</label>
				<div class="col-sm-9">
					<input type="text" class="form-control checkgroup"
						name="versionInfoFormMap.DownLoadURL" id="DownLoadURL" value='${versioninfo.DownLoadURL}'>
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">更新内容</label>
				<div class="col-sm-9">
					<input type="text" class="form-control checkupdatecontent"
						name="versionInfoFormMap.UpdateContent" id="UpdateContent" value='${versioninfo.UpdateContent}'>
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