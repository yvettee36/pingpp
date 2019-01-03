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
    private BalanceService balanceService = new BalanceService();
    private OrderService orderService = new OrderService();

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

    /**
     * 退款测试
     */
    @Test
    public void testRefund() {
        Refund refund = refundService.refund(1, "ch_yrPuzDLCOenD5KeHCKrTqjnD");
        System.out.println(refund.toString());
    }

    @Test
    public void testTransfer() {
        Transfer transfer = transferService.createTransfer("balance");
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
        Recharge recharge = new Recharge();
        recharge.setUser("0");
        recharge = balanceService.createRecharge(recharge);
        System.out.println(recharge.toString());
    }


    /**
     * 创建order
     */
    @Test
    public void testCreateOrder() {
        Order order = new Order();
        order.setAmount(1);
        order.setClientIp("127.0.0.1");
        Order recharge = orderService.createOrder(order);
        System.out.println(recharge.toString());
    }


    /**
     * 查询order
     */
    @Test
    public void testGetOrder() {
        String id = "";
        Order order = orderService.testGetOrder(id);
        System.out.println(order.toString());
    }

    /**
     * 查询withdrawal
     */
    @Test
    public void testGetWithdrawal() {
        String id = "1711901021758167943";
        Withdrawal withdrawal = null;
//        withdrawal = balanceService.getWithdrawal(id);
        withdrawal = balanceService.pendingWithdrawal(id);
        System.out.println(withdrawal.toString());
    }
}
