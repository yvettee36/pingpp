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

@Controller
@RequestMapping("/charge")
public class ChargeController {

    private ChargeService chargeService = new ChargeService();

    @RequestMapping(value = "/createCharge")
    @ResponseBody
    public String createCharge(HttpServletRequest request, HttpServletResponse response, @RequestBody Charge charge) {
        charge.setClientIp(IPUtil.getIp(request));
        String chargeString = chargeService.createCharge(charge);
        return chargeString;
    }

}
