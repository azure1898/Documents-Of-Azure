/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.dao;

import java.util.Map;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.order.entity.OrderGoods;
import com.its.modules.order.entity.OrderGroupPurc;

/**
 * 订单-团购券DAO接口
 * @author lq
 * @created on 2017年7月27日
 */
@MyBatisDao
public interface OrderGroupPurcDao extends CrudDao<OrderGroupPurc> {
	
	/**
	 * 订单完成
	 * @param orderGoods 订单号
	 * @return
	 */
	public int complete(OrderGoods orderGoods);
	
	/**
	 * 订单接受
	 * @param orderGoods 订单号
	 * @return
	 */
	public int accept(OrderGoods orderGoods);
	
	/**
	 * 订单取消
	 * @param orderGoods 订单号
	 * @return
	 */
	public int cancel(OrderGoods orderGoods);

	/**
	 * 订单配送
	 * @param orderNo 订单号
	 * @return
	 */
	public int dispatching(OrderGoods orderGoods);

	/**
	 * 订单状态更新前检查
	 * @param paramer 检索条件
	 */
	public int check(Map<String,String> paramer);

	/**
	 * 本周商品订单金额
	 * @return
	 */
	double findAllListMoney();

	/**
	 * 本周商品订单数量
	 *
	 * @return
	 */
	int findAllListCount();

}