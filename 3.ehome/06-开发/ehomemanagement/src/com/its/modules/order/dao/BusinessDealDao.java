/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.dao;

import java.util.Map;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.order.entity.BusinessDeal;
import com.its.modules.order.entity.OrderGoods;

/**
 * 商户交易DAO接口
 * @author lq
 */
@MyBatisDao
public interface BusinessDealDao extends CrudDao<BusinessDeal> {

	Map<String, Object> getTotal(BusinessDeal OrderBusinessDeal);
	
	OrderGoods getAll(String id);
}