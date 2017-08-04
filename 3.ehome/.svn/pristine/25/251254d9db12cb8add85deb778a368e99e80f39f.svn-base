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
 * 物业结算明细信息Entity
 * 
 * @author LiuQi
 * @version 2017-07-17
 */
public class PropertyBalanceDetail extends DataEntity<PropertyBalanceDetail> {

	private static final long serialVersionUID = 1L;
	private String propertyBalanceId; // 物业结算信息表ID
	private String propertyCompanyId; // 物业公司ID
	private String villageInfoId; // 楼盘ID
	private String moduleId; // 模块ID
	private String orderId; // 订单号
	private String orderNo; // 订单号
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
	private String companyName; // 物业名称
	private String villageName; // 楼盘名城
	private String moduleName; // 模块名称

	public PropertyBalanceDetail() {
		super();
	}

	public PropertyBalanceDetail(String id) {
		super(id);
	}

	@Length(min = 0, max = 64, message = "物业结算信息表ID长度必须介于 0 和 64 之间")
	public String getPropertyBalanceId() {
		return propertyBalanceId;
	}

	public void setPropertyBalanceId(String propertyBalanceId) {
		this.propertyBalanceId = propertyBalanceId;
	}

	@Length(min = 0, max = 64, message = "物业公司ID长度必须介于 0 和 64 之间")
	public String getPropertyCompanyId() {
		return propertyCompanyId;
	}

	public void setPropertyCompanyId(String propertyCompanyId) {
		this.propertyCompanyId = propertyCompanyId;
	}

	@Length(min = 0, max = 64, message = "楼盘ID长度必须介于 0 和 64 之间")
	public String getVillageInfoId() {
		return villageInfoId;
	}

	public void setVillageInfoId(String villageInfoId) {
		this.villageInfoId = villageInfoId;
	}

	@Length(min = 0, max = 64, message = "模块ID长度必须介于 0 和 64 之间")
	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Length(min = 0, max = 64, message = "订单号长度必须介于 0 和 64 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Length(min = 0, max = 4000, message = "订单号/团购券码长度必须介于 0 和 4000 之间")
	@ExcelField(title = "订单号/团购券码", type = 1, align = 2, sort = 5)
	public String getOrderGroupNo() {
		return orderGroupNo;
	}

	public void setOrderGroupNo(String orderGroupNo) {
		this.orderGroupNo = orderGroupNo;
	}

	@ExcelField(title = "订单金额", type = 1, align = 2, sort = 6)
	public Double getOrderMoney() {
		return orderMoney;
	}

	public void setOrderMoney(Double orderMoney) {
		this.orderMoney = orderMoney;
	}

	@ExcelField(title = "平台优惠", type = 1, align = 2, sort = 7)
	public Double getCouponMoney() {
		return couponMoney;
	}

	public void setCouponMoney(Double couponMoney) {
		this.couponMoney = couponMoney;
	}

	@ExcelField(title = "扣点金额", type = 1, align = 2, sort = 8)
	public Double getDeductionMoney() {
		return deductionMoney;
	}

	public void setDeductionMoney(Double deductionMoney) {
		this.deductionMoney = deductionMoney;
	}

	@ExcelField(title = "应付金额", type = 1, align = 2, sort = 9)
	public Double getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(Double payMoney) {
		this.payMoney = payMoney;
	}

	@Length(min = 0, max = 64, message = "付款方式长度必须介于 0 和 64 之间")
	@ExcelField(title = "付款方式", type = 1, align = 2, sort = 10, dictType = "pay_type")
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
	@ExcelField(title = "下单时间", type = 1, align = 2, sort = 11)
	public Date getTradeCompleteTime() {
		return tradeCompleteTime;
	}

	public void setTradeCompleteTime(Date tradeCompleteTime) {
		this.tradeCompleteTime = tradeCompleteTime;
	}

	@Length(min = 0, max = 1, message = "支付对账状态(修改状态时两边都要修改)长度必须介于 0 和 1 之间")
	@ExcelField(title = "支付对账状态", type = 1, align = 2, sort = 12, dictType = "pay_check_state")
	public String getPayCheckState() {
		return payCheckState;
	}

	public void setPayCheckState(String payCheckState) {
		this.payCheckState = payCheckState;
	}

	public Date getBalanceStartTime() {
		return balanceStartTime;
	}

	public void setBalanceStartTime(Date balanceStartTime) {
		this.balanceStartTime = balanceStartTime;
	}

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

	@ExcelField(title = "物业名称", type = 1, align = 2, sort = 2)
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@ExcelField(title = "楼盘名称", type = 1, align = 2, sort = 3)
	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	@ExcelField(title = "模块名称", type = 1, align = 2, sort = 4)
	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	@Override
	public String toString() {
		return "PropertyBalanceDetail [propertyBalanceId=" + propertyBalanceId + ", propertyCompanyId="
				+ propertyCompanyId + ", villageInfoId=" + villageInfoId + ", moduleId=" + moduleId + ", orderId="
				+ orderId + ", orderNo=" + orderNo + ", orderGroupNo=" + orderGroupNo + ", orderMoney=" + orderMoney
				+ ", couponMoney=" + couponMoney + ", deductionMoney=" + deductionMoney + ", payMoney=" + payMoney
				+ ", payType=" + payType + ", payOrg=" + payOrg + ", tradeCompleteTime=" + tradeCompleteTime
				+ ", payCheckState=" + payCheckState + ", balanceStartTime=" + balanceStartTime + ", balanceEndTime="
				+ balanceEndTime + ", serialNum=" + serialNum + ", companyName=" + companyName + ", villageName="
				+ villageName + ", moduleName=" + moduleName + "]";
	}

}