package com.pingpp.service;

import com.pingplusplus.model.User;

import javax.servlet.http.HttpSession;

public class IndexService {

    private UserService userService = new UserService();

    public String index(HttpSession session) {
        User user1 = userService.getUser("yvettee_user1");
        Long amount = user1.getAvailableBalance();

        session.setAttribute("amount", amount);

        return "index";
    }
}