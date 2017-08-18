/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.dao;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.order.entity.WalletDetail;

/**
 * 钱包明细DAO接口
 * 
 * @author like
 * @version 2017-07-17
 */
@MyBatisDao
public interface WalletDetailDao extends CrudDao<WalletDetail> {
    /**
     * 根据订单ID取得实际支付所用本金以及赠送金额
     * 
     * @param orderId
     * @return
     */
    public WalletDetail getByOrderId(String orderId);
}