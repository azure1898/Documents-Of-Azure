/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.social.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.social.dao.SocialSubRelationDao;
import com.its.modules.social.entity.SocialSubRelation;

/**
 * 发言和话题关联Service
 * @author wmm
 * @version 2017-08-02
 */
@Service
@Transactional(readOnly = true)
public class SocialSubRelationService extends CrudService<SocialSubRelationDao, SocialSubRelation> {

	public SocialSubRelation get(String id) {
		return super.get(id);
	}
	
	public List<SocialSubRelation> findList(SocialSubRelation socialSubRelation) {
		return super.findList(socialSubRelation);
	}
	
	public Page<SocialSubRelation> findPage(Page<SocialSubRelation> page, SocialSubRelation socialSubRelation) {
		return super.findPage(page, socialSubRelation);
	}
	
	@Transactional(readOnly = false)
	public void save(SocialSubRelation socialSubRelation) {
		super.save(socialSubRelation);
	}
	
	@Transactional(readOnly = false)
	public void delete(SocialSubRelation socialSubRelation) {
		super.delete(socialSubRelation);
	}
	
}