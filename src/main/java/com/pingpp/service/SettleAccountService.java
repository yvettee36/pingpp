package com.pingpp.service;

import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.*;
import com.pingplusplus.model.SettleAccount;
import com.pingpp.utils.Const;

import java.util.HashMap;
import java.util.Map;

public class SettleAccountService {

    public SettleAccount createSettleAccount(String channel) {
        SettleAccount settleAccount = null;
        Pingpp.appId = Const.APP_ID;
        Pingpp.apiKey = Const.APP_KEY;
        Pingpp.privateKey = Const.APP_PRIVATE_KEY;
        Map<String, Object> settleAccountMap = new HashMap<String, Object>();
        settleAccountMap.put("channel", channel);
        settleAccountMap.put("recipient", channelRecipient(channel));

        try {
            settleAccount = SettleAccount.create("yvettee_user1", settleAccountMap);
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

        return settleAccount;
    }


    public SettleAccount getSettleAccount(String id) {
        SettleAccount settleAccount = null;
        Pingpp.appId = Const.APP_ID;
        Pingpp.apiKey = Const.APP_KEY;
        Pingpp.privateKey = Const.APP_PRIVATE_KEY;

        try {
            settleAccount = SettleAccount.retrieve("yvettee_user1", id);
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

        return settleAccount;
    }


    private Map<String, Object> channelRecipient(String channel) {
        Map<String, Object> recipient = new HashMap<>();

        switch (channel) {
            case "alipay":
                // 渠道为 alipay 时，若 type 为 b2c，为个人支付宝账号，若 type 为 b2b，为企业支付宝账号。
                recipient.put("account", "842207739@qq.com");
                recipient.put("name", "陈小镜");
                recipient.put("type", "b2c");
                break;
            case "wx_pub":
                // 渠道为 wx_pub 时，需要传 recipient 为用户在商户 appid 下的 open_id
                recipient.put("account", "o9zpMswQdsU9rGgpBKyYyrWX2XK8");
                break;
        }

        return recipient;
    }

}
