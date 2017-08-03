/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.business.dao.BusinessCategorydictDao;
import com.its.modules.business.dao.BusinessServicetimeDao;
import com.its.modules.business.entity.BusinessCategorydict;
import com.its.modules.business.entity.BusinessInfo;
import com.its.modules.business.entity.BusinessServicetime;

/**
 * 商家服务时间信息管理Service
 * 
 * @author zhujiao
 * @version 2017-06-29
 */
@Service
@Transactional(readOnly = true)
public class BusinessServicetimeService extends CrudService<BusinessServicetimeDao, BusinessServicetime> {

    @Autowired
    private BusinessServicetimeDao businessServicetimeDao;

    public BusinessServicetime get(String id) {
        return super.get(id);
    }

    public List<BusinessServicetime> findList(BusinessServicetime businessServicetime) {
        return super.findList(businessServicetime);
    }

    public Page<BusinessServicetime> findPage(Page<BusinessServicetime> page, BusinessServicetime businessServicetime) {
        return super.findPage(page, businessServicetime);
    }

    @Transactional(readOnly = false)
    public void save(BusinessServicetime businessServicetime) {
        super.save(businessServicetime);
    }

    @Transactional(readOnly = false)
    public void delete(BusinessServicetime businessServicetime) {
        super.delete(businessServicetime);
    }

    @Transactional(readOnly = false)
    public void deleteService(BusinessInfo businessInfo) {
        BusinessServicetime businessServicetime = new BusinessServicetime();
        businessServicetime.setBusinessinfoId(businessInfo.getId());
        super.delete(businessServicetime);
    }

    /**
     * add by zhujiao 根据商家ID和时段类型获取时段数据。
     * 
     * @return
     */
    public List<BusinessServicetime> findAllList(String timetype, String businessinfoId) {
        BusinessServicetime businessServicetime = new BusinessServicetime();
        businessServicetime.setTimetype(timetype);
        businessServicetime.setBusinessinfoId(businessinfoId);
        return businessServicetimeDao.findAllList(businessServicetime);
    }

    /**
     * 通过商家信息添加服务时段和配送时段的数据
     * 
     * @param businessInfo
     */
    @Transactional(readOnly = false)
    public void saveService(BusinessInfo businessInfo) {
        String[] sHours = businessInfo.getsHours().split(",");
        String[] eHours = businessInfo.geteHours().split(",");
        String[] sMinutes = businessInfo.getsMinutes().split(",");
        String[] eMinutes = businessInfo.geteMinutes().split(",");

        String[] sHours1 = businessInfo.getsHours1().split(",");
        String[] eHours1 = businessInfo.geteHours1().split(",");
        String[] sMinutes1 = businessInfo.getsMinutes1().split(",");
        String[] eMinutes1 = businessInfo.geteMinutes1().split(",");
        BusinessServicetime businessServicetime = new BusinessServicetime();
        // 添加服务时间段数据
        if (businessInfo.getServiceModel() != null) {//
            for (int i = 0; i < sHours.length; i++) {
                businessServicetime = new BusinessServicetime();
                businessServicetime.setBusinessinfoId(businessInfo.getId());
                businessServicetime.setTimetype(businessInfo.getServiceModel());
                businessServicetime.setBeginHour(sHours[i]);
                businessServicetime.setEndHour(eHours[i]);
                businessServicetime.setBeginMinute(sMinutes[i]);
                businessServicetime.setEndMinute(eMinutes[i]);
                super.save(businessServicetime);
            }
        }
        // 添加配置时间段数据
        if (businessInfo.getDistributeModel() != null) {
            for (int i = 0; i < sHours1.length; i++) {
                businessServicetime = new BusinessServicetime();
                businessServicetime.setBusinessinfoId(businessInfo.getId());
                businessServicetime.setTimetype("2");
                businessServicetime.setBeginHour(sHours1[i]);
                businessServicetime.setEndHour(eHours1[i]);
                businessServicetime.setBeginMinute(sMinutes1[i]);
                businessServicetime.setEndMinute(eMinutes1[i]);
                super.save(businessServicetime);
            }
        }
    }
}