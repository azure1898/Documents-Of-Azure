/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.dao;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.order.entity.OrderRefundInfo;

/**
 * 退款信息DAO接口
 * @author liuhl
 * @version 2017-07-13
 */
@MyBatisDao
public interface OrderRefundInfoDao extends CrudDao<OrderRefundInfo> {

	/**
	 * 根据订单号查询退款信息
	 * 
	 * @param orderRefundInfo 退款信息查询条件
	 * 
	 * @return 退款信息
	 */
	OrderRefundInfo findOrderRefundInfoByOrderNo(OrderRefundInfo orderRefundInfo);
	
}