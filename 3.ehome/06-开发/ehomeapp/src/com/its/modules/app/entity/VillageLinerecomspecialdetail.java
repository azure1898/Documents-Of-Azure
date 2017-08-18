package com.its.modules.app.entity;

import org.hibernate.validator.constraints.Length;

import com.its.common.persistence.DataEntity;

/**
 * 楼盘产品线专题推荐明细Entity
 * 
 * @author sushipeng
 * 
 * @version 2017-08-15
 */
public class VillageLineRecomSpecialDetail extends DataEntity<VillageLineRecomSpecialDetail> {
	
	private static final long serialVersionUID = 1L;
	private String villageLineRecomSpecialId;		// 楼盘产品线专题推荐ID
	private String recomType;		// 推荐类型：0商家  1模块
	private Integer sortNum;		// 排序序号
	private String recomModuleId;		// 推荐商家ID/模块ID
	private String businessCategoryDictId;		// business_category_dict_id
	private String recomBusinessId;		// recom_business_id
	private String describes;		// 描述
	private String picUrl;		// 图片
	
	public VillageLineRecomSpecialDetail() {
		super();
	}

	public VillageLineRecomSpecialDetail(String id){
		super(id);
	}

	@Length(min=0, max=64, message="楼盘产品线专题推荐ID长度必须介于 0 和 64 之间")
	public String getVillageLineRecomSpecialId() {
		return villageLineRecomSpecialId;
	}

	public void setVillageLineRecomSpecialId(String villageLineRecomSpecialId) {
		this.villageLineRecomSpecialId = villageLineRecomSpecialId;
	}
	
	@Length(min=0, max=1, message="推荐类型：0商家  1模块长度必须介于 0 和 1 之间")
	public String getRecomType() {
		return recomType;
	}

	public void setRecomType(String recomType) {
		this.recomType = recomType;
	}
	
	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	
	@Length(min=0, max=64, message="推荐商家ID/模块ID长度必须介于 0 和 64 之间")
	public String getRecomModuleId() {
		return recomModuleId;
	}

	public void setRecomModuleId(String recomModuleId) {
		this.recomModuleId = recomModuleId;
	}
	
	@Length(min=0, max=64, message="business_category_dict_id长度必须介于 0 和 64 之间")
	public String getBusinessCategoryDictId() {
		return businessCategoryDictId;
	}

	public void setBusinessCategoryDictId(String businessCategoryDictId) {
		this.businessCategoryDictId = businessCategoryDictId;
	}
	
	@Length(min=0, max=64, message="recom_business_id长度必须介于 0 和 64 之间")
	public String getRecomBusinessId() {
		return recomBusinessId;
	}

	public void setRecomBusinessId(String recomBusinessId) {
		this.recomBusinessId = recomBusinessId;
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
	
}