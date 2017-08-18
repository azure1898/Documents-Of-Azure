package com.its.modules.app.common.refundUtil.wechat;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WXUtilsConfig {

    /** 退款接口URL */
    public static final String REFUND_URL_SUFFIX = "/secapi/pay/refund";

    /** 连接超时时间 */
    public static final int HTTP_CONNECT_TIMEOUTMS = 1000;

    /** 读超时时间 */
    public static final int HTTP_READ_TIMEOUTMS = 1000;

    public static final String FAIL = "FAIL";
    public static final String SUCCESS = "SUCCESS";
    public static final String HMACSHA256 = "HMAC-SHA256";
    public static final String MD5 = "MD5";
    public static final String FIELD_SIGN = "sign";
    public static final String FIELD_SIGN_TYPE = "sign_type";
    public static final String DO_MAIN = "api.mch.weixin.qq.com";

    public enum SignType {
        MD5, HMACSHA256
    }

    public static InputStream getCertStream() throws Exception {
        Properties props = new Properties();
        // 加载配置信息
        InputStream in = null;
        in = WXUtils.class.getClassLoader().getResourceAsStream("wx-config.properties");
        try {
            props.load(in);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        ByteArrayInputStream certBis;
        byte[] certData;
        String certPath = props.getProperty("CERT_PATH");
        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);
        certData = new byte[(int) file.length()];
        certStream.read(certData);
        certStream.close();
        certBis = new ByteArrayInputStream(certData);
        return certBis;
    }
}
