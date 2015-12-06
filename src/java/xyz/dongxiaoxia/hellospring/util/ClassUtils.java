package xyz.dongxiaoxia.hellospring.util;

import xyz.dongxiaoxia.hellospring.util.annotation.Entity;

/**
 * Created by dongxiaoxia on 2015/12/6.
 * <p/>
 * 与类、反射的相关操作类
 */
public class ClassUtils {
    /**
     * 根据实体类获取表名
     *
     * @param clazz
     * @return
     */
    public static String getTableName(Class<?> clazz) {
        // Class<?> cls = Class.forName("xyz.dongxiaoxia.hellospring.core.entity.User"); //或直接XXXX.class
        //Class<?> clazz = User.class;
        return clazz.getAnnotation(Entity.class).name();
    }
}
