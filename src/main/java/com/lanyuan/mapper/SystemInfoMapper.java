package com.lanyuan.mapper;

import java.util.List;

import com.lanyuan.entity.SystemInfoFormMap;
import com.lanyuan.mapper.base.BaseMapper;


public interface SystemInfoMapper extends BaseMapper{

	public List<SystemInfoFormMap> findSystemInfoPage(SystemInfoFormMap systemInfoFormMap);
	
}
