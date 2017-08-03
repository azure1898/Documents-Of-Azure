/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.setup.entity;

import org.hibernate.validator.constraints.Length;

import com.its.common.persistence.DataEntity;

/**
 * 商家促销Entity
 * @author like
 * @version 2017-07-17
 */
public class BusinessSales extends DataEntity<BusinessSales> {
    
    private static final long serialVersionUID = 1L;
    private String businessInfoId;        // 商家id
    private Double money;        // 达标额度
    private Double benefitMoney;        // 优惠额度
    
    public BusinessSales() {
        super();
    }

    public BusinessSales(String id){
        super(id);
    }

    @Length(min=1, max=64, message="商家id长度必须介于 1 和 64 之间")
    public String getBusinessInfoId() {
        return businessInfoId;
    }

    public void setBusinessInfoId(String businessInfoId) {
        this.businessInfoId = businessInfoId;
    }
    
    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
    
    public Double getBenefitMoney() {
        return benefitMoney;
    }

    public void setBenefitMoney(Double benefitMoney) {
        this.benefitMoney = benefitMoney;
    }
    
}