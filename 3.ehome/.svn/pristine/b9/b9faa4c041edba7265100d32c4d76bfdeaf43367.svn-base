/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.subject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.subject.entity.SocialSubject;
import com.its.modules.subject.dao.SocialSubjectDao;

/**
 * 话题管理Service
 * @author wmm
 * @version 2017-07-31
 */
@Service
@Transactional(readOnly = true)
public class SocialSubjectService extends CrudService<SocialSubjectDao, SocialSubject> {
	
	@Autowired
	private SocialSubjectDao socialSubjectDao;

	public SocialSubject get(String id) {
		return super.get(id);
	}
	
	public List<SocialSubject> findList(SocialSubject socialSubject) {
		return super.findList(socialSubject);
	}
	
	public Page<SocialSubject> findPage(Page<SocialSubject> page, SocialSubject socialSubject) {
		return super.findPage(page, socialSubject);
	}
	
	@Transactional(readOnly = false)
	public void save(SocialSubject socialSubject) {
		super.save(socialSubject);
	}
	
	@Transactional(readOnly = false)
	public void delete(SocialSubject socialSubject) {
		super.delete(socialSubject);
	}
	
	/**
	 * @Description：保存排序
	 * @Author：王萌萌
	 * @Date：2017年8月11日
	 * @param socialSubject
	 * @return
	 */
	@Transactional(readOnly = false)
	public int updateOrderNum(SocialSubject socialSubject) {
		int result = socialSubjectDao.updateSortNum(socialSubject);
		return result;
	}
	
	/**
	 * @Description：是否推荐
	 * @Author：王萌萌
	 * @Date：2017年8月11日
	 * @param socialSubject
	 * @return
	 */
	@Transactional(readOnly = false)
	public int updateRecommend(SocialSubject socialSubject) {
		int resultNum = socialSubjectDao.updateRecommend(socialSubject);
		return resultNum;
	}
	
	/**
	 * @Description：查询所有话题
	 * @Author：王萌萌
	 * @Date：2017年8月11日
	 * @return
	 */
	@Transactional(readOnly = false)
	public List<SocialSubject> findAll() {
		List<SocialSubject> allList =  socialSubjectDao.findAll();
		return allList;
	}
	
	/**
	 * @Description：根据发言id查询话题
	 * @Author：王萌萌
	 * @Date：2017年8月11日
	 * @param speakId
	 * @return
	 */
	public List<SocialSubject> findSubListBySpeakId(String speakId) {
		return socialSubjectDao.findSubListBySpeakId(speakId);
	}

	/**
	 * @Description：根据话题名称查询话题
	 * @Author：王萌萌
	 * @Date：2017年8月23日
	 * @param tag
	 */
	public SocialSubject findByTag(String tag) {
		return socialSubjectDao.findByTag(tag);
	}

}