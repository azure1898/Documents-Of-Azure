/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.setup.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.setup.entity.BusinessInfo;
import com.its.modules.setup.entity.BusinessSales;
import com.its.modules.setup.entity.BusinessServicetime;
import com.its.modules.setup.entity.BusinessUnit;
import com.its.modules.setup.dao.BusinessUnitDao;

/**
 * 商家单位信息Service
 * 
 * @author liuhl
 * @version 2017-07-12
 */
@Service
@Transactional(readOnly = true)
public class BusinessUnitService extends CrudService<BusinessUnitDao, BusinessUnit> {

    public BusinessUnit get(String id) {
        return super.get(id);
    }

    public List<BusinessUnit> findList(BusinessUnit businessUnit) {
        return super.findList(businessUnit);
    }

    public Page<BusinessUnit> findPage(Page<BusinessUnit> page, BusinessUnit businessUnit) {
        return super.findPage(page, businessUnit);
    }

    @Transactional(readOnly = false)
    public void save(BusinessUnit businessUnit) {
        super.save(businessUnit);
    }

    /**
     * 通过商家Id删除单位信息
     * 
     * @param businessInfo
     * @return void
     * @author zhujiao
     * @date 2017年7月17日 下午4:29:38
     */
    @Transactional(readOnly = false)
    public void deleteUnit(BusinessInfo businessInfo) {
        BusinessUnit businessUnit = new BusinessUnit();
        businessUnit.setBusinessInfoId(businessInfo.getId());
        super.delete(businessUnit);
    }
    /**
     * 循环插入单位信息
     * @param businessInfo
     * @return void
     * @author zhujiao   
     * @date 2017年7月17日 下午4:37:21
     */
    @Transactional(readOnly = false)
    public void saveUnit(BusinessInfo businessInfo) {
        BusinessUnit businessUnit = new BusinessUnit();
        String[] unitNames = businessInfo.getUnitName().split(",");
        for (int i = 0; i < unitNames.length; i++) {
            businessUnit = new BusinessUnit();
            businessUnit.setBusinessInfoId(businessInfo.getId());
            businessUnit.setName(unitNames[i]);
            super.save(businessUnit);
        }
    }
    /**
     * 获取单位信息
     * @param businessinfoId
     * @return
     * @return List<BusinessUnit>
     * @author zhujiao   
     * @date 2017年7月17日 下午5:03:41
     */
    public List<BusinessUnit> findAllList(String businessinfoId) {
        BusinessUnit businessUnit = new BusinessUnit();
        businessUnit.setBusinessInfoId(businessinfoId);
        return super.findList(businessUnit);
    }
}