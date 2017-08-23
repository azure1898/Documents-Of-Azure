package com.its.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Arrays;
import java.util.Properties;

import org.activiti.engine.impl.util.json.JSONObject;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.request.AlipayDataDataserviceBillDownloadurlQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.its.common.utils.zipUtil.ZipEntry;
import com.its.common.utils.zipUtil.ZipInputStream;

/**
 * 支付宝相关工具类
 * 
 * @author liuhl
 */
public class AlipayUtils {

	private static String DOWNLOADBILL_FILEPATH; 
	
	/**
	 * 支付宝退款请求
	 * 
	 * @param out_trade_no
	 *            订单支付时传入的商户订单号
	 * @param refund_amount
	 *            需要退款的金额，该金额不能大于订单金额,单位为元，支持两位小数
	 * @param refund_reason
	 *            退款原因
	 */
	static public JSONObject alipayRefundRequest(String out_trade_no, String refund_amount, String refund_reason) {

		// 从配置文件中加载支付宝配置信息
		AlipayConfig.init();

		AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID,
				AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,
				AlipayConfig.SIGNTYPE);
		AlipayTradeRefundRequest alipay_request = new AlipayTradeRefundRequest();

		AlipayTradeRefundModel model = new AlipayTradeRefundModel();
		model.setOutTradeNo(out_trade_no);
		model.setRefundAmount(refund_amount);
		model.setRefundReason(refund_reason);
		model.setOutRequestNo(out_trade_no);
		alipay_request.setBizModel(model);

		AlipayTradeRefundResponse alipay_response = null;
		try {
			alipay_response = client.execute(alipay_request);
			System.out.println(alipay_response.getBody());
		} catch (AlipayApiException e) {
			e.printStackTrace();
			return new JSONObject();
		}

		JSONObject jsonObj = new JSONObject(alipay_response.getBody());
		if (jsonObj.get("alipay_trade_refund_response") != null) {
			return (JSONObject) jsonObj.get("alipay_trade_refund_response");
		}
		return jsonObj;
	}

	/**
	 * 获取支付宝对账单的下载地址，并在30秒内下载zip对账文件 
	 * 账单时间：日账单格式为yyyy-MM-dd，月账单格式为yyyy-MM
	 * 账单类型，商户通过接口或商户经开放平台授权后其所属服务商通过接口可以获取以下账单类型：trade、signcustomer；
	 * trade指商户基于支付宝交易收单的业务账单；signcustomer是指基于商户支付宝余额收入及支出等资金变动的帐务账单；
	 * 
	 * @param billDate
	 */
	public static String dealAlipayDownloadBill(String billDate) {
		AlipayConfig.init();
		AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID,
				AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,
				AlipayConfig.SIGNTYPE);// 获得初始化的AlipayClient
		AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();// 创建API对应的request类

		request.setBizContent("{" + "\"bill_type\":\"trade\"," + "\"bill_date\":\"" + billDate + "\"}"); // 设置业务参数
		AlipayDataDataserviceBillDownloadurlQueryResponse response = null;
		String filePath = null;
		
		// 读取配置文件
		Properties props = new Properties();
        // 加载配置信息
        InputStream in = null;
        in = WXUtils.class.getClassLoader().getResourceAsStream("alipay-config.properties");
        try {
            props.load(in);
			// 通过alipayClient调用API，获得对应的response类
			response = client.execute(request);

			// 根据response中的结果继续业务逻辑处理
			System.out.print(response.getBillDownloadUrl());
		} catch (AlipayApiException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 如果支付宝下载对账单接口调用成功，则获取对账单下载链接
		if (response.isSuccess()) {

			// 将接口返回的对账单下载地址传入urlStr
			String urlStr = response.getBillDownloadUrl();
			// 指定希望保存的文件路径
			DOWNLOADBILL_FILEPATH = props.getProperty("DOWNLOADBILL_FILEPATH");
			filePath = File.separator+"Users"+File.separator+"fund_bill_" + DateUtils.getDate("yyyyMMdd") + ".zip";
			URL url = null;
			HttpURLConnection httpUrlConnection = null;
			InputStream fis = null;
			FileOutputStream fos = null;
			try {
				url = new URL(urlStr);
				httpUrlConnection = (HttpURLConnection) url.openConnection();
				httpUrlConnection.setConnectTimeout(5 * 1000);
				httpUrlConnection.setDoInput(true);
				httpUrlConnection.setDoOutput(true);
				httpUrlConnection.setUseCaches(false);
				httpUrlConnection.setRequestMethod("GET");

				httpUrlConnection.setRequestProperty("CHARSET", "UTF-8");
				httpUrlConnection.connect();
				fis = httpUrlConnection.getInputStream();
				byte[] temp = new byte[1024];
				int b;
				// File file = new File(filePath);
				// if(!file.exists()){
				// file.mkdirs();
				// }
				fos = new FileOutputStream(new File(filePath));
				while ((b = fis.read(temp)) != -1) {
					fos.write(temp, 0, b);
					fos.flush();
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (fis != null)
						fis.close();
					if (fos != null)
						fos.close();
					if (httpUrlConnection != null)
						httpUrlConnection.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return filePath;
	}

	/**
	 * 读取zip文件，不解压缩直接解析，支持文件名中文，解决内容乱码
	 * 
	 * @param file
	 * @throws IOException 
	 * @throws Exception
	 */
	public static StringBuffer readZipCvsFile(File file) throws IOException {
		StringBuffer sb = new StringBuffer();
		// 获得输入流，文件为zip格式，
		// 支付宝提供
		// fund_bill_20170818.zip内包含
		// 20887217759422330156_20170818_业务明细(汇总).csv
		// 20887217759422330156_20170818_业务明细.csv
		ZipInputStream in = new ZipInputStream(new FileInputStream(file));
		// 不解压直接读取,加上gbk解决乱码问题
		BufferedReader br = new BufferedReader(new InputStreamReader(in, "GBK"));
		ZipEntry zipFile;
		// 循环读取zip中的csv文件，无法使用jdk自带，因为文件名中有中文
		while ((zipFile = in.getNextEntry()) != null) {
			if (zipFile.isDirectory()) {
				// 如果是目录，不处理
			}

			// 获得csv名字
			String fileName = zipFile.getName();
			System.out.println("-----" + fileName);

			// 检测文件是否存在
			if (fileName != null && fileName.indexOf(".") != -1 && fileName.contains("业务明细.csv")) {
				String line;
				while ((line = br.readLine()) != null) {
					if (!line.startsWith("#")) {
						System.out.println(line);
						sb.append(line);
						sb.append("\r\n");
					}
				}
			}
		}
		// 关闭流
		br.close();
		in.close();
		
		return sb;
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
