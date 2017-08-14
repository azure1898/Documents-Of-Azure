package com.its.modules.app.common.payUtil.alipay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 支付宝支付通用类
 */
public class AlipayCore {

	/**
	 * 除去数组中的空值和签名参数
	 * 
	 * @param signArray
	 *            签名参数组
	 * @return 去掉空值与签名参数后的新签名参数组
	 */
	public static Map<String, String> paraFilter(Map<String, String> signArray) {
		Map<String, String> result = new HashMap<String, String>();
		if (signArray == null || signArray.size() <= 0) {
			return result;
		}
		for (String key : signArray.keySet()) {
			String value = signArray.get(key);
			if (value == null || value.equals("") || key.equalsIgnoreCase("sign") || key.equalsIgnoreCase("sign_type")) {
				continue;
			}
			result.put(key, value);
		}
		return result;
	}

	/**
	 * 把数组所有元素，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	 * 
	 * @param params
	 *            需要参与字符拼接的参数组
	 * @param sorts
	 *            是否需要排序 true 或者 false
	 * @return 拼接后字符串
	 */
	public static String createLinkString(Map<String, String> params) {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		String prestr = "";
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);
			if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
				prestr = prestr + key + "=" + value;
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
		}
		return prestr;
	}
}