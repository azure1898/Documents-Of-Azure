/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.its.common.persistence.DataEntity;

/**
 * 每日运动记录Entity
 * @author like
 * @version 2017-07-24
 */
public class BraceletExerciseRecord extends DataEntity<BraceletExerciseRecord> {
	
	private static final long serialVersionUID = 1L;
	private String accountId;		// account_id
	private String villageinfoId;		// 楼盘ID
	private String braceletId;		// bracelet_id
	private Date recordDate;		// record_date
	private Integer walkNumber;		// walk_number
	private Double walkMileage;		// walk_mileage
	private Double walkCalorie;		// walk_calorie
	private Integer runNumber;		// run_number
	private Double runMileage;		// run_mileage
	private Double runCalorie;		// run_calorie
	
	public BraceletExerciseRecord() {
		super();
	}

	public BraceletExerciseRecord(String id){
		super(id);
	}

	@Length(min=0, max=64, message="account_id长度必须介于 0 和 64 之间")
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	@Length(min=0, max=64, message="楼盘ID长度必须介于 0 和 64 之间")
	public String getVillageinfoId() {
		return villageinfoId;
	}

	public void setVillageinfoId(String villageinfoId) {
		this.villageinfoId = villageinfoId;
	}
	
	@Length(min=0, max=64, message="bracelet_id长度必须介于 0 和 64 之间")
	public String getBraceletId() {
		return braceletId;
	}

	public void setBraceletId(String braceletId) {
		this.braceletId = braceletId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
	
	public Integer getWalkNumber() {
		return walkNumber;
	}

	public void setWalkNumber(Integer walkNumber) {
		this.walkNumber = walkNumber;
	}
	
	public Double getWalkMileage() {
		return walkMileage;
	}

	public void setWalkMileage(Double walkMileage) {
		this.walkMileage = walkMileage;
	}
	
	public Double getWalkCalorie() {
		return walkCalorie;
	}

	public void setWalkCalorie(Double walkCalorie) {
		this.walkCalorie = walkCalorie;
	}
	
	public Integer getRunNumber() {
		return runNumber;
	}

	public void setRunNumber(Integer runNumber) {
		this.runNumber = runNumber;
	}
	
	public Double getRunMileage() {
		return runMileage;
	}

	public void setRunMileage(Double runMileage) {
		this.runMileage = runMileage;
	}
	
	public Double getRunCalorie() {
		return runCalorie;
	}

	public void setRunCalorie(Double runCalorie) {
		this.runCalorie = runCalorie;
	}
	
}