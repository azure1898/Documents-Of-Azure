/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.setup.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.setup.dao.BusinessSalesDao;
import com.its.modules.setup.entity.BusinessInfo;
import com.its.modules.setup.entity.BusinessSales;

/**
 * 商家促销Service
 * @author like
 * @version 2017-07-17
 */
@Service
@Transactional(readOnly = true)
public class BusinessSalesService extends CrudService<BusinessSalesDao, BusinessSales> {

    
    public BusinessSales get(String id) {
        return super.get(id);
    }
    
    public List<BusinessSales> findList(BusinessSales businessSales) {
        return super.findList(businessSales);
    }
    
    public Page<BusinessSales> findPage(Page<BusinessSales> page, BusinessSales businessSales) {
        return super.findPage(page, businessSales);
    }
    
    @Transactional(readOnly = false)
    public void save(BusinessSales businessSales) {
        super.save(businessSales);
    }
    /**
     * 通过商家Id删除活动信息
     * @param businessInfo
     * @return void
     * @author zhujiao   
     * @date 2017年7月17日 下午4:39:25
     */
    @Transactional(readOnly = false)
    public void deleteSale(BusinessInfo businessInfo) {
        BusinessSales businessSales = new BusinessSales();
        businessSales.setBusinessInfoId(businessInfo.getId());
        super.delete(businessSales);
    }
    /**
     * 循环插入活动信息
     * @param businessInfo
     * @return void
     * @author zhujiao   
     * @date 2017年7月17日 下午4:39:15
     */
    @Transactional(readOnly = false)
    public void saveSale(BusinessInfo businessInfo) {
        BusinessSales businessSales = new BusinessSales();
        String[] moneys= businessInfo.getMoney().split(",");
        String[] benefitMoneys= businessInfo.getBenefitMoney().split(",");
        
        for (int i = 0; i < moneys.length; i++) {
        	// 如勾选启用满减活动，但并未在两个文本框内填写金额数字，则保存后满减不生效
        	if (StringUtils.isBlank(moneys[i]) || StringUtils.isBlank(benefitMoneys[i])) {
        		continue;
        	}
            businessSales = new BusinessSales();
            businessSales.setBusinessInfoId(businessInfo.getId());
            businessSales.setMoney(Double.parseDouble(moneys[i]));
            businessSales.setBenefitMoney(Double.parseDouble(benefitMoneys[i]));
            super.save(businessSales);
        }
    }
    /**
     * 获取活动信息
    * @param businessinfoId
    * @return
    * @return List<BusinessSales>
    * @author zhujiao   
    * @date 2017年7月17日 下午5:02:23
     */
    public List<BusinessSales> findAllList(String businessinfoId) {
        BusinessSales businessSales = new BusinessSales();
        businessSales.setBusinessInfoId(businessinfoId);
        return super.findList(businessSales);
    }
    
}