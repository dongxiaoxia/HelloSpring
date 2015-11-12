package xyz.dongxiaoxia.hellospring.core.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import xyz.dongxiaoxia.hellospring.core.entity.Role;
import xyz.dongxiaoxia.hellospring.core.repository.RoleDao;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2015/11/8.
 */
@Repository
public class RoleDaoImpl implements RoleDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int insert(Role role) {
        return this.jdbcTemplate.update("INSERT INTO system_role (name ) VALUES (?)", role.getName());
    }

    @Override
    public int delete(String id) {
        return this.jdbcTemplate.update("DELETE From system_role WHERE id = ?", id);
    }

    @Override
    public int update(Role role) {
        return this.jdbcTemplate.update("UPDATE system_role SET name = ? WHERE id = ?", role.getName(), role.getId());
    }

    @Override
    public Role get(String id) {
        return this.jdbcTemplate.queryForObject("select id, name from system_role where id = ?", new RoleMapper(), id);
    }

    @Override
    public List<Role> list() {
        return this.jdbcTemplate.query("select id, name from system_role", new RoleMapper());

    }

    @Override
    public List<Role> findRoleByUsername(String userId) {
        return this.jdbcTemplate.query("SELECT r.name,u.id,ur.role_id,ur.user_id FROM system_role r,SYSTEM_USER u,system_user_role ur WHERE u.id = ur.user_id AND r.id = ur.role_id AND u.username = ?", new RoleMapper(), userId);
    }

    private static final class RoleMapper implements RowMapper<Role> {
        public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
            Role role = new Role();
            role.setId(rs.getString("id"));
            role.setName(rs.getString("name"));
            return role;
        }
    }

}
