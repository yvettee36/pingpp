<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<base href="<%=basePath%>">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <title>pay</title>
    <link rel="stylesheet" type="text/css" href="styles/pinus.css">
</head>
<body>
<header>
    <div class="h_content">
        <span></span>
    </div>
</header>
<section class="block">
    <div class="content2">
        <div class="app">
            <span class="iphone">
                <img src="img/bgpic.jpg" width="100%" height="auto">
            </span>
            <%--<span class="balance">账 户 余 额：--%>
                <%--<input id="balance" type="text" value="<%= session.getAttribute("amount")%>分"--%>
                       <%--onkeydown="onkeyup();" onkeyup="size=(this.value.length>4?this.value.length:4);"/>--%>
            <%--</span>--%>
            <label class="text_amount">
                <input id="amount" type="text" placeholder="金 额"/>
                <input id="recipient" type="text" placeholder="对方账户"/>
                <div class="select">
                    <select id="payWay" class="payWay">
                        <option value="" disabled selected>充值 or 提现</option>
                        <option value="alipay_pc_direct">支付宝电脑网站</option>
                        <option value="cb_alipay_pc_direct">跨境支付宝电脑网站</option>
                        <option value="wx_pub_qr">微信Native</option>
                        <option value="alipay">支付宝APP</option>
                        <option value="wx">微信APP</option>
                        <option value="wx_pub">微信JSAPI</option>
                    </select>
                </div>
                <input class="btStyle" id="recharge" type="button" value="充值" onclick="recharge()"/>
                <input class="btStyle" id="transfer" type="button" value="转账" onclick="transfer()"/>
                <input class="btStyle" id="withdrawal" type="button" value="提现" onclick="withdrawal()"/>
                <%--<input class="btStyle" id="test" type="button" value="Test" onclick="test()"/>--%>
            </label>

            <div class="ch">
                <span class="up" onclick="wap_pay('alipay_wap');">支付宝手机网页</span>
                <span class="up" onclick="qr_pay('alipay_qr');">支付宝扫码</span>
                <span class="up" onclick="wap_pay('alipay_pc_direct');">支付宝电脑网站</span>
                <span class="up" onclick="wap_pay('cb_alipay_pc_direct');">跨境支付宝电脑网站</span>
                <span class="up" onclick="qr_pay('wx_pub_qr');">微信Native</span>
                <span class="up" onclick="wap_pay('wx_wap');">微信H5</span>
                <span class="up" onclick="wap_pay('upacp_pc');">银联网关支付</span>
                <span class="up" onclick="qr_pay('upacp_qr');">银联二维码</span>
                <span class="up" onclick="wap_pay('upacp_wap');">银联手机网站</span>
                <span class="up" onclick="wap_pay('cmb_wallet');">招行一网通支付</span>
                <span class="up" onclick="qr_pay('isv_qr');">线下扫码</span>
                <span class="up" onclick="wap_pay('isv_wap');">线下固定码</span>
                <span class="up" onclick="wap_pay('paypal');">paypal</span>
                <span class="up" onclick="wap_pay('upacp_b2b');">企业银联支付</span>
                <span class="up" onclick="wap_pay('balance');">支付</span>
            </div>
            <div class="qrCode"></div>
        </div>
    </div>

</section>
<script src="js/pingpp.js" type="text/javascript"></script>
<script src="js/jquery-3.3.1.min.js" type="text/javascript"></script>
<script src="js/layer/layer.js" type="text/javascript"></script>
<script src="js/jquery.qrcode.min.js" type="text/javascript"></script>
<script src="js/action.js" type="text/javascript"></script>
<%--<script>
    var data={
        "id": "2011901210012058241",
        "object": "order",
        "created": 1548055237,
        "livemode": true,
        "paid": false,
        "refunded": false,
        "status": "created",
        "app": "app_ufTqzTr90GuPyP00",
        "uid": "18NeEphThC9KahUyY-DgA..J",
        "available_balance": 0,
        "merchant_order_no": "03201901215815203782",
        "amount": 1,
        "actual_amount": 1,
        "amount_refunded": 0,
        "amount_paid": 0,
        "coupon_amount": 0,
        "currency": "cny",
        "subject": "信贷审核费用",
        "body": "信贷审核费用",
        "client_ip": "127.0.0.1",
        "time_paid": null,
        "time_expire": 1548141637,
        "charge": "ch_9OafrTbHa5SSWDSi1GLWnnXD",
        "coupon": null,
        "description": null,
        "metadata": {},
        "charge_essentials": {
            "channel": "alipay_wap",
            "transaction_no": null,
            "failure_code": null,
            "failure_msg": null,
            "extra": {
                "success_url": "http://192.168.0.101:8090/zhst-web/zhst-pay/pages/pay-success.html?orderNo=2011901210012058241"
            },
            "credential": {
                "object": "credential",
                "alipay_wap": {
                    "app_id": "2019011062886032",
                    "method": "alipay.trade.wap.pay",
                    "format": "JSON",
                    "charset": "utf-8",
                    "sign_type": "RSA",
                    "timestamp": "2019-01-21 15:20:38",
                    "version": "1.0",
                    "biz_content": "{\"body\":\"信贷审核费用\",\"subject\":\"信贷审核费用\",\"out_trade_no\":\"03201901215815203782\",\"total_amount\":0.01,\"product_code\":\"QUICK_WAP_PAY\",\"extend_params\":{\"TRANS_MEMO\":\"ch_9OafrTbHa5SSWDSi1GLWnnXD\"},\"time_expire\":\"2019-01-22 15:20\"}",
                    "notify_url": "https://notify.pingxx.com/notify/charges/ch_9OafrTbHa5SSWDSi1GLWnnXD",
                    "return_url": "http://192.168.0.101:8090/zhst-web/zhst-pay/pages/pay-success.html?orderNo=2011901210012058241",
                    "sign": "LNao1lucD09cNdyTDgu+ORysJ2l92ispfpNH1sC/KPU06ueQGFZ0f4TYgO4IkvWgpefqTt3VCzkKJm8ZKnGu58IPIDKBnqTrjn13/FzVFKf+DsdSlvlateX5/tlvrPwyhd4NYlzpgxDRJ32HXJmCAycEFCxn/7XCm5EbBvFjJoRGQfRJ45zya0EA0XagBpgYP7KOfn7x0fzO2C6zgC6HLZwA+4Aa4JLixOANHF9eOR3MV4VgZZ2DDkH8gCjJVp9gpyrmkLEjvW2IB4Xb7d6Mqfd82qtFsOC1USxYNkf9ixKtA4jah2P/+kWMkbSiIcyypId8MGcqJWs1hVn16sHdKw==",
                    "channel_url": "https://openapi.alipay.com/gateway.do"
                }
            }
        },
        "receipt_app": "app_ufTqzTr90GuPyP00",
        "service_app": "app_ufTqzTr90GuPyP00",
        "available_methods": [
            "balance",
            "alipay_wap"
        ],
        "charges": {
            "object": "list",
            "url": "/v1/charges",
            "has_more": false,
            "data": [
                {
                    "id": "ch_9OafrTbHa5SSWDSi1GLWnnXD",
                    "object": "charge",
                    "created": 1548055237,
                    "livemode": true,
                    "paid": false,
                    "refunded": false,
                    "reversed": false,
                    "app": "app_ufTqzTr90GuPyP00",
                    "channel": "alipay_wap",
                    "order_no": "03201901215815203782",
                    "client_ip": "127.0.0.1",
                    "amount": 1,
                    "amount_settle": 1,
                    "currency": "cny",
                    "subject": "信贷审核费用",
                    "body": "信贷审核费用",
                    "extra": {
                        "success_url": "http://192.168.0.101:8090/zhst-web/zhst-pay/pages/pay-success.html?orderNo=2011901210012058241"
                    },
                    "time_paid": null,
                    "time_expire": 1548141637,
                    "time_settle": null,
                    "transaction_no": null,
                    "refunds": null,
                    "amount_refunded": 0,
                    "failure_code": null,
                    "failure_msg": null,
                    "metadata": {},
                    "credential": {
                        "object": "credential",
                        "alipay_wap": {
                            "app_id": "2019011062886032",
                            "method": "alipay.trade.wap.pay",
                            "format": "JSON",
                            "charset": "utf-8",
                            "sign_type": "RSA",
                            "timestamp": "2019-01-21 15:20:38",
                            "version": "1.0",
                            "biz_content": "{\"body\":\"信贷审核费用\",\"subject\":\"信贷审核费用\",\"out_trade_no\":\"03201901215815203782\",\"total_amount\":0.01,\"product_code\":\"QUICK_WAP_PAY\",\"extend_params\":{\"TRANS_MEMO\":\"ch_9OafrTbHa5SSWDSi1GLWnnXD\"},\"time_expire\":\"2019-01-22 15:20\"}",
                            "notify_url": "https://notify.pingxx.com/notify/charges/ch_9OafrTbHa5SSWDSi1GLWnnXD",
                            "return_url": "http://192.168.0.101:8090/zhst-web/zhst-pay/pages/pay-success.html?orderNo=2011901210012058241",
                            "sign": "LNao1lucD09cNdyTDgu+ORysJ2l92ispfpNH1sC/KPU06ueQGFZ0f4TYgO4IkvWgpefqTt3VCzkKJm8ZKnGu58IPIDKBnqTrjn13/FzVFKf+DsdSlvlateX5/tlvrPwyhd4NYlzpgxDRJ32HXJmCAycEFCxn/7XCm5EbBvFjJoRGQfRJ45zya0EA0XagBpgYP7KOfn7x0fzO2C6zgC6HLZwA+4Aa4JLixOANHF9eOR3MV4VgZZ2DDkH8gCjJVp9gpyrmkLEjvW2IB4Xb7d6Mqfd82qtFsOC1USxYNkf9ixKtA4jah2P/+kWMkbSiIcyypId8MGcqJWs1hVn16sHdKw==",
                            "channel_url": "https://openapi.alipay.com/gateway.do"
                        }
                    },
                    "description": null
                }
            ]
        }
    }

    pingpp.createPayment(data, function (result, err) {
        // object 需是 Charge/Order/Recharge 的 JSON 字符串
        // 可按需使用 alert 方法弹出 log
        console.log(result);
        console.log(err.msg);
        console.log(err.extra);
        if (result == "success") {
            alert("OK")
            // 只有微信公众号 (wx_pub)、微信小程序（wx_lite）、QQ 公众号 (qpay_pub)、支付宝小程序（alipay_lite）支付成功的结果会在这里返回，其他的支付结果都会跳转到 extra 中对应的 URL
        } else if (result == "fail") {
            alert("fail")
            // Ping++ 对象 object 不正确或者微信公众号/微信小程序/QQ公众号支付失败时会在此处返回
        } else if (result == "cancel") {
            alert("cancel")
            // 微信公众号、微信小程序、QQ 公众号、支付宝小程序支付取消支付
        }
    });
</script>--%>
</body>
</html>
