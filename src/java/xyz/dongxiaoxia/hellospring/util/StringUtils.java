package xyz.dongxiaoxia.hellospring.util;

/**
 * Created by Administrator on 2015/11/8.
 */
public class StringUtils {
    public static boolean isEmpty(String str) {
        return str == null || str.equals("");
    }

    public static boolean isNotEmpty(String str) {
        return str != null && !str.equals("");
    }
}
