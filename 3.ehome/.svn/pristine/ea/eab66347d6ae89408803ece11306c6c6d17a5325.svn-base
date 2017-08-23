/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.social.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.social.bean.SocialRelationBean;
import com.its.modules.social.dao.SocialRelationDao;
import com.its.modules.social.entity.SocialRelation;

/**
 * socialService
 * @author 刘浩浩
 * @version 2017-08-07
 */
@Service
@Transactional(readOnly = true)
public class SocialRelationService extends CrudService<SocialRelationDao, SocialRelation> {
	
	@Autowired
	private SocialRelationDao socialRelationDao;

	public SocialRelation get(String id) {
		return super.get(id);
	}
	
	public List<SocialRelation> findList(SocialRelation socialRelation) {
		return super.findList(socialRelation);
	}
	
	public Page<SocialRelation> findPage(Page<SocialRelation> page, SocialRelation socialRelation) {
		return super.findPage(page, socialRelation);
	}
	
	@Transactional(readOnly = false)
	public void save(SocialRelation socialRelation) {
		super.save(socialRelation);
	}
	
	@Transactional(readOnly = false)
	public void delete(SocialRelation socialRelation) {
		super.delete(socialRelation);
	}
	
	/**
	 * @Description：根据用户id 查询该用户的关注集合
	 * @Author：刘浩浩
	 * @Date：2017年8月7日
	 * @param socialRelationBean 查询条件
	 * @param pageIndex 分页索引
	 * @param pageSize 分页大小
	 * @return
	 */
	public List<SocialRelationBean> findFocusListByUserId(SocialRelationBean socialRelationBean, int pageIndex, int pageSize){
		return socialRelationDao.findFocusListByUserId(socialRelationBean, pageIndex, pageSize);
	}
	
	/**
	 * @Description：根据用户id 查询该用户的粉丝集合
	 * @Author：刘浩浩
	 * @Date：2017年8月7日
	 * @param socialRelationBean 查询条件
	 * @param pageIndex 分页索引
	 * @param pageSize 分页大小
	 * @return
	 */
	public List<SocialRelationBean> findFansListByUserId(SocialRelationBean socialRelationBean, int pageIndex, int pageSize){
		return socialRelationDao.findFansListByUserId(socialRelationBean, pageIndex, pageSize);
	}
	
	/**
	 * @Description：根据用户ID和登录用户ID查询出个人相关信息
	 * @Author：王萌萌
	 * @Date：2017年8月8日
	 * @param subUserId 从用户ID
	 * @param userId 当前登录用户ID
	 * @return
	 */
	public SocialRelationBean findPersonalInfo(String userId, String subUserId) {
		return socialRelationDao.findPersonalInfo(userId, subUserId);
	}
	
	/**
	 * @Description：根据用户id和从用户id删除记录
	 * @Author：王萌萌
	 * @Date：2017年8月9日
	 * @param userId 用户id
	 * @param subUserId 从用户id
	 */
	@Transactional(readOnly = false)
	public void deleteByUserIdAndSubUserId(String userId, String subUserId) {
		socialRelationDao.deleteByUserIdAndSubUserId(userId, subUserId);
	}

	/**
	 * 
	 * @Description：根据userId和subUserId查询所有数据
	 * @Author：邵德才
	 * @Date：2017年8月14日
	 * @param socialRelation
	 * @return
	 */
	public List<SocialRelation> findListByUserIdAndSubUserId(SocialRelation socialRelation) {
		return socialRelationDao.findListByUserIdAndSubUserId(socialRelation);
	}
	
	/**
	 * @Description：根据用户id查询出所有相互关注的朋友的信息
	 * @Author：王萌萌
	 * @Date：2017年8月14日
	 * @param userId 用户id
	 * @return
	 */
	public List<SocialRelationBean> findToUser(String userId) {
		return socialRelationDao.findToUser(userId);
	}
	
	/**
	 * @Description：根据用户ID查询粉丝数量
	 * @Author：刘浩浩
	 * @Date：2017年8月14日
	 * @param userId
	 * @return
	 */
	public int countFansByUserId(String userId) {
		return socialRelationDao.countFansByUserId(userId);
	}

	/**
	 * @Description：根据用户ID查询关注数量
	 * @Author：刘浩浩
	 * @Date：2017年8月14日
	 * @param userId
	 * @return
	 */
	public int countFocusByUserId(String userId) {
		return socialRelationDao.countFocusByUserId(userId);
	}
	
	/**
	 * @Description：根据用户ID， 从用户ID 查询用户关系对象
	 * @Author：刘浩浩
	 * @Date：2017年8月14日
	 * @param userId
	 * @param subUserId
	 * @return
	 */
	public SocialRelation getSocialRelation(String userId, String subUserId){
		return socialRelationDao.getSocialRelation(userId, subUserId);
	}
	
	/**
	 * @Description：是否已关注该用户
	 * @Author：王萌萌
	 * @Date：2017年8月14日
	 * @param userId 当前userid
	 * @param subUserId 被关注userid
	 * @return
	 */
	public int isFocus(String userId, String subUserId) {
		return socialRelationDao.isFocus(userId, subUserId);
	}

	/**
	 * 
	 * @Description：根据用户id设置为屏蔽Ta的发言
	 * @Author：邵德才
	 * @Date：2017年8月22日
	 * @param id
	 */
	@Transactional(readOnly = false)
	public void updateBlack(String id) {
		socialRelationDao.updateBlack(id);
	}
	
}