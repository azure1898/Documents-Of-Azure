package com.its.modules.app.dao;

import org.apache.ibatis.annotations.Param;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.app.bean.OrderGoodsBean;
import com.its.modules.app.entity.OrderGoods;

/**
 * 订单-商品类DAO接口
 * 
 * @author like
 * 
 * @version 2017-07-10
 */
@MyBatisDao
public interface OrderGoodsDao extends CrudDao<OrderGoods> {
	/**
	 * 根据订单号获取订单信息
	 * 
	 * @param orderNo
	 *            订单号
	 * @return OrderGoods
	 */
	public OrderGoods getByOrderNo(String orderNo);

	/**
	 * 根据订单ID和用户ID获取订单信息
	 * 
	 * @param orderId
	 *            订单ID
	 * @param accountId
	 *            用户ID
	 * @return OrderGoodsBean
	 */
	public OrderGoodsBean getOrderGoodsByOrderIdAndAccountId(@Param("orderId") String orderId, @Param("accountId") String accountId);

	/**
	 * 判断某用户是否可以取消某订单
	 * 
	 * @param orderId
	 *            订单ID
	 * @param accountId
	 *            用户ID
	 * @return OrderGoods
	 */
	public OrderGoods judgeOrderGoodsCancelAble(@Param("orderId") String orderId, @Param("accountId") String accountId);
}