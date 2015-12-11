package xyz.dongxiaoxia.hellospring.core.repository.persistence;

import xyz.dongxiaoxia.hellospring.logging.LoggerAdapter;
import xyz.dongxiaoxia.hellospring.logging.LoggerAdapterFactory;
import xyz.dongxiaoxia.hellospring.util.annotation.Column;
import xyz.dongxiaoxia.hellospring.util.annotation.Entity;
import xyz.dongxiaoxia.hellospring.util.annotation.Id;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by dongxiaoxia on 2015/12/12.
 * <p/>
 * 持久层sql语句组装类
 */
public class Finder {

    private static final LoggerAdapter logger = LoggerAdapterFactory.getLoggerAdapter(Finder.class);

    /**
     * 判断一个类是否被Entity注解类所注解，也就是判断一个类是否为实体类
     *
     * @param clazz
     * @return
     * @see Entity
     */
    public static boolean isEntityAnnotationPresent(Class clazz) {
        return clazz.isAnnotationPresent(Entity.class);
    }


    /**
     * 根据实体类获取表名
     *
     * @param clazz 实体类类型
     * @return
     */
    public static String getTableName(Class<?> clazz) {
        // Class<?> cls = Class.forName("xyz.dongxiaoxia.hellospring.core.entity.User"); //或直接XXXX.class
        if (!isEntityAnnotationPresent(clazz)) {
            throw new PersistenceException("the " + clazz.getClass() + " is not used Entity annotation");
        }
        String tableName = clazz.getAnnotation(Entity.class).value();
        return "".equals(tableName) ? clazz.getSimpleName().toUpperCase() : tableName;
    }

    /**
     * 根据实体类属性获取列名
     *
     * @param field 实体类属性
     * @return
     */
    public static String getColumnName(Field field) {
        if (!field.isAnnotationPresent(Column.class)) {
            throw new PersistenceException("the " + field.getName() + " is not used Column annotation");
        }
        String columnName = field.getAnnotation(Column.class).value();
        return "".equals(columnName) ? field.getName() : columnName;
    }

    /**
     * 根据实体类获取主键名称
     *
     * @param clazz
     * @return 主键名称
     */
    public static String getIdentityName(Class clazz) {
        String identityName = null;
        //查找类是否被注解
        boolean isExist = clazz.isAnnotationPresent(Entity.class);
        if (!isExist) {
            throw new PersistenceException("the " + clazz.getClass() + " is not used Entity annotation");
        }
        //查找属性是否被注解
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            boolean isExistIdAnnotation = field.isAnnotationPresent(Id.class);
            if (!isExistIdAnnotation) {
                continue;
            }
            identityName = getColumnName(field);
            break;
        }
        if (null == identityName) {
            throw new PersistenceException("the " + clazz.getName() + " is not used Id annotation");
        }
        return identityName;
    }

    /**
     * 根据实体对象获取主键值
     *
     * @param object 实体类对象
     * @return 主键值
     */
    public static String getIdentityValue(Object object) {
        if (object == null) {
            return null;
        }
        Class<?> clazz = object.getClass();
        String identityName = getIdentityName(clazz);
        String getIdentityMethodName = "get" + identityName.substring(0, 1).toUpperCase() + identityName.substring(1);
        String identityValue = null;
        try {
            Method getMethod = clazz.getDeclaredMethod(getIdentityMethodName);
            identityValue = (String) getMethod.invoke(object);
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            logger.error(e.getMessage(), e);
        } catch (NoSuchMethodException e) {
            logger.error(e.getMessage(), e);
        }
        return identityValue;
    }

    /**
     * 获取insert sql语句
     *
     * @param object 实体类对象
     * @return
     */
    public static String getInsertSql(Object object) {
        Class<?> clazz = object.getClass();
        StringBuilder sqlSb = new StringBuilder("INSERT INTO ");
        sqlSb.append(getTableName(clazz)).append(" (");
        //查找属性是否被注解
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder nameStr = new StringBuilder();
        StringBuilder valueStr = new StringBuilder();
        for (Field field : fields) {
            //处理每个字段对应的sql
            //拿到字段值
            boolean isFieldExist = field.isAnnotationPresent(Column.class);
            if (!isFieldExist) {
                continue;
            }
            if (field.isAnnotationPresent(Id.class)) {
                continue;
            }
            nameStr.append(getColumnName(field)).append(",");
            //获取字段
            String fieldName = field.getName();
            //获取属性类型
            Class fieldClass = field.getType();
            //getter
            String getFieldMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            try {
                Method getMethod = clazz.getDeclaredMethod(getFieldMethodName);
                //获取字段值
                Object fieldValue = getMethod.invoke(object);
                if (fieldValue == null) {
                    valueStr.append("null").append(",");
                } else if (fieldClass == String.class) {
                    valueStr.append("'").append(fieldValue).append("',");
                } else if (fieldClass == int.class) {
                    valueStr.append(fieldValue).append(",");
                } else {
                    valueStr.append("'").append(fieldValue).append("',");
                }
            } catch (NoSuchMethodException e) {
                logger.error(e.getMessage(), e);
            } catch (InvocationTargetException e) {
                logger.error(e.getMessage(), e);
            } catch (IllegalAccessException e) {
                logger.error(e.getMessage(), e);
            }
        }
        sqlSb.append(nameStr.substring(0, nameStr.length() - 1)).append(") VALUES (").append(valueStr.substring(0, valueStr.length() - 1)).append(")");
        //返回拼装好的sql语句
        return sqlSb.toString();
    }

    /**
     * 根据对象主键与实体类类型获取delete sql语句
     *
     * @param id    主键
     * @param clazz 实体类类型
     * @return
     */
    public static String getDeleteSql(String id, Class<?> clazz) {
        return new StringBuilder().append("DELETE FROM ").append(getTableName(clazz)).append(" WHERE ").append(getIdentityName(clazz)).append(" =").append(id).toString();
    }

    /**
     * 根据实体类对象获取update sql语句
     *
     * @param object 实体类对象
     * @return
     */
    public static String getUpdateSql(Object object) {
        Class<?> clazz = object.getClass();
        StringBuilder sqlSb = new StringBuilder("UPDATE ").append(getTableName(clazz)).append(" SET ");
        //查找属性是否被注解
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder nameAndValueStr = new StringBuilder();
        for (Field field : fields) {
            //处理每个字段对应的sql
            //拿到字段值
            boolean isFieldExist = field.isAnnotationPresent(Column.class);
            if (!isFieldExist) {
                continue;
            }
            if (field.isAnnotationPresent(Id.class)) {
                continue;
            }
            //获取字段
            String fieldName = field.getName();
            //获取属性类型
            Class fieldClass = field.getType();

            String getFieldMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            try {
                Method getMethod = clazz.getDeclaredMethod(getFieldMethodName);
                //获取字段值
                Object fieldValue = getMethod.invoke(object);
                if (fieldValue == null) {
                    continue;
                } else if (fieldClass == String.class) {
                    nameAndValueStr.append(getColumnName(field)).append(" = '").append(fieldValue).append("',");
                } else if (fieldClass == int.class) {
                    nameAndValueStr.append(getColumnName(field)).append(" = ").append(fieldValue).append(",");
                } else {
                    nameAndValueStr.append(getColumnName(field)).append(" = '").append(fieldValue).append("',");
                }

            } catch (NoSuchMethodException e) {
                logger.error(e.getMessage(), e);
            } catch (InvocationTargetException e) {
                logger.error(e.getMessage(), e);
            } catch (IllegalAccessException e) {
                logger.error(e.getMessage(), e);
            }
        }
        sqlSb.append(nameAndValueStr.substring(0, nameAndValueStr.length() - 1)).append(" WHERE ").append(getIdentityName(clazz)).append(" = ").append(getIdentityValue(object));
        //返回拼装好的sql语句
        return sqlSb.toString();
    }

    /**
     * 获取根据主键查找sql语句
     *
     * @param id    主键
     * @param clazz 实体类
     * @return
     */
    public static String getSelectByIdentitySql(String id, Class<?> clazz) {
        return new StringBuilder("SELECT * FROM ").append(getTableName(clazz)).append(" WHERE ").append(getIdentityName(clazz)).append(" =").append(id).toString();
    }

    /**
     * 根据实体类对象获取query sql语句
     *
     * @param object 实体类对象
     * @return
     */
    public static String getQuerySql(Object object) {
        Class<?> clazz = object.getClass();
        StringBuilder sqlSb = new StringBuilder("SELECT * FROM ").append(getTableName(clazz)).append("WHERE 1=1");
        //查找属性是否被注解
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            boolean isFieldExist = field.isAnnotationPresent(Column.class);
            if (!isFieldExist) {
                continue;
            }
            //获取字段
            String fieldName = field.getName();
            //获取属性类型
            Class fieldClass = field.getType();
            String getFieldMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            try {
                Method getMethod = clazz.getDeclaredMethod(getFieldMethodName);
                //获取字段值
                Object fieldValue = getMethod.invoke(object);
                if (fieldValue == null || (fieldValue instanceof Integer && (Integer) fieldValue == 0)) {
                    continue;
                }
                sqlSb.append(" AND ").append(getColumnName(field));
                if (fieldValue instanceof Integer) {
                    sqlSb.append("=").append(fieldValue);
                } else if (fieldValue instanceof String) {
                    if (((String) fieldValue).contains(",")) {
                        String[] fieldValueIn = ((String) fieldValue).split(",");
                        sqlSb.append(" IN(");
                        for (String s : fieldValueIn) {
                            sqlSb.append("'").append(s).append("'").append(",");
                        }
                        sqlSb.deleteCharAt(sqlSb.length() - 1);
                        sqlSb.append(")");
                    } else {
                        sqlSb.append("='").append(fieldValue).append("'");
                    }
                } else if (fieldClass == int.class) {
                    sqlSb.append(" = ").append(fieldValue);
                } else {
                    sqlSb.append(" = '").append(fieldValue).append("'");
                }
            } catch (NoSuchMethodException e) {
                logger.error(e.getMessage(), e);
            } catch (InvocationTargetException e) {
                logger.error(e.getMessage(), e);
            } catch (IllegalAccessException e) {
                logger.error(e.getMessage(), e);
            }
        }
        //返回拼装好的sql语句
        return sqlSb.toString();
    }
}
