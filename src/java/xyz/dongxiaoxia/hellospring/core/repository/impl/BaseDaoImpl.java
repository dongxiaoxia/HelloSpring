package xyz.dongxiaoxia.hellospring.core.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import xyz.dongxiaoxia.hellospring.core.entity.User;
import xyz.dongxiaoxia.hellospring.core.repository.BaseDao;
import xyz.dongxiaoxia.hellospring.util.ClassUtils;
import xyz.dongxiaoxia.hellospring.util.Paging;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
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
    public Paging page(T t, int pageStart, int pageSize) {
        return null;
    }

    @Override
    public int $save(Object object) {
        final String sql = ClassUtils.getInsertSql(object);
        int result = 0;
        KeyHolder holder = new GeneratedKeyHolder();
        result = jdbcTemplate.update(new PreparedStatementCreator() {

            public PreparedStatement createPreparedStatement(Connection con)
                    throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                return ps;
            }
        }, holder);
        System.out.println(holder.getKey().intValue());
        return result;

    }

    @Override
    public void $update(Object object) {
        getJdbcTemplate().update(ClassUtils.getUpdateSql(object));
    }

    @Override
    public T $get(String id, Class<T> clazz) {
        List<T> list = getJdbcTemplate().query(ClassUtils.getSelectByIdentitySql(id, clazz), new BaseRowMapper<>(clazz));
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<T> $query(T t) {
        return getJdbcTemplate().query(ClassUtils.getQuerySql(t), new BaseRowMapper<>(t.getClass()));
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

}
