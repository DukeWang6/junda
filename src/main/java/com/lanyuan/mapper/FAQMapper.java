package com.lanyuan.mapper;

import java.util.List;

import com.lanyuan.entity.FAQFormMap;
import com.lanyuan.mapper.base.BaseMapper;


public interface FAQMapper extends BaseMapper{

	public List<FAQFormMap> findFAQPage(FAQFormMap faqFormMap);
	
}
