/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.config.Global;
import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.business.dao.BusinessCategorydictDao;
import com.its.modules.business.entity.BusinessCategorydict;
import com.its.modules.module.entity.ModuleManage;
import com.its.modules.module.service.ModuleManageService;
import com.its.modules.sys.utils.DictUtils;

/**
 * 商户分类Service
 * 
 * @author ChenXiangyu
 * @version 2017-06-23
 */
@Service
@Transactional(readOnly = true)
public class BusinessCategorydictService extends CrudService<BusinessCategorydictDao, BusinessCategorydict> {

	@Autowired
	private ModuleManageService moduleManageService;
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

	/**
	 * 保存商户分类数据，同步更新对应模块信息
	 */
	@Transactional(readOnly = false)
	public void save(BusinessCategorydict businessCategorydict) {
		// 商户分类是否为新增数据
		boolean isNewRecordFlg = businessCategorydict.getIsNewRecord();
		// 保存商户管理信息
		super.save(businessCategorydict);

		ModuleManage moduleManage = null;
		// 商户分类是新添加的 新增对应的模块
		if (isNewRecordFlg) {
			// 以下注释掉的代码功能：新增时，若新增的商户分类名与已有的模块名相同，则将二者绑定，不论该模块是否由其他商户分类生成
			// List<ModuleManage> moduleManageList =
			// moduleManageService.getModuleByModuleName(businessCategorydict.getCategoryname());
			// if(moduleManageList != null && !moduleManageList.isEmpty()){
			// 已存在与此模块名称相同的模块
			// moduleManage = moduleManageList.get(0);
			// } else {
			// 不存在与此模块名称相同的模块
			moduleManage = new ModuleManage();
			moduleManage.setModuleName(businessCategorydict.getCategoryName());
			moduleManage.setModuleType(DictUtils.getDictValue("生活", "module_type", "生活"));
			// }
			// 标识该模块由商户分类转化而来
			moduleManage.setBusinessCategoryDictFlag(Global.YES);
			moduleManage.setBusinessCategoryDictId(businessCategorydict.getId());

			// 保存模块信息
			moduleManageService.save(moduleManage);
			// 商户分类是修改的 修改对应的模块
		} else {
			moduleManage = moduleManageService.findByBusinessCategorydictId(businessCategorydict.getId());
			if (moduleManage != null) {
				moduleManage.setIsNewRecord(false);
				moduleManage.setModuleName(businessCategorydict.getCategoryName());
				// 保存模块信息
				moduleManageService.save(moduleManage);
			}
		}
	}

	@Transactional(readOnly = false)
	public void delete(BusinessCategorydict businessCategorydict) {
		super.delete(businessCategorydict);
	}

	/**
	 * 根据商户分类名查询商户分类记录
	 * 
	 * @param categoryname
	 *            商户分类名
	 * @return 商户分类记录，若没有查到则返回null
	 */
	public List<BusinessCategorydict> getBusinessCategorydictByCategoryName(String categoryname) {
		List<BusinessCategorydict> BusinessCategorydictList = businessCategorydictDao
				.getBusinessCategoryByCategoryName(categoryname);
		if (BusinessCategorydictList == null || BusinessCategorydictList.isEmpty()) {
			return null;
		}
		return BusinessCategorydictList;
	}
	/**
	 * 通过商家ID获取分类列表 （设置推荐中用到）
	 * @param businessinfoId
	 * @return
	 * @return List<BusinessCategorydict>
	 * @author zhujiao   
	 * @date 2017年8月3日 下午7:47:12
	 */
	public List<BusinessCategorydict> getListBybusId(String businessinfoId){
	    return businessCategorydictDao.getListBybusId(businessinfoId);
	}

}