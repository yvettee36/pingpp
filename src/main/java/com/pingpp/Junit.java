package com.pingpp;

import com.pingplusplus.model.Charge;
import com.pingplusplus.model.Refund;
import com.pingpp.service.ChargeService;
import com.pingpp.service.RefundService;
import org.junit.jupiter.api.Test;

public class Junit {


    private ChargeService chargeService = new ChargeService();
    private RefundService refundService = new RefundService();
    @Test
    public void testCharge(){
        Charge charge = new Charge();
        charge.setChannel("alipay");
        charge.setClientIp("127.0.0.1");
        charge.setAmount(1);
        String chargeString = chargeService.createCharge(charge).toString();
        System.out.println(chargeString);
    }

    @Test
    public void testRefund(){
        Refund refund = refundService.refund(1,"ch_1mvLOKjL4ubDD0COaDGm5WL0");
        System.out.println(refund.toString());
    }
}
