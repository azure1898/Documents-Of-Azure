/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.setup.service;

import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.common.service.ServiceException;
import com.its.common.utils.StringUtils;
import com.its.modules.setup.dao.BusinessCategorydictDao;
import com.its.modules.setup.dao.BusinessInfoDao;
import com.its.modules.setup.entity.BusinessCategorydict;
import com.its.modules.setup.entity.BusinessInfo;

/**
 * 商家信息管理Service
 * 
 * @author zhujiao
 * @version 2017-07-10
 */
@Service
@Transactional(readOnly = true)
public class BusinessInfoService extends CrudService<BusinessInfoDao, BusinessInfo> {

    @Autowired
    private BusinessInfoDao businessInfoDao;
    @Autowired
    private BusinessCategorydictDao businessCategorydictDao;
    @Autowired
    private BusinessServicetimeService businessServicetimeService;
    @Autowired
    private BusinessUnitService businessUnitService;
    @Autowired
    private BusinessSalesService businessSalesService;

    public BusinessInfo get(String id) {
        BusinessInfo businessInfo = businessInfoDao.get(id);
        try {
            businessInfo.setCategoryList(businessCategorydictDao.findList(new BusinessCategorydict(businessInfo)));
        } catch (Exception e) {
            System.out.println("获取商家信息失败：" + e.getMessage());
        }
        return businessInfoDao.get(id);
    }

    public List<BusinessInfo> findList(BusinessInfo businessInfo) {
        return super.findList(businessInfo);
    }

    public Page<BusinessInfo> findPage(Page<BusinessInfo> page, BusinessInfo businessInfo) {
        return super.findPage(page, businessInfo);
    }

    @Transactional(readOnly = false)
    public void save(BusinessInfo businessInfo) {
        super.save(businessInfo);
    }

    /**
     * 商家基本資料信息修改
     * 
     * @param businessInfo
     * @return void
     * @author zhujiao
     * @date 2017年7月12日 下午6:11:56
     */
    @Transactional(readOnly = false)
    public void updateBaseInfo(BusinessInfo businessInfo) {
        if (StringUtils.isNotBlank(businessInfo.getBusinessIntroduce())) {
            businessInfo.setBusinessIntroduce(StringEscapeUtils.unescapeHtml4(businessInfo.getBusinessIntroduce()));
        }
        businessInfoDao.updateBaseInfo(businessInfo);
    }

    /**
     * 修改商家营业状态
     * 
     * @param businessInfo
     * @return void
     * @author zhujiao
     * @date 2017年7月13日 下午7:06:49
     */
    @Transactional(readOnly = false)
    public void updateState(BusinessInfo businessInfo) {
        businessInfoDao.updateState(businessInfo);
    }

    /**
     * 获取商家信息
     * 
     * @param businessinfoId
     * @return
     */
    public BusinessInfo getJoinArea(String businessinfoId) {
        return dao.getJoinArea(businessinfoId);
    }

    /**
     * 修改商家营业设置
     * 
     * @param businessInfo
     * @return void
     * @author zhujiao
     * @date 2017年7月17日 下午4:47:26
     */
    @Transactional(readOnly = false)
    public void updateBusiness(BusinessInfo businessInfo) {
        businessInfoDao.updateBusiness(businessInfo);
        // 删除时间段信息
        businessServicetimeService.deleteService(businessInfo);
        // 添加时间段修改
        if (businessInfo.getsHours() != null) {
            businessServicetimeService.saveService(businessInfo);
        }
        // 删除活动信息
        businessSalesService.deleteSale(businessInfo);
        // 添加活动信息
        if (businessInfo.getMoney() != null) {
            businessSalesService.saveSale(businessInfo);
        }
        // 删除单位信息
        businessUnitService.deleteUnit(businessInfo);
        // 添加单位信息
        if (businessInfo.getUnitName() != null) {
            businessUnitService.saveUnit(businessInfo);
        }

    }

    /**
     * 修改商家提醒状态
     * 
     * @param businessInfo
     * @return void
     * @author zhujiao
     * @date 2017年7月13日 下午7:06:49
     */
    @Transactional(readOnly = false)
    public void updateSetting(BusinessInfo businessInfo) {
        businessInfoDao.updateSetting(businessInfo);
    }
}