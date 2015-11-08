package xyz.dongxiaoxia.hellospring.core.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import xyz.dongxiaoxia.hellospring.core.entity.Privilege;
import xyz.dongxiaoxia.hellospring.core.repository.PrivilegeDao;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2015/11/8.
 */
@Repository
public class PrivilegeDaoImpl implements PrivilegeDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int insert(Privilege privilege) {
        return this.jdbcTemplate.update("INSERT INTO system_privilege (type ) VALUES (?)", privilege.getType());
    }

    @Override
    public int delete(String id) {
        return this.jdbcTemplate.update("DELETE From system_privilege WHERE id = ?", id);
    }

    @Override
    public int update(Privilege privilege) {
        return this.jdbcTemplate.update("UPDATE system_privilege SET type = ? WHERE id = ?", privilege.getType(), privilege.getId());
    }

    @Override
    public Privilege get(String id) {
        return this.jdbcTemplate.queryForObject("select id, type from system_privilege where id = ?", new PrivilegeMapper(), id);
    }

    @Override
    public List<Privilege> list() {
        return this.jdbcTemplate.query("select id, type from system_privilege", new PrivilegeMapper());
    }

    @Override
    public List<Privilege> listByType(String type) {
        return this.jdbcTemplate.query("select id, type from system_privilege where type = ?", new PrivilegeMapper(), type);
    }

    private static final class PrivilegeMapper implements RowMapper<Privilege> {
        public Privilege mapRow(ResultSet rs, int rowNum) throws SQLException {
            Privilege privilege = new Privilege();
            privilege.setId(rs.getString("id"));
            privilege.setType(rs.getString("type"));
            return privilege;
        }
    }

}
