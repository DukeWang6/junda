package com.lanyuan.controller.system;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.lanyuan.controller.index.BaseController;
import com.lanyuan.entity.AboutUsFormMap;
import com.lanyuan.entity.InviteCodeFormMap;
import com.lanyuan.entity.VersionInfoFormMap;
import com.lanyuan.plugin.PageView;
import com.lanyuan.util.Common;

/**
 * 
 * @author lanyuan 2014-11-19
 * @Email: mmm333zzz520@163.com
 * @version 3.0v
 */
@Controller
@RequestMapping("/aboutus/")
public class AboutUsController extends BaseController {
	@Inject
	private AboutUsMapper aboutUsMapper;
	private static Session session = SecurityUtils.getSubject().getSession();
	private static DateFormat formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	@RequestMapping("list")
	public String listUI(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/aboutus/list";
	}

	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage( String pageNow,
			String pageSize,String column,String sort) throws Exception {
		AboutUsFormMap aboutUsFormMap = getFormMap(AboutUsFormMap.class);
		
		if(aboutUsFormMap.containsKey("updateDate")){
			String dateRange = (String) aboutUsFormMap.get("updateDate");
			String dateFrom = dateRange.split(" - ")[0];
			String dateTo = dateRange.split(" - ")[1];
			aboutUsFormMap.put("dateFrom", dateFrom);
			aboutUsFormMap.put("dateTo", dateTo);
		}
		
		aboutUsFormMap=toFormMap(aboutUsFormMap, pageNow, pageSize,aboutUsFormMap.getStr("orderby"));
		aboutUsFormMap.put("column", column);
		aboutUsFormMap.put("sort", sort);
        pageView.setRecords(aboutUsMapper.findAboutUsPage(aboutUsFormMap));//不调用默认分页,调用自已的mapper中findUserPage
        return pageView;
	}
	
	@RequestMapping("addUI")
	public String addUI(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/system/aboutus/add";
	}
	
	@ResponseBody
	@RequestMapping("addEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	public String addEntity( String pageNow,
			String pageSize,String column,String sort) throws Exception {
		AboutUsFormMap aboutUsFormMap = getFormMap(AboutUsFormMap.class);
		aboutUsFormMap.put("AddUserId", session.getAttribute("userSessionId"));
		aboutUsFormMap.put("AddDate", formater.format(new Date()));
		aboutUsFormMap.put("Id", UUID.randomUUID());
		aboutUsMapper.addEntity(aboutUsFormMap);
        return "success";
	}
	
	@RequestMapping("editUI")
	public String editUI(Model model) throws Exception {
		String id = getPara("id");
		if(Common.isNotEmpty(id)){
			model.addAttribute("aboutus", aboutUsMapper.findbyFrist("id", id, AboutUsFormMap.class));
		}
		return Common.BACKGROUND_PATH + "/system/aboutus/edit";
	}
	
	@ResponseBody
	@RequestMapping("editEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	public String editEntity(String pageNow,
			String pageSize,String column,String sort) throws Exception {
		AboutUsFormMap aboutUsFormMap = getFormMap(AboutUsFormMap.class);
		aboutUsFormMap.put("UpdateUserID", session.getAttribute("userSessionId"));
		aboutUsFormMap.put("UpdateDate", formater.format(new Date()));
		aboutUsMapper.editEntity(aboutUsFormMap);
        return "success";
	}
	
	@ResponseBody
	@RequestMapping("deleteEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	public String deleteEntity() throws Exception {
		String[] ids = getParaValues("ids");
		for (String id : ids) {
			AboutUsFormMap aboutUsFormMap = aboutUsMapper.findbyFrist("id", id, AboutUsFormMap.class);
			aboutUsFormMap.put("DeleteFlag", 1);
			aboutUsMapper.editEntity(aboutUsFormMap);
		}
		return "success";
	}
	
	@RequestMapping("isExist")
	@ResponseBody
	public boolean isExist(String title) {
		AboutUsFormMap aboutUsFormMap = aboutUsMapper.findbyFrist("title", title, AboutUsFormMap.class);
		if (aboutUsFormMap == null) {
			return true;
		} else {
			return false;
		}
	}
}