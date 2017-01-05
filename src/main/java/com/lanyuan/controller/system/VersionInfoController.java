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
import com.lanyuan.mapper.VersionInfoMapper;
import com.lanyuan.controller.index.BaseController;
import com.lanyuan.entity.AboutUsFormMap;
import com.lanyuan.entity.ProtocolFormMap;
import com.lanyuan.entity.SystemInfoFormMap;
import com.lanyuan.entity.VersionInfoFormMap;
import com.lanyuan.plugin.PageView;
import com.lanyuan.util.Common;
import com.lanyuan.util.constant.ModelType;
import com.lanyuan.util.constant.IsOntopType;
import com.lanyuan.util.constant.PlatformType;

/**
 * 
 * @author lanyuan 2014-11-19
 * @Email: mmm333zzz520@163.com
 * @version 3.0v
 */
@Controller
@RequestMapping("/versioninfo/")
public class VersionInfoController extends BaseController {
	@Inject
	private VersionInfoMapper versionInfoMapper;
	
	@RequestMapping("list")
	public String listUI(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/versioninfo/list";
	}

	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage( String pageNow,
			String pageSize,String column,String sort) throws Exception {
		VersionInfoFormMap versionInfoFormMap = getFormMap(VersionInfoFormMap.class);
		
		if(versionInfoFormMap.containsKey("updateDate")){
			String dateRange = (String) versionInfoFormMap.get("updateDate");
			String dateFrom = dateRange.split(" - ")[0];
			String dateTo = dateRange.split(" - ")[1];
			versionInfoFormMap.put("dateFrom", dateFrom);
			versionInfoFormMap.put("dateTo", dateTo);
		}
		
		versionInfoFormMap=toFormMap(versionInfoFormMap, pageNow, pageSize,versionInfoFormMap.getStr("orderby"));
		versionInfoFormMap.put("column", column);
		versionInfoFormMap.put("sort", sort);
		
		List<VersionInfoFormMap> list = versionInfoMapper.findVersionInfoPage(versionInfoFormMap);
		for(int i=0;i<list.size();i++){
			VersionInfoFormMap versionInfo = list.get(i);
			int modelType = (Integer) versionInfo.get("modelType");
			versionInfo.put("modelType", ModelType.getName(modelType));
			
			int platform = (Integer) versionInfo.get("platform");
			versionInfo.put("platform", PlatformType.getName(platform));
		}
        pageView.setRecords(list);//不调用默认分页,调用自已的mapper中findUserPage
        return pageView;
	}
	
	@RequestMapping("addUI")
	public String addUI(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/system/versioninfo/add";
	}
	
	@ResponseBody
	@RequestMapping("addEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	public String addEntity( String pageNow,
			String pageSize,String column,String sort) throws Exception {
		VersionInfoFormMap versionInfoFormMap = getFormMap(VersionInfoFormMap.class);
		Session session = SecurityUtils.getSubject().getSession();
		DateFormat formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		versionInfoFormMap.put("AddUserId", session.getAttribute("userSessionId"));
		versionInfoFormMap.put("AaddDate", formater.format(new Date()));
		versionInfoFormMap.put("Id", UUID.randomUUID());
		versionInfoMapper.addEntity(versionInfoFormMap);
        return "success";
	}
	
	@RequestMapping("editUI")
	public String editUI(Model model) throws Exception {
		String id = getPara("id");
		if(Common.isNotEmpty(id)){
		VersionInfoFormMap versionInfoFormMap = versionInfoMapper.findbyFrist("id", id, VersionInfoFormMap.class);
		int modelType = (Integer) versionInfoFormMap.get("modelType");
		versionInfoFormMap.put("modelType", ModelType.getName(modelType));
		
		int platform = (Integer) versionInfoFormMap.get("platform");
		versionInfoFormMap.put("platform", PlatformType.getName(platform));
		model.addAttribute("versioninfo", versionInfoFormMap);
		}
		return Common.BACKGROUND_PATH + "/system/versioninfo/edit";
	}
	
	@ResponseBody
	@RequestMapping("editEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	public String editEntity(String pageNow,
			String pageSize,String column,String sort) throws Exception {
		VersionInfoFormMap versionInfoFormMap = getFormMap(VersionInfoFormMap.class);
		Session session = SecurityUtils.getSubject().getSession();
		DateFormat formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		versionInfoFormMap.put("UpdateUserID", session.getAttribute("userSessionId"));
		versionInfoFormMap.put("UpdateDate", formater.format(new Date()));
		versionInfoMapper.editEntity(versionInfoFormMap);
        return "success";
	}
	
	@ResponseBody
	@RequestMapping("deleteEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	public String deleteEntity() throws Exception {
		String[] ids = getParaValues("ids");
		for (String id : ids) {
			if(Common.isNotEmpty(id)){
				VersionInfoFormMap versionInfoFormMap = versionInfoMapper.findbyFrist("id", id, VersionInfoFormMap.class);
				versionInfoFormMap.put("DeleteFlag", 1);
				versionInfoMapper.editEntity(versionInfoFormMap);
			}
		}
		return "success";
	}
}