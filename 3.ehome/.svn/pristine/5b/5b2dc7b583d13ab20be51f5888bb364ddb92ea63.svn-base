package com.its.modules.task;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.its.common.utils.DateUtils;
import com.its.common.utils.WXUtils;
import com.its.common.web.BaseController;
import com.its.modules.balance.entity.DownloadBill;
import com.its.modules.balance.service.DownloadBillService;

@Component("downloadBillTask")
public class DownloadBillTask extends BaseController {
	private static Properties props = new Properties();
	@Autowired
	private DownloadBillService downloadBillService;

	/**
	 * cron表达式：* * * * * *（共6位，使用空格隔开，具体如下） cron表达式：*(秒0-59) *(分钟0-59) *(小时0-23)
	 * *(日期1-31) *(月份1-12或是JAN-DEC) *(星期1-7或是SUN-SAT)
	 */

	/**
	 * 定时卡点计算。每天上午 10:00 执行一次
	 */
	@Scheduled(cron = "0 0 10 * * *")
	public void createDownloadBillTask() {

		// 每天下载前一天的微信对账单
		Calendar c = Calendar.getInstance();
		Date date = c.getTime();
		date = DateUtils.addDays(date, -1);
		String billDate = DateUtils.formatDate(date, "yyyyMMdd");
		String billStr = new String();
		Map<String, String> downloadBillMap = this.downloadWXBill(billDate);
		if (!downloadBillMap.get("return_code").equals("200")) {

			// 下载微信对账单失败
			String errMsg = new String();
			String errorCode = downloadBillMap.get("return_msg");
			if (errorCode != null) {
				errorCode = errorCode.replaceAll(" ", "");
				errMsg = downloadBillMap.get("return_msg") + ":" + props.getProperty(errorCode);
			}
			logger.error(errMsg);

		} else {
			// 下载微信对账单成功
			billStr = downloadBillMap.get("return_msg");
			List<DownloadBill> downloadBillList = this.getDownloadBillList(billStr, billDate);
			downloadBillService.saveDownloadBillList(downloadBillList);
		}
		
		// 批量处理某时间段的对账单
		// this.createBillByTimeSpan("20170801","20170816");

	}

	/**
	 * 将微信接口返回的对账单转换成可入库的对账单集合
	 * 
	 * @return
	 */
	private List<DownloadBill> getDownloadBillList(String billStr, String billDate) {

		List<DownloadBill> downloadBillList = new ArrayList<DownloadBill>();
		
		// 将字符串转成流
		ByteArrayInputStream bis = new ByteArrayInputStream(billStr.getBytes());
		InputStreamReader isr = new InputStreamReader(bis);
		BufferedReader br = new BufferedReader(isr);
		try {
			String line = "";
			while ((line = br.readLine()) != null) {
				if (line.contains("交易时间,公众账号ID")) {

					// 跳过第一行标题行
					continue;
				} else if (line.contains("总交易单数,总交易额")) {

					// 跳过最后两行汇总金额
					break;
				} else {
					// 对对账单中间部分的可转化为DownloadBill对象部分进行处理
					System.out.println(line);
					String[] downloadBillArr = line.split(",`");
					DownloadBill downloadBill = new DownloadBill();
					String tradeTime = downloadBillArr[0].substring(1);
					String deviceInfo = downloadBillArr[4];
					String transactionId = downloadBillArr[5];
					String outTradeNo = downloadBillArr[6];
					String openid = downloadBillArr[7];
					String tradeType = downloadBillArr[8];
					String tradeState = downloadBillArr[9];
					String bankType = downloadBillArr[10];
					String totalFee = downloadBillArr[12];
					String productName = downloadBillArr[14];
					String productData = downloadBillArr[15];
					String costFee = downloadBillArr[16];
					String costRate = downloadBillArr[17];

					downloadBill.setTradeTime(DateUtils.parseDate(tradeTime));
					downloadBill.setDeviceInfo(deviceInfo);
					downloadBill.setTransactionId(transactionId);
					downloadBill.setOutTradeNo(outTradeNo);
					downloadBill.setOpenid(openid);
					downloadBill.setTradeType(tradeType);
					downloadBill.setTradeState(tradeState);
					downloadBill.setBankType(bankType);
					downloadBill.setTotalFee(Double.parseDouble(totalFee));
					downloadBill.setProductName(productName);
					downloadBill.setProductData(productData);
					downloadBill.setCostFee(Double.parseDouble(costFee));
					downloadBill.setCostRate(Double.parseDouble(costRate.split("%")[0]) / 100);

					// 额外增加两个字段
					downloadBill.setBillType("0"); // 微信对账单类型
					downloadBill.setBillDate(billDate);

					// 将对账单对象加入list里
					downloadBillList.add(downloadBill);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return downloadBillList;
	}

	/**
	 * 根据传入的时间段获取对账单集合
	 * 
	 * @param billStartDate
	 * @param billEndDate
	 * @return
	 */
	private void createBillByTimeSpan(String billStartDate, String billEndDate) {
		Date startDate = DateUtils.parseDate(billStartDate);
		Date endDate = DateUtils.parseDate(billEndDate);
		long days = (endDate.getTime()-startDate.getTime())/1000/60/60/24;
		
		for (int i = 0; i<days; i++) {
			Date currDate = DateUtils.addDays(startDate, i);
			String billCurrDate = DateUtils.formatDate(currDate, "yyyyMMdd");
			
			String billStr = new String();
			Map<String, String> downloadBillMap = this.downloadWXBill(billCurrDate);
			if (!downloadBillMap.get("return_code").equals("200")) {

				// 下载微信对账单失败
				String errMsg = new String();
				String errorCode = downloadBillMap.get("return_msg");
				if (errorCode != null) {
					errorCode = errorCode.replaceAll(" ", "");
					errMsg = downloadBillMap.get("return_msg") + ":" + props.getProperty(errorCode);
				}
				logger.error(errMsg);

			} else {
				// 下载微信对账单成功
				billStr = downloadBillMap.get("return_msg");
				List<DownloadBill> downloadBillList = this.getDownloadBillList(billStr, billCurrDate);
				downloadBillService.saveDownloadBillList(downloadBillList);
			}
		}
	}

	/**
	 * 根据对账日期下载对账单
	 * 
	 * @param billDate
	 * @return
	 */
	private Map<String, String> downloadWXBill(String billDate) {

		// 加载配置信息
		InputStream in = null;
		in = WXUtils.class.getClassLoader().getResourceAsStream("wx-config.properties");
		try {
			props.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String billType = "SUCCESS";
		Map<String, String> downloadBillMap = WXUtils.doDownloadBill(billDate, billType);

		return downloadBillMap;
	}

}
