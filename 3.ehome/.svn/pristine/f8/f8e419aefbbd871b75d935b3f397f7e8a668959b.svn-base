/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.setup.entity;

import org.hibernate.validator.constraints.Length;

import com.its.common.persistence.DataEntity;

/**
 * 商家单位信息Entity
 * @author liuhl
 * @version 2017-07-12
 */
public class BusinessUnit extends DataEntity<BusinessUnit> {
    
    private static final long serialVersionUID = 1L;
    private String businessInfoId;        // 商家id
    private String name;        // 单位名称
    
    public BusinessUnit() {
        super();
    }

    public BusinessUnit(String id){
        super(id);
    }

    @Length(min=1, max=64, message="商家id长度必须介于 1 和 64 之间")
    public String getBusinessInfoId() {
        return businessInfoId;
    }

    public void setBusinessInfoId(String businessInfoId) {
        this.businessInfoId = businessInfoId;
    }
    
    @Length(min=0, max=64, message="单位名称长度必须介于 0 和 64 之间")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}