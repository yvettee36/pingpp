//支付
function wap_pay(channel) {
    var amount = $("#amount").val();
    var params = {
        "amount": amount,
        "channel": channel
    };
    $.ajax({
        type: 'POST',
        data: JSON.stringify(params),
        contentType: "application/json;charset=utf-8",
        dataType: 'json',
        traditional: true, //使json格式的字符串不会被转码
        // url: '/charge/createCharge',//charge接口
        url: '/order/createOrder',//order接口

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

/**
 * 充值
 */
function recharge() {
    var amount = $("#amount").val();
    var options = $("#payWay option:selected"); //获取选中的项

    // alert(options.val()); //拿到选中项的值
    var params = {
        "amount": amount,
        "charge": {
            "channel": options.val()
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
                    // $("#balance").val(""); //清空上次input框里的数据
                    // $('#balance').val(data['amount']);  //往input框里传值
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


/**
 * 转账
 */
function transfer() {
    var amount = $("#amount").val();
    var recipient = $("#recipient").val();
    var balance = parseInt($('#balance').val());
    var params = {
        "amount": amount,
        "recipient": recipient
    };
    $.ajax({
        type: 'POST',
        data: JSON.stringify(params),
        contentType: "application/json;charset=utf-8",
        dataType: 'json',
        traditional: true, //使json格式的字符串不会被转码
        url: '/balance/balanceTransfer',
        success: function (data) {
            console.log(data);
            layer.msg('转账成功', {icon: 1});
            $('#balance').val(balance - Number(data['amount']));  //往input框里传值
        },

        error: function (e) {
            console.log(e)
        }

    });

}

/**
 * 提现
 */
function withdrawal() {


    var amount = $("#amount").val();
    var options = $("#payWay option:selected"); //获取选中的项

    // alert(options.val()); //拿到选中项的值
    var params = {
        "amount": amount,
        "channel": options.val()
    };
    $.ajax({
        type: 'POST',
        data: JSON.stringify(params),
        contentType: "application/json;charset=utf-8",
        dataType: 'json',
        traditional: true, //使json格式的字符串不会被转码
        url: '/balance/balanceWithdrawal',

        success: function (data) {
            console.log(data);
            layer.msg('提现成功', {icon: 1});
        },

        error: function (e) {
            console.log(e)
        }

    });


}
