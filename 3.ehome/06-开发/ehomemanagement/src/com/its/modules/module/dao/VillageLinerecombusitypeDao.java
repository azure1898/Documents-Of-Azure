/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.module.dao;

import java.util.List;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.module.entity.VillageLinerecombusitype;
import com.its.modules.module.entity.VillageLinerecomspecial;

/**
 * 楼盘产品线推荐商家模式DAO接口
 * 
 * @author zhujiao
 * @version 2017-07-27
 */
@MyBatisDao
public interface VillageLinerecombusitypeDao extends CrudDao<VillageLinerecombusitype> {

    /**
     * 通过楼盘线获取商家推荐列表
     * 
     * @param villageLineId
     * @return
     * @return List<VillageLinerecombusitype>
     * @author zhujiao
     * @date 2017年8月4日 上午9:42:43
     */
    public List<VillageLinerecombusitype> getRecomBusTypeList(String villageLineId);

    /**
     * 通过楼盘线删除商家推荐模块数据
     * 
     * @param villageLineId
     * @return void
     * @author zhujiao
     * @date 2017年8月4日 上午9:43:03
     */
    public void deleteByLine(String villageLineId);

}