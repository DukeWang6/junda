<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/systeminfo/list.js"></script>
<link rel="stylesheet" type="text/css" media="all" href="js/date/daterangepicker-bs3.css" />
<script type="text/javascript" src="js/date/moment.js"></script>
<script type="text/javascript" src="js/date/daterangepicker.js"></script>
	<div class="m-b-md">
		<form class="form-inline" role="form" id="searchForm"
			name="searchForm">
			<div class="form-group">
				<label class="control-label"> <span
					class="h4 font-thin v-middle">标题:</span></label> <input
					class="input-medium ui-autocomplete-input" id="title"
					name="systemInfoFormMap.title">
			</div>
			
			<div class="form-group">
				<label class="control-label"> <span
					class="h4 font-thin v-middle">发布日期:</span></label> <input
					class="input-medium ui-autocomplete-input" id="pushDate"
					name="systemInfoFormMap.pushDate">
			</div>
			
			<div class="form-group">
				<label class="control-label"><span
					class="h4 font-thin v-middle">是否置顶:</span></label>
				<div class="btn-group m-r">
					<button data-toggle="dropdown"
						class="btn btn-sm btn-default dropdown-toggle">
						<span class="dropdown-label">是</span> <span class="caret"></span>
					</button>
					<ul class="dropdown-menu dropdown-select">
						<li class=""><a href="#"><input type="radio"
								name="systemInfoFormMap.isOntop" value="1" checked="checked">是</a></li>
						<li class="active"><a href="#"><input type="radio"
								name="systemInfoFormMap.isOntop" value="0">否</a></li>
					</ul>
				</div>
			</div>
			
			<a href="javascript:void(0)" class="btn btn-default" id="search">查询</a>
		</form>
	</div>
	<header class="panel-heading">
	<div class="doc-buttons">
		<c:forEach items="${res}" var="key">
			${key.description}
		</c:forEach>
	</div>
	</header>
	<div class="table-responsive">
		<div id="paging" class="pagclass"></div>
	</div>
	
<script type="text/javascript">
	$(document).ready(function() {
	   $('#pushDate').daterangepicker({
	     timePicker: true,
	     timePickerIncrement: 30,
	     format: 'YYYY-MM-DD HH:mm'
	   }, function(start, end, label) {
	     console.log(start.toISOString(), end.toISOString(), label);
	   });
	});
</script>
