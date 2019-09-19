package com.pingpp.service;

import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.*;
import com.pingplusplus.model.SubApp;
import com.pingplusplus.model.SubAppCollection;
import com.pingplusplus.model.User;
import com.pingpp.utils.Const;

import java.util.HashMap;
import java.util.Map;

public class UserService {

    public User createUser(User user) {
        Pingpp.appId = Const.APP_ID;
        Pingpp.apiKey = Const.APP_KEY;
        Pingpp.privateKey = Const.APP_PRIVATE_KEY;
        Map<String, Object> userMap = new HashMap<String, Object>();
        userMap.put("id", user.getId());
        try {
            user = User.create(userMap);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        } catch (ChannelException e) {
            e.printStackTrace();
        } catch (RateLimitException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 查询User
     *
     * @param id
     * @return
     */
    public User getUser(String id) {
        Pingpp.appId = Const.APP_ID;
        Pingpp.apiKey = Const.APP_KEY;
        Pingpp.privateKey = Const.APP_PRIVATE_KEY;
        Pingpp.setApiBase("https://api.pingplusplus.com");
        try {
            return User.retrieve(id);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        } catch (ChannelException e) {
            e.printStackTrace();
        } catch (RateLimitException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 创建子商户
     *
     * @return
     */
    public SubApp createSub() {
        Pingpp.appId = Const.APP_ID;
        Pingpp.apiKey = Const.APP_KEY;
        Pingpp.privateKey = Const.APP_PRIVATE_KEY;
        Pingpp.setApiBase("https://api.pingplusplus.com");
        Map<String, Object> subapp = new HashMap();
        subapp.put("display_name", "Test");
        subapp.put("user", "test123test");

        try {
            return SubApp.create(subapp);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        } catch (ChannelException e) {
            e.printStackTrace();
        } catch (RateLimitException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询子商户
     *
     * @return
     */
    public SubApp getRetriveSub() {
        Pingpp.appId = Const.APP_ID;
        Pingpp.apiKey = Const.APP_KEY;
        Pingpp.privateKey = Const.APP_PRIVATE_KEY;
        Pingpp.setApiBase("https://api.pingplusplus.com");

        try {
            return SubApp.retrieve("app_jDyHSOyHebT45WHG");
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        } catch (ChannelException e) {
            e.printStackTrace();
        } catch (RateLimitException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询子商户列表
     *
     * @return
     */
    public SubAppCollection getSub() {
        Pingpp.appId = Const.APP_ID;
        Pingpp.apiKey = Const.APP_KEY;
        Pingpp.privateKey = Const.APP_PRIVATE_KEY;
        Pingpp.setApiBase("https://api.pingplusplus.com");
        Map<String, Object> subapp = new HashMap();
        try {
            return SubApp.list(subapp);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        } catch (ChannelException e) {
            e.printStackTrace();
        } catch (RateLimitException e) {
            e.printStackTrace();
        }
        return null;
    }
}
