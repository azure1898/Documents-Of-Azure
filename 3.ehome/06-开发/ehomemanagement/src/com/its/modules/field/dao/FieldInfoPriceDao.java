/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.field.dao;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.field.entity.FieldInfoPrice;

/**
 * 场地预约子表_分段编辑临时表DAO接口
 * @author xzc
 * @version 2017-07-03
 */
@MyBatisDao
public interface FieldInfoPriceDao extends CrudDao<FieldInfoPrice> {

    /**
     * 删除所有分段价格
     * @param fieldInfoPrice
     */
    void deleteAll(FieldInfoPrice fieldInfoPrice);
}