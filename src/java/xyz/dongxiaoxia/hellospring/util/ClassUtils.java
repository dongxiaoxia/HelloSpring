package xyz.dongxiaoxia.hellospring.util;

import xyz.dongxiaoxia.hellospring.util.annotation.Column;
import xyz.dongxiaoxia.hellospring.util.annotation.Entity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

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
        return clazz.getAnnotation(Entity.class).value();
    }

    /**
     * @param object 实体类对西象
     * @return String 返回的是拼装好的sql查询语句
     */
    public static String executeQuery(Object object) {
        Class<?> clazz = object.getClass();
        StringBuffer sb = new StringBuffer("SELECT * FROM ");
        //查找类是否被注解
        boolean isExist = clazz.isAnnotationPresent(Entity.class);
        if (!isExist) {
            try {
                throw new Exception("the " + clazz.getClass().getClass() + " class is not used Entity annotation");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //获取Entity注解
        Entity entity = clazz.getAnnotation(Entity.class);
        sb.append(entity.value()).append(" WHERE 1=1");
        //查找属性是否被注解
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            //处理每个字段对应的sql
            //拿到字段值
            boolean isFieldExist = field.isAnnotationPresent(Column.class);
            if (!isFieldExist) {
                try {
                    throw new Exception("the " + field.getName() + " field is not used Column annotation");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //获取属性注解
            Column column = field.getAnnotation(Column.class);
            String columnName = column.value();
            //获取字段
            String fieldName = field.getName();
            //获取字段值
            Object fieldValue = null;
            String getFieldMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            try {
                Method getMethod = clazz.getDeclaredMethod(getFieldMethodName);
                fieldValue = getMethod.invoke(object);
                //拼装sql
                if (fieldValue == null || (fieldValue instanceof Integer && (Integer) fieldValue == 0)) {
                    continue;
                }
                sb.append(" AND ").append(columnName);
                if (fieldValue instanceof Integer) {
                    sb.append("=").append(fieldValue);
                } else if (fieldValue instanceof String) {
                    if (((String) fieldValue).contains(",")) {
                        String[] fieldValueIn = ((String) fieldValue).split(",");
                        sb.append(" IN(");
                        for (String s : fieldValueIn) {
                            sb.append("'").append(s).append("'").append(",");
                        }
                        sb.deleteCharAt(sb.length() - 1);
                        sb.append(")");
                    } else {
                        sb.append("='").append(fieldValue).append("'");
                    }
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        //返回拼装好的sql语句
        return sb.toString();
    }
}
