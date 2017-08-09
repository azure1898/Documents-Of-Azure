package com.its.modules.social.web;

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
import com.its.modules.app.entity.Account;
import com.its.modules.app.service.AccountService;
import com.its.modules.social.common.SocialGlobal;
import com.its.modules.social.entity.SocialSpeak;
import com.its.modules.social.entity.SocialSubRelation;
import com.its.modules.social.entity.SocialSubject;
import com.its.modules.social.entity.SocialTips;
import com.its.modules.social.service.SocialCommentService;
import com.its.modules.social.service.SocialPraiseService;
import com.its.modules.social.service.SocialSpeakService;
import com.its.modules.social.service.SocialSubRelationService;
import com.its.modules.social.service.SocialSubjectService;
import com.its.modules.social.service.SocialTipsService;

import net.sf.json.JSONObject;

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
	
	/**
	 * @我的
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="myMsg",method = RequestMethod.POST)
	@ResponseBody
	public String myMsg(String userId) throws Exception {
		Map<String, Object> map = new HashMap<>();
		
		if(StringUtils.isEmpty(userId)) {
			map.put("code", Global.CODE_PROMOT);
			map.put("message", "查看@我的发言失败");
			String result = JSONObject.fromObject(map).toString();
			return result;
		}
		
		map.put("code", Global.CODE_SUCCESS);
		//根据用户id从Account查询信息
		Account account = accountService.get(userId);
		Map<String, Object> dataMap = new HashMap<>(); 
		dataMap.put("userId", userId);
		dataMap.put("userHeadUrl", account.getPhoto());
		dataMap.put("userName", account.getNickname());
		
		List<SocialTips> listByUserId = socialTipsService.getListByUserIdAndType(userId, SocialGlobal.TIPS__TYPE_SPEAK);
		for (SocialTips socialTips : listByUserId) {
			String speakid = socialTips.getFkid();
			SocialSpeak socialSpeak = socialSpeakService.get(speakid); // 根据发言的id得到所有数据
			dataMap.put("creatTime", socialSpeak.getCreatetime());
			dataMap.put("content", socialSpeak.getContent());
			dataMap.put("imgUrls", socialSpeak.getImages());
		
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
				String subName = socialSubject.getSubname();
				subjectListMap.put("subName", subName); // 话题
				
				//根据发言id得到评论数量
				int commentCount = socialCommentService.commentCount(speakid);
				dataMap.put("countComment", commentCount);
				
				//根据发言id得到转发数量
				/*int countForward = socialForwardService.countForward(speakid);
				dataMap.put("countForward", countForward);*/
				
				//根据发言id得到点赞数量
				int countPraise = socialPraiseService.countPraise(speakid);
				dataMap.put("countPraise", countPraise);
				
			}
			dataMap.put("subjectList", subjectListMap);
			
		}
		
		map.put("data", dataMap);
		map.put("message", "查看@我的发言成功");
		String result = JSONObject.fromObject(map).toString();
		return result;
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
	@RequestMapping(value="black",method = RequestMethod.POST)
	@ResponseBody
	public String black(String userId, String subUserId) {
		Map<String, Object> map = new HashMap<>();
		
		if(StringUtils.isEmpty(userId)){
			map.put("code", Global.CODE_PROMOT);
			map.put("message", "屏蔽TA的发言失败");
			String result = JSONObject.fromObject(map).toString();
			return result;
		}
		
		
		map.put("code", Global.CODE_SUCCESS);
		map.put("message", "屏蔽TA的发言成功");
		String result = JSONObject.fromObject(map).toString();
		return result;
	}
	
	/**
	 * 
	 * @Description：评论我的
	 * @Author：邵德才
	 * @Date：2017年8月8日
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="commentMe",method = RequestMethod.POST)
	@ResponseBody
	public String commentMe(String userId) {
		Map<String, Object> map = new HashMap<>();
		
		if(StringUtils.isEmpty(userId)){
			map.put("code", Global.CODE_PROMOT);
			map.put("message", "评论我的失败");
			String result = JSONObject.fromObject(map).toString();
			return result;
		}
		
		map.put("code", Global.CODE_SUCCESS);
		
		Map<String, Object> dataMap = new HashMap<>();
		
		map.put("data", dataMap);
		map.put("message", "评论我的成功");
		String result = JSONObject.fromObject(map).toString();
		return result;
		
	}
}
