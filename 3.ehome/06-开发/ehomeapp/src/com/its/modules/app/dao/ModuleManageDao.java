package com.its.modules.app.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.app.bean.ModuleManageBean;
import com.its.modules.app.entity.ModuleManage;

/**
 * 模块管理DAO接口
 * 
 * @author like
 * 
 * @version 2017-07-03
 */
@MyBatisDao
public interface ModuleManageDao extends CrudDao<ModuleManage> {

	/**
	 * 获取某楼盘下的模块列表（关联在产品模式下的模块）
	 * 
	 * @param villageInfoId
	 *            楼盘ID
	 * @return List<ModuleManage>
	 */
	public List<ModuleManage> getModuleListByVillageInfoId(String villageInfoId);

	/**
	 * 获取某商户某产品模式下的商户分类的模块ID
	 * 
	 * @param prodType
	 *            产品模式：0商品购买 1服务预约 2课程购买 3场地预约
	 * @param businessInfoId
	 *            商户ID
	 * @return 模块ID
	 */
	public String getModuleId(@Param("prodType") String prodType, @Param("businessInfoId") String businessInfoId);

	/**
	 * 获取生活首页模块推荐列表
	 * 
	 * @param moduleIds
	 *            模块ID列表
	 * @return List<ModuleManageBean>
	 */
	public List<ModuleManageBean> getModuleList(List<String> moduleIds);

	/**
	 * 获取某楼盘下某推荐位置的商家列表
	 * 
	 * @param recommendPosition
	 *            推荐位置：00首页推荐商家 10社区模块推荐2 11社区更多服务推荐 20生活商家推荐1
	 * @param villageInfoId
	 *            楼盘ID
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> getRecommendBusinessList(@Param("recommendPosition") String recommendPosition, @Param("villageInfoId") String villageInfoId);

	/**
	 * 根据手机端模块编码获取模块信息
	 * 
	 * @param phoneModuleCode
	 *            机端模块编码
	 * @return ModuleManage
	 */
	public ModuleManage getModuleByPhoneCode(@Param("phoneModuleCode") String phoneModuleCode);

	/**
	 * 获取模块管理列表
	 * 
	 * @param list
	 *            INLIST
	 * @param inStr
	 *            INSTR
	 * @return List<ModuleManage>
	 */
	public List<ModuleManage> getModuleManageList(@Param("list") List<String> list, @Param("inStr") String inStr);
}