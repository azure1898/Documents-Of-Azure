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
import com.its.modules.social.bean.SocialRelationBean;
import com.its.modules.social.bean.SocialSpeakBean;
import com.its.modules.social.common.SocialGlobal;
import com.its.modules.social.common.SortUtil;
import com.its.modules.social.entity.SocialComment;
import com.its.modules.social.entity.SocialSpeak;
import com.its.modules.social.entity.SocialSubject;
import com.its.modules.social.entity.SocialTips;
import com.its.modules.social.service.SocialCommentService;
import com.its.modules.social.service.SocialRelationService;
import com.its.modules.social.service.SocialSpeakService;
import com.its.modules.social.service.SocialSubjectService;
import com.its.modules.social.service.SocialTipsService;

/**
 * @Description：转发相关接口
 * @Author：王萌萌
 * @Date：2017年8月11日
 */
@Controller
@RequestMapping(value = "${appPath}/forward")
public class SocialForwardController extends BaseController {
	
	@Autowired
	private SocialSpeakService socialSpeakService;
	
	@Autowired
	private SocialSubjectService socialSubjectService;
	
	@Autowired
	private SocialRelationService socialRelationService;
	
	@Autowired
	private SocialTipsService socialTipsService;
	
	@Autowired
	private SocialCommentService socialCommentService;
	
	/**
	 * @Description：转发信息查询
	 * @Author：王萌萌
	 * @Date：2017年8月11日
	 * @param speakId 发言id
	 * @return
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@RequestMapping(value="toForward",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map toForward(String speakId) {
		Map toJson = new HashMap();
		if(StringUtils.isEmpty(speakId)) {
			toJson.put("code", Global.CODE_ERROR);
			toJson.put("message", "转发发言id为空");
			return toJson;
		}
		SocialSpeakBean socialSpeakBean = socialSpeakService.findSpeakBySpeakId(speakId);
		if(StringUtils.isEmpty(socialSpeakBean)) {
			toJson.put("code", Global.CODE_ERROR);
			toJson.put("message", "转发发言不存在");
			return toJson;
		}
		toJson.put("speakId", speakId);
		toJson.put("noticeId", socialSpeakBean.getNoticeid());
		toJson.put("userName", socialSpeakBean.getNickName());
		toJson.put("speakContent", socialSpeakBean.getContent());
		toJson.put("title", socialSpeakBean.getTitle());
		toJson.put("summary", socialSpeakBean.getSummary());
		String images = socialSpeakBean.getImages();
		if(!StringUtils.isEmpty(images)) {
			String[] image = images.split(",");
			String imgUrl = image[0];
			toJson.put("imgUrl", imgUrl);
		} else {
			toJson.put("imgUrl", "");
		}
		
		List<SocialSubject> ssList = socialSubjectService.findAllByfkId(speakId, SocialGlobal.SUB_RELATION_SPK);
		if(!StringUtils.isEmpty(ssList)) {
			List subjectList = new ArrayList();
			for (SocialSubject ss : ssList) {
				Map subjectListMap = new HashMap();
				subjectListMap.put("id", ss.getId());
				subjectListMap.put("subName", ss.getSubname());
				subjectList.add(subjectListMap);
			}
			toJson.put("subjectList", subjectList);
		}
		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("message", "转发信息查询成功");
		return toJson;
	}
	
	/**
	 * @Description：@用户
	 * @Author：王萌萌
	 * @Date：2017年8月14日
	 * @param userId @用户的用户ID
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@RequestMapping(value="toUser",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map toUser(String userId) throws Exception {
		Map toJson = new HashMap();
		if(StringUtils.isEmpty(userId)) {
			toJson.put("code", Global.CODE_ERROR);
			toJson.put("message", "用户id为空");
			return toJson;
		}
		List dataList = new ArrayList();
		List<SocialRelationBean> ssbList = socialRelationService.findToUser(userId);
		if(!StringUtils.isEmpty(ssbList)) {
			for(SocialRelationBean socialRelationBean : ssbList) {
				Map<String, Object> dataListMap = new HashMap<String, Object>();
				String friendId = socialRelationBean.getSubuserid();
				String friendName = socialRelationBean.getNickName();
				String headPicSrc = socialRelationBean.getPhoto();
				dataListMap.put("friendId", friendId);
				dataListMap.put("friendName", friendName);
				dataListMap.put("headPicSrc", headPicSrc);
				dataList.add(dataListMap);
			}
		} else {
			Map dataListMap = new HashMap();
			dataListMap.put("friendId", "");
			dataListMap.put("friendName", "");
			dataListMap.put("headPicSrc", "");
			dataList.add(dataListMap);
		}
		//对数据进行格式化，按首个汉字拼音的首个字母，进行分组，并排序
		List data = new SortUtil().sort(dataList);
		toJson.put("data", data);
		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("message", "查询朋友用户成功");
		return toJson;
	}
	
	/**
	 * @Description：保存转发
	 * @Author：王萌萌
	 * @Date：2017年8月14日
	 * @param speakId 原发言id
	 * @param userId 当前用户id
	 * @param reason 转发原因
	 * @param isComment 是否同时评论
	 * @param visible 可见范围
	 * @param imageUrl 图片地址
	 * @param toUserId @用户id
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@RequestMapping(value="saveForward",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map saveForward(String speakId, String userId, String reason, int isComment, int visible, String imageUrl, String toUserId) throws Exception {
		Map toJson = new HashMap();
		if(StringUtils.isEmpty(speakId)) {
			toJson.put("code", Global.CODE_ERROR);
			toJson.put("message", "发言id为空");
			return toJson;
		}
		if(StringUtils.isEmpty(visible)) {
			toJson.put("code", Global.CODE_ERROR);
			toJson.put("message", "可见范围为空");
			return toJson;
		}
		Date date = new Date();
		//获取原发言相关信息
		SocialSpeak ss = socialSpeakService.get(speakId);
		
		SocialSpeak socialSpeak = new SocialSpeak();
		socialSpeak.setUserid(userId);
		socialSpeak.setReason(reason);
		socialSpeak.setVisrange(visible);
		socialSpeak.setIstop(SocialGlobal.SPEAK_IS_TOP_NO);
		socialSpeak.setCreatetime(date);
		socialSpeak.setImages(imageUrl);
		socialSpeak.setDelflag(SocialGlobal.SPEAK_DEL_FLAG_NO);
		socialSpeak.setFid(speakId);
		//判断原发言是否为转发别的发言，如果是转发，那么rootid为原发言记录的rootid，如果不是转发，那么rootid为原发言id
		if("".equals(ss.getRootid()) || ss.getRootid() == null) {
			socialSpeak.setRootid(speakId);
		} else {
			socialSpeak.setRootid(ss.getRootid());
		}
		socialSpeak.setIsspeak(SocialGlobal.SPEAK_IS_SPEAK_NO);
		socialSpeakService.save(socialSpeak);
		//判断当前转发有没有@好友
		if(!StringUtils.isEmpty(toUserId)) {
			String[] toUserIds = toUserId.split(",");
			for(String id : toUserIds) {
				SocialTips socialTips = new SocialTips();
				socialTips.setUserid(id);
				socialTips.setType(SocialGlobal.TIPS_TYPE_FORWARD);
				socialTips.setFkid(socialSpeak.getId());
				socialTips.setIsnotice(SocialGlobal.TIPS_IS_NOTICE_NO);
				socialTips.setIsread(SocialGlobal.TIPS_IS_READ_NO);
				socialTipsService.save(socialTips);
			}
		}
		//当前转发是否同时评论
		if(!StringUtils.isEmpty(isComment) && isComment == SocialGlobal.SPEAK_IS_COMMENT_YES) {
			SocialComment socialComment = new SocialComment();
			socialComment.setSpeakid(speakId);
			socialComment.setUserid(userId);
			socialComment.setCreatetime(date);
			socialComment.setContent(reason);
			socialComment.setDelflag(SocialGlobal.COMMENT_DEL_FLAG_NO);
			socialComment.setImages(imageUrl);
			socialCommentService.save(socialComment);
			//当前评论有没有@好友
			if(!StringUtils.isEmpty(toUserId)) {
				String[] toUserIds = toUserId.split(",");
				for(String id : toUserIds) {
					SocialTips socialTips = new SocialTips();
					socialTips.setUserid(id);
					socialTips.setType(SocialGlobal.TIPS_TYPE_COMMENT);
					socialTips.setFkid(socialComment.getId());
					socialTips.setIsnotice(SocialGlobal.TIPS_IS_NOTICE_NO);
					socialTips.setIsread(SocialGlobal.TIPS_IS_READ_NO);
					socialTipsService.save(socialTips);
				}
			}
		}
		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("message", "转发成功");
		return toJson;
	}

}
