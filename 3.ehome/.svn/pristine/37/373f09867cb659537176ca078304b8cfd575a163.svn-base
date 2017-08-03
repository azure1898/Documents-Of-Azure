/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.module.dao;

import java.util.List;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.module.entity.ModuleManage;

/**
 * 模块管理DAO接口
 * @author ChenXiangyu
 * @version 2017-06-26
 */
@MyBatisDao
public interface ModuleManageDao extends CrudDao<ModuleManage> {
	public List<ModuleManage> findListByBusinessCategorydictId(String businessCategorydictId);

	public List<ModuleManage> getModuleByModuleName(String modulename);
	
	public List<ModuleManage> getLifeModule();

	/**
	 * 团购管理中，获取模块列表
	 * @param moduleManage
	 * @return
	 */
	public List<ModuleManage> getLifeModuleList(ModuleManage moduleManage);
	
	/**
	 * 团购管理中，获取模块的商户分类字典表ID
	 * @param moduleManage
	 * @return
	 */
    public ModuleManage getBusinessCategoryDictId(ModuleManage moduleManage);

    /**
     * 设置管理中  获取社区模块的列表
     * @param moduleManage
     * @return
     * @return List<ModuleManage>
     * @author zhujiao
     * @date 2017年7月25日 下午2:36:23
     */
    public List<ModuleManage> getCommunityModuleList(ModuleManage moduleManage);
    
    /**
     * 推荐设置-获取设置选中模块的生活社区模块列表
     * @param moduleIds
     * @return
     * @return List<ModuleManage>
     * @author zhujiao   
     * @date 2017年7月26日 上午11:05:10
     */
    public List<ModuleManage> getSetModuleList(List<String> moduleIds);

}