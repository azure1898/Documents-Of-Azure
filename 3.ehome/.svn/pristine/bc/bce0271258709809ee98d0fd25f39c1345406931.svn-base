package com.its.modules.rabbitmq.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.its.modules.account.entity.Account;
import com.its.modules.account.service.AccountService;
import com.its.modules.rabbitmq.dao.DataLogDao;
import com.its.modules.rabbitmq.dao.ProductFlowDao;
import com.its.modules.rabbitmq.entity.DataLog;
import com.its.modules.rabbitmq.entity.ProductFlow;

@Service
public class QueueProductFlow implements MessageListener {
	@Autowired
	AccountService accountService;
	@Autowired
	ProductFlowDao productFlowDao;
	@Autowired
	DataLogDao dataLogDao;
	
	private static Map<String, String> accountMap = new HashMap<String, String>();

	@Override
	public void onMessage(Message message) {
		String msg = new String((byte[]) message.getBody());
		//日志记录
		DataLog dl = new DataLog(msg,"1","0");
		JSONArray jmessage = JSONArray.fromObject(msg);
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
		List<ProductFlow> list = new ArrayList<ProductFlow>();
		Iterator<Object> it = jmessage.iterator();
		while (it.hasNext()) {
			ProductFlow productFlow = (ProductFlow) JSONObject.toBean((JSONObject) it.next(), ProductFlow.class);
			// 填入会员id
			productFlow.setMemberId(accountMap.get(productFlow.getPhoneNumber()));
			// 填入id,创建时间
			productFlow.preInsert();
			list.add(productFlow);
		}
		// 批量插入
		productFlowDao.batchInsert(list);
		
		//插入日志
		dl.preInsert();
		dataLogDao.insert(dl);
	}

}
