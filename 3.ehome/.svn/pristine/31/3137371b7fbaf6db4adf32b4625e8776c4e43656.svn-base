/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.order.entity.OrderTrack;
import com.its.modules.order.dao.OrderTrackDao;

/**
 * 订单跟踪表Service
 * @author xzc
 * @version 2017-07-11
 */
@Service
@Transactional(readOnly = true)
public class OrderTrackService extends CrudService<OrderTrackDao, OrderTrack> {

	public OrderTrack get(String id) {
		return super.get(id);
	}
	
	public List<OrderTrack> findList(OrderTrack orderTrack) {
		return super.findList(orderTrack);
	}
	
	public Page<OrderTrack> findPage(Page<OrderTrack> page, OrderTrack orderTrack) {
		return super.findPage(page, orderTrack);
	}

	/**
	 * 添加订单跟踪
	 * @param orderTrack
	 */
	@Transactional(readOnly = false)
	public void save(OrderTrack orderTrack) {
		super.save(orderTrack);
	}

	/**
	 * 添加订单跟踪
	 * @param orderTrack 订单跟踪信息
	 */
	@Transactional(readOnly = false)
	public void saveOrdMsg(OrderTrack orderTrack) {
		dao.insert(orderTrack);
	}
	
	/**
	 * 添加订单跟踪
	 * @param orderNo 订单号
	 * @param stateMsg  跟踪状态
	 * @param handleMsg 处理信息
	 */
	@Transactional(readOnly = false)
	public void saveOrdMsg(String orderNo,String stateMsg,String handleMsg) {
		OrderTrack orderTrack=new OrderTrack();
		orderTrack.preInsert();
		orderTrack.setOrderNo(orderNo);
		orderTrack.setStateMsg(stateMsg);
		orderTrack.setHandleMsg(handleMsg);
		dao.insert(orderTrack);
	}
	
	@Transactional(readOnly = false)
	public void delete(OrderTrack orderTrack) {
		super.delete(orderTrack);
	}
	
}