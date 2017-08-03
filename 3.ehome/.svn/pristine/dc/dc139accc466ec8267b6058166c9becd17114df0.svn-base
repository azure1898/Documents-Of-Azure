/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.dao;

import java.util.Map;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.order.entity.OrderField;

/**
 * 场地预约订单表DAO接口
 * @author xzc
 * @version 2017-07-06
 */
@MyBatisDao
public interface OrderFieldDao extends CrudDao<OrderField> {

    /**
     * 本周订单个数
     * @return
     */
    int findAllListCount();


    /**
     * 本周订单金额
     * @return
     */
    double findAllListMoney();


    /**
     * 订单状态更新前检查
     * @param paramer 检索条件
     */
    int check(Map<String, String> paramer);

}