package com.pingpp.service;

import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.*;
import com.pingplusplus.model.Recharge;
import com.pingpp.utils.Const;

import java.util.HashMap;
import java.util.Map;

public class RechargeService {

    private ChargeService chargeService = new ChargeService();

    public Recharge createRecharge(Recharge recharge) {
        Pingpp.appId = Const.APP_ID;
        Pingpp.apiKey = Const.APP_KEY;
        Pingpp.privateKey = Const.APP_PRIVATE_KEY;
        Map<String, Object> rechargeMap = new HashMap<String, Object>();
        rechargeMap.put("user", recharge.getUser());
        Map<String, Object> charge = new HashMap<>();
        charge.put("amount", recharge.getAmount()); // 用户实际支付金额，单位分, 必传
        String channel = recharge.getCharge().getChannel();
        charge.put("channel", channel); // 支付使用的第三方支付渠道, 必传
        charge.put("order_no", "2017" + System.currentTimeMillis()); // 商户订单号，适配每个渠道对此参数的要求，必须在商户系统内唯一, 必传
        charge.put("client_ip", "127.0.0.1");   // 客户端的 IP，IPv4，默认 127.0.0.1, 可选
        charge.put("subject", "Recharge subject"); // 充值标题，该参数最长为 32 个 Unicode 字符, 必传
        charge.put("body", "Recharge body"); // 充值描述信息，该参数最长为 128 个 Unicode 字符, 必传
        Map<String, Object> extra = new HashMap<>(); // extra: 根据不同渠道传入相应的参数
        extra.put("success_url", "http://www.pingxx.com");
        charge.put("extra", ChargeService.channelExtra(channel));
        rechargeMap.put("charge", charge);
        try {
            recharge = Recharge.create(rechargeMap); // 创建 recharge 方法
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


        return recharge;
    }


}
