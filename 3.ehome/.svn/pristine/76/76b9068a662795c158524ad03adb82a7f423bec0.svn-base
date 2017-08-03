/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.entity;

import org.hibernate.validator.constraints.Length;

import com.its.common.persistence.DataEntity;

/**
 * 订单跟踪Entity
 * @author sushipeng
 * @version 2017-07-19
 */
public class OrderTrack extends DataEntity<OrderTrack> {
	
	private static final long serialVersionUID = 1L;
	private String orderNo;		// 订单号
	private String stateMsg;		// 跟踪状态
	private String handleMsg;		// 处理信息
	
	public OrderTrack() {
		super();
	}

	public OrderTrack(String id){
		super(id);
	}

	@Length(min=0, max=64, message="订单号长度必须介于 0 和 64 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Length(min=0, max=64, message="跟踪状态长度必须介于 0 和 64 之间")
	public String getStateMsg() {
		return stateMsg;
	}

	public void setStateMsg(String stateMsg) {
		this.stateMsg = stateMsg;
	}
	
	@Length(min=0, max=255, message="处理信息长度必须介于 0 和 255 之间")
	public String getHandleMsg() {
		return handleMsg;
	}

	public void setHandleMsg(String handleMsg) {
		this.handleMsg = handleMsg;
	}
	
}