package com.its.common.utils;

import org.activiti.engine.impl.util.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;

/**
 * 支付宝相关工具类
 * 
 * @author liuhl
 */
public class AlipayUtils {
    private static Logger logger = LoggerFactory.getLogger(AlipayUtils.class);

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

        logger.warn("支付宝退款------------>订单号：" + out_trade_no + "退款开始");
        logger.warn("支付宝退款------------>订单号：" + out_trade_no + "退款金额：" + refund_amount + "元");
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
            logger.warn("支付宝退款------------>订单号：" + out_trade_no + "退款正常结束");
            logger.warn("支付宝退款------------>订单号：" + out_trade_no + "返回参数：" + alipay_response.getBody());
            System.out.println(alipay_response.getBody());
        } catch (AlipayApiException e) {
            e.printStackTrace();
            logger.warn("支付宝退款------------>订单号：" + out_trade_no + "退款异常");
            logger.warn("支付宝退款------------>订单号：" + out_trade_no + "错误信息：" + e.toString());
            return new JSONObject();
        }

        JSONObject jsonObj = new JSONObject(alipay_response.getBody());
        if (jsonObj.get("alipay_trade_refund_response") != null) {
            logger.warn("支付宝退款------------>订单号：" + out_trade_no + "退款结束");
            return (JSONObject) jsonObj.get("alipay_trade_refund_response");
        }
        logger.warn("支付宝退款------------>订单号：" + out_trade_no + "退款异常结束");
        return jsonObj;
    }
}
