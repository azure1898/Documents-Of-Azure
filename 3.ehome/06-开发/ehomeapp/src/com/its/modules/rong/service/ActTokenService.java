/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.rong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.rong.dao.ActTokenDao;
import com.its.modules.rong.entity.ActToken;
import com.its.modules.social.dao.SocialCommentDao;

/**
 * rongService
 * @author 刘浩浩
 * @version 2017-08-09
 */
@Service
@Transactional(readOnly = true)
public class ActTokenService extends CrudService<ActTokenDao, ActToken> {

	@Autowired
	private ActTokenDao actTokenDao;
	
	public ActToken get(String id) {
		return super.get(id);
	}
	
	public List<ActToken> findList(ActToken actToken) {
		return super.findList(actToken);
	}
	
	public Page<ActToken> findPage(Page<ActToken> page, ActToken actToken) {
		return super.findPage(page, actToken);
	}
	
	@Transactional(readOnly = false)
	public void save(ActToken actToken) {
		super.save(actToken);
	}
	
	@Transactional(readOnly = false)
	public void delete(ActToken actToken) {
		super.delete(actToken);
	}
	
	/**
	 * @Description：根据用户ID查询该用户的token对象
	 * @Author：刘浩浩
	 * @Date：2017年8月9日
	 * @param actToken
	 * @return
	 */
	public ActToken getActToken(String accountId) {
		return actTokenDao.getActToken(accountId);
	}
	
}