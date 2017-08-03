/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.setup.dao;

import java.util.List;
import java.util.Map;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.setup.entity.BusinessCategorydict;

/**
 * 商户分类DAO接口
 * @author ChenXiangyu
 * @version 2017-06-23
 */
@MyBatisDao
public interface BusinessCategorydictDao extends CrudDao<BusinessCategorydict> {
    public List<BusinessCategorydict> getBusinessCategoryByCategoryName(String categoryname);

    /**
     * 根据商家ID检索出对应的产品模式
     * 
     * @param paramer 检索条件
     * @author Liuhl
     * @return 商户分类Entity
     */
    public List<BusinessCategorydict> findCategoryListByBusinessId(Map<String, String> paramer);
}