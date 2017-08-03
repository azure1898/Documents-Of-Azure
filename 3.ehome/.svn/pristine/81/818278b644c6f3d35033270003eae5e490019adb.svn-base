/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.field.entity;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;
import com.its.common.persistence.DataEntity;

/**
 * 场地预约Entity
 * @author xzc
 * @version 2017-06-30
 */
public class FieldInfo extends DataEntity<FieldInfo> {
	
	private static final long serialVersionUID = 1L;
	private String businessInfoId;		// 商家id
	private String serialNumbers;		// 场地编号
	private String name;		// 场地名称
	private String monday;		// 星期一是否营业:0否1是
	private String tuesday;		// 星期二是否营业:0否1是
	private String wednesday;		// 星期三是否营业:0否1是
	private String thursday;		// 星期四是否营业:0否1是
	private String friday;		// 星期五是否营业:0否1是
	private String saturday;		// 星期六是否营业:0否1是
	private String sunday;		// 星期日是否营业:0否1是
	private Integer shortTime;		// 场地最短预约时间
	private String startOpenTime;		// 开放预约起始时段
	private String endOpenTime;		// 开放预约结束时段
	private Double basePrice;		// 场地基础价格(每小时)
	private String isChild;		// 是否分段预约:0否1是
	private String state;		// 是否暂停 0否1是
	private String createState;		// 场地是否生成 （是否生成分段价格数据）
	private String[] weekStr;

	private String[] weekPriceStr;//分段价格的星期
	private String[] startOpenTimeStr;		// 分段价格的开放预约起始时段
	private String[] endOpenTimeStr;		//分段价格的 开放预约结束时段
	private String[] basePriceStr;		// 分段价格的场地基础价格
	private List<FieldInfoPrice> fieldInfoPriceList = Lists.newArrayList();		// 子表列表
	private List<FieldPartitionPrice> fieldPartitionPriceList = Lists.newArrayList();		// 子表列表

	private FieldPartitionPrice partitionPrice;//用于条件查询


	private String stock;		// 用于条件查询,约满

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}
	public FieldInfo() {
		super();
	}

	public FieldInfo(String id){
		super(id);
	}

	@Length(min=1, max=64, message="商家id长度必须介于 1 和 64 之间")
	public String getBusinessInfoId() {
		return businessInfoId;
	}

	public void setBusinessInfoId(String businessInfoId) {
		this.businessInfoId = businessInfoId;
	}
	
	@Length(min=1, max=11, message="场地编号长度必须介于 1 和 11 之间")
	public String getSerialNumbers() {
		return serialNumbers;
	}

	public void setSerialNumbers(String serialNumbers) {
		this.serialNumbers = serialNumbers;
	}
	
	@Length(min=1, max=64, message="场地名称长度必须介于 1 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getWeekStr() {
		String[] wks=new String[7];
		if (monday!=null&&monday.equals("1")){
			wks[0]=("1");
		}
		if(tuesday!=null&&tuesday.equals("1")){
			wks[1]=("2");
		}
		if(wednesday!=null&&wednesday.equals("1")){
			wks[2]=("3");
		}
		if(thursday!=null&&thursday.equals("1")){
			wks[3]=("4");
		}
		if(friday!=null&&friday.equals("1")){
			wks[4]=("5");
		}
		if(saturday!=null&&saturday.equals("1")){
			wks[5]=("6");
		}
		if(sunday!=null&&sunday.equals("1")){
			wks[6]=("7");

		}
		return wks;
	}

	public void setWeekStr(String[] weekStr) {
		this.weekStr = weekStr;
	}

	@Length(min=0, max=1, message="星期一是否营业:0否1是长度必须介于 0 和 1 之间")
	public String getMonday() {
		return monday;
	}

	public void setMonday(String monday) {
		this.monday = monday;
	}
	
	@Length(min=0, max=1, message="星期二是否营业:0否1是长度必须介于 0 和 1 之间")
	public String getTuesday() {
		return tuesday;
	}

	public void setTuesday(String tuesday) {
		this.tuesday = tuesday;
	}
	
	@Length(min=0, max=1, message="星期三是否营业:0否1是长度必须介于 0 和 1 之间")
	public String getWednesday() {
		return wednesday;
	}

	public void setWednesday(String wednesday) {
		this.wednesday = wednesday;
	}
	
	@Length(min=0, max=1, message="星期四是否营业:0否1是长度必须介于 0 和 1 之间")
	public String getThursday() {
		return thursday;
	}

	public void setThursday(String thursday) {
		this.thursday = thursday;
	}
	
	@Length(min=0, max=1, message="星期五是否营业:0否1是长度必须介于 0 和 1 之间")
	public String getFriday() {
		return friday;
	}

	public void setFriday(String friday) {
		this.friday = friday;
	}
	
	@Length(min=0, max=1, message="星期六是否营业:0否1是长度必须介于 0 和 1 之间")
	public String getSaturday() {
		return saturday;
	}

	public void setSaturday(String saturday) {
		this.saturday = saturday;
	}
	
	@Length(min=0, max=1, message="星期日是否营业:0否1是长度必须介于 0 和 1 之间")
	public String getSunday() {
		return sunday;
	}

	public void setSunday(String sunday) {
		this.sunday = sunday;
	}
	
	@NotNull(message="场地最短预约时间不能为空")
	public Integer getShortTime() {
		return shortTime;
	}

	public void setShortTime(Integer shortTime) {
		this.shortTime = shortTime;
	}
	
	@Length(min=1, max=64, message="开放预约起始时段长度必须介于 1 和 64 之间")
	public String getStartOpenTime() {
		return startOpenTime;
	}

	public void setStartOpenTime(String startOpenTime) {
		this.startOpenTime = startOpenTime;
	}
	
	@Length(min=1, max=64, message="开放预约结束时段长度必须介于 1 和 64 之间")
	public String getEndOpenTime() {
		return endOpenTime;
	}

	public void setEndOpenTime(String endOpenTime) {
		this.endOpenTime = endOpenTime;
	}
	
	public Double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}
	
	@Length(min=0, max=1, message="是否分段预约:0否1是长度必须介于 0 和 1 之间")
	public String getIsChild() {
		return isChild;
	}

	public void setIsChild(String isChild) {
		this.isChild = isChild==null||isChild.equals("")?"0":isChild;
	}
	
	@Length(min=0, max=1, message="是否暂停 0否1是长度必须介于 0 和 1 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


	public String[] getWeekPriceStr() {
		return weekPriceStr;
	}

	public void setWeekPriceStr(String[] weekPriceStr) {
		this.weekPriceStr = weekPriceStr;
	}

	public String[] getStartOpenTimeStr() {
		return startOpenTimeStr;
	}

	public void setStartOpenTimeStr(String[] startOpenTimeStr) {
		this.startOpenTimeStr = startOpenTimeStr;
	}

	public String[] getEndOpenTimeStr() {
		return endOpenTimeStr;
	}

	public void setEndOpenTimeStr(String[] endOpenTimeStr) {
		this.endOpenTimeStr = endOpenTimeStr;
	}

	public String[] getBasePriceStr() {
		return basePriceStr;
	}

	public void setBasePriceStr(String[] basePriceStr) {
		this.basePriceStr = basePriceStr;
	}
	public List<FieldInfoPrice> getFieldInfoPriceList() {
		return fieldInfoPriceList;
	}

	public void setFieldInfoPriceList(List<FieldInfoPrice> fieldInfoPriceList) {
		this.fieldInfoPriceList = fieldInfoPriceList;
	}

	public List<FieldPartitionPrice> getFieldPartitionPriceList() {
		return fieldPartitionPriceList;
	}

	public void setFieldPartitionPriceList(List<FieldPartitionPrice> fieldPartitionPriceList) {
		this.fieldPartitionPriceList = fieldPartitionPriceList;
	}

	public FieldPartitionPrice getPartitionPrice() {
		return partitionPrice;
	}

	public void setPartitionPrice(FieldPartitionPrice partitionPrice) {
		this.partitionPrice = partitionPrice;
	}

	public String getCreateState() {
		return createState;
	}

	public void setCreateState(String createState) {
		this.createState = createState;
	}

	@Override
	public String toString() {
		return "FieldInfo [businessInfoId=" + businessInfoId
				+ ", serialNumbers=" + serialNumbers + ", name=" + name
				+ ", monday=" + monday + ", tuesday=" + tuesday
				+ ", wednesday=" + wednesday + ", thursday=" + thursday
				+ ", friday=" + friday + ", saturday=" + saturday + ", sunday="
				+ sunday + ", shortTime=" + shortTime + ", startOpenTime="
				+ startOpenTime + ", endOpenTime=" + endOpenTime
				+ ", basePrice=" + basePrice + ", isChild=" + isChild
				+ ", state=" + state + ", createState=" + createState
				+ ", weekStr=" + Arrays.toString(weekStr) + ", weekPriceStr="
				+ Arrays.toString(weekPriceStr) + ", startOpenTimeStr="
				+ Arrays.toString(startOpenTimeStr) + ", endOpenTimeStr="
				+ Arrays.toString(endOpenTimeStr) + ", basePriceStr="
				+ Arrays.toString(basePriceStr) + ", fieldInfoPriceList="
				+ fieldInfoPriceList + ", fieldPartitionPriceList="
				+ fieldPartitionPriceList + ", partitionPrice="
				+ partitionPrice + ", stock=" + stock + "]";
	}
	
}