/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.social.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.csource.common.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.its.common.config.Global;
import com.its.common.utils.MyFDFSClientUtils;
import com.its.common.web.BaseController;
import com.its.modules.app.entity.Account;
import com.its.modules.app.service.AccountService;
import com.its.modules.rong.common.RongGlobal;
import com.its.modules.rong.entity.SocialMsg;
import com.its.modules.rong.service.SocialMsgService;
import com.its.modules.social.bean.SocialSpeakBean;
import com.its.modules.social.common.DateUtil;
import com.its.modules.social.common.SocialGlobal;
import com.its.modules.social.entity.SocialPraise;
import com.its.modules.social.entity.SocialRelation;
import com.its.modules.social.entity.SocialSpeak;
import com.its.modules.social.entity.SocialSubRelation;
import com.its.modules.social.entity.SocialSubject;
import com.its.modules.social.entity.SocialTips;
import com.its.modules.social.service.SocialPraiseService;
import com.its.modules.social.service.SocialRelationService;
import com.its.modules.social.service.SocialSpeakService;
import com.its.modules.social.service.SocialSubRelationService;
import com.its.modules.social.service.SocialSubjectService;
import com.its.modules.social.service.SocialTipsService;

import io.rong.RongCloud;
import io.rong.messages.TxtMessage;

/**
 * @Description：发言手机端相关接口
 * @Author：刘浩浩
 * @Date：2017年8月4日
 */
@Controller
@RequestMapping(value = "${appPath}/speak")
public class SocialSpeakController extends BaseController {

	@Autowired
	private SocialSpeakService socialSpeakService;
	
	@Autowired
	private SocialSubjectService socialSubjectService;

	@Autowired
	private SocialSubRelationService socialSubRelationService;

	@Autowired
	private SocialTipsService socialTipsService;

	@Autowired
	private SocialPraiseService socialPraiseService;

	@Autowired
	private SocialRelationService socialRelationService;

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private SocialMsgService socialMsgService;
	
	/**
	 * @Description：首页发言列表
	 * @Author：刘浩浩
	 * @Date：2017年8月4日
	 * @param villageInfoId 楼盘ID
	 * @param userId 用户id
	 * @param isAll 是否查询全部
	 * @param pageIndex  分页页码(从0开始为起始页)
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="speakList",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> getSpeakList(String userId, String villageInfoId, int isAll, int pageIndex, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		//结果封装
		Map<String, Object> toJson = new HashMap<String, Object>();
		
		int pageSize = pageIndex == 0 ? SocialGlobal.PAGE_SIZE_INDEX : SocialGlobal.PAGE_SIZE;
		pageIndex = pageIndex == 0 ? pageSize * pageIndex : pageSize * pageIndex -SocialGlobal.PAGE_SIZE_INDEX;
		List<SocialSpeakBean> socialSpeakBeanList = new ArrayList();
		SocialSpeak socialSpeak = new SocialSpeak();
		socialSpeak.setVillageinfoid(villageInfoId);
		socialSpeak.setDelflag(SocialGlobal.SPEAK_DEL_FLAG_NO);
		if(isAll==SocialGlobal.SPEAK_LIST_ALL){//全部
			socialSpeakBeanList = socialSpeakService.findListByUserId(null, socialSpeak, pageIndex, pageSize);
		}else{//只看我关注的
			socialSpeakBeanList = socialSpeakService.findListByUserId(userId, socialSpeak, pageIndex, pageSize);
		}
		
		List<Map> resultList = new ArrayList(); 
		if(socialSpeakBeanList!=null && socialSpeakBeanList.size()>0){
			for(SocialSpeakBean spb : socialSpeakBeanList){
				Map<String, Object> data = new HashMap<String, Object>();
				//人员属性
				data.put("userName", spb.getNickName());//发言人姓名 昵称
				data.put("headPicSrc", spb.getPhoto());//发言人头像
				
				//发言属性
				data.put("speakId", spb.getId());//发言id
				data.put("speakUserId", spb.getUserid());//发言人id
				String createTime = DateUtil.getDaysBeforeNow(spb.getCreatetime());
				data.put("createTime", createTime);//发言时间
				data.put("content", spb.getContent());//发言内容
				data.put("isSpeak", spb.getIsspeak());
				
				//根据ID 获取话题集合
				String speakId = spb.getId();
				List<SocialSubject> subjectList = socialSubjectService.findAllByfkId(speakId, SocialGlobal.SUB_RELATION_SPK);
				Map<String, Object> subMap = new HashMap<String, Object>();
				if(subjectList!=null && subjectList.size()>0){
					for(SocialSubject sub : subjectList){
						subMap.put("subjectId", sub.getId());
						subMap.put("subName", sub.getSubname());
					}
					
				}
				data.put("subjectList", subMap);
				
				//获取发言图片集合
				String images = spb.getImages();
				if(!StringUtils.isEmpty(images)){
					String[] imgs = images.split(",");
					Map<String, Object> imgMap = new HashMap<>();
					for(String img : imgs){
						imgMap.put("imgUrl", MyFDFSClientUtils.get_fdfs_file_url(request, img));
					}
					data.put("imgsList", imgMap);
				}else{
					data.put("imgsList", "");
				}
				
				
				//获取root发言
				String rootId = spb.getRootid();
				SocialSpeak rootSpeak = socialSpeakService.get(rootId);
				Map<String, Object> rootMap = new HashMap<String, Object>();
				if(rootSpeak!=null){
					rootMap.put("id", rootSpeak.getId());
					rootMap.put("noticeId", rootSpeak.getNoticeid());
					rootMap.put("rootUserId", rootSpeak.getUserid());
					rootMap.put("title", rootSpeak.getTitle());
					rootMap.put("summary", rootSpeak.getSummary());
					rootMap.put("content", rootSpeak.getContent());
					String rootImages = rootSpeak.getImages();
					if(StringUtils.isEmpty(rootImages)){
						rootMap.put("image", "");
					}else{
						rootMap.put("imgUrl", MyFDFSClientUtils.get_fdfs_file_url(request, rootImages.split(",")[0]));
					}
				}
				
				data.put("rootSpeak", rootMap);
				
				//发言相关属性
				data.put("isFocus", spb.getIsFocus());//是否对发言人关注
				data.put("isPraise", spb.getIsPraise());//是否点赞
				data.put("countForward", spb.getCountForward());//转发数量
				data.put("countComment", spb.getCountComment());//评论数量
				data.put("countPraise", spb.getCountPraise());//点赞数量
				resultList.add(data);
			}
			
		}
		
		
		toJson.put("userId", userId);
		toJson.put("pageIndex", pageIndex);
		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("data", resultList);
		toJson.put("message", "查询首页发言列表成功");
		
		/*String result = JSONObject.fromObject(toJson).toString();
		System.out.println("+++++++" + result);*/
		return toJson;
	}

	/**
	 * @Description：屏蔽发言
	 * @Author：刘浩浩
	 * @Date：2017年8月15日
	 * @param userId 当前用户ID
	 * @param subUserId 从用户ID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="black",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> black(String userId, String subUserId) throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		SocialRelation socialRelation = socialRelationService.getSocialRelation(userId, subUserId);
		if(socialRelation!=null){
			socialRelation.setIsblack(SocialGlobal.RELATION_IS_BLACK_YES);
			socialRelationService.save(socialRelation);
		}else{
			socialRelation = new SocialRelation();
			socialRelation.setUserid(userId);
			socialRelation.setSubuserid(subUserId);
			socialRelation.setIsblack(SocialGlobal.RELATION_IS_BLACK_YES);
			socialRelationService.save(socialRelation);
		}
		return resultMap;
	}
	
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
	@ResponseBody
	public Map<String, Object> getForwardList(String speakId, int pageIndex) throws Exception{
		//结果封装
		Map<String, Object> toJson = new HashMap<String, Object>();
		if(StringUtils.isEmpty(speakId)){
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "发言ID为空");
			//String result = JSONObject.fromObject(toJson).toString();
			return toJson;
		}
		
		int pageSize = pageIndex == 0 ? SocialGlobal.PAGE_SIZE_INDEX : SocialGlobal.PAGE_SIZE;
		pageIndex = pageIndex == 0 ? pageSize * pageIndex : pageSize * pageIndex -SocialGlobal.PAGE_SIZE_INDEX;
		SocialSpeak socialSpeak = new SocialSpeak();
		socialSpeak.setDelflag(SocialGlobal.SPEAK_DEL_FLAG_NO);
		socialSpeak.setFid(speakId);
		List<SocialSpeakBean> sbList = socialSpeakService.findSpeakList(socialSpeak, pageIndex, pageSize);
		List<Map> dataList = new ArrayList();
		if(sbList!=null && sbList.size()>0){
			for(SocialSpeakBean sb: sbList){
				Map dataListMap = new HashMap();
				dataListMap.put("userName", sb.getNickName());
				dataListMap.put("headPicSrc", sb.getPhoto());
				
				dataListMap.put("id", sb.getId());
				String createTime = DateUtil.getDaysBeforeNow(sb.getCreatetime());
				dataListMap.put("createTime", createTime);
				dataListMap.put("content", sb.getContent());
				
				//根据ID查询话题集合
				List<SocialSubject> subjectList = socialSubjectService.findAllByfkId(sb.getId(), SocialGlobal.SUB_RELATION_SPK);
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
		/*String result = JSONObject.fromObject(toJson).toString();
		System.out.println("+++++++" + result);*/
		return toJson;
	}
	
	/**
	 * @Description：发言详情
	 * @Author：刘浩浩
	 * @Date：2017年8月4日
	 * @param userId 当前用户ID
	 * @param speakId 发言ID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="speakDetail",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> getSpeakDetail(String userId, String speakId, HttpServletRequest request, HttpServletResponse response) throws Exception{
		//结果封装
		Map<String, Object> toJson = new HashMap<String, Object>();
		if(StringUtils.isEmpty(speakId)){
			toJson.put("code", Global.CODE_PROMOT);
			toJson.put("message", "发言ID为空");
			//String result = JSONObject.fromObject(toJson).toString();
			return toJson;
		}
		SocialSpeakBean socialSpeakBean = socialSpeakService.getSocialSpeakBeanById(speakId);
		if(socialSpeakBean!=null){
			//人员属性
			toJson.put("userName", socialSpeakBean.getNickName());//发言人姓名 昵称
			toJson.put("headPicSrc", socialSpeakBean.getPhoto());//发言人头像
			
			//根据ID 获取话题集合
			List<SocialSubject> subjectList = socialSubjectService.findAllByfkId(speakId, SocialGlobal.SUB_RELATION_SPK);;
			Map<String, Object> subMap = new HashMap<String, Object>();
			if(subjectList!=null && subjectList.size()>0){
				for(SocialSubject sub : subjectList){
					subMap.put("id", sub.getId());
					subMap.put("subName", sub.getSubname());
				}
				
			}
			toJson.put("subjectList", subMap);
			
			//发言属性
			toJson.put("speakId", socialSpeakBean.getId());//发言id
			toJson.put("speakUserId", socialSpeakBean.getUserid());//发言人id
			String createTime = DateUtil.getDaysBeforeNow(socialSpeakBean.getCreatetime());
			toJson.put("createTime", createTime);//发言时间
			toJson.put("spContent", socialSpeakBean.getContent());//发言内容
			
			//是否点赞
			SocialPraise socialPraise = new SocialPraise();
			socialPraise.setUserid(userId);
			socialPraise.setPid(socialSpeakBean.getId());
			SocialPraise sp = socialPraiseService.getSocialPraise(socialPraise);
			if(sp!=null){
				toJson.put("isPraise", "1");//是否点赞  0：否 1：是
			}else{
				toJson.put("isPraise", "0");//是否点赞  0：否 1：是
			}
			
			//是否关注
			SocialRelation socialRelation = new SocialRelation();
			socialRelation.setUserid(userId);
			socialRelation.setSubuserid(socialSpeakBean.getUserid());
			SocialRelation sr = socialRelationService.getSocialRelation(userId, socialSpeakBean.getUserid());
			if(sr!=null){
				toJson.put("isFocus", "1");//是否关注  0：否 1：是
			}else{
				toJson.put("isFocus", "0");//是否关注  0：否 1：是
			}
			
			//获取发言图片集合
			String images = socialSpeakBean.getImages();
			Map<String, Object> imgMap = new HashMap<>();
			if(!StringUtils.isEmpty(images)){
				String[] imgs = images.split(",");
				for(String img : imgs){
					imgMap.put("imgUrl", MyFDFSClientUtils.get_fdfs_file_url(request, img));
				}
			}
			toJson.put("releasePictures", imgMap);
			
			//发言相关属性
			toJson.put("spCountFwd", socialSpeakBean.getCountForward());//转发数量
			toJson.put("spCountCmt", socialSpeakBean.getCountComment());//评论数量
			toJson.put("spCountPraise", socialSpeakBean.getCountPraise());//点赞数量
			
		}
		toJson.put("code", Global.CODE_SUCCESS);
		toJson.put("message", "查看发言详情成功");
		/*String result = JSONObject.fromObject(toJson).toString();
		System.out.println("+++++++" + result);*/
		return toJson;
	}
	
	/**
	 * @Description：跳转发言页
	 * @Author：刘浩浩
	 * @Date：2017年8月18日
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="toSpeak",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> toSpeak(String userId) throws Exception{
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("userId", userId);
		resultMap.put("plateId", SocialGlobal.SYS_PLATE_ID);
		resultMap.put("code", Global.CODE_SUCCESS);
		resultMap.put("message", "跳转发言页成功");
		
		return resultMap;
	}
	
	/**
	 * @Description：保存发言
	 * @Author：刘浩浩
	 * @Date：2017年8月10日
	 * @param userId 用户id
	 * @param plateId 板块id
	 * @param villageInfoId 楼盘ID
	 * @param content 发言内容
	 * @param imageUrl 图片地址
	 * @param visible 可见范围
	 * @param toUsersIds at用户id 多个id 以“,”分割（半角,）
	 * @param subjectIds 话题id 多个id 以“,”分割（半角,）
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="saveSpeak",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> saveSpeak(String userId, String plateId, String villageInfoId, String content, int visible, String toUsersIds, String subjectIds, HttpServletRequest request) throws Exception{
		Map<String, Object> resultMap = new HashMap();
		//保存发言
		SocialSpeak socialSpeak = new SocialSpeak();
		socialSpeak.setUserid(userId);
		
		socialSpeak.setPlateid(plateId);
		socialSpeak.setVillageinfoid(villageInfoId);
		socialSpeak.setContent(content);
		socialSpeak.setVisrange(visible);
		socialSpeak.setIsspeak(SocialGlobal.SPEAK_IS_SPEAK_YES);
		socialSpeak.setCreatetime(new Date());
		
		//处理图片附件
		ServletContext servletContext = request.getSession().getServletContext();
		// 解析上下文
		CommonsMultipartResolver resolver = new CommonsMultipartResolver(servletContext);
		// 多部件上传
		String images = "";
		if (resolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 获取文件名称迭代器
			Iterator<String> it = multipartRequest.getFileNames();
			while (it.hasNext()) {
				MultipartFile multipartFile = multipartRequest.getFile(it.next());
				if (multipartFile != null) {
					// 获取原始文件名
					String originalFilename = multipartFile.getOriginalFilename();
					// 获取文件扩展名
					String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
					String dirName = "gif,jpg,jpeg,png,bmp";
					if (!Arrays.asList(dirName.split(",")).contains(suffix)) {
						resultMap.put("code", Global.CODE_PROMOT);
						resultMap.put("message", "文件上传失败，只允许上传" + dirName + "格式的文件");
						return resultMap;
					}
					String fileName = null;
					try {
						fileName = MyFDFSClientUtils.uploadFile(request, multipartFile);
					} catch (IOException | MyException e1) {
						resultMap.put("code", Global.CODE_PROMOT);
						resultMap.put("message", "文件上传失败");
						return resultMap;
					}
					String imagePath = MyFDFSClientUtils.get_fdfs_file_url(request, fileName);

					// 更新路径
					if(StringUtils.isEmpty(images)){
						images = imagePath;
					}else{
						images = "," + imagePath;
					}
				}
			}
		}
		socialSpeak.setImages(images);
		socialSpeakService.save(socialSpeak);
		
		//保存发言话题中间表
		if(!StringUtils.isEmpty(subjectIds)){
			String[] subIds = subjectIds.split(",");
			for(String subId : subIds){
				SocialSubRelation socialSubRelation = new SocialSubRelation();
				socialSubRelation.setSubjectid(subId);
				socialSubRelation.setSpeakid(socialSpeak.getId());
				
				socialSubRelationService.save(socialSubRelation);
			}
		}
		
		//保存提醒
		if(!StringUtils.isEmpty(toUsersIds)){
			String[] atUserIds = toUsersIds.split(",");
			for(String atUserId : atUserIds){
				SocialTips socialTips = new SocialTips();
				socialTips.setUserid(atUserId);
				socialTips.setIsnotice(SocialGlobal.TIPS_IS_NOTICE_NO);
				socialTips.setType(SocialGlobal.TIPS_TYPE_SPEAK);
				socialTips.setFkid(socialSpeak.getId());
				socialTipsService.save(socialTips);
			}
			
			//发送消息
			try {
				Account account = accountService.get(userId);
				String nickname = account.getNickname();
				String msgContent = "@" + nickname + "\n提到你：" + content;
				String msgExtra = "{\"sendType\":1,"
						+ "\"fromUserId\":\""+userId+"\","
						+ "\"toUserId\":\""+atUserIds+"\","
						+ "\"msgContent\":\""+msgContent+"\""
						+"}";
				RongCloud rongCloud = RongCloud.getInstance(RongGlobal.APP_KEY, RongGlobal.APP_SECRET);
				TxtMessage messagePublishSystemTxtMessage = new TxtMessage(msgContent,msgExtra);
				rongCloud.message.PublishSystem(userId, atUserIds, messagePublishSystemTxtMessage, "", "", 1, 1);
				
				for(String atUserId : atUserIds){
					SocialMsg socialMsg = new SocialMsg();
					socialMsg.setUserid(userId);
					socialMsg.setUsername(nickname);
					socialMsg.setTouserid(atUserId);
					socialMsg.setContent(msgContent);
					socialMsg.setIsnotice(RongGlobal.MSG_IS_NOTICE_YES);
					socialMsg.setNoticetime(new Date());
					socialMsg.setFirtype(RongGlobal.MSG_FIRTYPE_SOCIAL);
					socialMsg.setSectype(RongGlobal.MSG_SECTYPE_ATWD);
					socialMsg.setIsread(RongGlobal.MSG_IS_READ_NO);
					socialMsg.setNoticetime(new Date());
					socialMsgService.save(socialMsg);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		resultMap.put("code", Global.CODE_SUCCESS);
		resultMap.put("message", "保存发言成功");
		return resultMap;
	}
	
}