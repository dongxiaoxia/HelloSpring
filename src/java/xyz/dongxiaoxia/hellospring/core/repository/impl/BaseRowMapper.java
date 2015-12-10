package xyz.dongxiaoxia.hellospring.core.repository.impl;

import org.springframework.jdbc.core.RowMapper;
import xyz.dongxiaoxia.hellospring.core.entity.User;
import xyz.dongxiaoxia.hellospring.logging.LoggerAdapter;
import xyz.dongxiaoxia.hellospring.logging.LoggerAdapterFactory;
import xyz.dongxiaoxia.hellospring.util.StringUtils;
import xyz.dongxiaoxia.hellospring.util.annotation.Column;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 2015/12/10.
 */
public class BaseRowMapper<T> implements RowMapper {
    private LoggerAdapter logger = LoggerAdapterFactory.getLoggerAdapter(this.getClass());
    private Class<T> cls;

    public BaseRowMapper(Class<T> cls) {
        this.cls = cls;
    }

    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        T t = null;
        try {
            // 获取对象中的所有字段
            Field[] fields = cls.getDeclaredFields();
            // 实例化
            t = cls.newInstance();
            for (Field f : fields) {
                if (!f.isAnnotationPresent(Column.class)) {
                    continue;
                }
                // 获取字段名称
                String fieldName = f.getName();
                if (StringUtils.isEmpty(fieldName)) {
                    continue;
                }
                Object o = null;
                // 通过字段名称获取该字段的值（实体字段名称必须与数据库字段名称一致才可以）
                o = resultSet.getObject(fieldName);
                if (o == null) {
                    continue;
                }
                Class fieldClass = f.getType();
                String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                Method method = cls.getMethod(methodName, new Class[]{fieldClass});
                Object fieldVale = resultSet.getObject(fieldName.toLowerCase());
                if (fieldVale != null) {
                    if (fieldClass == String.class) {
                        method.invoke(t, fieldVale.toString());
                    } else {
                        method.invoke(t, fieldVale);
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return t;
    }
}
