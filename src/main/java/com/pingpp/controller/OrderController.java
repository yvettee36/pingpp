package com.pingpp.controller;

import com.pingplusplus.model.Order;
import com.pingpp.service.OrderService;
import com.pingpp.utils.IPUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService = new OrderService();

    /**
     * 支付
     *
     * @param request
     * @param response
     * @param order
     * @return
     */
    @RequestMapping(value = "/createOrder")
    @ResponseBody
    public String createOrder(HttpServletRequest request, HttpServletResponse response, @RequestBody Order order) {

        order.setClientIp(IPUtil.getIp(request));

        //创建order
        order = orderService.createOrder(order);
        //支付order
        Order order1 = orderService.payOrder(order);
        String orderString = order1.toString();
        System.out.println("orderString:" + orderString);
        return orderString;
    }
}
