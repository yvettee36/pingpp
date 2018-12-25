package com.pingpp.service;

import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.PingppException;
import com.pingplusplus.model.Charge;
import com.pingpp.utils.Const;

import java.util.HashMap;
import java.util.Map;

public class RetrieveService {

    public Charge getCharge(String id){
        Pingpp.appId = Const.APP_ID;
        Pingpp.apiKey = Const.APP_KEY;
        Pingpp.privateKey = Const.APP_PRIVATE_KEY;
        Charge charge = null;
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            charge = Charge.retrieve(id, params);
            System.out.println(charge);
        } catch (PingppException e) {
            e.printStackTrace();
        }

        return charge;
    }
}
