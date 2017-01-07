var pageii = null;
var grid = null;
$(function() {
	
	grid = lyGrid({
		pagId : 'paging',
		l_column : [{
			colkey : "Location",
			name : "起始地址",
			isSort:true
		}, {
			colkey : "Destination",
			name : "目的地地址",
			isSort:true
		}, {
			colkey : "OrdersState",
			name : "订单状态",
			isSort:true
		}, {
			colkey : "Subsidy",
			name : "补偿费",
			isSort:true
		},  {
			colkey : "ActualPrice",
			name : "订单金额",
			isSort:true
		}, {
			colkey : "PhoneNumber",
			name : "乘客电话",
			isSort:true
		}, {
			colkey : "ID",
			name : "订单ID",
			isSort:true
		},{
			colkey : "DriverTelephone",
			name : "司机电话",
			isSort:true
		},{
			colkey : "CancelOrders",
			name : "取消原因",
			isSort:true
		},{
			colkey : "GrabDate",
			name : "抢单时间",
			isSort:true,
			renderData : function(rowindex,data, rowdata, column) {
				if(data==null||data=="") {
					return null;
				} else {
					return new Date(data).format("yyyy-MM-dd hh:mm:ss");
				}
			}
		},{
			colkey : "PickupTime",
			name : "取件时间",
			isSort:true,
			renderData : function(rowindex,data, rowdata, column) {
				if(data==null||data=="") {
					return null;
				} else {
					return new Date(data).format("yyyy-MM-dd hh:mm:ss");
				}
			}
		},{
			colkey : "ArrivedTime",
			name : "到达时间",
			isSort:true,
			renderData : function(rowindex,data, rowdata, column) {
				if(data==null||data=="") {
					return null;
				} else {
					return new Date(data).format("yyyy-MM-dd hh:mm:ss");
				}
			}
		},{
			colkey : "ExpressName",
			name : "快递名称",
			isSort:true,
		},{
			colkey : "GoodsWithOwner",
			name : "是否押运同行",
			isSort:true,
		},{
			colkey : "IsReceiptedByOthor",
			name : "是否自己接货",
			isSort:true,
		},{
			colkey : "ReceiptName",
			name : "接货人",
			isSort:true,
		},{
			colkey : "ReceiptTel",
			name : "接货人电话",
			isSort:true,
		},{
			colkey : "IsArrivalOnTime",
			name : "是否按时送达",
			isSort:true,
		}],
		jsonUrl : rootPath + '/smallExp/findByPage.shtml',
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