/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.common.utils.StringUtils;
import com.its.modules.app.bean.MyCollectBean;
import com.its.modules.app.dao.MyCollectDao;
import com.its.modules.app.entity.MyCollect;

/**
 * 我的收藏Service
 * 
 * @author like
 * @version 2017-07-04
 */
@Service
@Transactional(readOnly = true)
public class MyCollectService extends CrudService<MyCollectDao, MyCollect> {

	public MyCollect get(String id) {
		return super.get(id);
	}

	public List<MyCollect> findList(MyCollect myCollect) {
		return super.findList(myCollect);
	}

	public Page<MyCollect> findPage(Page<MyCollect> page, MyCollect myCollect) {
		return super.findPage(page, myCollect);
	}

	@Transactional(readOnly = false)
	public void save(MyCollect myCollect) {
		super.save(myCollect);
	}

	@Transactional(readOnly = false)
	public void delete(MyCollect myCollect) {
		super.delete(myCollect);
	}

	/**
	 * 取消收藏
	 * 
	 * @param userID
	 * @param buildingID
	 * @param businessID
	 */
	@Transactional(readOnly = false)
	public void cancelCollection(String accountId, String villageInfoId, String businessInfoId) {
		dao.cancelCollection(accountId, villageInfoId, businessInfoId);
	}

	/**
	 * 获取用户的收藏集合
	 * 
	 * @param accountId
	 * @param villageInfoId
	 */
	public List<MyCollectBean> findMyCollectOfAccount(String accountId, String villageInfoId) {
		return dao.findMyCollectOfAccount(accountId, villageInfoId);
	}

	public MyCollect hasCollect(String accountId, String villageInfoId, String businessInfoId) {
		return dao.hasCollect(accountId, villageInfoId, businessInfoId);
	}
	/**
	 * 判断用户是否收藏商家
	 * 
	 * @param accountId
	 * @param villageInfoId
	 * @param businessInfoId
	 * @return
	 */
	public int isCollect(String accountId, String villageInfoId, String businessInfoId) {
		if (StringUtils.isBlank(accountId) || StringUtils.isBlank(villageInfoId) || StringUtils.isBlank(businessInfoId)) {
			return 0;
		}
		return dao.hasCollect(accountId, villageInfoId, businessInfoId) != null ? 1 : 0;
	}
}