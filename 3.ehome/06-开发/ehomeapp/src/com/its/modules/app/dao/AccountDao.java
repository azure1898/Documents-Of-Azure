package com.its.modules.app.dao;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.app.entity.Account;

/**
 * 会员信息DAO接口
 * 
 * @author like
 * 
 * @version 2017-06-26
 */
@MyBatisDao
public interface AccountDao extends CrudDao<Account> {

	public boolean isExist(String phoneNum);

	public Account getByPhoneNum(String phoneNum);

	/**
	 * 保存用户的楼盘选择
	 * 
	 * @id
	 * @param villageID
	 */
	public void saveAccountVillageID(String id, String villageID);
}