/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.notice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.notice.dao.NoticeManageDao;
import com.its.modules.notice.entity.NoticeManage;

/**
 * 社区公告Service
 * @author ChenXiangyu
 * @version 2017-07-18
 */
@Service
@Transactional(readOnly = true)
public class NoticeManageService extends CrudService<NoticeManageDao, NoticeManage> {

	public NoticeManage get(String id) {
		return super.get(id);
	}
	
	public List<NoticeManage> findList(NoticeManage noticeManage) {
		return super.findList(noticeManage);
	}
	
	public Page<NoticeManage> findPage(Page<NoticeManage> page, NoticeManage noticeManage) {
		return super.findPage(page, noticeManage);
	}
	
	@Transactional(readOnly = false)
	public void save(NoticeManage noticeManage) {
		super.save(noticeManage);
	}
	
	@Transactional(readOnly = false)
	public void delete(NoticeManage noticeManage) {
		super.delete(noticeManage);
	}
	
}