/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.entity;

import org.hibernate.validator.constraints.Length;

import com.its.common.persistence.DataEntity;

/**
 * 楼盘产品线推荐商家模式Entity
 * @author sushipeng
 * @version 2017-07-31
 */
public class VillageLineRecomBusiTypeDetail extends DataEntity<VillageLineRecomBusiTypeDetail> {
	
	private static final long serialVersionUID = 1L;
	private String villageLineRecomBusiTypeId;		// 楼盘产品线推荐商家模式ID
	private Integer sortNum;		// 排序序号
	private String prodType;		// 产品模式：0商品购买  1服务预约  2课程购买  3场地预约
	private String describes;		// 描述
	private String picUrl;		// 图片
	private String defaultFlag;		// 是否默认
	
	public VillageLineRecomBusiTypeDetail() {
		super();
	}

	public VillageLineRecomBusiTypeDetail(String id){
		super(id);
	}

	@Length(min=0, max=64, message="楼盘产品线推荐商家模式ID长度必须介于 0 和 64 之间")
	public String getVillageLineRecomBusiTypeId() {
		return villageLineRecomBusiTypeId;
	}

	public void setVillageLineRecomBusiTypeId(String villageLineRecomBusiTypeId) {
		this.villageLineRecomBusiTypeId = villageLineRecomBusiTypeId;
	}
	
	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	
	@Length(min=0, max=1, message="产品模式：0商品购买  1服务预约  2课程购买  3场地预约长度必须介于 0 和 1 之间")
	public String getProdType() {
		return prodType;
	}

	public void setProdType(String prodType) {
		this.prodType = prodType;
	}
	
	@Length(min=0, max=64, message="描述长度必须介于 0 和 64 之间")
	public String getDescribes() {
		return describes;
	}

	public void setDescribes(String describes) {
		this.describes = describes;
	}
	
	@Length(min=0, max=256, message="图片长度必须介于 0 和 256 之间")
	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
	@Length(min=0, max=1, message="是否默认长度必须介于 0 和 1 之间")
	public String getDefaultFlag() {
		return defaultFlag;
	}

	public void setDefaultFlag(String defaultFlag) {
		this.defaultFlag = defaultFlag;
	}
	
}