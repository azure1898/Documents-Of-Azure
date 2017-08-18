/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.balance.dao;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.balance.entity.DownloadBill;

/**
 * 结算对账单DAO接口
 * @author Liuqi
 * @version 2017-08-16
 */
@MyBatisDao
public interface DownloadBillDao extends CrudDao<DownloadBill> {
	
}