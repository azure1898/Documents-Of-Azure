/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.field.dao;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.field.entity.FieldPartitionPrice;

import java.util.List;

/**
 * 场地预约子表-场地分段信息DAO接口
 * @author xzc
 * @version 2017-07-03
 */
@MyBatisDao
public interface FieldPartitionPriceDao extends CrudDao<FieldPartitionPrice> {

    /**
     * 修改  场地分段信息状态
     * @param fieldPartitionPrice
     */
    void updateState(FieldPartitionPrice fieldPartitionPrice);

    /**
     * 取消订单
     * @param id
     */
    void cancelOrderFieldList(String id);

    /**
     * 查询预约时间的场地预约分段信息
     * @param fieldPartitionPrice 场地预约信息
     * @return
     */
    List<FieldPartitionPrice> findListByAppointmentTime(FieldPartitionPrice fieldPartitionPrice);

    /**
     * 查询场地预约分段信息并添加行级锁
     * @param fieldPartitionPrice 场地预约信息
     * @return
     */
    FieldPartitionPrice getForUpdate(String id);
}