package xyz.dongxiaoxia.hellospring.core.repository.impl;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import xyz.dongxiaoxia.hellospring.core.entity.UserLoginList;
import xyz.dongxiaoxia.hellospring.core.repository.UserLoginListDao;
import xyz.dongxiaoxia.hellospring.util.Paging;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2015/11/18.
 */
@Repository
public class UserLoginListDaoImpl extends BaseDaoImpl implements UserLoginListDao {

    private static final String TABLE_NAME = "SYSTEM_USERLOGINLISTO";

    public UserLoginListDaoImpl() {
        super(TABLE_NAME);
    }


    public int insert(UserLoginList userLoginList) {
        return getJdbcTemplate().update("INSERT INTO SYSTEM_userloginlist (loginId,userId,loginTime,loginIP) VALUES (?,?,?,?)", userLoginList.getLoginId(), userLoginList.getUserId(), userLoginList.getLoginTime(), userLoginList.getLoginIp());
    }

    @Override
    public int delete(String id) {
        return getJdbcTemplate().update("DELETE FROM SYSTEM_userloginlist WHERE loginId = ?", id);
    }


    public int update(UserLoginList userLoginList) {
        return getJdbcTemplate().update("UPDATE SYSTEM_userloginlist SET userId = ?,loginTime = ?,loginIP = ? WHERE loginId = ? ", userLoginList.getUserId(), userLoginList.getLoginTime(), userLoginList.getLoginIp(), userLoginList.getLoginId());
    }

    @Override
    public UserLoginList get(String id) {
        return getJdbcTemplate().queryForObject("SELECT ul.*,u.username FROM system_userloginlist ul JOIN SYSTEM_USER u WHERE u.id = ul.userId AND ul.loginId = ?", new UserLoginListMapper(), id);
    }


    public List<UserLoginList> list(UserLoginList userLoginList) {
        return getJdbcTemplate().query("SELECT ul.*,u.username FROM system_userloginlist ul JOIN SYSTEM_USER u WHERE u.id = ul.userId", new UserLoginListMapper());
    }


    public Paging<UserLoginList> page(UserLoginList userLoginList, int pageStart, int pageSize) {
        return null;
    }

    private static final class UserLoginListMapper implements RowMapper<UserLoginList> {
        public UserLoginList mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserLoginList userLoginList = new UserLoginList();
            userLoginList.setLoginId(rs.getString("loginId"));
            userLoginList.setLoginIp(rs.getString("loginIP"));
            userLoginList.setLoginTime(rs.getDate("loginTime"));
            userLoginList.setUserName(rs.getString("username"));
            return userLoginList;
        }
    }
}
