var pageii = null;
var grid = null;
$(function() {
	
	grid = lyGrid({
		pagId : 'paging',
		l_column : [{
			colkey : "LocationAddress",
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
			colkey : "IncarTime",
			name : "上车时间",
			isSort:true,
			renderData : function(rowindex,data, rowdata, column) {
				if(data==null||data=="") {
					return null;
				} else {
					return new Date(data).format("yyyy-MM-dd hh:mm:ss");
				}
			}
		},{
			colkey : "OutcarTime",
			name : "下车时间",
			isSort:true,
			renderData : function(rowindex,data, rowdata, column) {
				if(data==null||data=="") {
					return null;
				} else {
					return new Date(data).format("yyyy-MM-dd hh:mm:ss");
				}
			}
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
			colkey : "modelType",
			name : "用车类型",
			isSort:true,
		}],
		jsonUrl : rootPath + '/orders/findByPage.shtml',
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