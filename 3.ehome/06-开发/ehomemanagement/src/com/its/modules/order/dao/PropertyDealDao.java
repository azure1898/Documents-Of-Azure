/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.dao;

import java.util.Map;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.order.entity.PropertyDeal;

/**
 * 商户交易DAO接口
 * @author lq
 */
@MyBatisDao
public interface PropertyDealDao extends CrudDao<PropertyDeal> {

	Map<String, Object> getTotal(PropertyDeal OrderPropertyDeal);
	
	PropertyDeal getAll(String id);
}