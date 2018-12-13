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
            <span class="iphone"><img src="img/bgpic.jpg" width="100%" height="auto"></span>
            <label class="text_amount">
                <input id="amount" type="text" placeholder="金 额"/>
            </label>

            <div class="ch">
                <span class="up" onclick="wap_pay('upacp_wap');">银联 WAP</span>
                <span class="up" onclick="wap_pay('alipay_wap');">支付宝 WAP</span>
                <span class="up" onclick="wap_pay('alipay_pc_direct');">支付宝电脑网站支付</span>
                <span class="up" onclick="wap_pay('bfb_wap');">百度钱包 WAP</span>
                <span class="up" onclick="wap_pay('jdpay_wap');">京东支付 WAP</span>
                <span class="up" onclick="wap_pay('yeepay_wap');">易宝支付 WAP</span>
                <span class="up" onclick="wap_pay('wx_pub');">微信浏览器内公众号支付 WAP</span>

            </div>
        </div>
    </div>
</section>
<script src="js/pingpp.js" type="text/javascript"></script>
<script src="js/jquery.min.js" type="text/javascript"></script>

<script>
    function wap_pay(channel) {
        var amount = $("#amount").val();
        var params = {
            "channel": channel,
            "amount": amount
        };
        $.ajax({
            type: 'POST',
            data: JSON.stringify(params),
            contentType: "application/json;charset=utf-8",
            dataType: 'json',
            traditional: true, //使json格式的字符串不会被转码
            url: '/charge/createCharge',

            success: function (data) {
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
            },

            error: function (e) {
                alert("error");

                console.log(e)
            }

        });
    }
</script>
</body>
</html>
