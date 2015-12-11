package xyz.dongxiaoxia.hellospring.core.repository.impl;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import xyz.dongxiaoxia.hellospring.core.entity.Log;
import xyz.dongxiaoxia.hellospring.core.repository.LogDao;
import xyz.dongxiaoxia.hellospring.util.Paging;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 2015/11/7.
 */
@Repository
public class LogDaoImpl extends BaseDaoImpl implements LogDao {

    public Paging<Log> page(Log log, int pageStart, int pageSize) {
        return null;
    }

    private static final class LogMapper implements RowMapper<Log> {
        public Log mapRow(ResultSet rs, int rowNum) throws SQLException {
            Log log = new Log();
            log.setId(rs.getString("id"));
            log.setModule(rs.getString("module"));
            log.setDescription(rs.getString("description"));
            log.setMethod(rs.getString("method"));
            log.setLogType(rs.getLong("logType"));
            log.setRequestIp(rs.getString("requestIp"));
            log.setExceptionCode(rs.getString("exceptionCode"));
            log.setExceptionDetail(rs.getString("exceptionDetail"));
            log.setParams(rs.getString("params"));
            log.setCreateBy(rs.getString("createBy"));
            log.setCreateDate(rs.getDate("createDate"));
            return log;
        }
    }
}
