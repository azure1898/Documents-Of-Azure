/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.entity;

import org.hibernate.validator.constraints.Length;

import com.its.common.persistence.DataEntity;

/**
 * 广告位置管理Entity
 * @author like
 * @version 2017-07-28
 */
public class AdverPosition extends DataEntity<AdverPosition> {
	
	private static final long serialVersionUID = 1L;
	private String positionName;		// 位置名称
	private String moduleCode;		// 产品线
	private String openScreenFlag;		// 是否开屏广告
	
	public AdverPosition() {
		super();
	}

	public AdverPosition(String id){
		super(id);
	}

	@Length(min=1, max=64, message="位置名称长度必须介于 1 和 64 之间")
	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	
	@Length(min=1, max=100, message="产品线长度必须介于 1 和 100 之间")
	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}
	
	@Length(min=0, max=1, message="是否开屏广告长度必须介于 0 和 1 之间")
	public String getOpenScreenFlag() {
		return openScreenFlag;
	}

	public void setOpenScreenFlag(String openScreenFlag) {
		this.openScreenFlag = openScreenFlag;
	}
	
}