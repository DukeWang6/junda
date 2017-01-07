package com.lanyuan.controller.system;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lanyuan.mapper.AboutUsMapper;
import com.lanyuan.mapper.OrdersMapper;
import com.lanyuan.controller.index.BaseController;
import com.lanyuan.entity.AboutUsFormMap;
import com.lanyuan.entity.InviteCodeFormMap;
import com.lanyuan.entity.OrdersFormMap;
import com.lanyuan.entity.ProtocolFormMap;
import com.lanyuan.entity.VersionInfoFormMap;
import com.lanyuan.plugin.PageView;
import com.lanyuan.util.Common;
import com.lanyuan.util.constant.ModelType;
import com.lanyuan.util.constant.OrderState;
import com.lanyuan.util.constant.CarType;

/**
 * 
 * @author lanyuan 2014-11-19
 * @Email: mmm333zzz520@163.com
 * @version 3.0v
 */
@Controller
@RequestMapping("/orders/")
public class OrdersController extends BaseController {
	@Inject
	private OrdersMapper ordersMapper;
	
	@RequestMapping("list")
	public String listUI(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/orders/list";
	}

	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage( String pageNow,
			String pageSize,String column,String sort) throws Exception {
		    OrdersFormMap orderFormMap = getFormMap(OrdersFormMap.class);
		
		if(orderFormMap.containsKey("incarDate")){
			String dateRange = (String) orderFormMap.get("incarDate");
			String incarDateFrom = dateRange.split(" - ")[0];
			String incarDateTo = dateRange.split(" - ")[1];
			orderFormMap.put("incarDateFrom", incarDateFrom);
			orderFormMap.put("incarDateTo", incarDateTo);
		}
		if(orderFormMap.containsKey("outcarDate")){
			String dateRange = (String) orderFormMap.get("outcarDate");
			String outcarDateFrom = dateRange.split(" - ")[0];
			String outcarDateTo = dateRange.split(" - ")[1];
			orderFormMap.put("outcarDateFrom", outcarDateFrom);
			orderFormMap.put("outcarDateTo", outcarDateTo);
		}
		
		orderFormMap=toFormMap(orderFormMap, pageNow, pageSize,orderFormMap.getStr("orderby"));
		orderFormMap.put("column", column);
		orderFormMap.put("sort", sort);
		
		List<OrdersFormMap> list = ordersMapper.findOrderInfoPage(orderFormMap);
		for(int i=0;i<list.size();i++){
			OrdersFormMap order = list.get(i);
			int ordersState = (Integer) order.get("OrdersState");
			order.put("OrdersState", OrderState.getName(ordersState));
			
			int modelType = (Integer) order.get("modelType");
			order.put("modelType", ModelType.getName(modelType));
			
		}
        pageView.setRecords(list);//不调用默认分页,调用自已的mapper中findUserPage
      
        return pageView;
	}
	
	
	@RequestMapping("isExist")
	@ResponseBody
	public boolean isExist(String title) {
		OrdersFormMap orderFormMap = ordersMapper.findbyFrist("title", title, OrdersFormMap.class);
		if (orderFormMap == null) {
			return true;
		} else {
			return false;
		}
	}
}