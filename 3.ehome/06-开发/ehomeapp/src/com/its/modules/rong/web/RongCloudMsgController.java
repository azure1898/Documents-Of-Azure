
package com.its.modules.rong.web;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.mail.search.DateTerm;

import org.apache.poi.xslf.model.geom.Guide;
import org.apache.taglibs.standard.tag.common.xml.ForEachTag;
import org.apache.tomcat.util.log.SystemLogHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.support.logging.Log;
import com.its.modules.app.bean.OrderGroupPurcRCBean;
import com.its.modules.app.entity.Account;
import com.its.modules.app.entity.RoomCertify;
import com.its.modules.app.service.AccountService;
import com.its.modules.app.service.OrderGroupPurcService;
import com.its.modules.app.service.RoomCertifyService;
import com.its.modules.rong.common.RongGlobal;
import com.its.modules.rong.entity.SocialMsg;
import com.its.modules.rong.service.SocialMsgService;
import com.its.modules.sys.service.LogService;

import io.rong.RongCloud;
import io.rong.messages.TxtMessage;
import io.rong.models.CodeSuccessResult;

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
	AccountService accountService = new AccountService();
	@Autowired
	private  LogService logService;
	
	@Autowired
	private RoomCertifyService roomCertifyService;
	
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
			CodeSuccessResult csr = rongCloud.message.PublishSystem(businessId, messagePublishSystemToUserId, messagePublishSystemTxtMessage, msgExtra, msgExtra, 1, 1);
			
			SocialMsg socialMsg = new SocialMsg();
			socialMsg.setIsNewRecord(true);
			socialMsg.setId(UUID.randomUUID().toString());
			socialMsg.setUserid(businessId);
			socialMsg.setUsername(businessName);
			socialMsg.setTouserid(toUserId);
			socialMsg.setContent(msgContent);
			socialMsg.setRemark("{\"Code\":\""+csr.getCode()+"\",\"ErrorMessage\":\""+csr.getErrorMessage()+"\",\"msgExtra\":\""+msgExtra+"\"}");
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
			CodeSuccessResult csr = rongCloud.message.PublishSystem(businessId, messagePublishSystemToUserId, messagePublishSystemTxtMessage, msgExtra, msgExtra, 1, 1);
			
			SocialMsg socialMsg = new SocialMsg();
			socialMsg.setIsNewRecord(true);
			socialMsg.setId(UUID.randomUUID().toString());
			socialMsg.setUserid(businessId);
			socialMsg.setUsername(businessName);
			socialMsg.setTouserid(toUserId);
			socialMsg.setContent(msgContent);
			socialMsg.setRemark("{\"Code\":\""+csr.getCode()+"\",\"ErrorMessage\":\""+csr.getErrorMessage()+"\",\"msgExtra\":\""+msgExtra+"\"}");
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
			CodeSuccessResult csr = rongCloud.message.PublishSystem(businessId, messagePublishSystemToUserId, messagePublishSystemTxtMessage, msgExtra, msgExtra, 1, 1);
			
			SocialMsg socialMsg = new SocialMsg();
			socialMsg.setIsNewRecord(true);
			socialMsg.setId(UUID.randomUUID().toString());
			socialMsg.setUserid(businessId);
			socialMsg.setUsername(businessName);
			socialMsg.setTouserid(toUserId);
			socialMsg.setContent(msgContent);
			socialMsg.setRemark("{\"Code\":\""+csr.getCode()+"\",\"ErrorMessage\":\""+csr.getErrorMessage()+"\",\"msgExtra\":\""+msgExtra+"\"}");
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
			CodeSuccessResult csr = rongCloud.message.PublishSystem(businessId, messagePublishSystemToUserId, messagePublishSystemTxtMessage, msgExtra, msgExtra, 1, 1);
			
			SocialMsg socialMsg = new SocialMsg();
			socialMsg.setUserid(businessId);
			socialMsg.setUsername(businessName);
			socialMsg.setTouserid(toUserId);
			socialMsg.setContent(msgContent);
			socialMsg.setRemark("{\"Code\":\""+csr.getCode()+"\",\"ErrorMessage\":\""+csr.getErrorMessage()+"\",\"msgExtra\":\""+msgExtra+"\"}");
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
	
	
	
	/**
	 * 公告通知
	 * @param fromId 		发送用户Id
	 * @param noticeId 		公告Id
	 * @param noticeTitle	公告Title
	 * @param noticeSummary	公告Summary
	 * @param villageId		楼盘Id	
	 * @return				{code,message}
	 * @throws Exception
	 */
	@RequestMapping(value="noticeMsg",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String noticeMsg(String fromId,String noticeId,String noticeTitle,String noticeSummary,String villageId,String sendType) throws Exception{
		try{
			RongCloud rongCloud = RongCloud.getInstance(RongGlobal.APP_KEY, RongGlobal.APP_SECRET);
			
			String msgContent = noticeTitle+"<br>"+noticeSummary+">>详细";
			String[] messagePublishSystemToUserId = new String[1];
						
			List<Account> list_account = accountService.findListByVillage(villageId);
			if(list_account!=null && !list_account.isEmpty()){
				for (int i = 0 ; i<list_account.size();i++) {
					Account account = list_account.get(i);
					messagePublishSystemToUserId[0] = account.getId();
					String msgExtra = "{\"sendType\":\""+sendType+"\",\"noticeId\":\""+noticeId+"\","
							+ "\"fromUserId\":\""+fromId+"\","
							+ "\"toUserId\":\""+account.getId()+"\","
							+ "\"msgContent\":\""+msgContent+"\""
							+"}";
					TxtMessage messagePublishSystemTxtMessage = new TxtMessage(msgContent,msgExtra);
					CodeSuccessResult csr = rongCloud.message.PublishSystem(fromId, messagePublishSystemToUserId, messagePublishSystemTxtMessage, msgExtra, msgExtra, 1, 1);
					
					SocialMsg socialMsg = new SocialMsg();
					socialMsg.setUserid(fromId);
					socialMsg.setUsername("");
					socialMsg.setTouserid(account.getId());
					socialMsg.setContent(msgContent);
					socialMsg.setRemark("{\"Code\":\""+csr.getCode()+"\",\"ErrorMessage\":\""+csr.getErrorMessage()+"\",\"msgExtra\":\""+msgExtra+"\"}");
					socialMsg.setIsnotice(RongGlobal.MSG_IS_NOTICE_YES);
					socialMsg.setNoticetime(new Date());
					socialMsg.setFirtype(RongGlobal.MSG_FIRTYPE_ADMIN);
					socialMsg.setSectype(RongGlobal.MSG_SECTYPE_GGTZ);
					socialMsg.setIsread(RongGlobal.MSG_IS_READ_NO);
					socialMsgService.save(socialMsg);
					
					if(i%99==0)
						Thread.sleep(1000);
				}
			}
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
	
	/**
	 * 发言消息
	 * @param fromId 		发送用户Id
	 * @param noticeId 		公告Id
	 * @param noticeTitle	公告Title
	 * @param noticeSummary	公告Summary
	 * @param villageIds		楼盘Ids,支持多个，使用逗号隔开。	
	 * @param sendType		消息类型	
	 * @return				{code,message}
	 * @throws Exception
	 */
	@RequestMapping(value="speakMsg",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String speakMsg(String fromId,String noticeId,String noticeTitle,String noticeSummary,String villageIds,String sendType) throws Exception{
		try{
			RongCloud rongCloud = RongCloud.getInstance(RongGlobal.APP_KEY, RongGlobal.APP_SECRET);
			
			String msgContent = noticeTitle+"<br>"+noticeSummary+">>详细";
			String[] messagePublishSystemToUserId = new String[1];
			
			String[] arr_villageId = villageIds.split(",");
			
			for (String villageId : arr_villageId) {
							
				List<Account> list_account = accountService.findListByVillage(villageId);
				if(list_account!=null && !list_account.isEmpty()){
					for (int i = 0 ; i<list_account.size();i++) {
						Account account = list_account.get(i);
						messagePublishSystemToUserId[0] = account.getId();
						String msgExtra = "{\"sendType\":\""+sendType+"\",\"noticeId\":\""+noticeId+"\","
								+ "\"fromUserId\":\""+fromId+"\","
								+ "\"toUserId\":\""+account.getId()+"\","
								+ "\"msgContent\":\""+msgContent+"\""
								+"}";
						TxtMessage messagePublishSystemTxtMessage = new TxtMessage(msgContent,msgExtra);
						CodeSuccessResult csr = rongCloud.message.PublishSystem(fromId, messagePublishSystemToUserId, messagePublishSystemTxtMessage, msgExtra, msgExtra, 1, 1);
						
						SocialMsg socialMsg = new SocialMsg();
						socialMsg.setUserid(fromId);
						socialMsg.setUsername("");
						socialMsg.setTouserid(account.getId());
						socialMsg.setContent(msgContent);
						socialMsg.setRemark("{\"Code\":\""+csr.getCode()+"\",\"ErrorMessage\":\""+csr.getErrorMessage()+"\",\"msgExtra\":\""+msgExtra+"\"}");
						socialMsg.setIsnotice(RongGlobal.MSG_IS_NOTICE_YES);
						socialMsg.setNoticetime(new Date());
						socialMsg.setFirtype(RongGlobal.MSG_FIRTYPE_ADMIN);
						socialMsg.setSectype(RongGlobal.MSG_SECTYPE_GLYFY);
						socialMsg.setIsread(RongGlobal.MSG_IS_READ_NO);
						socialMsgService.save(socialMsg);
						
						if(i%99==0)
							Thread.sleep(1000);
					}
				}
			}
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
	
	
}
