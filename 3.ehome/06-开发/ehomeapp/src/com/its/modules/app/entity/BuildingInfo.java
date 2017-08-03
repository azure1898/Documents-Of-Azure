package com.its.modules.app.entity;

import org.hibernate.validator.constraints.Length;

import com.its.common.persistence.DataEntity;

/**
 * 楼栋信息Entity
 * 
 * @author sushipeng
 * 
 * @version 2017-07-21
 */
public class BuildingInfo extends DataEntity<BuildingInfo> {
	
	private static final long serialVersionUID = 1L;
	private String villageInfoId;		// 楼盘信息ID
	private String buildingName;		// 楼栋名称
	private Integer sortNum;		// 排序字段
	
	public BuildingInfo() {
		super();
	}

	public BuildingInfo(String id){
		super(id);
	}

	@Length(min=0, max=64, message="楼盘信息ID长度必须介于 0 和 64 之间")
	public String getVillageInfoId() {
		return villageInfoId;
	}

	public void setVillageInfoId(String villageInfoId) {
		this.villageInfoId = villageInfoId;
	}
	
	@Length(min=0, max=64, message="楼栋名称长度必须介于 0 和 64 之间")
	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	
	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	
}