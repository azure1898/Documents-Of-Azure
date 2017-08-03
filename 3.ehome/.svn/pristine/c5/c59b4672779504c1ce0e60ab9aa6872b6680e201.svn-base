/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.balance.entity;

import java.util.Date;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.its.common.persistence.DataEntity;
import com.its.common.utils.excel.annotation.ExcelField;

/**
 * 商家结算明细信息Entity
 * 
 * @author LiuQi
 * @version 2017-07-17
 */
public class BusinessBalanceDetail extends DataEntity<BusinessBalanceDetail> {

	private static final long serialVersionUID = 1L;
	private String businessBalanceId; // 商户结算信息表ID
	private String businessInfoId; // 商户ID
	private String moduleId; // 模块ID
	private String orderNo; // 订单号
	private String prodType; // 产品模式：0商品购买 1服务预约 2课程购买 3场地预约
	private String orderGroupNo; // 订单号/团购券码
	private Double orderMoney; // 订单金额
	private Double couponMoney; // 平台优惠金额
	private Double deductionMoney; // 扣点金额
	private Double payMoney; // 应付金额
	private String payType; // 付款方式
	private String payOrg; // 支付机构(0微信1支付宝2平台钱包)
	private Date tradeCompleteTime; // 交易完成时间
	private String payCheckState; // 支付对账状态(修改状态时两边都要修改)
	private Date balanceStartTime; // 结算周期开始时间
	private Date balanceEndTime; // 结算周期结束时间
	
	// 导出结算明细
	private String serialNum; // 序号
	private String businessName; // 商户名称
	private String moduleName; // 模块名称

	public BusinessBalanceDetail() {
		super();
	}

	public BusinessBalanceDetail(String id) {
		super(id);
	}

	@Length(min = 0, max = 64, message = "商户结算信息表ID长度必须介于 0 和 64 之间")
	public String getBusinessBalanceId() {
		return businessBalanceId;
	}

	public void setBusinessBalanceId(String businessBalanceId) {
		this.businessBalanceId = businessBalanceId;
	}

	@Length(min = 0, max = 64, message = "商户ID长度必须介于 0 和 64 之间")
	public String getBusinessInfoId() {
		return businessInfoId;
	}

	public void setBusinessInfoId(String businessInfoId) {
		this.businessInfoId = businessInfoId;
	}

	@Length(min = 0, max = 64, message = "模块ID长度必须介于 0 和 64 之间")
	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	@Length(min = 0, max = 64, message = "订单号长度必须介于 0 和 64 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Length(min = 0, max = 64, message = "产品模式：0商品购买  1服务预约  2课程购买  3场地预约长度必须介于 0 和 64 之间")
	public String getProdType() {
		return prodType;
	}

	public void setProdType(String prodType) {
		this.prodType = prodType;
	}

	@Length(min = 0, max = 4000, message = "订单号/团购券码长度必须介于 0 和 4000 之间")
	@ExcelField(title = "订单号/团购券码", type = 1, align = 2, sort = 4)
	public String getOrderGroupNo() {
		return orderGroupNo;
	}

	public void setOrderGroupNo(String orderGroupNo) {
		this.orderGroupNo = orderGroupNo;
	}

	@ExcelField(title = "订单金额", type = 1, align = 2, sort = 5)
	public Double getOrderMoney() {
		return orderMoney;
	}

	public void setOrderMoney(Double orderMoney) {
		this.orderMoney = orderMoney;
	}

	@ExcelField(title = "平台优惠", type = 1, align = 2, sort = 6)
	public Double getCouponMoney() {
		return couponMoney;
	}

	public void setCouponMoney(Double couponMoney) {
		this.couponMoney = couponMoney;
	}

	@ExcelField(title = "扣点金额", type = 1, align = 2, sort = 7)
	public Double getDeductionMoney() {
		return deductionMoney;
	}

	public void setDeductionMoney(Double deductionMoney) {
		this.deductionMoney = deductionMoney;
	}

	@ExcelField(title = "应付金额", type = 1, align = 2, sort = 8)
	public Double getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(Double payMoney) {
		this.payMoney = payMoney;
	}

	@Length(min = 0, max = 64, message = "付款方式长度必须介于 0 和 64 之间")
	@ExcelField(title = "付款方式", type = 1, align = 2, sort = 9,dictType="pay_type")
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	@Length(min = 0, max = 64, message = "支付机构(0微信1支付宝2平台钱包)长度必须介于 0 和 64 之间")
	public String getPayOrg() {
		return payOrg;
	}

	public void setPayOrg(String payOrg) {
		this.payOrg = payOrg;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title = "交易完成时间", type = 1, align = 2, sort = 10)
	public Date getTradeCompleteTime() {
		return tradeCompleteTime;
	}

	public void setTradeCompleteTime(Date tradeCompleteTime) {
		this.tradeCompleteTime = tradeCompleteTime;
	}

	@Length(min = 0, max = 1, message = "支付对账状态(修改状态时两边都要修改)长度必须介于 0 和 1 之间")
	@ExcelField(title = "支付对账状态", type = 1, align = 2, sort = 11, dictType="pay_check_state")
	public String getPayCheckState() {
		return payCheckState;
	}

	public void setPayCheckState(String payCheckState) {
		this.payCheckState = payCheckState;
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
	
	@ExcelField(title = "序号", type = 1, align = 2, sort = 1)
	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	@ExcelField(title = "商户名称", type = 1, align = 2, sort = 2)
	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	@ExcelField(title = "模块名称", type = 1, align = 2, sort = 3)
	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	@Override
	public String toString() {
		return "BusinessBalanceDetail [businessBalanceId=" + businessBalanceId + ", businessInfoId=" + businessInfoId
				+ ", moduleId=" + moduleId + ", orderNo=" + orderNo + ", prodType=" + prodType + ", orderGroupNo="
				+ orderGroupNo + ", orderMoney=" + orderMoney + ", couponMoney=" + couponMoney + ", deductionMoney="
				+ deductionMoney + ", payMoney=" + payMoney + ", payType=" + payType + ", payOrg=" + payOrg
				+ ", tradeCompleteTime=" + tradeCompleteTime + ", payCheckState=" + payCheckState
				+ ", balanceStartTime=" + balanceStartTime + ", balanceEndTime=" + balanceEndTime + "]";
	}

}