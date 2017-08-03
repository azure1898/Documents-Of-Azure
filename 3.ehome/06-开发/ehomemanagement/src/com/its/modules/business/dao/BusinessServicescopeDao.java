/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.business.dao;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.business.entity.BusinessServicescope;

/**
 * 商户服务范围DAO接口
 * @author liuqi
 * @version 2017-07-04
 */
@MyBatisDao
public interface BusinessServicescopeDao extends CrudDao<BusinessServicescope> {
    
}