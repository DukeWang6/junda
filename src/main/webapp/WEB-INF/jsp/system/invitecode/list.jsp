<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/invitecode/list.js"></script>
	<div class="m-b-md">
		<form class="form-inline" role="form" id="searchForm"
			name="searchForm">
			<div class="form-group">
				<label class="control-label"> <span
					class="h4 font-thin v-middle">邀请码:</span></label> <input
					class="input-medium ui-autocomplete-input" id="title"
					name="inviteCodeFormMap.inviteCode">
			</div>
			
			<div class="form-group">
				<label class="control-label"> <span
					class="h4 font-thin v-middle">组织名称:</span></label> <input
					class="input-medium ui-autocomplete-input" id="title"
					name="inviteCodeFormMap.groupName">
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
