package com.its.modules.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.app.bean.OrderGroupPurcBean;
import com.its.modules.app.entity.OrderGroupPurc;

/**
 * 订单-团购类DAO接口
 * 
 * @author sushipeng
 * 
 * @version 2017-07-13
 */
@MyBatisDao
public interface OrderGroupPurcDao extends CrudDao<OrderGroupPurc> {

	/**
	 * 获取某精品团购用户已购数量
	 * 
	 * @param accountId
	 *            用户ID
	 * @param groupPurchaseId
	 *            团购ID
	 * @return 某精品团购用户已购数量
	 */
	public int getCountByGroupPurcIdAndAccountId(@Param("accountId") String accountId, @Param("groupPurchaseId") String groupPurchaseId);

	/**
	 * 根据订单号获取团购券订单
	 * 
	 * @param orderNo
	 *            订单号
	 * @return OrderGroupPurc
	 */
	public OrderGroupPurc getByOrderNo(String orderNo);

	/**
	 * 获取某用户某楼盘下的团购类订单
	 * 
	 * @param villageInfoId
	 *            楼盘ID
	 * @param accountId
	 *            用户ID
	 * @param moduleManageId
	 *            模块ID
	 * @return List<OrderFieldBean>
	 */
	public List<OrderGroupPurcBean> getOrderGroupPurcList(@Param("villageInfoId") String villageInfoId, @Param("accountId") String accountId, @Param("moduleManageId") String moduleManageId);

	/**
	 * 根据订单ID和用户ID获取订单信息
	 * 
	 * @param orderId
	 *            订单ID
	 * @param accountId
	 *            用户ID
	 * @return OrderGroupPurcBean
	 */
	public OrderGroupPurcBean getOrderGroupPurcByOrderIdAndAccountId(@Param("orderId") String orderId, @Param("accountId") String accountId);

	/**
	 * 判断某用户是否可以取消某订单
	 * 
	 * @param orderId
	 *            订单ID
	 * @param accountId
	 *            用户ID
	 * @return OrderGroupPurc
	 */
	public OrderGroupPurc judgeOrderGroupPurcByOrderIdAndAccountId(@Param("orderId") String orderId, @Param("accountId") String accountId);
}