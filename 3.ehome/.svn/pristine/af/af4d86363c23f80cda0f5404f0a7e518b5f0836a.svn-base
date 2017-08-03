package com.its.modules.app.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.its.common.persistence.DataEntity;

/**
 * 团购管理Entity
 * 
 * @author like
 * 
 * @version 2017-07-04
 */
public class GroupPurchase extends DataEntity<GroupPurchase> {
	
	private static final long serialVersionUID = 1L;
	private String moduleId;		// 模块ID
	private String businessinfoId;		// 商家ID
	private String groupPurcName;		// 团购名称
	private String groupPurcPic;		// 团购图片
	private Double marketMoney;		// 市场价
	private Double groupPurcMoney;		// 团购价
	private Integer restrictionNumber;		// 用户限购数
	private String supportType;		// 商家支持：0支持随时退  1支持过期退 2免预约
	private String groupPurcDetail;		// 团购详情
	private Date validityStartTime;		// 团购券有效期开始时间
	private Date validityEndTime;		// 团购券有效期结束时间
	private String useTime;		// 使用时间
	private String useRule;		// 使用规则
	private String sortNum;		// 排序序号
	private String groupPurcState;		// 团购状态：0待开始、1活动中、2已结束、3已撤消
	
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
	
	public Double getMarketMoney() {
		return marketMoney;
	}

	public void setMarketMoney(Double marketMoney) {
		this.marketMoney = marketMoney;
	}
	
	public Double getGroupPurcMoney() {
		return groupPurcMoney;
	}

	public void setGroupPurcMoney(Double groupPurcMoney) {
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
	
	@Length(min=0, max=11, message="排序序号长度必须介于 0 和 11 之间")
	public String getSortNum() {
		return sortNum;
	}

	public void setSortNum(String sortNum) {
		this.sortNum = sortNum;
	}
	
	@Length(min=0, max=1, message="团购状态：0待开始、1活动中、2已结束、3已撤消长度必须介于 0 和 1 之间")
	public String getGroupPurcState() {
		return groupPurcState;
	}

	public void setGroupPurcState(String groupPurcState) {
		this.groupPurcState = groupPurcState;
	}
}