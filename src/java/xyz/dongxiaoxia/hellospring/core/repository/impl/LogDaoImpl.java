package xyz.dongxiaoxia.hellospring.core.repository.impl;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import xyz.dongxiaoxia.hellospring.core.entity.Log;
import xyz.dongxiaoxia.hellospring.core.repository.LogDao;
import xyz.dongxiaoxia.hellospring.util.Paging;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2015/11/7.
 */
@Repository
public class LogDaoImpl extends BaseDaoImpl implements LogDao {

    private static final String TABLE_NAME = "SYSTEM_LOG";

    public LogDaoImpl() {
        super(TABLE_NAME);
    }


    public int insert(Log log) {
        String sql = "INSERT INTO SYSTEM_LOG (description,module,method,logType,requestIp,exceptionCode,exceptionDetail,params,createBy,createDate) values (?,?,?,?,?,?,?,?,?,?)";
        return getJdbcTemplate().update(sql, new Object[]{log.getDescription(), log.getModule(), log.getMethod(), log.getLogType(), log.getRequestIp(), log.getExceptionCode(), log.getExceptionDetail(), log.getParams(), log.getCreateBy(), log.getCreateDate()});
    }

    @Override
    public int delete(String id) {
        return getJdbcTemplate().update("DELETE FROM system_log WHERE id = ?", id);
    }


    public int update(Log log) {
        return getJdbcTemplate().update("UPDATE system_log SET description = ?,module = ? ,method = ? ,logType = ? ,requestIp = ? ,exceptionCode = ?, exceptionDetail = ? , params = ?, createBy = ?,createDate = ? WHERE id = ?", log.getDescription(), log.getModule(), log.getMethod(), log.getLogType(), log.getRequestIp(), log.getExceptionCode(), log.getExceptionDetail(), log.getParams(), log.getCreateBy(), log.getCreateDate(), log.getId());
    }

    @Override
    public Log get(String id) {
        return getJdbcTemplate().queryForObject("select * from system_log where id = ?", new LogMapper(), id);
    }


    public List<Log> list(Log log) {
        return getJdbcTemplate().query("select * from system_log", new LogMapper());
    }


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
