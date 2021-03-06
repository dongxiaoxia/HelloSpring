package xyz.dongxiaoxia.hellospring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import xyz.dongxiaoxia.hellospring.core.entity.Role;
import xyz.dongxiaoxia.hellospring.core.entity.UserRole;
import xyz.dongxiaoxia.hellospring.core.repository.RoleDao;
import xyz.dongxiaoxia.hellospring.service.RoleService;
import xyz.dongxiaoxia.hellospring.util.Paging;

import java.util.List;

/**
 * Created by Administrator on 2015/11/19.
 */
@Component
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;


    @Override
    public Paging<Role> page(Role role, int pageStart, int pageSize) {
        return roleDao.$page(role, pageStart, pageSize);
    }

    @Override
    public void add(Role role) {
        roleDao.$save(role);
    }

    @Override
    public void delete(String id) {
        roleDao.$delete(id, Role.class);
    }

    @Override
    public void modify(Role role) {
        roleDao.$update(role);
    }

    @Override
    public Role get(String id) {
        return roleDao.$get(id, Role.class);
    }

    @Override
    public List<Role> findAll() {
        return roleDao.$query(null);
    }

    @Override
    public void saveUserRole(UserRole userRole) {
        roleDao.deleteUserRole(userRole.getUserId().toString());
        roleDao.saveUserRole(userRole);
    }

    @Override
    public List<Role> listRoleByUserId(String id) {
        return roleDao.listRoleByUserId(id);
    }
}
