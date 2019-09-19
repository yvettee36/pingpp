package com.pingpp.utils;

import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

    /**
     * 图片转成 Base64
     * @param imgFile
     * @return
     */
    public static String getImageStr(String imgFile) {
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(imgFile);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 加密
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

}
