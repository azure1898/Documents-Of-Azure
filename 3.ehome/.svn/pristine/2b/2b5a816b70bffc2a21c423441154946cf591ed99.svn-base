/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.operation.dao;

import java.util.List;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.operation.entity.GroupPurchase;

/**
 * 团购管理DAO接口
 * @author caojing
 * @version 2017-06-28
 */
@MyBatisDao
public interface GroupPurchaseDao extends CrudDao<GroupPurchase> {
	
	/**
	 * 团购管理中，依据模块的商户分类字典表ID，获取商家ID
	 * @param groupPurchase
	 * @return
	 */
	public List<GroupPurchase> getBusinessId (GroupPurchase groupPurchase);
	
	/**
	 * 团购管理中，依据模块的商户ID，获取商家名称 
	 * @return
	 */
	public GroupPurchase getBusinessNameList(GroupPurchase groupPurchase);
	
	/**
	 * 团购管理中，取全部商家 
	 * @return
	 */
	public List<GroupPurchase> getAllBusinessList();
	
	/**
	 * 团购管理中，依据团购ID，获取团购信息详情
	 * @return
	 */
	public GroupPurchase getDetail(GroupPurchase groupPurchase);
	
	
	/**
	 * 团购管理：团购下线
	 * @param groupPurchase
	 */
	public int updateState(GroupPurchase groupPurchase);
	
	/**
	 * 团购管理：保存排序
	 * @param groupPurchase
	 */
	public int updateSortNum(GroupPurchase groupPurchase);
	
}