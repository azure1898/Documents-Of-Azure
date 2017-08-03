/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.app.entity.OrderFieldList;
import com.its.modules.app.dao.OrderFieldListDao;

/**
 * 场地订单明细Service
 * @author like
 * @version 2017-07-12
 */
@Service
@Transactional(readOnly = true)
public class OrderFieldListService extends CrudService<OrderFieldListDao, OrderFieldList> {

	public OrderFieldList get(String id) {
		return super.get(id);
	}
	
	public List<OrderFieldList> findList(OrderFieldList orderFieldList) {
		return super.findList(orderFieldList);
	}
	
	public Page<OrderFieldList> findPage(Page<OrderFieldList> page, OrderFieldList orderFieldList) {
		return super.findPage(page, orderFieldList);
	}
	
	@Transactional(readOnly = false)
	public void save(OrderFieldList orderFieldList) {
		super.save(orderFieldList);
	}
	
	@Transactional(readOnly = false)
	public void delete(OrderFieldList orderFieldList) {
		super.delete(orderFieldList);
	}
	/*******************************************************/
	@Transactional(readOnly = false)
	public void insert(OrderFieldList orderFieldList){
		dao.insert(orderFieldList);
	}
}