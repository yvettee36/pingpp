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
            <span class="balance">账 户 余 额：
                <input id="balance" type="text" value="<%= session.getAttribute("amount")%>分"
                       onkeydown="onkeyup();" onkeyup="size=(this.value.length>4?this.value.length:4);"
                />
            </span>
            <label class="text_amount">
                <input id="amount" type="text" placeholder="金 额"/>
                <input id="recipient" type="text" placeholder="对方账户"/>
                <div class="select">
                    <select id="payWay" class="payWay">
                        <option value="" disabled selected>充值 or 提现</option>
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
        </div>
    </div>

</section>
<script src="js/pingpp.js" type="text/javascript"></script>
<script src="js/jquery-3.3.1.min.js" type="text/javascript"></script>
<script src="js/layer/layer.js" type="text/javascript"></script>
<script src="js/jquery.qrcode.min.js" type="text/javascript"></script>
<script src="js/action.js" type="text/javascript"></script>


<script>


    function withdrawal() {

        layer.msg('提现成功', {icon: 1});
    }

</script>
</body>
</html>
