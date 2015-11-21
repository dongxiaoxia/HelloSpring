package xyz.dongxiaoxia.hellospring.core.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import xyz.dongxiaoxia.hellospring.core.repository.BaseDao;
import xyz.dongxiaoxia.hellospring.util.PageView;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Administrator on 2015/11/19.
 */
@Repository
public class BaseDaoImpl {

    protected JdbcTemplate jdbcTemplate;

    @Autowired
    private void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
