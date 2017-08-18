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
 * 每日运动记录Entity
 * @author yinxiaoyin
 * @version 2017-08-18
 */
public class BraceletExerciseRecord extends DataEntity<BraceletExerciseRecord> {
	
	private static final long serialVersionUID = 1L;
	private String accountId;		// 会员ID
	private String villageinfoId;		// 楼盘ID
	private String braceletId;		// 手环ID
	private Date recordDate;		// 记录日期
	private Integer walkNumber;		// 走步步数
	private Double walkMileage;		// 走步里程
	private Integer walkCalorie;		// walk_calorie
	private Integer runNumber;		// 跑步步数
	private Double runMileage;		// 跑步里程
	private Integer runCalorie;		// run_calorie
	private Integer totalNumber;		// 总运动步数
	private Double totalMileage;		// 总运动里程
	private Integer totalCalorie;		// 总运动热量
	private Integer type;		// 运动类型
	
	public BraceletExerciseRecord() {
		super();
	}

	public BraceletExerciseRecord(String id){
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
	
	public Integer getWalkCalorie() {
		return walkCalorie;
	}

	public void setWalkCalorie(Integer walkCalorie) {
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
	
	public Integer getRunCalorie() {
		return runCalorie;
	}

	public void setRunCalorie(Integer runCalorie) {
		this.runCalorie = runCalorie;
	}
	
	public Integer getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}
	
	public Double getTotalMileage() {
		return totalMileage;
	}

	public void setTotalMileage(Double totalMileage) {
		this.totalMileage = totalMileage;
	}
	
	public Integer getTotalCalorie() {
		return totalCalorie;
	}

	public void setTotalCalorie(Integer totalCalorie) {
		this.totalCalorie = totalCalorie;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
}