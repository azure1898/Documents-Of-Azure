package com.its.common.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class WXUtilsConfig {

    /** 退款接口URL */
    public static final String REFUND_URL_SUFFIX = "/secapi/pay/refund";

    /** 连接超时时间 */
    public static final int HTTP_CONNECT_TIMEOUTMS = 1000;

    /** 读超时时间 */
    public static final int HTTP_READ_TIMEOUTMS = 1000;
    
    /** 公众账号ID */
    public static final String APP_ID = "wxab8acb865bb1637e";
    
    /** 商户号 */
    public static final String MCH_ID = "11473623";
    
    /** 商户秘钥 */
    public static final String KEY = "2ab9071b06b9f739b950ddb41db2690d";
    
    public static final String FAIL     = "FAIL";
    public static final String SUCCESS  = "SUCCESS";
    public static final String HMACSHA256 = "HMAC-SHA256";
    public static final String MD5 = "MD5";
    public static final String FIELD_SIGN = "sign";
    public static final String FIELD_SIGN_TYPE = "sign_type";
    public static final String DO_MAIN = "api.mch.weixin.qq.com";
    
    public enum SignType {
        MD5, HMACSHA256
    }

    public static InputStream getCertStream()  throws Exception{
        ByteArrayInputStream certBis;
        byte[] certData;
        String certPath = "D://CERT/common/apiclient_cert.p12";
        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);
        certData = new byte[(int) file.length()];
        certStream.read(certData);
        certStream.close();
        certBis = new ByteArrayInputStream(certData);
        return certBis;
    }
}
