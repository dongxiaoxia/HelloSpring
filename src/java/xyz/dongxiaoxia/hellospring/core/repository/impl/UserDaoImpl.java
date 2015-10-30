package xyz.dongxiaoxia.hellospring.core.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import xyz.dongxiaoxia.hellospring.core.entity.User;
import xyz.dongxiaoxia.hellospring.core.repository.UserDao;

import javax.sql.DataSource;

/**
 * Created by chenwendong on 2015/10/29.
 */
@Repository
public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int getCount() {
        return this.jdbcTemplate.queryForObject("select count(*) from system_user", Integer.class);
    }

    @Override
    public User get(String id) {
        if (id.equals("123")) {
            User user = new User();
            user.setId("123");
            user.setName("东小侠");
            user.setPassword("123456");
            return user;
        }
        return null;
    }
}
