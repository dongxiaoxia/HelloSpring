package xyz.dongxiaoxia.hellospring.core.repository;

import xyz.dongxiaoxia.hellospring.core.entity.Role;
import xyz.dongxiaoxia.hellospring.core.entity.UserRole;

import java.util.List;

/**
 * Created by Administrator on 2015/11/8.
 */
public interface RoleDao {
    int insert(Role role);

    int delete(String id);

    int update(Role role);

    Role get(String id);

    List<Role> list();

    List<Role> findRoleByUsername(String userId);

    void deleteUserRole(String userId);

    void saveUserRole(UserRole userRole);
}
