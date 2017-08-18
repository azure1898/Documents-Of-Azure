package com.its.common.utils;

public class AlipayConfig {
	// 商户appid
	public static String APPID = "2017081608219753";
	// 私钥 pkcs8格式的
	public static String RSA_PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAN8RKcj1cJkyqRBAe8nuCj32ZKmc0A4E4xdzVl8vEMuwy+h3KLOSHtelXmLBG9ZoCKI7kOmKcVryCelkppWM9RjWCvnSSOIlLrKmtk6eB80a365ZjL9OJOfkcRcmAKBqBNeDff+GaszRo8hxwqfTRuwjToM97xCEnAbfIgLMEINfAgMBAAECgYEA1APqJTkzRkjia7H+dlUrou0XjCahkVGPznAvfdSsMA3gIRlwtAyECA8DgPVuUgEbg6b9+xs81mYKiazCsI4DPUuRp3KlV9UhOMqBXmk7+BH5PBzAUMT8HtZSYyYoxwfrkJ4jvLCq/C1VyCBdr32z2M3O2UClXjTyfFnd7McWc2ECQQDy13+DTib+e8Aaqi3jXfI/wCD3NZYIjIhffvloHiDVMV6id8PyEH+w5NtVm1LHnzxvZsoKdnPucX7fq/rm+VmnAkEA6ydduC3Ws43ky/h+4eToUeZLSDNI2KYTcedzBJ1oV3S48aSYwWOtKAlqI1JpbKppxYu/8XcN/cm7T7/IZwNPiQJAZH+Og9aqCJNKMz7OctQEVyG6AtSoXK1cGrk/mjktuU0PLwmFi/4L+jccFleLcJWTuz68youu+rT5nrNbyI7U7QJAUSUhcwfmWclyFWynpB3QiUp12fFQhuowNZMPnp0Ov3cYxtHsoHdb7j/pb0zl8kiP/UovLwzNODFfwLJGXj3HOQJBAKMSKefowbnKfJZcF3ZsDnEculAMKiGADMeAdt4suPTHOlnyMfBeUcMmi7m0NQRQ9uYyc6x0seoCfeXu5f3N85g=";
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
	public static String ALIPAY_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDfESnI9XCZMqkQQHvJ7go99mSpnNAOBOMXc1ZfLxDLsMvodyizkh7XpV5iwRvWaAiiO5DpinFa8gnpZKaVjPUY1gr50kjiJS6yprZOngfNGt+uWYy/TiTn5HEXJgCgagTXg33/hmrM0aPIccKn00bsI06DPe8QhJwG3yICzBCDXwIDAQAB";
	// 日志记录目录
	public static String log_path = "/log";
	// RSA2
	public static String SIGNTYPE = "RSA";
}
