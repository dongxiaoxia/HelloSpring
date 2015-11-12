package xyz.dongxiaoxia.hellospring.core.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import xyz.dongxiaoxia.hellospring.core.entity.Resource;
import xyz.dongxiaoxia.hellospring.core.repository.ResourceDao;

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
        return this.jdbcTemplate.update("INSERT INTO system_resource (name ) VALUES (?)", resource.getName());
    }

    @Override
    public int delete(String id) {
        return this.jdbcTemplate.update("DELETE From system_resource WHERE id = ?", id);
    }

    @Override
    public int update(Resource resource) {
        return this.jdbcTemplate.update("UPDATE system_resource SET name = ? WHERE id = ?", resource.getName(), resource.getId());
    }

    @Override
    public Resource get(String id) {
        return this.jdbcTemplate.queryForObject("select id, name from system_resource where id = ?", new ResouceMapper(), id);
    }

    @Override
    public List<Resource> list() {
        return this.jdbcTemplate.query("select * from system_resource", new ResouceMapper());
    }

    //sptingSecurity
    public List<Resource> listResourceAndRoleName() {
        return this.jdbcTemplate.query("SELECT re.res_string,r.name FROM system_role r JOIN system_role_resource rr ON r.id = rr.role_id JOIN system_resource re ON re.id = rr.resource_id ORDER BY re.priority", new ResouceMapper());
    }

    private static final class ResouceMapper implements RowMapper<Resource> {
        public Resource mapRow(ResultSet rs, int rowNum) throws SQLException {
            Resource resource = new Resource();
            resource.setId(rs.getString("id"));
            resource.setName(rs.getString("name"));
            resource.setRes_string(rs.getString("res_string"));
            resource.setDesc(rs.getString("desc"));
            resource.setType(rs.getString("type"));
            resource.setPriority(rs.getString("priority"));
            return resource;
        }
    }
}
