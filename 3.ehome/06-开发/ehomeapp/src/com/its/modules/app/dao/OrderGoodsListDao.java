package com.its.modules.app.dao;

import java.util.List;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;

import com.its.modules.app.entity.OrderGoodsList;

/**
 * 订单商品明细表DAO接口
 * 
 * @author like
 * 
 * @version 2017-07-10
 */
@MyBatisDao
public interface OrderGoodsListDao extends CrudDao<OrderGoodsList> {

	/**
	 * 获取某商品订单的订单清单
	 * 
	 * @param orderGoodsId
	 *            订单ID
	 * @return List<OrderGoodsList>
	 */
	public List<OrderGoodsList> getOrderGoodsLists(String orderGoodsId);
}