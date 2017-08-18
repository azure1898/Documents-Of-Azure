/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.balance.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.its.common.persistence.DataEntity;

/**
 * 结算对账单Entity
 * @author Liuqi
 * @version 2017-08-16
 */
public class DownloadBill extends DataEntity<DownloadBill> {
	
	private static final long serialVersionUID = 1L;
	private Date tradeTime;		// 交易时间
	private String deviceInfo;		// 设备号
	private String transactionId;		// 微信订单号
	private String outTradeNo;		// 商户订单号
	private String openid;		// 用户标识
	private String tradeType;		// 交易类型
	private String tradeState;		// 交易状态
	private String bankType;		// 付款银行
	private Double totalFee;		// 总金额
	private String productName;		// 商品名称
	private String productData;		// 商品数据包
	private Double costFee;		// 手续费
	private Double costRate;		// 费率
	private String billType;		// 对账单类型
	private String billDate;		// 对账单日期
	
	public DownloadBill() {
		super();
	}

	public DownloadBill(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}
	
	@Length(min=0, max=32, message="设备号长度必须介于 0 和 32 之间")
	public String getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
	
	@Length(min=0, max=32, message="微信订单号长度必须介于 0 和 32 之间")
	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	@Length(min=0, max=32, message="商户订单号长度必须介于 0 和 32 之间")
	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	
	@Length(min=0, max=128, message="用户标识长度必须介于 0 和 128 之间")
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	@Length(min=0, max=16, message="交易类型长度必须介于 0 和 16 之间")
	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	
	@Length(min=0, max=32, message="交易状态长度必须介于 0 和 32 之间")
	public String getTradeState() {
		return tradeState;
	}

	public void setTradeState(String tradeState) {
		this.tradeState = tradeState;
	}
	
	@Length(min=0, max=16, message="付款银行长度必须介于 0 和 16 之间")
	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	
	public Double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}
	
	@Length(min=0, max=128, message="商品名称长度必须介于 0 和 128 之间")
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	@Length(min=0, max=128, message="商品数据包长度必须介于 0 和 128 之间")
	public String getProductData() {
		return productData;
	}

	public void setProductData(String productData) {
		this.productData = productData;
	}
	
	public Double getCostFee() {
		return costFee;
	}

	public void setCostFee(Double costFee) {
		this.costFee = costFee;
	}
	
	public Double getCostRate() {
		return costRate;
	}

	public void setCostRate(Double costRate) {
		this.costRate = costRate;
	}
	
	@Length(min=0, max=1, message="对账单类型长度必须介于 0 和 1 之间")
	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	@Length(min=0, max=8, message="对账单日期长度必须介于 0 和 8 之间")
	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	@Override
	public String toString() {
		return "DownloadBill [tradeTime=" + tradeTime + ", deviceInfo=" + deviceInfo + ", transactionId="
				+ transactionId + ", outTradeNo=" + outTradeNo + ", openid=" + openid + ", tradeType=" + tradeType
				+ ", tradeState=" + tradeState + ", bankType=" + bankType + ", totalFee=" + totalFee + ", productName="
				+ productName + ", productData=" + productData + ", costFee=" + costFee + ", costRate=" + costRate
				+ ", billType=" + billType + ", billDate=" + billDate + "]";
	}
	
}