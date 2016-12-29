package com.lanyuan.controller.system;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lanyuan.mapper.AboutUsMapper;
import com.lanyuan.mapper.InviteCodeMapper;
import com.lanyuan.mapper.UserMapper;
import com.lanyuan.annotation.SystemLog;
import com.lanyuan.controller.index.BaseController;
import com.lanyuan.entity.AboutUsFormMap;
import com.lanyuan.entity.InviteCodeFormMap;
import com.lanyuan.entity.ResUserFormMap;
import com.lanyuan.entity.RoleFormMap;
import com.lanyuan.entity.UserFormMap;
import com.lanyuan.entity.UserGroupsFormMap;
import com.lanyuan.exception.SystemException;
import com.lanyuan.plugin.PageView;
import com.lanyuan.util.Common;
import com.lanyuan.util.JsonUtils;
import com.lanyuan.util.POIUtils;
import com.lanyuan.util.PasswordHelper;

/**
 * 
 * @author lanyuan 2014-11-19
 * @Email: mmm333zzz520@163.com
 * @version 3.0v
 */
@Controller
@RequestMapping("/invitecode/")
public class InviteCodeController extends BaseController {
	@Inject
	private InviteCodeMapper inviteCodeMapper;
	
	@RequestMapping("list")
	public String listUI(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/invitecode/list";
	}

	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage( String pageNow,
			String pageSize,String column,String sort) throws Exception {
		InviteCodeFormMap inviteCodeFormMap = getFormMap(InviteCodeFormMap.class);
		inviteCodeFormMap=toFormMap(inviteCodeFormMap, pageNow, pageSize,inviteCodeFormMap.getStr("orderby"));
		inviteCodeFormMap.put("column", column);
		inviteCodeFormMap.put("sort", sort);
        pageView.setRecords(inviteCodeMapper.findInviteCodePage(inviteCodeFormMap));//不调用默认分页,调用自已的mapper中findUserPage
        return pageView;
	}
	
	@RequestMapping("addUI")
	public String addUI(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/system/invitecode/add";
	}
	
	@ResponseBody
	@RequestMapping("addEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	public String addEntity( String pageNow,
			String pageSize,String column,String sort) throws Exception {
		InviteCodeFormMap inviteCodeFormMap = getFormMap(InviteCodeFormMap.class);
		inviteCodeMapper.addEntity(inviteCodeFormMap);
        return "success";
	}
	
	@RequestMapping("editUI")
	public String editUI(Model model) throws Exception {
		String id = getPara("id");
		if(Common.isNotEmpty(id)){
			model.addAttribute("inviteCode", inviteCodeMapper.findbyFrist("id", id, InviteCodeFormMap.class));
		}
		return Common.BACKGROUND_PATH + "/system/invitecode/edit";
	}
	
	@ResponseBody
	@RequestMapping("editEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	public String editEntity(String pageNow,
			String pageSize,String column,String sort) throws Exception {
		InviteCodeFormMap inviteCodeFormMap = getFormMap(InviteCodeFormMap.class);
		inviteCodeMapper.editEntity(inviteCodeFormMap);
        return "success";
	}
	
	@ResponseBody
	@RequestMapping("deleteEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	public String deleteEntity() throws Exception {
		String[] ids = getParaValues("ids");
		for (String id : ids) {
			inviteCodeMapper.deleteByAttribute("id", id, InviteCodeFormMap.class);
		}
		return "success";
	}
}