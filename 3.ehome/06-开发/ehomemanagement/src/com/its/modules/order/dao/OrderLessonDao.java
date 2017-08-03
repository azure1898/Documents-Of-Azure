/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.dao;

import java.util.Map;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.order.entity.OrderLesson;

/**
 * 订单-课程培训类DAO接口
 * 
 * @author liuhl
 * @version 2017-07-19
 */
@MyBatisDao
public interface OrderLessonDao extends CrudDao<OrderLesson> {

    /**
     * 订单状态更新前检查
     * 
     * @param paramer
     *            检索条件
     */
    int check(Map<String, String> paramer);

    /**
     * 订单取消
     * 
     * @param orderLesson
     *            更新条件
     */
    int cancel(OrderLesson orderLesson);

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
}