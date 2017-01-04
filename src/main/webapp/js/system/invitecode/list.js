var pageii = null;
var grid = null;
$(function() {
	
	grid = lyGrid({
		pagId : 'paging',
		l_column : [{
			colkey : "inviteCode",
			name : "邀请码",
			isSort:true,
		}, {
			colkey : "groupName",
			name : "组织名称",
			isSort:true,
		}],
		jsonUrl : rootPath + '/invitecode/findByPage.shtml',
		dymCol:true,
		checkbox : true,
		serNumber : true
	});
	$("#search").click("click", function() {// 绑定查询按扭
		var searchParams = $("#searchForm").serializeJson();// 初始化传参数
		grid.setOptions({
			data : searchParams
		});
	});
	
	$("#callback_test").click("click", function() {
		paging_callback();
	});
});
function paging_callback(){
	var parm = {
			pagId : 'paging_callback',
			l_column : [  {
				colkey : "id",
				name : "id",
			}, {
				colkey : "title",
				name : "标题",
				isSort:true,
			}, {
				colkey : "content",
				name : "内容",
				isSort:true,
			}, {
				colkey : "updateDate",
				name : "更新时间"
			} ],
			jsonUrl : rootPath + '/aboutus/findByPage.shtml',
			checkbox : true,
			serNumber : true
	}
	
	var grid_c=lyGrid(parm,function(c,d){
		//回调方法
		pageii = layer.open({
			title : "回调方法生成表格", 
			type : 1,
			area : [ "800px", "400px" ],
			content : $("#callback_div"),btn: ['确认', '取消']
		  	,yes: function(sum, layero){ //或者使用btn1
		  		layer.close(index);
			 },cancel: function(index){ //或者使用btn2
				 layer.close(index);
			 }
		});
	});
}
$("#addInvitecode").click("click", function() {
	addInvitecode();
});
$("#editInvitecode").click("click", function() {
	editInvitecode();
});
$("#delInvitecode").click("click", function() {
	delInvitecode();
});

function addInvitecode() {
	pageii = layer.open({
		title : "新增",
		type : 2,
		area : [ "600px", "80%" ],
		content : rootPath + '/invitecode/addUI.shtml'
	});
}

function editInvitecode() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox.length > 1) {
		layer.msg("只能选中一个");
		return;
	}
	if (cbox == "") {
		layer.msg("必须选中一个");
		return;
	}
	pageii = layer.open({
		title : "编辑",
		type : 2,
		area : [ "600px", "80%" ],
		content : rootPath + '/invitecode/editUI.shtml?id=' + cbox
	});
}
function delInvitecode() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox == "") {
		layer.msg("请选择删除项！！");
		return;
	}
	layer.confirm('是否删除？', function(index) {
		var url = rootPath + '/invitecode/deleteEntity.shtml';
		var s = CommnUtil.ajax(url, {
			ids : cbox.join(",")
		}, "json");
		if (s == "success") {
			layer.msg('删除成功');
			grid.loadData();
		} else {
			layer.msg('删除失败');
		}
	});
}
function permissions() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox.length > 1 || cbox == "") {
		layer.msg("请选择一个对象！");
		return;
	}
	var url = rootPath + '/resources/permissions.shtml?userId='+cbox;
	pageii = layer.open({
		title : "分配权限",
		type : 2,
		area : [ "700px", "80%" ],
		content : url
	});
}