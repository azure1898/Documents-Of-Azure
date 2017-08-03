/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.adver.dao;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.adver.entity.AdverManage;
import com.its.modules.business.entity.BusinessInfo;
import com.its.modules.sys.entity.Role;

/**
 * 广告管理-发布管理DAO接口
 * 
 * @author zhujiao
 * @version 2017-07-04
 */
@MyBatisDao
public interface AdverManageDao extends CrudDao<AdverManage> {
    /**
     * 根据广告名称获取对象
     * 
     * @author zhujiao
     * @date 2017年7月4日 下午5:55:24
     * @return AdverManage
     */
    public AdverManage getModelByName(AdverManage adverManage);

    /**
     * 插入投放楼盘关联数据
     * 
     * @author zhujiao
     * @date 2017年7月4日 下午6:09:22
     * @return int
     */
    public int insertAdverBuilding(AdverManage adverManage);

    /**
     * 删除投放楼盘关联数据
     * 
     * @author zhujiao
     * @date 2017年7月4日 下午6:13:03
     * @return int
     */
    public int deleteAdverBuilding(AdverManage adverManage);

}