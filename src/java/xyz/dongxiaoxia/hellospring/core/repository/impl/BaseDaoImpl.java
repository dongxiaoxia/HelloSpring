package xyz.dongxiaoxia.hellospring.core.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

/**
 * Created by Administrator on 2015/11/19.
 */
@Repository
public abstract class BaseDaoImpl {

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
    /**
     * 通用实体类删除方法
     * @param id 主键
     * @param object 实体类对象
     * /
    public void $delete(String id,Object object){
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ").append(ClassUtils.getTableName(object.getClass())).append(" WHERE ").append(CLassUtils.getIdentityName(object.getClass())).append(" =").append(id);
    }
}
