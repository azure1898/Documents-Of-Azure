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
import com.its.modules.order.dao.BusinessDealDao;
import com.its.modules.order.entity.BusinessDeal;
import com.its.modules.order.entity.OrderGoods;

/**
 * 商户交易Service
 * @author lq
 */
@Service
@Transactional(readOnly = true)
public class BusinessDealService extends CrudService<BusinessDealDao, BusinessDeal> {

    @Autowired
    private BusinessDealDao businessDealDao;


    public BusinessDeal get(String id) {
        return super.get(id);
    }

    public List<BusinessDeal> findList(BusinessDeal OrderBusinessDeal) {
        return super.findList(OrderBusinessDeal);
    }

    public Page<BusinessDeal> findPage(Page<BusinessDeal> page, BusinessDeal OrderBusinessDeal) {
        return super.findPage(page, OrderBusinessDeal);
    }
    
    public BusinessDeal getTotal(String id) {
        return super.get(id);
    }

    public Map<String, Object> getTotal(BusinessDeal OrderBusinessDeal){
        return this.businessDealDao.getTotal(OrderBusinessDeal);
    }
    
    /**
     * 获取所有订单相关信息
     * @param OrderBusinessDeal
     * @return
     */
    public OrderGoods getAll(String id){
    	  return this.businessDealDao.getAll(id);
    }
}