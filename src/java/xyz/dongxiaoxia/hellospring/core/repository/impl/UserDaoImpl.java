package xyz.dongxiaoxia.hellospring.core.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import xyz.dongxiaoxia.hellospring.core.entity.Role;
import xyz.dongxiaoxia.hellospring.core.entity.User;
import xyz.dongxiaoxia.hellospring.core.repository.UserDao;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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


    @Override
    public int insert(User user) {
        return this.jdbcTemplate.update("INSERT INTO SYSTEM_USER (username,password) VALUES (?,?)", user.getName(), user.getPassword());
    }

    @Override
    public int delete(String id) {
        return this.jdbcTemplate.update("DELETE FROM SYSTEM_USER WHERE id = ?", id);
    }

    @Override
    public int update(User user) {
        return this.jdbcTemplate.update("UPDATE SYSTEM_USER  SET username = ?,password = ? WHERE id = ?", user.getName(), user.getPassword(), user.getId());
    }

    @Override
    public User get(String id) {
        return this.jdbcTemplate.queryForObject("SELECT * FROM SYSTEM_USER WHERE id = ?", new UserMapper(), id);
    }

    @Override
    public User findByUsername(String username) {
        List<User> userList = this.jdbcTemplate.query("SELECT * FROM SYSTEM_USER WHERE username = ?", new UserMapper(), username);
        if (userList != null && userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }

    }

    @Override
    public int countUser(String username, String password) {
        User user = this.jdbcTemplate.queryForObject("SELECT * FROM SYSTEM_USER WHERE username = ? and password = ?", new UserMapper(), username, password);
        if (user == null) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public List<User> list() {
        return this.jdbcTemplate.query("SELECT * FROM SYSTEM_USER", new UserMapper());
    }

    private static final class UserMapper implements RowMapper<User> {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            return user;
        }
    }

}
