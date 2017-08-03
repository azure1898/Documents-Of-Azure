/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
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
	 * @return
	 */
	public List<WalletDetail> getWalletDetail(@Param("accountId") String accountId, @Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);
}