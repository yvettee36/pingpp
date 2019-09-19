package com.pingpp.service;

import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.*;
import com.pingplusplus.model.Order;
import com.pingpp.utils.CommonUtil;
import com.pingpp.utils.Const;
import com.pingpp.utils.ExtraUtil;

import java.util.*;


public class OrderService {

    public Order createOrder(Order order) {
        Pingpp.apiKey = Const.APP_KEY;
//        Pingpp.overrideApiBase("https://api.pingplusplus.com");
        Pingpp.privateKey = Const.APP_PRIVATE_KEY;
        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("uid", "test_user_001"); // 用户在当前 app 下的 User ID, 可选
        params.put("app", Const.APP_ID); // App ID, 必传
        String orderNo = new Date().getTime() / 1000 + CommonUtil.randomString(7);
        params.put("merchant_order_no", "or" + orderNo); // 商户订单号, 必传
        params.put("subject", "ORDER_SUBJECT"); // 商品的标题, 必传
        params.put("body", "ORDER_BODY"); // 商品的描述信息, 必传
        params.put("amount", order.getAmount()); // 订单总金额，单位：分, 必传
        params.put("currency", "cny"); // 仅支持人民币 cny, 必传
        params.put("client_ip", order.getClientIp()); // 客户端的 IP 地址 (IPv4 格式，要求商户上传真实的，渠道可能会判断), 必传
        Map<String, String> initialMetadata = new HashMap<String, String>();
        initialMetadata.put("订单A", "1234567");
        initialMetadata.put("订单B", "278282889");
        params.put("metadata", initialMetadata); // 客户端的 IP 地址 (IPv4 格式，要求商户上传真实的，渠道可能会判断), 必传

        //余额结算
//        Map<String, Object> balance_settlement = new HashMap<String, Object>();
//        balance_settlement.put("user","yvettee");
//        balance_settlement.put("user_fee",1);
//        params.put("balance_settlement",balance_settlement);

        //分润
//        List royalty_users= new ArrayList();
//        Map<String, Object> royalty = new HashMap<String, Object>();
//        royalty.put("user","yvettee");
//        royalty.put("amount",15);
//        royalty_users.add(royalty);
//        params.put("royalty_users",royalty_users);

        try {
            order = Order.create(params);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        } catch (ChannelException e) {
            e.printStackTrace();
        } catch (RateLimitException e) {
            e.printStackTrace();
        }
        return order;
    }


    /**
     * 支付order
     * @param order
     * @return
     */

    public Order payOrder(Order order) {
        Pingpp.apiKey = Const.APP_KEY;
        Pingpp.privateKey = Const.APP_PRIVATE_KEY;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("charge_amount", order.getAmount()); // 订单总金额，单位：分, 必传
        params.put("channel", "alipay_pc_direct");

        // extra 取值请查看相应方法说明
        params.put("extra", channelExtra("alipay_pc_direct"));
//        Map<String, Object> combined_with = new HashMap<String, Object>();
//        combined_with.put("channel", "balance");
//        combined_with.put("charge_amount", 1);
//        params.put("combined_with", combined_with); // 客户端的 IP 地址 (IPv4 格式，要求商户上传真实的，渠道可能会判断), 必传


        try {
            order = Order.pay(order.getId(), params);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        } catch (ChannelException e) {
            e.printStackTrace();
        } catch (RateLimitException e) {
            e.printStackTrace();
        }
        return order;
    }

    public Order testGetOrder(String id) {
        Pingpp.apiKey = Const.APP_KEY;
        Pingpp.privateKey = Const.APP_PRIVATE_KEY;
        Order order = null;
        try {
            order = Order.retrieve(id); // 查询单个 order 方法  参数: orderId
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        } catch (ChannelException e) {
            e.printStackTrace();
        } catch (RateLimitException e) {
            e.printStackTrace();
        }
        return order;
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
            case "paypal":
                extra = ExtraUtil.paypalExtra();
                break;
        }

        return extra;
    }

}
