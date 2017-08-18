package com.its.modules.order.dao;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.order.entity.Account;

/**
 * 会员信息DAO接口
 * 
 * @author like
 * 
 * @version 2017-06-26
 */
@MyBatisDao
public interface AccountDao extends CrudDao<Account> {

    /**
     * 根据ID取得用户信息并施加行级锁
     * 
     * @param id
     *            用户ID
     * @return
     */
    public Account getForUpdate(String id);

}