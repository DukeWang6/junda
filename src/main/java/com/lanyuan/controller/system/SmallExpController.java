package com.lanyuan.controller.system;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lanyuan.mapper.SmallExpMapper;
import com.lanyuan.controller.index.BaseController;
import com.lanyuan.entity.SmallExpFormMap;
import com.lanyuan.plugin.PageView;
import com.lanyuan.util.Common;

@Controller
@RequestMapping("/smallexp/")
public class SmallExpController extends BaseController {
	@Inject
	private SmallExpMapper smallExpMapper;
	 
	@RequestMapping("list")
	public String listUI(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/smallExp/list";
	}

	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage( String pageNow,
		String pageSize,String column,String sort) throws Exception {
		SmallExpFormMap smallExpFormMap = getFormMap(SmallExpFormMap.class);
		smallExpFormMap=toFormMap(smallExpFormMap, pageNow, pageSize,smallExpFormMap.getStr("orderby"));
		smallExpFormMap.put("column", column);
		smallExpFormMap.put("sort", sort);
        pageView.setRecords(smallExpMapper.findSmallExpInfoPage(smallExpFormMap));
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