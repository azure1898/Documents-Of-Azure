/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.dao;

import org.apache.ibatis.annotations.Param;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.app.entity.VerifyCodeRecord;

/**
 * 短信验证码发送记录DAO接口
 * 
 * @author like
 * @version 2017-07-21
 */
@MyBatisDao
public interface VerifyCodeRecordDao extends CrudDao<VerifyCodeRecord> {

	/**
	 * 验证时查询验证码
	 * @param phoneNum	手机号
	 * @param code	验证码
	 * @param codeType	
	 * @return
	 */
	public VerifyCodeRecord getLatestCode(@Param("phoneNum") String phoneNum, @Param("code") String code, @Param("codeType") String codeType);
	/**
	 * 查询手机号当天已发送验证码的次数
	 * @param phoneNum
	 * @param codeType
	 * @return
	 */
	public int getTodaySendNum(@Param("phoneNum") String phoneNum,@Param("codeType") String codeType);
}