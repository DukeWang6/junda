package com.lanyuan.mapper;

import java.util.List;

import com.lanyuan.entity.OrdersFormMap;
import com.lanyuan.mapper.base.BaseMapper;


public interface OrdersMapper extends BaseMapper{
	

	public List<OrdersFormMap> findOrderInfoPage(OrdersFormMap ordersFormMap);
	
}
