package com.pingpp.service;

import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.*;
import com.pingplusplus.model.Order;
import com.pingpp.utils.CommonUtil;
import com.pingpp.utils.Const;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class OrderService {

    public Order createOrder(Order order) {
        Pingpp.apiKey = Const.APP_KEY;
        Pingpp.privateKey = Const.APP_PRIVATE_KEY;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("uid", "test_user_001"); // 用户在当前 app 下的 User ID, 可选
        params.put("app", Const.APP_ID); // App ID, 必传
        String orderNo = new Date().getTime() / 1000 + CommonUtil.randomString(7);
        params.put("merchant_order_no", "or" + orderNo); // 商户订单号, 必传
        params.put("subject", "ORDER_SUBJECT"); // 商品的标题, 必传
        params.put("body", "ORDER_BODY"); // 商品的描述信息, 必传
        params.put("amount", order.getAmount()); // 订单总金额，单位：分, 必传
        params.put("currency", "cny"); // 仅支持人民币 cny, 必传
        params.put("client_ip", order.getClientIp()); // 客户端的 IP 地址 (IPv4 格式，要求商户上传真实的，渠道可能会判断), 必传
        Map<String, String> initialMetadata = new HashMap<String, String>();
        initialMetadata.put("color", "red");
        initialMetadata.put("phone_number", "13918651111");
        params.put("metadata", initialMetadata); // 客户端的 IP 地址 (IPv4 格式，要求商户上传真实的，渠道可能会判断), 必传

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

}
