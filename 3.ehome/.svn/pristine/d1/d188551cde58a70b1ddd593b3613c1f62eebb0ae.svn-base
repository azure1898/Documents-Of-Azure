/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.operation.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.its.common.persistence.DataEntity;
import com.its.common.utils.excel.annotation.ExcelField;

/**
 * 会员的优惠券Entity
 * 
 * @author liuqi
 * @version 2017-07-05
 */
public class MemberDiscount extends DataEntity<MemberDiscount> {

	/**
	 * 使用状态：0未使用；1已使用；2已过期；3已冻结
	 */
	public final static String USE_STATE_UNUSE = "0";
	/**
	 * 使用状态：0未使用；1已使用；2已过期；3已冻结
	 */
	public final static String USE_STATE_USED = "1";
	/**
	 * 使用状态：0未使用；1已使用；2已过期；3已冻结
	 */
	public final static String USE_STATE_EXPIRED = "2";
	/**
	 * 使用状态：0未使用；1已使用；2已过期；3已冻结
	 */
	public final static String USE_STATE_FROZEN = "3";

	private static final long serialVersionUID = 1L;
	private String discountNum; // 优惠券号
	private String discountId; // 优惠券ID
	private String villageInfoId; // 楼盘信息ID
	private String accountId; // 会员ID
	private Date obtainDate; // 获得时间
	private Date validStart; // 有效起始
	private Date validEnd; // 有效结束
	private String useState; // 使用状态
	private String receiveType; // 领取方式
	private String orderType; // 赠送的订单类型：0-商品；1服务；2课程；3场地
	private String orderId; // 哪个订单赠送的优惠券

	public MemberDiscount() {
		super();
	}

	public MemberDiscount(String id) {
		super(id);
	}

	@Length(min = 0, max = 32, message = "优惠券号长度必须介于 0 和 32 之间")
	@ExcelField(title = "优惠券号", type = 1, align = 2, sort = 1)
	public String getDiscountNum() {
		return discountNum;
	}

	public void setDiscountNum(String discountNum) {
		this.discountNum = discountNum;
	}

	@Length(min = 0, max = 64, message = "优惠券ID长度必须介于 0 和 64 之间")
	@ExcelField(title = "优惠券名称", type = 1, align = 2, sort = 2)
	public String getDiscountId() {
		return discountId;
	}

	public void setDiscountId(String discountId) {
		this.discountId = discountId;
	}

	@Length(min = 0, max = 64, message = "楼盘信息ID长度必须介于 0 和 64 之间")
	@ExcelField(title = "优惠券内容", type = 1, align = 2, sort = 3)
	public String getVillageInfoId() {
		return villageInfoId;
	}

	public void setVillageInfoId(String villageInfoId) {
		this.villageInfoId = villageInfoId;
	}

	@Length(min = 0, max = 64, message = "会员ID长度必须介于 0 和 64 之间")
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getObtainDate() {
		return obtainDate;
	}

	public void setObtainDate(Date obtainDate) {
		this.obtainDate = obtainDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getValidStart() {
		return validStart;
	}

	public void setValidStart(Date validStart) {
		this.validStart = validStart;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getValidEnd() {
		return validEnd;
	}

	public void setValidEnd(Date validEnd) {
		this.validEnd = validEnd;
	}

	@Length(min = 0, max = 1, message = "使用状态长度必须介于 0 和 1 之间")
	@ExcelField(title = "使用状态", type = 1, align = 2, sort = 4, dictType = "use_state_memberdiscount")
	public String getUseState() {
		return useState;
	}

	public void setUseState(String useState) {
		this.useState = useState;
	}

	@Length(min = 0, max = 1, message = "领取方式长度必须介于 0 和 1 之间")
	@ExcelField(title = "领取方式", type = 1, align = 2, sort = 5, dictType = "receive_type")
	public String getReceiveType() {
		return receiveType;
	}

	public void setReceiveType(String receiveType) {
		this.receiveType = receiveType;
	}

	@Length(min = 0, max = 1, message = "赠送的订单类型：0-商品；1服务；2课程；3场地长度必须介于 0 和 1 之间")
	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	@Length(min = 0, max = 64, message = "哪个订单赠送的优惠券长度必须介于 0 和 64 之间")
	@ExcelField(title = "订单号", type = 1, align = 2, sort = 6)
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "MemberDiscount [discountNum=" + discountNum + ", discountId=" + discountId + ", villageInfoId="
				+ villageInfoId + ", accountId=" + accountId + ", obtainDate=" + obtainDate + ", validStart="
				+ validStart + ", validEnd=" + validEnd + ", useState=" + useState + ", receiveType=" + receiveType
				+ ", orderType=" + orderType + ", orderId=" + orderId + "]";
	}

}