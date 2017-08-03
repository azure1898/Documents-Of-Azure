/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.setup.entity;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.its.common.persistence.DataEntity;
import com.its.common.utils.excel.annotation.ExcelField;
import com.its.common.utils.excel.fieldtype.CategoryListType;
import com.its.modules.village.entity.VillageInfo;

/**
 * 商家信息管理Entity
 * 
 * @author zhujiao
 * @version 2017-06-26
 */
public class BusinessInfo extends DataEntity<BusinessInfo> {

    private static final long serialVersionUID = 1L;
    private String businessName; // 商户名称
    private String businessPic; // 商家图片
    private String contactPerson; // 联系人
    private String phoneNum; // 联系电话
    private String addrPro; // 地址_省
    private String addrCity; // 地址_市
    private String addrArea; // 地址_区
    private String addrDetail; // 详址
    private String longitude; // 经度
    private String latitude; // 纬度
    private String businessLabel; // 商家标签
    private String businessHours; // 营业时间
    private String recomFlag; // 是否推荐：0否 1是
    private String onlineFlag; // 是否在线交易
    private String groupPurchaseFlag; // 是否支持团购
    private String balanceModel; // 结算模式：0 单笔订单比例抽成 1 单笔订单固定金额抽成
    private String collectFees; // 收取费用
    private String balanceCycle; // 结算周期：0 按周结算 1按月结算 2按天结算
    private String businessIntroduce; // 商家介绍描述
    private String businessState; // 营业状态：0停业 1正常营业
    private Date stopBusinessBeginTime; // 暂停营业起始时间
    private Date stopBusinessEndTime; // 暂停营业结束时间
    private String serviceModel; // 预约服务方式：0到店 1上门
    private String serviceTimeInterval; // 服务时间间隔
    private String serviceCharge; // 上门服务费用
    private String shortestTime; // 最短上门时间
    private String distributeModel; // 商品配送方式：0商家配送 1第三方配送
    private String distributeTimeInterval; // 时间片显示区间(配送时间间隔)
    private String shortestArriveTime; // 最短送达时间
    private String distributeCharge; // 配送费用
    private String fullDistributeFlag; // 是否满额起配
    private String fullDistributeMoney; // 满额起配金额
    private String freeDistributeFlag; // 是否满额免运费
    private String freeDistributeMoney; // 满额免运费的金额
    private String depositBank; // 开户银行
    private String accountName; // 开户名称
    private String bankAccount; // 银行账号
    private String promotionFlag; // 促销设置（启用满减活动）
    
    private Date beginCreateDate; // 开始 录入时间
    private Date endCreateDate; // 结束 录入时间
    private String useState;

    private BusinessCategorydict businessCategorydict; // 根据分类查询商家条件
    private List<BusinessCategorydict> categoryList = Lists.newArrayList(); // 拥有分类列表

    private VillageInfo villageInfo; // 根据楼盘查询商家
    private List<VillageInfo> villageList = Lists.newArrayList(); // 拥有楼盘信息
    private String sHours;
    private String eHours;
    private String sMinutes;
    private String eMinutes;
    private String sHours1;
    private String eHours1;
    private String sMinutes1;
    private String eMinutes1;
    
    private String money;//促销活动的达标金额
    private String benefitMoney;//促销活动的优惠金额
    private String unitName;//单位设置的 单位名称
    
    private String customUnitFlag;//启用自定义单位
    private String soundWarn;
    private String stockWarn;
    private String stockWarnNum;

    private String addrCityCode; // 地址_市
    public String getUseState() {
        return useState;
    }

    public void setUseState(String useState) {
        this.useState = useState;
    }

    public String getsHours() {
        return sHours;
    }

    public void setsHours(String sHours) {
        this.sHours = sHours;
    }

    public String geteHours() {
        return eHours;
    }

    public void seteHours(String eHours) {
        this.eHours = eHours;
    }

    public String getsMinutes() {
        return sMinutes;
    }

    public void setsMinutes(String sMinutes) {
        this.sMinutes = sMinutes;
    }

    public String geteMinutes() {
        return eMinutes;
    }

    public void seteMinutes(String eMinutes) {
        this.eMinutes = eMinutes;
    }

    public String getsHours1() {
        return sHours1;
    }

    public void setsHours1(String sHours1) {
        this.sHours1 = sHours1;
    }

    public String geteHours1() {
        return eHours1;
    }

    public void seteHours1(String eHours1) {
        this.eHours1 = eHours1;
    }

    public String getsMinutes1() {
        return sMinutes1;
    }

    public void setsMinutes1(String sMinutes1) {
        this.sMinutes1 = sMinutes1;
    }

    public String geteMinutes1() {
        return eMinutes1;
    }

    public void seteMinutes1(String eMinutes1) {
        this.eMinutes1 = eMinutes1;
    }

    public BusinessCategorydict getBusinessCategorydict() {
        return businessCategorydict;
    }

    public void setBusinessCategorydict(BusinessCategorydict businessCategorydict) {
        this.businessCategorydict = businessCategorydict;
    }

    @JsonIgnore
    @ExcelField(title = "商家分类", align = 1, sort = 800, fieldType = CategoryListType.class)
    public List<BusinessCategorydict> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<BusinessCategorydict> categoryList) {
        this.categoryList = categoryList;
    }

    @JsonIgnore
    public List<String> getCategoryIdList() {
        List<String> categoryIdList = Lists.newArrayList();
        for (BusinessCategorydict businessCategorydict : categoryList) {
            categoryIdList.add(businessCategorydict.getId());
        }
        return categoryIdList;
    }

    public void setCategoryIdList(List<String> categoryIdList) {
        categoryList = Lists.newArrayList();
        for (String categoryId : categoryIdList) {
            BusinessCategorydict businessCategorydict = new BusinessCategorydict();
            businessCategorydict.setId(categoryId);
            categoryList.add(businessCategorydict);
        }
    }

    public BusinessInfo() {
        super();
        this.recomFlag = "0";// 是否推荐 默认否
        this.onlineFlag = "1";// 是否在线交易，默认为“是”
        this.groupPurchaseFlag = "0";// 是否支持团购 默认为“否”
        this.balanceModel = "0";// 结算模式：0 单笔订单比例抽成 1 单笔订单固定金额抽成
        this.businessState = "1";// 正常营业

    }

    public BusinessInfo(String id) {
        super(id);
    }

    @Length(min = 0, max = 64, message = "商户名称长度必须介于 0 和 64 之间")
    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    @Length(min = 1, max = 64, message = "商家图片长度必须介于 1 和 64 之间")
    public String getBusinessPic() {
        return businessPic;
    }

    public void setBusinessPic(String businessPic) {
        this.businessPic = businessPic;
    }

    @Length(min = 1, max = 64, message = "联系人长度必须介于 1 和 64 之间")
    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    @Length(min = 1, max = 64, message = "联系电话长度必须介于 1 和 64 之间")
    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Length(min = 1, max = 64, message = "地址_省长度必须介于 1 和 64 之间")
    public String getAddrPro() {
        return addrPro;
    }

    public void setAddrPro(String addrPro) {
        this.addrPro = addrPro;
    }

    @Length(min = 1, max = 64, message = "地址_市长度必须介于 1 和 64 之间")
    public String getAddrCity() {
        return addrCity;
    }

    public void setAddrCity(String addrCity) {
        this.addrCity = addrCity;
    }

    @Length(min = 1, max = 64, message = "地址_区长度必须介于 1 和 64 之间")
    public String getAddrArea() {
        return addrArea;
    }

    public void setAddrArea(String addrArea) {
        this.addrArea = addrArea;
    }

    @Length(min = 1, max = 200, message = "详址长度必须介于 1 和 200 之间")
    public String getAddrDetail() {
        return addrDetail;
    }

    public void setAddrDetail(String addrDetail) {
        this.addrDetail = addrDetail;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Length(min = 1, max = 128, message = "商家标签长度必须介于 1 和 128 之间")
    public String getBusinessLabel() {
        return businessLabel;
    }

    public void setBusinessLabel(String businessLabel) {
        this.businessLabel = businessLabel;
    }

    @Length(min = 0, max = 256, message = "营业时间长度必须介于 0和 256 之间")
    public String getBusinessHours() {
        return businessHours;
    }

    public void setBusinessHours(String businessHours) {
        this.businessHours = businessHours;
    }

    @Length(min = 1, max = 1, message = "是否推荐：0否  1是长度必须介于 1 和 1 之间")
    public String getRecomFlag() {
        return recomFlag;
    }

    public void setRecomFlag(String recomFlag) {
        this.recomFlag = recomFlag;
    }

    @Length(min = 1, max = 1, message = "是否在线交易长度必须介于 1 和 1 之间")
    public String getOnlineFlag() {
        return onlineFlag;
    }

    public void setOnlineFlag(String onlineFlag) {
        this.onlineFlag = onlineFlag;
    }

    @Length(min = 1, max = 1, message = "是否支持团购长度必须介于 1 和 1 之间")
    public String getGroupPurchaseFlag() {
        return groupPurchaseFlag;
    }

    public void setGroupPurchaseFlag(String groupPurchaseFlag) {
        this.groupPurchaseFlag = groupPurchaseFlag;
    }

    @Length(min = 1, max = 1, message = "结算模式：0 单笔订单比例抽成  1 单笔订单固定金额抽成长度必须介于 1 和 1 之间")
    public String getBalanceModel() {
        return balanceModel;
    }

    public void setBalanceModel(String balanceModel) {
        this.balanceModel = balanceModel;
    }

    public String getCollectFees() {
        return collectFees;
    }

    public void setCollectFees(String collectFees) {
        this.collectFees = collectFees;
    }

    @Length(min = 1, max = 1, message = "结算周期：0 按周结算  1按月结算  2按天结算长度必须介于 1 和 1 之间")
    public String getBalanceCycle() {
        return balanceCycle;
    }

    public void setBalanceCycle(String balanceCycle) {
        this.balanceCycle = balanceCycle;
    }

    @Length(min = 0, max = 2000, message = "商家介绍描述长度必须介于 0 和 2000 之间")
    public String getBusinessIntroduce() {
        return businessIntroduce;
    }

    public void setBusinessIntroduce(String businessIntroduce) {
        this.businessIntroduce = businessIntroduce;
    }

    @Length(min = 1, max = 1, message = "营业状态：0停业  1正常营业长度必须介于 1 和 1 之间")
    public String getBusinessState() {
        return businessState;
    }

    public void setBusinessState(String businessState) {
        this.businessState = businessState;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getStopBusinessBeginTime() {
        return stopBusinessBeginTime;
    }

    public void setStopBusinessBeginTime(Date stopBusinessBeginTime) {
        this.stopBusinessBeginTime = stopBusinessBeginTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getStopBusinessEndTime() {
        return stopBusinessEndTime;
    }

    public void setStopBusinessEndTime(Date stopBusinessEndTime) {
        this.stopBusinessEndTime = stopBusinessEndTime;
    }

    @Length(min = 1, max = 1, message = "预约服务方式：0到店  1上门长度必须介于 1 和 1 之间")
    public String getServiceModel() {
        return serviceModel;
    }

    public void setServiceModel(String serviceModel) {
        this.serviceModel = serviceModel;
    }

    @Length(min = 1, max = 11, message = "服务时间间隔长度必须介于 1 和 11 之间")
    public String getServiceTimeInterval() {
        return serviceTimeInterval;
    }

    public void setServiceTimeInterval(String serviceTimeInterval) {
        this.serviceTimeInterval = serviceTimeInterval;
    }

    public String getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(String serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    @Length(min = 1, max = 11, message = "最短上门时间长度必须介于 1 和 11 之间")
    public String getShortestTime() {
        return shortestTime;
    }

    public void setShortestTime(String shortestTime) {
        this.shortestTime = shortestTime;
    }

    @Length(min = 1, max = 1, message = "商品配送方式：0商家配送  1第三方配送长度必须介于 1 和 1 之间")
    public String getDistributeModel() {
        return distributeModel;
    }

    public void setDistributeModel(String distributeModel) {
        this.distributeModel = distributeModel;
    }

    @Length(min = 1, max = 11, message = "时间片显示区间(配送时间间隔)长度必须介于 1 和 11 之间")
    public String getDistributeTimeInterval() {
        return distributeTimeInterval;
    }

    public void setDistributeTimeInterval(String distributeTimeInterval) {
        this.distributeTimeInterval = distributeTimeInterval;
    }

    @Length(min = 1, max = 11, message = "最短送达时间长度必须介于 1 和 11 之间")
    public String getShortestArriveTime() {
        return shortestArriveTime;
    }

    public void setShortestArriveTime(String shortestArriveTime) {
        this.shortestArriveTime = shortestArriveTime;
    }

    public String getDistributeCharge() {
        return distributeCharge;
    }

    public void setDistributeCharge(String distributeCharge) {
        this.distributeCharge = distributeCharge;
    }

    @Length(min = 1, max = 1, message = "是否满额起配长度必须介于 1 和 1 之间")
    public String getFullDistributeFlag() {
        return fullDistributeFlag;
    }

    public void setFullDistributeFlag(String fullDistributeFlag) {
        this.fullDistributeFlag = fullDistributeFlag;
    }

    public String getFullDistributeMoney() {
        return fullDistributeMoney;
    }

    public void setFullDistributeMoney(String fullDistributeMoney) {
        this.fullDistributeMoney = fullDistributeMoney;
    }

    @Length(min = 1, max = 1, message = "是否满额免运费长度必须介于 1 和 1 之间")
    public String getFreeDistributeFlag() {
        return freeDistributeFlag;
    }

    public void setFreeDistributeFlag(String freeDistributeFlag) {
        this.freeDistributeFlag = freeDistributeFlag;
    }

    public String getFreeDistributeMoney() {
        return freeDistributeMoney;
    }

    public void setFreeDistributeMoney(String freeDistributeMoney) {
        this.freeDistributeMoney = freeDistributeMoney;
    }

    @Length(min = 1, max = 64, message = "开户银行长度必须介于 1 和 64 之间")
    public String getDepositBank() {
        return depositBank;
    }

    public void setDepositBank(String depositBank) {
        this.depositBank = depositBank;
    }

    @Length(min = 0, max = 64, message = "开户名称长度必须介于 0 和 64 之间")
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @Length(min = 0, max = 32, message = "银行账号长度必须介于 0 和 32 之间")
    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Length(min = 0, max = 1, message = "促销设置（启用满减活动）长度必须介于 0 和 1 之间")
    public String getPromotionFlag() {
        return promotionFlag;
    }

    public void setPromotionFlag(String promotionFlag) {
        this.promotionFlag = promotionFlag;
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

    public VillageInfo getVillageInfo() {
        return villageInfo;
    }

    public void setVillageInfo(VillageInfo villageInfo) {
        this.villageInfo = villageInfo;
    }

    /**
     * 范围内的楼盘数据
     */
    public List<VillageInfo> getVillageList() {
        return villageList;
    }

    public void setVillageList(List<VillageInfo> villageList) {
        this.villageList = villageList;
    }

    public List<String> getVillageIdList() {
        List<String> villageIdList = Lists.newArrayList();
        for (VillageInfo village : villageList) {
            villageIdList.add(village.getId());
        }
        return villageIdList;
    }

    public void setVillageIdList(List<String> villageIdList) {
        villageList = Lists.newArrayList();
        for (String villageId : villageIdList) {
            VillageInfo village = new VillageInfo();
            village.setId(villageId);
            villageList.add(village);
        }
    }

    public String getVillageIds() {
        return StringUtils.join(getVillageIdList(), ",");
    }

    public void setVillageIds(String villageIds) {
        villageList = Lists.newArrayList();
        if (villageIds != null) {
            String[] ids = StringUtils.split(villageIds, ",");
            setVillageIdList(Lists.newArrayList(ids));
        }
    }


    public String getAddrCityCode() {
        return addrCityCode;
    }

    public void setAddrCityCode(String addrCityCode) {
        this.addrCityCode = addrCityCode;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getBenefitMoney() {
        return benefitMoney;
    }

    public void setBenefitMoney(String benefitMoney) {
        this.benefitMoney = benefitMoney;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getCustomUnitFlag() {
        return customUnitFlag;
    }

    public void setCustomUnitFlag(String customUnitFlag) {
        this.customUnitFlag = customUnitFlag;
    }

    public String getSoundWarn() {
        return soundWarn;
    }

    public void setSoundWarn(String soundWarn) {
        this.soundWarn = soundWarn;
    }

    public String getStockWarn() {
        return stockWarn;
    }

    public void setStockWarn(String stockWarn) {
        this.stockWarn = stockWarn;
    }

    public String getStockWarnNum() {
        return stockWarnNum;
    }

    public void setStockWarnNum(String stockWarnNum) {
        this.stockWarnNum = stockWarnNum;
    }
    
}