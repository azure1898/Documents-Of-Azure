/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.social.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.social.bean.SocialRelationBean;
import com.its.modules.social.entity.SocialRelation;

/**
 * socialDAO接口
 * @author 刘浩浩
 * @version 2017-08-07
 */
@MyBatisDao
public interface SocialRelationDao extends CrudDao<SocialRelation> {
	
	/**
	 * @Description：根据用户id 查询该用户的关系用户集合
	 * @Author：刘浩浩
	 * @Date：2017年8月7日
	 * @param socialRelationBean 查询条件
	 * @param pageIndex 分页索引
	 * @param pageSize 分页大小
	 * @return
	 */
	public List<SocialRelationBean> findFocusListByUserId(@Param("socialRelationBean") SocialRelationBean socialRelationBean, @Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);
	
	/**
	 * @Description：根据用户id 查询该用户的粉丝集合
	 * @Author：刘浩浩
	 * @Date：2017年8月7日
	 * @param socialRelationBean 查询条件
	 * @param pageIndex 分页索引
	 * @param pageSize 分页大小
	 * @return
	 */
	public List<SocialRelationBean> findFansListByUserId(@Param("socialRelationBean") SocialRelationBean socialRelationBean, @Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);
	
	/**
	 * @Description：根据用户ID和登录用户ID查询出个人相关信息
	 * @Author：王萌萌
	 * @Date：2017年8月8日
	 * @param subUserId 
	 * @param userId
	 * @return
	 */
	public SocialRelationBean findPersonalInfo(@Param("userId") String userId, @Param("subUserId") String subUserId);
	
	/**
	 * @Description：根据用户id和从用户id删除记录
	 * @Author：王萌萌
	 * @Date：2017年8月9日
	 * @param userId 用户id
	 * @param subUserId 从用户id
	 */
	public void deleteByUserIdAndSubUserId(@Param("userId") String userId, @Param("subUserId") String subUserId);
	
	/**
	 * @Description：根据用户ID 查询粉丝数量
	 * @Author：刘浩浩
	 * @Date：2017年8月14日
	 * @param userId
	 * @return
	 */
	public int countFansByUserId(@Param("userId") String userId);
	
	/**
	 * @Description：genuine用户ID 查询关注数量
	 * @Author：刘浩浩
	 * @Date：2017年8月14日
	 * @param userId
	 * @return
	 */
	public int countFocusByUserId(@Param("userId") String userId);
	
	
	/**
	 * 
	 * @Description：根据userId和subUserId查询所有数据
	 * @Author：邵德才
	 * @Date：2017年8月14日
	 * @param userId
	 * @param subUserId
	 * @return
	 */
	public List<SocialRelation> findListByUserIdAndSubUserId(SocialRelation socialRelation);
	
	/**
	 * @Description：根据用户id查询出所有相互关注的朋友的信息
	 * @Author：王萌萌
	 * @Date：2017年8月14日
	 * @param userid 用户id
	 * @return
	 */
	public List<SocialRelationBean> findToUser(@Param("userId") String userId);
	
	/**
	 * @Description：根据用户ID， 从用户ID 查询用户关系对象
	 * @Author：刘浩浩
	 * @Date：2017年8月14日
	 * @param userId
	 * @param subUserId
	 * @return
	 */
	public SocialRelation getSocialRelation(@Param("userId") String userId, @Param("subUserId") String subUserId);
	
	/**
	 * @Description：是否已关注该用户
	 * @Author：王萌萌
	 * @Date：2017年8月14日
	 * @param userId 当前userid
	 * @param subUserId 被关注userid
	 * @return
	 */
	public int isFocus(@Param("userId") String userId, @Param("subUserId") String subUserId);

	/**
	 * 
	 * @Description：根据用户id设置为屏蔽Ta的发言
	 * @Author：邵德才
	 * @Date：2017年8月22日
	 * @param id
	 */
	public void updateBlack(String id);
	
}