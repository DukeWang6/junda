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
import com.lanyuan.mapper.FAQMapper;
import com.lanyuan.controller.index.BaseController;
import com.lanyuan.entity.AboutUsFormMap;
import com.lanyuan.entity.FAQFormMap;
import com.lanyuan.entity.InviteCodeFormMap;
import com.lanyuan.entity.ProtocolFormMap;
import com.lanyuan.plugin.PageView;
import com.lanyuan.util.Common;
import com.lanyuan.util.constant.ModelType;

/**
 * 
 * @author lanyuan 2014-11-19
 * @Email: mmm333zzz520@163.com
 * @version 3.0v
 */
@Controller
@RequestMapping("/faq/")
public class FAQController extends BaseController {
	@Inject
	private FAQMapper faqMapper;
	private static DateFormat formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private static Session session = SecurityUtils.getSubject().getSession();
	
	@RequestMapping("list")
	public String listUI(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/faq/list";
	}

	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage( String pageNow,
			String pageSize,String column,String sort) throws Exception {
		FAQFormMap faqFormMap = getFormMap(FAQFormMap.class);
		
		if(faqFormMap.containsKey("updateDate")){
			String dateRange = (String) faqFormMap.get("updateDate");
			String dateFrom = dateRange.split(" - ")[0];
			String dateTo = dateRange.split(" - ")[1];
			faqFormMap.put("dateFrom", dateFrom);
			faqFormMap.put("dateTo", dateTo);
		}
		
		faqFormMap=toFormMap(faqFormMap, pageNow, pageSize,faqFormMap.getStr("orderby"));
		faqFormMap.put("column", column);
		faqFormMap.put("sort", sort);
		
		List<FAQFormMap> list = faqMapper.findFAQPage(faqFormMap);
		for(int i=0;i<list.size();i++){
			FAQFormMap faq = list.get(i);
			if(faq.get("modelType")!=null) {
				int modelType = (Integer) faq.get("modelType");
				faq.put("modelType", ModelType.getName(modelType));
			}
		}
        pageView.setRecords(list);//不调用默认分页,调用自已的mapper中findUserPage
        return pageView;
	}
	
	@RequestMapping("addUI")
	public String addUI(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/system/faq/add";
	}
	
	@ResponseBody
	@RequestMapping("addEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	public String addEntity( String pageNow,
			String pageSize,String column,String sort) throws Exception {
		FAQFormMap faqFormMap = getFormMap(FAQFormMap.class);
		faqFormMap.put("AddUserId", session.getAttribute("userSessionId"));
		faqFormMap.put("AddDate", formater.format(new Date()));
		faqFormMap.put("Id", UUID.randomUUID());
		faqMapper.addEntity(faqFormMap);
        return "success";
	}
	
	@RequestMapping("editUI")
	public String editUI(Model model) throws Exception {
		String id = getPara("id");
		if(Common.isNotEmpty(id)){
			FAQFormMap faqFormMap = faqMapper.findbyFrist("id", id, FAQFormMap.class);
			int modelType = (Integer) faqFormMap.get("ModelType");
			faqFormMap.put("ModelType", ModelType.getName(modelType));
			model.addAttribute("faq", faqFormMap);
		}
		return Common.BACKGROUND_PATH + "/system/faq/edit";
	}
	
	@ResponseBody
	@RequestMapping("editEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	public String editEntity(String pageNow,
			String pageSize,String column,String sort) throws Exception {
		FAQFormMap faqFormMap = getFormMap(FAQFormMap.class);
		faqFormMap.put("UpdateUserId", session.getAttribute("userSessionId"));
		faqFormMap.put("UpdateDate", formater.format(new Date()));
		faqMapper.editEntity(faqFormMap);
        return "success";
	}
	
	@ResponseBody
	@RequestMapping("deleteEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	public String deleteEntity() throws Exception {
		String[] ids = getParaValues("ids");
		for (String id : ids) {
			FAQFormMap faqFormMap = faqMapper.findbyFrist("id", id, FAQFormMap.class);
			faqFormMap.put("DeleteFlag", 1);
			faqMapper.editEntity(faqFormMap);
		}
		return "success";
	}
}