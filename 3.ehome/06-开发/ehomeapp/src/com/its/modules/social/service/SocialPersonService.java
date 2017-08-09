package com.its.modules.social.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.service.CrudService;
import com.its.modules.social.bean.SocialSpeakBean;
import com.its.modules.social.dao.SocialPersonDao;
import com.its.modules.social.entity.SocialSpeak;

/**
 * @Description：个人发言Service
 * @Author：王萌萌
 * @Date：2017年8月8日
 */
@Service
@Transactional(readOnly = true)
public class SocialPersonService extends CrudService<SocialPersonDao, SocialSpeak> {
	
	@Autowired
	private SocialPersonDao socialPersonDao;
	
	/**
	 * @Description：根据subUserId查询bean集合
	 * @Author：王萌萌
	 * @Date：2017年8月8日
	 * @param subUserId
	 * @return
	 */
	public List<SocialSpeakBean> findPersonalInfoList(String subUserId, String userId, int pageIndex, int pageSize) {
		return socialPersonDao.findPersonalInfoList(subUserId, userId, pageIndex, pageSize);
	}

}
