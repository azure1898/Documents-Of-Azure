package com.its.common.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.its.common.utils.WXUtilsConfig.SignType;
import com.its.modules.gen.util.GenUtils;

public class WXUtils {

    private static Logger logger = LoggerFactory.getLogger(WXUtils.class);

    private static Properties props = new Properties();

    /**
     * 微信退款
     * 
     * @param out_trade_no
     *            商户订单号
     * @param out_refund_no
     *            商户退款单号
     * @param total_fee
     *            订单金额
     * @param refund_fee
     *            退款金额
     * @param refund_desc
     *            退款原因
     */
    static public Map<String, String> doRefund(String out_trade_no, String out_refund_no, String total_fee,
            String refund_fee, String refund_desc) {
        logger.warn("微信退款------------>订单号：" + out_trade_no + "退款开始");
        // 加载配置信息
        InputStream in = null;
        in = WXUtils.class.getClassLoader().getResourceAsStream("wx-config.properties");
        try {
            props.load(in);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        HashMap<String, String> data = new HashMap<String, String>();
        Map<String, String> result = new HashMap<String, String>();
        data.put("out_trade_no", out_trade_no);
        data.put("out_refund_no", out_refund_no);
        data.put("total_fee", total_fee);
        data.put("refund_fee", refund_fee);
        data.put("refund_fee_type", "CNY");
        data.put("op_user_id", props.getProperty("MCH_ID"));
        data.put("refund_desc", refund_desc);

        try {
            result = refund(data);
            logger.warn("微信退款返回数据------>" + result.toString());
            logger.warn("微信退款------------>退款金额：" + total_fee + "（分）");
            logger.warn("微信退款------------>订单号：" + out_trade_no + "退款正常结束");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn("微信退款------------>异常：" + e.toString());
            logger.warn("微信退款------------>订单号：" + out_trade_no + "退款异常结束");
        }
        return result;
    }

    /**
     * 作用：申请退款<br>
     * 场景：刷卡支付、公共号支付、扫码支付、APP支付
     * 
     * @param reqData
     *            向wxpay post的请求数据
     * @return API返回数据
     * @throws Exception
     */
    private static Map<String, String> refund(Map<String, String> reqData) throws Exception {
        return refund(reqData, WXUtilsConfig.HTTP_CONNECT_TIMEOUTMS, WXUtilsConfig.HTTP_READ_TIMEOUTMS);
    }

    /**
     * 作用：申请退款<br>
     * 场景：刷卡支付、公共号支付、扫码支付、APP支付<br>
     * 其他：需要证书
     * 
     * @param reqData
     *            向wxpay post的请求数据
     * @param connectTimeoutMs
     *            连接超时时间，单位是毫秒
     * @param readTimeoutMs
     *            读超时时间，单位是毫秒
     * @return API返回数据
     * @throws Exception
     */
    private static Map<String, String> refund(Map<String, String> reqData, int connectTimeoutMs, int readTimeoutMs)
            throws Exception {
        String url = WXUtilsConfig.REFUND_URL_SUFFIX;
        String respXml = requestWithCert(url, fillRequestData(reqData), connectTimeoutMs, readTimeoutMs);
        return processResponseXml(respXml);
    }

    /**
     * 向 Map 中添加 appid、mch_id、nonce_str、sign_type、sign <br>
     * 该函数适用于商户适用于统一下单等接口，不适用于红包、代金券接口
     *
     * @param reqData
     * @return
     * @throws Exception
     */
    private static Map<String, String> fillRequestData(Map<String, String> reqData) throws Exception {
        // 加载配置信息
        InputStream in = null;
        in = WXUtils.class.getClassLoader().getResourceAsStream("wx-config.properties");
        try {
            props.load(in);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        reqData.put("appid", props.getProperty("APP_ID"));
        reqData.put("mch_id", props.getProperty("MCH_ID"));
        reqData.put("nonce_str", generateUUID());
        reqData.put("sign_type", WXUtilsConfig.MD5);
        reqData.put("sign", generateSignature(reqData, props.getProperty("KEY")));
        return reqData;
    }

    /**
     * 需要证书的请求
     * 
     * @param urlSuffix
     *            String
     * @param reqData
     *            向wxpay post的请求数据 Map
     * @param connectTimeoutMs
     *            超时时间，单位是毫秒
     * @param readTimeoutMs
     *            超时时间，单位是毫秒
     * @return API返回数据
     * @throws Exception
     */
    private static String requestWithCert(String urlSuffix, Map<String, String> reqData, int connectTimeoutMs,
            int readTimeoutMs) throws Exception {
        String msgUUID = reqData.get("nonce_str");
        String reqBody = mapToXml(reqData);

        String resp = WXPayRequest.requestWithCert(urlSuffix, msgUUID, reqBody);
        return resp;
    }

    /**
     * XML格式字符串转换为Map
     *
     * @param strXML
     *            XML字符串
     * @return XML数据转换后的Map
     * @throws Exception
     */
    private static Map<String, String> xmlToMap(String strXML) throws Exception {
        try {
            Map<String, String> data = new HashMap<String, String>();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            InputStream stream = new ByteArrayInputStream(strXML.getBytes("UTF-8"));
            org.w3c.dom.Document doc = documentBuilder.parse(stream);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getDocumentElement().getChildNodes();
            for (int idx = 0; idx < nodeList.getLength(); ++idx) {
                Node node = nodeList.item(idx);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                    data.put(element.getNodeName(), element.getTextContent());
                }
            }
            try {
                stream.close();
            } catch (Exception ex) {
                // do nothing
            }
            return data;
        } catch (Exception ex) {
            throw ex;
        }

    }

    /**
     * 将Map转换为XML格式的字符串
     *
     * @param data
     *            Map类型数据
     * @return XML格式的字符串
     * @throws Exception
     */
    private static String mapToXml(Map<String, String> data) throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        org.w3c.dom.Document document = documentBuilder.newDocument();
        org.w3c.dom.Element root = document.createElement("xml");
        document.appendChild(root);
        for (String key : data.keySet()) {
            String value = data.get(key);
            if (value == null) {
                value = "";
            }
            value = value.trim();
            org.w3c.dom.Element filed = document.createElement(key);
            filed.appendChild(document.createTextNode(value));
            root.appendChild(filed);
        }
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        DOMSource source = new DOMSource(document);
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        transformer.transform(source, result);
        String output = writer.getBuffer().toString(); // .replaceAll("\n|\r",
                                                       // "");
        try {
            writer.close();
        } catch (Exception ex) {
        }
        return output;
    }

    /**
     * 生成带有 sign 的 XML 格式字符串
     *
     * @param data
     *            Map类型数据
     * @param key
     *            API密钥
     * @return 含有sign字段的XML
     */
    private static String generateSignedXml(final Map<String, String> data, String key) throws Exception {
        return generateSignedXml(data, key, SignType.MD5);
    }

    /**
     * 生成带有 sign 的 XML 格式字符串
     *
     * @param data
     *            Map类型数据
     * @param key
     *            API密钥
     * @param signType
     *            签名类型
     * @return 含有sign字段的XML
     */
    private static String generateSignedXml(final Map<String, String> data, String key, SignType signType)
            throws Exception {
        String sign = generateSignature(data, key, signType);
        data.put(WXUtilsConfig.FIELD_SIGN, sign);
        return mapToXml(data);
    }

    /**
     * 判断签名是否正确
     *
     * @param xmlStr
     *            XML格式数据
     * @param key
     *            API密钥
     * @return 签名是否正确
     * @throws Exception
     */
    private static boolean isSignatureValid(String xmlStr, String key) throws Exception {
        Map<String, String> data = xmlToMap(xmlStr);
        if (!data.containsKey(WXUtilsConfig.FIELD_SIGN)) {
            return false;
        }
        String sign = data.get(WXUtilsConfig.FIELD_SIGN);
        return generateSignature(data, key).equals(sign);
    }

    /**
     * 判断签名是否正确，必须包含sign字段，否则返回false。使用MD5签名。
     *
     * @param data
     *            Map类型数据
     * @param key
     *            API密钥
     * @return 签名是否正确
     * @throws Exception
     */
    private static boolean isSignatureValid(Map<String, String> data, String key) throws Exception {
        return isSignatureValid(data, key, SignType.MD5);
    }

    /**
     * 判断签名是否正确，必须包含sign字段，否则返回false。
     *
     * @param data
     *            Map类型数据
     * @param key
     *            API密钥
     * @param signType
     *            签名方式
     * @return 签名是否正确
     * @throws Exception
     */
    private static boolean isSignatureValid(Map<String, String> data, String key, SignType signType) throws Exception {
        if (!data.containsKey(WXUtilsConfig.FIELD_SIGN)) {
            return false;
        }
        String sign = data.get(WXUtilsConfig.FIELD_SIGN);
        return generateSignature(data, key, signType).equals(sign);
    }

    /**
     * 生成签名
     *
     * @param data
     *            待签名数据
     * @param key
     *            API密钥
     * @return 签名
     */
    private static String generateSignature(final Map<String, String> data, String key) throws Exception {
        return generateSignature(data, key, SignType.MD5);
    }

    /**
     * 生成签名. 注意，若含有sign_type字段，必须和signType参数保持一致。
     *
     * @param data
     *            待签名数据
     * @param key
     *            API密钥
     * @param signType
     *            签名方式
     * @return 签名
     */
    private static String generateSignature(final Map<String, String> data, String key, SignType signType)
            throws Exception {
        Set<String> keySet = data.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k : keyArray) {
            if (k.equals(WXUtilsConfig.FIELD_SIGN)) {
                continue;
            }
            if (data.get(k).trim().length() > 0) // 参数值为空，则不参与签名
                sb.append(k).append("=").append(data.get(k).trim()).append("&");
        }
        sb.append("key=").append(key);
        if (SignType.MD5.equals(signType)) {
            return MD5(sb.toString()).toUpperCase();
        } else if (SignType.HMACSHA256.equals(signType)) {
            return HMACSHA256(sb.toString(), key);
        } else {
            throw new Exception(String.format("Invalid sign_type: %s", signType));
        }
    }

    /**
     * 获取随机字符串 Nonce Str
     *
     * @return String 随机字符串
     */
    private static String generateNonceStr() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
    }

    /**
     * 生成 MD5
     *
     * @param data
     *            待处理数据
     * @return MD5结果
     */
    private static String MD5(String data) throws Exception {
        java.security.MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 生成 HMACSHA256
     * 
     * @param data
     *            待处理数据
     * @param key
     *            密钥
     * @return 加密结果
     * @throws Exception
     */
    private static String HMACSHA256(String data, String key) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 获取当前时间戳，单位秒
     * 
     * @return
     */
    private static long getCurrentTimestamp() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 获取当前时间戳，单位毫秒
     * 
     * @return
     */
    private static long getCurrentTimestampMs() {
        return System.currentTimeMillis();
    }

    /**
     * 生成 uuid， 即用来标识一笔单，也用做 nonce_str
     * 
     * @return
     */
    private static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
    }

    /**
     * 处理 HTTPS API返回数据，转换成Map对象。return_code为SUCCESS时，验证签名。
     * 
     * @param xmlStr
     *            API返回的XML格式数据
     * @return Map类型数据
     * @throws Exception
     */
    private static Map<String, String> processResponseXml(String xmlStr) throws Exception {
        String RETURN_CODE = "return_code";
        String return_code;
        Map<String, String> respData = xmlToMap(xmlStr);
        if (respData.containsKey(RETURN_CODE)) {
            return_code = respData.get(RETURN_CODE);
        } else {
            throw new Exception(String.format("No `return_code` in XML: %s", xmlStr));
        }

        if (return_code.equals(WXUtilsConfig.FAIL)) {
            return respData;
        } else if (return_code.equals(WXUtilsConfig.SUCCESS)) {
            if (isResponseSignatureValid(respData)) {
                return respData;
            } else {
                throw new Exception(String.format("Invalid sign value in XML: %s", xmlStr));
            }
        } else {
            throw new Exception(String.format("return_code value %s is invalid in XML: %s", return_code, xmlStr));
        }
    }

    /**
     * 判断xml数据的sign是否有效，必须包含sign字段，否则返回false。
     *
     * @param reqData
     *            向wxpay post的请求数据
     * @return 签名是否有效
     * @throws Exception
     */
    private static boolean isResponseSignatureValid(Map<String, String> reqData) throws Exception {
        // 返回数据的签名方式和请求中给定的签名方式是一致的
        return isSignatureValid(reqData, props.getProperty("KEY"), WXUtilsConfig.SignType.MD5);
    }
}
