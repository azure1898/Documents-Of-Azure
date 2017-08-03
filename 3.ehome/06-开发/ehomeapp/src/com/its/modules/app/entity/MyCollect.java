/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.its.common.persistence.DataEntity;

/**
 * 我的收藏Entity
 * 
 * @author like
 * @version 2017-07-04
 */
public class MyCollect extends DataEntity<MyCollect> {
	
	private static final long serialVersionUID = 1L;
	private String accountId;		// 会员ID
	private String villageInfoId;		// 楼盘ID
	private String businessInfoId;		// 商家ID
	private Date collectDate;		// 收藏时间
	
	public MyCollect() {
		super();
	}

	public MyCollect(String id){
		super(id);
	}

	@Length(min=0, max=64, message="会员ID长度必须介于 0 和 64 之间")
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	@Length(min=0, max=64, message="楼盘ID长度必须介于 0 和 64 之间")
	public String getVillageInfoId() {
		return villageInfoId;
	}

	public void setVillageInfoId(String villageInfoId) {
		this.villageInfoId = villageInfoId;
	}
	
	@Length(min=0, max=64, message="商家ID长度必须介于 0 和 64 之间")
	public String getBusinessInfoId() {
		return businessInfoId;
	}

	public void setBusinessInfoId(String businessInfoId) {
		this.businessInfoId = businessInfoId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCollectDate() {
		return collectDate;
	}

	public void setCollectDate(Date collectDate) {
		this.collectDate = collectDate;
	}
	
}