/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.village.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import com.its.common.persistence.DataEntity;
import com.its.common.utils.StringUtils;
import com.its.modules.module.entity.VillageLinerecombusitype;
import com.its.modules.module.entity.VillageLinerecombusitypedetail;
import com.its.modules.module.entity.VillageLinerecommodule;
import com.its.modules.module.entity.VillageLinerecomspecial;

/**
 * 楼盘信息产品线Entity
 * 
 * @author ChenXiangyu
 * @version 2017-07-11
 */
public class VillageLine extends DataEntity<VillageLine> {

    private static final long serialVersionUID = 1L;
    private String villageInfoId; // 楼盘ID
    private String productLine; // 产品线
    private String setState; // 设置状态
    private Date setTime; // 设置时间
    private String mainNavigation; // 主导航
    private String communityModule; // 社区模块
    private String lifeModule; // 生活模块
    private String maintRecomModule; // 首页推荐模块
    private String communityRecomModule; // 社区推荐模块
    private String lifeRecomModule; // 生活推荐模块
    private VillageInfo villageInfo;// 楼盘信息对象

    private String[] villageIdList;// 楼盘Id的集合 批量设置模块时使用 add by zhujiao

    private VillageLinerecombusitype recomBusType;// 楼盘产品线推荐商家模式
    private List<VillageLinerecombusitype> recomBusTypeList = Lists.newArrayList();// 楼盘产品线推荐商家模式列表

    private VillageLinerecommodule recomModule;// 楼盘产品线推荐模块或者商家
    private List<VillageLinerecommodule> recomModuleList = Lists.newArrayList();// 楼盘产品线推荐模块或者商家列表

    private VillageLinerecomspecial recomSpecial;// 楼盘产品线推荐专题
    private List<VillageLinerecomspecial> recomSpecialList = Lists.newArrayList();// 楼盘产品线推荐专题列表

    public VillageLine() {
        super();
    }

    public VillageLine(String id) {
        super(id);
    }

    @Length(min = 0, max = 64, message = "楼盘ID长度必须介于 0 和 64 之间")
    public String getVillageInfoId() {
        return villageInfoId;
    }

    public void setVillageInfoId(String villageInfoId) {
        this.villageInfoId = villageInfoId;
    }

    @Length(min = 0, max = 1, message = "产品线长度必须介于 0 和 1 之间")
    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    @Length(min = 0, max = 1, message = "设置状态长度必须介于 0 和 1 之间")
    public String getSetState() {
        return setState;
    }

    public void setSetState(String setState) {
        this.setState = setState;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getSetTime() {
        return setTime;
    }

    public void setSetTime(Date setTime) {
        this.setTime = setTime;
    }

    @Length(min = 0, max = 254, message = "主导航长度必须介于 0 和 254 之间")
    public String getMainNavigation() {
        return mainNavigation;
    }

    public void setMainNavigation(String mainNavigation) {
        this.mainNavigation = mainNavigation;
    }

    public String getCommunityModule() {
        return communityModule;
    }

    public void setCommunityModule(String communityModule) {
        this.communityModule = communityModule;
    }

    public String getLifeModule() {
        return lifeModule;
    }

    public void setLifeModule(String lifeModule) {
        this.lifeModule = lifeModule;
    }

    @Length(min = 0, max = 6400, message = "首页推荐模块长度必须介于 0 和 6400 之间")
    public String getMaintRecomModule() {
        return maintRecomModule;
    }

    public void setMaintRecomModule(String maintRecomModule) {
        this.maintRecomModule = maintRecomModule;
    }

    @Length(min = 0, max = 6400, message = "社区推荐模块长度必须介于 0 和 6400 之间")
    public String getCommunityRecomModule() {
        return communityRecomModule;
    }

    public void setCommunityRecomModule(String communityRecomModule) {
        this.communityRecomModule = communityRecomModule;
    }

    @Length(min = 0, max = 6400, message = "生活推荐模块长度必须介于 0 和 6400 之间")
    public String getLifeRecomModule() {
        return lifeRecomModule;
    }

    public void setLifeRecomModule(String lifeRecomModule) {
        this.lifeRecomModule = lifeRecomModule;
    }

    public VillageInfo getVillageInfo() {
        return villageInfo;
    }

    public void setVillageInfo(VillageInfo villageInfo) {
        this.villageInfo = villageInfo;
    }

    public String[] getVillageIdList() {
        return villageIdList;
    }

    public void setVillageIdList(String[] villageIdList) {
        this.villageIdList = villageIdList;
    }

    // # add by zhujiao ---start
    // 主导航模块集合
    public String[] getMainNavigationIds() {
        if (mainNavigation != null) {
            return mainNavigation.split(",");
        } else {
            return null;
        }
    }

    public void setMainNavigationIds(String[] mainNavigationIds) {
        this.mainNavigation = StringUtils.arrToStr(mainNavigationIds);
    }

    // 社区模块数组集合
    public String[] getCommunityModuleIds() {
        if (communityModule != null) {
            return communityModule.split(",");
        } else {
            return null;
        }
    }

    /*
     * public void setCommunityModuleIds(String[] communityModuleIds) {
     * this.communityModule = StringUtils.arrToStr(communityModuleIds); }
     */

    // 生活模块数组集合
    public String[] getLifeModuleIds() {
        if (lifeModule != null) {
            return lifeModule.split(",");
        } else {
            return null;
        }
    }

    /*
     * public void setLifeModuleIds(String[] lifeModuleIds) { this.lifeModule =
     * StringUtils.arrToStr(lifeModuleIds); }
     */

    // 首页推荐模块集合
    public String[] getMaintRecomModuleIds() {
        if (maintRecomModule != null) {
            return maintRecomModule.split(",");
        } else {
            return null;
        }
    }

    /*
     * public void setMaintRecomModuleIds(String[] maintRecomModuleIds) {
     * this.maintRecomModule = StringUtils.arrToStr(maintRecomModuleIds); }
     */

    // 优家推荐模块集合
    public String[] getLifeRecomModuleIds() {
        if (lifeRecomModule != null) {
            return lifeRecomModule.split(",");
        } else {
            return null;
        }
    }

    /*
     * public void setLifeRecomModuleIds(String[] lifeRecomModuleIds) {
     * this.lifeRecomModule = StringUtils.arrToStr(lifeRecomModuleIds); }
     */
    // 社区推荐模块集合
    public String[] getCommunityRecomModuleIds() {
        if (communityRecomModule != null) {
            return communityRecomModule.split(",");
        } else {
            return null;
        }
    }

    /*
     * public void setCommunityRecomModuleIds(String[] communityRecomModuleIds)
     * { this.communityRecomModule =
     * StringUtils.arrToStr(communityRecomModuleIds); }
     */
    // # add by zhujiao ---end

    public VillageLinerecombusitype getRecomBusType() {
        return recomBusType;
    }

    public void setRecomBusType(VillageLinerecombusitype recomBusType) {
        this.recomBusType = recomBusType;
    }

    public VillageLinerecommodule getRecomModule() {
        return recomModule;
    }

    public void setRecomModule(VillageLinerecommodule recomModule) {
        this.recomModule = recomModule;
    }

    public VillageLinerecomspecial getRecomSpecial() {
        return recomSpecial;
    }

    public void setRecomSpecial(VillageLinerecomspecial recomSpecial) {
        this.recomSpecial = recomSpecial;
    }

    public List<VillageLinerecombusitype> getRecomBusTypeList() {
        return recomBusTypeList;
    }

    public void setRecomBusTypeList(List<VillageLinerecombusitype> recomBusTypeList) {
        this.recomBusTypeList = recomBusTypeList;
    }

    public List<VillageLinerecommodule> getRecomModuleList() {
        return recomModuleList;
    }

    public void setRecomModuleList(List<VillageLinerecommodule> recomModuleList) {
        this.recomModuleList = recomModuleList;
    }

    public List<VillageLinerecomspecial> getRecomSpecialList() {
        return recomSpecialList;
    }

    public void setRecomSpecialList(List<VillageLinerecomspecial> recomSpecialList) {
        this.recomSpecialList = recomSpecialList;
    }

}