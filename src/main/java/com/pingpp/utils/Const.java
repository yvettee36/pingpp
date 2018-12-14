package com.pingpp.utils;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.InputStream;

public class Const {
    public static String APP_ID = "*****";
//    public static String APP_KEY = "******";
    public static String APP_KEY = "*******";
    public static String APP_PRIVATE_KEY = null;
    public static String PINGPP_PUBLIC__KEY = null;

    static {
        try {
            Resource resource = new ClassPathResource("private.pem");
            InputStream in = resource.getInputStream();
            APP_PRIVATE_KEY = IOUtils.toString(in);
            Resource resource1 = new ClassPathResource("public.pem");
            InputStream in1 = resource1.getInputStream();
            PINGPP_PUBLIC__KEY = IOUtils.toString(in1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
