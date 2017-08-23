package com.its.modules.rong.task;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.its.modules.app.bean.OrderGroupPurcRCBean;
import com.its.modules.app.service.OrderGroupPurcService;
import com.its.modules.rong.common.RongGlobal;
import com.its.modules.rong.entity.SocialMsg;
import com.its.modules.rong.service.SocialMsgService;
import com.its.modules.rong.web.RongCloudMsgController;
import com.its.modules.sys.service.LogService;

import io.rong.RongCloud;
import io.rong.messages.TxtMessage;
import io.rong.models.CodeSuccessResult;

@Service @Lazy(false)
public class RongCloudTask {

	@Autowired
	private SocialMsgService socialMsgService;
	
	@Autowired
	private OrderGroupPurcService orderGroupPurcService;
	
	@Autowired
	private  LogService logService;
	
    /** 
     * cron表达式：* * * * * *（共6位，使用空格隔开，具体如下）  
     * cron表达式：*(秒0-59) *(分钟0-59) *(小时0-23) *(日期1-31) *(月份1-12或是JAN-DEC) *(星期1-7或是SUN-SAT)  
     * 注意： 30 * * * * * 表示每分钟的第30秒执行，而（*斜杠30）表示每30秒执行 
     *  
     * */ 
	@Scheduled(cron = RongGlobal.Group_Cron)
    public void firstTask(){  
        System.out.println("==============it is first task!时间：");  
        //rongCloudMsgController.TicketExpireMsg();
        TicketExpireMsg();
    }
	
	/**
	 * 团购券临近期消息自动发送
	 */
	public void TicketExpireMsg() {
		RongCloud rongCloud = RongCloud.getInstance(RongGlobal.APP_KEY, RongGlobal.APP_SECRET);
		List<OrderGroupPurcRCBean> list_orders = orderGroupPurcService.findTicketExpireMsg(RongGlobal.Group_NumDays);
		String[] ToUserId = new String[1];
		if(list_orders!=null && !list_orders.isEmpty()){
			for (int i = 0 ; i<list_orders.size();i++) {
				try{
					OrderGroupPurcRCBean order = list_orders.get(i);
					ToUserId[0] = order.getAccountId();
					String msgContent = "您购买的["+order.getGroupPurcName()+"] 团购券马上就要到期了，截止日期"+order.getValidityEndTime();
					String msgExtra = "{\"sendType\":\"2.3\",\"orderType\":\"5\",\"orderId\":\""+order.getOrderNo()+"\","
							+ "\"fromUserId\":\""+order.getBusinessinfoId()+"\","
							+ "\"toUserId\":\""+ToUserId[0]+"\","
							+ "\"msgContent\":\""+msgContent+"\""
							+"}";
					TxtMessage messagePublishSystemTxtMessage = new TxtMessage(msgContent,msgExtra);
					CodeSuccessResult csr = rongCloud.message.PublishSystem(order.getBusinessinfoId(), ToUserId, messagePublishSystemTxtMessage, msgExtra, msgExtra, 1, 1);
					
					SocialMsg socialMsg = new SocialMsg();
					socialMsg.setIsNewRecord(true);
					socialMsg.setId(UUID.randomUUID().toString());
					socialMsg.setUserid(order.getBusinessinfoId());
					socialMsg.setUsername("");
					socialMsg.setTouserid(ToUserId[0]);
					socialMsg.setContent(msgContent);
					socialMsg.setRemark("{\"Code\":\""+csr.getCode()+"\",\"ErrorMessage\":\""+csr.getErrorMessage()+"\",\"msgExtra\":\""+msgExtra+"\"}");
					socialMsg.setIsnotice(RongGlobal.MSG_IS_NOTICE_YES);
					socialMsg.setNoticetime(new Date());
					socialMsg.setFirtype(RongGlobal.MSG_FIRTYPE_ORDER);
					socialMsg.setSectype(RongGlobal.MSG_SECTYPE_LQTX);
					socialMsg.setIsread(RongGlobal.MSG_IS_READ_NO);
					System.out.println("id:"+socialMsg.getId());
					socialMsgService.save(socialMsg);
					
					if(i%99==0)
						Thread.sleep(1000);
					
					}catch (Exception ex) {
						com.its.modules.sys.entity.Log log = new com.its.modules.sys.entity.Log();
						log.setIsNewRecord(true);
						log.setId(UUID.randomUUID().toString());
						log.setType(com.its.modules.sys.entity.Log.TYPE_Exception);
						log.setTitle("TicketExpireMsg");
						log.setException("message:"+ex.getMessage()+",StackTrace:"+ex.getStackTrace().toString());
						log.setCreateDate(new Date());
						logService.save(log);
					}
				}
			}
		}

	
	
}
