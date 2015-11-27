package xyz.dongxiaoxia.hellospring.core.repository.impl;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import xyz.dongxiaoxia.hellospring.core.entity.Operation;
import xyz.dongxiaoxia.hellospring.core.repository.OperationDao;
import xyz.dongxiaoxia.hellospring.util.Paging;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2015/11/8.
 */
@Repository
public class OperationDaoImpl extends BaseDaoImpl implements OperationDao {

    @Override
    public int insert(Operation operation) {
        return this.jdbcTemplate.update("INSERT INTO system_operation (name ,code,prefix_url,parent_id) VALUES (?,?,?,?)", operation.getName(), operation.getCode(), operation.getPrefixUrl(), operation.getParentId());
    }

    @Override
    public int delete(String id) {
        return this.jdbcTemplate.update("DELETE From system_operation WHERE id = ?", id);
    }

    @Override
    public int update(Operation operation) {
        return this.jdbcTemplate.update("UPDATE system_operation SET name = ?,code = ?,prefix_url = ?,parent_id = ? WHERE id = ?", operation.getName(), operation.getCode(), operation.getPrefixUrl(), operation.getParentId(), operation.getId());
    }

    @Override
    public Operation get(String id) {
        return this.jdbcTemplate.queryForObject("select * from system_operation where id = ?", new OperationMapper(), id);
    }

    @Override
    public List<Operation> listByParentId(String parentId) {
        return this.jdbcTemplate.query("select * from system_operation where parent_id = ?", new OperationMapper(), parentId);
    }

    @Override
    public List<Operation> listByPrefixUrl(String prefixUrl) {
        return this.jdbcTemplate.query("select * from system_operation where prefix_url = ?", new OperationMapper(), prefixUrl);
    }

    @Override
    public List<Operation> list(Operation operation) {
        return this.jdbcTemplate.query("select * from system_operation", new OperationMapper());
    }

    @Override
    public Paging<Operation> page(Operation operation, int pageStart, int pageSize) {
        return null;
    }

    private static final class OperationMapper implements RowMapper<Operation> {
        public Operation mapRow(ResultSet rs, int rowNum) throws SQLException {
            Operation operation = new Operation();
            operation.setId(rs.getString("id"));
            operation.setName(rs.getString("name"));
            operation.setCode(rs.getString("code"));
            operation.setParentId(rs.getString("parent_id"));
            operation.setPrefixUrl(rs.getString("prefix_url"));
            return operation;
        }
    }


}
