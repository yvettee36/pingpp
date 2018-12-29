package com.pingpp;

import com.pingplusplus.model.*;
import com.pingpp.service.*;
import org.junit.jupiter.api.Test;

public class Junit {


    private ChargeService chargeService = new ChargeService();
    private RefundService refundService = new RefundService();
    private RetrieveService retrieveService = new RetrieveService();
    private TransferService transferService = new TransferService();
    private UserService userService = new UserService();
    private SettleAccountService settleAccountService = new SettleAccountService();
    private RechargeService rechargeService = new RechargeService();

    @Test
    public void testCharge() {
        Charge charge = new Charge();
        charge.setChannel("wx");
        charge.setClientIp("127.0.0.1");
        charge.setAmount(1);
        String chargeString = chargeService.createCharge(charge).toString();
        System.out.println(chargeString);
    }

    @Test
    public void testGetCharge() {
        Charge charge = retrieveService.getCharge("ch_90a5qPPWLOS4nrjbH4bTijDS");
        System.out.println(charge.toString());
    }

    @Test
    public void testRefund() {
        Refund refund = refundService.refund(1, "ch_88m98OD0uLK4Gy90q1004ubT");
        System.out.println(refund.toString());
    }

    @Test
    public void testTransfer() {
        Transfer transfer = transferService.createTransfer("alipay");
        System.out.println(transfer.toString());
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setId("yvettee_user1");
        user = userService.createUser(user);
        System.out.println(user.toString());
    }

    /**
     * 查询User对象
     */
    @Test
    public void testGetUser() {
        String id = "yvettee_user1";
        User user = userService.getUser(id);
        System.out.println(user.toString());
    }


    /**
     * 创建yvettee_user1的结算账户对象
     */
    @Test
    public void testCreateSettleAccount() {
        String channel = "alipay";
        SettleAccount settleAccount = settleAccountService.createSettleAccount(channel);
        System.out.println(settleAccount.toString());
    }

    /**
     * 查询yvettee_user1的结算账户对象
     */
    @Test
    public void testGetSettleAccount() {
        String id = "320218122610335300002901";
        SettleAccount settleAccount = settleAccountService.getSettleAccount(id);
        System.out.println(settleAccount.toString());
    }

    /**
     * 给yvettee_user1充值
     */
    @Test
    public void testRecharge() {
//        String user = "yvettee_user1";
//        Recharge recharge = rechargeService.createRecharge(user);
//        System.out.println(recharge.toString());
    }
}
