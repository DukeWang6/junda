var pageii = null;
var grid = null;
$(function() {
	
	grid = lyGrid({
		pagId : 'paging',
		l_column : [{
			colkey : "modelValue",
			name : "用户类型",
			isSort:true
		}, {
			colkey : "title",
			name : "标题",
			isSort:true
		}, {
			colkey : "content",
			name : "协议文件URL",
			isSort:true
		}, {
			colkey : "updateDate",
			name : "更新日期",
			isSort:true,
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}
		}],
		jsonUrl : rootPath + '/protocol/findByPage.shtml',
		dymCol:true,
		checkbox : true,
		serNumber : true
	});
	$("#searchForm").click("click", function() {// 绑定查询按扭
		var searchParams = $("#searchForm").serializeJson();// 初始化传参数
		grid.setOptions({
			data : searchParams
		});
	});
});

$("#editProtocol").click("click", function() {
	editProtocol();
});

function editProtocol() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox.length > 1 || cbox == "") {
		layer.msg("只能选中一个");
		return;
	}
	pageii = layer.open({
		title : "编辑",
		type : 2,
		area : [ "600px", "80%" ],
		content : rootPath + '/protocol/editUI.shtml?id=' + cbox
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