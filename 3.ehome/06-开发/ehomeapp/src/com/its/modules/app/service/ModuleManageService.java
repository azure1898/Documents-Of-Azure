package com.its.modules.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.common.utils.StringUtils;

import com.its.modules.app.bean.ModuleManageBean;
import com.its.modules.app.dao.ModuleManageDao;
import com.its.modules.app.entity.ModuleManage;

/**
 * 模块管理Service
 * 
 * @author like
 * 
 * @version 2017-07-03
 */
@Service
@Transactional(readOnly = true)
public class ModuleManageService extends CrudService<ModuleManageDao, ModuleManage> {

	public ModuleManage get(String id) {
		return super.get(id);
	}

	public List<ModuleManage> findList(ModuleManage moduleManage) {
		return super.findList(moduleManage);
	}

	public Page<ModuleManage> findPage(Page<ModuleManage> page, ModuleManage moduleManage) {
		return super.findPage(page, moduleManage);
	}

	@Transactional(readOnly = false)
	public void save(ModuleManage moduleManage) {
		super.save(moduleManage);
	}

	@Transactional(readOnly = false)
	public void delete(ModuleManage moduleManage) {
		super.delete(moduleManage);
	}

	/**
	 * 获取某楼盘下的模块列表（关联在产品模式下的模块）
	 * 
	 * @param villageInfoId
	 *            楼盘ID
	 * @return List<ModuleManage>
	 */
	public List<ModuleManage> getModuleListByVillageInfoId(String villageInfoId) {
		return dao.getModuleListByVillageInfoId(villageInfoId);
	}

	/**
	 * 获取某商户某产品模式下的商户分类的模块ID
	 * 
	 * @param prodType
	 *            产品模式：0商品购买 1服务预约 2课程购买 3场地预约
	 * @param businessInfoId
	 *            商户ID
	 */
	public String getModuleId(String prodType, String businessInfoId) {
		return dao.getModuleId(prodType, businessInfoId);
	}

	/**
	 * 获取生活首页模块推荐列表
	 * 
	 * @param moduleIds
	 *            模块ID列表
	 * @return List<ModuleManageBean>
	 */
	public List<ModuleManageBean> getModuleList(String recomModules, String villageInfoId) {
		List<String> moduleIds = this.getStrSplitList(recomModules);
		if (moduleIds.size() == 0) {
			return new ArrayList<ModuleManageBean>();
		}
		return dao.getModuleList(moduleIds);
	}

	/**
	 * 获取某楼盘下某推荐位置的商家列表
	 * 
	 * @param recommendPosition
	 *            推荐位置：00首页推荐商家 10社区模块推荐2 11社区更多服务推荐 20生活商家推荐1
	 * @param villageInfoId
	 *            楼盘ID
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> getRecommendBusinessList(String recommendPosition, String villageInfoId) {
		return dao.getRecommendBusinessList(recommendPosition, villageInfoId);
	}

	/**
	 * 根据手机端模块编码获取模块信息
	 * 
	 * @param phoneModuleCode
	 *            手机端模块编码
	 * @return ModuleManage
	 */
	public ModuleManage getModuleByPhoneCode(String phoneModuleCode) {
		return dao.getModuleByPhoneCode(phoneModuleCode);
	}

	/**
	 * 获取模块管理列表
	 * 
	 * @param list
	 *            INLIST
	 * @return List<ModuleManage>
	 */
	public List<ModuleManage> getModuleManageList(List<String> list) {
		if (list == null || list.size() == 0) {
			return new ArrayList<ModuleManage>();
		}
		String inStr = "";
		for (int i = 0; i < list.size(); i++) {
			inStr = (i == list.size() - 1) ? inStr + list.get(i) : inStr + list.get(i) + ",";
		}
		return dao.getModuleManageList(list, inStr);
	}

	/**
	 * 获取模块管理列表
	 * 
	 * @param inStr
	 *            INSTR
	 * @return List<ModuleManage>
	 */
	public List<ModuleManage> getModuleManageList(String inStr) {
		if (StringUtils.isBlank(inStr)) {
			return new ArrayList<ModuleManage>();
		}
		List<String> list = this.getStrSplitList(inStr);
		return dao.getModuleManageList(list, inStr);
	}

	/**
	 * 拆分字符串并获取LIST集合
	 * 
	 * @param str
	 *            要拆分的字符串
	 * @return LIST集合
	 */
	public List<String> getStrSplitList(String str) {
		List<String> list = new ArrayList<String>();
		if (StringUtils.isNoneBlank(str)) {
			for (String string : str.replace("，", ",").split(",")) {
				list.add(string);
			}
		}
		return list;
	}
	
	/**
	 * 获取某模块的产品模式
	 * @param moduleID
	 * @return
	 *            产品模式：0商品购买 1服务预约 2课程购买 3场地预约
	 */
	public String getProdType(String moduleID) {
		return dao.getProdType(moduleID);
	}
}