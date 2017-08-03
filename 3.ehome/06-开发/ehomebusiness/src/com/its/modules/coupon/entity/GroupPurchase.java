/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.coupon.entity;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import com.its.common.persistence.DataEntity;

/**
 * 优惠验券管理Entity
 * @author caojing
 * @version 2017-07-25
 */
public class GroupPurchase extends DataEntity<GroupPurchase> {
	
	private static final long serialVersionUID = 1L;
	private String moduleId;		// 模块ID
	private String businessinfoId;		// 商家ID
	private String businessName;    //商家名称
	private String groupPurcName;		// 团购名称
	private String groupPurcPic;		// 团购图片
	private String marketMoney;		// 市场价
	private String groupPurcMoney;		// 团购价
	private Integer restrictionNumber;		// 用户限购数
	private String supportType;		// 商家支持：0支持随时退  1支持过期退 2免预约
	private String groupPurcDetail;		// 团购详情
	private Date validityStartTime;		// 团购券有效期开始时间
	private Date validityEndTime;		// 团购券有效期结束时间
	private String useTime;		// 使用时间
	private String useRule;		// 使用规则
	private Integer sortNum;		// 排序序号
	private String groupPurcState;		// 团购状态：0待开始、1活动中、2已结束、3已撤消
	
	private Date startTime;		// 团购开始时间
	private Date endTime;		// 团购结束时间
	private Integer stockNum;		// 库存量
	private Integer saleNum;		// 已出售数量
	
	public GroupPurchase() {
		super();
	}

	public GroupPurchase(String id){
		super(id);
	}

	@Length(min=0, max=64, message="模块ID长度必须介于 0 和 64 之间")
	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	
	@Length(min=0, max=64, message="商家ID长度必须介于 0 和 64 之间")
	public String getBusinessinfoId() {
		return businessinfoId;
	}

	public void setBusinessinfoId(String businessinfoId) {
		this.businessinfoId = businessinfoId;
	}
	
	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	@Length(min=0, max=200, message="团购名称长度必须介于 0 和 200 之间")
	public String getGroupPurcName() {
		return groupPurcName;
	}

	public void setGroupPurcName(String groupPurcName) {
		this.groupPurcName = groupPurcName;
	}
	
	@Length(min=0, max=200, message="团购图片长度必须介于 0 和 200 之间")
	public String getGroupPurcPic() {
		return groupPurcPic;
	}

	public void setGroupPurcPic(String groupPurcPic) {
		this.groupPurcPic = groupPurcPic;
	}
	
	public String getMarketMoney() {
		return marketMoney;
	}

	public void setMarketMoney(String marketMoney) {
		this.marketMoney = marketMoney;
	}
	
	public String getGroupPurcMoney() {
		return groupPurcMoney;
	}

	public void setGroupPurcMoney(String groupPurcMoney) {
		this.groupPurcMoney = groupPurcMoney;
	}
	
	public Integer getRestrictionNumber() {
		return restrictionNumber;
	}

	public void setRestrictionNumber(Integer restrictionNumber) {
		this.restrictionNumber = restrictionNumber;
	}
	
	@Length(min=0, max=10, message="商家支持：0支持随时退  1支持过期退 2免预约长度必须介于 0 和 10 之间")
	public String getSupportType() {
		return supportType;
	}

	public void setSupportType(String supportType) {
		this.supportType = supportType;
	}
	
	public String getGroupPurcDetail() {
		return groupPurcDetail;
	}

	public void setGroupPurcDetail(String groupPurcDetail) {
		this.groupPurcDetail = groupPurcDetail;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getValidityStartTime() {
		return validityStartTime;
	}

	public void setValidityStartTime(Date validityStartTime) {
		this.validityStartTime = validityStartTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getValidityEndTime() {
		return validityEndTime;
	}

	public void setValidityEndTime(Date validityEndTime) {
		this.validityEndTime = validityEndTime;
	}
	
	@Length(min=0, max=200, message="使用时间长度必须介于 0 和 200 之间")
	public String getUseTime() {
		return useTime;
	}

	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}
	
	public String getUseRule() {
		return useRule;
	}

	public void setUseRule(String useRule) {
		this.useRule = useRule;
	}
	
	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	
	@Length(min=0, max=1, message="团购状态：0待开始、1活动中、2已结束、3已撤消长度必须介于 0 和 1 之间")
	public String getGroupPurcState() {
		return groupPurcState;
	}

	public void setGroupPurcState(String groupPurcState) {
		this.groupPurcState = groupPurcState;
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
	
	public Integer getStockNum() {
		return stockNum;
	}

	public void setStockNum(Integer stockNum) {
		this.stockNum = stockNum;
	}
	
	public Integer getSaleNum() {
		return saleNum;
	}

	public void setSaleNum(Integer saleNum) {
		this.saleNum = saleNum;
	}
	
	public List<String> getSupportTypeList() {
		List<String> list = Lists.newArrayList();
		if (supportType != null){
			for (String s : StringUtils.split(supportType, ",")) {
				list.add(s);
			}
		}
		return list;
	}
	
	public void setSupportTypeList(List<String> list) {
		supportType = ","+StringUtils.join(list, ",")+",";
	}
}