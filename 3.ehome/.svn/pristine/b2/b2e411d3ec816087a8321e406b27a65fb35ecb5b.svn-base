/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.app.entity.OrderTrack;
import com.its.modules.app.dao.OrderTrackDao;

/**
 * 订单跟踪Service
 * @author sushipeng
 * @version 2017-07-19
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
	
	@Transactional(readOnly = false)
	public void save(OrderTrack orderTrack) {
		super.save(orderTrack);
	}
	
	@Transactional(readOnly = false)
	public void delete(OrderTrack orderTrack) {
		super.delete(orderTrack);
	}
	/**
	 * 提交订单时插入订单跟踪状态
	 * @param orderType	订单类型：0-商品；1-服务；2-课程；3场地预约；4-团购
	 * @param orderId
	 * @param orderNo
	 */
	public void createTrackSubmit(String orderType,String orderId,String orderNo){
		OrderTrack track = new OrderTrack();
		track.setOrderNo(orderNo);
		track.setStateMsg("待支付");
		track.setHandleMsg("订单提交成功，等待支付");
		this.save(track);
	}
}