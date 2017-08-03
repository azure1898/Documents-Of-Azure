/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.order.entity.OrderServiceList;
import com.its.modules.order.dao.OrderServiceListDao;

/**
 * 订单-服务类子表-服务清单Service
 * @author liuhl
 * @version 2017-07-17
 */
@Service
@Transactional(readOnly = true)
public class OrderServiceListService extends CrudService<OrderServiceListDao, OrderServiceList> {

	public OrderServiceList get(String id) {
		return super.get(id);
	}
	
	public List<OrderServiceList> findList(OrderServiceList orderServiceList) {
		return super.findList(orderServiceList);
	}
	
	public Page<OrderServiceList> findPage(Page<OrderServiceList> page, OrderServiceList orderServiceList) {
		return super.findPage(page, orderServiceList);
	}
	
	@Transactional(readOnly = false)
	public void save(OrderServiceList orderServiceList) {
		super.save(orderServiceList);
	}
	
	@Transactional(readOnly = false)
	public void delete(OrderServiceList orderServiceList) {
		super.delete(orderServiceList);
	}
	
}