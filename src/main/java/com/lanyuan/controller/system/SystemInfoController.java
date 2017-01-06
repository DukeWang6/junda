package com.lanyuan.controller.system;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lanyuan.mapper.AboutUsMapper;
import com.lanyuan.mapper.SystemInfoMapper;
import com.lanyuan.controller.index.BaseController;
import com.lanyuan.entity.AboutUsFormMap;
import com.lanyuan.entity.InviteCodeFormMap;
import com.lanyuan.entity.ProtocolFormMap;
import com.lanyuan.entity.SystemInfoFormMap;
import com.lanyuan.plugin.PageView;
import com.lanyuan.util.Common;
import com.lanyuan.util.constant.ModelType;
import com.lanyuan.util.constant.IsOntopType;

/**
 * 
 * @author lanyuan 2014-11-19
 * @Email: mmm333zzz520@163.com
 * @version 3.0v
 */
@Controller
@RequestMapping("/systeminfo/")
public class SystemInfoController extends BaseController {
	@Inject
	private SystemInfoMapper systemInfoMapper;
	private static DateFormat formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private static Session session = SecurityUtils.getSubject().getSession();
	
	@RequestMapping("list")
	public String listUI(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/systeminfo/list";
	}

	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage( String pageNow,
			String pageSize,String column,String sort) throws Exception {
		SystemInfoFormMap systemInfoFormMap = getFormMap(SystemInfoFormMap.class);
		
		if(systemInfoFormMap.containsKey("pushDate")){
			String dateRange = (String) systemInfoFormMap.get("pushDate");
			String dateFrom = dateRange.split(" - ")[0];
			String dateTo = dateRange.split(" - ")[1];
			systemInfoFormMap.put("dateFrom", dateFrom);
			systemInfoFormMap.put("dateTo", dateTo);
		}
		
		systemInfoFormMap=toFormMap(systemInfoFormMap, pageNow, pageSize,systemInfoFormMap.getStr("orderby"));
		systemInfoFormMap.put("column", column);
		systemInfoFormMap.put("sort", sort);
		
		List<SystemInfoFormMap> list = systemInfoMapper.findSystemInfoPage(systemInfoFormMap);
		for(int i=0;i<list.size();i++){
			SystemInfoFormMap systemInfo = list.get(i);
			int modelType = (Integer) systemInfo.get("modelType");
			systemInfo.put("modelType", ModelType.getName(modelType));
			
			int isOntop = Integer.valueOf((String) systemInfo.get("isOntop"));
			systemInfo.put("isOntop", IsOntopType.getName(isOntop));
		}
        pageView.setRecords(list);//不调用默认分页,调用自已的mapper中findUserPage
        return pageView;
	}
	
	@RequestMapping("addUI")
	public String addUI(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/system/systeminfo/add";
	}
	
	@ResponseBody
	@RequestMapping("addEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	public String addEntity( String pageNow,
			String pageSize,String column,String sort) throws Exception {
		SystemInfoFormMap systemInfoFormMap = getFormMap(SystemInfoFormMap.class);
		systemInfoFormMap.put("AddUserId", session.getAttribute("userSessionId"));
		systemInfoFormMap.put("AddDate", formater.format(new Date()));
		systemInfoFormMap.put("Id", UUID.randomUUID());
		systemInfoMapper.addEntity(systemInfoFormMap);
        return "success";
	}
	
	@RequestMapping("editUI")
	public String editUI(Model model) throws Exception {
		String id = getPara("id");
		if(Common.isNotEmpty(id)){
		SystemInfoFormMap systemInfoFormMap = systemInfoMapper.findbyFrist("id", id, SystemInfoFormMap.class);
		int modelType = (Integer) systemInfoFormMap.get("ModelType");
		systemInfoFormMap.put("ModelType", ModelType.getName(modelType));
		
		int isOntop = Integer.valueOf((String) systemInfoFormMap.get("IsOnTop"));
		systemInfoFormMap.put("IsOnTop", IsOntopType.getName(isOntop));
		model.addAttribute("systemInfo", systemInfoFormMap);
		}
		return Common.BACKGROUND_PATH + "/system/systeminfo/edit";
	}
	
	@ResponseBody
	@RequestMapping("editEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	public String editEntity(String pageNow,
			String pageSize,String column,String sort) throws Exception {
		SystemInfoFormMap systemInfoFormMap = getFormMap(SystemInfoFormMap.class);
		systemInfoMapper.editEntity(systemInfoFormMap);
        return "success";
	}
	
	@ResponseBody
	@RequestMapping("deleteEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	public String deleteEntity() throws Exception {
		String[] ids = getParaValues("ids");
		for (String id : ids) {
			if(Common.isNotEmpty(id)){
				SystemInfoFormMap systemInfoFormMap = systemInfoMapper.findbyFrist("id", id, SystemInfoFormMap.class);
				systemInfoFormMap.put("DeleteFlag", 1);
				systemInfoMapper.editEntity(systemInfoFormMap);
			}
		}
		return "success";
	}
}