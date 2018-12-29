package com.pingpp.controller;

import com.pingplusplus.model.Recharge;
import com.pingplusplus.model.User;
import com.pingpp.service.RechargeService;
import com.pingpp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(value = "/balance")
@Controller
public class BalanceController {

    private UserService userService = new UserService();
    private RechargeService rechargeService = new RechargeService();

    /**
     * 获取余额
     *
     * @param request
     * @param response
     * @param user
     * @return
     */
    @RequestMapping(value = "/getBalance")
    @ResponseBody
    public String getBalance(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) {
        User user1 = userService.getUser("yvettee_user1");

        return null;
    }

    /**
     * 充值
     *
     * @param request
     * @param response
     * @param recharge
     * @return
     */
    @RequestMapping(value = "/createRecharge")
    @ResponseBody
    public String createRecharge(HttpServletRequest request, HttpServletResponse response, @RequestBody Recharge recharge) {
        User user1 = userService.getUser("yvettee_user1");
        recharge.setUser(user1.getId());
        Recharge recharge1 = rechargeService.createRecharge(recharge);
        return recharge1.toString();
    }
}
