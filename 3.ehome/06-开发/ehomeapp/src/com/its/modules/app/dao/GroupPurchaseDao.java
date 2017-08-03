package com.its.modules.app.dao;

import java.util.List;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.app.bean.GroupPurchaseBean;
import com.its.modules.app.entity.GroupPurchase;

/**
 * 团购管理DAO接口
 * 
 * @author like
 * 
 * @version 2017-07-04
 */
@MyBatisDao
public interface GroupPurchaseDao extends CrudDao<GroupPurchase> {

	/**
	 * 获取某商家正在进行和即将进行的团购活动
	 * 
	 * @param businessInfoID
	 *            商家ID
	 * @return List<GroupPurchaseBean>
	 */
	public List<GroupPurchaseBean> getBusinessGroupPurchase(String businessInfoID);

	/**
	 * 获取正在进行或即将进行的团购活动
	 * 
	 * @param groupPurchase
	 *            团购信息，用于传递团购类型 团购类型：1->进行中 2->即将开始
	 * @return List<GroupPurchaseBean>
	 */
	public List<GroupPurchaseBean> getGroupPurchaseListOnOrComing(GroupPurchase groupPurchase);

	/**
	 * 根据团购活动子表ID获取该团购活动信息
	 * 
	 * @param groupPurchaseTimeId
	 *            团购活动子表ID
	 * @return GroupPurchaseBean
	 */
	public GroupPurchaseBean getGroupPurchaseBean(String groupPurchaseTimeId);

	/**
	 * 获取某楼盘下正在进行中和即将进行的团购活动
	 * 
	 * @param villageInfoId
	 *            楼盘ID
	 * @return List<GroupPurchaseBean>
	 */
	public List<GroupPurchaseBean> getByVillageInfoId(String villageInfoId);
}