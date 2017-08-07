/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.module.dao;

import java.util.List;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.module.entity.VillageLinerecombusitypedetail;
import com.its.modules.module.entity.VillageLinerecomspecialdetail;

/**
 * 楼盘产品线推荐商家模式DAO接口
 * 
 * @author zhujiao
 * @version 2017-07-27
 */
@MyBatisDao
public interface VillageLinerecombusitypedetailDao extends CrudDao<VillageLinerecombusitypedetail> {

    /**
     * 通过商家推荐的ID获取商家推荐分类明细List
     * @param businessInfoId
     * @return
     * @return List<VillageLinerecombusitypedetail>
     * @author zhujiao   
     * @date 2017年8月4日 上午9:39:44
     */
    public List<VillageLinerecombusitypedetail> getRecomBusTypeDetailList(String businessInfoId);

    /**
     * 通过商家推荐的ID删除商家推荐分类明细 
     * @param businessInfoId
     * @return void
     * @author zhujiao   
     * @date 2017年8月4日 上午9:40:14
     */
    public void deleteByBusId(String businessInfoId);

}