package xyz.dongxiaoxia.hellospring.core.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import xyz.dongxiaoxia.hellospring.core.entity.Log;
import xyz.dongxiaoxia.hellospring.core.repository.LogDao;

import javax.sql.DataSource;

/**
 * Created by Administrator on 2015/11/7.
 */
@Repository
public class LogDaoImpl implements LogDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Log log) {
        String sql = "INSERT INTO SYSTEM_LOG (description,method,logType,requestIp,exceptionCode,exceptionDetail,params,createBy,createDate) values (?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{log.getDescription(), log.getMethod(), log.getLogType(), log.getRequestIp(), log.getExceptionCode(), log.getExceptionDetail(), log.getParams(), log.getCreateBy(), log.getCreateDate()});
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(Log log) {

    }

    @Override
    public Log get(String id) {
        return null;
    }
}
