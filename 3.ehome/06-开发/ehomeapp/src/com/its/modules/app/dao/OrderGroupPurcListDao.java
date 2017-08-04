package com.its.modules.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.app.entity.OrderGroupPurcList;

/**
 * 订单-团购类子表-团购券清单DAO接口
 * 
 * @author sushipeng
 * 
 * @version 2017-07-13
 */
@MyBatisDao
public interface OrderGroupPurcListDao extends CrudDao<OrderGroupPurcList> {

	/**
	 * 获取某团购订单中某种状态的团购券列表
	 * 
	 * @param orderGroupPurcId
	 *            团购订单ID
	 * @param groupPurcState
	 *            团购券状态:0未消费1已消费2退款处理中3已退款
	 * @return List<OrderGroupPurcList>
	 */
	public List<OrderGroupPurcList> getOrderGroupPurcList(@Param("orderGroupPurcId") String orderGroupPurcId, @Param("groupPurcState") String groupPurcState);
}