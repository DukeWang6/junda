package com.lanyuan.controller.system;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lanyuan.mapper.ProtocolMapper;
import com.lanyuan.controller.index.BaseController;
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
@RequestMapping("/protocol/")
public class ProtocolController extends BaseController {
	@Inject
	private ProtocolMapper protocolMapper;
	private static DateFormat formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private static Session session = SecurityUtils.getSubject().getSession();
	
	@RequestMapping("list")
	public String listUI(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/protocol/list";
	}

	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage( String pageNow,
			String pageSize,String column,String sort) throws Exception {
		ProtocolFormMap protocolFormMap = getFormMap(ProtocolFormMap.class);
		protocolFormMap=toFormMap(protocolFormMap, pageNow, pageSize,protocolFormMap.getStr("orderby"));
		protocolFormMap.put("column", column);
		protocolFormMap.put("sort", sort);
		List<ProtocolFormMap> list = protocolMapper.findProtocolPage(protocolFormMap);
		for(int i=0;i<list.size();i++){
			ProtocolFormMap protocol = list.get(i);
			int modelType = (Integer) protocol.get("modelType");
			protocol.put("modelValue", ModelType.getName(modelType));
		}
        pageView.setRecords(list);//不调用默认分页,调用自已的mapper中findUserPage
        return pageView;
	}
	
	@RequestMapping("editUI")
	public String editUI(Model model) throws Exception {
		String id = getPara("id");
		if(Common.isNotEmpty(id)){
			ProtocolFormMap protocolFormMap = protocolMapper.findbyFrist("id", id, ProtocolFormMap.class);
			int modelType = (Integer) protocolFormMap.get("ModelType");
			protocolFormMap.put("ModelType", ModelType.getName(modelType));
			model.addAttribute("protocol", protocolFormMap);
		}
		return Common.BACKGROUND_PATH + "/system/protocol/edit";
	}
	
	@ResponseBody
	@RequestMapping("editEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	public String editEntity(String pageNow,
			String pageSize,String column,String sort) throws Exception {
		ProtocolFormMap protocolFormMap = getFormMap(ProtocolFormMap.class);
		protocolFormMap.put("UpdateUserId", session.getAttribute("userSessionId"));
		protocolFormMap.put("UpdateDate", formater.format(new Date()));
		protocolMapper.editEntity(protocolFormMap);
        return "success";
	}
	
}