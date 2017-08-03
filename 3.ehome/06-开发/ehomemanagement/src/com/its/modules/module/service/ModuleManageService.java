/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.module.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.config.Global;
import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.module.dao.ModuleManageDao;
import com.its.modules.module.entity.ModuleManage;
import com.its.modules.sys.service.SysCodeMaxService;

/**
 * 模块管理Service
 * 
 * @author ChenXiangyu
 * @version 2017-06-26
 */
@Service
@Transactional(readOnly = true)
public class ModuleManageService extends CrudService<ModuleManageDao, ModuleManage> {

    @Autowired
    private ModuleManageDao moduleManageDao;
    @Autowired
    private SysCodeMaxService sysCodeMaxService;
    /** 流水类型编号：模块类型 */
    private static final String ID_IN = "002";
    /** 初始化最小值 */
    private static final int SMALL_IN = 0;
    /** 模块ID最小位数 */
    private static final int MIN_LENGTH = 2;

    public ModuleManage get(String id) {
        return super.get(id);
    }

    public List<ModuleManage> findList(ModuleManage moduleManage) {
        return super.findList(moduleManage);
    }

    /**
     * 根据商户分类Id查找模块信息
     * 
     * @param businessCategorydictId
     *            商户分类Id
     * @return 模块信息，若未找到返回 null
     */
    public ModuleManage findByBusinessCategorydictId(String businessCategorydictId) {
        List<ModuleManage> moduleManageList = moduleManageDao.findListByBusinessCategorydictId(businessCategorydictId);
        if (moduleManageList != null && !moduleManageList.isEmpty()) {
            return moduleManageList.get(0);
        }
        return null;
    }

    public Page<ModuleManage> findPage(Page<ModuleManage> page, ModuleManage moduleManage) {
        return super.findPage(page, moduleManage);
    }

    @Transactional(readOnly = false)
    public void save(ModuleManage moduleManage) {
        if (!Global.YES.equals(moduleManage.getBusinessCategoryDictFlag())) {
            moduleManage.setBusinessCategoryDictId(null);
            moduleManage.setBusinessCategoryDictFlag(Global.NO);
        }

        if (moduleManage.getIsNewRecord()) {
            Integer codeNo = sysCodeMaxService.getCodeNo(ID_IN, SMALL_IN, Boolean.FALSE.toString());
            // 模块ID不足两位，前补零
            if (String.valueOf(codeNo).length() < MIN_LENGTH) {
                moduleManage.setId(String.format("%02d", codeNo));
            } else {
                moduleManage.setId(String.valueOf(codeNo));
            }
            moduleManage.setIsNewRecord(true);
        }
        super.save(moduleManage);
    }

    @Transactional(readOnly = false)
    public void delete(ModuleManage moduleManage) {
        super.delete(moduleManage);
    }

    /**
     * 根据模块名称查找模块记录
     * 
     * @param modulename
     *            模块名称
     * @return 模块记录，若没有查到则返回null
     */
    public List<ModuleManage> getModuleByModuleName(String modulename) {
        List<ModuleManage> moduleManageList = moduleManageDao.getModuleByModuleName(modulename);
        if (moduleManageList == null || moduleManageList.isEmpty()) {
            return null;
        }
        return moduleManageList;
    }

    /**
     * 在优惠券管理中获取生活类服务品类和物业缴费这一服务品类的记录
     * 
     * @return 模块记录，若没有查到则返回null
     */
    public List<ModuleManage> getLifeModule() {
        List<ModuleManage> moduleManageList = moduleManageDao.getLifeModule();
        if (moduleManageList == null || moduleManageList.isEmpty()) {
            return null;
        }
        return moduleManageList;
    }

    /**
     * 查询全部模块数据
     * 
     * @author zhujiao
     * @date 2017年7月4日 下午7:39:39
     * @return List<AdverPosition>
     */
    public List<ModuleManage> findAllList() {
        return moduleManageDao.findAllList(new ModuleManage());
    }

    /**
     * 团购管理中，获取模块列表
     * 
     * @param moduleManage
     * @return
     */
    public List<ModuleManage> getLifeModuleList(ModuleManage moduleManage) {
        return moduleManageDao.getLifeModuleList(moduleManage);
    }

    /**
     * 团购管理中，获取模块的商户分类字典表ID
     * 
     * @param moduleManage
     * @return
     */
    public ModuleManage getBusinessCategoryDictId(ModuleManage moduleManage) {
        return moduleManageDao.getBusinessCategoryDictId(moduleManage);
    }
    /**
     * 设置管理中 获取社区模块的列表
     * @return
     * @return ModuleManage
     * @author zhujiao   
     * @date 2017年7月25日 下午2:41:02
     */
    public List<ModuleManage> getCommunityModuleList() {
        return moduleManageDao.getCommunityModuleList(new ModuleManage());
    }
    /**
     * 设置管理中 获取社区模块的列表
     * @return
     * @return List<ModuleManage>
     * @author zhujiao   
     * @date 2017年7月26日 上午11:03:20
     */
    public List<ModuleManage> getSetModuleList(List<String> moduleIds) {
        return moduleManageDao.getSetModuleList(moduleIds);
    }
    
}