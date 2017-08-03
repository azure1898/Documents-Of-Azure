/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.entity;

import org.hibernate.validator.constraints.Length;

import com.its.common.persistence.DataEntity;

/**
 * 意见反馈Entity
 * @author like
 * @version 2017-07-17
 */
public class Feedback extends DataEntity<Feedback> {
	
	private static final long serialVersionUID = 1L;
	private String accountId;		// 会员ID
	private String villageInfoId;		// 楼盘ID
	private String content;		// 内容
	
	public Feedback() {
		super();
	}

	public Feedback(String id){
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
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}