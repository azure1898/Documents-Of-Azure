package com.its.modules.app.common.payUtil.wechatpay;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;

import com.its.common.utils.StringUtils;

public class PayToolUtil {

	/**
	 * 是否签名正确,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
	 * 
	 * @return boolean
	 */
	public static boolean isTenpaySign(String characterEncoding, SortedMap<String, String> packageParams, String API_KEY) {
		StringBuffer sb = new StringBuffer();
		Iterator<Entry<String, String>> it = packageParams.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			String key = entry.getKey();
			String value = entry.getValue();
			if (!"sign".equals(key) && StringUtils.isNotBlank(value)) {
				sb.append(key + "=" + value + "&");
			}
		}

		sb.append("key=" + API_KEY);

		// 算出摘要
		String mysign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toLowerCase();
		String tenpaySign = packageParams.get("sign").toLowerCase();
		return tenpaySign.equals(mysign);
	}

	/**
	 * 创建签名
	 * 
	 * @param characterEncoding
	 *            编码格式
	 * @param parameters
	 *            请求参数
	 * @return 签名
	 */
	public static String createSign(String characterEncoding, SortedMap<Object, Object> packageParams, String API_KEY) {
		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + API_KEY);
		String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
		return sign;
	}

	/**
	 * 将请求参数转换为XML格式的字符串
	 * 
	 * @param parameters
	 *            请求参数
	 * @return XML格式的字符串
	 */
	public static String getRequestXml(SortedMap<Object, Object> parameters) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {
				sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
			} else {
				sb.append("<" + k + ">" + v + "</" + k + ">");
			}
		}
		sb.append("</xml>");
		return sb.toString();
	}

	/**
	 * 取出一个指定长度大小的随机正整数
	 * 
	 * @param length
	 *            设定所取出随机数的长度，length小于11
	 * @return 返回生成的随机数
	 */
	public static int buildRandom(int length) {
		int num = 1;
		double random = Math.random();
		if (random < 0.1) {
			random = random + 0.1;
		}
		for (int i = 0; i < length; i++) {
			num = num * 10;
		}
		return (int) ((random * num));
	}
}