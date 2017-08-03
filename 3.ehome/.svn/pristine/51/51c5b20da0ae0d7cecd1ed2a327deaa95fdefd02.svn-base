/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.balance.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.its.common.persistence.DataEntity;

/**
 * 物业结算操作日志Entity
 * @author Liuqi
 * @version 2017-08-01
 */
public class LogPropertyBalance extends DataEntity<LogPropertyBalance> {
	
	private static final long serialVersionUID = 1L;
	private String operationType;		// 操作类型
	private String batchNo;		// 批次号
	private String propertyBalanceId;		// 物业结算信息表ID
	private String propertyCompanyId;		// 物业公司ID
	private String balanceCycle;		// 结算周期
	private Date balanceStartTime;		// 结算周期开始时间
	private Date balanceEndTime;		// 结算周期结束时间
	private String balanceModel;		// 结算模式
	private String balanceState;		// 结算状态
	private String orderMoney;		// 订单金额
	private String couponMoney;		// 平台优惠金额
	private String deductionMoney;		// 扣点金额
	private String payMoney;		// 应付金额
	private String checkState;		// 核对状态
	
	public LogPropertyBalance() {
		super();
	}

	public LogPropertyBalance(String id){
		super(id);
	}

	@Length(min=0, max=1, message="操作类型长度必须介于 0 和 1 之间")
	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	
	@Length(min=0, max=64, message="批次号长度必须介于 0 和 64 之间")
	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	
	@Length(min=0, max=64, message="物业结算信息表ID长度必须介于 0 和 64 之间")
	public String getPropertyBalanceId() {
		return propertyBalanceId;
	}

	public void setPropertyBalanceId(String propertyBalanceId) {
		this.propertyBalanceId = propertyBalanceId;
	}
	
	@Length(min=0, max=64, message="物业公司ID长度必须介于 0 和 64 之间")
	public String getPropertyCompanyId() {
		return propertyCompanyId;
	}

	public void setPropertyCompanyId(String propertyCompanyId) {
		this.propertyCompanyId = propertyCompanyId;
	}
	
	@Length(min=0, max=1, message="结算周期长度必须介于 0 和 1 之间")
	public String getBalanceCycle() {
		return balanceCycle;
	}

	public void setBalanceCycle(String balanceCycle) {
		this.balanceCycle = balanceCycle;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBalanceStartTime() {
		return balanceStartTime;
	}

	public void setBalanceStartTime(Date balanceStartTime) {
		this.balanceStartTime = balanceStartTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBalanceEndTime() {
		return balanceEndTime;
	}

	public void setBalanceEndTime(Date balanceEndTime) {
		this.balanceEndTime = balanceEndTime;
	}
	
	@Length(min=0, max=1, message="结算模式长度必须介于 0 和 1 之间")
	public String getBalanceModel() {
		return balanceModel;
	}

	public void setBalanceModel(String balanceModel) {
		this.balanceModel = balanceModel;
	}
	
	@Length(min=0, max=1, message="结算状态长度必须介于 0 和 1 之间")
	public String getBalanceState() {
		return balanceState;
	}

	public void setBalanceState(String balanceState) {
		this.balanceState = balanceState;
	}
	
	public String getOrderMoney() {
		return orderMoney;
	}

	public void setOrderMoney(String orderMoney) {
		this.orderMoney = orderMoney;
	}
	
	public String getCouponMoney() {
		return couponMoney;
	}

	public void setCouponMoney(String couponMoney) {
		this.couponMoney = couponMoney;
	}
	
	public String getDeductionMoney() {
		return deductionMoney;
	}

	public void setDeductionMoney(String deductionMoney) {
		this.deductionMoney = deductionMoney;
	}
	
	public String getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(String payMoney) {
		this.payMoney = payMoney;
	}
	
	@Length(min=0, max=1, message="核对状态长度必须介于 0 和 1 之间")
	public String getCheckState() {
		return checkState;
	}

	public void setCheckState(String checkState) {
		this.checkState = checkState;
	}
	
}