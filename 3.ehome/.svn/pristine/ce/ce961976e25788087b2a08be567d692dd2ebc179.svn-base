/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.its.common.persistence.DataEntity;

/**
 * 手环睡眠记录Entity
 * @author like
 * @version 2017-07-26
 */
public class BraceletSleepRecord extends DataEntity<BraceletSleepRecord> {
	
	private static final long serialVersionUID = 1L;
	private String accountId;		// 会员ID
	private String villageinfoId;		// 楼盘ID
	private String braceletId;		// 手环ID
	private Date recordDate;		// 记录日期
	private Double sleepTime;		// 睡眠时长
	private Double deepSleepTime;		// 深睡时长
	private Double lightSleepTime;		// 浅睡时长
	private Date sleepStart;		// 睡眠时段开始
	private Date sleepEnd;		// 睡眠时段结束
	
	public BraceletSleepRecord() {
		super();
	}

	public BraceletSleepRecord(String id){
		super(id);
	}

	@Length(min=1, max=64, message="会员ID长度必须介于 1 和 64 之间")
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	@Length(min=1, max=64, message="楼盘ID长度必须介于 1 和 64 之间")
	public String getVillageinfoId() {
		return villageinfoId;
	}

	public void setVillageinfoId(String villageinfoId) {
		this.villageinfoId = villageinfoId;
	}
	
	@Length(min=1, max=64, message="手环ID长度必须介于 1 和 64 之间")
	public String getBraceletId() {
		return braceletId;
	}

	public void setBraceletId(String braceletId) {
		this.braceletId = braceletId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="记录日期不能为空")
	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
	
	public Double getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(Double sleepTime) {
		this.sleepTime = sleepTime;
	}
	
	public Double getDeepSleepTime() {
		return deepSleepTime;
	}

	public void setDeepSleepTime(Double deepSleepTime) {
		this.deepSleepTime = deepSleepTime;
	}
	
	public Double getLightSleepTime() {
		return lightSleepTime;
	}

	public void setLightSleepTime(Double lightSleepTime) {
		this.lightSleepTime = lightSleepTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSleepStart() {
		return sleepStart;
	}

	public void setSleepStart(Date sleepStart) {
		this.sleepStart = sleepStart;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSleepEnd() {
		return sleepEnd;
	}

	public void setSleepEnd(Date sleepEnd) {
		this.sleepEnd = sleepEnd;
	}
	
}