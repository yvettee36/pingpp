package com.pingpp.service;

import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.*;
import com.pingplusplus.model.Transfer;
import com.pingpp.utils.Const;
import com.pingpp.utils.ExtraUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 企业付款接口
 */
public class TransferService {

    /**
     * 创建企业转账
     * <p>
     * 创建企业转账需要传递一个 map 给 Transfer.create();
     * map 填写的具体介绍可以参考：https://www.pingxx.com/api#api-t-new
     *
     * @return
     */
    public Transfer createTransfer(String channel) {
        Pingpp.appId = Const.APP_ID;
        Pingpp.apiKey = Const.APP_KEY;
        Pingpp.privateKey = Const.APP_PRIVATE_KEY;
        Transfer transfer = null;

        String orderNo = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        Map<String, Object> transferMap = new HashMap<String, Object>();
        transferMap.put("channel", channel); // 目前支持 支付宝：alipay，银联：unionpay，微信公众号：wx_pub，通联：allinpay，京东：jdpay

        // 付款使用的商户内部订单号。
        // wx_pub 规定为 1 ~ 50 位不能重复的数字字母组合;
        // alipay 为 1 ~ 64 位不能重复的数字字母组合;
        // unionpay 为 1 ~ 16 位的纯数字;
        // jdpay 限长 1 ~ 64 位不能重复的数字字母组合;
        // allinpay 限长 20 ~ 40 位不能重复的数字字母组合，必须以签约的通联的商户号开头（建议组合格式：通联商户号 + 时间戳 + 固定位数顺序流水号，不包含+号）

        if (channel == "allinpay") {
            orderNo += System.currentTimeMillis();
        }

        transferMap.put("order_no", orderNo);
        transferMap.put("amount", 200); // 付款金额，相关渠道的限额，请查看 https://help.pingxx.com/article/133366/ 。单位为对应币种的最小货币单位，例如：人民币为分。
        transferMap.put("type", "b2c"); // 付款类型，转账到个人用户为 b2c，转账到企业用户为 b2b（微信公众号 wx_pub 的企业付款，仅支持 b2c）。
        transferMap.put("currency", "cny");
        transferMap.put("recipient", channelRecipient(channel)); // 接收者

        // 备注信息。
        // 渠道为 unionpay 时，最多 99 个 Unicode 字符；
        // 渠道为 wx_pub 时，最多 99 个英文和数字的组合或最多 33 个中文字符，不可以包含特殊字符；
        // 渠道为 alipay 时，最多 100 个 Unicode 字符。
        // 渠道为 jdpay 最多100个 Unicode 字符。
        // 渠道为 allinpay 最多30个 Unicode 字符
        transferMap.put("description", "your description");

        transferMap.put("extra", channelExtra(channel));

        Map<String, String> app = new HashMap<String, String>();
        app.put("id", Const.APP_ID);
        transferMap.put("app", app);

        try {
            transfer = Transfer.create(transferMap);
            System.out.println(transfer);
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
        return transfer;
    }

    private String channelRecipient(String channel) {
        String recipient = null;

        switch (channel) {
            case "wx_pub":
                // 渠道为 wx_pub 时，需要传 recipient 为用户在商户 appid 下的 open_id
                recipient = "o9zpMswQdsU9rGgpBKyYyrWX2XK8";
                break;
            case "alipay":
                // 渠道为 alipay 时，若 type 为 b2c，为个人支付宝账号，若 type 为 b2b，为企业支付宝账号。
                recipient = "842207739@qq.com";
                break;
            case "unionpay":
            case "allinpay":
            case "balance":
                recipient = "yvettee_user1";
                break;
            case "jdpay":
                break;
        }

        return recipient;
    }


    private Map<String, Object> channelExtra(String channel) {
        Map<String, Object> extra = new HashMap<>();

        switch (channel) {
            case "alipay":
                extra = alipayExtra();
                break;
            case "wx_pub":
                extra = wxPubExtra();
                break;
            case "unionpay":
                extra = unionpayExtra();
                break;
            case "allinpay":
                extra = allinpayExtra();
                break;
            case "jdpay":
                extra = jdpayExtra();
                break;
        }

        return extra;
    }

    private Map<String, Object> alipayExtra() {
        Map<String, Object> extra = new HashMap<>();
        // 必须，收款人姓名，1~50位。
        extra.put("recipient_name", "温凉");

        // 可选，收款方账户类型。可取值：1、 ALIPAY_USERID ：支付宝账号对应的支付宝唯一用户号。以2088开头的16位纯数字组成。 2、 ALIPAY_LOGONID （默认值）：支付宝登录号，支持邮箱和手机号格式。
        // extra.put("recipient_account_type", "ALIPAY_LOGONID");

        return extra;
    }

    private Map<String, Object> wxPubExtra() {
        Map<String, Object> extra = new HashMap<>();
        // 可选，收款人姓名。当该参数为空，则不校验收款人姓名。
        // extra.put("user_name", "张三");

        // 可选，是否强制校验收款人姓名。仅当  user_name 参数不为空时该参数生效。
        // extra.put("force_check", true);

        return extra;
    }

    private Map<String, Object> unionpayExtra() {
        Map<String, Object> extra = new HashMap<>();
        // 必须，1~32位，收款人银行卡号或者存折号。
        extra.put("card_number", "6228480402564890011");

        // 必须，1~100位，收款人姓名。
        extra.put("user_name", "张三");

        /**
         * open_bank_code 和 open_bank 两个参数必传一个，建议使用 open_bank_code ，若都传参则优先使用 open_bank_code 读取规则；prov 和 city 均为可选参数，如果不传参，则使用默认值 "上海" 给渠道接口。
         */

        // 条件可选，4位，开户银行编号，详情请参考 企业付款（银行卡）银行编号说明：https://www.pingxx.com/api#%E9%93%B6%E8%A1%8C%E7%BC%96%E5%8F%B7%E8%AF%B4%E6%98%8E。
        extra.put("open_bank_code", "0103");

        // 条件可选，1~50位，开户银行，详情请参考 企业付款（银行卡）银行编号说明：https://www.pingxx.com/api#%E9%93%B6%E8%A1%8C%E7%BC%96%E5%8F%B7%E8%AF%B4%E6%98%8E。
        extra.put("open_bank", "农业银行");

        // 可选，1～20位，省份。
        // extra.put("prov", "上海");

        // 可选，1～40位，城市。
        // extra.put("city", "上海");

        // 可选，1～80位，开户支行名称。
        // extra.put("sub_bank", "上海陆家嘴支行");

        return extra;
    }

    private Map<String, Object> allinpayExtra() {
        Map<String, Object> extra = new HashMap<>();
        // 必须，1~32位，收款人银行卡号或者存折号。
        extra.put("card_number", "6228480402564890011");

        // 必须，1~100位，收款人姓名。
        extra.put("user_name", "张三");

        // 必须，4位，开户银行编号，详情请参考 企业付款（银行卡）银行编号说明：https://www.pingxx.com/api#%E9%93%B6%E8%A1%8C%E7%BC%96%E5%8F%B7%E8%AF%B4%E6%98%8E。
        extra.put("open_bank_code", "0103");

        // 可选，5位，业务代码，根据通联业务人员提供，不填使用通联提供默认值09900。
        // extra.put("business_code", "09900");

        // 可选，1位，银行卡号类型，0：银行卡、1：存折，不填默认使用银行卡。
        // extra.put("card_type", 0);

        return extra;
    }

    private Map<String, Object> jdpayExtra() {
        Map<String, Object> extra = new HashMap<>();
        // 必须，1~32位，收款人银行卡号或者存折号。
        extra.put("card_number", "6228480402564890011");

        // 必须，1~100位，收款人姓名。
        extra.put("user_name", "李四");

        // 必须，4位，开户银行编号，详情请参考 企业付款（银行卡）银行编号说明：https://www.pingxx.com/api#%E9%93%B6%E8%A1%8C%E7%BC%96%E5%8F%B7%E8%AF%B4%E6%98%8E。
        extra.put("open_bank_code", "0103");

        return extra;

    }

}
