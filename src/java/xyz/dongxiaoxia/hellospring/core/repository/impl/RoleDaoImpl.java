package xyz.dongxiaoxia.hellospring.core.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import xyz.dongxiaoxia.hellospring.core.entity.Resource;
import xyz.dongxiaoxia.hellospring.core.entity.Role;
import xyz.dongxiaoxia.hellospring.core.entity.UserRole;
import xyz.dongxiaoxia.hellospring.core.repository.RoleDao;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        return this.jdbcTemplate.update("INSERT INTO system_role (name,desc,roleKey,enable ) VALUES (?,?,?,?)", role.getName(), role.getDescription(), role.getRoleKey(), role.getEnable());
    }

    @Override
    public int delete(String id) {
        return this.jdbcTemplate.update("DELETE From system_role WHERE id = ?", id);
    }

    @Override
    public int update(Role role) {
        return this.jdbcTemplate.update("UPDATE system_role SET name = ?,desc = ?,roleKey = ?,enable = ? WHERE id = ?", role.getName(), role.getDescription(), role.getRoleKey(), role.getEnable(), role.getId());
    }

    @Override
    public Role get(String id) {
        Role role = this.jdbcTemplate.queryForObject("select * from system_role where id = ?", new RoleMapper(), id);
        List<Resource> resourceList = this.jdbcTemplate.query("SELECT re.* FROM system_resource re JOIN system_role_resource rr ON rr.resource_id = re.id JOIN system_role r ON rr.role_id = r.id WHERE r.id = ?", new ResouceMapper(), role.getId());
        Set<Resource> resources = new HashSet<>(resourceList);
        role.setResources(resources);
        return role;
    }

    @Override
    public List<Role> list() {
        List<Role> roleList = this.jdbcTemplate.query("select * from system_role", new RoleMapper());
        for (Role role : roleList) {
            Set<Resource> resources = new HashSet<>(this.jdbcTemplate.query("SELECT re.* FROM system_resource re JOIN system_role_resource rr ON rr.resource_id = re.id JOIN system_role r ON rr.role_id = r.id WHERE r.id = ?", new ResouceMapper(), role.getId()));
            role.setResources(resources);
        }
        return roleList;
    }

    @Override
    public List<Role> findRoleByUsername(String userId) {
        return this.jdbcTemplate.query("SELECT r.name,u.id,ur.role_id,ur.user_id FROM system_role r,SYSTEM_USER u,system_user_role ur WHERE u.id = ur.user_id AND r.id = ur.role_id AND u.username = ?", new RoleMapper(), userId);
    }

    @Override
    public void deleteUserRole(String userId) {

    }

    @Override
    public void saveUserRole(UserRole userRole) {

    }

    private static final class RoleMapper implements RowMapper<Role> {
        public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
            Role role = new Role();
            role.setId(rs.getString("id"));
            role.setName(rs.getString("name"));
            return role;
        }
    }

    private static final class ResouceMapper implements RowMapper<Resource> {
        public Resource mapRow(ResultSet rs, int rowNum) throws SQLException {
            Resource resource = new Resource();
            resource.setId(rs.getString("id"));
            resource.setName(rs.getString("name"));
            resource.setParentId(rs.getString("parentId"));
            resource.setResKey(rs.getString("resKey"));
            resource.setType(rs.getString("type"));
            resource.setResUrl(rs.getString("resUrl"));
            resource.setLevel(rs.getInt("level"));
            resource.setDescription(rs.getString("description"));
            return resource;
        }
    }

}
