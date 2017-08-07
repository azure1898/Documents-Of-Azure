/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.social.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.its.common.config.Global;
import com.its.common.web.BaseController;
import com.its.modules.social.bean.SocialCommentBean;
import com.its.modules.social.bean.SocialForwardBean;
import com.its.modules.social.bean.SocialSpeakBean;
import com.its.modules.social.common.DateUtil;
import com.its.modules.social.common.SocialGlobal;
import com.its.modules.social.entity.SocialComment;
import com.its.modules.social.entity.SocialForward;
import com.its.modules.social.entity.SocialSpeak;
import com.its.modules.social.entity.SocialSubject;
import com.its.modules.social.service.SocialCommentService;
import com.its.modules.social.service.SocialForwardService;
import com.its.modules.social.service.SocialSpeakService;
import com.its.modules.social.service.SocialSubjectService;

import net.sf.json.JSONObject;

/**
 * @Description：发言手机端相关接口
 * @Author：刘浩浩
 * @Date：2017年8月4日
 */
@Controller
@RequestMapping(value = "${appPath}/forward")
public class SocialForwardController extends BaseController {

	@Autowired
	private SocialSpeakService socialSpeakService;
	
	@Autowired
	private SocialForwardService socialForwardService;
	
	@Autowired
	private SocialSubjectService socialSubjectService;

	@Autowired
	private SocialCommentService socialCommentService;
	
	
	
	/**
	 * @Description：详情页面转发列表
	 * @Author：刘浩浩
	 * @Date：2017年8月4日
	 * @param speakId 发言ID
	 * @param pageIndex  分页页码(从0开始为起始页)
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="forwardList",method = {RequestMethod.POST,RequestMethod.GET})
	public String getForwardList(String speakId, int pageIndex) throws Exception{
		//结果封装
		Map<String, Object> toJson = new HashMap<String, Object>();
		if(StringUtils.isEmpty(speakId)){
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "发言ID为空");
			String result = JSONObject.fromObject(toJson).toString();
			return result;
		}
		
		int pageSize = SocialGlobal.FORWARD_PAGE_SIZE;
		SocialForward socialForward = new SocialForward();
		socialForward.setDelflag(SocialGlobal.FORWARD_DEL_FLAG_NO);
		socialForward.setSpeakid(speakId);
		List<SocialForwardBean> fbList = socialForwardService.findForwardBeanList(socialForward, pageSize * pageIndex, pageSize);
		List<Map> dataList = new ArrayList();
		if(fbList!=null && fbList.size()>0){
			for(SocialForwardBean fb: fbList){
				Map dataListMap = new HashMap();
				dataListMap.put("userName", fb.getNickName());
				dataListMap.put("headPicSrc", fb.getPhoto());
				
				dataListMap.put("cmtId", fb.getId());
				String createTime = DateUtil.getDaysBeforeNow(fb.getCreatetime());
				dataListMap.put("createTime", createTime);
				dataListMap.put("reason", fb.getReason());
				
				//根据ID查询话题集合
				List<SocialSubject> subjectList = socialSubjectService.findAllByfkId(fb.getId(), SocialGlobal.SUB_RELATION_FRD);
				List<Map> resultList = new ArrayList();
				Map<String, Object> subMap = new HashMap<String, Object>();
				if(subjectList!=null && subjectList.size()>0){
					for(SocialSubject sub : subjectList){
						subMap.put("subjectId", sub.getId());
						subMap.put("subName", sub.getSubname());
						resultList.add(subMap);
					}
				}
				dataListMap.put("subjectList", resultList);
				dataList.add(dataListMap);
			}
		}
		
		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("message", "查看转发列表成功");
		toJson.put("pageIndex", pageIndex);
		toJson.put("data", dataList);
		String result = JSONObject.fromObject(toJson).toString();
		System.out.println("+++++++" + result);
		return result;
	}
	
	/**
	 * @Description：详情页面评论列表
	 * @Author：刘浩浩
	 * @Date：2017年8月4日
	 * @param userId 当前用户ID
	 * @param speakId 发言ID
	 * @param pageIndex 分页页码(从0开始为起始页)
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="commentList",method = {RequestMethod.POST,RequestMethod.GET})
	public String getCommontList(String userId, String speakId, int pageIndex) throws Exception{
		//结果封装
		Map<String, Object> toJson = new HashMap<String, Object>();
		if(StringUtils.isEmpty(speakId)){
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "发言ID为空");
			String result = JSONObject.fromObject(toJson).toString();
			return result;
		}
		
		int pageSize = SocialGlobal.COMMENT_PAGE_SIZE;
		SocialComment socialComment = new SocialComment();
		socialComment.setDelflag(SocialGlobal.COMMENT_DEL_FLAG_NO);
		socialComment.setSpeakid(speakId);
		List<SocialCommentBean> cmtList = socialCommentService.findCommentBeanList(userId, socialComment, pageSize * pageIndex, pageSize);
		List<Map> dataList = new ArrayList();
		if(cmtList!=null && cmtList.size()>0){
			for(SocialCommentBean cb: cmtList){
				Map dataListMap = new HashMap();
				dataListMap.put("userName", cb.getNickName());
				dataListMap.put("headPicSrc", cb.getPhoto());
				
				dataListMap.put("cmtId", cb.getId());
				String createTime = DateUtil.getDaysBeforeNow(cb.getCreatetime());
				dataListMap.put("createTime", createTime);
				dataListMap.put("content", cb.getContent());
				
				dataListMap.put("isPraise", cb.getIsPraise());//是否点赞
				dataListMap.put("countPraise", cb.getCountPraise());//赞的数量
				
				//根据ID查询话题集合
				List<SocialSubject> subjectList = socialSubjectService.findAllByfkId(cb.getId(), SocialGlobal.SUB_RELATION_CMT);
				List<Map> resultList = new ArrayList();
				Map<String, Object> subMap = new HashMap<String, Object>();
				if(subjectList!=null && subjectList.size()>0){
					for(SocialSubject sub : subjectList){
						subMap.put("subjectId", sub.getId());
						subMap.put("subName", sub.getSubname());
						resultList.add(subMap);
					}
				}
				dataListMap.put("subjectList", resultList);
				
				int secCountCmt = socialCommentService.getSecCmtCount(cb.getId());
				dataListMap.put("secCountCmt", secCountCmt);//二级评论数量
				
				dataList.add(dataListMap);
			}
		}
		
		toJson.put("userId", userId);
		toJson.put("speakId", speakId);
		toJson.put("pageIndex", pageIndex);
		toJson.put("data", dataList);
		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("message", "查看评论列表成功");
		
		String result = JSONObject.fromObject(toJson).toString();
		System.out.println("+++++++" + result);
		return result;
	}
	
	
}