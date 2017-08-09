/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.rong.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.its.common.config.Global;
import com.its.common.web.BaseController;
import com.its.modules.rong.common.RongGlobal;
import com.its.modules.rong.entity.ActToken;
import com.its.modules.rong.service.ActTokenService;

import io.rong.RongCloud;
import io.rong.models.TokenResult;
import net.sf.json.JSONObject;

/**
 * @Description：融云相关接口
 * @Author：刘浩浩
 * @Date：2017年8月4日
 */
@Controller
@RequestMapping(value = "${appPath}/rongCloud")
public class RongCloudController extends BaseController {

	@Autowired
	private ActTokenService actTokenService;
	
	/**
	 * @Description：根据用户ID、用户姓名、头像uri 获取token
	 * @Author：刘浩浩
	 * @Date：2017年8月9日
	 * @param userId 用户 Id，最大长度 64 字节.是用户在 App 中的唯一标识码，必须保证在同一个 App 内不重复，重复的用户 Id 将被当作是同一用户。（必传）
	 * @param userName 用户名称，最大长度 128 字节.用来在 Push 推送时显示用户的名称.用户名称，最大长度 128 字节.用来在 Push 推送时显示用户的名称。（必传）
	 * @param portraitUri 用户头像 URI，最大长度 1024 字节.用来在 Push 推送时显示用户的头像。（必传）
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getToken",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String getToken(String userId, String userName, String portraitUri) throws Exception{
		Map<String, Object> toJson = new HashMap<String, Object>();
		if(StringUtils.isEmpty(userId)){
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "userId为空");
			String result = JSONObject.fromObject(toJson).toString();
			return result;
		}
		if(StringUtils.isEmpty(userName)){
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "userName为空");
			String result = JSONObject.fromObject(toJson).toString();
			return result;
		}
		if(StringUtils.isEmpty(portraitUri)){
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "用户头像为空");
			String result = JSONObject.fromObject(toJson).toString();
			return result;
		}
		
		String result = "";
		//根据用户ID查询该用户是否有token
		ActToken actToken = actTokenService.getActToken(userId);
		if(actToken==null){//需要新增
			RongCloud rongCloud = RongCloud.getInstance(RongGlobal.APP_KEY, RongGlobal.APP_SECRET);
			TokenResult userGetTokenResult = rongCloud.user.getToken(userId, userName, portraitUri);
			result = userGetTokenResult.toString();
			String token = userGetTokenResult.getToken();
			actToken = new ActToken();
			actToken.setToken(token);
			actToken.setAccountid(userId);
			actToken.setCreatetime(new Date());
			actToken.setTokencreatetime(new Date());
			actTokenService.save(actToken);
		}else{
			//获取token 返回
			toJson.put("code", "200");
			toJson.put("token", actToken.getToken());
			toJson.put("userId", userId);
			result = JSONObject.fromObject(toJson).toString();
		}
		
		
		return result;
	}

}