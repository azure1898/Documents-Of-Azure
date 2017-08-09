package com.its.modules.social.web;

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
import com.its.modules.social.common.SocialGlobal;
import com.its.modules.social.entity.SocialRelation;
import com.its.modules.social.service.SocialRelationService;

import net.sf.json.JSONObject;

/**
 * @Description：个人发言首页 关注/取消关注
 * @Author：王萌萌
 * @Date：2017年8月9日
 */
@Controller
@RequestMapping(value = "${appPath}/focus")
public class SocialFocusController {
	
	@Autowired
	private SocialRelationService socialRelationService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="saveFocus",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map saveFocus(String userId, String subUserId, int type) throws Exception {
		Map<String, Object> toJson = new HashMap<String, Object>();
		if(StringUtils.isEmpty(userId)){
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "当前用户id为空");
			return toJson;
		}
		if(StringUtils.isEmpty(subUserId)){
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "被关注/取消关注用户id为空");
			return toJson;
		}
		if(StringUtils.isEmpty(type)){
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "状态为空");
			return toJson;
		}
		
		//关注，在social_relation表中添加一条记录
		if(type == SocialGlobal.RELATION_IS_FOCUS_YES) {
			SocialRelation socialRelation = new SocialRelation();
			socialRelation.setUserid(userId);
			socialRelation.setSubuserid(subUserId);
			socialRelation.setCreatetime(new Date());
			socialRelation.setIsblack(SocialGlobal.RELATION_IS_BLACK_NO);
			socialRelationService.save(socialRelation);
			toJson.put("code", Global.CODE_SUCCESS);
			toJson.put("message", "关注成功");
		} else {//取消关注，删除social_relation表中对应的记录
			socialRelationService.deleteByUserIdAndSubUserId(userId, subUserId);
			toJson.put("code", Global.CODE_SUCCESS);
			toJson.put("message", "取消关注成功");
		}
		System.out.println("+++++++" + JSONObject.fromObject(toJson).toString());
		return toJson;
	}
	
}
