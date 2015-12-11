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

    public int insert(Privilege privilege) {
        return getJdbcTemplate().update("INSERT INTO system_privilege (type ) VALUES (?)", privilege.getType());
    }

    public int update(Privilege privilege) {
        return getJdbcTemplate().update("UPDATE system_privilege SET type = ? WHERE id = ?", privilege.getType(), privilege.getId());
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
