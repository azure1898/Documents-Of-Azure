package com.its.modules.social.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.social.bean.SocialSpeakBean;
import com.its.modules.social.entity.SocialSpeak;

/**
 * @Description：个人发言Dao接口
 * @Author：王萌萌
 * @Date：2017年8月8日
 */
@MyBatisDao
public interface SocialPersonDao extends CrudDao<SocialSpeak> {
	
	/**
	 * @Description：根据subUserId获取发言bean集合
	 * @Author：王萌萌
	 * @Date：2017年8月8日
	 * @param subUserId
	 * @return
	 */
	public List<SocialSpeakBean> findPersonalInfoList(@Param("subUserId") String subUserId, @Param("userId") String userId, @Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

}
