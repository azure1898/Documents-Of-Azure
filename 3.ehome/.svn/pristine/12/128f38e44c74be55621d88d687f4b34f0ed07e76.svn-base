/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.comment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.comment.entity.SocialComment;
import com.its.modules.speak.entity.SocialSpeak;
import com.its.modules.comment.dao.SocialCommentDao;

/**
 * 评论Service
 * @author wmm
 * @version 2017-08-03
 */
@Service
@Transactional(readOnly = true)
public class SocialCommentService extends CrudService<SocialCommentDao, SocialComment> {
	
	@Autowired
	private SocialCommentDao commentDao;

	public SocialComment get(String id) {
		return super.get(id);
	}
	
	public List<SocialComment> findList(SocialComment socialComment) {
		return super.findList(socialComment);
	}
	
	public Page<SocialComment> findPage(Page<SocialComment> page, SocialComment socialComment) {
		return super.findPage(page, socialComment);
	}
	
	@Transactional(readOnly = false)
	public void save(SocialComment socialComment) {
		super.save(socialComment);
	}
	
	@Transactional(readOnly = false)
	public void delete(SocialComment socialComment) {
		super.delete(socialComment);
	}
	
	@Transactional(readOnly = false)
	public List<SocialComment> findBySpeakId(SocialComment socialComment) {
		return commentDao.findBySpeakId(socialComment);
	}
	
	@Transactional(readOnly = false)
	public int changeDelFlag(SocialComment socialComment) {
		return commentDao.changeDelFlag(socialComment);
	}
	
	@Transactional(readOnly = false)
	public List<SocialComment> findChildComment(SocialComment socialComment) {
		return commentDao.findChildComment(socialComment);
	}
	
}