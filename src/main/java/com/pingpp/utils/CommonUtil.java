package com.pingpp.utils;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {
    private static SecureRandom random = new SecureRandom();

    public static String randomString(int length) {
        String str = new BigInteger(130, random).toString(32);
        return str.substring(0, length);
    }

    public static int currentTimeSeconds() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    /**
     * 设置订单失效时间，并返回具体失效的时间点
     *
     * @param expire 有效时间，单位是秒
     * @return
     */
    public static Long getOrderExpireTime(Long expire) {
        Date now = new Date();
        Long afterDateTime = (now.getTime() + expire) / 1000;
        return afterDateTime;
    }

}
