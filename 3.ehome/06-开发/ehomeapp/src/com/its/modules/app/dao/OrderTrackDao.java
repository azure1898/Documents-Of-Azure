package com.its.modules.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.app.bean.MyOrderViewBean;
import com.its.modules.app.bean.OrderTrackViewBean;
import com.its.modules.app.entity.OrderTrack;

/**
 * 订单跟踪DAO接口
 * 
 * @author sushipeng
 * 
 * @version 2017-07-19
 */
@MyBatisDao
public interface OrderTrackDao extends CrudDao<OrderTrack> {

	/**
	 * 获取某用户某楼盘某模块下的订单
	 * 
	 * @param villageInfoId
	 *            楼盘ID
	 * @param accountId
	 *            用户ID
	 * @param moduleManageId
	 *            模块ID：0全部订单 -1精品团购 else产品模式下的模块
	 * @return List<MyOrderViewBean>
	 */
	public List<MyOrderViewBean> getMyOrderViewList(@Param("villageInfoId") String villageInfoId, @Param("accountId") String accountId, @Param("moduleManageId") String moduleManageId);

	/**
	 * 获取某用户某楼盘下的最新两条订单
	 * 
	 * @param villageInfoId
	 *            楼盘ID
	 * @param accountId
	 *            用户ID
	 * @return List<MyOrderViewBean>
	 */
	public List<MyOrderViewBean> getRecentMyOrderView(@Param("villageInfoId") String villageInfoId, @Param("accountId") String accountId);

	/**
	 * 获取某订单最新的状态信息
	 * 
	 * @param orderId
	 *            订单ID
	 * @return OrderTrack
	 */
	public OrderTrack getRecentOrderStatus(String orderId);

	/**
	 * 获取某订单的订单追踪信息
	 * 
	 * @param orderId
	 *            订单ID
	 * @return OrderTrackViewBean
	 */
	public OrderTrackViewBean getOrderTrackViewBean(String orderId);
}