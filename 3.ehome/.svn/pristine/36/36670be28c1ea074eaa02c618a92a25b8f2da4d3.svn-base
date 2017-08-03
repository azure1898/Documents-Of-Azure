/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.its.common.config.Global;
import com.its.common.utils.StringUtils;
import com.its.common.web.BaseController;
import com.its.modules.app.entity.Feedback;
import com.its.modules.app.service.AccountService;
import com.its.modules.app.service.FeedbackService;

import net.sf.json.JSONObject;

/**
 * 意见反馈Controller
 * 
 * @author like
 * @version 2017-07-17
 */
@Controller
@RequestMapping(value = "${appPath}/my")
public class FeedbackController extends BaseController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private FeedbackService feedbackService;

	@ResponseBody
	@RequestMapping(value = "/submitFeedback")
	public String submitFeedback(String userID, String feedbackContent) throws UnsupportedEncodingException {
		if (StringUtils.isBlank(userID) || StringUtils.isBlank(feedbackContent)) {
			return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"参数有误\"}";
		}
		if (accountService.get(userID) == null) {
			return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"用户不存在\"}";
		}
		String content = StringUtils.isNotBlank(feedbackContent) ? URLDecoder.decode(feedbackContent, "UTF-8") : "";
		Feedback feedback = new Feedback();
		feedback.setAccountId(userID);
		feedback.setContent(content);
		feedbackService.save(feedback);
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("code", Global.CODE_SUCCESS);
		json.put("message", "成功");
		return JSONObject.fromObject(json).toString();
	}
}