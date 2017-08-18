/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.social.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.social.bean.SocialSpeakBean;
import com.its.modules.social.entity.SocialSpeak;

/**
 * 发言管理DAO接口
 * @author wmm
 * @version 2017-08-01
 */
@MyBatisDao
public interface SocialSpeakDao extends CrudDao<SocialSpeak> {
	
	/**
	 * 修改状态
	 * @param socialSpeak
	 * @return
	 */
	public int changeDelFlag(SocialSpeak socialSpeak);
	
	/**
	 * 修改置顶状态
	 * @param socialSpeak
	 * @return
	 */
	public int changeTop(SocialSpeak socialSpeak);
	
	/**
	 * @Description：根据用户id查询该用户在改楼盘下关注的发言,如果用户id为空，查询当前楼盘所有发言
	 * @Author：刘浩浩
	 * @Date：2017年8月3日
	 * @param userId 用户ID
	 * @param socialSpeak 发言对象
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<SocialSpeakBean> findListByUserId(@Param("userId") String userId, @Param("socialSpeak") SocialSpeak socialSpeak, @Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);
	
	/**
	 * @Description：根据发言id 获取发言bean
	 * @Author：刘浩浩
	 * @Date：2017年8月4日
	 * @param id 发言id
	 * @return
	 */
	public SocialSpeakBean getSocialSpeakBeanById(@Param("id") String id);
	
	/**
	 * @Description：根据条件查询发言/转发集合
	 * @Author：刘浩浩
	 * @Date：2017年8月8日
	 * @param socialSpeak
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<SocialSpeakBean> findSpeakList(@Param("socialSpeak") SocialSpeak socialSpeak, @Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);
	
	/**
	 * 
	 * @Description：根据用户id查询该用户的所有发言数据
	 * @Author：邵德才
	 * @Date：2017年8月8日
	 * @param userId
	 * @return
	 */
	public List<SocialSpeak> getListByUserId(@Param("userid")String userid);
	
	/**
	 * 
	 * @Description：根据speakid查询转发数量
	 * @Author：邵德才
	 * @Date：2017年8月8日
	 * @param speakid
	 * @return
	 */
	public int countForward(@Param("speakid")String speakid);
	
	/**
	 * @Description：根据发言id查找发言
	 * @Author：王萌萌
	 * @Date：2017年8月8日
	 * @param speakId 发言id
	 * @return
	 */
	public SocialSpeakBean findSpeakBySpeakId(@Param("speakId") String speakId);
	
	/**
	 * @Description：根据话题名称和楼盘id查询发言
	 * @Author：王萌萌
	 * @Date：2017年8月10日
	 * @param addressId 楼盘id
	 * @param subjectName 话题名称
	 * @return
	 */
	public List<SocialSpeakBean> findByAddIdAndSubName(@Param("addressId") String addressId, @Param("subjectName") String subjectName, @Param("subjectId") String subjectId, 
			@Param("userId") String userId, @Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);
	
	/**
	 * @Description：根据用户id 查询该用户的发言数量
	 * @Author：刘浩浩
	 * @Date：2017年8月14日
	 * @param socialSpeak
	 * @return
	 */
	public int countSpeak(@Param("socialSpeak") SocialSpeak socialSpeak);

}