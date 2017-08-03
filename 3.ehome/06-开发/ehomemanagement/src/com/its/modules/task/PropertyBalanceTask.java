package com.its.modules.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.its.common.web.BaseController;
import com.its.modules.balance.entity.PropertyBalance;
import com.its.modules.balance.service.PropertyBalanceService;


@Component
public class PropertyBalanceTask extends BaseController {
	
	@Autowired
	private PropertyBalanceService propertyBalanceService;

	/**
	 * cron表达式：* * * * * *（共6位，使用空格隔开，具体如下） 
	 * cron表达式：*(秒0-59) *(分钟0-59) *(小时0-23) *(日期1-31) *(月份1-12或是JAN-DEC) *(星期1-7或是SUN-SAT)
	 */

	/**
	 * 定时卡点计算。每天凌晨 02:00 执行一次
	 */ 
	@Scheduled(cron = "0 0 2 * * *")
	public void createPropertyBalanceTask() {
		logger.warn("---------物业结算 开始-----------");
		// 1.根据物业缴费单生成物业结算信息
		List<PropertyBalance> propertyBalanceList = propertyBalanceService.findListByPropertyBill();
		logger.warn("商品类待结算订单："+propertyBalanceList.size()+"笔");
		for (PropertyBalance propertyBalance : propertyBalanceList) {
			propertyBalanceService.save(propertyBalance);			
		}
		logger.warn("---------物业结算 结束-----------");
	}

}
