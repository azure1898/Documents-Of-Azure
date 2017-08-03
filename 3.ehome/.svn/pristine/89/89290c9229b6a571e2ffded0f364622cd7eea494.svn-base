/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.adver.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.adver.entity.AdverPosition;

/**
 * 位置管理DAO接口
 * @author ChenXiangyu
 * @version 2017-07-03
 */
@MyBatisDao
public interface AdverPositionDao extends CrudDao<AdverPosition> {

    public Integer getOpenScreenOfModuleCount(AdverPosition adverPosition);

    /**
     * 根据位置名称查找对应产品线的广告位置记录
     * @param positionName 位置名称
     * @param moduleCode 产品线
     * @return 广告位置记录
     */
	public List<AdverPosition> getPositionByPositionName(@Param("positionName")String positionName, @Param("moduleCode")String moduleCode);
    
}