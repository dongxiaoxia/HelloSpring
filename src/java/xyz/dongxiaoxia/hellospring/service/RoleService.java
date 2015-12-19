package xyz.dongxiaoxia.hellospring.service;

import xyz.dongxiaoxia.hellospring.core.entity.Role;
import xyz.dongxiaoxia.hellospring.core.entity.UserRole;
import xyz.dongxiaoxia.hellospring.util.Paging;

import java.util.List;

/**
 * Created by Administrator on 2015/11/19.
 */
public interface RoleService {
    Paging<Role> page(Role role, int pageStart, int pageSize);

    void add(Role role);

    void delete(String id);

    void modify(Role role);

    Role get(String id);

    List<Role> findAll();

    void saveUserRole(UserRole userRole);

    List<Role> listRoleByUserId(String id);
}
