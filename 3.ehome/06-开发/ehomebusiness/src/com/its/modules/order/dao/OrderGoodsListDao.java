/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.dao;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.order.entity.OrderGoodsList;

/**
 * 订单商品明细表DAO接口
 * @author liuhl
 * @version 2017-07-12
 */
@MyBatisDao
public interface OrderGoodsListDao extends CrudDao<OrderGoodsList> {
	
}