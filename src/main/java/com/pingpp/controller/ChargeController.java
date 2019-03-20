package com.pingpp.controller;

import com.pingplusplus.model.Charge;
import com.pingpp.service.ChargeService;
import com.pingpp.utils.IPUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/charge")
public class ChargeController {

    private ChargeService chargeService = new ChargeService();

    /**
     * 支付
     *
     * @param request
     * @param response
     * @param charge
     * @return
     */
    @RequestMapping(value = "/createCharge")
    @ResponseBody
    public String createCharge(HttpServletRequest request, HttpServletResponse response, @RequestBody Charge charge) {

        charge.setClientIp(IPUtil.getIp(request));
//        charge.setClientIp("180.168.5.158");
        Charge charge1 = chargeService.createCharge(charge);
        String chargeString = charge1.toString();

        return chargeString;
    }


    /**
     * 生成二维码，返回到页面上
     *
     * @param request
     * @param response
     * @param charge
     * @return
     */
    @RequestMapping(value = "/getQrCode")
    @ResponseBody
    public String getQrCode(HttpServletRequest request, HttpServletResponse response, @RequestBody Charge charge) {
        charge.setClientIp(IPUtil.getIp(request));
        Charge charge1 = chargeService.createCharge(charge);
        Map<String, Object> credentialMap = new HashMap<>();
        credentialMap = charge1.getCredential();
        String url = null;
        
        for (String key : credentialMap.keySet()) {
            if (charge.getChannel().equals(key)){

                url = (String) credentialMap.get(charge.getChannel());
            }

        }

        return url;
    }



}
