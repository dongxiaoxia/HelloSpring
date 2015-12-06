package xyz.dongxiaoxia.hellospring.core.repository.impl;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import xyz.dongxiaoxia.hellospring.core.entity.Role;
import xyz.dongxiaoxia.hellospring.core.entity.User;
import xyz.dongxiaoxia.hellospring.core.repository.UserDao;
import xyz.dongxiaoxia.hellospring.logging.LoggerAdapter;
import xyz.dongxiaoxia.hellospring.logging.LoggerAdapterFactory;
import xyz.dongxiaoxia.hellospring.util.ClassUtils;
import xyz.dongxiaoxia.hellospring.util.Common;
import xyz.dongxiaoxia.hellospring.util.Paging;
import xyz.dongxiaoxia.hellospring.util.StringUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenwendong on 2015/10/29.
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    //private static final String TABLE_NAME = "SYSTEM_USER";
    private static final String TABLE_NAME = ClassUtils.getTableName(User.class);
    private static final String COLUME_NAMES = "username,password,nickname,realname,age,sex,email,regtime,lastlogintime,level,accountType,status";
    private static final String SQL_INSERT_DATA = "INSERT INTO  " + TABLE_NAME + " (" + COLUME_NAMES + " ) " + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_DELETE_DATE = "DELETE FROM " + TABLE_NAME + " WHERE id IN  (%s)";
    private static final String SQL_UPDATE_STATUS = "UPDATE " + TABLE_NAME + " SET" + " status = ? " + "WHERE id IN (%s)";
    private static final String SQL_UPDATE_DATA = "UPDATE " + TABLE_NAME + " SET username = ?,password = ?,nickname = ?,realname = ?,age = ?,sex = ?,email = ?,regtime = ?,lastlogintime = ?,level = ?,accountType = ?,status = ? WHERE id = ?";
    //    查询数据一般方法，及注意事项
    //注意点：由于主键id会唯一标识一个数据项，有些人会使用queryForObject获取数据项，若未找到目标数据时，该方法并非返回null，而是抛异常EmptyResultDataAccessException。应使用query方法，并检测返回值数据量
    private static final String SQL_SELECT_DATA = "SELECT id," + COLUME_NAMES + " FROM " + TABLE_NAME + " WHERE id = ?";
    private final String SQL_DELETE_DATE_BY_ID = "DELETE FROM" + TABLE_NAME + " WHERE id = ?";
    private LoggerAdapter logger = LoggerAdapterFactory.getLoggerAdapter(getClass());

    public UserDaoImpl() {
        super(TABLE_NAME);
    }

    @Override
    public int insert(final User user) {
        //获取新增数据的自增id
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(SQL_INSERT_DATA, Statement.RETURN_GENERATED_KEYS);
                int i = 0;
                //ps.setString(++i, user.getId());
                ps.setString(++i, user.getUsername());
                ps.setString(++i, user.getPassword());
                ps.setString(++i, user.getNickname());
                ps.setString(++i, user.getRealname());
                ps.setInt(++i, user.getAge());
                ps.setInt(++i, user.getSex());
                ps.setString(++i, user.getEmail());
                ps.setTimestamp(++i, user.getRegTime());
                ps.setTimestamp(++i, user.getLastLoginTime());
                ps.setInt(++i, user.getLevel());
                ps.setString(++i, user.getAccountType());
                ps.setString(++i, user.getStatus());
                return ps;
            }
        }, keyHolder);
        return keyHolder.getKey().intValue();
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
//        SqlParameterSource parameterSource = new MapSqlParameterSource()
//                .addValue("username", user.getUsername())
//                .addValue("password", user.getPassword());
//        Number id = simpleJdbcInsert.withTableName("system_user").usingGeneratedKeyColumns("id").executeAndReturnKey(parameterSource);
//        user.setId(id.toString());
//        return 1;
    }

    @Override
    public int delete(String id) {
        return getJdbcTemplate().update(SQL_DELETE_DATE_BY_ID, id);
    }

    public int delete(List<String> ids) {
        if (ids == null || ids.size() == 0) {
            throw new IllegalArgumentException("ids is empty");
        }
        String idsText = StringUtils.join(ids, ",");
        String sql = String.format(SQL_DELETE_DATE, idsText);
        return getJdbcTemplate().update(sql);
    }

    @Override
    public int update(User user) {
        return getJdbcTemplate().update(SQL_UPDATE_DATA, user.getUsername(), user.getPassword(), user.getNickname(), user.getRealname(), user.getAge(), user.getSex(), user.getEmail(), user.getRegTime(), user.getLastLoginTime(), user.getLevel(), user.getAccountType(), user.getStatus(), user.getId());
    }

    public void updateStatus(List<Integer> ids, String status) {
        if (ids == null || ids.size() == 0) {
            throw new IllegalArgumentException("ids is empty");
        }
        String idsText = StringUtils.join(ids, ",");
        String sql = String.format(SQL_UPDATE_STATUS, idsText);
        getJdbcTemplate().update(sql, status);
    }

    @Override
    public User get(String id) {
        //List<User> userList = this.jdbcTemplate.query(getSql, new UserMapper(), id);
        List<User> userList = getJdbcTemplate().query(SQL_SELECT_DATA, new Object[]{id}, new UserMapper());
        return userList.size() > 0 ? userList.get(0) : null;
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
//            if (!Common.isEmpty(user.getSex())) {
//                listSql += " AND sex = '" + user.getSex() + "'";
//            }
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
        return getJdbcTemplate().query(listSql, new UserMapper());
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
//            if (!Common.isEmpty(user.getSex())) {
//                pageSql += " AND sex = '" + user.getSex() + "'";
//            }
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
        List<User> userList = getJdbcTemplate().query(pageSql, new UserMapper());
//        long pageCount = this.jdbcTemplate.queryForObject("select count(*) from system_user", Long.class);
//        pageView.setRecords(userList);
//        pageView.setPageCount(pageCount);
        return null;
    }


    //SQL IN 语句
//    IN语句中的数据项由逗号分隔，数量不固定，"?"仅支持单参数的替换，因此无法使用。此时只能拼接SQL字符串，如更新一批数据的status值，简单有效的实现方式如下

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
            user.setSex(rs.getInt("sex"));
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
