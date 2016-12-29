package com.lanyuan.mapper;

import java.util.List;

import com.lanyuan.entity.AboutUsFormMap;
import com.lanyuan.mapper.base.BaseMapper;


public interface AboutUsMapper extends BaseMapper{

	public List<AboutUsFormMap> findAboutUsPage(AboutUsFormMap aboutUsFormMap);
	
}
