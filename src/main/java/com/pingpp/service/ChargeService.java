package com.pingpp.service;

import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.*;
import com.pingplusplus.model.Charge;
import com.pingpp.utils.CommonUtil;
import com.pingpp.utils.Const;
import com.pingpp.utils.ExtraUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ChargeService {

    /**
     * 创建支付
     *
     * @param charge
     * @return
     */
    public Charge createCharge(Charge charge) {
        Pingpp.appId = Const.APP_ID;
        Pingpp.apiKey = Const.APP_KEY;
        Pingpp.privateKey = Const.APP_PRIVATE_KEY;
        Map<String, Object> chargeMap = new HashMap<String, Object>();
        String orderNo = new Date().getTime() + CommonUtil.randomString(7);
        chargeMap.put("order_no", orderNo);// 推荐使用 8-20 位，要求数字或字母，不允许其他字符
        chargeMap.put("channel", charge.getChannel());// 支付使用的第三方支付渠道取值，请参考：https://www.pingxx.com/api#api-c-new
        chargeMap.put("amount", charge.getAmount());//订单总金额, 人民币单位：分（如订单总金额为 1 元，此处请填 100）
        chargeMap.put("client_ip", charge.getClientIp()); // 发起支付请求客户端的 IP 地址，格式为 IPV4，如: 127.0.0.1
        chargeMap.put("currency", "cny");
        chargeMap.put("subject", "subjectTest_yvettee");
        chargeMap.put("body", "bodyTest_yvettee");
        chargeMap.put("description", "备注");
        Long time_expire = CommonUtil.getOrderExpireTime(2 * 60 * 1000L);
        chargeMap.put("time_expire", time_expire);
        Map<String, String> initialMetadata = new HashMap<String, String>();
        initialMetadata.put("color", "red");
        initialMetadata.put("phone_number", "13918651111");
        chargeMap.put("metadata", initialMetadata);
        Map<String, String> app = new HashMap<String, String>();
        app.put("id", Const.APP_ID);
        chargeMap.put("app", app);

        // extra 取值请查看相应方法说明
        chargeMap.put("extra", channelExtra(charge.getChannel()));

        try {
            //发起交易请求
            charge = Charge.create(chargeMap);
            System.out.println(charge.toString());
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (ChannelException e) {
            e.printStackTrace();
        } catch (RateLimitException e) {
            e.printStackTrace();
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        }

        // 传到客户端请先转成字符串 .toString(), 调该方法，会自动转成正确的 JSON 字符串
        return charge;
    }

    public static Map<String, Object> channelExtra(String channel) {
        Map<String, Object> extra = new HashMap<>();

        switch (channel) {
            case "alipay":
                extra = ExtraUtil.alipayExtra();
                break;
            case "alipay_wap":
                extra = ExtraUtil.alipayWapExtra();
                break;
            case "alipay_pc_direct":
                extra = ExtraUtil.alipayPcDirectExtra();
                break;
            case "alipay_qr":
                extra = ExtraUtil.alipayQrExtra();
                break;
            case "wx":
                extra = ExtraUtil.wxExtra();
                break;
            case "wx_pub":
                extra = ExtraUtil.wxPubExtra();
                break;
            case "wx_pub_qr":
                extra = ExtraUtil.wxPubQrDirectExtra();
                break;
            case "wx_lite":
                extra = ExtraUtil.wxLiteExtra();
                break;
            case "wx_wap":
                extra = ExtraUtil.wxWapExtra();
                break;
            case "bfb":
                extra = ExtraUtil.bfbExtra();
                break;
            case "bfb_wap":
                extra = ExtraUtil.bfbWapExtra();
                break;
            case "upacp":
                extra = ExtraUtil.upacpExtra();
                break;
            case "upacp_wap":
                extra = ExtraUtil.upacpWapExtra();
                break;
            case "upacp_pc":
                extra = ExtraUtil.upacpPcExtra();
                break;
            case "jdpay_wap":
                extra = ExtraUtil.jdpayWapExtra();
                break;
            case "yeepay_wap":
                extra = ExtraUtil.yeepayWapExtra();
                break;
            case "applepay_upacp":
                extra = ExtraUtil.applepayUpacpExtra();
                break;
            case "qpay":
                extra = ExtraUtil.qpayExtra();
                break;
            case "cmb_wallet":
                extra = ExtraUtil.cmbWalletExtra();
                break;
            case "cp_b2b":
                extra = ExtraUtil.cpB2bExtra();
                break;
            case "isv_scan":
                extra = ExtraUtil.isvScanExtra();
                break;
            case "isv_qr":
                extra = ExtraUtil.isvQrExtra();
                break;
            case "isv_wap":
                extra = ExtraUtil.isvWapExtra();
                break;
            case "alipay_scan":
                extra = ExtraUtil.alipayScanExtra();
                break;
            case "wx_pub_scan":
                extra = ExtraUtil.wxPubScanExtra();
                break;
            case "cb_alipay":
                extra = ExtraUtil.cbAlipayExtra();
                break;
            case "cb_wx":
                extra = ExtraUtil.cbWxExtra();
                break;
            case "cb_wx_pub":
                extra = ExtraUtil.cbWxPubExtra();
                break;
            case "cb_wx_pub_qr":
                extra = ExtraUtil.cbWxPubQrExtra();
                break;
            case "cb_wx_pub_scan":
                extra = ExtraUtil.cbWxPubScanExtra();
                break;
        }

        return extra;
    }
}
