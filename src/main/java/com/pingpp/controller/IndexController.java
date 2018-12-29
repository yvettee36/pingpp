package com.pingpp.controller;

import com.pingpp.service.IndexService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 加载首页
 */
@Controller
public class IndexController {

    private IndexService indexService = new IndexService();

    @RequestMapping(value = "/index")
    public String index(HttpSession session) {

        String view = indexService.index(session);
        return view;
    }

}
