package com.its.modules.app.dao;

import java.util.List;

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
	 * @return
	 */
	public OrderGoods getByOrderNo(String orderNo);

	/**
	 * 
	 * 获取某用户某楼盘下的商品类订单
	 * 
	 * @param villageInfoId
	 *            楼盘ID
	 * @param accountId
	 *            用户ID
	 * @param moduleManageId
	 *            模块ID
	 * @return List<OrderGoodsBean>
	 */
	public List<OrderGoodsBean> getOrderGoodsList(@Param("villageInfoId") String villageInfoId, @Param("accountId") String accountId, @Param("moduleManageId") String moduleManageId);

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
	 * @return OrderGoodsBean
	 */
	public OrderGoodsBean judgeOrderGoodsByOrderIdAndAccountId(@Param("orderId") String orderId, @Param("accountId") String accountId);

	/**
	 * 更新商品订单状态
	 * 
	 * @param orderGoodsBean
	 *            商品订单信息
	 */
	public void updateState(OrderGoodsBean orderGoodsBean);
}