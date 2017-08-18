package com.its.modules.app.entity;

import org.hibernate.validator.constraints.Length;

import com.its.common.persistence.DataEntity;

/**
 * 楼盘产品线专题推荐Entity
 * 
 * @author sushipeng
 * 
 * @version 2017-08-15
 */
public class VillageLineRecomSpecial extends DataEntity<VillageLineRecomSpecial> {
	
	private static final long serialVersionUID = 1L;
	private String villageLineId;		// 楼盘产品线ID
	private String recomPosition;		// 推荐位置：00 首页专题推荐  10 社区推荐   20 生活推荐
	private String specialName;		// 专题名称
	private Integer sortNum;		// 排序序号
	
	public VillageLineRecomSpecial() {
		super();
	}

	public VillageLineRecomSpecial(String id){
		super(id);
	}

	@Length(min=0, max=64, message="楼盘产品线ID长度必须介于 0 和 64 之间")
	public String getVillageLineId() {
		return villageLineId;
	}

	public void setVillageLineId(String villageLineId) {
		this.villageLineId = villageLineId;
	}
	
	@Length(min=0, max=2, message="推荐位置：00 首页专题推荐  10 社区推荐   20 生活推荐长度必须介于 0 和 2 之间")
	public String getRecomPosition() {
		return recomPosition;
	}

	public void setRecomPosition(String recomPosition) {
		this.recomPosition = recomPosition;
	}
	
	@Length(min=0, max=32, message="专题名称长度必须介于 0 和 32 之间")
	public String getSpecialName() {
		return specialName;
	}

	public void setSpecialName(String specialName) {
		this.specialName = specialName;
	}
	
	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	
}