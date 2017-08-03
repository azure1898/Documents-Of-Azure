/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.common.utils.StringUtils;
import com.its.modules.app.common.AppGlobal;
import com.its.modules.app.dao.VerifyCodeRecordDao;
import com.its.modules.app.entity.VerifyCodeRecord;

/**
 * 短信验证码发送记录Service
 * 
 * @author like
 * @version 2017-07-21
 */
@Service
@Transactional(readOnly = true)
public class VerifyCodeRecordService extends CrudService<VerifyCodeRecordDao, VerifyCodeRecord> {

	public VerifyCodeRecord get(String id) {
		return super.get(id);
	}

	public List<VerifyCodeRecord> findList(VerifyCodeRecord verifyCodeRecord) {
		return super.findList(verifyCodeRecord);
	}

	public Page<VerifyCodeRecord> findPage(Page<VerifyCodeRecord> page, VerifyCodeRecord verifyCodeRecord) {
		return super.findPage(page, verifyCodeRecord);
	}

	@Transactional(readOnly = false)
	public void save(VerifyCodeRecord verifyCodeRecord) {
		super.save(verifyCodeRecord);
	}

	@Transactional(readOnly = false)
	public void delete(VerifyCodeRecord verifyCodeRecord) {
		super.delete(verifyCodeRecord);
	}

	/**
	 * 判断验证码是否正确
	 * 
	 * @param phoneNum
	 *            手机号
	 * @param code
	 *            验证码
	 * @param codeType
	 *            验证码类型：1快速登录；2注册；3忘记密码
	 * @return
	 */
	public boolean checkVerifyCode(String phoneNum, String code, String codeType) {
		if (StringUtils.isBlank(phoneNum) || StringUtils.isBlank(code) || StringUtils.isBlank(codeType)) {
			return false;
		}
		VerifyCodeRecord record = dao.getLatestCode(phoneNum, code, codeType);
		if (record == null || record.getSendTime() == null) {
			return false;
		}
		if ((new Date().getTime() - record.getSendTime().getTime()) / (1000 * 60) > AppGlobal.VERIFY_CODE_VALID_MINUTE) {
			return false;
		}
		return true;
	}

	/**
	 * 查询手机号当天已发送验证码的次数
	 * 
	 * @param phoneNum
	 * @param codeType
	 * @return
	 */
	public int getTodaySendNum(String phoneNum, String codeType) {
		return dao.getTodaySendNum(phoneNum, codeType);
	}
}