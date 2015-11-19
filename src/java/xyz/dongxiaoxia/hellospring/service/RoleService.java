package xyz.dongxiaoxia.hellospring.service;

import xyz.dongxiaoxia.hellospring.core.entity.Role;
import xyz.dongxiaoxia.hellospring.core.entity.UserRole;
import xyz.dongxiaoxia.hellospring.util.PageView;

import java.util.List;

/**
 * Created by Administrator on 2015/11/19.
 */
public interface RoleService {
    PageView query(PageView pageView, Role role);

    void add(Role role);

    void delete(String id);

    void modify(Role role);

    Role getById(String id);

    List<Role> findAll();

    void saveUserRole(UserRole userRole);
}
