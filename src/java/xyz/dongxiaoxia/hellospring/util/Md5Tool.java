package xyz.dongxiaoxia.hellospring.util;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;

/**
 * Created by Administrator on 2015/11/20.
 */
public class Md5Tool {
    public static String getMd5(String password) {
        String str = "";
        if (password != null && !password.equals("")) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                BASE64Encoder base = new BASE64Encoder();
                //加密后的字符串
                str = base.encode(md.digest(password.getBytes("utf-8")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return str;
    }

}
