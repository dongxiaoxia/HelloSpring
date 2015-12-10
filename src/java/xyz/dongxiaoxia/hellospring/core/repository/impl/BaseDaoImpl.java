package xyz.dongxiaoxia.hellospring.core.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import xyz.dongxiaoxia.hellospring.core.entity.User;
import xyz.dongxiaoxia.hellospring.core.repository.BaseDao;
import xyz.dongxiaoxia.hellospring.util.ClassUtils;
import xyz.dongxiaoxia.hellospring.util.Paging;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2015/11/19.
 */
@Repository
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;
    private String tableName;
    /*通过@Resource注入jdbcTemplate对象，由于我仅定义了一个jdbcTemplate bean，可以这里可以省略掉name参数，及@Resource即可，或者使用@Autowired*/


    public BaseDaoImpl(String tableName) {
        this.tableName = tableName;
    }

    @Autowired
    private void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
//        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("system_user");
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource);
    }

    protected JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void cleanAll() {
        getJdbcTemplate().update("DELETE FROM " + tableName);
    }

    public int count() {
        return getJdbcTemplate().queryForObject("SELECT COUNT(*) FROM" + tableName, Integer.class);
    }


    @Override
    public int insert(T t) {
        return 0;
    }

    @Override
    public int delete(String id) {
        return 0;
    }

    @Override
    public int update(T t) {
        return 0;
    }

    @Override
    public T get(String id) {
        return null;
    }

    @Override
    public List list(T t) {
        return null;
    }

    @Override
    public Paging page(T t, int pageStart, int pageSize) {
        return null;
    }

    @Override
    public void $save(Object object) {
        getJdbcTemplate().update(ClassUtils.getInsertSql(object));
    }

    @Override
    public void $update(Object object) {
        getJdbcTemplate().update(ClassUtils.getUpdateSql(object));
    }

//    @Override
//    public void $get(String id, T t) {
//        getJdbcTemplate().get(ClassUtils.getUpdateSql(object));
//    }

    @Override
    public List<T> $query(T t, Class<T> clazz) {
        return getJdbcTemplate().query(ClassUtils.getQuerySql(t), new BaseRowMapper<>(clazz));
    }

    /**
     * 通用实体类删除方法
     *
     * @param id    主键
     * @param clazz 实体类
     */

    @Override
    public void $delete(String id, Class clazz) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("DELETE FROM ").append(ClassUtils.getTableName(clazz)).append(" WHERE ").append(ClassUtils.getIdentityName(clazz)).append(" =").append(id);
            getJdbcTemplate().update(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*public class BaseMapper<T> implements RowMapper {
        @Override
        public T mapRow(ResultSet rs, int rowNum) throws SQLException {
            try {

                Class clazz =  this.getClass();
                Object obj = clazz.newInstance();

                Field[] fields = clazz.getDeclaredFields();
                if (fields == null) {
                    return null;
                }
                for (int i = 0; i < fields.length; i++) {
                    String fieldName = fields[i].getName();
                    Class fieldClass = fields[i].getType();
                    String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    Method method = clazz.getMethod(methodName , new Class[]{fieldClass});
                    method.invoke(obj, rs.getObject(fieldName.toLowerCase()));
                }
                return (T) obj;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }*/
}
