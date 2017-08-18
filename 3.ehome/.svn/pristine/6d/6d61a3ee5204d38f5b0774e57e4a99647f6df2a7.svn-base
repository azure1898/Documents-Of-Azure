/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.social.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.its.common.config.Global;
import com.its.common.web.BaseController;
import com.its.modules.social.bean.SocialPraiseBean;
import com.its.modules.social.common.DateUtil;
import com.its.modules.social.common.SocialGlobal;
import com.its.modules.social.entity.SocialPraise;
import com.its.modules.social.service.SocialPraiseService;

import net.sf.json.JSONObject;

/**
 * @Description：点赞手机端相关接口
 * @Author：刘浩浩
 * @Date：2017年8月4日
 */
@Controller
@RequestMapping(value = "${appPath}/praise")
public class SocialPraiseController extends BaseController {

	@Autowired
	private SocialPraiseService socialPraisetService;
	
	/**
	 * @Description：详情页面点赞列表
	 * @Author：刘浩浩
	 * @Date：2017年8月4日
	 * @param speakId 发言ID
	 * @param pageIndex 分页页码(从0开始为起始页)
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="praiseList",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String getPraiseList(String speakId, int pageIndex) throws Exception{
		//结果封装
		Map<String, Object> toJson = new HashMap<String, Object>();
		if(StringUtils.isEmpty(speakId)){
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "发言ID为空");
			String result = JSONObject.fromObject(toJson).toString();
			return result;
		}
		
		int pageSize = pageIndex == 0 ? SocialGlobal.PAGE_SIZE_INDEX : SocialGlobal.PAGE_SIZE;
		pageIndex = pageIndex == 0 ? pageSize * pageIndex : pageSize * pageIndex -SocialGlobal.PAGE_SIZE_INDEX;
		SocialPraise socialPraise = new SocialPraise();
		socialPraise.setType(SocialGlobal.PRAISE_TYPE_SPEAK);
		socialPraise.setPid(speakId);
		List<SocialPraiseBean> praiseList = socialPraisetService.findPraiseBeanList(socialPraise, pageIndex, pageSize);
		List<Map> dataList = new ArrayList();
		if(praiseList!=null && praiseList.size()>0){
			for(SocialPraiseBean praiseBean: praiseList){
				Map dataListMap = new HashMap();
				dataListMap.put("userName", praiseBean.getNickName());
				dataListMap.put("headPicSrc", praiseBean.getPhoto());
				
				dataListMap.put("id", praiseBean.getId());
				String createTime = DateUtil.getDaysBeforeNow(praiseBean.getPraisetime());
				dataListMap.put("createTime", createTime);
				
				dataList.add(dataListMap);
			}
		}
		
		toJson.put("speakId", speakId);
		toJson.put("pageIndex", pageIndex);
		toJson.put("data", dataList);
		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("message", "查看赞列表成功");
		
		String result = JSONObject.fromObject(toJson).toString();
		System.out.println("+++++++" + result);
		return result;
	}
	
	
	/**
	 * @Description：点赞/取消点赞
	 * @Author：刘浩浩
	 * @Date：2017年8月7日
	 * @param userId 当前用户id
	 * @param toUserId 被赞人用户id
	 * @param pid 外键id
	 * @param type 类型 1：发言/转发 2：评论
	 * @param state 点赞、取消点赞 标识 1：点赞 0：取消点赞
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="savePraise",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String savePraise(String userId, String toUserId, String pid, int type, int state) throws Exception{
		//结果封装
		Map<String, Object> toJson = new HashMap<String, Object>();
		if(StringUtils.isEmpty(type)){
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "类型为空");
			String result = JSONObject.fromObject(toJson).toString();
			return result;
		}
		if(StringUtils.isEmpty(userId)){
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "当前用户id为空");
			String result = JSONObject.fromObject(toJson).toString();
			return result;
		}
		SocialPraise socialPraise = new SocialPraise();
		socialPraise.setUserid(userId);
		socialPraise.setPid(pid);
		socialPraise.setType(type);
		socialPraise.setTouserid(toUserId);
		if(state==SocialGlobal.PRAISE_STATE_YES){//点赞
			List<SocialPraise> praiseList = socialPraisetService.findList(socialPraise);
			if(praiseList!=null && praiseList.size()>0){//非首次点赞
				socialPraise.setPraisetime(new Date());
				socialPraise.setState(SocialGlobal.PRAISE_STATE_YES);
				socialPraisetService.updateSocialPraise(socialPraise);
			}else{//首次点赞
				socialPraise.setPraisetime(new Date());
				socialPraise.setState(SocialGlobal.PRAISE_STATE_YES);
				socialPraisetService.save(socialPraise);
			}
			toJson.put("code", Global.CODE_SUCCESS);
			toJson.put("message", "点赞成功");
		}else{//取消点赞
			socialPraise.setPraisetime(new Date());
			socialPraise.setState(SocialGlobal.PRAISE_STATE_NO);
			
			socialPraisetService.updateSocialPraise(socialPraise);
			toJson.put("code", Global.CODE_SUCCESS);
			toJson.put("message", "取消赞成功");
		}
		
		String result = JSONObject.fromObject(toJson).toString();
		return result;
	}
	
	
}