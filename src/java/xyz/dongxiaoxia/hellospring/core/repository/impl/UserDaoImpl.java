package xyz.dongxiaoxia.hellospring.core.repository.impl;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Repository;
import xyz.dongxiaoxia.hellospring.core.entity.Role;
import xyz.dongxiaoxia.hellospring.core.entity.User;
import xyz.dongxiaoxia.hellospring.core.repository.UserDao;
import xyz.dongxiaoxia.hellospring.util.Common;
import xyz.dongxiaoxia.hellospring.util.Paging;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        /*直接使用jdbcTemplate*/
//        return this.jdbcTemplate.update(insetSql, user.getUsername(), user.getPassword(), user.getNickname(), user.getRealname(), user.getAge(), user.getSex(), user.getEmail(), user.getRegTime(), user.getLastLoginTime(), user.getLevel(), user.getAccountType(), user.getStatus());
         /*使用simpleJdbcInsert*/
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("username", user.getUsername());
//        parameters.put("password", user.getPassword());
//        parameters.put("nickname", user.getNickname());
//        parameters.put("realname", user.getRealname());
//        parameters.put("age",user.getAge());
//        parameters.put("sex",user.getSex());
//        parameters.put("email", user.getEmail());
//        parameters.put("regtime",user.getRegTime() == null?new Date():user.getRegTime());
//        parameters.put("lastLogintime",user.getLastLoginTime() == null?new Date():user.getLastLoginTime());
//        parameters.put("level",user.getLevel());
//        parameters.put("accountType",user.getAccountType());
//        parameters.put("status", user.getStatus());
        //simpleJdbcInsert.withTableName("system_user").usingGeneratedKeyColumns("id").execute(parameters);
        //simpleJdbcInsert.execute(parameters);
//        Number id = simpleJdbcInsert.withTableName("system_user").usingGeneratedKeyColumns("id").executeAndReturnKey(parameters);
         /*使用SqlParameterSource*/
        // SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("username", user.getUsername())
                .addValue("password", user.getPassword());
        Number id = simpleJdbcInsert.withTableName("system_user").usingGeneratedKeyColumns("id").executeAndReturnKey(parameterSource);
//        user.setId(id.toString());
        return 1;
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
        List<User> userList = this.jdbcTemplate.query(getSql, new UserMapper(), id);
        if (userList == null || userList.size() == 0) {
            return null;
        } else {
            return userList.get(0);
        }
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
    public Paging<User> page(User user, int pageStart, int pageSize) {
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
        // pageSql += " limit " + pageView.getPageSize() * (pageView.getPageNow() - 1) + "," + pageView.getPageSize();
        List<User> userList = this.jdbcTemplate.query(pageSql, new UserMapper());
//        long pageCount = this.jdbcTemplate.queryForObject("select count(*) from system_user", Long.class);
//        pageView.setRecords(userList);
//        pageView.setPageCount(pageCount);
        return null;
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
