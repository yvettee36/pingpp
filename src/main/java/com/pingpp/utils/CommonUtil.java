package com.pingpp.utils;

import java.math.BigInteger;
import java.security.SecureRandom;

public class CommonUtil {
    private static SecureRandom random = new SecureRandom();

    public static String randomString(int length) {
        String str = new BigInteger(130, random).toString(32);
        return str.substring(0, length);
    }

    public static int currentTimeSeconds() {
        return (int) (System.currentTimeMillis() / 1000);
    }
}
