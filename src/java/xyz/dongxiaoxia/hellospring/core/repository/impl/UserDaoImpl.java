package xyz.dongxiaoxia.hellospring.core.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import xyz.dongxiaoxia.hellospring.core.entity.Role;
import xyz.dongxiaoxia.hellospring.core.entity.User;
import xyz.dongxiaoxia.hellospring.core.repository.UserDao;
import xyz.dongxiaoxia.hellospring.util.Common;
import xyz.dongxiaoxia.hellospring.util.PageView;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by chenwendong on 2015/10/29.
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    private final String insetSql = "INSERT INTO SYSTEM_USER (username,password,nickname,realname,age,sex,email,regtime,lastlogintime,level,accountType,status) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

    private final String deleteSql = "DELETE FROM SYSTEM_USER WHERE id = ?";

    private final String updateSql = "UPDATE SYSTEM_USER  SET username = ?,password = ?,nickname = ?,realname = ?,age = ?,sex = ?,email = ?,regtime = ?,lastlogintime = ?,level = ?,accountType = ?,status = ? WHERE id = ?";

    private final String getSql = "SELECT * FROM SYSTEM_USER WHERE id = ?";

    @Override
    public int insert(User user) {
        return this.jdbcTemplate.update(insetSql, user.getUsername(), user.getPassword(), user.getNickname(), user.getRealname(), user.getAge(), user.getSex(), user.getEmail(), user.getRegTime(), user.getLastLoginTime(), user.getLevel(), user.getAccountType(), user.getStatus());
    }

    @Override
    public int delete(String id) {
        return this.jdbcTemplate.update(deleteSql, id);
    }

    @Override
    public int update(User user) {
        return this.jdbcTemplate.update(updateSql, user.getUsername(), user.getPassword(), user.getNickname(), user.getRealname(), user.getAge(), user.getSex(), user.getEmail(), user.getRegTime(), user.getLastLoginTime(), user.getLevel(), user.getAccountType(), user.getStatus(), user.getId());
    }

    @Override
    public User get(String id) {
        return this.jdbcTemplate.queryForObject(getSql, new UserMapper(), id);
    }

    @Override
    public List<User> list(User user) {
        String listSql = "SELECT * FROM SYSTEM_USER WHERE 1=1";
        if (user != null) {
            if (!Common.isEmpty(user.getUsername())) {
                listSql += " AND username = '" + user.getUsername() + "'";
            }
            if (!Common.isEmpty(user.getPassword())) {
                listSql += " AND password = '" + user.getPassword() + "'";
            }
            if (!Common.isEmpty(user.getNickname())) {
                listSql += " AND nickname = '" + user.getNickname() + "'";
            }
            if (!Common.isEmpty(user.getRealname())) {
                listSql += " AND realname = '" + user.getRealname() + "'";
            }
            if (user.getAge() != 0) {
                //TODO how to check the type of int
                listSql += " AND age = " + user.getAge();
            }
            if (!Common.isEmpty(user.getSex())) {
                listSql += " AND sex = '" + user.getSex() + "'";
            }
            if (!Common.isEmpty(user.getEmail())) {
                listSql += " AND email = '" + user.getEmail() + "'";
            }
            if (user.getRegTime() != null) {
                listSql += " AND regtime = '" + user.getRegTime() + "'";
            }
            if (user.getLastLoginTime() != null) {
                listSql += " AND lastlogintime = '" + user.getLastLoginTime() + "'";
            }
            if (user.getLevel() != 0) {
                listSql += " AND level = '" + user.getLevel() + "'";
            }
            if (!Common.isEmpty(user.getAccountType())) {
                listSql += " AND accountType = '" + user.getAccountType() + "'";
            }
            if (!Common.isEmpty(user.getStatus())) {
                listSql += " AND status = '" + user.getStatus() + "'";
            }
        }
        return this.jdbcTemplate.query(listSql, new UserMapper());
    }

    @Override
    public List<User> page(PageView pageView, User user) {
        String pageSql = "SELECT * FROM SYSTEM_USER WHERE 1=1";
        if (user != null) {
            if (!Common.isEmpty(user.getUsername())) {
                pageSql += " AND username = '" + user.getUsername() + "'";
            }
            if (!Common.isEmpty(user.getPassword())) {
                pageSql += " AND password = ," + user.getPassword() + "'";
            }
            if (!Common.isEmpty(user.getNickname())) {
                pageSql += " AND nickname = '" + user.getNickname() + "'";
            }
            if (!Common.isEmpty(user.getRealname())) {
                pageSql += " AND realname = '" + user.getRealname() + "'";
            }
            if (user.getAge() != 0) {
                //TODO how to check the type of int
                pageSql += " AND age = " + user.getAge();
            }
            if (!Common.isEmpty(user.getSex())) {
                pageSql += " AND sex = '" + user.getSex() + "'";
            }
            if (!Common.isEmpty(user.getEmail())) {
                pageSql += " AND email = '" + user.getEmail() + "'";
            }
            if (user.getRegTime() != null) {
                pageSql += " AND regtime = '" + user.getRegTime() + "'";
            }
            if (user.getLastLoginTime() != null) {
                pageSql += " AND lastlogintime = '" + user.getLastLoginTime() + "'";
            }
            if (user.getLevel() != 0) {
                pageSql += " AND level = '" + user.getLevel() + "'";
            }
            if (!Common.isEmpty(user.getAccountType())) {
                pageSql += " AND accountType = '" + user.getAccountType() + "'";
            }
            if (!Common.isEmpty(user.getStatus())) {
                pageSql += " AND status = '" + user.getStatus() + "'";
            }
        }
        pageSql += " limit " + pageView.getPageSize() * (pageView.getPageNow() - 1) + "," + pageView.getPageSize();
        return this.jdbcTemplate.query(pageSql, new UserMapper());
    }

    @Override
    public List<Role> findRoleByUserId(String userId) {
        return null;
    }

    private static final class UserMapper implements RowMapper<User> {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getString("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setNickname(rs.getString("nickname"));
            user.setRealname(rs.getString("realname"));
            user.setAge(rs.getInt("age"));
            user.setSex(rs.getString("sex"));
            user.setEmail(rs.getString("email"));
            user.setRegTime(rs.getTimestamp("regtime"));
            user.setLastLoginTime(rs.getTimestamp("lastlogintime"));
            user.setLevel(rs.getInt("level"));
            user.setAccountType(rs.getString("accountType"));
            user.setStatus(rs.getString("status"));
            return user;
        }
    }

}
