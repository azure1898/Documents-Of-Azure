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
import com.its.common.service.ServiceException;
import com.its.common.utils.StringUtils;
import com.its.modules.business.dao.BusinessCategorydictDao;
import com.its.modules.business.dao.BusinessInfoDao;
import com.its.modules.business.entity.BusinessCategorydict;
import com.its.modules.business.entity.BusinessInfo;

/**
 * 商家信息管理Service
 * 
 * @author zhujiao
 * @version 2017-06-26
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

    public BusinessInfo get(String id) {
        BusinessInfo businessInfo = businessInfoDao.get(id);
        businessInfo.setCategoryList(businessCategorydictDao.findList(new BusinessCategorydict(businessInfo)));
        return businessInfoDao.get(id);
    }

    public List<BusinessInfo> findList(BusinessInfo businessInfo) {
        return super.findList(businessInfo);
    }
    public List<BusinessInfo> findAllList() {
        return businessInfoDao.findAllList(new BusinessInfo());
    }

    public Page<BusinessInfo> findPage(Page<BusinessInfo> page, BusinessInfo businessInfo) {
        return super.findPage(page, businessInfo);
    }

    @Transactional(readOnly = false)
    public void save(BusinessInfo businessInfo) {
        super.save(businessInfo);
    }

    /**
     * 商家银行账号管理
     * 
     * @author zhujiao
     * @param businessInfo
     */
    @Transactional(readOnly = false)
    public void editBank(BusinessInfo businessInfo) {
        businessInfoDao.editBank(businessInfo);
    }

    /**
     * 冻结商家信息
     * 
     * @author zhujiao
     * @param businessInfo
     */
    @Transactional(readOnly = false)
    public void updateState(BusinessInfo businessInfo) {
        businessInfoDao.updateState(businessInfo);
    }

    /**
     * 添加商家信息数据
     * 
     * @author zhujiao
     * @param businessInfo
     */
    @Transactional(readOnly = false)
    public void saveInfo(BusinessInfo businessInfo) {
        if (StringUtils.isBlank(businessInfo.getId())) {
            businessInfo.preInsert();
            businessInfoDao.insert(businessInfo);
            // 插入商家分类关联表
            if (businessInfo.getCategoryList() != null && businessInfo.getCategoryList().size() > 0) {
                businessInfoDao.insertInfoCategory(businessInfo);
            } else {
                throw new ServiceException(businessInfo.getBusinessName() + "没有设置分类！");
            }
            // 插入商家服务时间关联表
            if (businessInfo.getsHours() != null) {
                businessServicetimeService.saveService(businessInfo);
            }
            // 插入投放楼盘关联数据
            if (businessInfo.getVillageList() != null && businessInfo.getVillageList().size() > 0) {
                businessInfoDao.insertInfoServiceScope(businessInfo);
            } else {
                throw new ServiceException(businessInfo.getBusinessName() + "没有设置投放楼盘！");
            }
        } else {
            //修改主表信息
            businessInfoDao.update(businessInfo);
            //删除服务时段信息
            businessServicetimeService.deleteService(businessInfo);
            //插入时段的信息
            if (businessInfo.getsHours() != null) {
                businessServicetimeService.saveService(businessInfo);
            }
           //删除商家分类的信息
            businessInfoDao.deleteInfoCategory(businessInfo);
            //插入商家分类的信息
            if (businessInfo.getCategoryList() != null && businessInfo.getCategoryList().size() > 0) {
                businessInfoDao.insertInfoCategory(businessInfo);
            } else {
                throw new ServiceException(businessInfo.getBusinessName() + "没有设置分类！");
            }
            //删除商家账号下所有用户的分类角色
            
            
            //添加商家账号下所有用户的分类角色
            
            // 删除投放楼盘关联数据
            businessInfoDao.deleteInfoServiceScope(businessInfo);
            // 插入投放楼盘关联数据
            if (businessInfo.getVillageList() != null && businessInfo.getVillageList().size() > 0) {
                businessInfoDao.insertInfoServiceScope(businessInfo);
            } else {
                throw new ServiceException(businessInfo.getBusinessName() + "没有设置投放楼盘！");
            }
        }
    }

    /**
     * 验证商家名字是否有效
     * 
     * @author zhujiao
     * @date 2017年7月6日 下午3:51:19
     * @return AdverManage
     */
    @Transactional(readOnly = false)
    public BusinessInfo getModelByName(String name) {
        BusinessInfo r = new BusinessInfo();
        r.setBusinessName(name);
        return businessInfoDao.getModelByName(r);
    }
    @Transactional(readOnly = false)
    public List<BusinessInfo> getBusinessList(String categorydict) {
        BusinessInfo businessInfo = new BusinessInfo();
        BusinessCategorydict businessCategorydict = new BusinessCategorydict();
        businessCategorydict.setId(categorydict);
        businessInfo.setBusinessCategorydict(businessCategorydict);
        return super.findList(businessInfo);
    }
    /**
     * 根据模块获取商家列表信息  
     * @param moduleId
     * @return
     * @return List<BusinessInfo>
     * @author zhujiao   
     * @date 2017年7月27日 下午2:01:15
     */
    @Transactional(readOnly = false)
    public List<BusinessInfo> getBusListByModule(String moduleId) {
        return businessInfoDao.getBusListByModule(moduleId);
    }
}