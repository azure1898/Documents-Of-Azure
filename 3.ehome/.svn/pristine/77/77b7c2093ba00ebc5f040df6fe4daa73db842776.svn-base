/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.business.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.its.common.persistence.DataEntity;

/**
 * 商户分类Entity
 * 
 * @author ChenXiangyu
 * @version 2017-06-23
 */
public class BusinessCategorydict extends DataEntity<BusinessCategorydict> {

    private static final long serialVersionUID = 1L;
    private String categoryName; // 分类名称
    private String prodType; // 产品模式
    private String categoryIntroduce; // 分类介绍

    private BusinessInfo businessInfo;// 根据商家查询分类列表

    public BusinessCategorydict() {
        super();
    }

    public BusinessCategorydict(String id) {
        super(id);
    }

    public BusinessCategorydict(BusinessInfo businessInfo) {
        this();
        this.businessInfo = businessInfo;
    }

    public BusinessInfo getBusinessInfo() {
        return businessInfo;
    }

    public void setBusinessInfo(BusinessInfo businessInfo) {
        this.businessInfo = businessInfo;
    }

    @NotBlank(message = "请输入分类名称")
    @Length(min = 0, max = 32, message = "分类名称长度必须介于 0 和 32 之间")
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @NotBlank(message = "请选择产品模式")
    public String getProdType() {
        return prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    @Length(min = 0, max = 2000, message = "分类介绍长度必须介于 0 和 2000 之间")
    public String getCategoryIntroduce() {
        return categoryIntroduce;
    }

    public void setCategoryIntroduce(String categoryIntroduce) {
        this.categoryIntroduce = categoryIntroduce;
    }

}