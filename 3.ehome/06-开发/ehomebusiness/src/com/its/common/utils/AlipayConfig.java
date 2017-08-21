package com.its.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AlipayConfig {
	// 商户appid
	public static String APPID;
	// 私钥 pkcs8格式的
	public static String RSA_PRIVATE_KEY ;
	// 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://218.28.28.186:9088/alipay.trade.wap.pay-JAVA-UTF-8/notify_url.jsp";
	// 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
	public static String return_url = "http://218.28.28.186:9088/alipay.trade.wap.pay-JAVA-UTF-8/return_url.jsp";
	// 请求网关地址
	public static String URL = "https://openapi.alipay.com/gateway.do";
	// 编码
	public static String CHARSET = "UTF-8";
	// 返回格式
	public static String FORMAT = "json";
	// 支付宝公钥
	public static String ALIPAY_PUBLIC_KEY;
	// 日志记录目录
	public static String log_path = "/log";
	// RSA2
	public static String SIGNTYPE;
	
	static public void init() {
	    Properties props = new Properties();
        // 加载配置信息
        InputStream in = null;
        in = WXUtils.class.getClassLoader().getResourceAsStream("alipay-config.properties");
        try {
            props.load(in);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
	    APPID = props.getProperty("APPID");
	    RSA_PRIVATE_KEY = props.getProperty("RSA_PRIVATE_KEY");
	    ALIPAY_PUBLIC_KEY = props.getProperty("ALIPAY_PUBLIC_KEY");
	    SIGNTYPE = props.getProperty("SIGNTYPE");
	}
}
