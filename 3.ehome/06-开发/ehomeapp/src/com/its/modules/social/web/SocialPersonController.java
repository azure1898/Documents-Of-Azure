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
import org.springframework.web.bind.annotation.ResponseBody;

import com.its.common.config.Global;
import com.its.common.web.BaseController;
import com.its.modules.app.entity.Account;
import com.its.modules.app.service.AccountService;
import com.its.modules.social.bean.SocialRelationBean;
import com.its.modules.social.bean.SocialSpeakBean;
import com.its.modules.social.common.DateUtil;
import com.its.modules.social.common.SocialGlobal;
import com.its.modules.social.entity.SocialSubject;
import com.its.modules.social.service.SocialPersonService;
import com.its.modules.social.service.SocialRelationService;
import com.its.modules.social.service.SocialSpeakService;
import com.its.modules.social.service.SocialSubjectService;

import net.sf.json.JSONObject;

/**
 * @Description：个人发言相关接口
 * @Author：王萌萌
 * @Date：2017年8月8日
 */
@Controller
@RequestMapping(value = "${appPath}/person")
public class SocialPersonController extends BaseController {
	
	@Autowired
	private SocialRelationService socialRelationService;
	
	@Autowired
	private SocialPersonService socialPersonService;
	
	@Autowired
	private SocialSubjectService socialSubjectService;
	
	@Autowired
	private SocialSpeakService socialSpeakService;
	
	@Autowired
	private AccountService accountService;
	
	/**
	 * @Description：首页信息展现
	 * @Author：王萌萌
	 * @Date：2017年8月8日
	 * @param subUserId 用户id
	 * @param userId 当前用户id
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="personalInfo",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String personalInfo(String subUserId, String userId, int pageIndex) {
		//结果封装
		Map<String, Object> toJson = new HashMap<String, Object>();
		if(StringUtils.isEmpty(subUserId)){
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "发言ID为空");
			String result = JSONObject.fromObject(toJson).toString();
			return result;
		}
		//根据用户id获取用户基本信息
		SocialRelationBean srb = socialRelationService.findPersonalInfo(userId, subUserId);
		if(!StringUtils.isEmpty(srb)) {
			toJson.put("id", srb.getSubuserid());
			toJson.put("userName", srb.getNickName());
			toJson.put("headPicSrc", srb.getPhoto());
			toJson.put("countFollow", srb.getCountFollow());
			toJson.put("countFans", srb.getCountFans());
			toJson.put("isFocus", srb.getRelation());
		}
		
		int pageSize = pageIndex == 0 ? SocialGlobal.PAGE_SIZE_INDEX : SocialGlobal.PAGE_SIZE;
		//获取用户的发言列表
		List<SocialSpeakBean> ssbList = socialPersonService.findPersonalInfoList(subUserId, userId, pageIndex, pageSize);
		List<Map> dataList = new ArrayList<Map>();
		if(ssbList != null && ssbList.size()>0) {
			for(SocialSpeakBean ssb : ssbList) {
				Map dataListMap = new HashMap();
				Map speak = new HashMap();
				speak.put("id", ssb.getId());
				String createtime = DateUtil.getDaysBeforeNow(ssb.getCreatetime());
				speak.put("createTime", createtime);
				speak.put("speakContent", ssb.getContent());
				speak.put("reason", ssb.getReason());
				if(ssb.getIsspeak() == SocialGlobal.SPEAK_IS_SPEAK_YES) {//如果该发言是用户直接发言
					speak.put("isForword", SocialGlobal.SPEAK_IS_FORWORD_NO);//是否转发为否
				} else {
					speak.put("isForword", SocialGlobal.SPEAK_IS_FORWORD_YES);//是否转发为是
					//查询转发的发言
					SocialSpeakBean ssbForword = socialSpeakService.findSpeakBySpeakId(ssb.getRootid());
					if(!StringUtils.isEmpty(ssbForword)) {
						Map rootSpeak = new HashMap();
						rootSpeak.put("speakId", ssb.getRootid());
						rootSpeak.put("userName", ssbForword.getNickName());
						rootSpeak.put("speakContent", ssbForword.getContent());
						String createTime = DateUtil.getDaysBeforeNow(ssbForword.getCreatetime());
						rootSpeak.put("createTime", createTime);
						rootSpeak.put("noticeId", ssbForword.getNoticeid());
						rootSpeak.put("title", ssbForword.getTitle());
						rootSpeak.put("summary", ssbForword.getSummary());
						rootSpeak.put("isSpeak", ssbForword.getIsspeak());
						rootSpeak.put("delFlag", ssbForword.getDelflag());
						//根据转发的发言id获取话题
						List<SocialSubject> ssForwordList = socialSubjectService.findAllByfkId(ssbForword.getId(), SocialGlobal.SUB_RELATION_SPK);
						List subjectList = new ArrayList();
						for (SocialSubject ss : ssForwordList) {
							Map subjectListMap = new HashMap();
							subjectListMap.put("id", ss.getId());
							subjectListMap.put("subName", ss.getSubname());
							subjectList.add(subjectListMap);
						}
						rootSpeak.put("subjectList", subjectList);
						//转发的发言中的图片
						String images = ssbForword.getImages();
						if(!StringUtils.isEmpty(images)) {
							String[] image = images.split(",");
							rootSpeak.put("imgList", image[0]);
						} else {
							Account account = accountService.get(ssbForword.getUserid());
							rootSpeak.put("imageList", account.getPhoto());
						}
						dataListMap.put("rootSpeak", rootSpeak);
					}
				}
				dataListMap.put("speak", speak);
				dataListMap.put("countForword", ssb.getCountForward());
				dataListMap.put("countComment", ssb.getCountComment());
				dataListMap.put("countPraise", ssb.getCountPraise());
				if(ssb.getIsPraise() > SocialGlobal.PRAISE_STATE_NO) {
					ssb.setIsPraise(SocialGlobal.PRAISE_STATE_YES);
				}
				dataListMap.put("isPraise", ssb.getIsPraise());
				
				dataList.add(dataListMap);
			}
		}
		toJson.put("data", dataList);
		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("message", "首页信息展现成功");
		
		String result = JSONObject.fromObject(toJson).toString();
		System.out.println("+++++++" + result);
		return result;
	}
	
}
