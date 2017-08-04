package com.its.modules.app.dao;

import org.apache.ibatis.annotations.Param;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;

import com.its.modules.app.bean.OrderFieldBean;
import com.its.modules.app.entity.OrderField;

/**
 * 场地订单DAO接口
 * 
 * @author like
 * 
 * @version 2017-07-12
 */
@MyBatisDao
public interface OrderFieldDao extends CrudDao<OrderField> {

	/**
	 * 根据订单ID和用户ID获取订单信息
	 * 
	 * @param orderId
	 *            订单ID
	 * @param accountId
	 *            用户ID
	 * @return OrderFieldBean
	 */
	public OrderFieldBean getOrderFieldByOrderIdAndAccountId(@Param("orderId") String orderId, @Param("accountId") String accountId);

	/**
	 * 判断某用户是否可以取消某订单
	 * 
	 * @param orderId
	 *            订单ID
	 * @param accountId
	 *            用户ID
	 * @return OrderField
	 */
	public OrderField judgeOrderFieldByOrderIdAndAccountId(@Param("orderId") String orderId, @Param("accountId") String accountId);
}