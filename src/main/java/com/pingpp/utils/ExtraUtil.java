package com.pingpp.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtraUtil {

    // extra 根据渠道会有不同的参数
    public static Map<String, Object> alipayExtra() {
        Map<String, Object> extra = new HashMap<>();

        // 可选，开放平台返回的包含账户信息的 token（授权令牌，商户在一定时间内对支付宝某些服务的访问权限）。通过授权登录后获取的  alipay_open_id ，作为该参数的  value ，登录授权账户即会为支付账户，32 位字符串。
        // extra.put("extern_token", "TOKEN");

        // 可选，是否发起实名校验，T 代表发起实名校验；F 代表不发起实名校验。
//        extra.put("rn_check", "F");

        return extra;
    }

    public static Map<String, Object> alipayWapExtra() {
        Map<String, Object> extra = new HashMap<>();

        // 必须，支付成功的回调地址，在本地测试不要写 localhost ，请写 127.0.0.1。URL 后面不要加自定义参数。
        extra.put("success_url", "https://example.com/success");
//        extra.put("success_url", "http://127.0.0.1:8040/channel/promote?productId=141666&channel=123");

        // 可选，支付取消的回调地址， app_pay 为true时，该字段无效，在本地测试不要写 localhost ，请写 127.0.0.1。URL 后面不要加自定义参数。
        extra.put("cancel_url", "https://example.com/success");
        extra.put("hb_fq_num", 12);
        extra.put("hb_fq_seller_percent", "100%");

        // 可选，2016 年 6 月 16 日之前登录 Ping++ 管理平台填写支付宝手机网站的渠道参数的旧接口商户，需要更新接口时设置此参数值为true，6月16号后接入的新接口商户不需要设置该参数。
        // extra.put("new_version", true);

        // 可选，是否使用支付宝客户端支付，该参数为true时，调用客户端支付。
        // extra.put("app_pay", true);

        return extra;
    }

    public static Map<String, Object> alipayPcDirectExtra() {
        Map<String, Object> extra = new HashMap<>();
        // 必须，支付成功的回调地址，在本地测试不要写 localhost ，请写 127.0.0.1。URL 后面不要加自定义参数。
        extra.put("success_url", "www.baidu.com");

        // 可选，是否开启防钓鱼网站的验证参数（如果已申请开通防钓鱼时间戳验证，则此字段必填）。
        // extra.put("enable_anti_phishing_key", false);

        // 可选，客户端 IP ，用户在创建交易时，该用户当前所使用机器的IP（如果商户申请后台开通防钓鱼IP地址检查选项，此字段必填，校验用）。
        // extra.put("exter_invoke_ip", "192.168.100.8");

        return extra;
    }

    public static Map<String, Object> alipayQrExtra() {
        Map<String, Object> extra = new HashMap<>();

        return extra;
    }

    public static Map<String, Object> wxExtra() {
        Map<String, Object> extra = new HashMap<>();
        // 可选，指定支付方式，指定不能使用信用卡支付可设置为 no_credit 。
        extra.put("limit_pay", "no_credit");

        // 可选，商品标记，代金券或立减优惠功能的参数。
        // extra.put("goods_tag", "YOUR_GOODS_TAG");

        return extra;
    }

    public static Map<String, Object> wxPubExtra() {
        Map<String, Object> extra = new HashMap<>();
        // 可选，指定支付方式，指定不能使用信用卡支付可设置为 no_credit 。
        extra.put("limit_pay", "no_credit");

        // 可选，商品标记，代金券或立减优惠功能的参数。
        // extra.put("goods_tag", "YOUR_GOODS_TAG");

        // 必须，用户在商户 appid 下的唯一标识。
        extra.put("open_id", "o9zpMswQdsU9rGgpBKyYyrWX2XK8");

        return extra;
    }

    public static Map<String, Object> wxPubQrDirectExtra() {
        Map<String, Object> extra = new HashMap<>();
        // 可选，指定支付方式，指定不能使用信用卡支付可设置为 no_credit 。
        extra.put("limit_pay", "no_credit");

        // 可选，商品标记，代金券或立减优惠功能的参数。
        // extra.put("goods_tag", "YOUR_GOODS_TAG");

        // 必须，商品 ID，1-32 位字符串。此 id 为二维码中包含的商品 ID，商户可自定义。
        extra.put("product_id", CommonUtil.randomString(16));

        return extra;
    }

    public static Map<String, Object> wxLiteExtra() {
        Map<String, Object> extra = new HashMap<>();
        // 可选，指定支付方式，指定不能使用信用卡支付可设置为 no_credit 。
        extra.put("limit_pay", "no_credit");

        // 可选，商品标记，代金券或立减优惠功能的参数。
        // extra.put("goods_tag", "YOUR_GOODS_TAG");

        // 必须，用户在商户 appid 下的唯一标识。
        extra.put("open_id", "o7xEMsySBFG3MVHI-9VsAJX-j50W");

        return extra;
    }

    public static Map<String, Object> wxWapExtra() {
        Map<String, Object> extra = new HashMap<>();
        // 可选，支付完成的回调地址。
//        extra.put("result_url", "https://example.com/success");
        extra.put("result_url", "https://alibetahybrid.followme.com/subscription/payment-result?orderId=13190316183535085317&_appVersion=pro-v4.2.5.25s");

        // 可选，商品标记，代金券或立减优惠功能的参数。
        // extra.put("goods_tag", "YOUR_GOODS_TAG");

        return extra;
    }

    public static Map<String, Object> bfbExtra() {
        Map<String, Object> extra = new HashMap<>();

        return extra;
    }

    public static Map<String, Object> bfbWapExtra() {
        Map<String, Object> extra = new HashMap<>();
        // 必须，支付完成的回调地址，在本地测试不要写 localhost ，请写 127.0.0.1。URL 后面不要加自定义参数。
        extra.put("result_url", "https://example.com/success");

        // 必须，是否需要登录百度钱包来进行支付。
        extra.put("bfb_login", true);

        return extra;
    }

    public static Map<String, Object> upacpExtra() {
        Map<String, Object> extra = new HashMap<>();

        return extra;
    }

    public static Map<String, Object> upacpWapExtra() {
        Map<String, Object> extra = new HashMap<>();
        // 必须，支付完成的回调地址，在本地测试不要写 localhost ，请写 127.0.0.1。URL 后面不要加自定义参数。
//        extra.put("result_url", "https://alibetahybrid.followme.com/subscription/payment-result?orderId=13190316183535085317&_appVersion=pro-v4.2.5.25");
        extra.put("result_url", "https://example.com/success");

        return extra;
    }

    public static Map<String, Object> upacpPcExtra() {
        Map<String, Object> extra = new HashMap<>();
        // 必须，支付完成的回调地址，在本地测试不要写 localhost ，请写 127.0.0.1。URL 后面不要加自定义参数。
        extra.put("result_url", "https://example.com/success");

        return extra;
    }

    public static Map<String, Object> jdpayWapExtra() {
        Map<String, Object> extra = new HashMap<>();
        // 必须，支付完成的回调地址。
        extra.put("success_url", "https://example.com/success");

        // 必须，支付失败页面跳转路径。
        extra.put("fail_url", "https://example.com/fail");

        // 可选，用户交易令牌，用于识别用户信息，支付成功后会调用 success_url 返回给商户。商户可以记录这个 token 值，当用户再次支付的时候传入该  token ，用户无需再次输入银行卡信息，直接输入短信验证码进行支付。32 位字符串。
        // extra.put("token", "TOKEN");

        // 可选，订单类型，值为0表示实物商品订单，值为 1 代表虚拟商品订单，该参数默认值为 0 。
        // extra.put("order_type", 0);

        // 可选，设置是否通过手机端发起支付，值为  true 时调用手机 h5 支付页面，值为  false 时调用 PC 端支付页面，该参数默认值为  true 。
        extra.put("is_mobile", true);

        // 可选，用户账号类型，取值只能为：BIZ。传参存在问题请参考 帮助中心：https://help.pingxx.com/article/1012535/。
        // extra.put("user_type", "BIZ");

        // 可选，商户的用户账号。传参存在问题请参考 帮助中心：https://help.pingxx.com/article/1012535/。
        // extra.put("user_id", "YOUR_USER_ID");

        return extra;
    }

    public static Map<String, Object> yeepayWapExtra() {
        Map<String, Object> extra = new HashMap<>();
        // 必须，商品类别码，商品类别码参考链接 ：https://www.pingxx.com/api#%E6%98%93%E5%AE%9D%E6%94%AF%E4%BB%98%E5%95%86%E5%93%81%E7%B1%BB%E5%9E%8B%E7%A0%81 。
        extra.put("product_category", "1");

        // 必须，用户标识,商户生成的用户账号唯一标识，最长 50 位字符串。
        extra.put("identity_id", "IDENTITY_ID");

        // 必须，用户标识类型，用户标识类型参考链接：https://www.pingxx.com/api#%E6%98%93%E5%AE%9D%E6%94%AF%E4%BB%98%E7%94%A8%E6%88%B7%E6%A0%87%E8%AF%86%E7%B1%BB%E5%9E%8B%E7%A0%81 。
        extra.put("identity_type", 2);

        // 必须，终端类型，对应取值 0:IMEI, 1:MAC, 2:UUID, 3:other。
        extra.put("terminal_type", 2);

        // 必须，终端 ID。
        extra.put("terminal_id", "TERMINAL_ID");

        // 必须，用户使用的移动终端的 UserAgent 信息。
        extra.put("user_ua", "USER_UA");

        // 必须，前台通知地址。
        extra.put("result_url", "https://example.com/result");

        return extra;
    }

    public static Map<String, Object> applepayUpacpExtra() {
        Map<String, Object> extra = new HashMap<>();

        return extra;
    }

    public static Map<String, Object> qpayExtra() {
        Map<String, Object> extra = new HashMap<>();
        // 必须，客户端设备类型，取值范围: "ios" ，"android"。
        extra.put("device", "ios");

        return extra;
    }

    public static Map<String, Object> cmbWalletExtra() {
        Map<String, Object> extra = new HashMap<>();
        // 必须，交易完成跳转的地址。
        extra.put("result_url", "https://example.com/result");

        /**
         * 对于 p_no, seq , m_uid , mobile 这几个参数：
         * 1. 这几个参数是用户自定义的。
         * 2. 对于同一个终端用户每次请求 charge 务必使用同一套参数（确保每个参数都不变），
         * 任意参数变更都会导致用户重新签约，同一个用户和招行重新签约的次数有限制，超限制就会无法签约 ，导致用户无法使用。
         */

        // 必须，客户协议号，不超过 30 位的纯数字字符串。
        extra.put("p_no", "201700100001");

        // 必须，协议开通请求流水号，不超过 20 位的纯数字字符串，请保证系统内唯一。
        extra.put("seq", "201700200001");

        // 必须，协议用户 ID，不超过 20 位的纯数字字符串。
        extra.put("m_uid", "201700300001");

        // 必须，协议手机号，11 位数字。
        extra.put("mobile", "13523456789");

        return extra;
    }

    public static Map<String, Object> cpB2bExtra() {
        Map<String, Object> extra = new HashMap<>();

        return extra;
    }

    public static Map<String, Object> isvScanExtra() {
        Map<String, Object> extra = new HashMap<>();
        // 必须，终端号，1~8 位英文或数字，要求不同终端此号码不一样，会显示在对账单中。
        extra.put("terminal_id", "A0000007");

        // 必须，客户端软件中展示的条码值，扫码设备扫描获取。1~32 位字符串。
        extra.put("scan_code", "280614577834623988");

        // 可选，商品列表，上送格式参照下面示例。序列化后总字符串长度不超过 8000。
        List<Object> goodsList = goodsListForIsv();
        extra.put("goods_list", goodsList);

        return extra;
    }

    public static Map<String, Object> isvQrExtra() {
        Map<String, Object> extra = new HashMap<>();
        // 必须，终端号，1~8 位英文或数字，要求不同终端此号码不一样，会显示在对账单中。
        extra.put("terminal_id", "A0000007");

        // 必须，具体支付渠道，目前支持：alipay、wx、bfb。
        extra.put("pay_channel", "wx");

        // 可选，商品列表，上送格式参照下面示例。序列化后总字符串长度不超过 8000。
//        List<Object> goodsList = goodsListForIsv();
//        extra.put("goods_list", goodssList);

        return extra;
    }

    public static Map<String, Object> isvWapExtra() {
        Map<String, Object> extra = new HashMap<>();
        // 必须，终端号，1~8 位英文或数字，要求不同终端此号码不一样，会显示在对账单中。
        extra.put("terminal_id", "A0000007");

        // 必须，具体支付渠道，目前支持：alipay、wx、bfb。
        extra.put("pay_channel", "alipay");

        // 必须，前台通知地址，支付成功或失败后，跳转到的 URL。
        extra.put("result_url", "https://www.example.com/payment-result");

        // 可选，商品列表，上送格式参照下面示例。
        List<Object> goodsList = goodsListForIsv();
        extra.put("goods_list", goodsList);

        return extra;
    }

    public static Map<String, Object> alipayScanExtra() {
        Map<String, Object> extra = new HashMap<>();
        // 必填，客户端软件中展示的条码值，扫码设备扫描获取。
        extra.put("scan_code", "286801346868493272");
        // 必填，终端号，要求不同终端此号码不一样，会显示在对账单中，如A01、SH008等。
        extra.put("terminal_id", "SH008");
        // 可选，商户操作员编号(可包含字母、数字、下划线、中划线)
        extra.put("operator_id", "yx_001");
        // 可选，商户门店编号(可包含字母、数字、下划线、中划线)
        extra.put("store_id", "SH_001");
        // 可选，系统商编号
        extra.put("sys_service_provider_id", "2088511833207846");

        // 可选，商品列表，上送格式参照下面示例。
        List<Object> goodsList = goodsListForAlipayScan();
        extra.put("goods_list", goodsList);
        return extra;
    }

    public static Map<String, Object> wxPubScanExtra() {
        Map<String, Object> extra = new HashMap<>();
        // 必填，客户端软件中展示的条码值，扫码设备扫描获取。
        extra.put("scan_code", "286801346868493272");
        // 可选，终端号，要求不同终端此号码不一样，会显示在对账单中，如A01、SH008等。
        extra.put("terminal_id", "SH008");
        // 可选，指定支付方式，指定不能使用信用卡支付可设置为 no_credit 。
        extra.put("limit_pay", "no_credit");
        // 可选，商品标记，代金券或立减优惠功能的参数。
        // extra.put("goods_tag", "your goods_tag");
        return extra;
    }

    public static Map<String, Object> cbAlipayExtra() {
        Map<String, Object> extra = new HashMap<>();
        // 可选，支付类型。默认值为：1（商品购买）。
        extra.put("payment_type", 1);

        // 可选，分账列表。
        List<Object> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("account", "2088866088886666"); // 接受分账资金的支付宝账户ID。
        map.put("amount", 1);  // 分账的金额。
        map.put("desc", "split_desc desc");  // 分账描述信息。
        extra.put("split_fund_info", list);
        return extra;
    }

    public static Map<String, Object> cbAlipayPcDirectExtra() {
        Map<String, Object> extra = new HashMap<>();
        // 可选，支付类型。默认值为：1（商品购买）。
        extra.put("refer_url", "www.baidu.com");

        // 可选，分账列表。
        Map<String, Object> map = new HashMap<>();
        map.put("business_type", "2"); // 接受分账资金的支付宝账户ID。
        map.put("flight_number", "NWS 996"); // 接受分账资金的支付宝账户ID。
        map.put("departure_time", "2019-06-22 20:49"); // 接受分账资金的支付宝账户ID。
        extra.put("trade_information",map);// 分账描述信息。
        return extra;
    }

    public static Map<String, Object> cbWxExtra() {
        Map<String, Object> extra = new HashMap<>();
        // 可选，指定支付方式，指定不能使用信用卡支付可设置为 no_credit 。
        extra.put("limit_pay", "no_credit");

        // 必填，商品列表
        List<Object> goodsList = goodsListForCbWx();
        extra.put("goods_list", goodsList);
        return extra;
    }

    public static Map<String, Object> cbWxPubExtra() {
        Map<String, Object> extra = new HashMap<>();
        // 可选，指定支付方式，指定不能使用信用卡支付可设置为 no_credit 。
        extra.put("limit_pay", "no_credit");
        // // 必填，用户在商户 appid 下的唯一标识。
        extra.put("open_id", "openidxxxxxxxxxxxx");
        // 必填，商品列表
        List<Object> goodsList = goodsListForCbWx();
        extra.put("goods_list", goodsList);
        return extra;
    }

    public static Map<String, Object> cbWxPubQrExtra() {
        Map<String, Object> extra = new HashMap<>();
        // 可选，指定支付方式，指定不能使用信用卡支付可设置为 no_credit
        extra.put("limit_pay", "no_credit");
        // 必填，商品 ID，1-32 位字符串。此 id 为二维码中包含的商品 ID，商户可自定义。
        extra.put("product_id", "286801346868493272");
        // 必填，商品列表
        List<Object> goodsList = goodsListForCbWx();
        extra.put("goods_list", goodsList);
        return extra;
    }

    public static Map<String, Object> cbWxPubScanExtra() {
        Map<String, Object> extra = new HashMap<>();
        // 必填，客户端软件中展示的条码值，扫码设备扫描获取。
        extra.put("scan_code", "286801346868493272");
        // 可选，指定支付方式，指定不能使用信用卡支付可设置为 no_credit 。
        extra.put("limit_pay", "no_credit");
        // 可选，终端号，要求不同终端此号码不一样，会显示在对账单中，如A01、SH008等。
        extra.put("terminal_id", "SH008");
        // 必填，商品列表
        List<Object> goodsList = goodsListForCbWx();
        extra.put("goods_list", goodsList);
        return extra;
    }

    public static Map<String, Object> paypalExtra() {
        Map<String, Object> extra = new HashMap<>();

        // 必须，支付成功的回调地址，在本地测试不要写 localhost ，请写 127.0.0.1。URL 后面不要加自定义参数。
        extra.put("result_url", "https://example.com/success");
//        extra.put("result_url", "https://alibetahybrid.followme.com/subscription/payment-result?orderId=13190316183535085317&_appVersion=pro-v4.2.5.25");
        // 可选，支付取消的回调地址， app_pay 为true时，该字段无效，在本地测试不要写 localhost ，请写 127.0.0.1。URL 后面不要加自定义参数。
        extra.put("cancel_url", "https://example.com/cancel");

        // 可选，2016 年 6 月 16 日之前登录 Ping++ 管理平台填写支付宝手机网站的渠道参数的旧接口商户，需要更新接口时设置此参数值为true，6月16号后接入的新接口商户不需要设置该参数。
        // extra.put("new_version", true);

        // 可选，是否使用支付宝客户端支付，该参数为true时，调用客户端支付。
        // extra.put("app_pay", true);

        return extra;
    }

    public static List<Object> goodsListForIsv() {
        List<Object> goodsList = new ArrayList<>();
        Map<String, Object> goods = new HashMap<>();
        goods.put("goods_id", "iphone6s16G"); // 商户定义商品编号（一般为商品条码）。
        goods.put("unified_goods_id", "1001"); // 统一商品编号，可选。
        goods.put("goods_name", "iPhone 6s 16G"); // 商品名称。
        goods.put("goods_num", 1); // 商品数量。
        goods.put("price", "528800"); // 商品价格，单位为分。
        goods.put("goods_category", "smartphone"); // 商品类目，可选。
        goods.put("body", "苹果手机 iPhone 6s 16G"); // 商品描述信息，可选。
        goods.put("show_url", "https://www.example.com"); // 商品的展示网址，可选。
        goodsList.add(goods);

        return goodsList;
    }

    public static List<Object> goodsListForAlipayScan() {
        List<Object> goodsList = new ArrayList<>();
        Map<String, Object> goods = new HashMap<>();
        goods.put("goods_id", "iphone"); // 商户定义商品编号（一般为商品条码）。
        goods.put("goods_name", "iPhone"); // 商品名称
        goods.put("quantity", 1); // 商品数量。
        goods.put("price", "528800"); // 商品价格，单位为分。
        goods.put("goods_category", "123456"); // 商品类目，可选。
        goods.put("body", "苹果手机"); // 商品描述信息，可选。
        goods.put("show_url", "https://www.example.com"); // 商品的展示网址，可选。
        goodsList.add(goods);

        return goodsList;
    }

    public static List<Object> goodsListForCbWx() {
        List<Object> goodsList = new ArrayList<>();
        Map<String, Object> goods = new HashMap<>();
        goods.put("goods_name", "iPhone"); // 商品名称
        goods.put("goods_num", "1"); // 数量
        goodsList.add(goods);
        return goodsList;
    }
}
