/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.recharge.dao;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.recharge.entity.WalletDetail;

/**
 * 充值记录DAO接口
 * 
 * @author ChenXiangyu
 * @version 2017-07-05
 */
@MyBatisDao
public interface WalletDetailDao extends CrudDao<WalletDetail> {

}