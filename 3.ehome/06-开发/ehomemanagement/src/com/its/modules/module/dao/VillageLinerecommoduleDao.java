/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.module.dao;

import java.util.List;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.module.entity.VillageLinerecommodule;

/**
 * 楼盘产品线推荐模块或商家DAO接口
 * 
 * @author zhujiao
 * @version 2017-07-27
 */
@MyBatisDao
public interface VillageLinerecommoduleDao extends CrudDao<VillageLinerecommodule> {
    /**
     * 通过楼盘线获取推荐模块的List
     * @param villageLineId
     * @return
     * @return List<VillageLinerecommodule>
     * @author zhujiao   
     * @date 2017年7月27日 上午11:08:18
     */
    public List<VillageLinerecommodule> getRecomModuleList(String villageLineId);
    /**
     * 通过楼盘线Id删除推荐模块
     * @param villageLineId
     * @return
     * @return List<VillageLinerecommodule>
     * @author zhujiao   
     * @date 2017年7月27日 上午11:08:18
     */
    public void deleteByLine(String villageLineId);
}