/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.field.dao.FieldPartitionPriceDao;
import com.its.modules.field.service.FieldInfoService;
import com.its.modules.field.service.FieldPartitionPriceService;
import com.its.modules.order.dao.OrderFieldDao;
import com.its.modules.order.dao.OrderFieldListDao;
import com.its.modules.order.entity.OrderField;
import com.its.modules.order.entity.OrderFieldList;
import com.its.modules.sys.service.SysCodeMaxService;

/**
 * 场地预约订单表Service
 * 
 * @author xzc
 * @version 2017-07-06
 */
@Service
@Transactional(readOnly = true)
public class OrderFieldService extends CrudService<OrderFieldDao, OrderField> {
    /**
     * 场地预约订单表DAO接口
     */
    @Autowired
    private OrderFieldListDao orderFieldListDao;
    /**
     * 最大编码表Service
     */
    @Autowired
    private SysCodeMaxService sysCodeMaxService;

    /**
     * 场地预约子表-场地分段信息DAO接口
     */
    @Autowired
    private FieldPartitionPriceDao fieldPartitionPriceDao;
    /**
     * 场地预约Service
     */
    @Autowired
    private FieldInfoService fieldInfoService;
    /**
     * 订单跟踪表Service
     */
    @Autowired
    private OrderTrackService orderTrackService;

    /**
     * 场地预约子表-场地分段信息Service
     */
    @Autowired
    private FieldPartitionPriceService fieldPartitionPriceService;

    /**
     * 获取单个对象
     * 
     * @param id
     * @return
     */
    public OrderField get(String id) {
        OrderField orderField = super.get(id);
        List<OrderFieldList> orderFieldList = orderFieldListDao.findList(new OrderFieldList(orderField));
        if (orderFieldList != null && orderFieldList.size() > 0) {
            orderField.setOrderFieldList(orderFieldList.get(0));
        }
        return orderField;
    }

    /**
     * 查询集合
     * 
     * @param orderField
     * @return
     */
    public List<OrderField> findList(OrderField orderField) {
        return super.findList(orderField);
    }

    /**
     * 查询带分页的集合
     * 
     * @param page
     *            分页对象
     * @param orderField
     * @return
     */
    public Page<OrderField> findPage(Page<OrderField> page, OrderField orderField) {
        return super.findPage(page, orderField);
    }


    /**
     * 删除
     * 
     * @param orderField
     */
    @Transactional(readOnly = false)
    public void delete(OrderField orderField) {
        super.delete(orderField);
        orderFieldListDao.delete(new OrderFieldList(orderField));
    }

    /**
     * 通过场地预约清单id获取场地预约清单对象
     * 
     * @param id
     *            场地预约清单id
     * @return
     */
    @Transactional(readOnly = false)
    public OrderFieldList getOrderFieldListByFieldPartitionPriceId(String id) {

        return orderFieldListDao.getOrderFieldListByFieldPartitionPriceId(id);
    }

    /**
     * 本周订单个数
     * 
     * @return
     */
    public Integer findAllListCount() {
        return this.dao.findAllListCount();
    }

    /**
     * 本周订单金额
     * 
     * @return
     */
    public Double findAllListMoney() {
        return this.dao.findAllListMoney();
    }
}