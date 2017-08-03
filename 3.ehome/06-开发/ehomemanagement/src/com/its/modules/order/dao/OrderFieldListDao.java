/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.dao;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.order.entity.OrderFieldList;

/**
 * 场地预约订单表DAO接口
 * @author xzc
 * @version 2017-07-06
 */
@MyBatisDao
public interface OrderFieldListDao extends CrudDao<OrderFieldList> {

    /**
     *  通过场地预约订单清单id获取场地预约清单对象
     * @param Id 场地预约清单id
     * @return
     */
    OrderFieldList getJoin(String Id);
    /**
     *  通过场地预约分段信息d 获取场地预约清单对象
     * @param Id 场地预约清单id
     * @return
     */
    OrderFieldList getOrderFieldListByFieldPartitionPriceId(String Id);

    /**
     * 取消预约
     * @param id 订单场地预约清单表ID
     * @return
     */
    void cancelOrderFieldList(String id);
}