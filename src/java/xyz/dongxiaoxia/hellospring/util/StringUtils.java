package xyz.dongxiaoxia.hellospring.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

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

    //    public static String join(List list, String separator) {
//        String str = "";
//        if (list != null && list.size() > 0) {
//            for (int i = 0; i < list.size(); i++) {
//                if (i == 0) {
//                    str += list.get(i);
//                } else {
//                    str += separator + list.get(i);
//                }
//            }
//        }
//        return str;
//    }
    public static String join(Collection var0, String var1) {
        StringBuffer var2 = new StringBuffer();

        for (Iterator var3 = var0.iterator(); var3.hasNext(); var2.append((String) var3.next())) {
            if (var2.length() != 0) {
                var2.append(var1);
            }
        }

        return var2.toString();
    }
}
