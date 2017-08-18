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
import com.its.modules.social.dao.SocialSubjectDao;
import com.its.modules.social.entity.SocialSubject;

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
	
	@Transactional(readOnly = false)
	public int updateOrderNum(SocialSubject socialSubject) {
		int result = socialSubjectDao.updateSortNum(socialSubject);
		return result;
	}
	
	@Transactional(readOnly = false)
	public int updateRecommend(SocialSubject socialSubject) {
		int resultNum = socialSubjectDao.updateRecommend(socialSubject);
		return resultNum;
	}
	
	@Transactional(readOnly = false)
	public List<SocialSubject> findAll() {
		List<SocialSubject> allList =  socialSubjectDao.findAll();
		return allList;
	}
	
	/**
	 * @Description：根据发言ID 查询所有话题
	 * @Author：刘浩浩
	 * @Date：2017年8月4日
	 * @param fkId 外键ID
	 * @param type 类型
	 * @return
	 */
	@Transactional(readOnly = false)
	public List<SocialSubject> findAllByfkId(String fkId, int type){
		List<SocialSubject> allList =  socialSubjectDao.findAllByfkId(fkId, type+ "");
		return allList;
	}
	
	/**
	 * @Description：根据话题名称查询话题列表
	 * @Author：王萌萌
	 * @Date：2017年8月10日
	 * @param subjectName
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<SocialSubject> findBySubName(String subjectName, int pageIndex, int pageSize) {
		return socialSubjectDao.findBySubName(subjectName, pageIndex, pageSize);
	}
	
	/**
	 * @Description：根据话题名称查询话题是否存在
	 * @Author：王萌萌
	 * @Date：2017年8月11日
	 * @param subjectName 话题名称
	 * @return
	 */
	public int findSocialSubjectBySubName(String subjectName) {
		return socialSubjectDao.findSocialSubjectBySubName(subjectName);
	}
	
	/**
	 * @Description：查询所有热门话题
	 * @Author：王萌萌
	 * @Date：2017年8月15日
	 * @param isRecommend 是否推荐
	 * @return
	 */
	public List<SocialSubject> findHotSubjectList(int isRecommend) {
		return socialSubjectDao.findHotSubjectList(isRecommend);
	}

}