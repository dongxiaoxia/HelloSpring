package xyz.dongxiaoxia.hellospring.core.repository.impl;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import xyz.dongxiaoxia.hellospring.core.entity.Privilege;
import xyz.dongxiaoxia.hellospring.core.repository.PrivilegeDao;
import xyz.dongxiaoxia.hellospring.util.Paging;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2015/11/8.
 */
@Repository
public class PrivilegeDaoImpl extends BaseDaoImpl implements PrivilegeDao {

    private static final String TABLE_NAME = "SYSTEM_PRIVILEGE";

    public PrivilegeDaoImpl() {
        super(TABLE_NAME);
    }


    public int insert(Privilege privilege) {
        return getJdbcTemplate().update("INSERT INTO system_privilege (type ) VALUES (?)", privilege.getType());
    }

    @Override
    public int delete(String id) {
        return getJdbcTemplate().update("DELETE From system_privilege WHERE id = ?", id);
    }


    public int update(Privilege privilege) {
        return getJdbcTemplate().update("UPDATE system_privilege SET type = ? WHERE id = ?", privilege.getType(), privilege.getId());
    }

    @Override
    public Privilege get(String id) {
        return getJdbcTemplate().queryForObject("select id, type from system_privilege where id = ?", new PrivilegeMapper(), id);
    }


    public List<Privilege> list(Privilege privilege) {
        return getJdbcTemplate().query("select id, type from system_privilege", new PrivilegeMapper());
    }


    public Paging<Privilege> page(Privilege privilege, int paeStart, int pageSize) {
        return null;
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
