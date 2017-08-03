/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.account.dao;

import java.util.List;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.account.entity.Account;

/**
 * 用户注册DAO接口
 * @author ChenXiangyu
 * @version 2017-07-04
 */
@MyBatisDao
public interface AccountDao extends CrudDao<Account> {
	
	/**
	 * 更新会员使用状态
	 * @author ChenXiangyu
	 * @param account 会员信息
	 * @return 记录更新条数
	 */
	public int updateUseState(Account account);
	
	/**
	 * 根据下单情况获取会员列表
	 * 
	 * @param account
	 * @return
	 */
	public List<Account> findListByOrder(Account account);
}