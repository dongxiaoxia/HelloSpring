package xyz.dongxiaoxia.hellospring.core.repository.impl;

import org.springframework.stereotype.Repository;
import xyz.dongxiaoxia.hellospring.core.entity.User;
import xyz.dongxiaoxia.hellospring.core.repository.UserDao;
import xyz.dongxiaoxia.hellospring.logging.LoggerAdapter;
import xyz.dongxiaoxia.hellospring.logging.LoggerAdapterFactory;

/**
 * Created by chenwendong on 2015/10/29.
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
    private LoggerAdapter logger = LoggerAdapterFactory.getLoggerAdapter(getClass());

    /*public int insert(final User user) {
        //获取新增数据的自增id
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("SQL_INSERT_DATA", Statement.RETURN_GENERATED_KEYS);
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
        *//*直接使用jdbcTemplate*//*
//        return this.jdbcTemplate.update(insetSql, user.getUsername(), user.getPassword(), user.getNickname(), user.getRealname(), user.getAge(), user.getSex(), user.getEmail(), user.getRegTime(), user.getLastLoginTime(), user.getLevel(), user.getAccountType(), user.getStatus());
         *//*使用simpleJdbcInsert*//*
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
         *//*使用SqlParameterSource*//*
        // SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);
//        SqlParameterSource parameterSource = new MapSqlParameterSource()
//                .addValue("username", user.getUsername())
//                .addValue("password", user.getPassword());
//        Number id = simpleJdbcInsert.withTableName("system_user").usingGeneratedKeyColumns("id").executeAndReturnKey(parameterSource);
//        user.setId(id.toString());
//        return 1;
    }

//    public int delete(String id) {
//        return getJdbcTemplate().update(SQL_DELETE_DATE_BY_ID, id);
//    }
    */

    /*//Here is a simple query for getting the number of rows in a relation:
    int rowCount = getJdbcTemplate().queryForObject("select count(*) from SYSTEM_USER ", Integer.class);

    //A simple query using a bind variable
    int countOfActorsNamedJoe = getJdbcTemplate().queryForObject("select count(*) from SYSTEM_USER where username = ?", Integer.class, "Joe");

    //Querying for a String
    String lastName = getJdbcTemplate().queryForObject(
            "select last_name from t_actor where id = ?",
            new Object[]{1212L}, String.class);*/


    /*private static final class UserMapper implements RowMapper<User> {
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
    }*/

}
