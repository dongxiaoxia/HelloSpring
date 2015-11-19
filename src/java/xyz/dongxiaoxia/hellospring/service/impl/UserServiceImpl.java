package xyz.dongxiaoxia.hellospring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import xyz.dongxiaoxia.hellospring.core.entity.Role;
import xyz.dongxiaoxia.hellospring.core.entity.User;
import xyz.dongxiaoxia.hellospring.core.repository.UserDao;
import xyz.dongxiaoxia.hellospring.service.UserService;
import xyz.dongxiaoxia.hellospring.util.PageView;

import java.util.List;

/**
 * Created by Administrator on 2015/11/19.
 */
@Component
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public PageView query(PageView pageView, User user) {
        List<User> list = userDao.query(pageView, user);
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    public void add(User user) {
        userDao.insert(user);
    }

    @Override
    public void delete(String id) {
        userDao.delete(id);
    }

    @Override
    public void modify(User user) {
        userDao.update(user);
    }

    @Override
    public User getById(String id) {
        return userDao.get(id);
    }

    @Override
    public int countUser(String userName, String userPassword) {
        return userDao.countUser(userName, userPassword);
    }

    @Override
    public User querySingleUser(String userName) {
        return userDao.findByUsername(userName);
    }

    @Override
    public List<Role> findbyUserRole(String userId) {
        return userDao.findRoleByUserId(userId);
    }
}
