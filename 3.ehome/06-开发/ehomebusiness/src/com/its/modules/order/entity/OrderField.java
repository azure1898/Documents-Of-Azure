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
import com.its.modules.sys.utils.DictUtils;

/**
 * 场地预约订单表Entity
 * 
 * @author xzc
 * @version 2017-07-06
 */
public class OrderField extends DataEntity<OrderField> {

    private static final long serialVersionUID = 1L;
    private String businessInfoId; // 商家id
    private String orderNo; // 订单号
    private String moduleManageId; // 模块管理ID
    private String prodType; // 产品模式：0商品购买 1服务预约 2课程购买 3场地预约
    private String type; // 终端类型(0 Android 1 IOS 2 商家后台)
    private String villageInfoId; // 楼盘ID
    private String provinceId; // 省份ID
    private String cityId; // 城市ID
    private String name; // 预约场地名称
    private String fieldInfoId; // 场地预约ID
    private Double sumMoney; // 总金额
    private Double benefitMoney = 0.0; // 商家优惠金额
    private Double couponMoney = 0.0; // 平台优惠金额
    private Double payMoney; // 实际支付金额
    private String orderState; // 订单状态:0待预约、1预约成功、2已取消
    private String checkOrderState; // 支付对账状态:0未对账1正常2异常
    private Date checkTime; // 结算时间
    private String checkState; // 结算状态:0未结算1已结算
    private String accountId; // 买家ID
    private String accountName; // 买家名称
    private String accountPhoneNumber; // 买家联系方式
    private String accountMsg; // 买家留言
    private String payType; // 支付方式(0在线支付,1线下支付)
    private String payOrg; // 支付机构(0微信1支付宝2平台钱包3线下)
    private Date payTime; // 支付时间
    private String payUserName; // 支付账号
    private String payState; // 支付状态:0未支付1已支付2退款中3已退款
    private OrderFieldList orderFieldList = new OrderFieldList(); // 场地预约详情
    private String fieldPartitionPriceId;// 场地预约分区ID
    private Date beginCreateDate; // 开始 创建时间
    private Date endCreateDate; // 结束 创建时间
    private String searchFlg; // 检索按钮按下标示
    private String updateDateString; // 字符串类型的日期
    private boolean outTimeState;// 结束时段 是否超过当前日期
    private String timeForExcel; // 时间（EXCEL导出用）
    private String fieldNameForExcel; // 预约场地（EXCEL导出用）
    private String cancelRemarks; // 取消原因
    private List<OrderTrack> orderTrackList; // 订单跟踪明细

    public OrderField() {
        super();
    }

    public OrderField(String id) {
        super(id);
    }

    public String getBusinessInfoId() {
        return businessInfoId;
    }

    public void setBusinessInfoId(String businessInfoId) {
        this.businessInfoId = businessInfoId;
    }

    @ExcelField(title = "订单号", type = 1, align = 2, sort = 1, width = 6000)
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

    @Length(min = 0, max = 64, message = "预约场地名称长度必须介于 0 和 64 之间")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 1, max = 64, message = "场地预约ID长度必须介于 1 和 64 之间")
    public String getFieldInfoId() {
        return fieldInfoId;
    }

    public void setFieldInfoId(String fieldInfoId) {
        this.fieldInfoId = fieldInfoId;
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

    @ExcelField(title = "金额", type = 1, align = 2, sort = 5)
    public Double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    @Length(min = 0, max = 1, message = "订单状态:0待预约、1预约成功、2已取消长度必须介于 0 和 1 之间")
    public String getOrderState() {
        return orderState;
    }

    @ExcelField(title = "订单状态", type = 1, align = 2, sort = 8)
    @Length(min = 0, max = 1, message = "订单状态:0待预约、1预约成功、2已取消长度必须介于 0 和 1 之间")
    public String getOrderStateForExcel() {
        if (this.outTimeState && "1".equals(this.orderState)) {
            return "已完成";
        } else {
            return DictUtils.getDictLabel(this.orderState, "order_lesson_state", "");
        }
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

    @ExcelField(title = "电话", type = 1, align = 2, sort = 3)
    @Length(min = 0, max = 64, message = "买家联系方式长度必须介于 0 和 64 之间")
    public String getAccountPhoneNumber() {
        return accountPhoneNumber;
    }

    public void setAccountPhoneNumber(String accountPhoneNumber) {
        this.accountPhoneNumber = accountPhoneNumber;
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

    public OrderFieldList getOrderFieldList() {
        return orderFieldList;
    }

    public void setOrderFieldList(OrderFieldList orderFieldList) {
        this.orderFieldList = orderFieldList;
    }

    @ExcelField(title = "终端类型", type = 1, align = 2, sort = 6)
    public String getTypeStr() {
        // 终端类型对应的字符串(0 Android 1 IOS 2 商家后台)
        if (this.type == null) {
            return "";
        } else if (this.type.equals("0")) {
            return "Android";
        } else if (this.type.equals("1")) {
            return "IOS";
        } else if (this.type.equals("2")) {
            return "商家后台";
        } else {
            return "";
        }
    }

    public String getVillageInfoId() {
        return villageInfoId;
    }

    public void setVillageInfoId(String villageInfoId) {
        this.villageInfoId = villageInfoId;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getAccountMsg() {
        return accountMsg;
    }

    public void setAccountMsg(String accountMsg) {
        this.accountMsg = accountMsg;
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

    public String getSearchFlg() {
        return searchFlg;
    }

    public void setSearchFlg(String searchFlg) {
        this.searchFlg = searchFlg;
    }

    public String getUpdateDateString() {
        return updateDateString;
    }

    public void setUpdateDateString(String updateDateString) {
        this.updateDateString = updateDateString;
    }

    public boolean isOutTimeState() {
        return outTimeState;
    }

    public void setOutTimeState(boolean outTimeState) {
        this.outTimeState = outTimeState;
    }

    public String getFieldPartitionPriceId() {
        return fieldPartitionPriceId;
    }

    public void setFieldPartitionPriceId(String fieldPartitionPriceId) {
        this.fieldPartitionPriceId = fieldPartitionPriceId;
    }

    @ExcelField(title = "时间", type = 1, align = 2, sort = 9, width = 6000)
    public String getTimeForExcel() {
        return timeForExcel;
    }

    public void setTimeForExcel(String timeForExcel) {
        this.timeForExcel = timeForExcel;
    }

    @ExcelField(title = "预约场地", type = 1, align = 2, sort = 4)
    public String getFieldNameForExcel() {
        return fieldNameForExcel;
    }

    public void setFieldNameForExcel(String fieldNameForExcel) {
        this.fieldNameForExcel = fieldNameForExcel;
    }

    public String getCancelRemarks() {
        return cancelRemarks;
    }

    public void setCancelRemarks(String cancelRemarks) {
        this.cancelRemarks = cancelRemarks;
    }

    public List<OrderTrack> getOrderTrackList() {
        return orderTrackList;
    }

    public void setOrderTrackList(List<OrderTrack> orderTrackList) {
        this.orderTrackList = orderTrackList;
    }

}