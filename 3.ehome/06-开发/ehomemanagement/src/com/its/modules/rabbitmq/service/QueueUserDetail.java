package com.its.modules.rabbitmq.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.its.modules.account.entity.Account;
import com.its.modules.account.service.AccountService;
import com.its.modules.rabbitmq.dao.DataLogDao;
import com.its.modules.rabbitmq.dao.UserDetailDao;
import com.its.modules.rabbitmq.entity.DataLog;
import com.its.modules.rabbitmq.entity.UserDetail;

@Service
public class QueueUserDetail implements MessageListener {

	@Autowired
	AccountService accountService;
	@Autowired
	UserDetailDao userDetailDao;
	@Autowired
	DataLogDao dataLogDao;
	
	private static Map<String, String> accountMap = new HashMap<String, String>();

	@Override
	public void onMessage(Message message) {
		String msg = new String((byte[]) message.getBody());
		//日志记录
		DataLog dl = new DataLog(msg,"0","0");
		
		JSONObject jmessage = JSONObject.fromObject(msg);
		UserDetail userDetail = (UserDetail) JSONObject.toBean(jmessage,
				UserDetail.class);
		// 获取所有会员信息
		if (accountMap.size() <= 0) {
			List<Account> accountList = accountService.findList(new Account());
			for (Account a : accountList) {
				if (a.getPhoneNum() != null
						&& accountMap.get(a.getPhoneNum()) == null) {
					accountMap.put(a.getPhoneNum(), a.getId());
				}
			}
		}
		// 填入会员id
		userDetail.setMemberId(accountMap.get(userDetail.getPhoneNumber()));
		if(userDetail.getMemberId()!=null && userDetail.getMemberId()!=""){
			dl.setCheckState("1");
		}
		// 插入
		userDetail.preInsert();
		userDetailDao.insert(userDetail);
		//插入日志
		dl.preInsert();
		dataLogDao.insert(dl);
	}

}
