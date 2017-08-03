/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.order.dao.OrderGroupPurcListDao;
import com.its.modules.order.entity.OrderGroupPurcList;

/**
 * 订单团购券清单Service
 * 
 * @author lq
 * @created on 2017年7月27日
 */
@Service
@Transactional(readOnly = true)
public class OrderGroupPurcListService extends CrudService<OrderGroupPurcListDao, OrderGroupPurcList> {

	public OrderGroupPurcList get(String id) {
		return super.get(id);
	}
	
	public List<OrderGroupPurcList> findList(OrderGroupPurcList OrderGroupPurcList) {
		return super.findList(OrderGroupPurcList);
	}
	
	public Page<OrderGroupPurcList> findPage(Page<OrderGroupPurcList> page, OrderGroupPurcList OrderGroupPurcList) {
		return super.findPage(page, OrderGroupPurcList);
	}
}