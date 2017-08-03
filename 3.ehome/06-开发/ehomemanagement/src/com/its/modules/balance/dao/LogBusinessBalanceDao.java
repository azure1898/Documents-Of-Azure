/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.balance.dao;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.balance.entity.LogBusinessBalance;

/**
 * 商户结算操作日志DAO接口
 * @author Liuqi
 * @version 2017-08-01
 */
@MyBatisDao
public interface LogBusinessBalanceDao extends CrudDao<LogBusinessBalance> {
	
}