/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.dao;

import java.util.Map;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.order.entity.OrderGoods;
import com.its.modules.order.entity.OrderService;

/**
 * 订单-服务类DAO接口
 * @author liuhl
 * @version 2017-07-17
 */
@MyBatisDao
public interface OrderServiceDao extends CrudDao<OrderService> {

	/**
	 * 订单完成
	 * @param OrderService 订单号
	 * @return
	 */
	public int complete(OrderService orderService);
	
	/**
	 * 订单接受
	 * @param OrderService 订单号
	 * @return
	 */
	public int accept(OrderService orderService);
	
	/**
	 * 订单取消
	 * @param OrderService 订单号
	 * @return
	 */
	public int cancel(OrderService orderService);
	
	/**
	 * 订单状态更新前检查
	 * @param paramer 检索条件
	 */
	int check(Map<String, String> paramer);

	/**
	 * 查询本周服务订单个数
	 * @return
	 */
    int findAllListCount();

	/**
	 * 查询本周服务订单总金额
	 * @return
	 */
	double findAllListMoney();

}