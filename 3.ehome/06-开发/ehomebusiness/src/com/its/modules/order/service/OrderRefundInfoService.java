/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.order.entity.OrderRefundInfo;
import com.its.modules.order.dao.OrderRefundInfoDao;

/**
 * 退款信息Service
 * @author liuhl
 * @version 2017-07-13
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
	
	/**
	 * 根据订单号查询退款信息
	 * 
	 * @param orderRefundInfo 退款信息查询条件
	 * 
	 * @return 退款信息
	 */
	public OrderRefundInfo findOrderRefundInfoByOrderNo(OrderRefundInfo orderRefundInfo) {
		return this.dao.findOrderRefundInfoByOrderNo(orderRefundInfo);
	}
	
}