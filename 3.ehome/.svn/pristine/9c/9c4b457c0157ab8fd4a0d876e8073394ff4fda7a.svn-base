/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.social.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.social.bean.SocialForwardBean;
import com.its.modules.social.entity.SocialForward;

/**
 * 转发DAO接口
 * @author wmm
 * @version 2017-08-04
 */
@MyBatisDao
public interface SocialForwardDao extends CrudDao<SocialForward> {
	
	/**
	 * @Description：根据发言ID获取转发bean集合
	 * @Author：刘浩浩
	 * @Date：2017年8月4日
	 * @param socialForward
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<SocialForwardBean> findForwardBeanList(@Param("socialForward") SocialForward socialForward, @Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);
	
}