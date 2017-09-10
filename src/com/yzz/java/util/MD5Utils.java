package com.yzz.java.util;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by yzz on 2017/9/7.
 * mail:yzzstyle@163.com
 */

public class MD5Utils {
    public static String encode(String str){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            BASE64Encoder base64Encoder = new BASE64Encoder();
            return base64Encoder.encode(messageDigest.digest(str.getBytes("utf-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
