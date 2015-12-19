package xyz.dongxiaoxia.hellospring.core.repository;

import xyz.dongxiaoxia.hellospring.core.entity.Role;
import xyz.dongxiaoxia.hellospring.core.entity.UserRole;

import java.util.List;

/**
 * Created by Administrator on 2015/11/8.
 */
public interface RoleDao extends BaseDao<Role> {

    List<Role> findRoleByUsername(String userId);

    void deleteUserRole(String userId);

    void saveUserRole(UserRole userRole);

    List<Role> listRoleByResourceId(String id);

    List<Role> listRoleByUserId(String id);
}
