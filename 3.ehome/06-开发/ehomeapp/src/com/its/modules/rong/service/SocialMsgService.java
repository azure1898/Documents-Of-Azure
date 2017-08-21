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
import com.its.modules.rong.dao.SocialMsgDao;
import com.its.modules.rong.entity.SocialMsg;

/**
 * 消息表Service
 * @author 刘浩浩
 * @version 2017-08-11
 */
@Service
@Transactional(readOnly = true)
public class SocialMsgService extends CrudService<SocialMsgDao, SocialMsg> {

	@Autowired
	private SocialMsgDao socialMsgDao;
	
	public SocialMsg get(String id) {
		return super.get(id);
	}
	
	public List<SocialMsg> findList(SocialMsg socialMsg) {
		return super.findList(socialMsg);
	}
	
	public Page<SocialMsg> findPage(Page<SocialMsg> page, SocialMsg socialMsg) {
		return super.findPage(page, socialMsg);
	}
	
	@Transactional(readOnly = false)
	public void save(SocialMsg socialMsg) {
		super.save(socialMsg);
	}
	
	@Transactional(readOnly = false)
	public void delete(SocialMsg socialMsg) {
		super.delete(socialMsg);
	}
	
	/**
	 * @Description：查询未读消息的数量
	 * @Author：王萌萌
	 * @Date：2017年8月17日
	 * @param toUserId 用户id
	 * @param secType 消息类型
	 * @return
	 */
	public int countUnRead(String toUserId, Integer secType, Integer isRead, Integer firType) {
		return socialMsgDao.countUnRead(toUserId, secType, isRead, firType);
	}
	
	/**
	 * @Description：根据条件查询消息集合
	 * @Author：刘浩浩
	 * @Date：2017年8月19日
	 * @param socialMsg
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<SocialMsg> findList(SocialMsg socialMsg, int pageIndex, int pageSize){
		return socialMsgDao.findListByPage(socialMsg, pageIndex, pageSize);
	}
}