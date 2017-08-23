package com.its.modules.task;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.its.common.utils.AlipayUtils;
import com.its.common.utils.DateUtils;
import com.its.common.utils.StringUtils;
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
	 * 
	 * @throws IOException
	 */
	@Scheduled(cron = "5 * * * * *")
	public void createDownloadBillTask() throws IOException {
		List<DownloadBill> downloadBillList = new ArrayList<DownloadBill>();
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
			/* 添加微信对账单 */
			downloadBillList = this.getWXDownloadBillList(billStr, billDate);
			System.out.println("微信对账单条数：" + downloadBillList.size());
		}

		/* 添加支付宝对账单 */
		List<DownloadBill> alipayDownloadBillList = this.getAlipayDownloadBillList(billDate);
		System.out.println("支付宝对账单条数：" + alipayDownloadBillList.size());
		downloadBillList.addAll(alipayDownloadBillList);
		downloadBillService.saveDownloadBillList(downloadBillList);

		// 批量处理某时间段的对账单
		// this.createBillByTimeSpan("20170801","20170816");

	}

	/**
	 * 将微信接口返回的对账单转换成可入库的对账单集合
	 * 
	 * @param billStr
	 * @param billDate
	 * @return
	 */
	private List<DownloadBill> getWXDownloadBillList(String billStr, String billDate) {

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
	 * 将支付宝接口返回的对账单转换成可入库的对账单集合
	 * 
	 * @param billDate
	 * @return
	 * @throws IOException
	 */
	private List<DownloadBill> getAlipayDownloadBillList(String billDate) throws IOException {
		// 将billDate转换，由20170818转成2017-08-18的形式
		Date date = DateUtils.parseDate(billDate);
		String billDateLine = DateUtils.formatDate(date, "yyyy-MM-dd");
		// TODO: 待删除日志
		System.out.println("billDateLine---:" + billDateLine);

		// 定义初始变量
		List<DownloadBill> downloadBillList = new ArrayList<DownloadBill>();
		StringBuffer sb = new StringBuffer();

		// 获取支付宝zip对账单文件路径
		String filePath = AlipayUtils.dealAlipayDownloadBill(billDateLine);
		if (filePath != null) {
			File file = new File(filePath);
			sb.append(AlipayUtils.readZipCvsFile(file));
		}

		// 将字符串转成流
		ByteArrayInputStream bis = new ByteArrayInputStream(sb.toString().getBytes());
		InputStreamReader isr = new InputStreamReader(bis);
		BufferedReader br = new BufferedReader(isr);
		try {
			String line = "";
			while ((line = br.readLine()) != null) {
				if (line.contains("支付宝交易号,商户订单号")) {
					// 跳过第一行标题
				} else if (line.startsWith("#")) {
					// 跳过最后四行汇总
				} else {
					// 对对账单中间部分的可转化为DownloadBill对象部分进行处理
					// System.out.println(line);
					String[] downloadBillArr = line.split(",");
					DownloadBill downloadBill = new DownloadBill();
					String tradeTime = downloadBillArr[6].trim();
					String deviceInfo = StringUtils.replaceBlank(downloadBillArr[9]);
					String transactionId = StringUtils.replaceBlank(downloadBillArr[0]);
					String outTradeNo = StringUtils.replaceBlank(downloadBillArr[1]);
					String openid = StringUtils.replaceBlank(downloadBillArr[10]);
					String tradeType = null;
					String tradeState = StringUtils.replaceBlank(downloadBillArr[2]);
					;
					String bankType = null;
					String totalFee = StringUtils.replaceBlank(downloadBillArr[12]); // 商家实收（元）
					String productName = StringUtils.replaceBlank(downloadBillArr[3]);
					String productData = StringUtils.replaceBlank(downloadBillArr[24]); // 备注
					String costFee = StringUtils.replaceBlank(downloadBillArr[22]); // 服务费（元）
					String costRate = StringUtils.replaceBlank(downloadBillArr[23]); // 分润（元）

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
					downloadBill.setCostRate(Double.parseDouble(costRate));

					// 额外增加两个字段
					downloadBill.setBillType("1"); // 微信对账单类型
					downloadBill.setBillDate(billDate);

					// 将对账单对象加入list里
					if ("交易".equals(downloadBill.getTradeState())) {
						downloadBillList.add(downloadBill);
					}
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
	 * @throws IOException
	 */
	private void createBillByTimeSpan(String billStartDate, String billEndDate) throws IOException {
		Date startDate = DateUtils.parseDate(billStartDate);
		Date endDate = DateUtils.parseDate(billEndDate);
		long days = (endDate.getTime() - startDate.getTime()) / 1000 / 60 / 60 / 24;

		for (int i = 0; i < days; i++) {
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
				// 赋值微信对账单
				List<DownloadBill> downloadBillList = this.getWXDownloadBillList(billStr, billCurrDate);
				// 添加支付宝对账单
				downloadBillList.addAll(this.getAlipayDownloadBillList(billCurrDate));
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

	public static void main(String[] args) {
		String filePath = AlipayUtils.dealAlipayDownloadBill("2017-08-18");
		if (filePath != null) {
			File file = new File(filePath);
			try {
				AlipayUtils.readZipCvsFile(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
