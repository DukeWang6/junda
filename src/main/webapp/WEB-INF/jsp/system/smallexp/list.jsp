<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/smallexp/list.js"></script>
<link rel="stylesheet" type="text/css" media="all" href="js/date/daterangepicker-bs3.css" />
<script type="text/javascript" src="js/date/moment.js"></script>
<script type="text/javascript" src="js/date/daterangepicker.js"></script>
	<div class="m-b-md">
		<form class="form-inline" role="form" id="searchForm"
			name="searchForm">
			<div class="form-group">
				<label class="control-label"> <span
					class="h4 font-thin v-middle">上车时间 :</span></label> <input
					class="input-medium ui-autocomplete-input" id="incarDate"
					name="SmallExpFormMap.incarDate">
			</div>
			<div class="form-group">
				<label class="control-label"> <span
					class="h4 font-thin v-middle">下车时间 :</span></label> <input
					class="input-medium ui-autocomplete-input" id="outcarDate"
					name="SmallExpFormMap.outcarDate">
			</div>
			
			<div class="form-group">
				<label class="control-label"> <span
					class="h4 font-thin v-middle">司机电话:</span></label> <input
					class="input-medium ui-autocomplete-input" id="driverTel" 
					name="SmallExpFormMap.driverTel">
			</div>
			
			<div class="form-group">
				<label class="control-label"> <span
					class="h4 font-thin v-middle">乘客电话:</span></label> <input
					class="input-medium ui-autocomplete-input" id="userTel"
					name="SmallExpFormMap.userTel">
			</div>
			 
			<div class="form-group">
				<label class="control-label"><span
					class="h4 font-thin v-middle">订单状态:</span></label>
				<div class="btn-group m-r">
					<button data-toggle="dropdown"
						class="btn btn-sm btn-default dropdown-toggle">
						<span class="dropdown-label">全部</span> <span class="caret"></span>
					</button>
					<ul class="dropdown-menu dropdown-select">
						<li class="active"><a href="#"><input type="radio"
								name="SmallExpFormMap.orderState" value="">全部</a></li>
						<li class=""><a href="#"><input type="radio"
								name="SmallExpFormMap.orderState" value="1">等待接单</a></li>
						<li class=""><a href="#"><input type="radio"
								name="SmallExpFormMap.orderState" value="2">已接单</a></li>
						<li class=""><a href="#"><input type="radio"
								name="SmallExpFormMap.orderState" value="3">已结束</a></li>
						<li class=""><a href="#"><input type="radio"
								name="SmallExpFormMap.orderState" value="4">已取消</a></li>
						<li class=""><a href="#"><input type="radio"
								name="SmallExpFormMap.orderState" value="5">失效</a></li>
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
	   $('#incarDate').daterangepicker({
	     timePicker: true,
	     timePickerIncrement: 30,
	     format: 'YYYY-MM-DD HH:mm'
	   }, function(start, end, label) {
	     console.log(start.toISOString(), end.toISOString(), label);
	   });
	   $('#outcarDate').daterangepicker({
		     timePicker: true,
		     timePickerIncrement: 30,
		     format: 'YYYY-MM-DD HH:mm'
		   }, function(start, end, label) {
		     console.log(start.toISOString(), end.toISOString(), label);
		   });
	});
</script>
