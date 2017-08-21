package com.its.modules.social.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.its.common.config.Global;
import com.its.common.web.BaseController;
import com.its.modules.app.common.OrderGlobal;
import com.its.modules.app.entity.Account;
import com.its.modules.app.entity.OrderGoods;
import com.its.modules.app.service.AccountService;
import com.its.modules.app.service.ModuleManageService;
import com.its.modules.app.service.OrderGoodsService;
import com.its.modules.rong.common.RongGlobal;
import com.its.modules.rong.entity.SocialMsg;
import com.its.modules.rong.service.SocialMsgService;
import com.its.modules.social.bean.SocialSpeakBean;
import com.its.modules.social.common.DateUtil;
import com.its.modules.social.common.SocialGlobal;
import com.its.modules.social.entity.SocialComment;
import com.its.modules.social.entity.SocialPraise;
import com.its.modules.social.entity.SocialRelation;
import com.its.modules.social.entity.SocialSpeak;
import com.its.modules.social.entity.SocialSubRelation;
import com.its.modules.social.entity.SocialSubject;
import com.its.modules.social.entity.SocialTips;
import com.its.modules.social.service.SocialCommentService;
import com.its.modules.social.service.SocialPraiseService;
import com.its.modules.social.service.SocialRelationService;
import com.its.modules.social.service.SocialSpeakService;
import com.its.modules.social.service.SocialSubRelationService;
import com.its.modules.social.service.SocialSubjectService;
import com.its.modules.social.service.SocialTipsService;

/**
 * 消息页面相关接口
 * @author 邵德才
 *
 */
@Controller
@RequestMapping(value = "${appPath}/message")
public class SocialMessageController extends BaseController {

	@Autowired
	private AccountService accountService;
	@Autowired
	private SocialSubRelationService socialSubRelationService;
	@Autowired
	private SocialSubjectService socialSubjectService;
	@Autowired
	private SocialTipsService socialTipsService;
	@Autowired
	private SocialSpeakService socialSpeakService;
	@Autowired
	private SocialCommentService socialCommentService;
	@Autowired
	private SocialPraiseService socialPraiseService;
	@Autowired
	private SocialRelationService socialRelationService;
	@Autowired
	private OrderGoodsService orderGoodsService;
	@Autowired
	private ModuleManageService moduleManageService;
	@Autowired
	private SocialMsgService socialMsgService;
	
	
	/**
	 * @我的
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="myMsg",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> myMsg(String userId) throws Exception {
		Map<String, Object> map = new HashMap<>();
		
		Account account2 = accountService.get(userId);
		if(StringUtils.isBlank(userId) || account2 == null) {
			map.put("code", Global.CODE_PROMOT);
			map.put("message", "查看@我的发言失败");
		} else {
			List<Map<String, Object>> dataList = new ArrayList<>();
			List<Map<String, Object>> subjectList = new ArrayList<>();
			Map<String, Object> rootSpeakMap = new HashMap<>();

			// 根据用户id和提醒类型查询所有数据
			List<SocialTips> listByUserId = socialTipsService.getListByUserIdAndType(userId, SocialGlobal.TIPS_TYPE_SPEAK);
			for (SocialTips socialTips : listByUserId) {
				Map<String, Object> dataMap = new HashMap<>(); 
				String speakid = socialTips.getFkid();
				dataMap.put("id", speakid);
				SocialSpeak socialSpeak = socialSpeakService.get(speakid); // 根据发言的id得到所有数据
				
				int isPraise = socialPraiseService.getIsPraise(userId, speakid);
				dataMap.put("isPraise", isPraise==0 ? SocialGlobal.PRAISE_STATE_NO : SocialGlobal.PRAISE_STATE_YES);
				
				int isFocus = socialRelationService.isFocus(userId, socialSpeak.getUserid());
				dataMap.put("isFocus", isFocus>0 ? SocialGlobal.RELATION_IS_FOCUS_YES : SocialGlobal.RELATION_IS_FOCUS_NO);
				//根据用户id从Account查询信息
				Account account = accountService.get(userId);
				dataMap.put("userId", userId);
				dataMap.put("userHeadUrl", account.getPhoto());
				dataMap.put("userName", account.getNickname());
				
				dataMap.put("creatTime", DateUtil.getDaysBeforeNow(socialSpeak.getCreatetime()));
				dataMap.put("content", socialSpeak.getContent());
				int isspeak = socialSpeak.getIsspeak();
				dataMap.put("isSpeak ", isspeak);
				
				// 如果当前发言是转发的情况
				if(SocialGlobal.SPEAK_IS_SPEAK_NO == isspeak) {
					String rootid = socialSpeak.getRootid();
					rootSpeakMap.put("id", rootid); // 原发言id
					SocialSpeak socialSpeak2 = socialSpeakService.get(rootid); // 根据原发言id得到原发言的数据
					rootSpeakMap.put("noticeId", socialSpeak2.getNoticeid());
					
					String userid2 = socialSpeak2.getUserid(); // 原发言id的用户id
					rootSpeakMap.put("userId", userid2);
					Account account3 = accountService.get(userid2);
					rootSpeakMap.put("userName", account3.getNickname());
					
					Map<String, Object> subjectListMap = new HashMap<>();
					//根据用户id从关系表中找到话题id，然后查询话题名称
					SocialSubRelation socialSubRelation = new SocialSubRelation();
					socialSubRelation.setSpeakid(rootid);
					List<SocialSubRelation> findList = socialSubRelationService.findList(socialSubRelation);
					for (SocialSubRelation socialSubRelation2 : findList) {
						String subjectid = socialSubRelation2.getSubjectid();
						subjectListMap.put("id", subjectid); // 话题id
						//根据话题id查找话题名称
						SocialSubject socialSubject = socialSubjectService.get(subjectid);
						if(socialSubject != null){
							String subName = socialSubject.getSubname();
							subjectListMap.put("subName", subName); // 话题
						}else {
							subjectListMap.put("subName", ""); // 话题
						}
						subjectList.add(subjectListMap);
					}
					rootSpeakMap.put("subjectList", subjectList);
					
					rootSpeakMap.put("delFlag", socialSpeak2.getDelflag());
					rootSpeakMap.put("content", socialSpeak2.getContent());
					rootSpeakMap.put("title", socialSpeak2.getTitle());
					rootSpeakMap.put("summary", socialSpeak2.getSummary());
					rootSpeakMap.put("image", socialSpeak2.getImages());
					dataMap.put("rootSpeak", rootSpeakMap);
				}
				
				String images = socialSpeak.getImages();
				if(StringUtils.isNotBlank(images)){
					String[] split = images.split(",");
					dataMap.put("imgUrls", split);
				}else{
					dataMap.put("imgUrls", "");
				}
			
				List<Map<String, Object>> subjectList1 = new ArrayList<>(); 
				Map<String, Object> subjectListMap = new HashMap<>();
				//根据用户id从关系表中找到话题id，然后查询话题名称
				SocialSubRelation socialSubRelation = new SocialSubRelation();
				socialSubRelation.setSpeakid(speakid);
				List<SocialSubRelation> findList = socialSubRelationService.findList(socialSubRelation);
				for (SocialSubRelation socialSubRelation2 : findList) {
					String subjectid = socialSubRelation2.getSubjectid();
					subjectListMap.put("id", subjectid); // 话题id
					//根据话题id查找话题名称
					SocialSubject socialSubject = socialSubjectService.get(subjectid);
					if(socialSubject != null){
						String subName = socialSubject.getSubname();
						subjectListMap.put("subName", subName); // 话题
					}else {
						subjectListMap.put("subName", ""); // 话题
					}
					subjectList1.add(subjectListMap);
				}
				dataMap.put("subjectList", subjectList1);
				
				//根据发言id得到评论数量
				int commentCount = socialCommentService.commentCount(speakid);
				dataMap.put("countComment", commentCount);
				
				//根据发言id得到转发数量
				int countForward = socialSpeakService.countForward(speakid);
				dataMap.put("countForward", countForward);
				
				//根据发言id得到点赞数量
				int countPraise = socialPraiseService.countPraise(speakid);
				dataMap.put("countPraise", countPraise);
				
				dataList.add(dataMap);
			}
			map.put("code", Global.CODE_SUCCESS);
			map.put("data", dataList);
			map.put("message", "查看@我的发言成功");
		}
		return map;
	}
	
	/**
	 * 
	 * @Description：屏蔽TA的发言
	 * @Author：邵德才
	 * @Date：2017年8月8日
	 * @param userId
	 * @param subUserId
	 * @return
	 */
	@RequestMapping(value="black",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> black(String userId, String subUserId) {
		Map<String, Object> map = new HashMap<>();
		
		if(StringUtils.isBlank(userId)){
			map.put("code", Global.CODE_PROMOT);
			map.put("message", "屏蔽TA的发言失败");
		}else {
			SocialRelation socialRelation = new SocialRelation();
			socialRelation.setUserid(userId);
			socialRelation.setSubuserid(subUserId);
			List<SocialRelation> findList = socialRelationService.findListByUserIdAndSubUserId(socialRelation);
			findList.get(0).setIsblack(SocialGlobal.RELATION_IS_BLACK_YES);
			
			map.put("message", "屏蔽TA的发言成功");
			map.put("code", Global.CODE_SUCCESS);
		}
		return map;
	}
	
	/**
	 * 
	 * @Description：评论我的
	 * @Author：邵德才
	 * @Date：2017年8月8日
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="commentMe",method = {RequestMethod.POST,RequestMethod.GET})
	public Map<String, Object> commentMe(String userId) throws Exception {
		Map<String, Object> map = new HashMap<>();
		
		Account account2 = accountService.get(userId);
		if(StringUtils.isBlank(userId) || account2 == null){
			map.put("code", Global.CODE_PROMOT);
			map.put("message", "评论我的失败");
		}else {
			Map<String, Object> dataMap = new HashMap<>(); // 存储data数据
			List<Map<String, Object>> dataList = new ArrayList<>();
			Map<String, Object> speakMap = new HashMap<>(); // 存储发言对象数据
			Map<String, Object> commentMap = new HashMap<>(); // 存储评论数据
			List<Map<String, Object>> commentList = new ArrayList<>();
			// 根据用户id查询该用户的所有发言数据
			speakMap.put("userId", userId);
			List<SocialSpeak> listByUserId = socialSpeakService.getListByUserId(userId);
			for (SocialSpeak socialSpeak : listByUserId) {
				String speakid = socialSpeak.getId();
				speakMap.put("id", speakid);
				commentMap.put("speakId", speakid);
				// 根据发言id找到评论id
				SocialComment socialComment = new SocialComment();
				socialComment.setSpeakid(speakid);
				List<SocialComment> findList2 = socialCommentService.findList(socialComment);
				if(findList2 != null){
					for (SocialComment socialComment2 : findList2) {
						commentMap.put("id", socialComment2.getId());
						commentMap.put("userId", socialComment2.getUserid());
						commentMap.put("content", socialComment2.getContent());
						commentMap.put("createTime", socialComment2.getCreatetime());
						commentMap.put("fid", socialComment2.getFid());
						commentMap.put("delFlag", socialComment2.getDelflag());
						String images = socialComment2.getImages();
						if(StringUtils.isNotBlank(images)){
							String[] split = images.split(",");
							commentMap.put("images", split);
						}else{
							commentMap.put("images", "");
						}
						
						//根据评论人的userid查评论人的昵称和头像
						Account account = accountService.get(socialComment2.getUserid());
						if(account != null) {
							commentMap.put("userName", account.getNickname());
							commentMap.put("userHeadUrl", account.getPhoto());
						}
						
						// 根据评论id 找到话题id
						SocialSubRelation socialSubRelation = new SocialSubRelation();
						socialSubRelation.setCommentid(socialComment2.getId());
						List<SocialSubRelation> findList = socialSubRelationService.findList(socialSubRelation);
						for (SocialSubRelation socialSubRelation2 : findList) {
							Map<String, Object> subjectMap1 = new HashMap<>(); // 存储评论话题数据
							String subjectid = socialSubRelation2.getSubjectid();
							subjectMap1.put("id", subjectid);
							
							SocialSubject socialSubject = socialSubjectService.get(subjectid);
							String subname = socialSubject.getSubname(); // 用话题id在话题表中找到话题名称
							subjectMap1.put("subName", subname);
							commentMap.put("subjectList", subjectMap1);
						}
						commentList.add(commentMap);
					}
				}
				
				// 根据发言id找到话题id
				SocialSubRelation socialSubRelation = new SocialSubRelation();
				socialSubRelation.setSpeakid(speakid);
				List<SocialSubRelation> findList = socialSubRelationService.findList(socialSubRelation);
				Map<String, Object> subjectMap = new HashMap<>(); // 存储发言话题数据
				List<Map<String, Object>> subjectlist = new ArrayList<>();
				if(findList != null && findList.size() > 0){
					String subjectid = findList.get(0).getSubjectid(); // 得到话题id
					subjectMap.put("id", subjectid);
					SocialSubject socialSubject = socialSubjectService.get(subjectid);
					if(socialSubject != null) {
						String subname = socialSubject.getSubname(); // 用话题id在话题表中找到话题名称
						subjectMap.put("subName", subname);
					}else {
						subjectMap.put("subName", "");
					}
				}
				subjectlist.add(subjectMap);
				speakMap.put("subjectList", subjectlist);
				speakMap.put("content", socialSpeak.getContent());
				speakMap.put("noticeId", socialSpeak.getNoticeid());
				speakMap.put("title", socialSpeak.getTitle());
				speakMap.put("summary", socialSpeak.getSummary());
				speakMap.put("delFlag", socialSpeak.getDelflag());
				
				// 根据用户id查发言人用户昵称
				Account account = accountService.get(userId);
				speakMap.put("userName", account.getNickname());
				String images = socialSpeak.getImages();
				if(StringUtils.isNotBlank(images)){
					String[] split = images.split(",");
					speakMap.put("images", split);
				}else{
					speakMap.put("images", account.getPhoto());
				}
				
				dataMap.put("commontList", commentList);
				dataMap.put("speak", speakMap);
				dataList.add(dataMap);
			}
			
			
			map.put("code", Global.CODE_SUCCESS);
			map.put("data", dataList);
			map.put("message", "评论我的成功");
		}
		return map;
	}
	
	/**
	 * 
	 * @Description：我的评论
	 * @Author：邵德才
	 * @Date：2017年8月9日
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="myComment",method = {RequestMethod.POST,RequestMethod.GET})
	public Map<String, Object> myComment(String userId) throws Exception {
		Map<String, Object> map = new HashMap<>();
		
		Account account2 = accountService.get(userId);
		if(StringUtils.isBlank(userId) || account2 == null){
			map.put("code", Global.CODE_PROMOT);
			map.put("message", "我的评论失败");
		}else {
			List<Map<String, Object>> dataList = new ArrayList<>();
			Map<String, Object> dataMap = new HashMap<>(); // 存储data数据
			Map<String, Object> commentMap = new HashMap<>(); // 存储评论数据
			commentMap.put("userId", userId);
			
			//根据评论人userId从Account查询信息
			Account account = accountService.get(userId);
			commentMap.put("userHeadUrl", account.getPhoto());
			commentMap.put("userName", account.getNickname());
			
			//根据评论人userId找到评论id
			List<SocialComment> findList = socialCommentService.findListByUserId(userId);
			List<Map<String, Object>> commentList = new ArrayList<>(); 
			//遍历list
			for (SocialComment socialComment2 : findList) {
				Map<String, Object> speakMap = new HashMap<>(); // 存储发言对象数据
				String commentId = socialComment2.getId();
				commentMap.put("id", commentId);
				commentMap.put("content", socialComment2.getContent());
				commentMap.put("createTime", socialComment2.getCreatetime());
				commentMap.put("fid", socialComment2.getFid());
				String speakId = socialComment2.getSpeakid();
				commentMap.put("speakId", speakId);
				commentMap.put("delFlag", socialComment2.getDelflag());
				String images1 = socialComment2.getImages();
				if(StringUtils.isNotBlank(images1)){
				    String[] split = images1.split(",");
				    commentMap.put("images", split);
				}else{
				    commentMap.put("images", "");
				}
				
				//查找speakId对应的发言
				SocialSpeak socialSpeak = socialSpeakService.get(speakId);
				if(socialSpeak != null){
					String speakid = socialSpeak.getId();
					speakMap.put("id", speakid);
					speakMap.put("noticeId", socialSpeak.getNoticeid());
					speakMap.put("title", socialSpeak.getTitle());
					speakMap.put("summary", socialSpeak.getSummary());
					speakMap.put("content", socialSpeak.getContent());
					String images = socialSpeak.getImages();
					if(StringUtils.isNotBlank(images)){
						String[] split = images.split(",");
						speakMap.put("image", split);
					}else {
						speakMap.put("image", accountService.get(socialSpeak.getUserid()).getPhoto());
					}
					speakMap.put("delFlag", socialSpeak.getDelflag());
					Map<String, Object> subjectMap = new HashMap<>(); // 存储发言所属话题数据
					SocialSubRelation socialSubRelation = new SocialSubRelation();
					socialSubRelation.setSpeakid(speakid);
					//根据发言id 找到所属话题
					List<SocialSubRelation> findList2 = socialSubRelationService.findList(socialSubRelation);
					List<Map<String, Object>> subjectlist = new ArrayList<>();
					if(findList2 != null && findList2.size() > 0) {
						String subjectid = findList2.get(0).getSubjectid();
						subjectMap.put("id", subjectid);
						SocialSubject socialSubject = socialSubjectService.get(subjectid);
						if(socialSubject != null){
							String subname = socialSubject.getSubname();
							subjectMap.put("subName", subname);
						}else {
							subjectMap.put("subName", "");
						}
					}else {
						subjectMap.put("id", "");
						subjectMap.put("subName", "");
					}
					subjectlist.add(subjectMap);
					speakMap.put("subjectList", subjectlist);
				}
				dataMap.put("speak", speakMap);
				
				// 根据评论id 找到话题id
				SocialSubRelation socialSubRelation = new SocialSubRelation();
				socialSubRelation.setCommentid(speakId);
				List<SocialSubRelation> findList1 = socialSubRelationService.findList(socialSubRelation);
				Map<String, Object> subjectMap = new HashMap<>(); // 存储评论话题数据
				List<Map<String, Object>> subjectlist = new ArrayList<>();
				if(findList1 != null && findList1.size() > 0){
					for (SocialSubRelation socialSubRelation2 : findList1) {
						String subjectid = socialSubRelation2.getSubjectid();
						subjectMap.put("id", subjectid);
						SocialSubject socialSubject = socialSubjectService.get(subjectid);
						if(socialSubject != null){
							String subname = socialSubject.getSubname(); // 用话题id在话题表中找到话题名称
							subjectMap.put("subName", subname);
						}else {
							subjectMap.put("subName", "");
						}
					}
				}else{
					subjectMap.put("subName", "");
					subjectMap.put("id", "");
				}
				subjectlist.add(subjectMap);
				commentMap.put("subjectList", subjectlist);
				commentList.add(commentMap);
				
				dataMap.put("commontList", commentList);
				dataList.add(dataMap);
			}
			
			map.put("code", Global.CODE_SUCCESS);
			map.put("data", dataList);
			map.put("message", "我的评论成功");
		}
		return map;
	}
	
	/**
	 * 
	 * @Description：删除我的评论
	 * @Author：邵德才
	 * @Date：2017年8月10日
	 * @param commentId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="commentDel",method = {RequestMethod.POST,RequestMethod.GET})
	public Map<String, Object> commentDel(String commentId) throws Exception {
		Map<String, Object> map = new HashMap<>();
		
		Account account2 = accountService.get(commentId);
		if(StringUtils.isBlank(commentId) || account2 == null){
			map.put("code", Global.CODE_PROMOT);
			map.put("message", "删除我的评论失败");
		}else {
			socialCommentService.deleteComment(commentId);
			map.put("code", Global.CODE_SUCCESS);
			map.put("message", "删除我的评论成功");
		}
		return map;
	}
	
	/**
	 * 
	 * @Description：赞过我的
	 * @Author：邵德才
	 * @Date：2017年8月11日
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="praiseMeList",method = {RequestMethod.POST,RequestMethod.GET})
	public Map<String, Object> praiseMeList(String userId) throws Exception {
		Map<String, Object> map = new HashMap<>();
		
		Account account2 = accountService.get(userId);
		if(StringUtils.isBlank(userId) || account2 == null){
			map.put("code", Global.CODE_PROMOT);
			map.put("message", "查看赞过我的失败");
		}else {
			List<Map<String, Object>> dataList = new ArrayList<>();
			Map<String, Object> dataMap = new HashMap<>();
			Map<String, Object> speakMap = new HashMap<>(); // 存储发言对象的数据
			Map<String, Object> commentMap = new HashMap<>(); // 存储评论数据
			//根据被赞人我自己（useId）查找socialPraise中所有数据
			List<SocialPraise> findList = socialPraiseService.findListByToUserId(userId);
			for (SocialPraise socialPraise2 : findList) {
				String userid = socialPraise2.getUserid(); // 点赞人用户id
				dataMap.put("userId", userid);
				dataMap.put("creatTime", socialPraise2.getPraisetime());
				if(SocialGlobal.PRAISE_TYPE_SPEAK == socialPraise2.getType()){
					dataMap.put("isComment", 0);
				}else if(SocialGlobal.PRAISE_TYPE_COMMENT == socialPraise2.getType()){
					dataMap.put("isComment", 1);
				}
				
				//根据用户id从Account查询信息
				Account account = accountService.get(userid);
				dataMap.put("userHeadUrl", account.getPhoto());
				dataMap.put("userName", account.getNickname());
			}
			
			Map<String, Object> subjectListMap = new HashMap<>();
			speakMap.put("userId", userId);
			//根据用户id(被赞人)从Account查询信息
			Account account = accountService.get(userId);
			speakMap.put("userName", account.getNickname());
			// 根据被赞人（uesrId）查找当前发言
			List<SocialSpeak> listByUserId = socialSpeakService.getListByUserId(userId);
			List<Map<String, Object>> subjectList = new ArrayList<>();
			if(listByUserId != null && listByUserId.size() > 0){
				for (SocialSpeak socialSpeak : listByUserId) {
					String speakId = socialSpeak.getId(); // 发言id
					// 根据发言id找到评论id
					SocialComment socialComment = new SocialComment();
					socialComment.setSpeakid(speakId);
					List<SocialComment> findList2 = socialCommentService.findList(socialComment);
					if(findList2 != null){
						for (SocialComment socialComment2 : findList2) {
							String commentid = socialComment2.getId();
							commentMap.put("id", commentid);
							commentMap.put("userId", socialComment2.getUserid());
							commentMap.put("content", socialComment2.getContent());
							String images = socialComment2.getImages();
							if(StringUtils.isNotBlank(images)){
							    String[] split = images.split(",");
							    commentMap.put("imgUrls", split);
							}else{
							    commentMap.put("imgUrls", "");
							}
							
							// 评论人的用户名
							Account account1 = accountService.get(userId);
							commentMap.put("userName", account1.getNickname());
							
							// 根据评论id 找到话题id
							Map<String, Object> subjectMap1 = new HashMap<>(); // 存储评论话题数据
							SocialSubRelation socialSubRelation = new SocialSubRelation();
							socialSubRelation.setCommentid(commentid);
							List<SocialSubRelation> findList3 = socialSubRelationService.findList(socialSubRelation);
							List<Map<String, Object>> listSubject = new ArrayList<>();
							for (SocialSubRelation socialSubRelation2 : findList3) {
								String subjectid = socialSubRelation2.getSubjectid();
								subjectMap1.put("id", subjectid);
								SocialSubject socialSubject = socialSubjectService.get(subjectid);
								if(socialSubject != null){
									String subname = socialSubject.getSubname(); // 用话题id在话题表中找到话题名称
									subjectMap1.put("subName", subname);
								}else{
									subjectMap1.put("subName", "");
								}
								listSubject.add(subjectMap1);
							}
							commentMap.put("subjectList", listSubject);
							
						}
					}
					
					speakMap.put("id", speakId);
					speakMap.put("noticeId", socialSpeak.getNoticeid());
					speakMap.put("title", socialSpeak.getTitle());
					speakMap.put("summary", socialSpeak.getSummary());
					speakMap.put("content", socialSpeak.getContent());
					speakMap.put("delFlag", socialSpeak.getDelflag());
					String images = socialSpeak.getImages();
					if(StringUtils.isNotBlank(images)){
						String[] split = images.split(",");
						speakMap.put("imgUrls", split);
					}else{
						speakMap.put("imgUrls", "");
					}
					
					//根据发言id从关系表中找到话题id，然后查询话题名称
					SocialSubRelation socialSubRelation = new SocialSubRelation();
					socialSubRelation.setSpeakid(speakId);
					List<SocialSubRelation> findList1 = socialSubRelationService.findList(socialSubRelation);
					if (findList1 != null && findList1.size() > 0) {
						String subjectid = findList1.get(0).getSubjectid();
						subjectListMap.put("id", subjectid);
						
						SocialSubject socialSubject = socialSubjectService.get(subjectid);
						// 根据话题id找到话题名称
						if(socialSubject != null){
							subjectListMap.put("subName", socialSubject.getSubname());
						}else {
							subjectListMap.put("subName", "");
						}
					}else {
						subjectListMap.put("id", "");
						subjectListMap.put("subName", "");
					}
					subjectList.add(subjectListMap);
				}
				speakMap.put("subjectList", subjectList);
				dataMap.put("comment", commentMap);
				dataMap.put("speak", speakMap);
				
				dataList.add(dataMap);
			}
			
			map.put("code", Global.CODE_SUCCESS);
			map.put("data", dataList);
			map.put("message", "查看赞过我的成功");
		}
		return map;
	}
	
	/**
	 * 
	 * @Description：订单消息
	 * @Author：邵德才
	 * @Date：2017年8月14日
	 * @param userId
	 * @param pageIndex
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="orderMsg",method = {RequestMethod.POST,RequestMethod.GET})
	public Map<String, Object> orderMsg(String userId, int pageIndex) throws Exception {
		Map<String, Object> map = new HashMap<>();
		
		Account account2 = accountService.get(userId);
		if(StringUtils.isBlank(userId) || account2 == null){
			map.put("code", Global.CODE_PROMOT);
			map.put("message", "查看订单消息失败");
		}else {
			//根据用户id，订单状态(配送中)查询未配送订单
			OrderGoods orderGoods = new OrderGoods();
			orderGoods.setOrderState(OrderGlobal.ORDER_GOODS_DISPATCHING);
			orderGoods.setAccountId(userId);
			int pageSize = pageIndex == 0 ? SocialGlobal.PAGE_SIZE_INDEX : SocialGlobal.PAGE_SIZE;
			pageIndex = pageIndex == 0 ? pageSize * pageIndex : pageSize * pageIndex -SocialGlobal.PAGE_SIZE_INDEX;
			List<OrderGoods> findListByUserIdAndOrderState = orderGoodsService.findListByUserIdAndOrderState(orderGoods,pageIndex,pageSize);
			for (OrderGoods orderGoods2 : findListByUserIdAndOrderState) {
				String payState = orderGoods2.getPayState();
				if(!OrderGlobal.ORDER_PAY_STATE_REFUNDING.equals(payState) && !OrderGlobal.ORDER_PAY_STATE_REFUNDED.equals(payState)){
					map.put("id", orderGoods2.getId()); //订单id
					map.put("orderNum", orderGoods2.getOrderNo()); // 订单号
					//根据模块id找到模块名称
					String moduleManageId = orderGoods2.getModuleManageId();
					String moduleName = moduleManageService.get(moduleManageId).getModuleName();
					map.put("moduleName", moduleName); //模块名称
					map.put("createTime", orderGoods2.getStartTime());
				}
			}
			map.put("code", Global.CODE_SUCCESS);
			map.put("message", "查看订单消息成功");
		}
		return map;
	}
	
	/**
	 * 
	 * @Description：管理员消息
	 * @Author：邵德才
	 * @Date：2017年8月14日
	 * @param userId
	 * @param pageIndex
	 * @return
	 * @throws Exception
	 */
	/*@ResponseBody
	@RequestMapping(value="managerMsg",method = {RequestMethod.POST,RequestMethod.GET})
	public Map<String, Object> managerMsg(String villageInfoId, String userId, String subUserId, int pageIndex) throws Exception {
		Map<String, Object> map = new HashMap<>();
		
		if(StringUtils.isBlank(userId)){
			map.put("code", Global.CODE_PROMOT);
			map.put("message", "查看管理员消息失败");
		}else {
			List<Map<String, Object>> dataList = new ArrayList<>();
			Map<String, Object> dataMap = new HashMap<>();
			Map<String, Object> speakMap = new HashMap<>();	
			Map<String, Object> rootSpeakMap = new HashMap<>();	
			
			// 根据管理员id查找所有发过的公告
			List<SocialSpeak> listByUserId = socialSpeakService.getListByUserId(subUserId);
			if(listByUserId != null && listByUserId.size() > 0) {
				for (SocialSpeak socialSpeak : listByUserId) {
					List<Map<String, Object>> subjectList = new ArrayList<>();
					Map<String, Object> subjectMap = new HashMap<>();	
					String speakid = socialSpeak.getId();
					if(socialSpeak.getNoticeid() != null) {
						speakMap.put("id", speakid);
						speakMap.put("noticeId", socialSpeak.getNoticeid());
						speakMap.put("title", socialSpeak.getTitle());
						speakMap.put("summary", socialSpeak.getSummary());
						speakMap.put("delFlag", socialSpeak.getDelflag());
						speakMap.put("isSpeak", socialSpeak.getIsspeak());
						SocialSubRelation socialSubRelation = new SocialSubRelation();
						socialSubRelation.setSpeakid(speakid);
						
						List<SocialSubRelation> findList = socialSubRelationService.findList(socialSubRelation);
						for (SocialSubRelation socialSubRelation2 : findList) {
							String subjectid = socialSubRelation2.getSubjectid();
							subjectMap.put("id", subjectid); // 话题id
							//根据话题id查找话题名称
							SocialSubject socialSubject = socialSubjectService.get(subjectid);
							String subName = socialSubject.getSubname();
							subjectMap.put("subName", subName); // 话题
							subjectList.add(subjectMap);
						}
						
						speakMap.put("subjectList", subjectList);
						speakMap.put("createTime", socialSpeak.getCreatetime());
						speakMap.put("speakContent", socialSpeak.getContent());
						speakMap.put("reasion", socialSpeak.getReason());
						
						String images = socialSpeak.getImages();
						if(StringUtils.isNotBlank(images)) {
							speakMap.put("imgList", images.split(","));
						}else {
							speakMap.put("imgList", "");
						}
						dataMap.put("speak", speakMap);
						
						if(SocialGlobal.SPEAK_IS_SPEAK_NO == socialSpeak.getIsspeak()) {
							SocialSpeak ssf = socialSpeakService.get(socialSpeak.getRootid());
							String speakId = ssf.getId();
							rootSpeakMap.put("speakId", speakId);
							rootSpeakMap.put("noticeId", ssf.getNoticeid());
							
							//根据管理员id从Account查询信息
							Account account = accountService.get(ssf.getUserid());
							rootSpeakMap.put("userName", account.getNickname());
							rootSpeakMap.put("delFlag", ssf.getDelflag());
							rootSpeakMap.put("isSpeak", ssf.getIsspeak());
							
							SocialSubRelation socialSubRelation1 = new SocialSubRelation();
							socialSubRelation1.setSpeakid(speakId);
							List<SocialSubRelation> findList1 = socialSubRelationService.findList(socialSubRelation);
							List<Map<String, Object>> subjectList1 = new ArrayList<>();
							Map<String, Object> subjectMap1 = new HashMap<>();
							for (SocialSubRelation socialSubRelation2 : findList1) {
								String subjectid = socialSubRelation2.getSubjectid();
								subjectMap1.put("id", subjectid); // 话题id
								//根据话题id查找话题名称
								SocialSubject socialSubject = socialSubjectService.get(subjectid);
								String subName = socialSubject.getSubname();
								subjectMap1.put("subName", subName); // 话题
								subjectList1.add(subjectMap1);
							}
							rootSpeakMap.put("subjectList", subjectList1);
							rootSpeakMap.put("speakContent", ssf.getReason());
							rootSpeakMap.put("title", ssf.getTitle());
							rootSpeakMap.put("summary", ssf.getSummary());
							rootSpeakMap.put("createTime", ssf.getCreatetime());
							String imag = socialSpeak.getImages();
							if(StringUtils.isNotBlank(imag)) {
								rootSpeakMap.put("imgList", imag.split(","));
							}else {
								rootSpeakMap.put("imgList", "");
							}
							dataMap.put("rootSpeak", rootSpeakMap);
						}
					}
					//根据发言id得到评论数量
					int commentCount = socialCommentService.commentCount(speakid);
					dataMap.put("countComment", commentCount);
					
					//根据发言id得到转发数量
					int countForward = socialSpeakService.countForward(speakid);
					dataMap.put("countForward", countForward);
					
					//根据发言id得到点赞数量
					int countPraise = socialPraiseService.countPraise(speakid);
					dataMap.put("countPraise", countPraise);

					
					int isPraise = socialPraiseService.getIsPraise(subUserId, speakid);
					dataMap.put("isPraise", isPraise==0 ? SocialGlobal.PRAISE_STATE_NO : SocialGlobal.PRAISE_STATE_YES);
					dataList.add(dataMap);
				}
			}
			
			map.put("id", subUserId);
			//根据管理员id从Account查询信息
			Account account = accountService.get(subUserId);
			map.put("userName", account.getNickname());
			map.put("headPicSrc", account.getPhoto());
			
			int countFocusByUserId = socialRelationService.countFocusByUserId(subUserId);
			map.put("countFollow", countFocusByUserId);
			int countFansByUserId = socialRelationService.countFansByUserId(subUserId);
			map.put("countFans", countFansByUserId);
			int focus = socialRelationService.isFocus(userId, subUserId);
			map.put("isFocus", focus);
			map.put("data", dataList);
			map.put("code", Global.CODE_SUCCESS);
			map.put("message", "查看管理员消息成功");
		}
		return map;
	}*/
	
	/**
	 * @Description：消息页面
	 * @Author：王萌萌
	 * @Date：2017年8月16日
	 * @param userId 用户id
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@RequestMapping(value="countMsg",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map countMsg(String userId, String villageInfoId) throws Exception {
		Map toJson = new HashMap();
		Map count = new HashMap();
		if(StringUtils.isEmpty(userId)) {
			toJson.put("code", Global.CODE_ERROR);
			toJson.put("message", "用户id为空");
			return toJson;
		}
		int countAtMe = socialMsgService.countUnRead(userId, null, RongGlobal.MSG_IS_READ_NO, RongGlobal.MSG_FIRTYPE_ORDER);
		int countComment = socialMsgService.countUnRead(userId, RongGlobal.MSG_SECTYPE_PLWD, RongGlobal.MSG_IS_READ_NO, null);
		int countOrder = socialMsgService.countUnRead(userId, RongGlobal.MSG_SECTYPE_PS, RongGlobal.MSG_IS_READ_NO, null);
		count.put("countAtMe", countAtMe);
		count.put("countComment", countComment);
		//toJson.put("countOrder", countOrder);
		//订单信息
		SocialMsg socialMsg = new SocialMsg();
		socialMsg.setTouserid(userId);
		socialMsg.setIsread(RongGlobal.MSG_IS_READ_NO);
		socialMsg.setFirtype(RongGlobal.MSG_FIRTYPE_ORDER);
		List<SocialMsg> msgList = socialMsgService.findList(socialMsg, 0, 1);
		if(msgList!=null && msgList.size()>0){
			count.put("countOrder", msgList.size());
			SocialMsg msg = msgList.get(0);
			toJson.put("orderMsgContent", msg.getContent());
			String orderMsgTime = DateUtil.getDaysBeforeNow(msg.getNoticetime());
			toJson.put("orderMsgTime", orderMsgTime);
		}else{
			count.put("countOrder", 0);
			toJson.put("orderMsgContent", "暂无消息");
			toJson.put("orderMsgTime", "");
		}
		toJson.put("count", count);
		//管理员消息
		List<SocialSpeakBean> sbList = socialSpeakService.findAdminList(userId, villageInfoId);
		List<Map> sbMapList = new ArrayList<>();
		if(sbList!=null && sbList.size()>0){
			for(SocialSpeakBean sb : sbList){
				Map<String, Object> sbMap = new HashMap<>();
				sbMap.put("id", sb.getAid());
				sbMap.put("userHeadUrl", sb.getPhoto());
				sbMap.put("userName", sb.getNickName());
				//根据用户id 获取该用户的最近发言
				SocialSpeak speak = new SocialSpeakBean();
				speak.setUserid(sb.getAid());
				speak.setDelflag(SocialGlobal.SPEAK_DEL_FLAG_NO);
				
				List<SocialSpeakBean> socialSpeakBeanList = socialSpeakService.findSpeakList(speak, 0, 1);
				if(socialSpeakBeanList!=null && socialSpeakBeanList.size()>0){
					SocialSpeakBean speakBean = socialSpeakBeanList.get(0);
					sbMap.put("content", speakBean.getContent());
					String createTime = DateUtil.getDaysBeforeNow(speakBean.getCreatetime());
					sbMap.put("createTime", createTime);//发言时间
				}else{
					sbMap.put("createTime", "");
					sbMap.put("content", "");
				}
				
				sbMapList.add(sbMap);
			}
		}

		toJson.put("adminList", sbMapList);
		toJson.put("userId", userId);
		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("message", "查询消息页面成功");
		return toJson;
	}
}
