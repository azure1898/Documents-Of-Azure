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
	
	/**
	 * @Description：根据话题名称查询话题列表
	 * @Author：王萌萌
	 * @Date：2017年8月10日
	 * @param subjectName
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<SocialSubject> findBySubName(@Param("subjectName") String subjectName, @Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);
	
	/**
	 * @Description：根据话题名称查询话题是否存在
	 * @Author：王萌萌
	 * @Date：2017年8月11日
	 * @param subjectName 话题名称
	 * @return
	 */
	public int findSocialSubjectBySubName(@Param("subjectName") String subjectName);
	
	/**
	 * @Description：查询所有热门话题
	 * @Author：王萌萌
	 * @Date：2017年8月15日
	 * @param isRecommend 是否推荐
	 * @return
	 */
	public List<SocialSubject> findHotSubjectList(@Param("isRecommend") int isRecommend);
}