package com.pingpp.utils;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.InputStream;

public class Const {
    public static String APP_ID = "app_CSqrT0jPKabD1qjz";
//        public static String APP_KEY = "sk_test_SGqT049aXvb1vj9Gi9OCS44G";
    public static String APP_KEY = "sk_live_q544K4rLePCSOWbTeHn1GujD";
    public static String APP_PRIVATE_KEY = null;
    public static String PINGPP_PUBLIC__KEY = null;
    public static String SIGN_PATH = null;
    public static String EVENT_PATH = null;

    static {
        try {
            Resource resource = new ClassPathResource("private.pem");
            InputStream in = resource.getInputStream();
            APP_PRIVATE_KEY = IOUtils.toString(in);

            Resource resource1 = new ClassPathResource("public.pem");
            InputStream in1 = resource1.getInputStream();
            PINGPP_PUBLIC__KEY = IOUtils.toString(in1);

            Resource resource2 = new ClassPathResource("signature.txt");
            InputStream in2 = resource2.getInputStream();
            SIGN_PATH = IOUtils.toString(in2);

            Resource resource3 = new ClassPathResource("webhooksData.json");
            InputStream in3 = resource3.getInputStream();
            EVENT_PATH = IOUtils.toString(in3);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
