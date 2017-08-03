/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.business.entity;

import org.hibernate.validator.constraints.Length;

import com.its.common.persistence.DataEntity;

/**
 * 商家服务时间信息管理Entity
 * @author zhujiao
 * @version 2017-06-29
 */
public class BusinessServicetime extends DataEntity<BusinessServicetime> {
    
    private static final long serialVersionUID = 1L;
    private String businessinfoId;        // 商户基本信息ID
    private String timetype;        // 时段类型：0上门服务时段  1到店服务时段  2上门配送时段
    private String serialNumber;        // 序号
    private String beginHour;        // 开始小时
    private String beginMinute;        // 开始分钟
    private String endHour;        // 结束小时
    private String endMinute;        // 结束分钟
    
    public BusinessServicetime() {
        super();
    }

    public BusinessServicetime(String id){
        super(id);
    }

    @Length(min=0, max=64, message="商户基本信息ID长度必须介于 0 和 64 之间")
    public String getBusinessinfoId() {
        return businessinfoId;
    }

    public void setBusinessinfoId(String businessinfoId) {
        this.businessinfoId = businessinfoId;
    }
    
    @Length(min=0, max=1, message="时段类型：0上门服务时段  1到店服务时段  2上门配送时段长度必须介于 0 和 1 之间")
    public String getTimetype() {
        return timetype;
    }

    public void setTimetype(String timetype) {
        this.timetype = timetype;
    }
    
    @Length(min=0, max=11, message="序号长度必须介于 0 和 11 之间")
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    
    @Length(min=0, max=2, message="开始小时长度必须介于 0 和 2 之间")
    public String getBeginHour() {
        return beginHour;
    }

    public void setBeginHour(String beginHour) {
        this.beginHour = beginHour;
    }
    
    @Length(min=0, max=2, message="开始分钟长度必须介于 0 和 2 之间")
    public String getBeginMinute() {
        return beginMinute;
    }

    public void setBeginMinute(String beginMinute) {
        this.beginMinute = beginMinute;
    }
    
    @Length(min=0, max=2, message="结束小时长度必须介于 0 和 2 之间")
    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }
    
    @Length(min=0, max=2, message="结束分钟长度必须介于 0 和 2 之间")
    public String getEndMinute() {
        return endMinute;
    }

    public void setEndMinute(String endMinute) {
        this.endMinute = endMinute;
    }
    
}