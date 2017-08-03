package com.its.modules.app.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.its.common.persistence.DataEntity;

/**
 * 楼盘产品线及产品线设置管理Entity
 * 
 * @author sushipeng
 * 
 * @version 2017-07-28
 */
public class VillageLine extends DataEntity<VillageLine> {
	
	private static final long serialVersionUID = 1L;
	private String villageInfoId;		// 楼盘ID
	private String productLine;		// 产品线：0业主APP  1住宅交互媒体机  2售楼处交互媒体机
	private String setState;		// 设置状态：0未设置  1已设置
	private Date setTime;		// 设置时间
	private String mainNavigation;		// 主导航：0首页  1社区  2生活  3邻里圈
	private String communityModule;		// 社区模块
	private String lifeModule;		// 生活模块
	private String maintRecomModule;		// 首页推荐模块
	private String communityRecomModule;		// 社区推荐模块
	private String lifeRecomModule;		// 生活推荐模块
	
	public VillageLine() {
		super();
	}

	public VillageLine(String id){
		super(id);
	}

	@Length(min=0, max=64, message="楼盘ID长度必须介于 0 和 64 之间")
	public String getVillageInfoId() {
		return villageInfoId;
	}

	public void setVillageInfoId(String villageInfoId) {
		this.villageInfoId = villageInfoId;
	}
	
	@Length(min=0, max=1, message="产品线：0业主APP  1住宅交互媒体机  2售楼处交互媒体机长度必须介于 0 和 1 之间")
	public String getProductLine() {
		return productLine;
	}

	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}
	
	@Length(min=0, max=1, message="设置状态：0未设置  1已设置长度必须介于 0 和 1 之间")
	public String getSetState() {
		return setState;
	}

	public void setSetState(String setState) {
		this.setState = setState;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSetTime() {
		return setTime;
	}

	public void setSetTime(Date setTime) {
		this.setTime = setTime;
	}
	
	@Length(min=0, max=256, message="主导航：0首页  1社区  2生活  3邻里圈长度必须介于 0 和 256 之间")
	public String getMainNavigation() {
		return mainNavigation;
	}

	public void setMainNavigation(String mainNavigation) {
		this.mainNavigation = mainNavigation;
	}
	
	public String getCommunityModule() {
		return communityModule;
	}

	public void setCommunityModule(String communityModule) {
		this.communityModule = communityModule;
	}
	
	public String getLifeModule() {
		return lifeModule;
	}

	public void setLifeModule(String lifeModule) {
		this.lifeModule = lifeModule;
	}
	
	@Length(min=0, max=6400, message="首页推荐模块长度必须介于 0 和 6400 之间")
	public String getMaintRecomModule() {
		return maintRecomModule;
	}

	public void setMaintRecomModule(String maintRecomModule) {
		this.maintRecomModule = maintRecomModule;
	}
	
	@Length(min=0, max=6400, message="社区推荐模块长度必须介于 0 和 6400 之间")
	public String getCommunityRecomModule() {
		return communityRecomModule;
	}

	public void setCommunityRecomModule(String communityRecomModule) {
		this.communityRecomModule = communityRecomModule;
	}
	
	@Length(min=0, max=6400, message="生活推荐模块长度必须介于 0 和 6400 之间")
	public String getLifeRecomModule() {
		return lifeRecomModule;
	}

	public void setLifeRecomModule(String lifeRecomModule) {
		this.lifeRecomModule = lifeRecomModule;
	}
}