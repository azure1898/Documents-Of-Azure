package com.its.modules.social.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.its.common.web.BaseController;
import com.its.modules.app.entity.Account;
import com.its.modules.app.service.AccountService;
import com.its.modules.rong.common.RongGlobal;
import com.its.modules.rong.entity.SocialMsg;
import com.its.modules.rong.service.SocialMsgService;

import io.rong.RongCloud;
import io.rong.messages.TxtMessage;
import io.rong.models.CodeSuccessResult;

/**
 * @Description：互动消息相关接口
 * @Author：王萌萌
 * @Date：2017年8月18日
 */
@Controller
@RequestMapping(value = "${appPath}/msgSend")
public class SocialMsgSendController extends BaseController {
	
	@Autowired
	private SocialMsgService socialMsgService;
	@Autowired
	private AccountService accountService;
	
	/**
	 * @Description：@我的消息
	 * @Author：王萌萌
	 * @Date：2017年8月18日
	 * @param type 消息类别      1 : 用户发言@我      2 : 转发我的发言
	 * @param userId 发言或转发人的id
	 * @param userNickName 发言或转发人的昵称
	 * @param atUserNickName 被@人的昵称
	 * @param content 发言内容
	 * @param toUserId 被@人的id
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="speakMsg",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map speakMsg(int type, String userId, String userNickname, String atUserNickname, String content, String toUserId, HttpServletRequest request) throws Exception {
		Map toJson = new HashMap();
		
		try {
			String[] toUserIds =  new String[1];
			toUserIds[0] = toUserId;
			String msgContent = "";
			String msgExtra = "";
			String path = request.getContextPath(); 
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
			String url = basePath + "/app/message/myMsg?userId=" + toUserId;
			if(type == 1) {
				if(!StringUtils.isEmpty(content)) {
					msgContent = "@" + userNickname + "\n提到你：" + content;
				} else {
					msgContent = "@" + userNickname + "\n提到你@"+atUserNickname+"：查看图片";
				}
				msgExtra = "{\"sendType\":4.1,"
						+ "\"fromUserId\":\""+userId+"\","
						+ "\"toUserId\":\""+toUserIds+"\","
						+ "\"msgContent\":\""+msgContent+"\","
						+ "\"url\":\""+url+"\","
						+"}";
			} else {
				msgContent = "@" + userNickname + "\n提到你@"+atUserNickname+"：" + content;
				msgExtra = "{\"sendType\":4.4,"
						+ "\"fromUserId\":\""+userId+"\","
						+ "\"toUserId\":\""+toUserIds+"\","
						+ "\"msgContent\":\""+msgContent+"\","
						+ "\"url\":\""+url+"\","
						+"}";
			}
			
			RongCloud rongCloud = RongCloud.getInstance(RongGlobal.APP_KEY, RongGlobal.APP_SECRET);
			TxtMessage messagePublishSystemTxtMessage = new TxtMessage(msgContent,msgExtra);
			CodeSuccessResult csr = rongCloud.message.PublishSystem(userId, toUserIds, messagePublishSystemTxtMessage, "", "", 1, 1);
			
			for(String atUserId : toUserIds){
				SocialMsg socialMsg = new SocialMsg();
				socialMsg.setUserid(userId);
				socialMsg.setUsername(userNickname);
				socialMsg.setTouserid(atUserId);
				socialMsg.setContent("{\"Code\":\""+csr.getCode()+"\",\"ErrorMessage\":\""+csr.getErrorMessage()+"\",\"msgExtra\":\""+msgExtra+"\"}");
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
		
		toJson.put("code", "1000");
		toJson.put("type", "1");
		toJson.put("message", "success");
		return toJson;
	}
	
	/**
	 * @Description：评论消息
	 * @Author：王萌萌
	 * @Date：2017年8月18日
	 * @param type 消息类别     1 : 直接回复    2 : 回复主贴下其他人
	 * @param userId 发言或转发人的id
	 * @param userNickname 发言或转发人的昵称
	 * @param atUserNickname 被回复人的昵称
	 * @param content 评论内容
	 * @param toUserId 被回复人的id
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="commentMsg",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map commentMsg(int type, String userId, String userNickname, String atUserNickname, String content, String toUserId, HttpServletRequest request) throws Exception {
		Map toJson = new HashMap();
		
		try {
			String[] toUserIds =  new String[1];
			toUserIds[0] = toUserId;
			String msgContent = "";
			if(type == 1) {
				msgContent = "@" + userNickname + "\n评论你：" + content;
			} else {
				msgContent = "@" + userNickname + "\n评论@"+atUserNickname+"：" + content;
			}
			String path = request.getContextPath(); 
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
			String url = basePath + "/app/message/commentMe?userId=" + toUserId;
			String msgExtra = "{\"sendType\":4.2,"
					+ "\"fromUserId\":\""+userId+"\","
					+ "\"toUserId\":\""+toUserIds+"\","
					+ "\"msgContent\":\""+msgContent+"\","
					+ "\"url\":\""+url+"\","
					+"}";
			
			RongCloud rongCloud = RongCloud.getInstance(RongGlobal.APP_KEY, RongGlobal.APP_SECRET);
			TxtMessage messagePublishSystemTxtMessage = new TxtMessage(msgContent,msgExtra);
			CodeSuccessResult csr = rongCloud.message.PublishSystem(userId, toUserIds, messagePublishSystemTxtMessage, "", "", 1, 1);
			
			for(String atUserId : toUserIds){
				SocialMsg socialMsg = new SocialMsg();
				socialMsg.setUserid(userId);
				socialMsg.setUsername(userNickname);
				socialMsg.setTouserid(atUserId);
				socialMsg.setContent("{\"Code\":\""+csr.getCode()+"\",\"ErrorMessage\":\""+csr.getErrorMessage()+"\",\"msgExtra\":\""+msgExtra+"\"}");
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
		
		toJson.put("code", "1000");
		toJson.put("type", "2");
		toJson.put("message", "success");
		return toJson;
	}
	
	/**
	 * @Description：关注消息
	 * @Author：王萌萌
	 * @Date：2017年8月18日
	 * @param userId 粉丝id
	 * @param userNickname 粉丝昵称
	 * @param toUserId 被关注id
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="focusMsg",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map focusMsg(String userId, String fansNickname, String toUserId, HttpServletRequest request) throws Exception {
		Map toJson = new HashMap();
		
		try {
			String[] toUserIds =  new String[1];
			toUserIds[0] = toUserId;
			String msgContent = "@" + fansNickname + "\n成为你的新粉丝";
			String path = request.getContextPath(); 
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
			String url = basePath + "/app/myHome/myFansList?userId=" + toUserId+"&pageIndex=0\",";
			String msgExtra = "{\"sendType\":4.3,"
					+ "\"fromUserId\":\""+userId+"\","
					+ "\"toUserId\":\""+toUserIds+"\","
					+ "\"msgContent\":\""+msgContent+"\","
					+ "\"url\":\""+url+"\","
					+"}";
			
			RongCloud rongCloud = RongCloud.getInstance(RongGlobal.APP_KEY, RongGlobal.APP_SECRET);
			TxtMessage messagePublishSystemTxtMessage = new TxtMessage(msgContent,msgExtra);
			CodeSuccessResult csr = rongCloud.message.PublishSystem(userId, toUserIds, messagePublishSystemTxtMessage, "", "", 1, 1);
			
			for(String atUserId : toUserIds){
				SocialMsg socialMsg = new SocialMsg();
				socialMsg.setUserid(userId);
				socialMsg.setUsername(fansNickname);
				socialMsg.setTouserid(atUserId);
				socialMsg.setContent("{\"Code\":\""+csr.getCode()+"\",\"ErrorMessage\":\""+csr.getErrorMessage()+"\",\"msgExtra\":\""+msgExtra+"\"}");
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
		toJson.put("code", "1000");
		toJson.put("type", "4");
		toJson.put("message", "success");
		return toJson;
	}
	
	/**
	 * @Description：@我的消息
	 * @Author：王萌萌
	 * @Date：2017年8月18日
	 * @param userId 发言人id
	 * @param userName 发言人昵称
	 * @param content 发言内容
	 * @param villageIds 楼盘id数组
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="speakSendMsg",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map speakSendMsg(String userId, String userName, String content, String[] villageIds, HttpServletRequest request) throws Exception {
		Map toJson = new HashMap();
		String toUserId = "";
		
		for(String villageId : villageIds) {
			List<Account> accountList = accountService.findListByVillage(villageId);
			for(int i=1; i<accountList.size(); i++) {
				Account account = accountList.get(i);
				toUserId = account.getId();
				String[] toUserIds =  new String[1];
				toUserIds[0] = toUserId;
				String msgContent = "@" + userName + "\n提到你："+content;
				String path = request.getContextPath(); 
				String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
				String url = basePath + "/app/message/myMsg?userId=" + userId;
				String msgExtra = "{\"sendType\":4.3,"
						+ "\"fromUserId\":\""+userId+"\","
						+ "\"toUserId\":\""+toUserIds+"\","
						+ "\"msgContent\":\""+msgContent+"\","
						+ "\"url\":\""+url+"\","
						+"}";
				
				RongCloud rongCloud = RongCloud.getInstance(RongGlobal.APP_KEY, RongGlobal.APP_SECRET);
				TxtMessage messagePublishSystemTxtMessage = new TxtMessage(msgContent,msgExtra);
				CodeSuccessResult csr = rongCloud.message.PublishSystem(userId, toUserIds, messagePublishSystemTxtMessage, "", "", 1, 1);
				
				SocialMsg socialMsg = new SocialMsg();
				socialMsg.setUserid(userId);
				socialMsg.setUsername(userName);
				socialMsg.setTouserid(toUserId);
				socialMsg.setContent("{\"Code\":\""+csr.getCode()+"\",\"ErrorMessage\":\""+csr.getErrorMessage()+"\",\"msgExtra\":\""+msgExtra+"\"}");
				socialMsg.setIsnotice(RongGlobal.MSG_IS_NOTICE_YES);
				socialMsg.setNoticetime(new Date());
				socialMsg.setFirtype(RongGlobal.MSG_FIRTYPE_SOCIAL);
				socialMsg.setSectype(RongGlobal.MSG_SECTYPE_ATWD);
				socialMsg.setIsread(RongGlobal.MSG_IS_READ_NO);
				socialMsg.setNoticetime(new Date());
				socialMsgService.save(socialMsg);
				
				if(i%99==0) 
					Thread.sleep(1000);
			}
		}
		
		toJson.put("code", "1000");
		toJson.put("type", "1");
		toJson.put("message", "success");
		return toJson;
	}

}
