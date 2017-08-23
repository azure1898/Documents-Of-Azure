package com.its.modules.rabbitmq.dao;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.rabbitmq.entity.UserDetail;

@MyBatisDao
public interface UserDetailDao extends CrudDao<UserDetail>{

}
