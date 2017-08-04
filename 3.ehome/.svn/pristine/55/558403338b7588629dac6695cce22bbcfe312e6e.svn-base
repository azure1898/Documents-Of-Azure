package com.its.modules.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.its.common.web.BaseController;
import com.its.modules.balance.entity.BusinessBalance;
import com.its.modules.balance.service.BusinessBalanceService;


@Component("businessBalanceTask")
public class BusinessBalanceTask extends BaseController {

//	@Autowired
//	private BusinessInfoService businessInfoService;

	@Autowired
	private BusinessBalanceService businessBalanceService;

	/**
	 * cron表达式：* * * * * *（共6位，使用空格隔开，具体如下） 
	 * cron表达式：*(秒0-59) *(分钟0-59) *(小时0-23) *(日期1-31) *(月份1-12或是JAN-DEC) *(星期1-7或是SUN-SAT)
	 */

	/**
	 * 定时卡点计算。每天凌晨 02:00 执行一次
	 */ 
	@Scheduled(cron = "5 * * * * *")
	public void createBusinessBalanceTask() {
		logger.warn("---------商品类 商家结算 开始-----------");
		// 1.根据订单-商品类生成商家结算信息；
		List<BusinessBalance> businessBalanceList = businessBalanceService.findListByOrderGoods();
		logger.warn("商品类待结算订单："+businessBalanceList.size()+"笔");
		for (BusinessBalance businessBalance : businessBalanceList) {
			businessBalanceService.save(businessBalance, 0);			
		}
		logger.warn("---------商品类 商家结算 结束-----------");
		
		// 2.根据订单-服务类生成商家结算信息
		logger.warn("---------服务类 商家结算 开始-----------");
		businessBalanceList = businessBalanceService.findListByOrderService();
		logger.warn("订单-服务类待结算订单："+businessBalanceList.size()+"笔");
		for (BusinessBalance businessBalance : businessBalanceList) {
			businessBalanceService.save(businessBalance, 1);			
		}
		logger.warn("---------服务类 商家结算 结束-----------");
		
		// 3.根据订单-课程培训类生成商家结算信息
		logger.warn("---------课程培训类 商家结算 开始-----------");
		businessBalanceList = businessBalanceService.findListByOrderLesson();
		logger.warn("订单-课程培训类待结算订单："+businessBalanceList.size()+"笔");
		for (BusinessBalance businessBalance : businessBalanceList) {
			businessBalanceService.save(businessBalance, 2);
		}
		logger.warn("---------课程培训类 商家结算 结束-----------");	
		
		// 4.根据订单-场地类生成商家结算信息
		logger.warn("---------场地类 商家结算 开始-----------");
		businessBalanceList = businessBalanceService.findListByOrderField();
		logger.warn("订单-场地类待结算订单："+businessBalanceList.size()+"笔");
		for (BusinessBalance businessBalance : businessBalanceList) {
			businessBalanceService.save(businessBalance, 3);			
		}
		logger.warn("---------场地类 商家结算 结束-----------");
		
		// 5.根据订单-团购类生成商家结算信息
		logger.warn("---------团购类 商家结算 开始-----------");
		businessBalanceList = businessBalanceService.findListByOrderGroupPurc();
		logger.warn("订单-团购类待结算订单："+businessBalanceList.size()+"笔");
		for (BusinessBalance businessBalance : businessBalanceList) {
			businessBalanceService.save(businessBalance, 4);			
		}
		logger.warn("---------团购类 商家结算  结束-----------");

	}

}
