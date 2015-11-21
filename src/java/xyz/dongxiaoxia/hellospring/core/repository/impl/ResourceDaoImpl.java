package xyz.dongxiaoxia.hellospring.core.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import xyz.dongxiaoxia.hellospring.core.entity.Resource;
import xyz.dongxiaoxia.hellospring.core.entity.ResourceRole;
import xyz.dongxiaoxia.hellospring.core.repository.ResourceDao;
import xyz.dongxiaoxia.hellospring.util.PageView;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2015/11/8.
 */
@Repository
public class ResourceDaoImpl implements ResourceDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int insert(Resource resource) {
        return this.jdbcTemplate.update("INSERT INTO system_resource (name,parentId,resKey,type,resUrl,level,description ) VALUES (?,?,?,?,?,?,?)", resource.getName(), resource.getParentId(), resource.getResKey(), resource.getType(), resource.getResUrl(), resource.getLevel(), resource.getDescription());
    }

    @Override
    public int delete(String id) {
        return this.jdbcTemplate.update("DELETE From system_resource WHERE id = ?", id);
    }

    @Override
    public int update(Resource resource) {
        return this.jdbcTemplate.update("UPDATE system_resource SET name = ?,parentId = ?,resKey = ?,type = ?,resUrl = ?,level = ?,description = ? WHERE id = ?", resource.getName(), resource.getParentId(), resource.getResKey(), resource.getType(), resource.getResUrl(), resource.getLevel(), resource.getDescription(), resource.getId());
    }

    @Override
    public Resource get(String id) {
        return this.jdbcTemplate.queryForObject("select id,name,parentId,resKey,type,resUrl,level,description from system_resource where id = ?", new ResouceMapper(), id);
    }

    @Override
    public List<Resource> list() {
        return this.jdbcTemplate.query("select * from system_resource", new ResouceMapper());
    }

    @Override
    public List<Resource> query(PageView pageView, Resource resource) {
        return null;
    }

    //<!-- 根据用户Id获取该用户的权限-->
    @Override
    public List<Resource> getUserResources(String userId) {
        return this.jdbcTemplate.query("SELECT re.* FROM system_role r JOIN system_role_resource rr ON r.id = rr.role_id JOIN system_resource re  ON re.id = rr.resource_id JOIN system_user_role ur ON ur.role_id = r.id JOIN SYSTEM_USER u ON u.id = ur.user_id WHERE u.id = ? ORDER BY re.level", new ResouceMapper(), userId);
    }

    //<!-- 根据用户名获取该用户的权限-->
    @Override
    public List<Resource> getResourcesByUserName(String username) {
        return this.jdbcTemplate.query("SELECT re.* FROM system_role r JOIN system_role_resource rr ON r.id = rr.role_id JOIN system_resource re  ON re.id = rr.resource_id JOIN system_user_role ur ON ur.role_id = r.id JOIN SYSTEM_USER u ON u.id = ur.user_id WHERE u.username = ? ORDER BY re.level", new ResouceMapper(), username);
    }

    //<!-- 根据roleId获取该用户的权限-->
    @Override
    public List<Resource> getRoleResources(String roleId) {
        return this.jdbcTemplate.query("SELECT re.* FROM system_role r JOIN system_role_resource rr ON r.id = rr.role_id JOIN system_resource re  ON re.id = rr.resource_id  ORDER BY re.level", new ResouceMapper(), roleId);
    }

    @Override
    public void saveRoleResource(ResourceRole resourceRole) {
        this.jdbcTemplate.update("INSERT INTO system_role_resource (resource_id,role_id ) VALUES (?,?)", resourceRole.getRescId(), resourceRole.getRoleId());
    }

    @Override
    public void deleteRoleRescours(String roleId) {
        this.jdbcTemplate.update("DELETE From system_role_resource WHERE role_id = ?", roleId);
    }

    //springSecurity
    public List<Resource> listResourceAndRoleName() {
        return this.jdbcTemplate.query("SELECT re.resUrl,r.name FROM system_role r JOIN system_role_resource rr ON r.id = rr.role_id JOIN system_resource re ON re.id = rr.resource_id ORDER BY re.level", new ResouceMapper());
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
