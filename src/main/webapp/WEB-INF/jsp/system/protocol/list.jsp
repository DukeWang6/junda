<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/protocol/list.js"></script>
	<div class="m-b-md">
		<form class="form-inline" role="form" id="searchForm"
			name="searchForm">
			<div class="form-group">
				<label class="control-label"><span
					class="h4 font-thin v-middle">用户类型:</span></label>
				<div class="btn-group m-r">
					<button data-toggle="dropdown"
						class="btn btn-sm btn-default dropdown-toggle">
						<span class="dropdown-label"></span>乘客<span class="caret"></span>
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
	
	<div id="callback_div" class="table-responsive" style="display: none;">
		<div id="paging_callback" class="pagclass"></div>
	</div>
