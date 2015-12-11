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

    private static final String TABLE_NAME = "SYSTEM_GROUP";

    @Override
    public List<Group> getByParentId(String parentId) {
        return getJdbcTemplate().query("select id, name,parent_id from system_group where parent_id = ?", new GroupMapper(), parentId);
    }

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
