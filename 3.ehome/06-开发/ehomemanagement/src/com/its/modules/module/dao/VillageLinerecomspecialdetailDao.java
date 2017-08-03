/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.module.dao;

import java.util.List;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.module.entity.VillageLinerecomspecialdetail;

/**
 * 楼盘产品线专题推荐明细DAO接口
 * @author zhujiao
 * @version 2017-07-27
 */
@MyBatisDao
public interface VillageLinerecomspecialdetailDao extends CrudDao<VillageLinerecomspecialdetail> {
    /**
     * 通过专题推荐的ID获取专题推荐明细List
     * @param villageLineId
     * @return
     * @return List<VillageLinerecommodule>
     * @author zhujiao   
     * @date 2017年7月27日 上午11:08:18
     */
    public List<VillageLinerecomspecialdetail> getRecomSpecialDetailList(String specialId);
    /**
     * 通过专题推荐的ID删除专题推荐明细
     * @param villageLineId
     * @return
     * @return List<VillageLinerecommodule>
     * @author zhujiao   
     * @date 2017年7月27日 上午11:08:18
     */
    public void deleteBySpecialId(String specialId);
}