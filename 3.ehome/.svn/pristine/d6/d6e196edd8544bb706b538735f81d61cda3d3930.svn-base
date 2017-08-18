package com.its.modules.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.app.entity.WalletDetail;

/**
 * 钱包明细DAO接口
 * 
 * @author like
 * 
 * @version 2017-07-17
 */
@MyBatisDao
public interface WalletDetailDao extends CrudDao<WalletDetail> {

	/**
	 * 获取钱包明细
	 * 
	 * @param accountId
	 *            用户ID
	 * @param pageNum
	 *            分页页码
	 * @return List<WalletDetail>
	 */
	public List<WalletDetail> getWalletDetail(@Param("accountId") String accountId, @Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

	/**
	 * 获取钱包订单支付或退款的详情
	 * 
	 * @param orderId
	 *            订单ID
	 * @param tradeType
	 *            交易类型：0-充值；1-充值赠送；2-钱包支付(订单消费)；3-手环支付；4-刷脸支付；5-退款(订单取消)
	 * @return WalletDetail
	 */
	public WalletDetail getWalletDetailOfOrder(@Param("orderId") String orderId, @Param("tradeType") String tradeType);
}