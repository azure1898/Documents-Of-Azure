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
import com.its.modules.social.bean.SocialSpeakBean;
import com.its.modules.social.dao.SocialSpeakDao;
import com.its.modules.social.entity.SocialSpeak;

/**
 * 发言管理Service
 * @author wmm
 * @version 2017-08-01
 */
@Service
@Transactional(readOnly = true)
public class SocialSpeakService extends CrudService<SocialSpeakDao, SocialSpeak> {
	
	@Autowired
	private SocialSpeakDao socialSpeakDao;

	public SocialSpeak get(String id) {
		return super.get(id);
	}
	
	public List<SocialSpeak> findList(SocialSpeak socialSpeak) {
		return super.findList(socialSpeak);
	}
	
	public Page<SocialSpeak> findPage(Page<SocialSpeak> page, SocialSpeak socialSpeak) {
		return super.findPage(page, socialSpeak);
	}
	
	@Transactional(readOnly = false)
	public void save(SocialSpeak socialSpeak) {
		super.save(socialSpeak);
	}
	
	@Transactional(readOnly = false)
	public void delete(SocialSpeak socialSpeak) {
		super.delete(socialSpeak);
	}
	
	@Transactional(readOnly = false)
	public int changeDelFlag(SocialSpeak socialSpeak) {
		int resultNum = socialSpeakDao.changeDelFlag(socialSpeak);
		return resultNum;
	}
	
	@Transactional(readOnly = false)
	public int changeTop(SocialSpeak socialSpeak) {
		int resultNum = socialSpeakDao.changeTop(socialSpeak);
		return resultNum;
	}
	
	/**
	 * @Description：根据用户id查询该用户在改楼盘下关注的发言,如果用户id为空，查询当前楼盘所有发言
	 * @Author：刘浩浩
	 * @Date：2017年8月3日
	 * @param userId 用户id 当前用户id
	 * @param socialSpeak 
	 * @param pageIndex 分页页码(从0开始为起始页)
	 * @param pageSize
	 * @return
	 */
	public List<SocialSpeakBean> findListByUserId(String userId, SocialSpeak socialSpeak, int pageIndex, int pageSize){
		return socialSpeakDao.findListByUserId(userId, socialSpeak, pageIndex, pageSize);
	}
	
	/**
	 * @Description：根据发言id 获取发言bean
	 * @Author：刘浩浩
	 * @Date：2017年8月4日
	 * @param id 发言id
	 * @return
	 */
	public SocialSpeakBean getSocialSpeakBeanById(String id) {
		return socialSpeakDao.getSocialSpeakBeanById(id);
	}
	
	/**
	 * @Description：根据条件查询发言/转发集合
	 * @Author：刘浩浩
	 * @Date：2017年8月8日
	 * @param socialSpeak
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<SocialSpeakBean> findSpeakList(SocialSpeak socialSpeak, int pageIndex, int pageSize){
		return socialSpeakDao.findSpeakList(socialSpeak, pageIndex, pageSize);
	}
	
	/**
	 * 
	 * @Description：根据用户id查询该用户的所有发言数据
	 * @Author：邵德才
	 * @Date：2017年8月8日
	 * @param userId
	 * @return
	 */
	public List<SocialSpeak> getListByUserId(String userid) {
		return socialSpeakDao.getListByUserId(userid);
	}
	
	/**
	 * 
	 * @Description：查询转发数量
	 * @Author：邵德才
	 * @Date：2017年8月8日
	 * @param speakid
	 * @return
	 */
	public int countForward(String speakid) {
		return socialSpeakDao.countForward(speakid);
	}
	
	/**
	 * @Description：根据话题名称和楼盘id查询发言
	 * @Author：王萌萌
	 * @Date：2017年8月10日
	 * @param addressId 楼盘id
	 * @param subjectName 话题名称
	 * @return
	 */
	public List<SocialSpeakBean> findByAddIdAndSubName(String addressId, String subjectName, String subjectId, String userId, int pageIndex, int pageSize) {
		return socialSpeakDao.findByAddIdAndSubName(addressId, subjectName, subjectId, userId, pageIndex, pageSize);
	}
	
	/**
	 * @Description：根据发言id查找发言
	 * @Author：王萌萌
	 * @Date：2017年8月8日
	 * @param speakId 发言id
	 * @return
	 */
	public SocialSpeakBean findSpeakBySpeakId(String speakId) {
		return socialSpeakDao.findSpeakBySpeakId(speakId);
	}
	
	/**
	 * @Description：根据用户id 查询该用户的发言数量
	 * @Author：刘浩浩
	 * @Date：2017年8月14日
	 * @param socialSpeak
	 * @return
	 */
	public int countSpeak(SocialSpeak socialSpeak) {
		return socialSpeakDao.countSpeak(socialSpeak);
	}
}