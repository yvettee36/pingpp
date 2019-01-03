package com.pingpp.service;

import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.*;
import com.pingplusplus.model.BalanceTransfer;
import com.pingplusplus.model.Recharge;
import com.pingplusplus.model.Withdrawal;
import com.pingpp.utils.CommonUtil;
import com.pingpp.utils.Const;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BalanceService {

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

            System.out.println(recharge.toString());
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


    /**
     * 余额转账
     *
     * @param balanceTransfer
     * @return
     */
    public BalanceTransfer createBalanceTransfer(BalanceTransfer balanceTransfer) {
        Pingpp.apiKey = Const.APP_KEY;
        Pingpp.privateKey = Const.APP_PRIVATE_KEY;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user", "yvettee_user1");
        params.put("recipient", balanceTransfer.getRecipient());
        params.put("amount", balanceTransfer.getAmount());
        String orderNo = new Date().getTime() + CommonUtil.randomString(7);
        params.put("order_no", "bt" + orderNo);
        params.put("description", "Balance transfer description.");

        try {
            balanceTransfer = BalanceTransfer.create(params);
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

        return balanceTransfer;
    }


    /**
     * 余额提现
     *
     * @param withdrawal
     * @return
     */
    public Withdrawal createWithdrawal(Withdrawal withdrawal) {
        Pingpp.apiKey = Const.APP_KEY;
        Pingpp.privateKey = Const.APP_PRIVATE_KEY;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user", "yvettee_user1");
        params.put("channel", withdrawal.getChannel());
        params.put("amount", withdrawal.getAmount());
        String orderNo = new Date().getTime() + CommonUtil.randomString(7);
        params.put("order_no", "bt" + orderNo);
        params.put("description", "Balance withdrawal description.");
        params.put("extra", channelExtra(withdrawal.getChannel()));

        try {
            withdrawal = Withdrawal.create(params);
            withdrawal = pendingWithdrawal(withdrawal.getId());
            System.out.println(withdrawal.toString());
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

        return withdrawal;
    }


    /**
     * 余额提现确认
     *
     * @param id
     * @return
     */
    public Withdrawal pendingWithdrawal(String id) {
        Pingpp.appId = Const.APP_ID;
        Pingpp.apiKey = Const.APP_KEY;
        Pingpp.privateKey = Const.APP_PRIVATE_KEY;
        Withdrawal withdrawal = new Withdrawal();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("status", "pending");

        try {
            withdrawal = withdrawal.update(id, params);
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

        return withdrawal;
    }

    /**
     * 查询提现
     *
     * @param id 提现对象的id
     * @return
     */
    public Withdrawal getWithdrawal(String id) {
        Pingpp.appId = Const.APP_ID;
        Pingpp.apiKey = Const.APP_KEY;
        Pingpp.privateKey = Const.APP_PRIVATE_KEY;
        Withdrawal withdrawal = new Withdrawal();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("status", "pending");

        try {
            withdrawal = Withdrawal.retrieve(id);
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

        return withdrawal;
    }


    private Map<String, Object> channelExtra(String channel) {
        Map<String, Object> param = new HashMap<>();

        switch (channel) {
            case "alipay":
                // 渠道为 alipay 时，若 type 为 b2c，为个人支付宝账号，若 type 为 b2b，为企业支付宝账号。
                param.put("account", "842207739@qq.com");
                param.put("name", "陈小镜");
                param.put("type", "b2c");
                break;
            case "wx_pub":
                // 渠道为 wx_pub 时，需要传 recipient 为用户在商户 appid 下的 open_id
                param.put("open_id", "o9zpMswQdsU9rGgpBKyYyrWX2XK8");
                break;
        }

        return param;
    }

}
