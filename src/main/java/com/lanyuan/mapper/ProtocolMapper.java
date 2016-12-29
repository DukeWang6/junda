package com.lanyuan.mapper;

import java.util.List;

import com.lanyuan.entity.ProtocolFormMap;
import com.lanyuan.mapper.base.BaseMapper;


public interface ProtocolMapper extends BaseMapper{

	public List<ProtocolFormMap> findProtocolPage(ProtocolFormMap protocolFormMap);
	
}
