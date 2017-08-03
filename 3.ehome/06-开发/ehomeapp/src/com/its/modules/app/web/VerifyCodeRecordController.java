/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.its.common.config.Global;
import com.its.common.utils.StringUtils;
import com.its.common.web.BaseController;
import com.its.modules.app.common.AppGlobal;
import com.its.modules.app.common.MessageUtilsJson;
import com.its.modules.app.entity.Account;
import com.its.modules.app.entity.VerifyCodeRecord;
import com.its.modules.app.service.AccountService;
import com.its.modules.app.service.VerifyCodeRecordService;

import net.sf.json.JSONObject;

/**
 * 短信验证码发送记录Controller
 * 
 * @author like
 * @version 2017-07-21
 */
@Controller
@RequestMapping(value = "${appPath}/account")
public class VerifyCodeRecordController extends BaseController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private VerifyCodeRecordService verifyCodeRecordService;

	/**
	 * 快速登录发送验证码
	 * 
	 * @param phone
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "sendQuickVerifyCode")
	public String sendQuickVerifyCode(HttpServletRequest request, String phone) {
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			if (!StringUtils.checkPhoneNum(phone)) {
				json.put("code", Global.CODE_PROMOT);
				json.put("message", "请输入正确手机号");
				return JSONObject.fromObject(json).toString();
			}
			Account account = accountService.getByPhoneNum(phone);
			if (account == null) {
				json.put("code", Global.CODE_PROMOT);
				json.put("message", "此手机号未注册");
				return JSONObject.fromObject(json).toString();
			}
			if (verifyCodeRecordService.getTodaySendNum(phone, AppGlobal.VERIFY_CODE_TYPE_lOGIN) >= AppGlobal.VERIFY_CODE_DAY_LIMIT) {
				json.put("code", Global.CODE_PROMOT);
				json.put("message", "发送次数已达日上限(" + AppGlobal.VERIFY_CODE_DAY_LIMIT + "次)");
				return JSONObject.fromObject(json).toString();
			}
			// String code = StringUtils.generateValidCode(4);
			String code = "1234";
			// 发送验证码
			int result = MessageUtilsJson.sendMsg(phone, code);
			// int result = 0;
			if (result == 0) {// 保存验证码到数据库
				VerifyCodeRecord record = new VerifyCodeRecord();
				record.setPhoneNum(phone);
				record.setCode(code);
				record.setSendTime(new Date());
				record.setCodeType(AppGlobal.VERIFY_CODE_TYPE_lOGIN);
				verifyCodeRecordService.save(record);
				json.put("code", Global.CODE_SUCCESS);
				json.put("message", "验证码已发送");
				return JSONObject.fromObject(json).toString();
			} else {
				json.put("code", Global.CODE_PROMOT);
				json.put("message", "发送失败");
				return JSONObject.fromObject(json).toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (Global.isDebug()) {
				json.put("code", Global.CODE_ERROR);
				json.put("message", e.getMessage());
				return JSONObject.fromObject(json).toString();
			}
			json.put("code", Global.CODE_ERROR);
			json.put("message", "系统错误");
			return JSONObject.fromObject(json).toString();
		}
	}

	/**
	 * 注册发送验证码
	 * 
	 * @param phone
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "sendRegisterVerifyCode")
	public String sendRegisterVerifyCode(HttpServletRequest request, String phone) {
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			if (StringUtils.isBlank(phone) || !StringUtils.checkPhoneNum(phone)) {
				json.put("code", Global.CODE_PROMOT);
				json.put("message", "请输入正确手机号");
				return JSONObject.fromObject(json).toString();
			}
			Account account = accountService.getByPhoneNum(phone);
			if (account != null) {
				json.put("code", Global.CODE_PROMOT);
				json.put("message", "此手机号已注册");
				return JSONObject.fromObject(json).toString();
			}
			if (verifyCodeRecordService.getTodaySendNum(phone, AppGlobal.VERIFY_CODE_TYPE_REGISTER) >= AppGlobal.VERIFY_CODE_DAY_LIMIT) {
				json.put("code", Global.CODE_PROMOT);
				json.put("message", "发送次数已达日上限(" + AppGlobal.VERIFY_CODE_DAY_LIMIT + "次)");
				return JSONObject.fromObject(json).toString();
			}
			// String code = StringUtils.generateValidCode(4);
			String code = "1234";
			// 发送验证码
			int result = MessageUtilsJson.sendMsg(phone, code);
			// int result = 0;
			if (result == 0) {
				VerifyCodeRecord record = new VerifyCodeRecord();
				record.setPhoneNum(phone);
				record.setCode(code);
				record.setSendTime(new Date());
				record.setCodeType(AppGlobal.VERIFY_CODE_TYPE_REGISTER);
				verifyCodeRecordService.save(record);
				json.put("code", Global.CODE_SUCCESS);
				json.put("message", "验证码已发送");
				return JSONObject.fromObject(json).toString();
			} else {
				json.put("code", Global.CODE_PROMOT);
				json.put("message", "发送失败");
				return JSONObject.fromObject(json).toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (Global.isDebug()) {
				json.put("code", Global.CODE_ERROR);
				json.put("message", e.getMessage());
				return JSONObject.fromObject(json).toString();
			}
			json.put("code", Global.CODE_ERROR);
			json.put("message", "系统错误");
			return JSONObject.fromObject(json).toString();
		}
	}

	/**
	 * 忘记密码发送验证码
	 * 
	 * @param phone
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "sendForgetVerifyCode")
	public String sendForgetVerifyCode(HttpServletRequest request, String phone) {
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			if (StringUtils.isBlank(phone) || !StringUtils.checkPhoneNum(phone)) {
				json.put("code", Global.CODE_PROMOT);
				json.put("message", "请输入正确手机号");
				return JSONObject.fromObject(json).toString();
			}
			Account account = accountService.getByPhoneNum(phone);
			if (account == null) {
				json.put("code", Global.CODE_PROMOT);
				json.put("message", "此手机号未注册");
				return JSONObject.fromObject(json).toString();
			}
			if (verifyCodeRecordService.getTodaySendNum(phone, AppGlobal.VERIFY_CODE_TYPE_REGISTER) >= AppGlobal.VERIFY_CODE_DAY_LIMIT) {
				json.put("code", Global.CODE_PROMOT);
				json.put("message", "发送次数已达日上限(" + AppGlobal.VERIFY_CODE_DAY_LIMIT + "次)");
				return JSONObject.fromObject(json).toString();
			}
			// String code = StringUtils.generateValidCode(4);
			String code = "1234";
			// 发送验证码
			int result = MessageUtilsJson.sendMsg(phone, code);
			// int result = 0;
			if (result == 0) {
				VerifyCodeRecord record = new VerifyCodeRecord();
				record.setPhoneNum(phone);
				record.setCode(code);
				record.setSendTime(new Date());
				record.setCodeType(AppGlobal.VERIFY_CODE_TYPE_FORGET);
				verifyCodeRecordService.save(record);
				json.put("code", Global.CODE_PROMOT);
				json.put("message", "验证码已发送");
				return JSONObject.fromObject(json).toString();
			} else {
				json.put("code", Global.CODE_PROMOT);
				json.put("message", "发送失败");
				return JSONObject.fromObject(json).toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (Global.isDebug()) {
				json.put("code", Global.CODE_ERROR);
				json.put("message", e.getMessage());
				return JSONObject.fromObject(json).toString();
			}
			json.put("code", Global.CODE_ERROR);
			json.put("message", "系统错误");
			return JSONObject.fromObject(json).toString();
		}
	}

}