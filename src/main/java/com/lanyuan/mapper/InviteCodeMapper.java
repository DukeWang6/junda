package com.lanyuan.mapper;

import java.util.List;

import com.lanyuan.entity.InviteCodeFormMap;
import com.lanyuan.mapper.base.BaseMapper;


public interface InviteCodeMapper extends BaseMapper{

	public List<InviteCodeFormMap> findInviteCodePage(InviteCodeFormMap inviteCodeFormMap);
	
}
