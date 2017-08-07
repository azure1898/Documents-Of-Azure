/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.social.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.social.entity.SocialSubject;

/**
 * 话题管理DAO接口
 * @author wmm
 * @version 2017-07-31
 */
@MyBatisDao
public interface SocialSubjectDao extends CrudDao<SocialSubject> {
	
	public int updateSortNum(SocialSubject socialSubject);
	
	public int updateRecommend(SocialSubject socialSubject);
	
	public List<SocialSubject> findAll();
	
	/**
	 * @Description：根据发言ID 查询所有话题
	 * @Author：刘浩浩
	 * @Date：2017年8月4日
	 * @param fkId 外键ID
	 * @param fkType 类型
	 * @return
	 */
	public List<SocialSubject> findAllByfkId(@Param("fkId") String fkId, @Param("fkType") String fkType);
	
}