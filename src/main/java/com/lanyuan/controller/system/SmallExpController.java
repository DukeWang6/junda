package com.lanyuan.controller.system;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lanyuan.mapper.SmallExpMapper;
import com.lanyuan.controller.index.BaseController;
import com.lanyuan.entity.OrdersFormMap;
import com.lanyuan.entity.SmallExpFormMap;
import com.lanyuan.plugin.PageView;
import com.lanyuan.util.Common;
import com.lanyuan.util.constant.ModelType;
import com.lanyuan.util.constant.OrderState;
import com.lanyuan.util.constant.GoodsWithOwner;
import com.lanyuan.util.constant.IsReceiptedByOthor;
import com.lanyuan.util.constant.IsArrivalOnTime;

@Controller
@RequestMapping("/smallexp/")
public class SmallExpController extends BaseController {
	@Inject
	private SmallExpMapper smallExpMapper;
	 
	@RequestMapping("list")
	public String listUI(Model model) throws Exception { 
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/smallexp/list";
	}

	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage( String pageNow,
		String pageSize,String column,String sort) throws Exception {
		SmallExpFormMap smallExpFormMap = getFormMap(SmallExpFormMap.class);
		smallExpFormMap=toFormMap(smallExpFormMap, pageNow, pageSize,smallExpFormMap.getStr("orderby"));
		if(smallExpFormMap.containsKey("incarDate")){
			String dateRange = (String) smallExpFormMap.get("incarDate");
			String incarDateFrom = dateRange.split(" - ")[0];
			String incarDateTo = dateRange.split(" - ")[1];
			smallExpFormMap.put("incarDateFrom", incarDateFrom);
			smallExpFormMap.put("incarDateTo", incarDateTo);
		}
		if(smallExpFormMap.containsKey("outcarDate")){
			String dateRange = (String) smallExpFormMap.get("outcarDate");
			String outcarDateFrom = dateRange.split(" - ")[0];
			String outcarDateTo = dateRange.split(" - ")[1];
			smallExpFormMap.put("outcarDateFrom", outcarDateFrom);
			smallExpFormMap.put("outcarDateTo", outcarDateTo);
		}
		smallExpFormMap.put("column", column);
		smallExpFormMap.put("sort", sort);
		
		List<SmallExpFormMap> list = smallExpMapper.findSmallExpInfoPage(smallExpFormMap);
		for(int i=0;i<list.size();i++){
			SmallExpFormMap smallExp = list.get(i);
			int ordersState = (Integer) smallExp.get("OrderState");
			smallExp.put("OrderState", OrderState.getName(ordersState));
			
			int goodsWithOwner = (Integer) smallExp.get("GoodsWithOwner");
			smallExp.put("GoodsWithOwner", GoodsWithOwner.getName(goodsWithOwner));
			
			int isReceiptedByOthor = (Integer) smallExp.get("IsReceiptedByOthor");
			smallExp.put("IsReceiptedByOthor", IsReceiptedByOthor.getName(isReceiptedByOthor));
			
			int isArrivalOnTime = (Integer) smallExp.get("IsArrivalOnTime");
			smallExp.put("IsArrivalOnTime", IsArrivalOnTime.getName(isArrivalOnTime));
		}
        pageView.setRecords(list);
        return pageView;
	}
	
	@RequestMapping("isExist")
	@ResponseBody
	public boolean isExist(String title) {
		SmallExpFormMap smallExpFormMap = smallExpMapper.findbyFrist("title", title, SmallExpFormMap.class);
		if (smallExpFormMap == null) {
			return true;
		} else {
			return false;
		}
	}
}