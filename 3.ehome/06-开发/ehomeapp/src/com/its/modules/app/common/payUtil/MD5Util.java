package com.its.modules.app.common.payUtil;

import java.security.MessageDigest;

import com.its.common.utils.StringUtils;

public class MD5Util {
	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	public static String MD5Encode(String origin, String charsetname) {
		String result = new String(origin);
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			if (StringUtils.isNoneBlank(charsetname)) {
				result = byteArrayToHexString(digest.digest(result.getBytes()));
			} else {
				result = byteArrayToHexString(digest.digest(result.getBytes(charsetname)));
			}
		} catch (Exception exception) {

		}
		return result;
	}

	private static String byteArrayToHexString(byte[] bytes) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < bytes.length; i++)
			result.append(byteToHexString(bytes[i]));
		return result.toString();
	}

	private static String byteToHexString(byte byt) {
		int n = byt;
		n = n < 0 ? (n + 256) : n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
}