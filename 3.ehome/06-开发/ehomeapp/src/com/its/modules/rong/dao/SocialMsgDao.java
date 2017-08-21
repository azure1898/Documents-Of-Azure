/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.rong.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.rong.entity.SocialMsg;

/**
 * 消息表DAO接口
 * @author 刘浩浩
 * @version 2017-08-11
 */
@MyBatisDao
public interface SocialMsgDao extends CrudDao<SocialMsg> {
	
	/**
	 * @Description：查询未读消息的数量
	 * @Author：王萌萌
	 * @Date：2017年8月17日
	 * @param toUserId 用户id
	 * @param secType 消息类型
	 * @return
	 */
	public int countUnRead(@Param("toUserId") String toUserId, @Param("secType") Integer secType, @Param("isRead") Integer isRead, @Param("firType") Integer firType);
	
	/**
	 * @Description：根据条件查询消息集合
	 * @Author：刘浩浩
	 * @Date：2017年8月19日
	 * @param socialMsg
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<SocialMsg> findListByPage(@Param("socialMsg") SocialMsg socialMsg, @Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);
}