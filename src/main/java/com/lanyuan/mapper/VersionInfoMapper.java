package com.lanyuan.mapper;

import java.util.List;

import com.lanyuan.entity.VersionInfoFormMap;
import com.lanyuan.mapper.base.BaseMapper;


public interface VersionInfoMapper extends BaseMapper{

	public List<VersionInfoFormMap> findVersionInfoPage(VersionInfoFormMap versionInfoFormMap);
	
}
