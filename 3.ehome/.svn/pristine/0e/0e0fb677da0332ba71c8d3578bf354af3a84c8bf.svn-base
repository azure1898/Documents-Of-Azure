/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.business.dao;

import java.util.List;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.business.entity.BusinessCategorydict;

/**
 * 商户分类DAO接口
 * @author ChenXiangyu
 * @version 2017-06-23
 */
@MyBatisDao
public interface BusinessCategorydictDao extends CrudDao<BusinessCategorydict> {
    public List<BusinessCategorydict> getBusinessCategoryByCategoryName(String categoryname);
    /**
     * 通过商家ID获取分类列表 （设置推荐中用到）
     * @param businessId
     * @return
     * @return List<BusinessCategorydict>
     * @author zhujiao   
     * @date 2017年8月3日 下午7:43:41
     */
    public List<BusinessCategorydict> getListBybusId(String businessinfoId);
}