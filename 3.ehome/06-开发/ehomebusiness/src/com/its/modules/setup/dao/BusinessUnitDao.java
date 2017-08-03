/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.setup.dao;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.setup.entity.BusinessUnit;

/**
 * 商家单位信息DAO接口
 * @author liuhl
 * @version 2017-07-12
 */
@MyBatisDao
public interface BusinessUnitDao extends CrudDao<BusinessUnit> {
    
}