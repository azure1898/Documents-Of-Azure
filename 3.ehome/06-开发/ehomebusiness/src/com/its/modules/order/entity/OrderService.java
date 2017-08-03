/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.its.common.persistence.DataEntity;
import com.its.common.utils.excel.annotation.ExcelField;

/**
 * 订单-服务类Entity
 * 
 * @author liuhl
 * @version 2017-07-17
 */
public class OrderService extends DataEntity<OrderService> {

    private static final long serialVersionUID = 1L;
    private String orderNo; // 订单号
    private String businessInfoId; // 商家id
    private String moduleManageId; // 模块管理ID
    private String prodType; // 产品模式：0商品购买 1服务预约 2课程购买 3场地预约
    private String type; // 终端类型(0 Android 1 IOS 2 商家后台)
    private String villageInfoId; // 楼盘ID
    private String provinceId; // 省ID
    private String cityId; // 城市ID
    private Double sumMoney; // 总金额
    private Double benefitMoney; // 商家优惠金额
    private Double couponMoney; // 优惠券优惠金额
    private Double payMoney; // 实际支付金额
    private String orderState; // 订单状态:0待受理、1已受理、2已完成、3已取消
    private String orderStateLabel; // 订单状态Label
    private String checkOrderState; // 支付对账状态:0未对账1正常2异常
    private Date checkTime; // 结算时间
    private String checkState; // 结算状态:0未结算1已结算
    private String accountId; // 买家ID
    private String accountName; // 买家名称
    private String accountPhoneNumber; // 买家联系方式
    private String accountMsg; // 买家留言
    private String serviceType; // 服务方式
    private String serviceTypeLabel; // 服务方式Label
    private String serviceAddress; // 服务地址
    private Double serviceMoney; // 服务费用
    private String isStart; // 是否立即配送
    private Date startTime; // 预计开始时间
    private Date endTime; // 预计结束时间
    private Date overTime; // 完成时间
    private String payType; // 支付方式(0在线支付)
    private String payOrg; // 支付机构(0微信1支付宝2平台钱包)
    private Date payTime; // 支付时间
    private String payUserName; // 支付账号
    private String payState; // 支付状态:0未支付1已支付2退款中3已退款
    private String payStateLabel; // 支付状态Label
    private List<OrderTrack> orderTrackList; // 订单跟踪明细
    private List<OrderServiceList> orderServiceList; // 订单服务明细
    private String cancelRemarks; // 取消原因
    private String updateDateString; // 字符串类型的日期
    private Date beginCreateDate; // 开始 创建时间
    private Date endCreateDate; // 结束 创建时间
    private String timeForExcel; // EXCEL显示用时间
    private String searchFlg; // 检索按钮按下标示

    public OrderService() {
        super();
    }

    public OrderService(String id) {
        super(id);
    }

    public String getBusinessInfoId() {
        return businessInfoId;
    }

    public void setBusinessInfoId(String businessInfoId) {
        this.businessInfoId = businessInfoId;
    }

    public List<OrderTrack> getOrderTrackList() {
        return orderTrackList;
    }

    public void setOrderTrackList(List<OrderTrack> orderTrackList) {
        this.orderTrackList = orderTrackList;
    }

    public String getCancelRemarks() {
        return cancelRemarks;
    }

    public void setCancelRemarks(String cancelRemarks) {
        this.cancelRemarks = cancelRemarks;
    }

    public String getUpdateDateString() {
        return updateDateString;
    }

    public void setUpdateDateString(String updateDateString) {
        this.updateDateString = updateDateString;
    }

    @ExcelField(title = "订单号", type = 1, align = 2, sort = 1)
    @Length(min = 0, max = 64, message = "订单号长度必须介于 0 和 64 之间")
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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

    @Length(min = 0, max = 1, message = "终端类型(0 Android 1 IOS 2 商家后台)长度必须介于 0 和 1 之间")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Length(min = 0, max = 64, message = "楼盘ID长度必须介于 0 和 64 之间")
    public String getVillageInfoId() {
        return villageInfoId;
    }

    public void setVillageInfoId(String villageInfoId) {
        this.villageInfoId = villageInfoId;
    }

    @Length(min = 0, max = 64, message = "省ID长度必须介于 0 和 64 之间")
    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    @Length(min = 0, max = 64, message = "城市ID长度必须介于 0 和 64 之间")
    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public Double getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(Double sumMoney) {
        this.sumMoney = sumMoney;
    }

    public Double getBenefitMoney() {
        return benefitMoney;
    }

    public void setBenefitMoney(Double benefitMoney) {
        this.benefitMoney = benefitMoney;
    }

    public Double getCouponMoney() {
        return couponMoney;
    }

    public void setCouponMoney(Double couponMoney) {
        this.couponMoney = couponMoney;
    }

    @ExcelField(title = "金额", type = 1, align = 2, sort = 4)
    public Double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    @Length(min = 0, max = 1, message = "订单状态:0待受理、1已受理、2已完成、3已取消长度必须介于 0 和 1 之间")
    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    @Length(min = 0, max = 1, message = "支付对账状态:0未对账1正常2异常长度必须介于 0 和 1 之间")
    public String getCheckOrderState() {
        return checkOrderState;
    }

    public void setCheckOrderState(String checkOrderState) {
        this.checkOrderState = checkOrderState;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    @Length(min = 0, max = 1, message = "结算状态:0未结算1已结算长度必须介于 0 和 1 之间")
    public String getCheckState() {
        return checkState;
    }

    public void setCheckState(String checkState) {
        this.checkState = checkState;
    }

    @Length(min = 1, max = 64, message = "买家ID长度必须介于 1 和 64 之间")
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @ExcelField(title = "姓名", type = 1, align = 2, sort = 2)
    @Length(min = 0, max = 64, message = "买家名称长度必须介于 0 和 64 之间")
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @Length(min = 0, max = 64, message = "买家联系方式长度必须介于 0 和 64 之间")
    public String getAccountPhoneNumber() {
        return accountPhoneNumber;
    }

    public void setAccountPhoneNumber(String accountPhoneNumber) {
        this.accountPhoneNumber = accountPhoneNumber;
    }

    @Length(min = 0, max = 255, message = "买家留言长度必须介于 0 和 255 之间")
    public String getAccountMsg() {
        return accountMsg;
    }

    public void setAccountMsg(String accountMsg) {
        this.accountMsg = accountMsg;
    }

    @Length(min = 0, max = 1, message = "服务方式长度必须介于 0 和 1 之间")
    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    @ExcelField(title = "地址", type = 1, align = 2, sort = 3, width = 6500)
    @Length(min = 0, max = 255, message = "服务地址长度必须介于 0 和 255 之间")
    public String getServiceAddress() {
        return serviceAddress;
    }

    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public Double getServiceMoney() {
        return serviceMoney;
    }

    public void setServiceMoney(Double serviceMoney) {
        this.serviceMoney = serviceMoney;
    }

    @Length(min = 0, max = 1, message = "是否立即配送长度必须介于 0 和 1 之间")
    public String getIsStart() {
        return isStart;
    }

    public void setIsStart(String isStart) {
        this.isStart = isStart;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getOverTime() {
        return overTime;
    }

    public void setOverTime(Date overTime) {
        this.overTime = overTime;
    }

    @Length(min = 0, max = 64, message = "支付方式(0在线支付)长度必须介于 0 和 64 之间")
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
    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    @Length(min = 0, max = 255, message = "支付账号长度必须介于 0 和 255 之间")
    public String getPayUserName() {
        return payUserName;
    }

    public void setPayUserName(String payUserName) {
        this.payUserName = payUserName;
    }

    @Length(min = 0, max = 1, message = "支付状态:0未支付1已支付2退款中3已退款长度必须介于 0 和 1 之间")
    public String getPayState() {
        return payState;
    }

    public void setPayState(String payState) {
        this.payState = payState;
    }

    public Date getBeginCreateDate() {
        return beginCreateDate;
    }

    public void setBeginCreateDate(Date beginCreateDate) {
        this.beginCreateDate = beginCreateDate;
    }

    public Date getEndCreateDate() {
        return endCreateDate;
    }

    public void setEndCreateDate(Date endCreateDate) {
        this.endCreateDate = endCreateDate;
    }

    public List<OrderServiceList> getOrderServiceList() {
        return orderServiceList;
    }

    public void setOrderServiceList(List<OrderServiceList> orderServiceList) {
        this.orderServiceList = orderServiceList;
    }

    @ExcelField(title = "服务方式", type = 1, align = 2, sort = 5)
    public String getServiceTypeLabel() {
        return serviceTypeLabel;
    }

    public void setServiceTypeLabel(String serviceTypeLabel) {
        this.serviceTypeLabel = serviceTypeLabel;
    }

    @ExcelField(title = "订单状态", type = 1, align = 2, sort = 7)
    public String getOrderStateLabel() {
        return orderStateLabel;
    }

    public void setOrderStateLabel(String orderStateLabel) {
        this.orderStateLabel = orderStateLabel;
    }

    @ExcelField(title = "支付状态", type = 1, align = 2, sort = 6)
    public String getPayStateLabel() {
        return payStateLabel;
    }

    public void setPayStateLabel(String payStateLabel) {
        this.payStateLabel = payStateLabel;
    }

    @ExcelField(title = "时间", type = 1, align = 2, sort = 8, width = 5500)
    public String getTimeForExcel() {
        return timeForExcel;
    }

    public void setTimeForExcel(String timeForExcel) {
        this.timeForExcel = timeForExcel;
    }

    public String getSearchFlg() {
        return searchFlg;
    }

    public void setSearchFlg(String searchFlg) {
        this.searchFlg = searchFlg;
    }

}