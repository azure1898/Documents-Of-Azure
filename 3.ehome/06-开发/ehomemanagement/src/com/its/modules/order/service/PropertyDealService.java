/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.order.dao.PropertyDealDao;
import com.its.modules.order.entity.PropertyDeal;
import com.its.modules.order.entity.OrderGoods;

/**
 * 商户交易Service
 * @author lq
 */
@Service
@Transactional(readOnly = true)
public class PropertyDealService extends CrudService<PropertyDealDao, PropertyDeal> {

    @Autowired
    private PropertyDealDao PropertyDealDao;


    public PropertyDeal get(String id) {
        return super.get(id);
    }

    public List<PropertyDeal> findList(PropertyDeal OrderPropertyDeal) {
        return super.findList(OrderPropertyDeal);
    }

    public Page<PropertyDeal> findPage(Page<PropertyDeal> page, PropertyDeal OrderPropertyDeal) {
        return super.findPage(page, OrderPropertyDeal);
    }
    
    public PropertyDeal getTotal(String id) {
        return super.get(id);
    }

    public Map<String, Object> getTotal(PropertyDeal OrderPropertyDeal){
        return this.PropertyDealDao.getTotal(OrderPropertyDeal);
    }
    
    /**
     * 获取所有订单相关信息
     * @param OrderPropertyDeal
     * @return
     */
    public PropertyDeal getAll(String id){
    	  return this.PropertyDealDao.getAll(id);
    }
}