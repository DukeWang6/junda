package com.lanyuan.mapper;

import java.util.List;

import com.lanyuan.entity.SmallExpFormMap;
import com.lanyuan.mapper.base.BaseMapper;


public interface SmallExpMapper extends BaseMapper{
	

	public List<SmallExpFormMap> findSmallExpInfoPage(SmallExpFormMap smallExpFormMap);
	
}
