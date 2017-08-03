/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.module.entity;

import org.hibernate.validator.constraints.Length;

import com.its.common.persistence.DataEntity;

/**
 * 楼盘产品线推荐模块或商家Entity
 * 
 * @author zhujiao
 * @version 2017-07-27
 */
public class VillageLinerecommodule extends DataEntity<VillageLinerecommodule> {

    private static final long serialVersionUID = 1L;
    private String villageLineId; // 楼盘产品线ID
    private String recomPosition; // 推荐位置：0首页推荐 1优家推荐
                                  // 20生活商家推荐1
    private String sortNum; // 排序序号
    private String recomType; // 类型：0商家 1模块
    private String recomModuleId; // 推荐模块ID
    private String recomBusinessId; // 推荐商家ID
    private String describes; // 描述
    private String picUrl; // 图片

    public VillageLinerecommodule() {
        super();
    }

    public VillageLinerecommodule(String id) {
        super(id);
    }

    @Length(min = 0, max = 64, message = "楼盘产品线ID长度必须介于 0 和 64 之间")
    public String getVillageLineId() {
        return villageLineId;
    }

    public void setVillageLineId(String villageLineId) {
        this.villageLineId = villageLineId;
    }

    @Length(min = 0, max = 2, message = "推荐位置：00首页推荐商家   10社区模块推荐2  11社区更多服务推荐  20生活商家推荐1长度必须介于 0 和 2 之间")
    public String getRecomPosition() {
        return recomPosition;
    }

    public void setRecomPosition(String recomPosition) {
        this.recomPosition = recomPosition;
    }

    @Length(min = 0, max = 11, message = "排序序号长度必须介于 0 和 11 之间")
    public String getSortNum() {
        return sortNum;
    }

    public void setSortNum(String sortNum) {
        this.sortNum = sortNum;
    }

    @Length(min = 0, max = 1, message = "类型：0商家  1模块长度必须介于 0 和 1 之间")
    public String getRecomType() {
        return recomType;
    }

    public void setRecomType(String recomType) {
        this.recomType = recomType;
    }

    @Length(min = 0, max = 64, message = "推荐模块ID长度必须介于 0 和 64 之间")
    public String getRecomModuleId() {
        return recomModuleId;
    }

    public void setRecomModuleId(String recomModuleId) {
        this.recomModuleId = recomModuleId;
    }

    @Length(min = 0, max = 64, message = "推荐商家ID长度必须介于 0 和 64 之间")
    public String getRecomBusinessId() {
        return recomBusinessId;
    }

    public void setRecomBusinessId(String recomBusinessId) {
        this.recomBusinessId = recomBusinessId;
    }

    @Length(min = 0, max = 64, message = "描述长度必须介于 0 和 64 之间")
    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    @Length(min = 0, max = 256, message = "图片长度必须介于 0 和 256 之间")
    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

}