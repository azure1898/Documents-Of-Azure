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

	//  -----二期【邻里圈-发言管理】上线时，放开该代码 START-------
//	/** 发言管理 */
//	@Autowired
//	private SocialSpeakService socialSpeakService;
//	@Autowired
//	private AccountService accountService;
//	
//	/** 发言可见范围：公开 */
//	private static final String VISRANGE_PUBLIC = "1";
//	/** 否 */
//	private static final String YESORNO_NO = "0";
//	/** 数据状态：正常 */
//	private static final String DELFLAG_NORMAL = "1";
	//  -----二期【邻里圈-发言管理】上线时，放开该代码 END-------
	
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
		//  -----二期【邻里圈-发言管理】上线时，放开该代码 START-------
//		boolean isNewRecord = noticeManage.getIsNewRecord();
		//  -----二期【邻里圈-发言管理】上线时，放开该代码 END-------
		
		// 保存到社区公告表
		super.save(noticeManage);
		
		//  -----二期【邻里圈-发言管理】上线时，放开该代码 START-------
//		// 同步添加到发言表
//		if (isNewRecord) {
//			SocialSpeak socialSpeak = new SocialSpeak();
//			// 通过当前登录的帐户获取其绑定的APP用户
//			Account account = new Account();
//			if (StringUtils.isNotBlank(UserUtils.getUser().getAppUserPhone())) {
//				account.setPhoneNum(UserUtils.getUser().getAppUserPhone());
//				List<Account> accountList = accountService.findList(account);
//				if (accountList != null && !accountList.isEmpty() && accountList.get(0) != null) {
//					socialSpeak.setUserid(accountList.get(0).getId());
//				}
//			}
//			socialSpeak.setVisrange(VISRANGE_PUBLIC);
//			socialSpeak.setForbitcomment(YESORNO_NO);
//			socialSpeak.setForbidforward(YESORNO_NO);
//			socialSpeak.setCreatetime(new Date());
//			socialSpeak.setIstop(YESORNO_NO);
//			socialSpeak.setPlateid("1");
//			socialSpeak.setVillageinfoid(noticeManage.getVillageInfoId());
//			socialSpeak.setDelflag(DELFLAG_NORMAL);
//			socialSpeak.setIsspeak(1);
//			socialSpeak.setNoticeid(noticeManage.getId());
//			socialSpeak.setTitle(noticeManage.getNoticeTitle());
//			socialSpeak.setSummary(noticeManage.getNoticeAbstract());
//			socialSpeak.setRemarks(noticeManage.getRemarks());
//			// 处理内容及图片
//			socialSpeak.setContent(noticeManage.getNoticeContent());
////			socialSpeak.setImages(images);
//			socialSpeakService.save(socialSpeak);
//		}
		//  -----二期【邻里圈-发言管理】上线时，放开该代码 END-------
	}
	
	@Transactional(readOnly = false)
	public void delete(NoticeManage noticeManage) {
		super.delete(noticeManage);
	}
	
}