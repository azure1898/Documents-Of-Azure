/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.order.dao.OrderGroupPurcDao;
import com.its.modules.order.entity.OrderGroupPurc;

/**
 * 订单-团购类Service
 * 
 * @author lq
 * @created on 2017年7月27日
 */
@Service
@Transactional(readOnly = true)
public class OrderGroupPurcService extends CrudService<OrderGroupPurcDao, OrderGroupPurc> {

	public OrderGroupPurc get(String id) {
		return super.get(id);
	}

	public List<OrderGroupPurc> findList(OrderGroupPurc orderGroupPurc) {
		return super.findList(orderGroupPurc);
	}

	public Page<OrderGroupPurc> findPage(Page<OrderGroupPurc> page, OrderGroupPurc OrderGroupPurc) {
		return super.findPage(page, OrderGroupPurc);
	}
}
