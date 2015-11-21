package xyz.dongxiaoxia.hellospring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import xyz.dongxiaoxia.hellospring.core.entity.Role;
import xyz.dongxiaoxia.hellospring.core.entity.UserRole;
import xyz.dongxiaoxia.hellospring.core.repository.RoleDao;
import xyz.dongxiaoxia.hellospring.service.RoleService;
import xyz.dongxiaoxia.hellospring.util.PageView;

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
    public PageView query(PageView pageView, Role role) {
        List<Role> list = roleDao.page(pageView, role);
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    public void add(Role role) {
        roleDao.insert(role);
    }

    @Override
    public void delete(String id) {
        roleDao.delete(id);
    }

    @Override
    public void modify(Role role) {
        roleDao.update(role);
    }

    @Override
    public Role getById(String id) {
        return roleDao.get(id);
    }

    @Override
    public List<Role> findAll() {
        return roleDao.list(null);
    }

    @Override
    public void saveUserRole(UserRole userRole) {
        roleDao.deleteUserRole(userRole.getUserId().toString());
        roleDao.saveUserRole(userRole);
    }
}
