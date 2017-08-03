/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.setup.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.setup.dao.BusinessCategorydictDao;
import com.its.modules.setup.entity.BusinessCategorydict;

/**
 * 商户分类Service
 * 
 * @author zhujiao
 * @version 2017-07-10
 */
@Service
@Transactional(readOnly = true)
public class BusinessCategorydictService extends CrudService<BusinessCategorydictDao, BusinessCategorydict> {

    @Autowired
    private BusinessCategorydictDao businessCategorydictDao;

    public BusinessCategorydict get(String id) {
        return super.get(id);
    }

    public List<BusinessCategorydict> findList(BusinessCategorydict businessCategorydict) {
        return super.findList(businessCategorydict);
    }

    public Page<BusinessCategorydict> findPage(Page<BusinessCategorydict> page,
            BusinessCategorydict businessCategorydict) {
        return super.findPage(page, businessCategorydict);
    }

    /**
     * add by zhujiao 返回所有分类数据
     * 
     * @return
     */
    public List<BusinessCategorydict> findAllList() {
        return businessCategorydictDao.findAllList(new BusinessCategorydict());
    }

    @Transactional(readOnly = false)
    public void delete(BusinessCategorydict businessCategorydict) {
        super.delete(businessCategorydict);
    }

    /**
     * 根据商家ID检索出对应的产品模式
     * 
     * @param paramer 检索条件
     * @author Liuhl
     * @return 商户分类Entity
     */
    public List<BusinessCategorydict> findCategoryListByBusinessId(Map<String,String> paramer) {
        return businessCategorydictDao.findCategoryListByBusinessId(paramer);
    }
}