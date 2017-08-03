/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.dao;

import java.util.List;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.order.entity.OrderGroupPurcList;

/**
 * 订单团购券清单表DAO接口
 * @author lq
 * @created on 2017年7月27日
 */
@MyBatisDao
public interface OrderGroupPurcListDao extends CrudDao<OrderGroupPurcList> {
	/**
	 * 优惠/验券管理（验券）： 依据团购券号查找订单号
	 * @param orderGroupPurcList
	 * @return
	 */
	public List<OrderGroupPurcList> getOrderListByNumber(OrderGroupPurcList orderGroupPurcList);
	
	/**
	 * 优惠/验券管理（验券）： 依据团购券清单表ID查找订单号
	 * @param orderGroupPurcList
	 * @return
	 */
	public OrderGroupPurcList getOrderInfoById(OrderGroupPurcList orderGroupPurcList);
	
	/**
	 * 优惠/验券管理：验券的团购券消费列表信息的查询 
	 * @param orderGroupPurcList
	 * @return
	 */
	public List<OrderGroupPurcList> getNumberList(OrderGroupPurcList orderGroupPurcList);
	
	/**
	 * 优惠/验券管理（验券）： 查找同订单号下面未消费的记录
	 * @param orderGroupPurcList
	 * @return
	 */
	public int countNotSpending(OrderGroupPurcList orderGroupPurcList);
	
	/**
	 * 优惠/验券管理：依据团购券号，更新团购券状态、消费时间
	 * @param orderGroupPurcList
	 * @return
	 */
	public int updateGroupNumber(OrderGroupPurcList orderGroupPurcList);
}