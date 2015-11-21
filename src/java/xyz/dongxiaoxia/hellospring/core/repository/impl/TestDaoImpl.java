package xyz.dongxiaoxia.hellospring.core.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import xyz.dongxiaoxia.hellospring.core.repository.BaseDao;
import xyz.dongxiaoxia.hellospring.util.PageView;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/21.
 */
@Repository
public class TestDaoImpl implements BaseDao<Object> {

    protected JdbcTemplate jdbcTemplate;

    @Autowired
    private void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int insert(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        List<Map> list = new ArrayList();
        Map infoMap = null;
        for (int i = 0; i < fields.length; i++) {
            infoMap = new HashMap();
            infoMap.put("type", fields[i].getType().toString());
            infoMap.put("name", fields[i].getName());
            infoMap.put("value", new TestDaoImpl().getFieldValueByName(fields[i].getName(), o));
            list.add(infoMap);
        }
        //System.out.println(list);
        String sql = "insert into system_" + o.getClass().getSimpleName() + "(";
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                sql += list.get(i).get("name");
            } else {
                sql += "," + list.get(i).get("name");
            }
        }
        sql += ") values (";
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                sql += list.get(i).get("value");
            } else {
                sql += "," + list.get(i).get("value");
            }
        }
        sql += ")";
        System.out.println(sql);

        return this.jdbcTemplate.update(sql);
    }


    /**
     * 删除
     */
    @Override
    public int delete(String id) {
        String sql = "delete from system_" + this.getClass().getSimpleName() + " where id = '" + id + "'";
        System.out.println(sql);
        return this.jdbcTemplate.update(sql);
    }

    /**
     * 修改
     */
    @Override
    public int update(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        List<Map> list = new ArrayList();
        Map infoMap = null;
        for (int i = 0; i < fields.length; i++) {
            infoMap = new HashMap();
            infoMap.put("type", fields[i].getType().toString());
            infoMap.put("name", fields[i].getName());
            infoMap.put("value", new TestDaoImpl().getFieldValueByName(fields[i].getName(), o));
            list.add(infoMap);
        }
        //System.out.println(list);
        String sql = "update system_" + o.getClass().getSimpleName() + " set";
        for (Map map : list) {
            if (map.get("name") != "id") {
                sql += " " + map.get("name") + " = '" + map.get("value") + "',";
            }
        }
        sql += "where id = '" + getFieldValueByName("id", o) + "'";
        System.out.println(sql);
        return this.jdbcTemplate.update(sql);
    }

    /**
     * 获取
     */
    @Override
    public Object get(String id) {
        String sql = "select * from system_" + this.getClass().getSimpleName() + " where id = '" + id + "'";
        System.out.println(sql);
        return null;
    }

    @Override
    public List<Object> list(Object o) {
        return null;
    }

    @Override
    public List<Object> page(PageView pageView, Object o) {
        return null;
    }


    /**
     * 根据属性名获取属性值
     */
    private Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[]{});
            Object value = method.invoke(o, new Object[]{});
            return value;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * 获取属性名数组
     */
    private String[] getFiledName(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            System.out.println(fields[i].getType());
            fieldNames[i] = fields[i].getName();
        }
        return fieldNames;
    }

    /**
     * 获取属性类型(type)，属性名(name)，属性值(value)的map组成的list
     */
    private List getFiledsInfo(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        List list = new ArrayList();
        Map infoMap = null;
        for (int i = 0; i < fields.length; i++) {
            infoMap = new HashMap();
            infoMap.put("type", fields[i].getType().toString());
            infoMap.put("name", fields[i].getName());
            infoMap.put("value", getFieldValueByName(fields[i].getName(), o));
            list.add(infoMap);
        }
        return list;
    }

    /**
     * 获取对象的所有属性值，返回一个对象数组
     */
    public Object[] getFiledValues(Object o) {
        String[] fieldNames = this.getFiledName(o);
        Object[] value = new Object[fieldNames.length];
        for (int i = 0; i < fieldNames.length; i++) {
            value[i] = this.getFieldValueByName(fieldNames[i], o);
        }
        return value;
    }

}
