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
            <span class="balance">账 户 余 额：<%= session.getAttribute("amount")%></span>
            <label class="text_amount">
                <input id="amount" type="text" placeholder="金 额"/>
                <div class="select">
                    <select id="payWay" class="payWay">
                        <option value="" disabled selected>支付方式选择</option>
                        <option value="alipay_pc_direct">支付宝电脑网站支付</option>
                        <option value="wx_pub_qr">微信Native支付</option>
                    </select>
                </div>
                <input class="btStyle" id="recharge" type="button" value="充值" onclick="recharge()"/>
                <input class="btStyle" id="transfer" type="button" value="转账" onclick="transfer()"/>
                <input class="btStyle" id="withdrawal" type="button" value="提现" onclick="withdrawal()"/>
            </label>

            <div class="ch">
                <span class="up" onclick="wap_pay('alipay_wap');">支付宝手机网页支付</span>
                <span class="up" onclick="qr_pay('alipay_qr');">支付宝扫码支付</span>
                <span class="up" onclick="wap_pay('alipay_pc_direct');">支付宝电脑网站支付</span>
                <span class="up" onclick="qr_pay('wx_pub_qr');">微信Native支付</span>
                <span class="up" onclick="wap_pay('wx_wap');">微信H5支付</span>
                <span class="up" onclick="wap_pay('upacp_pc');">银联网关支付</span>
                <span class="up" onclick="qr_pay('upacp_qr');">银联二维码支付</span>
                <span class="up" onclick="wap_pay('upacp_wap');">银联手机网站支付</span>
                <span class="up" onclick="qr_pay('isv_qr');">线下扫码</span>
            </div>
            <div class="qrCode"></div>

            <div>

            </div>
        </div>
    </div>

</section>
<script src="js/pingpp.js" type="text/javascript"></script>
<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/jquery.qrcode.min.js" type="text/javascript"></script>
<script src="js/layer.js" type="text/javascript"></script>

<script>

    //支付
    function wap_pay(channel) {
        var amount = $("#amount").val();
        var params = {
            "amount": amount,
            "charge":{
                "channel":channel
            }
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
                console.log(e)
            }

        });
    }

    function qr_pay(channel) {
        $(".qrCode").hide();
        var amount = $("#amount").val();
        var params = {
            "channel": channel,
            "amount": amount
        };
        $.ajax({
            type: 'POST',
            data: JSON.stringify(params),
            contentType: "application/json;charset=utf-8",
            dataType: 'text',
            traditional: true, //使json格式的字符串不会被转码
            url: '/charge/getQrCode',

            success: function (data) {
                $(".ch").hide();
                $(".qrCode").show();

                /*注意：这个时候生成的二维码在微信中长按没任何反应，因为qrcode生成的是canvas标签而不是img标签*/
                $(".qrCode").qrcode({
                    render: "table", //table方式
                    width: 200, //宽度
                    height: 200, //高度
                    correctLevel: 0,
                    text: data //任意内容
                });
                console.log(data)
            },

            error: function (e) {
                console.log(e)
            }

        });
    }


    function recharge() {
        var amount = $("#amount").val();
        var options=$("#payWay option:selected"); //获取选中的项

        // alert(options.val()); //拿到选中项的值
        var params = {
            "amount": amount,
            "charge":{
                "channel":options.val()
            }
        };
        $.ajax({
            type: 'POST',
            data: JSON.stringify(params),
            contentType: "application/json;charset=utf-8",
            dataType: 'json',
            traditional: true, //使json格式的字符串不会被转码
            url: '/balance/createRecharge',

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
                console.log(e)
            }

        });
    }

</script>
</body>
</html>
