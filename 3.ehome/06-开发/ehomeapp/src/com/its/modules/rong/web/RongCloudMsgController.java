
package com.its.modules.rong.web;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.mail.search.DateTerm;

import org.apache.poi.xslf.model.geom.Guide;
import org.apache.tomcat.util.log.SystemLogHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.support.logging.Log;
import com.its.modules.app.bean.OrderGroupPurcRCBean;
import com.its.modules.app.entity.Account;
import com.its.modules.app.service.AccountService;
import com.its.modules.app.service.OrderGroupPurcService;
import com.its.modules.rong.common.RongGlobal;
import com.its.modules.rong.entity.SocialMsg;
import com.its.modules.rong.service.SocialMsgService;
import com.its.modules.sys.service.LogService;

import io.rong.RongCloud;
import io.rong.messages.TxtMessage;

/**
 * @Description：融云相关消息推送接口
 * @Author：刘恩富
 * @Date：2017年8月11日
 */
@Controller
@RequestMapping(value = "${appPath}/rongCloudMsg")
public class RongCloudMsgController {

	@Autowired
	private SocialMsgService socialMsgService;
	
	@Autowired
	private  LogService logService;
	
	/**
	 * 商品配送消息（仅商品购买模式）
	 * @param businessId	商家Id
	 * @param businessName	商家名称
	 * @param orderId		订单编号
	 * @param toUserId		发送用户Id
	 * @param sendType		发送消息函数类型
	 * @return				{code,message}
	 * @throws Exception
	 */
	@RequestMapping(value="sendGoodsMsg",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String sendGoodsMsg(String businessId,String businessName,String orderId,String toUserId,String sendType) throws Exception{
		try{
			
			RongCloud rongCloud = RongCloud.getInstance(RongGlobal.APP_KEY, RongGlobal.APP_SECRET);
			String[] messagePublishSystemToUserId = new String[1];
			messagePublishSystemToUserId[0] = toUserId;
			String msgContent = "您在["+businessName+"]下的订单已送出。 订单号:"+orderId+"。（点击查看详情）";
			String msgExtra = "{\"sendType\":\""+sendType+"\",\"orderType\":\"1\",\"orderId\":\""+orderId+"\","
					+ "\"fromUserId\":\""+businessId+"\","
					+ "\"toUserId\":\""+toUserId+"\","
					+ "\"msgContent\":\""+msgContent+"\""
					+"}";
			TxtMessage messagePublishSystemTxtMessage = new TxtMessage(msgContent,msgExtra);
			rongCloud.message.PublishSystem(businessId, messagePublishSystemToUserId, messagePublishSystemTxtMessage, msgExtra, msgExtra, 1, 1);
			
			SocialMsg socialMsg = new SocialMsg();
			socialMsg.setIsNewRecord(true);
			socialMsg.setId(UUID.randomUUID().toString());
			socialMsg.setUserid(businessId);
			socialMsg.setUsername(businessName);
			socialMsg.setTouserid(toUserId);
			socialMsg.setContent(msgExtra);
			socialMsg.setIsnotice(RongGlobal.MSG_IS_NOTICE_YES);
			socialMsg.setNoticetime(new Date());
			socialMsg.setFirtype(RongGlobal.MSG_FIRTYPE_ORDER);
			socialMsg.setSectype(RongGlobal.MSG_SECTYPE_PS);
			socialMsg.setIsread(RongGlobal.MSG_IS_READ_NO);
			socialMsgService.save(socialMsg);
			
			return "{code:1000,message:\"\"}";
		}catch (Exception ex) {
			
			com.its.modules.sys.entity.Log log = new com.its.modules.sys.entity.Log();
			log.setIsNewRecord(true);
			log.setId(UUID.randomUUID().toString());
			log.setType(com.its.modules.sys.entity.Log.TYPE_Exception);
			log.setTitle("sendGoodsMsg");
			log.setException("message:"+ex.getMessage()+",StackTrace:"+ex.getStackTrace().toString());
			log.setCreateDate(new Date());
			logService.save(log);
			
			ex.printStackTrace();
			return "{code:5000,message:\""+ex.getMessage()+"\"}";
		}
	}
	
	/**
	 * 商家取消订单消息（商品、课程、服务、场地）
	 * @param businessId	商家Id
	 * @param businessName	商家名称
	 * @param cancelReason	取消原因
	 * @param orderId		订单号
	 * @param toUserId		发送用户Id
	 * @param sendType		发送消息函数类型
	 * @return				{code,message}
	 * @throws Exception
	 */
	@RequestMapping(value="cancelOrderMsg",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String cancelOrderMsg(String businessId,String businessName,String cancelReason,String orderId,String toUserId,String sendType) throws Exception{
		try{
			RongCloud rongCloud = RongCloud.getInstance(RongGlobal.APP_KEY, RongGlobal.APP_SECRET);
			String[] messagePublishSystemToUserId = new String[1];
			messagePublishSystemToUserId[0] = toUserId;
			String msgContent = "您在["+businessName+"]下的订单已被商家取消，订单号："+orderId+"，取消原因："+cancelReason+"（点击查看详情）";
			String msgExtra = "{\"sendType\":\""+sendType+"\",\"orderType\":\"1\",\"orderId\":\""+orderId+"\","
					+ "\"fromUserId\":\""+businessId+"\","
					+ "\"toUserId\":\""+toUserId+"\","
					+ "\"msgContent\":\""+msgContent+"\""
					+"}";
			TxtMessage messagePublishSystemTxtMessage = new TxtMessage(msgContent,msgExtra);
			rongCloud.message.PublishSystem(businessId, messagePublishSystemToUserId, messagePublishSystemTxtMessage, msgExtra, msgExtra, 1, 1);
			
			SocialMsg socialMsg = new SocialMsg();
			socialMsg.setIsNewRecord(true);
			socialMsg.setId(UUID.randomUUID().toString());
			socialMsg.setUserid(businessId);
			socialMsg.setUsername(businessName);
			socialMsg.setTouserid(toUserId);
			socialMsg.setContent(msgExtra);
			socialMsg.setIsnotice(RongGlobal.MSG_IS_NOTICE_YES);
			socialMsg.setNoticetime(new Date());
			socialMsg.setFirtype(RongGlobal.MSG_FIRTYPE_ORDER);
			socialMsg.setSectype(RongGlobal.MSG_SECTYPE_QXDD);
			socialMsg.setIsread(RongGlobal.MSG_IS_READ_NO);
			socialMsgService.save(socialMsg);
			
			return "{code:1000,message:\"\"}";
		}catch (Exception ex) {
			
			com.its.modules.sys.entity.Log log = new com.its.modules.sys.entity.Log();
			
			log.setIsNewRecord(true);
			log.setId(UUID.randomUUID().toString());
			log.setType(com.its.modules.sys.entity.Log.TYPE_Exception);
			log.setTitle("cancelOrderMsg");
			log.setException("message:"+ex.getMessage()+",StackTrace:"+ex.getStackTrace().toString());
			log.setCreateDate(new Date());
			logService.save(log);
			
			ex.printStackTrace();
			return "{code:5000,message:\""+ex.getMessage()+"\"}";
		}
	}
	

	/**
	 * 团购券已消费消息（团购）
	 * @param businessId	商家Id
	 * @param businessName	商家名称
	 * @param eventName		活动名称
	 * @param toUserId		发送用户Id
	 * @return				{code,message}
	 * @throws Exception
	 */
	@RequestMapping(value="ticketConsumeMsg",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String ticketConsumeMsg(String businessId,String businessName,String eventName,String orderId,String toUserId,String sendType) throws Exception{
		try{
			RongCloud rongCloud = RongCloud.getInstance(RongGlobal.APP_KEY, RongGlobal.APP_SECRET);
			String[] messagePublishSystemToUserId = new String[1];
			messagePublishSystemToUserId[0] = toUserId;
			String msgContent = "您购买的["+eventName+"]已消费成功。（点击查看详情）【"+businessName+"】";
			String msgExtra = "{\"sendType\":\""+sendType+"\",\"orderType\":\"5\",\"orderId\":\""+orderId+"\","
					+ "\"fromUserId\":\""+businessId+"\","
					+ "\"toUserId\":\""+toUserId+"\","
					+ "\"msgContent\":\""+msgContent+"\""
					+"}";
			TxtMessage messagePublishSystemTxtMessage = new TxtMessage(msgContent,msgExtra);
			rongCloud.message.PublishSystem(businessId, messagePublishSystemToUserId, messagePublishSystemTxtMessage, msgExtra, msgExtra, 1, 1);
			
			SocialMsg socialMsg = new SocialMsg();
			socialMsg.setIsNewRecord(true);
			socialMsg.setId(UUID.randomUUID().toString());
			socialMsg.setUserid(businessId);
			socialMsg.setUsername(businessName);
			socialMsg.setTouserid(toUserId);
			socialMsg.setContent(msgExtra);
			socialMsg.setIsnotice(RongGlobal.MSG_IS_NOTICE_YES);
			socialMsg.setNoticetime(new Date());
			socialMsg.setFirtype(RongGlobal.MSG_FIRTYPE_ORDER);
			socialMsg.setSectype(RongGlobal.MSG_SECTYPE_TGYXF);
			socialMsg.setIsread(RongGlobal.MSG_IS_READ_NO);
			socialMsgService.save(socialMsg);
			
			return "{code:1000,message:\"\"}";
		}catch (Exception ex) {
			
			com.its.modules.sys.entity.Log log = new com.its.modules.sys.entity.Log();
			
			log.setIsNewRecord(true);
			log.setId(UUID.randomUUID().toString());
			log.setType(com.its.modules.sys.entity.Log.TYPE_Exception);
			log.setTitle("ticketConsumeMsg");
			log.setException("message:"+ex.getMessage()+",StackTrace:"+ex.getStackTrace().toString());
			log.setCreateDate(new Date());
			logService.save(log);
			
			ex.printStackTrace();
			return "{code:5000,message:\""+ex.getMessage()+"\"}";
		}
	}
	
	/**
	 * 团购券已退款消息（团购）
	 * @param businessId	商家Id
	 * @param businessName	商家名称
	 * @param eventName		活动名称
	 * @param toUserId		发送用户Id
	 * @return				{code,message}
	 * @throws Exception
	 */
	@RequestMapping(value="ticketBackMsg",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String ticketBackMsg(String businessId,String businessName,String eventName,String orderId,String toUserId,String sendType) throws Exception{
		try{
			RongCloud rongCloud = RongCloud.getInstance(RongGlobal.APP_KEY, RongGlobal.APP_SECRET);
			String[] messagePublishSystemToUserId = new String[1];
			messagePublishSystemToUserId[0] = toUserId;
			
			String msgContent = "您购买的["+eventName+"]已退款成功。（点击查看详情）【"+businessName+"】";
			String msgExtra = "{\"sendType\":\""+sendType+"\",\"orderType\":\"5\",\"orderId\":\""+orderId+"\","
					+ "\"fromUserId\":\""+businessId+"\","
					+ "\"toUserId\":\""+toUserId+"\","
					+ "\"msgContent\":\""+msgContent+"\""
					+"}";
			TxtMessage messagePublishSystemTxtMessage = new TxtMessage(msgContent,msgExtra);
			rongCloud.message.PublishSystem(businessId, messagePublishSystemToUserId, messagePublishSystemTxtMessage, msgExtra, msgExtra, 1, 1);
			
			SocialMsg socialMsg = new SocialMsg();
			socialMsg.setUserid(businessId);
			socialMsg.setUsername(businessName);
			socialMsg.setTouserid(toUserId);
			socialMsg.setContent(msgExtra);
			socialMsg.setIsnotice(RongGlobal.MSG_IS_NOTICE_YES);
			socialMsg.setNoticetime(new Date());
			socialMsg.setFirtype(RongGlobal.MSG_FIRTYPE_ORDER);
			socialMsg.setSectype(RongGlobal.MSG_SECTYPE_TGTK);
			socialMsg.setIsread(RongGlobal.MSG_IS_READ_NO);
			socialMsgService.save(socialMsg);
			return "{code:1000,message:\"\"}";
		}catch (Exception ex) {
			
			com.its.modules.sys.entity.Log log = new com.its.modules.sys.entity.Log();
			
			log.setIsNewRecord(true);
			log.setId(UUID.randomUUID().toString());
			log.setType(com.its.modules.sys.entity.Log.TYPE_Exception);
			log.setTitle("ticketBackMsg");
			log.setException("message:"+ex.getMessage()+",StackTrace:"+ex.getStackTrace().toString());
			log.setCreateDate(new Date());
			logService.save(log);
			
			ex.printStackTrace();
			return "{code:5000,message:\""+ex.getMessage()+"\"}";
		}
	}
	
	
	
	
//	/**
//	 * 公告通知
//	 * @param fromId 		发送用户Id
//	 * @param noticeTitle	公告Title
//	 * @param noticeSummary	公告Summary
//	 * @return				{code,message}
//	 * @throws Exception
//	 */
//	@RequestMapping(value="noticeMsg",method = {RequestMethod.POST,RequestMethod.GET})
//	@ResponseBody
//	public String noticeMsg(String fromId,String noticeTitle,String noticeSummary) throws Exception{
//		try{
//			RongCloud rongCloud = RongCloud.getInstance(RongGlobal.APP_KEY, RongGlobal.APP_SECRET);
//			TxtMessage messagePublishSystemTxtMessage = new TxtMessage(noticeTitle+"\r\n"+noticeSummary,"");
//			
//			String[] messagePublishSystemToUserId = new String[100];
//			int i_msg = 0;
//			AccountService accountService = new AccountService();
//			List<Account> list_account = accountService.findAllList();
//			if(list_account!=null && !list_account.isEmpty()){
//				for (int i = 0 ; i<list_account.size();i++) {
//					Account account = list_account.get(i);
//					messagePublishSystemToUserId[i_msg] = account.getCurrentUser().getId();
//					if(i_msg==99){
//						rongCloud.message.PublishSystem(fromId, messagePublishSystemToUserId, messagePublishSystemTxtMessage, "", "", 1, 1);
//						Thread.sleep(1000);
//						i_msg = 0;
//					}else if(i == list_account.size() -1){
//						String[] ToUserId_tmp = new String[i_msg+1];
//						for (int j = 0;j<ToUserId_tmp.length;j++) {
//							ToUserId_tmp[j] = messagePublishSystemToUserId[j];
//							rongCloud.message.PublishSystem(fromId, ToUserId_tmp, messagePublishSystemTxtMessage, "", "", 1, 1);
//							Thread.sleep(1000);
//							i_msg = 0;
//						}
//					}else{
//						i_msg++;
//					}
//				}
//			}
//			return "{code:1000,message:\"\"}";
//		}catch (Exception ex) {
//			return "{code:5000,message:\""+ex.getMessage()+"\"}";
//		}
//	}
	
}
