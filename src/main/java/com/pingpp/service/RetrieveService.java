package com.pingpp.service;

import com.pingplusplus.exception.PingppException;
import com.pingplusplus.model.Charge;

import java.util.HashMap;
import java.util.Map;

public class RetrieveService {

    public Charge getCharge(String id){
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
