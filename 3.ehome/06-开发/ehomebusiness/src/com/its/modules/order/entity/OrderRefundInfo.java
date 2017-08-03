/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.its.common.persistence.DataEntity;

/**
 * 退款信息Entity
 * 
 * @author liuhl
 * @version 2017-07-13
 */
public class OrderRefundInfo extends DataEntity<OrderRefundInfo> {

    private static final long serialVersionUID = 1L;
    private String orderId; // 订单id
    private String businessInfoId; // 商家id
    private String orderNo; // 订单号
    private String orderType; // 订单类型:0商品类 1服务类 2 课程培训类 3场地预约类 4团购类
    private String payType; // 订单支付方式
    private Double orderMoney; // 订单金额
    private String type; // 终端类型(0 Android 1 IOS 2 商家后台)
    private String moduleManageId; // 模块管理ID
    private String prodType; // 产品模式：0商品购买 1服务预约 2课程购买 3场地预约
    private Double refundMoney; // 退款金额
    private String refundNo; // 退款单号
    private String refundType; // 退款类型(0微信1支付宝2平台钱包)
    private String refundState; // 是否退款:0否1是
    private Date refundOverTime; // 退款完成时间

    public OrderRefundInfo() {
        super();
    }

    public OrderRefundInfo(String id) {
        super(id);
    }

    @Length(min = 1, max = 64, message = "商家id长度必须介于 1 和 64 之间")
    public String getBusinessInfoId() {
        return businessInfoId;
    }

    public void setBusinessInfoId(String businessInfoId) {
        this.businessInfoId = businessInfoId;
    }

    @Length(min = 0, max = 64, message = "订单号长度必须介于 0 和 64 之间")
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Length(min = 0, max = 1, message = "订单类型:0商品类 1服务类 2 课程培训类 3场地预约类 4团购类长度必须介于 0 和 1 之间")
    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    @Length(min = 0, max = 1, message = "订单支付方式长度必须介于 0 和 1 之间")
    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Double getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Double orderMoney) {
        this.orderMoney = orderMoney;
    }

    @Length(min = 0, max = 1, message = "终端类型(0 Android 1 IOS 2 商家后台)长度必须介于 0 和 1 之间")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Length(min = 0, max = 64, message = "模块管理ID长度必须介于 0 和 64 之间")
    public String getModuleManageId() {
        return moduleManageId;
    }

    public void setModuleManageId(String moduleManageId) {
        this.moduleManageId = moduleManageId;
    }

    @Length(min = 0, max = 64, message = "产品模式：0商品购买  1服务预约  2课程购买  3场地预约长度必须介于 0 和 64 之间")
    public String getProdType() {
        return prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    public Double getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(Double refundMoney) {
        this.refundMoney = refundMoney;
    }

    @Length(min = 0, max = 64, message = "退款单号长度必须介于 0 和 64 之间")
    public String getRefundNo() {
        return refundNo;
    }

    public void setRefundNo(String refundNo) {
        this.refundNo = refundNo;
    }

    @Length(min = 0, max = 1, message = "退款类型(0微信1支付宝2平台钱包)长度必须介于 0 和 1 之间")
    public String getRefundType() {
        return refundType;
    }

    public void setRefundType(String refundType) {
        this.refundType = refundType;
    }

    @Length(min = 0, max = 1, message = "是否退款:0否1是长度必须介于 0 和 1 之间")
    public String getRefundState() {
        return refundState;
    }

    public void setRefundState(String refundState) {
        this.refundState = refundState;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getRefundOverTime() {
        return refundOverTime;
    }

    public void setRefundOverTime(Date refundOverTime) {
        this.refundOverTime = refundOverTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

}