/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.praise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.praise.entity.SocialPraise;
import com.its.modules.praise.dao.SocialPraiseDao;

/**
 * 点赞Service
 * @author wmm
 * @version 2017-08-03
 */
@Service
@Transactional(readOnly = true)
public class SocialPraiseService extends CrudService<SocialPraiseDao, SocialPraise> {
	
	@Autowired SocialPraiseDao socialPraiseDao;

	public SocialPraise get(String id) {
		return super.get(id);
	}
	
	public List<SocialPraise> findList(SocialPraise socialPraise) {
		return super.findList(socialPraise);
	}
	
	public Page<SocialPraise> findPage(Page<SocialPraise> page, SocialPraise socialPraise) {
		return super.findPage(page, socialPraise);
	}
	
	@Transactional(readOnly = false)
	public void save(SocialPraise socialPraise) {
		super.save(socialPraise);
	}
	
	@Transactional(readOnly = false)
	public void delete(SocialPraise socialPraise) {
		super.delete(socialPraise);
	}
	
	@Transactional(readOnly = false)
	public List<SocialPraise> findBySpeakId(SocialPraise socialPraise) {
		return socialPraiseDao.findBySpeakId(socialPraise);
	}
	
}