/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.app.entity.OrderRefundInfo;
import com.its.modules.app.dao.OrderRefundInfoDao;

/**
 * 订单-退款信息Service
 * @author sushipeng
 * @version 2017-08-04
 */
@Service
@Transactional(readOnly = true)
public class OrderRefundInfoService extends CrudService<OrderRefundInfoDao, OrderRefundInfo> {

	public OrderRefundInfo get(String id) {
		return super.get(id);
	}
	
	public List<OrderRefundInfo> findList(OrderRefundInfo orderRefundInfo) {
		return super.findList(orderRefundInfo);
	}
	
	public Page<OrderRefundInfo> findPage(Page<OrderRefundInfo> page, OrderRefundInfo orderRefundInfo) {
		return super.findPage(page, orderRefundInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(OrderRefundInfo orderRefundInfo) {
		super.save(orderRefundInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(OrderRefundInfo orderRefundInfo) {
		super.delete(orderRefundInfo);
	}
	
}