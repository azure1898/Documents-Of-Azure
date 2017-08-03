/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.module.dao;

import java.util.List;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.module.entity.VillageLinerecomspecial;

/**
 * 楼盘产品线专题推荐DAO接口
 * 
 * @author zhujiao
 * @version 2017-07-27
 */
@MyBatisDao
public interface VillageLinerecomspecialDao extends CrudDao<VillageLinerecomspecial> {
    /**
     * 通过楼盘线获取专题推荐列表
     * 
     * @param villageLineId
     * @return
     * @return List<VillageLinerecomspecial>
     * @author zhujiao
     * @date 2017年7月31日 下午2:42:59
     */
    public List<VillageLinerecomspecial> getRecomSpecialList(String villageLineId);

    /**
     * 通过楼盘线删除专题推荐模块数据
     * 
     * @param villageLineId
     * @return void
     * @author zhujiao
     * @date 2017年7月31日 下午2:43:30
     */
    public void deleteByLine(String villageLineId);
}