var pageii = null;
var grid = null;
$(function() {
	
	grid = lyGrid({
		pagId : 'paging',
		l_column : [{
			colkey : "versionNumber",
			name : "版本号",
			isSort:true
		}, {
			colkey : "title",
			name : "标题",
			isSort:true
		}, {
			colkey : "fileUrl",
			name : "文件URL",
			isSort:true
		}, {
			colkey : "platform",
			name : "应用类型",
			isSort:true
		},  {
			colkey : "modelType",
			name : "用户类型",
			isSort:true
		}, {
			colkey : "downLoadURL",
			name : "版本连接地址(安卓需要)",
			isSort:true
		}, {
			colkey : "updateContent",
			name : "更新内容",
			isSort:true
		}, {
			colkey : "updateDate",
			name : "更新日期",
			isSort:true,
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}
		},{
			colkey : "id",
			name : "id",
			isSort:true,
		}],
		jsonUrl : rootPath + '/versioninfo/findByPage.shtml',
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
});
	

$("#addVersioninfo").click("click", function() {
	addVersioninfo();
});
$("#editVersioninfo").click("click", function() {
	editVersioninfo();
});
$("#delVersioninfo").click("click", function() {
	delVersioninfo();
});

function addVersioninfo() {
	pageii = layer.open({
		title : "新增",
		type : 2,
		area : [ "600px", "80%" ],
		content : rootPath + '/versioninfo/addUI.shtml'
	});
}

function editVersioninfo() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox.length > 1 || cbox == "") {
		layer.msg("只能选中一个");
		return;
	}
	pageii = layer.open({
		title : "编辑",
		type : 2,
		area : [ "600px", "80%" ],
		content : rootPath + '/versioninfo/editUI.shtml?id=' + cbox
	});
}
function delVersioninfo() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox == "") {
		layer.msg("请选择删除项！！");
		return;
	}
	layer.confirm('是否删除？', function(index) {
		var url = rootPath + '/versioninfo/deleteEntity.shtml';
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