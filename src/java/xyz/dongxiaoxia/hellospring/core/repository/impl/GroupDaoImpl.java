package xyz.dongxiaoxia.hellospring.core.repository.impl;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import xyz.dongxiaoxia.hellospring.core.entity.Group;
import xyz.dongxiaoxia.hellospring.core.repository.GroupDao;
import xyz.dongxiaoxia.hellospring.util.Paging;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2015/11/8.
 */
@Repository
public class GroupDaoImpl extends BaseDaoImpl implements GroupDao {

    @Override
    public int insert(Group group) {
        return this.jdbcTemplate.update("INSERT INTO system_group (name,parent_id ) VALUES (?,?)", group.getName(), group.getParentId());
    }

    @Override
    public int delete(String id) {
        return this.jdbcTemplate.update("DELETE From system_group WHERE id = ?", id);
    }

    @Override
    public int update(Group group) {
        return this.jdbcTemplate.update("UPDATE system_group SET name = ?,parent_id = ? WHERE id = ?", group.getName(), group.getParentId(), group.getId());
    }

    @Override
    public Group get(String id) {
        return this.jdbcTemplate.queryForObject("select id, name,parent_id from system_group where id = ?", new GroupMapper(), id);
    }

    @Override
    public List<Group> getByParentId(String parentId) {
        return this.jdbcTemplate.query("select id, name,parent_id from system_group where parent_id = ?", new GroupMapper(), parentId);
    }

    @Override
    public List<Group> list(Group group) {
        return this.jdbcTemplate.query("select id, name ,parent_id from system_group", new GroupMapper());
    }

    @Override
    public Paging<Group> page(Group group, int pageStart, int pageSize) {
        return null;
    }

    private static final class GroupMapper implements RowMapper<Group> {
        public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
            Group group = new Group();
            group.setId(rs.getString("id"));
            group.setName(rs.getString("name"));
            group.setParentId(rs.getString("parent_id"));
            return group;
        }
    }

}
