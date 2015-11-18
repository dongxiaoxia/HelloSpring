package xyz.dongxiaoxia.hellospring.core.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import xyz.dongxiaoxia.hellospring.core.entity.UserLoginList;
import xyz.dongxiaoxia.hellospring.core.repository.UserLoginListDao;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2015/11/18.
 */
@Repository
public class UserLoginListDaoImpl implements UserLoginListDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int insert(UserLoginList userLoginList) {
        return this.jdbcTemplate.update("INSERT INTO SYSTEM_userloginlist (loginId,userId,loginTime,loginIP) VALUES (?,?,?,?)", userLoginList.getLoginId(), userLoginList.getUserId(), userLoginList.getLoginTime(), userLoginList.getLoginIp());
    }

    @Override
    public int delete(String id) {
        return this.jdbcTemplate.update("DELETE FROM SYSTEM_userloginlist WHERE loginId = ?", id);
    }

    @Override
    public int update(UserLoginList userLoginList) {
        return this.jdbcTemplate.update("UPDATE SYSTEM_userloginlist SET userId = ?,loginTime = ?,loginIP = ? WHERE loginId = ? ", userLoginList.getUserId(), userLoginList.getLoginTime(), userLoginList.getLoginIp(), userLoginList.getLoginId());
    }

    @Override
    public UserLoginList get(String id) {
        return this.jdbcTemplate.queryForObject("SELECT ul.*,u.username FROM system_userloginlist ul JOIN SYSTEM_USER u WHERE u.id = ul.userId AND ul.loginId = ?", new UserLoginListMapper(), id);
    }

    @Override
    public List<UserLoginList> list() {
        return this.jdbcTemplate.query("SELECT ul.*,u.username FROM system_userloginlist ul JOIN SYSTEM_USER u WHERE u.id = ul.userId", new UserLoginListMapper());
    }

    private static final class UserLoginListMapper implements RowMapper<UserLoginList> {
        public UserLoginList mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserLoginList userLoginList = new UserLoginList();
            userLoginList.setLoginId(rs.getInt("loginId"));
            userLoginList.setLoginIp(rs.getString("loginIP"));
            userLoginList.setLoginTime(rs.getDate("loginTime"));
            userLoginList.setUserName(rs.getString("username"));
            return userLoginList;
        }
    }
}
