package com.its.modules.app.entity;

import org.hibernate.validator.constraints.Length;

import com.its.common.persistence.DataEntity;

/**
 * 订单-跟踪Entity
 * 
 * @author sushipeng
 * 
 * @version 2017-08-02
 */
public class OrderTrack extends DataEntity<OrderTrack> {
	
	private static final long serialVersionUID = 1L;
	private String orderId;		// 订单ID
	private String orderNo;		// 订单号
	private String orderType;		// 订单类型:0商品类 1服务类 2 课程培训类 3场地预约类 4团购类
	private String stateMsg;		// 跟踪状态PC
	private String handleMsg;		// 处理信息PC
	private String stateMsgPhone;		// 跟踪状态Phone
	private String handleMsgPhone;		// 处理信息Phone
	private String createName;		// 操作账号
	
	public OrderTrack() {
		super();
	}

	public OrderTrack(String id){
		super(id);
	}

	@Length(min=1, max=64, message="订单ID长度必须介于 1 和 64 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@Length(min=1, max=64, message="订单号长度必须介于 1 和 64 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Length(min=1, max=1, message="订单类型:0商品类 1服务类 2 课程培训类 3场地预约类 4团购类长度必须介于 1 和 1 之间")
	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	@Length(min=0, max=64, message="跟踪状态PC长度必须介于 0 和 64 之间")
	public String getStateMsg() {
		return stateMsg;
	}

	public void setStateMsg(String stateMsg) {
		this.stateMsg = stateMsg;
	}
	
	@Length(min=0, max=255, message="处理信息PC长度必须介于 0 和 255 之间")
	public String getHandleMsg() {
		return handleMsg;
	}

	public void setHandleMsg(String handleMsg) {
		this.handleMsg = handleMsg;
	}
	
	@Length(min=0, max=64, message="跟踪状态Phone长度必须介于 0 和 64 之间")
	public String getStateMsgPhone() {
		return stateMsgPhone;
	}

	public void setStateMsgPhone(String stateMsgPhone) {
		this.stateMsgPhone = stateMsgPhone;
	}
	
	@Length(min=0, max=255, message="处理信息Phone长度必须介于 0 和 255 之间")
	public String getHandleMsgPhone() {
		return handleMsgPhone;
	}

	public void setHandleMsgPhone(String handleMsgPhone) {
		this.handleMsgPhone = handleMsgPhone;
	}
	
	@Length(min=0, max=64, message="操作账号长度必须介于 0 和 64 之间")
	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}
}