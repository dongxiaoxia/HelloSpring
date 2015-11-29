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
public class BaseDaoImpl {

    protected JdbcTemplate jdbcTemplate;
    protected SimpleJdbcInsert simpleJdbcInsert;
    @Autowired
    private void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
//        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("system_user");
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource);
    }


}
